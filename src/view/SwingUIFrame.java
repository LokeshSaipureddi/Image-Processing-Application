package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;

/**
 * Implementation of a GUI View using Java Spring.
 */
public class SwingUIFrame extends JFrame implements View {
  private final JLabel fileOpenDisplay;
  private final JLabel saveImageDisplay;
  private final JLabel imageLabel;
  private final JLabel imageLabel1;
  private final JPanel imagePanel;
  private String getPath;
  private String getSavePath;
  private int compressValue;
  private JButton sepia;
  private JButton gray;
  private JButton rComp;
  private JButton gComp;
  private JButton hFlip;
  private JButton vFlip;
  private JButton blur;
  private JButton sharpen;
  private JButton compress;
  private JButton levelAdjust;
  private JButton colorCorrect;
  private JButton bComp;

  private JButton fileOpenButton;
  private JButton fileSaveButton;
  private JSlider split;
  private JSlider black;
  private JSlider mid;
  private JSlider white;
  private JSlider comp;

  /**
   * Create the main GUI Panel.
   */
  public SwingUIFrame() {
    super();

    // NOTE: this function is not long or unwieldy logic. UI is set in a straight forward way,
    // just GUI set up takes a lot of lines.
    // Set parameters of the main Panel.
    JFrame.setDefaultLookAndFeelDecorated(false);
    setTitle("Swing UI");
    setSize(1000, 800);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel mmPanel = new JPanel();
    mmPanel.setLayout(new BoxLayout(mmPanel, BoxLayout.Y_AXIS));
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
    JPanel mainPanel11 = new JPanel();
    mainPanel11.setLayout(new BoxLayout(mainPanel11, BoxLayout.Y_AXIS));

    // Set Image Panel.
    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image-1"));
    imagePanel.setLayout(new BorderLayout());
    imageLabel = new JLabel();
    imagePanel.add(imageLabel, BorderLayout.CENTER);
    Dimension imageSize = new Dimension(500, 700);
    imagePanel.setPreferredSize(imageSize);
    JScrollPane sp = new JScrollPane(imagePanel);
    mainPanel.add(sp);

    // Set Histogram Panel.
    JPanel imagePanel1 = new JPanel();
    imagePanel1.setPreferredSize(new Dimension(500, 300));
    imagePanel1.setLayout(new BorderLayout());
    imageLabel1 = new JLabel();
    imagePanel1.add(imageLabel1, BorderLayout.CENTER);

    // Set Sepia and Gray Buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.setBorder(BorderFactory.createTitledBorder("Convolution Operation"));
    sepia = new JButton("Sepia");
    sepia.setActionCommand("Sepia");
    buttonPanel.add(sepia);
    gray = new JButton("GrayScale");
    gray.setActionCommand("Gray");
    buttonPanel.add(gray);
    JScrollPane buttonScrollPane = new JScrollPane(buttonPanel);
    mainPanel11.add(buttonScrollPane);

    // Set RGB component Buttons.
    JPanel buttonPanel4 = new JPanel();
    buttonPanel4.setLayout(new FlowLayout());
    buttonPanel4.setBorder(BorderFactory.createTitledBorder("Operations"));
    rComp = new JButton("Red Component");
    rComp.setActionCommand("rcom");
    buttonPanel4.add(rComp);
    bComp = new JButton("Blue Component");
    bComp.setActionCommand("bcom");
    buttonPanel4.add(bComp);
    gComp = new JButton("Green Component");
    gComp.setActionCommand("gcom");
    buttonPanel4.add(gComp);
    JScrollPane buttonScrollPane4 = new JScrollPane(buttonPanel4);
    mainPanel11.add(buttonScrollPane4);

    // Set Horizontal and Vertical Flip buttons.
    JPanel buttonPanel1 = new JPanel();
    buttonPanel1.setLayout(new FlowLayout());
    buttonPanel1.setBorder(BorderFactory.createTitledBorder("Basic Operations"));
    hFlip = new JButton("Horizontal-Flip");
    hFlip.setActionCommand("HF");
    buttonPanel1.add(hFlip);
    vFlip = new JButton("Vertical-Flip");
    vFlip.setActionCommand("VF");
    buttonPanel1.add(vFlip);
    JScrollPane buttonScrollPane1 = new JScrollPane(buttonPanel1);
    mainPanel11.add(buttonScrollPane1);

    // Set Blur and Sharpen buttons.
    JPanel buttonPanel2 = new JPanel();
    buttonPanel2.setLayout(new FlowLayout());
    buttonPanel2.setBorder(BorderFactory.createTitledBorder("EW Operation"));
    blur = new JButton("Blur");
    blur.setActionCommand("Blr");
    buttonPanel2.add(blur);
    sharpen = new JButton("Sharpen");
    sharpen.setActionCommand("Shrp");
    buttonPanel2.add(sharpen);
    JScrollPane buttonScrollPane2 = new JScrollPane(buttonPanel2);
    mainPanel11.add(buttonScrollPane2);

    //Set Compress and Level Adjust Buttons.
    JPanel buttonPanel3 = new JPanel();
    buttonPanel3.setLayout(new FlowLayout());
    buttonPanel3.setBorder(BorderFactory.createTitledBorder("New Operations"));
    compress = new JButton("Compress");
    compress.setActionCommand("compress");
    buttonPanel3.add(compress);
    levelAdjust = new JButton("Level Adjust");
    buttonPanel3.add(levelAdjust);
    levelAdjust.setActionCommand("levels-adjust");
    colorCorrect = new JButton("Color Correction");
    colorCorrect.setActionCommand("cc");
    buttonPanel3.add(colorCorrect);
    JScrollPane buttonScrollPane3 = new JScrollPane(buttonPanel3);
    mainPanel11.add(buttonScrollPane3);
    mainPanel11.add(imagePanel1);
    mainPanel.add(mainPanel11);
    mmPanel.add(mainPanel);


    //file open
    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new FlowLayout());
    mmPanel.add(fileOpenPanel);
    fileOpenButton = new JButton("Open a file");
    fileOpenButton.setActionCommand("Open file");
    fileOpenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel("File path will appear here");
    fileOpenPanel.add(fileOpenDisplay, BorderLayout.SOUTH);

