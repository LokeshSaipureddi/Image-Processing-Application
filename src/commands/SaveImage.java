package commands;

import java.util.HashMap;

import controller.IMEImageIO;
import model.IMEImage;

/**
 * Save an Image.
 */
public class SaveImage implements IMECommand {
  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public SaveImage(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("save takes 3 parameters");
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
    IMEImageIO.save(key[1], im);
  }
}
