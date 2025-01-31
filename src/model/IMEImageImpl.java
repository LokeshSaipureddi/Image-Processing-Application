package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Implementation of an Image Model (IMEImage).
 */
public class IMEImageImpl implements IMEImage {
  protected int[][] r;
  protected int[][] g;
  protected int[][] b;
  protected int height;
  protected int width;

  /**
   * Initialize with given R, G, B arrays.
   *
   * @param r red component
   * @param g green component
   * @param b blue component
   */
  public IMEImageImpl(int[][] r, int[][] g, int[][] b) {
    if (r.length != b.length || b.length != g.length) {
      throw new IllegalArgumentException("dims don't match");
    }
    if (r[0].length != b[0].length || b[0].length != g[0].length) {
      throw new IllegalArgumentException("dims don't match");
    }
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        if (r[i][j] < 0 || b[i][j] < 0 || g[i][j] < 0) {
          throw new IllegalArgumentException("pixel val can't be negative");
        }
      }
    }
    this.r = clone2d(r);
    this.g = clone2d(g);
    this.b = clone2d(b);
    height = r.length;
    width = r[0].length;
  }

  @Override
  public IMEImageImpl convolution(float[][] kernelR, float[][] kernelG, float[][] kernelB) {
    if (kernelR.length != kernelG.length || kernelG.length != kernelB.length) {
      throw new IllegalArgumentException("rgb dims do not match");
    }

    if (kernelR[0].length != kernelG[0].length || kernelG[0].length != kernelB[0].length) {
      throw new IllegalArgumentException("rgb dims do not match");
    }

    if (kernelR[0].length > width || kernelR.length > height) {
      throw new IllegalArgumentException("Kernel is smaller than the image");
    }

    int[][] resultR = new int[height][width];
    int[][] resultG = new int[height][width];
    int[][] resultB = new int[height][width];

    int[][] paddedImageRed = convPad(r, kernelR);
    int[][] paddedImageGreen = convPad(g, kernelG);
    int[][] paddedImageBlue = convPad(b, kernelB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float resR = 0.0f;
        float resG = 0.0f;
        float resB = 0.0f;
        for (int ki = 0; ki < kernelR.length; ki++) {
          for (int kj = 0; kj < kernelR[0].length; kj++) {
            resR += (paddedImageRed[i + ki][j + kj] * kernelR[ki][kj]);
            resG += (paddedImageGreen[i + ki][j + kj] * kernelG[ki][kj]);
            resB += (paddedImageBlue[i + ki][j + kj] * kernelB[ki][kj]);
          }
        }
        resultR[i][j] = (int) Math.min(max(0, resR), 255);
        resultG[i][j] = (int) Math.min(max(0, resG), 255);
        resultB[i][j] = (int) Math.min(max(0, resB), 255);
      }
    }
    return new IMEImageImpl(resultR, resultG, resultB);
  }

  private int[][] convPad(int[][] matrix, float[][] kernel) {
    int kernelHeight = kernel.length;
    int kernelWidth = kernel[0].length;
    int paddingX = kernelWidth / 2;
    int paddingY = kernelHeight / 2;
    int paddedWidth = width + 2 * paddingX;
    int paddedHeight = height + 2 * paddingY;
    int[][] paddedImage = new int[paddedHeight][paddedWidth];

    for (int y = 0; y < paddedHeight; y++) {
      for (int x = 0; x < paddedWidth; x++) {
        paddedImage[y][x] = 0;
      }
    }
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        paddedImage[y + paddingY][x + paddingX] = matrix[y][x];
      }
    }
    return paddedImage;
  }

  @Override
  public IMEImageImpl brighten(int value) {
    IMEImageImpl m = new IMEImageImpl(r, g, b);
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        m.r[i][j] = Math.min(255, max(0, r[i][j] + value));
        m.g[i][j] = Math.min(255, max(0, g[i][j] + value));
        m.b[i][j] = Math.min(255, max(0, b[i][j] + value));
      }
    }
    return m;
  }

  @Override
  public IMEImageImpl maxIMG() {
    IMEImageImpl m = new IMEImageImpl(r, g, b);
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        m.r[i][j] = max(r[i][j], max(g[i][j], b[i][j]));
      }
    }
    m.g = clone2d(m.r);
    m.b = clone2d(m.r);
    return m;
  }

  @Override
  public IMEImageImpl avgIMG() {
    IMEImageImpl m = new IMEImageImpl(r, g, b);
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        m.r[i][j] = (r[i][j] + g[i][j] + b[i][j]) / 3;
      }
    }
    m.g = clone2d(m.r);
    m.b = clone2d(m.r);
    return m;
  }

  @Override
  public int[][][] getImage() {
    int[][][] img = new int[height][width][3];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        img[i][j][0] = r[i][j];
        img[i][j][1] = g[i][j];
        img[i][j][2] = b[i][j];
      }
    }
    return img;
  }

  @Override
  public IMEImageImpl verticalFlip() {
    IMEImageImpl m = new IMEImageImpl(r, g, b);
    for (int row = 0; row < height / 2; row++) {
      for (int col = 0; col < width; col++) {
        int tmp = m.r[row][col];
        m.r[row][col] = m.r[height - row - 1][col];
        m.r[height - row - 1][col] = tmp;

        tmp = m.g[row][col];
        m.g[row][col] = m.g[height - row - 1][col];
        m.g[height - row - 1][col] = tmp;

        tmp = m.b[row][col];
        m.b[row][col] = m.b[height - row - 1][col];
        m.b[height - row - 1][col] = tmp;
      }
    }
    return m;
  }

  @Override
  public IMEImageImpl horizontalFlip() {
    IMEImageImpl m = new IMEImageImpl(r, g, b);
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width / 2; col++) {
        int tmp = m.r[row][col];
        m.r[row][col] = m.r[row][width - col - 1];
        m.r[row][width - col - 1] = tmp;

        tmp = m.g[row][col];
        m.g[row][col] = m.g[row][width - col - 1];
        m.g[row][width - col - 1] = tmp;

        tmp = m.b[row][col];
        m.b[row][col] = m.b[row][width - col - 1];
        m.b[row][width - col - 1] = tmp;
      }
    }
    return m;
  }

  @Override
  public IMEImageImpl linearTransformation(float[][] kernel) {
    if (kernel.length != 3) {
      throw new IllegalArgumentException("Kernel dims mismatch");
    }
    if (kernel[0].length != 3) {
      throw new IllegalArgumentException("Kernel dims mismatch");
    }
    IMEImageImpl m = new IMEImageImpl(r, g, b);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        m.r[i][j] = (int) (r[i][j] * kernel[0][0] + g[i][j] * kernel[0][1] + b[i][j]
            * kernel[0][2]);
        m.g[i][j] = (int) (r[i][j] * kernel[1][0] + g[i][j] * kernel[1][1] + b[i][j]
            * kernel[1][2]);
        m.b[i][j] = (int) (r[i][j] * kernel[2][0] + g[i][j] * kernel[2][1] + b[i][j]
            * kernel[2][2]);

        m.r[i][j] = Math.max(0, Math.min(255, m.r[i][j]));
        m.g[i][j] = Math.max(0, Math.min(255, m.g[i][j]));
        m.b[i][j] = Math.max(0, Math.min(255, m.b[i][j]));
      }
    }
    return m;
  }

  @Override
  public IMEImageImpl combine(int[][] g, int[][] b) {

    if (g.length != r.length && g[0].length != r[0].length) {
      throw new IllegalArgumentException("R and G dims mismatch");
    }
    if (g.length != b.length && g[0].length != b[0].length) {
      throw new IllegalArgumentException("B and G dims mismatch");
    }

    return new IMEImageImpl(r, g, b);
  }

  /**
   * Get an specific component.
   *
   * @param axis color channel
   * @return image
   */
  @Override
  public IMEImage splitImage(int axis) {
    if (axis == 0) {
      int[][] compo = getR();
      return new IMEImageImpl(compo, new int[height][width], new int[height][width]);
    }
    if (axis == 1) {
      int[][] compo = getG();
      return new IMEImageImpl(new int[height][width], compo, new int[height][width]);
    }
    if (axis == 2) {
      int[][] compo = getB();
      return new IMEImageImpl(new int[height][width], new int[height][width], compo);
    }
    throw new IllegalArgumentException("axis out of bounds");
  }


  @Override
  public int[][] getR() {
    return clone2d(r);
  }

  @Override
  public int[][] getG() {
    return clone2d(g);
  }

  @Override
  public int[][] getB() {
    return clone2d(b);
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }


  @Override
  public IMEImage compress(int val) {

    val = max(0, Math.min(100, val));

    double[][] rd = htPad(r, height, width);
    rd = haarInverseTransform(threshold(val, haarTransform(rd)));
    double[][] gd = htPad(g, height, width);
    gd = haarInverseTransform(threshold(val, haarTransform(gd)));
    double[][] bd = htPad(b, height, width);
    bd = haarInverseTransform(threshold(val, haarTransform(bd)));

    return new IMEImageImpl(htUnPad(rd, height, width), htUnPad(gd, height, width),
        htUnPad(bd, height, width));
  }

  private double[][] haarTransform(double[][] matrix) {
    int m = matrix.length;
    double[] rowsTrans = new double[m];
    double[] colTrans = new double[m];
    while (m > 1) {
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < m; j++) {
          rowsTrans[j] = matrix[i][j];
        }
        rowsTrans = htTransform(rowsTrans, m);
        for (int j = 0; j < m; j++) {
          matrix[i][j] = rowsTrans[j];
        }
      }
      for (int i = 0; i < matrix[0].length; i++) {
        for (int j = 0; j < m; j++) {
          colTrans[j] = matrix[j][i];
        }
        colTrans = htTransform(colTrans, m);
        for (int j = 0; j < m; j++) {
          matrix[j][i] = colTrans[j];
        }
      }
      m = m / 2;
    }
    return matrix;
  }

  private double[][] threshold(int val, double[][] matrix) throws IllegalArgumentException {
    if (val > 100 || val < 0) {
      throw new IllegalArgumentException("value can't less than 0 " +
          "or value can't be greater than 100");
    }
    double[] rowsTrans;
    for (int i = 0; i < matrix.length; i++) {
      rowsTrans = Arrays.copyOf(matrix[i], matrix[i].length);
      Arrays.sort(rowsTrans);
      rowsTrans = setValues(matrix[i], rowsTrans, val);
      matrix[i] = rowsTrans;
    }
    double[] colsTrans = new double[matrix[0].length];
    double[] scolsTrans = new double[matrix[0].length];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        colsTrans[j] = matrix[j][i];
        scolsTrans[j] = colsTrans[j];
      }
      Arrays.sort(scolsTrans);
      rowsTrans = setValues(colsTrans, scolsTrans, val);
      for (int j = 0; j < matrix[0].length; j++) {
        matrix[j][i] = rowsTrans[j];
      }
    }
    return matrix;
  }

  private double[] setValues(double[] base, double[] sBase, int val) {
    Set<Double> s = new HashSet<>();
    for (double v : sBase) {
      s.add(Math.abs(v));
    }
    Double[] temp = s.toArray(new Double[0]);
    Double[] tc = Arrays.copyOf(temp, temp.length);
    Arrays.sort(tc);
    int thresh1 = ((val * (tc.length - 1)) / 100);
    double threshold = tc[thresh1];
    for (int i = 0; i < sBase.length; i++) {
      if (abs(base[i]) <= threshold) {
        base[i] = 0;
      }
    }
    return base;
  }

  private double[][] haarInverseTransform(double[][] matrix) {
    int m = 2;
    int l = matrix.length;
    double[] rowsTrans;
    rowsTrans = new double[l];
    while (m <= l) {
      for (int i = 0; i < matrix[0].length; i++) {
        for (int j = 0; j < m; j++) {
          rowsTrans[j] = matrix[j][i];
        }
        rowsTrans = htInverseTransform(rowsTrans, m);
        for (int j = 0; j < m; j++) {
          matrix[j][i] = rowsTrans[j];
        }
      }
      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < m; j++) {
          rowsTrans[j] = matrix[i][j];
        }
        rowsTrans = htInverseTransform(rowsTrans, m);
        for (int j = 0; j < m; j++) {
          matrix[i][j] = rowsTrans[j];
        }
      }
      m = m * 2;
    }
    return matrix;
  }

  private double[] htTransform(double[] s, int m) {
    double[] new_s = new double[s.length];
    for (int i = 0; i < m; i += 2) {
      if (i + 1 < m) {
        double a = s[i];
        double b = s[i + 1];
        double av = (a + b) / Math.sqrt(2);
        double diff = (a - b) / Math.sqrt(2);
        new_s[i / 2] = av;
        new_s[m / 2 + i / 2] = diff;
      }
    }
    for (int i = 0; i < m; i++) {
      s[i] = new_s[i];
    }
    return new_s;
  }

  private double[] htInverseTransform(double[] s, int m) {
    double[] new_s = new double[s.length];
    for (int i = 0; i < m; i += 2) {
      double a = s[i / 2];
      double b = s[m / 2 + i / 2];
      double av = (a + b) / Math.sqrt(2);
      double diff = (a - b) / Math.sqrt(2);
      new_s[i] = av;
      new_s[i + 1] = diff;
    }
    for (int i = 0; i < m; i++) {
      s[i] = new_s[i];
    }
    return s;
  }

  private double[][] htPad(int[][] s, int col, int row) {
    int size = max(col, row);
    while ((size & (size - 1)) != 0) {
      size = size + 1;
    }
    double[][] newArray = new double[size][size];
    for (int i = 0; i < s.length; i++) {
      for (int j = 0; j < s[0].length; j++) {
        newArray[i][j] = s[i][j];
      }
    }
    return newArray;
  }

  private int[][] htUnPad(double[][] s, int col, int row) {
    int[][] newArray = new int[col][row];
    for (int i = 0; i < newArray.length; i++) {
      for (int j = 0; j < newArray[0].length; j++) {
        if ((int) s[i][j] < 0) {
          s[i][j] = 0;
        } else if ((int) s[i][j] > 255) {
          s[i][j] = 255;
        }
        newArray[i][j] = (int) s[i][j];
      }
    }
    return newArray;
  }

  private int[][] clone2d(int[][] ar) {
    int[][] result = ar.clone();
    for (int i = 0; i < ar.length; i++) {
      result[i] = ar[i].clone();
    }
    return result;
  }

  private int[] getDist(int axis) {
    int[] dist = new int[256];
    for (int i = 0; i < 256; i++) {
      dist[i] = 0;
    }
    int[][] ptr;
    if (axis == 0) {
      ptr = r;
    } else if (axis == 1) {
      ptr = g;
    } else if (axis == 2) {
      ptr = b;
    } else {
      throw new IllegalArgumentException("Axis out of bounds");
    }
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int val = ptr[i][j];
        if (val > 255 || val < 0) {
          throw new IllegalArgumentException("Image is Corrupted");
        }
        dist[val]++;
      }
    }
    return dist;
  }

  @Override
  public IMEImage colorAdjust(int blacks, int mid, int whites) {
    blacks = max(0, Math.min(255, blacks));
    mid = max(blacks, Math.min(255, mid));
    whites = max(mid, Math.min(255, whites));

    float norm =
        (float) (Math.pow(blacks, 2) * (mid - whites) - blacks * ((Math.pow(mid, 2) -
            Math.pow(whites, 2))));
    norm += (float) (whites * Math.pow(mid, 2) - mid * Math.pow(whites, 2));

    float coeffAa = (float) (-blacks * (128 - 255) + 128 * whites - 255 * mid);
    float coeffAb =
        (float) (Math.pow(blacks, 2) * (128 - 255) + 255 * Math.pow(mid, 2) - 128 *
            Math.pow(whites, 2));
    float coeffAc =
        (float) (Math.pow(blacks, 2) * (255 * mid - 128 * whites) - blacks * (255 * Math.pow(mid, 2)
            - 128 * Math.pow(whites, 2)));
    coeffAa /= norm;
    coeffAb /= norm;
    coeffAc /= norm;

    final float finalCoeffAa = coeffAa;
    final float finalCoeffAb = coeffAb;
    final float finalCoeffAc = coeffAc;
    return elementWiseRGB(x -> (int) (finalCoeffAa * Math.pow(x, 2) + finalCoeffAb * x +
        finalCoeffAc));
  }

  @Override
  public IMEImage colorCorrection() {
    int rInd = getMaxDistIndex(0);
    int gInd = getMaxDistIndex(1);
    int bInd = getMaxDistIndex(2);

    int fInd = (rInd + gInd + bInd) / 3;
    return new IMEImageImpl(elementWise(x -> x + fInd - rInd, 0),
        elementWise(x -> x + fInd - gInd, 1),
        elementWise(x -> x + fInd - bInd, 2));
  }

  private int getMaxDistIndex(int axis) {
    int mx = -1;
    int ind = -1;
    int[] dst = getDist(axis);
    for (int i = 11; i < 245; i++) {
      if (mx < dst[i]) {
        mx = dst[i];
        ind = i;
      }
    }
    return ind;
  }

  @Override
  public IMEImage elementWiseRGB(Function<Integer, Integer> func) {
    IMEImageImpl m = new IMEImageImpl(r, g, b);
    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        m.r[i][j] = Math.min(255, max(0, func.apply(r[i][j])));
        m.g[i][j] = Math.min(255, max(0, func.apply(g[i][j])));
        m.b[i][j] = Math.min(255, max(0, func.apply(b[i][j])));
      }
    }
    return m;
  }

  private int[][] elementWise(Function<Integer, Integer> func, int axis) {
    int[][] m = new int[height][width];
    int[][] ptr;
    if (axis == 0) {
      ptr = r;
    } else if (axis == 1) {
      ptr = g;
    } else if (axis == 2) {
      ptr = b;
    } else {
      throw new IllegalArgumentException("Axis out of bounds");
    }

    for (int i = 0; i < r.length; i++) {
      for (int j = 0; j < r[0].length; j++) {
        m[i][j] = Math.min(255, max(0, func.apply(ptr[i][j])));
      }
    }
    return m;
  }

  @Override
  public IMEImage previewVerticalSplit(IMEImage right, int ratioPercent) {
    ratioPercent = max(0, Math.min(100, ratioPercent));

    IMEImageImpl m = new IMEImageImpl(r, g, b);
    int[][] rightR = right.getR();
    int[][] rightG = right.getG();
    int[][] rightB = right.getB();
    for (int i = 0; i < r.length; i++) {
      for (int j = r[0].length * ratioPercent / 100; j < r[0].length; j++) {
        m.r[i][j] = Math.min(255, max(0, rightR[i][j]));
        m.g[i][j] = Math.min(255, max(0, rightG[i][j]));
        m.b[i][j] = Math.min(255, max(0, rightB[i][j]));
      }
    }
    return m;
  }

  @Override
  public IMEImage getColorHistogram() {
    DrawImage img = new DrawImageImpl(256, 256);
    int[] redDist = getDist(0);
    int[] greenDist = getDist(1);
    int[] blueDist = getDist(2);

    int scaler = -1;
    for (int i = 0; i < 256; i++) {
      if (scaler < redDist[i]) {
        scaler = redDist[i];
      }
      if (scaler < greenDist[i]) {
        scaler = greenDist[i];
      }
      if (scaler < blueDist[i]) {
        scaler = blueDist[i];
      }
    }

    for (int i = 0; i < 256; i++) {
      redDist[i] = (redDist[i] * 255) / scaler;
      greenDist[i] = (greenDist[i] * 255) / scaler;
      blueDist[i] = (blueDist[i] * 255) / scaler;
    }

    img.drawLineGraph(redDist, 'r');
    img.drawLineGraph(greenDist, 'g');
    img.drawLineGraph(blueDist, 'b');
    return img.render();
  }
}
