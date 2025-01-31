package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Execute sharpen on the given image.
 */
public class Sharpen implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public Sharpen(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Sharpen takes 3 parameters");
    }
    this.key = key;
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    IMEImage im = map.get(key[1]);
    if (im == null) {
      throw new IllegalStateException("Image name not found");
    }

    float[][] kernel = new float[][]{
        {-1.f / 8, -1.f / 8, -1.f / 8, -1.f / 8, -1.f / 8},
        {-1.f / 8, 1.f / 4, 1.f / 4, 1.f / 4, -1.f / 8},
        {-1.f / 8, 1.f / 4, 1.f, 1.f / 4, -1.f / 8},
        {-1.f / 8, 1.f / 4, 1.f / 4, 1.f / 4, -1.f / 8},
        {-1.f / 8, -1.f / 8, -1.f / 8, -1.f / 8, -1.f / 8}
    };

    if (key.length == 3) {
      map.put(key[2], im.convolution(kernel, kernel, kernel));
    } else {
      map.put(key[2], im.convolution(kernel, kernel, kernel).previewVerticalSplit(im,
          Integer.parseInt(key[3])));
    }
  }
}
