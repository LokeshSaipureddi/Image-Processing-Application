package model;

import java.util.function.Function;

/**
 * Mock Model class to help debug.
 */
public class ModelMock implements IMEImage {
  private StringBuilder log;

  /**
   * Initialize the mock with logging objects.
   *
   * @param log  log stream
   * @param hash hashcode
   */
  public ModelMock(StringBuilder log, int hash) {
    this.log = log;
    log.append("MockCreated ").append(hash).append("\n");
  }

  @Override
  public IMEImage convolution(float[][] kernelR, float[][] kernelG, float[][] kernelB) {
    log.append("Called convolution\n");
    for (int i = 0; i < kernelR.length; i++) {
      for (int j = 0; j < kernelR[0].length; j++) {
        log.append(kernelR[i][j]);
      }
    }

    log.append("\n");
    for (int i = 0; i < kernelG.length; i++) {
      for (int j = 0; j < kernelG.length; j++) {
        log.append(kernelR[i][j]);
      }
    }

    log.append("\n");
    for (int i = 0; i < kernelB.length; i++) {
      for (int j = 0; j < kernelB.length; j++) {
        log.append(kernelR[i][j]);
      }
    }

    log.append("\n");
    return new ModelMock(log, 1);
  }

  @Override
  public IMEImage brighten(int value) {
    log.append("Called brighten\n").append(value).append("\n");
    return new ModelMock(log, 2);
  }

  @Override
  public IMEImage maxIMG() {
    log.append("Called maxIMG\n");
    return new ModelMock(log, 3);
  }

  @Override
  public IMEImage avgIMG() {
    log.append("Called avgIMG\n");
    return new ModelMock(log, 4);
  }

  @Override
  public int[][][] getImage() {
    log.append("Called getImage\n");
    return new int[][][]{{{0}}};
  }

  @Override
  public IMEImage verticalFlip() {
    log.append("Called verticalFlip\n");
    return new ModelMock(log, 5);
  }

  @Override
  public IMEImage horizontalFlip() {
    log.append("Called horizontalFlip\n");
    return new ModelMock(log, 5);
  }

  @Override
  public IMEImage linearTransformation(float[][] kernel) {
    log.append("Called linearTransformation\n");
    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        log.append(kernel[i][j]);
      }
    }
    log.append("\n");
    return new ModelMock(log, 6);
  }

  @Override
  public IMEImage combine(int[][] g, int[][] b) {
    log.append("Called combine\n");
    return new ModelMock(log, 7);
  }

  @Override
  public IMEImage splitImage(int axis) {
    log.append("Called splitImage\n");
    return new ModelMock(log, 8);
  }

  @Override
  public int[][] getR() {
    log.append("Called getR\n");
    return new int[0][];
  }

  @Override
  public int[][] getG() {
    log.append("Called getG\n");
    return new int[0][];
  }

  @Override
  public int[][] getB() {
    log.append("Called getB\n");
    return new int[0][];
  }

  /**
   * Get height of the image.
   *
   * @return height
   */
  @Override
  public int getHeight() {
    log.append("Called getHeight\n");
    return 0;
  }

  /**
   * Get width of the image.
   *
   * @return width
   */
  @Override
  public int getWidth() {
    log.append("Called getWidth\n");
    return 0;
  }


  @Override
  public IMEImage colorCorrection() {
    log.append("Called colorCorrection\n");
    return new ModelMock(log, 9);
  }

  @Override
  public IMEImage elementWiseRGB(Function<Integer, Integer> func) {
    log.append("Called elementWiseRGB\n");
    return new ModelMock(log, 10);
  }

  @Override
  public IMEImage previewVerticalSplit(IMEImage right, int ratioPercent) {
    log.append("Called previewVerticalSplit\n").append(ratioPercent).append("\n");
    return new ModelMock(log, 11);
  }

  @Override
  public IMEImage getColorHistogram() {
    log.append("Called getColorHistogram\n");
    return new ModelMock(log, 12);
  }

  @Override
  public IMEImage colorAdjust(int blacks, int mid, int whites) {
    log.append("Called colorAdjust\n")
        .append(blacks).append("\n")
        .append(mid).append("\n")
        .append(whites).append("\n");
    return new ModelMock(log, 13);
  }

  @Override
  public IMEImage compress(int val) {
    log.append("Called compress\n")
        .append(val).append("\n");
    return new ModelMock(log, 14);
  }
}

