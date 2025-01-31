package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to execute Color histogram operation on an Image.
 */
public class ColorHist implements IMECommand {
  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public ColorHist(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("histogram takes 3 parameters");
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
    map.put(key[2], im.getColorHistogram());
  }
}
