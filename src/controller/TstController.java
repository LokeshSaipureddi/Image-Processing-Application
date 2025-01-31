package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import commands.IMECommand;
import model.IMEImage;
import model.IMGModel;
import view.View;

/**
 * Controller class to control the flow of execution.
 */
public class TstController implements IMEControllerInterface {
  final Readable in;
  final Appendable out;

  /**
   * Controller handling IO streams.
   *
   * @param in  input stream
   * @param out output stream
   */
  public TstController(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  /**
   * Controller handling application using features with default model.
   *
   * @param view view instance
   */
  public TstController(View view) {
    Features features = new FeatureImpl();
    features.addView(view);
    view.addFeatures(features);
    in = null;
    out = null;
  }

  /**
   * Controller handling application using features.
   *
   * @param view  view instance
   * @param model model instance
   */
  public TstController(View view, IMGModel model) {
    Features features = new FeatureImpl(model, new HashMap<>());
    features.addView(view);
    view.addFeatures(features);
    in = null;
    out = null;
  }

  /**
   * Start the execution of IME program.
   */
  @Override
  public void start(IMGModel model) {

    Scanner scan = new Scanner(this.in);
    HashMap<String, IMEImage> map = new HashMap<>();
    CommandRegistry commands = new CommandRegistry(model);
    Map<String, Function<String[], IMECommand>> knownCommands = commands.getCommands();

    while (scan.hasNext()) {
      IMECommand c;
      String[] in = scan.nextLine().split(" ");
      if (in[0].equalsIgnoreCase("q") || in[0].equalsIgnoreCase("quit")) {
        return;
      }

      if ((in[0].isEmpty() || in[0].startsWith("#"))) {
        continue;
      }

      Function<String[], IMECommand> cmd = knownCommands.getOrDefault(in[0], null);
      if (cmd == null) {
        throw new IllegalArgumentException();
      } else {
        try {
          c = cmd.apply(in);
          c.exec(map);
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }

      }
    }
  }
}