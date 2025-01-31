package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Execute RGB combine, i.e. use r, g, b channels from the given images respectively.
 */
public class RGBCombine implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public RGBCombine(String[] key) {
    if (key.length < 5) {
      throw new IllegalArgumentException("Combine takes 3 parameters");
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
    IMEImage im1 = map.get(key[3]);
    if (im1 == null) {
      System.out.println("Image name not found");
      return;
    }
    IMEImage im2 = map.get(key[4]);
    if (im2 == null) {
      System.out.println("Image name not found");
      return;
    }
    map.put(key[1], im.combine(im1.getG(), im2.getB()));
  }
}