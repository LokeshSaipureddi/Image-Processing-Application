package view;

import java.awt.Image;

import controller.Features;

/**
 * View for an image editing application.
 */
public interface View {
  /**
   * Link features with the appropriate listeners of inputs.
   *
   * @param features features
   */
  void addFeatures(Features features);

  /**
   * Set an image to display.
   *
   * @param img image instance
   */
  void setImage(Image img);

  /**
   * Set the color histogram to display.
   *
   * @param img image instance
   */
  void setHist(Image img);

  /**
   * Get the path to load an image.
   *
   * @return path to image in disk
   */
  String getLoadPath();

  /**
   * Get the path to save an image.
   *
   * @return path to image in disk
   */
  String getSavePath();

  /**
   * Get the percentage of compression.
   *
   * @return percentage
   */
  String getCompressPerc();

  /**
   * Set the percentage of compression.
   *
   * @param p percentage
   */
  void setCompressPerc(String p);

  /**
   * Get the Black value for Level Adjust.
   *
   * @return value
   */
  String getBlack();

  /**
   * Set the Black value for Level Adjust.
   *
   * @param b value
   */
  void setBlack(String b);

  /**
   * Get the Mid value for Level Adjust.
   *
   * @return value
   */
  String getMid();

  /**
   * Set the Mid value for Level Adjust.
   *
   * @param m value
   */
  void setMid(String m);

  /**
   * Get the White value for Level Adjust.
   *
   * @return value
   */
  String getWhite();

  /**
   * Set the White value for Level Adjust.
   *
   * @param m value
   */
  void setWhite(String m);

  /**
   * Get the percentage of display to split for preview.
   *
   * @return percentage
   */
  String getPreviewPercentage();

  /**
   * Set the percentage of display to split for preview.
   *
   * @param p percentage
   */
  void setPreviewPercentage(String p);

  /**
   * Set the Label for preview mode's status.
   *
   * @param m message to show
   */
  void setPreviewModeLabel(String m);

  /**
   * Show an error message.
   *
   * @param m error message
   */
  void setErrorMessage(String m);

  /**
   * Ask to save an image e.g. at the time of closing.
   */
  void askSave();
}