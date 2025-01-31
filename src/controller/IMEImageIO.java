package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;

import javax.imageio.ImageIO;

import model.GenModel;
import model.IMEImage;
import model.IMGModel;

/**
 * A class with static methods to perform IO operations for an Image.
 */
public class IMEImageIO {

  /**
   * Read an image from the path.
   *
   * @param imgPath path to image
   * @return image object
   */
  public static IMEImage read(String imgPath) {
    String[] path = imgPath.split("[.]");
    String ext = path[path.length - 1];
    if (ext.equals("ppm")) {
      return readPPM(imgPath);
    } else {
      return readOthers(imgPath);
    }
  }

  private static IMEImage readPPM(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);
    int[][] r = new int[height][width];
    int[][] g = new int[height][width];
    int[][] b = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        r[i][j] = sc.nextInt();
        g[i][j] = sc.nextInt();
        b[i][j] = sc.nextInt();
      }
    }
    IMGModel m = new GenModel();
    return m.create(r, g, b);
  }

  private static IMEImage readOthers(String filename) {
    try {
      File imageFile = new File(filename);
      BufferedImage img = ImageIO.read(imageFile);

      return readBufferedImage(img);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Create a model object from the given BufferedImage.
   *
   * @param img BufferedImage
   * @return model image
   */
  public static IMEImage readBufferedImage(BufferedImage img) {
    if (img != null) {
      int height = img.getHeight();
      int width = img.getWidth();
      int[][] r = new int[height][width];
      int[][] g = new int[height][width];
      int[][] b = new int[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int rgb = img.getRGB(j, i);
          r[i][j] = (rgb >> 16) & 0xFF;
          g[i][j] = (rgb >> 8) & 0xFF;
          b[i][j] = rgb & 0xFF;
        }
      }
      IMGModel m = new GenModel();
      return m.create(r, g, b);
    }

    return null;
  }


  private static void savePPM(String filename, IMEImage file) {
    File ppmFile = new File(filename);
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(ppmFile);
    } catch (FileNotFoundException e) {
      System.out.println("Destination not found");
      return;
    }
    int[][] r = file.getR();
    int[][] g = file.getG();
    int[][] b = file.getB();
    int height = file.getHeight();
    int width = file.getWidth();

    writer.println("P3");
    writer.println(width + " " + height);
    writer.println("255");

    // Write pixel data
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {

        int red = r[y][x];
        int green = g[y][x];
        int blue = b[y][x];

        writer.print(red + " ");
        writer.print(green + " ");
        writer.print(blue + " ");
      }
      writer.println();
    }

    // Close file
    writer.close();

    System.out.println("Image saved as PPM file: " + ppmFile.getName());

  }

  private static void saveOthers(String filename, String ext, IMEImage file) {

    BufferedImage image = mtoBuffered(file);

    File outputfile = new File(filename);
    try {
      ImageIO.write(image, ext, outputfile);
    } catch (IOException e) {
      throw new IllegalArgumentException("Path not found or Format not supported");
    }
  }

  /**
   * Convert model instance to BufferedImage instane.
   *
   * @param img model instance
   * @return BufferedImage instance
   */
  public static BufferedImage mtoBuffered(IMEImage img) {
    int[][] r = img.getR();
    int[][] g = img.getG();
    int[][] b = img.getB();
    int height = img.getHeight();
    int width = img.getWidth();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Set image pixels from 3D array
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int rgb = r[y][x]; // red
        rgb = (rgb << 8) + g[y][x]; // add green
        rgb = (rgb << 8) + b[y][x]; // add blue
        image.setRGB(x, y, rgb);
      }
    }

    return image;
  }

  /**
   * Save an image to the path.
   *
   * @param imgPath path to destination image
   * @param img     image file to save
   */
  public static void save(String imgPath, IMEImage img) {
    String[] path = imgPath.split("[.]");
    String ext = path[path.length - 1];
    if (ext.equals("ppm")) {
      savePPM(imgPath, img);
    } else {
      saveOthers(imgPath, ext, img);
    }
  }

}
