package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Get the channel of an image corresponding to the given axis channel.
 */
public class XComponent implements IMECommand {

  private String[] key;
  private int axis;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public XComponent(String[] key, int axis) {
    if (key.length < 3) {
      throw new IllegalArgumentException("load takes 3 parameters");
    }
    this.key = key;
    this.axis = axis;
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    IMEImage im = map.get(key[1]);
    if (im == null) {
      System.out.println("Image name not found");
      return;
    }
    map.put(key[2], im.splitImage(axis));
  }
}
