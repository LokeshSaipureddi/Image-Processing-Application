package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to execute Split View operation on an Image.
 */
public class SplitView implements IMECommand {
  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public SplitView(String[] key) {
    if (key.length < 5) {
      throw new IllegalArgumentException("Split View takes 5 parameters");
    }
    this.key = key;
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    IMEImage left = map.get(key[2]);
    if (left == null) {
      throw new IllegalStateException("Image name not found");
    }

    IMEImage right = map.get(key[3]);
    if (right == null) {
      throw new IllegalStateException("Image name not found");
    }
    map.put(key[4], left.previewVerticalSplit(right, Integer.parseInt(key[1])));
  }
}