package model;

import java.awt.image.BufferedImage;

/**
 * Model Generator interface, helps to create IMEImage objects.
 */
public interface IMGModel {
  /**
   * Create the model instance.
   *
   * @param r red image channel
   * @param g green image channel
   * @param b blue image channel
   * @return image Model
   */
  IMEImage create(int[][] r, int[][] g, int[][] b);

  /**
   * Create the model instance.
   *
   * @param path path to the image
   * @return image Model
   */
  IMEImage create(String path);

  /**
   * Create a model instance.
   *
   * @param img BufferedImage instance
   * @return return model instance
   */
  IMEImage create(BufferedImage img);
}
