package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to execute Compress operation on an Image.
 */
public class Compress implements IMECommand {
  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public Compress(String[] key) {
    if (key.length < 4) {
      throw new IllegalArgumentException("Compress takes 4 parameters");
    }
    this.key = key;
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    IMEImage im = map.get(key[2]);
    if (im == null) {
      System.out.println("Image name not found");
      return;
    }
    int percent = Integer.parseInt(key[1]);
    IMEImage comp = im.compress(percent);
    map.put(key[3], comp);
  }
}
