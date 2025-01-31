package model;

/**
 * Class to draw on a canvas and render as a model instance.
 */
public interface DrawImage {

  /**
   * Draw a line graph on the canvas.
   *
   * @param dist  distribution corresponding to integral x axis
   * @param color color, 'r' for red 'g' for green 'b' for blue
   */
  void drawLineGraph(int[] dist, char color);

  /**
   * Render the image to the given model generator.
   *
   * @param m model generator class
   * @return model instance
   */
  IMEImage render(IMGModel m);

  /**
   * Render the image to the default model generator.
   *
   * @return model instance
   */
  IMEImage render();
}
