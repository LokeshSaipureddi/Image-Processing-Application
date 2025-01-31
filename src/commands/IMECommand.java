package commands;

import java.util.HashMap;

import model.IMEImage;

/**
 * Class to implement a command registered in controller.IMEController.
 */
public interface IMECommand {

  /**
   * Execute the command.
   * @param map registry of Models
   */
  void exec(HashMap<String, IMEImage> map);
}
