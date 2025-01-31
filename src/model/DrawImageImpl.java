package model;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Implementation class for DrawImage using BufferImage and Graphics2D java classes.
 */
public class DrawImageImpl implements DrawImage {
  private final BufferedImage img;
  private final Graphics2D g;
  private final int width;

  /**
   * Initialize the canvas with the given dimensions.
   *
   * @param width  width of the canvas
   * @param height height of the canvas
   */
  public DrawImageImpl(int width, int height) {
    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    this.width = width;
    g = img.createGraphics();
  }

  @Override
  public void drawLineGraph(int[] dist, char color) {
    if (dist.length > width) {
      throw new IllegalArgumentException("Distribution exceeds canvas width");
    }

    if (color == 'r') {
      g.setColor(Color.red);
    } else if (color == 'g') {
      g.setColor(Color.green);
    } else if (color == 'b') {
      g.setColor(Color.blue);
    } else {
      throw new IllegalArgumentException("Color not found");
    }

    for (int i = 1; i < dist.length; i++) {
      int y1 = dist[i - 1];
      int y2 = dist[i];
      g.drawLine(i - 1, 255 - y1, i, 255 - y2);
    }
  }

  @Override
  public IMEImage render(IMGModel m) {
    return m.create(img);
  }

  @Override
  public IMEImage render() {
    IMGModel m = new GenModel();
    return m.create(img);
  }
}
