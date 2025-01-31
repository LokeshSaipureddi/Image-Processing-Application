package controller;

import java.util.HashMap;

import commands.Blur;
import commands.ColorAdjust;
import commands.ColorCorrect;
import commands.ColorHist;
import commands.Compress;
import commands.HorizonFlip;
import commands.IMECommand;
import commands.LoadImage;
import commands.LumaComponent;
import commands.SaveImage;
import commands.Sepia;
import commands.Sharpen;
import commands.SplitView;
import commands.VerticalFlip;
import commands.XComponent;
import model.GenModel;
import model.IMEImage;
import model.IMGModel;
import view.View;

import static java.lang.Math.max;

/**
 * Implementation of features for the Java Swing GUI application.
 */
public class FeatureImpl implements Features {
  HashMap<String, IMEImage> map;
  String init;
  String[] dest;
  String[] hs;
  int count;
  View view;
  IMGModel model;
  boolean saved;

  /**
   * Initialize the internal variables with default model.
   */
  public FeatureImpl() {
    map = new HashMap<>();
    init = "GUI";
    dest = new String[]{"dest", "dest1", "prev"};
    hs = new String[]{"hs", "hs1", "prevhs"};
    count = 0;
    view = null;
    saved = true;
    model = new GenModel();
  }

  /**
   * Initialize the internal variables with given model.
   *
   * @param model model generator
   * @param map   Map to store images
   */
  public FeatureImpl(IMGModel model, HashMap<String, IMEImage> map) {
    this.map = map;
    init = "GUI";
    dest = new String[]{"dest", "dest1", "prev"};
    hs = new String[]{"hs", "hs1", "prevhs"};
    count = 0;
    view = null;
    saved = true;
    this.model = model;
  }

  @Override
  public void addView(View view) {
    this.view = view;
  }

  @Override
  public void load() {
    String path = view.getLoadPath();
    IMECommand cmd = new LoadImage(new String[]{init, path, dest[0]}, model);
    getImage(cmd);
    saved = true;
  }

  @Override
  public void save() {
    String path = view.getSavePath();
    IMECommand cmd = new SaveImage(new String[]{init, path, dest[0]});
    cmd.exec(map);
    saved = true;
  }

  @Override
  public void getRChannel() {
    IMECommand cmd = new XComponent(new String[]{init, dest[0], dest[count]}, 0);
    getImage(cmd);
  }

  @Override
  public void getGChannel() {
    IMECommand cmd = new XComponent(new String[]{init, dest[0], dest[count]}, 1);
    getImage(cmd);
  }

  @Override
  public void getBChannel() {
    IMECommand cmd = new XComponent(new String[]{init, dest[0], dest[count]}, 2);
    getImage(cmd);
  }

  @Override
  public void horizontalFlip() {
    IMECommand cmd = new HorizonFlip(new String[]{init, dest[0], dest[count]});
    getImage(cmd);
  }

  @Override
  public void verticalFlip() {
    IMECommand cmd = new VerticalFlip(new String[]{init, dest[0], dest[count]});
    getImage(cmd);
  }

  @Override
  public void blur() {
    IMECommand cmd = new Blur(new String[]{init, dest[0], dest[count]});
    getImage(cmd);
  }

  @Override
  public void sharpen() {
    IMECommand cmd = new Sharpen(new String[]{init, dest[0], dest[count]});
    getImage(cmd);
  }

  @Override
  public void luma() {
    IMECommand cmd = new LumaComponent(new String[]{init, dest[0], dest[count]});
    getImage(cmd);
  }

  @Override
  public void sepia() {
    IMECommand cmd = new Sepia(new String[]{init, dest[0], dest[count]});
    getImage(cmd);
  }

  @Override
  public void compress() {
    String percent = view.getCompressPerc();
    IMECommand cmd = new Compress(new String[]{init, percent, dest[0], dest[count]});
    getImage(cmd);
    int p = Integer.parseInt(percent);
    p = max(0, Math.min(100, p));
    view.setCompressPerc(String.valueOf(p));
  }

  @Override
  public void colorAdjust() {
    String black = view.getBlack();
    String mid = view.getMid();
    String white = view.getWhite();
    IMECommand cmd = new ColorAdjust(new String[]{init, black, mid, white, dest[0],
        dest[count]});
    getImage(cmd);
    int b = Integer.parseInt(black);
    int m = Integer.parseInt(mid);
    int w = Integer.parseInt(white);

    view.setBlack(String.valueOf(max(0, Math.min(255, b))));
    view.setMid(String.valueOf(max(b, Math.min(255, m))));
    view.setWhite(String.valueOf(max(m, Math.min(255, w))));
  }

  @Override
  public void colorCorrect() {
    IMECommand cmd = new ColorCorrect(new String[]{init, dest[0], dest[count]});
    getImage(cmd);
  }

  @Override
  public void colorHist() {
    IMECommand cmd = new ColorHist(new String[]{init, dest[count], hs[0]});
    cmd.exec(map);
    view.setHist(IMEImageIO.mtoBuffered(map.get(hs[0])));
  }

  private void colorHistInc(int ind) {
    IMECommand cmd = new ColorHist(new String[]{init, dest[ind], hs[ind]});
    cmd.exec(map);
    view.setHist(IMEImageIO.mtoBuffered(map.get(hs[ind])));
  }

  private void getImage(IMECommand cmd) {
    try {
      cmd.exec(map);
      getPreviewImage();
    } catch (Exception e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  @Override
  public void getPreviewImage() {
    if (count == 1) {
      String perc = view.getPreviewPercentage();
      IMECommand vCmd = new SplitView(new String[]{init, perc, dest[1], dest[0], dest[2]});
      vCmd.exec(map);
      colorHistInc(2);
      view.setImage(IMEImageIO.mtoBuffered(map.get(dest[2])));
      int p = Integer.parseInt(perc);
      p = max(0, Math.min(100, p));
      view.setPreviewPercentage(String.valueOf(p));
    }
    if (count == 0) {
      view.setImage(IMEImageIO.mtoBuffered(map.get(dest[0])));
      colorHistInc(0);
      saved = false;
    }
  }

  @Override
  public void saveOnClose() {
    if (!saved) {
      view.askSave();
    }
  }

  @Override
  public void togglePreviewModeOn() {
    count = 1;
    view.setPreviewModeLabel("Preview Mode On");
  }

  @Override
  public void togglePreviewModeOff() {
    count = 0;
    view.setPreviewModeLabel("Preview Mode Off");
  }
}
