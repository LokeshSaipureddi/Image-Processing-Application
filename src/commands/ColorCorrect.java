package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to execute ColorCorrect operation on an Image.
 */
public class ColorCorrect implements IMECommand {
  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public ColorCorrect(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Color Correction takes 3 parameters");
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
      map.put(key[2], im.colorCorrection());
    } else {
      map.put(key[2], im.colorCorrection().previewVerticalSplit(im, Integer.parseInt(key[3])));
    }
  }
}
