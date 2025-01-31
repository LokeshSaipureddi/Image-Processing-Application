package controller;

import model.GenModel;
import view.SwingUIFrame;
import view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Main method execution class.
 */
public class IMEController {

  /**
   * Main method to execute IME application.
   *
   * @param args commandline arguments
   * @throws FileNotFoundException if the passed file in commandline does not exist
   */
  public static void main(String[] args) throws FileNotFoundException {
    if (args.length == 0) {
      View view = new SwingUIFrame();
      TstController controller = new TstController(view);
    } else if (args[0].equals("-file")) {
      File file = new File(args[1]);
      InputStream targetStream = new FileInputStream(file);
      new TstController(new InputStreamReader(targetStream), System.out).start(new GenModel());
    } else if (args[0].equals("-text")) {
      new TstController(new InputStreamReader(System.in), System.out).start(new GenModel());
    }
  }
}

