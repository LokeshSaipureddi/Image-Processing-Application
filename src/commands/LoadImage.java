package commands;

import java.util.HashMap;

import model.GenModel;
import model.IMEImage;
import model.IMGModel;

/**
 * Load an Image.
 */
public class LoadImage implements IMECommand {

  private String[] key;
  private IMGModel model;


  /**
   * Constructor checking command and initializing with given model.
   *
   * @param key   keys
   * @param model model
   */
  public LoadImage(String[] key, IMGModel model) {
    if (key.length < 3) {
      throw new IllegalArgumentException("load takes 3 parameters");
    }
    this.key = key;
    this.model = model;
  }


  /**
   * Constructor checking command and initializing with default model.
   *
   * @param key command keys
   */
  public LoadImage(String[] key) {
    if (key.length < 3) {
      throw new IllegalArgumentException("load takes 3 parameters");
    }
    this.key = key;
    this.model = new GenModel();
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    IMEImage im = model.create(key[1]);
    map.put(key[2], im);
  }

}
