package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to execute Color Level Adjust operation on an Image.
 */
public class ColorAdjust implements IMECommand {
  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public ColorAdjust(String[] key) {
    if (key.length < 6) {
      throw new IllegalArgumentException("Color Adjust takes 6 parameters");
    }
    this.key = key;
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    IMEImage im = map.get(key[4]);
    if (im == null) {
      System.out.println("Image name not found");
      return;
    }

    if (key.length == 6) {
      map.put(key[5], im.colorAdjust(Integer.parseInt(key[1]), Integer.parseInt(key[2]),
          Integer.parseInt(key[3])));
    } else {
      map.put(key[5], im.colorAdjust(Integer.parseInt(key[1]), Integer.parseInt(key[2]),
          Integer.parseInt(key[3])).previewVerticalSplit(im, Integer.parseInt(key[6])));
    }
  }
}
