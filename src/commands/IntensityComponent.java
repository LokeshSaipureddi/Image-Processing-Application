package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Get the intensity grayscale image. I.e. every pixel is average of it's r, g, b.
 */
public class IntensityComponent implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public IntensityComponent(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Intensity takes 3 parameters");
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
      map.put(key[2], im.avgIMG());
    } else {
      map.put(key[2], im.avgIMG().previewVerticalSplit(im, Integer.parseInt(key[3])));
    }
  }
}
