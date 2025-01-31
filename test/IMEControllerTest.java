import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import controller.TstController;
import model.MockGen;

import static org.junit.Assert.assertEquals;

/**
 * Test for the controller.
 */
public class IMEControllerTest {

  @Test
  public void MockTest() throws IOException {
    StringBuilder log = new StringBuilder();
    File file = new File("src/script.txt");
    InputStream targetStream = new FileInputStream(file);
    new TstController(new InputStreamReader(targetStream), System.out).start(new MockGen(log));
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
    assertEquals(log.toString(), "MockCreated 2\n" +
        "Called brighten\n" +
        "50\n" +
        "MockCreated 2\n" +
        "Called save 2\n" +
        "MockCreated 2\n" +
        "Called maxIMG\n" +
        "MockCreated 3\n" +
        "Called save 3\n");
  }

  @Test
  public void MockTestOne() throws IOException {
    StringBuilder log = new StringBuilder();
    File file = new File("src/Script1.txt");
    InputStream targetStream = new FileInputStream(file);
    new TstController(new InputStreamReader(targetStream), System.out).start(new MockGen(log));
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
    assertEquals(log.toString(), "MockCreated 2\n" +
        "Called brighten\n" +
        "10\n" +
        "MockCreated 2\n" +
        "Called verticalFlip\n" +
        "MockCreated 5\n" +
        "Called horizontalFlip\n" +
        "MockCreated 5\n" +
        "Called maxIMG\n" +
        "MockCreated 3\n" +
        "Called save 2\n" +
        "Called save 3\n" +
        "MockCreated 2\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called brighten\n" +
        "50\n" +
        "MockCreated 2\n" +
        "Called getG\n" +
        "Called getB\n" +
        "Called combine\n" +
        "MockCreated 7\n" +
        "Called save 7\n");
  }

  @Test
  public void MockTestTwo() throws IOException {
    StringBuilder log = new StringBuilder();
    File file = new File("src/script2.txt");
    InputStream targetStream = new FileInputStream(file);
    new TstController(new InputStreamReader(targetStream), System.out).start(new MockGen(log));
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
    assertEquals(log.toString(), "MockCreated 2\n" +
        "Called brighten\n" +
        "10\n" +
        "MockCreated 2\n" +
        "Called verticalFlip\n" +
        "MockCreated 5\n" +
        "Called horizontalFlip\n" +
        "MockCreated 5\n" +
        "Called maxIMG\n" +
        "MockCreated 3\n" +
        "Called save 2\n" +
        "Called save 3\n" +
        "MockCreated 2\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called brighten\n" +
        "50\n" +
        "MockCreated 2\n" +
        "Called convolution\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "MockCreated 1\n" +
        "Called convolution\n" +
        "-0.125-0.125-0.125-0.125-0.125-0.1250.250." +
        "250.25-0.125-0.1250.251.00.25-0.125-0.125" +
        "0.250.250.25-0.125-0.125-0.125-0.125-0.125-0.125\n" +
        "-0.125-0.125-0.125-0.125-0.125-0.1250.250.250." +
        "25-0.125-0.1250.251.00.25-0.125-0.125" +
        "0.250.250.25-0.125-0.125-0.125-0.125-0.125-0.125\n" +
        "-0.125-0.125-0.125-0.125-0.125-0.1250.250.250.25-0." +
        "125-0.1250.251.00.25-0.125-0.125" +
        "0.250.250.25-0.125-0.125-0.125-0.125-0.125-0.125\n" +
        "MockCreated 1\n" +
        "Called linearTransformation\n" +
        "0.21260.71520.07220.21260.71520.07220.21260.71520.0722\n" +
        "MockCreated 6\n" +
        "Called linearTransformation\n" +
        "0.3930.7690.1890.3490.6860.1680.2720.5340.131\n" +
        "MockCreated 6\n" +
        "Called avgIMG\n" +
        "MockCreated 4\n" +
        "Called getG\n" +
        "Called getB\n" +
        "Called combine\n" +
        "MockCreated 7\n" +
        "Called save 7\n");
  }