    //file save
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    mmPanel.add(fileSavePanel);
    fileSaveButton = new JButton("Save a file");
    fileSaveButton.setActionCommand("Save file");
    fileSavePanel.add(fileSaveButton);
    saveImageDisplay = new JLabel("File path will appear here");
    fileSavePanel.add(saveImageDisplay, BorderLayout.SOUTH);

    setContentPane(mmPanel);
    setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    sepia.addActionListener(s -> showNumberSliderPopup(features::sepia,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    gray.addActionListener(s -> showNumberSliderPopup(features::luma,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    blur.addActionListener(s -> showNumberSliderPopup(features::blur,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    sharpen.addActionListener(s -> showNumberSliderPopup(features::sharpen,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    rComp.addActionListener(s -> showNumberSliderPopup(features::getRChannel,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    gComp.addActionListener(s -> showNumberSliderPopup(features::getGChannel,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    bComp.addActionListener(s -> showNumberSliderPopup(features::getBChannel,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    hFlip.addActionListener(s -> showNumberSliderPopup(features::horizontalFlip,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    vFlip.addActionListener(s -> showNumberSliderPopup(features::verticalFlip,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    colorCorrect.addActionListener(s -> showNumberSliderPopup(features::colorCorrect,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));

    fileOpenButton.addActionListener(s -> imageLoad(features::load, features::saveOnClose));
    fileSaveButton.addActionListener(s -> imageSave(features::save));

    levelAdjust.addActionListener(s -> levelAdjust(features::colorAdjust,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));

    compress.addActionListener(s -> showCompression(features::compress,
        features::togglePreviewModeOn, features::togglePreviewModeOff, features::getPreviewImage));
    addWindowListener(new WindowEventHandler(features::saveOnClose));

  }

  private void imageLoad(Runnable loader, Runnable saveLoader) {
    saveLoader.run();
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filer = new FileNameExtensionFilter("Images (ppm, jpeg, " +
        "png, jpg)", "ppm", "png", "jpeg", "jpg");
    fchooser.setFileFilter(filer);
    int retvalue = fchooser.showOpenDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      getPath = f.getAbsolutePath();
      fileOpenDisplay.setText(f.getAbsolutePath());
      try {
        loader.run();
      } catch (IllegalStateException e) {
        setErrorMessage(e.getMessage());
        return;
      }
      adjustImagePanelSize();
    }
  }

  private void imageSave(Runnable loader) {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filerPpm = new FileNameExtensionFilter("ppm",
        "ppm");
    FileNameExtensionFilter filerJpeg = new FileNameExtensionFilter("jpeg",
        "jpeg");
    FileNameExtensionFilter filerJpg = new FileNameExtensionFilter("jpg",
        "jpg");
    FileNameExtensionFilter filerPng = new FileNameExtensionFilter("png",
        "png");
    fchooser.addChoosableFileFilter(filerJpg);
    fchooser.addChoosableFileFilter(filerJpeg);
    fchooser.addChoosableFileFilter(filerPpm);
    fchooser.setFileFilter(filerPng);
    fchooser.setAcceptAllFileFilterUsed(false);
    int retvalue = fchooser.showSaveDialog(this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      getSavePath = f.getAbsolutePath() + "." + fchooser.getFileFilter().getDescription();
      saveImageDisplay.setText(f.getAbsolutePath());
      try {
        loader.run();
      } catch (IllegalStateException e) {
        setErrorMessage(e.getMessage());
        return;
      }
      adjustImagePanelSize();
    }
  }

  private void levelAdjust(Runnable func, Runnable previewModeOn, Runnable previewModeOff,
                           Runnable getPreview) {
    JPanel panel = new JPanel();
    previewModeOn.run();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    split = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    JLabel valueLabel = new JLabel("Split Percent: 50");

    black = new JSlider(JSlider.HORIZONTAL, 0, 255, 50);
    JLabel valueLabel1 = new JLabel("Black: 50");
    mid = new JSlider(JSlider.HORIZONTAL, 0, 255, 50);
    JLabel valueLabel2 = new JLabel("Mid: 50");
    white = new JSlider(JSlider.HORIZONTAL, 0, 255, 50);
    JLabel valueLabel3 = new JLabel("White: 50");

    try {
      func.run();
    } catch (IllegalStateException e) {
      setErrorMessage(e.getMessage());
      previewModeOff.run();
      return;
    }
    split.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        valueLabel.setText("Slider Value: " + split.getValue());
        getPreview.run();
      }
    });
    black.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        valueLabel1.setText("Slider Value: " + black.getValue());
        try {
          func.run();
        } catch (IllegalStateException er) {
          setErrorMessage(er.getMessage());
          previewModeOff.run();
          return;
        }
      }
    });

    mid.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        valueLabel2.setText("Slider Value: " + mid.getValue());
        try {
          func.run();
        } catch (IllegalStateException er) {
          setErrorMessage(er.getMessage());
          previewModeOff.run();
          return;
        }
      }
    });

    white.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        valueLabel3.setText("Slider Value: " + white.getValue());
        try {
          func.run();
        } catch (IllegalStateException er) {
          setErrorMessage(er.getMessage());
          previewModeOff.run();
          return;
        }
      }
    });


    panel.add(new JLabel("Select a percentage for the split"));
    panel.add(split);
    panel.add(valueLabel);

    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    panel.add(new JLabel("Select value for Black:"));
    panel.add(black);
    panel.add(valueLabel1);

    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    panel.add(new JLabel("Select value for Mid:"));
    panel.add(mid);
    panel.add(valueLabel2);

    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    panel.add(new JLabel("Select value for White:"));
    panel.add(white);
    panel.add(valueLabel3);

    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    int result = JOptionPane.showConfirmDialog(
        this,
        panel,
        "Parameters",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    split.setValue(0);
    previewModeOff.run();
    if (result == JOptionPane.OK_OPTION) {
      func.run();
    } else {
      split.setValue(0);
    }
  }


  private void showCompression(Runnable func, Runnable previewModeOn, Runnable previewModeOff,
                               Runnable getPreview) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    comp = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    JLabel valueLabel2 = new JLabel("Compression Value: 50");

    comp.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        valueLabel2.setText("Compression Value: " + comp.getValue());
      }
    });

    panel.add(Box.createRigidArea(new Dimension(0, 10)));

    panel.add(new JLabel("Select percent for the compression"));
    panel.add(comp);
    panel.add(valueLabel2);

    int result = JOptionPane.showConfirmDialog(
        this,
        panel,
        "Parameters",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );
    if (result == JOptionPane.OK_OPTION) {
      compressValue = comp.getValue();
      func.run();
    }
  }


  private void showNumberSliderPopup(Runnable func, Runnable previewModeOn,
                                     Runnable previewModeOff, Runnable getPreview) {
    JPanel panel = new JPanel();

    split = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
    JLabel valueLabel = new JLabel("Selected Value: 100");
    previewModeOn.run();
    try {
      func.run();
    } catch (IllegalStateException e) {
      setErrorMessage(e.getMessage());
      previewModeOff.run();
      return;
    }
    split.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        valueLabel.setText("Selected Value: " + split.getValue());
        getPreview.run();
      }
    });
    panel.add(new JLabel("Select a number:"));
    panel.add(split);
    panel.add(valueLabel);

    int result = JOptionPane.showConfirmDialog(null, panel,
        "Split View", JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE);
    split.setValue(0);
    previewModeOff.run();
    if (result == JOptionPane.OK_OPTION) {
      func.run();
    }
  }

  private void adjustImagePanelSize() {
    int imageWidth = imageLabel.getIcon().getIconWidth();
    int imageHeight = imageLabel.getIcon().getIconHeight();
    Dimension imageSize = new Dimension(imageWidth, imageHeight);
    imagePanel.setPreferredSize(imageSize);
    imagePanel.setMaximumSize(imageSize);
    imagePanel.setMinimumSize(imageSize);
    revalidate();
    repaint();
  }

  @Override
  public void setImage(Image img) {
    ImageIcon imageIcon = new ImageIcon(img);
    imageLabel.setIcon(imageIcon);
  }

  @Override
  public void setHist(Image img) {
    ImageIcon imageIcon = new ImageIcon(img);
    imageLabel1.setIcon(imageIcon);
  }

  @Override
  public String getLoadPath() {
    return getPath;
  }

  @Override
  public String getSavePath() {
    return getSavePath;
  }

  @Override
  public String getCompressPerc() {
    return String.valueOf(compressValue);
  }

  @Override
  public void setCompressPerc(String p) {
    compressValue = Integer.parseInt(p);
  }

  @Override
  public String getBlack() {
    return String.valueOf(black.getValue());
  }

  @Override
  public void setBlack(String b) {
    black.setValue(Integer.parseInt(b));
  }

  @Override
  public String getMid() {
    return String.valueOf(mid.getValue());
  }

  @Override
  public void setMid(String m) {
    mid.setValue(Integer.parseInt(m));
  }

  @Override
  public String getWhite() {
    return String.valueOf(white.getValue());
  }

  @Override
  public void setWhite(String m) {
    white.setValue(Integer.parseInt(m));
  }

  @Override
  public String getPreviewPercentage() {
    return String.valueOf(split.getValue());
  }

  @Override
  public void setPreviewPercentage(String p) {
    //We are using sliders, so no need to implemented it.
  }

  @Override
  public void setPreviewModeLabel(String m) {
    //We don't need any label to show it.
  }

  @Override
  public void setErrorMessage(String m) {
    JOptionPane.showMessageDialog(new JFrame(), m, "ERROR",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void askSave() {
    JPanel panel = new JPanel();
    panel.add(new JLabel("Save the image?"));
    int result = JOptionPane.showConfirmDialog(
        this,
        panel,
        "Save",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );

    if (result == JOptionPane.OK_OPTION) {
      fileSaveButton.doClick();
    }
  }

  private class WindowEventHandler extends WindowAdapter {
    private final Runnable func;

    WindowEventHandler(Runnable func) {
      this.func = func;
    }

    public void windowClosing(WindowEvent evt) {
      func.run();
      System.exit(0);
    }
  }
}
