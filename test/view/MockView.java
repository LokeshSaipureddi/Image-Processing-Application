package view;

import java.awt.Image;

import controller.Features;

/**
 * Mock view to assist in testing.
 */
public class MockView implements View {

  private StringBuilder log;

  public MockView(StringBuilder log, int hash) {
    this.log = log;
    log.append("MockViewCreated ").append(hash).append("\n");
  }

  /**
   * Link features with the appropriate listeners of inputs.
   *
   * @param features features
   */
  @Override
  public void addFeatures(Features features) {
    log.append("Features Added\n");
  }

  /**
   * Set an image to display.
   *
   * @param img image instance
   */
  @Override
  public void setImage(Image img) {
    log.append("Image set" + img.hashCode()).append("\n");
  }

  /**
   * Set the color histogram to display.
   *
   * @param img image instance
   */
  @Override
  public void setHist(Image img) {
    log.append("Image set" + img.hashCode()).append("\n");
  }

  /**
   * Get the path to load an image.
   *
   * @return path to image in disk
   */
  @Override
  public String getLoadPath() {
    log.append("Called getLoadPath\n");
    return "res/Example.png";
  }

  /**
   * Get the path to save an image.
   *
   * @return path to image in disk
   */
  @Override
  public String getSavePath() {
    log.append("Called getSavePath\n");
    return "res/mock.png";
  }

  /**
   * Get the percentage of compression.
   *
   * @return percentage
   */
  @Override
  public String getCompressPerc() {
    log.append("Called getCompressPerc\n");
    return "10";
  }

  /**
   * Set the percentage of compression.
   *
   * @param p percentage
   */
  @Override
  public void setCompressPerc(String p) {
    log.append("Called setCompressPerc\n").append(p).append("\n");
  }

  /**
   * Get the Black value for Level Adjust.
   *
   * @return value
   */
  @Override
  public String getBlack() {
    log.append("Called getBlack\n");
    return "11";
  }

  /**
   * Set the Black value for Level Adjust.
   *
   * @param b value
   */
  @Override
  public void setBlack(String b) {
    log.append("Called setBlack\n").append(b).append("\n");
  }

  /**
   * Get the Mid value for Level Adjust.
   *
   * @return value
   */
  @Override
  public String getMid() {
    log.append("Called getMid\n");
    return "12";
  }

  /**
   * Set the Mid value for Level Adjust.
   *
   * @param m value
   */
  @Override
  public void setMid(String m) {
    log.append("Called setMid\n").append(m).append("\n");
  }

  /**
   * Get the White value for Level Adjust.
   *
   * @return value
   */
  @Override
  public String getWhite() {
    log.append("Called getWhite\n");
    return "13";
  }

  /**
   * Set the White value for Level Adjust.
   *
   * @param m value
   */
  @Override
  public void setWhite(String m) {
    log.append("Called setWhite\n").append(m).append("\n");
  }

  /**
   * Get the percentage of display to split for preview.
   *
   * @return percentage
   */
  @Override
  public String getPreviewPercentage() {
    log.append("Called getPreviewPercentage\n");
    return "14";
  }

  /**
   * Set the percentage of display to split for preview.
   *
   * @param p percentage
   */
  @Override
  public void setPreviewPercentage(String p) {
    log.append("Called setPreviewPercentage\n").append(p).append("\n");
  }

  /**
   * Set the Label for preview mode's status.
   *
   * @param m message to show
   */
  @Override
  public void setPreviewModeLabel(String m) {
    log.append("Called setPreviewModeLabel\n").append(m).append("\n");
  }

  /**
   * Show an error message.
   *
   * @param m error message
   */
  @Override
  public void setErrorMessage(String m) {
    log.append("Called setErrorMessage\n").append(m).append("\n");
  }

  /**
   * Ask to save an image e.g. at the time of closing.
   */
  @Override
  public void askSave() {
    log.append("Called askSave\n");
  }
}
