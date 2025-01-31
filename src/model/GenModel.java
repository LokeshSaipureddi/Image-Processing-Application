package model;

import java.awt.image.BufferedImage;

import controller.IMEImageIO;

/**
 * Class to Generate a IMEImage Model Instance.
 */
public class GenModel implements IMGModel {

  /**
   * Create the model instance.
   *
   * @param r red image channel
   * @param g green image channel
   * @param b blue image channel
   * @return image Model
   */
  public IMEImage create(int[][] r, int[][] g, int[][] b) {
    return new IMEImageImpl(r, g, b);
  }

  /**
   * Create the model instance.
   *
   * @param path path to the image
   * @return image Model
   */
  public IMEImage create(String path) {
    return IMEImageIO.read(path);
  }


  /**
   * Create the model instance.
   *
   * @param img BufferedImage image instance
   * @return image model
   */
  public IMEImage create(BufferedImage img) {
    return IMEImageIO.readBufferedImage(img);
  }

}
