package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import commands.Blur;
import commands.Brighten;
import commands.ColorAdjust;
import commands.ColorCorrect;
import commands.ColorHist;
import commands.Compress;
import commands.HorizonFlip;
import commands.IMECommand;
import commands.IntensityComponent;
import commands.LoadImage;
import commands.LumaComponent;
import commands.RGBCombine;
import commands.RGBSplit;
import commands.Run;
import commands.SaveImage;
import commands.Sepia;
import commands.Sharpen;
import commands.ValueComponent;
import commands.VerticalFlip;
import commands.XComponent;
import model.IMGModel;

/**
 * Class containing all the commands.
 */
public class CommandRegistry {
  private Map<String, Function<String[], IMECommand>> knownCommands;

  /**
   * Initialize commands with the given model generator.
   *
   * @param model model generator
   */
  public CommandRegistry(IMGModel model) {
    knownCommands = new HashMap<>();
    knownCommands.put("load", s -> new LoadImage(s, model));
    knownCommands.put("save", SaveImage::new);
    knownCommands.put("red-component", s -> new XComponent(s, 0));
    knownCommands.put("green-component", s -> new XComponent(s, 1));
    knownCommands.put("blue-component", s -> new XComponent(s, 2));
    knownCommands.put("value-component", ValueComponent::new);
    knownCommands.put("luma-component", LumaComponent::new);
    knownCommands.put("intensity-component", IntensityComponent::new);
    knownCommands.put("horizontal-flip", HorizonFlip::new);
    knownCommands.put("vertical-flip", VerticalFlip::new);
    knownCommands.put("brighten", Brighten::new);
    knownCommands.put("rgb-split", RGBSplit::new);
    knownCommands.put("rgb-combine", RGBCombine::new);
    knownCommands.put("blur", Blur::new);
    knownCommands.put("sharpen", Sharpen::new);
    knownCommands.put("sepia", Sepia::new);
    knownCommands.put("run", s -> new Run(s, knownCommands));
    knownCommands.put("histogram", ColorHist::new);
    knownCommands.put("color-correct", ColorCorrect::new);
    knownCommands.put("levels-adjust", ColorAdjust::new);
    knownCommands.put("compress", Compress::new);
  }

  /**
   * Get commands.
   *
   * @return command map
   */
  public Map<String, Function<String[], IMECommand>> getCommands() {
    return knownCommands;
  }
}
