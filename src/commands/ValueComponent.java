package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Get the intensity grayscale image. I.e. every pixel is max of it's r, g, b.
 */
public class ValueComponent implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public ValueComponent(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Value takes 3 parameters");
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
    if (key.length == 3) {
      map.put(key[2], im.maxIMG());
    } else {
      map.put(key[2], im.maxIMG().previewVerticalSplit(im, Integer.parseInt(key[3])));
    }
  }
}
