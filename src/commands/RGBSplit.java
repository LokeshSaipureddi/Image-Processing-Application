package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Split an Image into R, G and B grayscale images.
 */
public class RGBSplit implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public RGBSplit(String[] key) {
    if (key.length < 5) {
      throw new IllegalArgumentException("RGBSplit takes 3 parameters");
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
    map.put(key[2], im.splitImage(0));
    map.put(key[3], im.splitImage(1));
    map.put(key[4], im.splitImage(2));
  }
}
