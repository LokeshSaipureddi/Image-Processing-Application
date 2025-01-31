package commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import model.IMEImage;

/**
 * Run a script file.
 */
public class Run implements IMECommand {

  private String[] key;
  private Map<String, Function<String[], IMECommand>> knownCommands;

  /**
   * Constructor checking command and initializing.
   *
   * @param key keys
   */
  public Run(String[] key, Map<String, Function<String[], IMECommand>> knownCommands) {
    if (key.length < 2) {
      throw new IllegalArgumentException("command takes 2 parameters");
    }
    this.key = key;
    this.knownCommands = knownCommands;
  }

  @Override
  public void exec(HashMap<String, IMEImage> map) {
    File script = new File(key[1]);
    Scanner scan;
    try {
      scan = new Scanner(script);
    } catch (FileNotFoundException e) {
      System.out.println("Script File does not exist");
      return;
    }

    while (scan.hasNext()) {
      IMECommand c;
      String[] in = scan.nextLine().split(" ");
      if (in[0].equalsIgnoreCase("q") || in[0].equalsIgnoreCase("quit")) {
        return;
      }
      Function<String[], IMECommand> cmd = knownCommands.getOrDefault(in[0], null);
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        c = cmd.apply(in);
        c.exec(map);
      }
    }
  }
}
