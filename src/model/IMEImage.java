package model;

import java.util.function.Function;

/**
 * Image Model interface, representing transforms and processes on an Image.
 */
public interface IMEImage {

  /**
   * Run convolution operation.
   *
   * @param kernelR kernel for r channel
   * @param kernelG kernel for g channel
   * @param kernelB kernel for b channel
   * @return result image
   */
  IMEImage convolution(float[][] kernelR, float[][] kernelG, float[][] kernelB);

  /**
   * Brighten an image.
   *
   * @param value value to brighten
   * @return result image
   */
  IMEImage brighten(int value);

  /**
   * Get Grayscale image, having max r, g, b value for each respective pixel.
   *
   * @return result image
   */
  IMEImage maxIMG();

  /**
   * Get Grayscale image, having average r, g, b value for each respective pixel.
   *
   * @return result image
   */
  IMEImage avgIMG();

  /**
   * Get image as a 3d int array.
   *
   * @return image
   */
  int[][][] getImage();

  /**
   * Vertically flip the image.
   *
   * @return result image
   */
  IMEImage verticalFlip();

  /**
   * Horizontally flip the image.
   *
   * @return result image
   */
  IMEImage horizontalFlip();

  /**
   * Linear transform the image.
   *
   * @param kernel kernel to transform
   * @return result image
   */
  IMEImage linearTransformation(float[][] kernel);

  /**
   * Combine G and B of given images with R of this image.
   *
   * @param g source of G channel
   * @param b source of B channel
   * @return result image
   */
  IMEImage combine(int[][] g, int[][] b);

  /**
   * Get the grayscale image of a specific component of the image.
   *
   * @param axis channel to get
   * @return result image
   */
  IMEImage splitImage(int axis);

  /**
   * Get Red channel array.
   *
   * @return red channel
   */
  int[][] getR();

  /**
   * Get Green channel array.
   *
   * @return green channel
   */
  int[][] getG();

  /**
   * Get Blue channel array.
   *
   * @return blue channel
   */
  int[][] getB();

  /**
   * Get height of the image.
   *
   * @return height
   */
  int getHeight();

  /**
   * Get width of the image.
   *
   * @return width
   */
  int getWidth();

  /**
   * Compress the image using Haar Transforms. I.e. Haar Transform in the image, then apply
   * thresholding and then Inverse Haar Transform the image.
   *
   * @param val percentage to compress
   * @return compressed image
   */
  IMEImage compress(int val);

  /**
   * Color correct the image, by aligning the most frequent values of R, G and B channel. To
   * align, a constant is added to every value of the channel.
   *
   * @return color corrected image
   */
  IMEImage colorCorrection();

  /**
   * Apply a function on every r, g and b pixel value in the image.
   *
   * @param func function to apply
   * @return result image
   */
  IMEImage elementWiseRGB(Function<Integer, Integer> func);

  /**
   * Get a preview by splitting the image by a vertical line. On the left show the current image,
   * and on the right show the given image.
   *
   * @param right        image to show on the right
   * @param ratioPercent percentage of the left image to be shown
   * @return result image
   */
  IMEImage previewVerticalSplit(IMEImage right, int ratioPercent);

  /**
   * Get the color histogram of the image.
   *
   * @return color histogram
   */
  IMEImage getColorHistogram();

  /**
   * Color Adjust the blacks, mid and whites of an image. Transforming the pixel values with the
   * resulting curve of blacks, mid and whites
   *
   * @param blacks black value
   * @param mid    mid value
   * @param whites white value
   * @return transformed image
   */
  IMEImage colorAdjust(int blacks, int mid, int whites);
}
