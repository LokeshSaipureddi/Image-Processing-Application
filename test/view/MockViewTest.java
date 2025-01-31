package view;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import controller.FeatureImpl;
import controller.Features;
import model.IMEImage;
import model.IMEImageImpl;
import model.MockGen;

import static org.junit.Assert.assertEquals;

/**
 * Class to test GUI based controller and view.
 */
public class MockViewTest {

  @Test
  public void mockViewOne() throws IOException {
    StringBuilder log = new StringBuilder();
    HashMap<String, IMEImage> map = new HashMap<>();
    map.put("dest", new IMEImageImpl(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    Features feat = new FeatureImpl(new MockGen(log), map);
    View view = new MockView(log, 1);
    view.addFeatures(feat);
    feat.addView(view);
    feat.blur();
    feat.colorAdjust();
    feat.getBChannel();
    feat.getRChannel();
    feat.getGChannel();
    File file1 = new File("log.txt");
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(file1));
      writer.append(log);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
    assertEquals(log.toString(), "MockViewCreated 1\n" +
        "Features Added\n" +
        "Image set1822383117\n" +
        "Image set592959754\n" +
        "Called getBlack\n" +
        "Called getMid\n" +
        "Called getWhite\n" +
        "Image set48914743\n" +
        "Image set510109769\n" +
        "Called setBlack\n" +
        "11\n" +
        "Called setMid\n" +
        "12\n" +
        "Called setWhite\n" +
        "13\n" +
        "Image set107456312\n" +
        "Image set360067785\n" +
        "Image set1860250540\n" +
        "Image set1690859824\n" +
        "Image set1074593562\n" +
        "Image set1381965390\n");
  }

  @Test
  public void mockViewTwo() throws IOException {
    StringBuilder log = new StringBuilder();
    HashMap<String, IMEImage> map = new HashMap<>();
    map.put("dest", new IMEImageImpl(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    map.put("hst", new IMEImageImpl(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}},
        new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
    Features feat = new FeatureImpl(new MockGen(log), map);
    View view = new MockView(log, 1);
    view.addFeatures(feat);
    feat.addView(view);
    feat.getPreviewImage();
    feat.togglePreviewModeOff();
    feat.colorCorrect();
    feat.compress();
    File file1 = new File("log.txt");
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(file1));
      writer.append(log);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
    assertEquals(log.toString(), "MockViewCreated 1\n" +
        "Features Added\n" +
        "Image set1909546776\n" +
        "Image set2141179775\n" +
        "Called setPreviewModeLabel\n" +
        "Preview Mode Off\n" +
        "Image set758013696\n" +
        "Image set48914743\n" +
        "Called getCompressPerc\n" +
        "Image set1473611564\n" +
        "Image set921760190\n" +
        "Called setCompressPerc\n" +
        "10\n");
  }

  @Test
  public void mockViewThree() throws IOException {
    StringBuilder log = new StringBuilder();
    HashMap<String, IMEImage> map = new HashMap<>();
    map.put("dest",
        new IMEImageImpl(new int[][]{{0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}},
            new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}},
            new int[][]{{0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}}));
    Features feat = new FeatureImpl(new MockGen(log), map);
    View view = new MockView(log, 1);
    view.addFeatures(feat);
    feat.addView(view);
    feat.togglePreviewModeOn();
    feat.horizontalFlip();
    feat.luma();
    feat.sepia();
    feat.sharpen();
    File file1 = new File("log.txt");
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(file1));
      writer.append(log);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
    assertEquals(log.toString(), "MockViewCreated 1\n" +
        "Features Added\n" +
        "Called setPreviewModeLabel\n" +
        "Preview Mode On\n" +
        "Called getPreviewPercentage\n" +
        "Image set2138564891\n" +
        "Image set1151755506\n" +
        "Called setPreviewPercentage\n" +
        "14\n" +
        "Called getPreviewPercentage\n" +
        "Image set1663619914\n" +
        "Image set341748265\n" +
        "Called setPreviewPercentage\n" +
        "14\n" +
        "Called getPreviewPercentage\n" +
        "Image set758013696\n" +
        "Image set1279309678\n" +
        "Called setPreviewPercentage\n" +
        "14\n" +
        "Called getPreviewPercentage\n" +
        "Image set510109769\n" +
        "Image set1473611564\n" +
        "Called setPreviewPercentage\n" +
        "14\n");
  }

  @Test
  public void mockViewFour() throws IOException {
    StringBuilder log = new StringBuilder();
    HashMap<String, IMEImage> map = new HashMap<>();
    map.put("dest", new IMEImageImpl(new int[][]{{0, 0}, {0, 0}}, new int[][]{{0, 0}, {0, 0}},
        new int[][]{{0, 0}, {0, 0}}));
    Features feat = new FeatureImpl(new MockGen(log), map);
    View view = new MockView(log, 1);
    view.addFeatures(feat);
    feat.addView(view);
    feat.verticalFlip();
    feat.colorHist();
    feat.colorAdjust();
    File file1 = new File("log.txt");
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(file1));
      writer.append(log);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
    assertEquals(log.toString(), "MockViewCreated 1\n" +
        "Features Added\n" +
        "Image set1822383117\n" +
        "Image set592959754\n" +
        "Image set341748265\n" +
        "Called getBlack\n" +
        "Called getMid\n" +
        "Called getWhite\n" +
        "Image set510109769\n" +
        "Image set107456312\n" +
        "Called setBlack\n" +
        "11\n" +
        "Called setMid\n" +
        "12\n" +
        "Called setWhite\n" +
        "13\n");
  }


  @Test
  public void mockViewFive() throws IOException {
    StringBuilder log = new StringBuilder();
    HashMap<String, IMEImage> map = new HashMap<>();
    map.put("dest", new IMEImageImpl(new int[][]{{0, 0}, {0, 0}}, new int[][]{{0, 0}, {0, 0}},
        new int[][]{{0, 0}, {0, 0}}));
    Features feat = new FeatureImpl(new MockGen(log), map);
    View view = new MockView(log, 1);
    view.addFeatures(feat);
    feat.addView(view);
    feat.load();
    feat.verticalFlip();
    feat.colorHist();
    feat.colorAdjust();
    feat.save();
    File file1 = new File("log.txt");
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(file1));
      writer.append(log);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
    assertEquals(log.toString(), "MockViewCreated 1\n" +
        "Features Added\n" +
        "MockCreated45284689\n" +
        "Image set1822383117\n" +
        "Image set592959754\n" +
        "Image set341748265\n" +
        "Called getBlack\n" +
        "Called getMid\n" +
        "Called getWhite\n" +
        "Image set510109769\n" +
        "Image set107456312\n" +
        "Called setBlack\n" +
        "11\n" +
        "Called setMid\n" +
        "12\n" +
        "Called setWhite\n" +
        "13\n" +
        "Called getSavePath\n");
  }
}