package model;

import java.awt.image.BufferedImage;

/**
 * Create Mock Model object.
 */
public class MockGen implements IMGModel {

  private StringBuilder log;

  /**
   * Initialize the mock model with the log.
   *
   * @param log log
   */
  public MockGen(StringBuilder log) {
    this.log = log;
  }

  /**
   * Create the model instance.
   *
   * @param r red image channel
   * @param g green image channel
   * @param b blue image channel
   * @return image Model
   */
  @Override
  public IMEImage create(int[][] r, int[][] g, int[][] b) {
    return new ModelMock(log, 1);
  }

  /**
   * Create the model instance.
   *
   * @param path path to the image
   * @return image Model
   */
  @Override
  public IMEImage create(String path) {
    return new ModelMock(log, 2);
  }

  @Override
  public IMEImage create(BufferedImage img) {
    return new ModelMock(log, 3);
  }
}
