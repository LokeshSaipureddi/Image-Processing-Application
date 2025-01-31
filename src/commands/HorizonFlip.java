package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Horizontally flip an Image.
 */
public class HorizonFlip implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public HorizonFlip(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Horizontal Flip takes 3 parameters");
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
    map.put(key[2], im.horizontalFlip());
  }
}
