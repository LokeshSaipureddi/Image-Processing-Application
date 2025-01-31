package controller;

import view.View;

/**
 * Features provided to enable a View on a Single image editing application.
 */
public interface Features {

  /**
   * Link the View with the features to appropriately take inputs and alter view.
   * @param view  view instance
   */
  void addView(View view);

  /**
   * Load an image.
   */
  void load();

  /**
   * Save an image.
   */
  void save();

  /**
   * Get R channel of the image.
   */
  void getRChannel();

  /**
   * Get G channel of the image.
   */
  void getGChannel();

  /**
   * Get B channel of the image.
   */
  void getBChannel();

  /**
   * Get R channel of the image.
   */
  void horizontalFlip();

  /**
   * Vertically flip the image.
   */
  void verticalFlip();

  /**
   * Blur the image.
   */
  void blur();

  /**
   * Sharpen the image.
   */
  void sharpen();

  /**
   * Convert the image to grayscale using Luma.
   */
  void luma();

  /**
   * Apply Sepia filter on the image.
   */
  void sepia();

  /**
   * Compress the image.
   */
  void compress();

  /**
   * Level Adjust the image.
   */
  void colorAdjust();

  /**
   * Color correct the image.
   */
  void colorCorrect();

  /**
   * Get color histogram of the image.
   */
  void colorHist();

  /**
   * Deactivate preview mode.
   */
  void togglePreviewModeOff();

  /**
   * Activate preview mode.
   */
  void togglePreviewModeOn();

  /**
   * Get preview image.
   */
  void getPreviewImage();

  /**
   * Save the image on the application close, if not yet saved.
   */
  void saveOnClose();
}
