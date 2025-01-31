package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to execute blur operation on an Image.
 */
public class Blur implements IMECommand {

  private String[] key;

  /**
   * Constructor checking command and initializing.
   *
   * @param key command keys
   */
  public Blur(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("Blur takes 3 parameters");
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
    float[][] kernel = new float[][]{
        {1.f / 16, 1.f / 8, 1.f / 16},
        {1.f / 8, 1.f / 4, 1.f / 8},
        {1.f / 16, 1.f / 8, 1.f / 16}
    };

    if (key.length == 3) {
      map.put(key[2], im.convolution(kernel, kernel, kernel));
    } else {
      map.put(key[2], im.convolution(kernel,kernel,kernel).previewVerticalSplit(im,
          Integer.parseInt(key[3])));
    }
  }
}
