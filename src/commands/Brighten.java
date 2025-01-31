package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to execute brighten operation on an Image.
 */
public class Brighten implements IMECommand {
  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public Brighten(String[] key) {
    if (key.length < 4) {
      throw new IllegalArgumentException("Brighten takes 4 parameters");
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
    map.put(key[3], im.brighten(Integer.parseInt(key[1])));
  }
}
