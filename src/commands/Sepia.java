package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Sepia filter the given image.
 */
public class Sepia implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public Sepia(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Sepia takes 3 parameters");
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
        {0.393f, 0.769f, 0.189f},
        {0.349f, 0.686f, 0.168f},
        {0.272f, 0.534f, 0.131f}
    };

    if (key.length == 3) {
      map.put(key[2], im.linearTransformation(kernel));
    } else {
      map.put(key[2], im.linearTransformation(kernel).previewVerticalSplit(im,
          Integer.parseInt(key[3])));
    }
  }
}
