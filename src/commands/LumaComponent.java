package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Get the Luma grayscale image.
 */
public class LumaComponent implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public LumaComponent(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Luma takes 3 parameters");
    }
    this.key = key;
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    IMEImage im = map.get(key[1]);
    if (im == null) {
      System.out.println("Image name not found");
      return;
    }
    float[][] kernel = new float[][]{
        {0.2126f, 0.7152f, 0.0722f},
        {0.2126f, 0.7152f, 0.0722f},
        {0.2126f, 0.7152f, 0.0722f}
    };

    if (key.length == 3) {
      map.put(key[2], im.linearTransformation(kernel));
    } else {
      map.put(key[2], im.linearTransformation(kernel).previewVerticalSplit(im,
          Integer.parseInt(key[3])));
    }
  }
}