  @Test
  public void MockTestThree() throws IOException {
    StringBuilder log = new StringBuilder();
    File file = new File("src/script3.txt");
    InputStream targetStream = new FileInputStream(file);
    new TstController(new InputStreamReader(targetStream), System.out).start(new MockGen(log));
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
    assertEquals(log.toString(), "MockCreated 2\n" +
        "Called compress\n" +
        "20\n" +
        "MockCreated 14\n" +
        "Called colorCorrection\n" +
        "MockCreated 9\n" +
        "Called colorAdjust\n" +
        "0\n" +
        "150\n" +
        "250\n" +
        "MockCreated 13\n" +
        "Called getColorHistogram\n" +
        "MockCreated 12\n" +
        "Called convolution\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "MockCreated 1\n" +
        "Called previewVerticalSplit\n" +
        "50\n" +
        "MockCreated 11\n" +
        "Called linearTransformation\n" +
        "0.3930.7690.1890.3490.6860.1680.2720.5340.131\n" +
        "MockCreated 6\n" +
        "Called previewVerticalSplit\n" +
        "50\n" +
        "MockCreated 11\n");
  }

  @Test
  public void MockTestFour() throws IOException {
    StringBuilder log = new StringBuilder();
    File file = new File("src/script4.txt");
    InputStream targetStream = new FileInputStream(file);
    new TstController(new InputStreamReader(targetStream), System.out).start(new MockGen(log));
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
    assertEquals(log.toString(), "MockCreated 2\n" +
        "Called brighten\n" +
        "10\n" +
        "MockCreated 2\n" +
        "Called verticalFlip\n" +
        "MockCreated 5\n" +
        "Called horizontalFlip\n" +
        "MockCreated 5\n" +
        "Called maxIMG\n" +
        "MockCreated 3\n" +
        "Called save 2\n" +
        "Called save 3\n" +
        "MockCreated 2\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called splitImage\n" +
        "MockCreated 8\n" +
        "Called brighten\n" +
        "50\n" +
        "MockCreated 2\n" +
        "Called convolution\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "MockCreated 1\n" +
        "Called convolution\n" +
        "-0.125-0.125-0.125-0.125-0.125-0.1250.250.250.25-0.125-0.1250.251.00.25-0.125-" +
        "0.1250.250.250.25-0.125-0.125-0.125-0.125-0.125-0.125\n" +
        "-0.125-0.125-0.125-0.125-0.125-0.1250.250.250.25-0.125-0.1250.251.00.25-0.125-" +
        "0.1250.250.250.25-0.125-0.125-0.125-0.125-0.125-0.125\n" +
        "-0.125-0.125-0.125-0.125-0.125-0.1250.250.250.25-0.125-0.1250.251.00.25-0.125-" +
        "0.1250.250.250.25-0.125-0.125-0.125-0.125-0.125-0.125\n" +
        "MockCreated 1\n" +
        "Called linearTransformation\n" +
        "0.21260.71520.07220.21260.71520.07220.21260.71520.0722\n" +
        "MockCreated 6\n" +
        "Called linearTransformation\n" +
        "0.3930.7690.1890.3490.6860.1680.2720.5340.131\n" +
        "MockCreated 6\n" +
        "Called avgIMG\n" +
        "MockCreated 4\n" +
        "Called getG\n" +
        "Called getB\n" +
        "Called combine\n" +
        "MockCreated 7\n" +
        "Called save 7\n" +
        "MockCreated 2\n" +
        "Called compress\n" +
        "20\n" +
        "MockCreated 14\n" +
        "Called colorCorrection\n" +
        "MockCreated 9\n" +
        "Called colorAdjust\n" +
        "0\n" +
        "150\n" +
        "250\n" +
        "MockCreated 13\n" +
        "Called getColorHistogram\n" +
        "MockCreated 12\n" +
        "Called convolution\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "0.06250.1250.06250.1250.250.1250.06250.1250.0625\n" +
        "MockCreated 1\n" +
        "Called previewVerticalSplit\n" +
        "50\n" +
        "MockCreated 11\n" +
        "Called linearTransformation\n" +
        "0.3930.7690.1890.3490.6860.1680.2720.5340.131\n" +
        "MockCreated 6\n" +
        "Called previewVerticalSplit\n" +
        "50\n" +
        "MockCreated 11\n");
  }
}