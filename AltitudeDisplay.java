package landerPackage;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;

public class AltitudeDisplay extends JPanel {

    private static final long serialVersionUID = 42l;

    /* TODO create GUI elements to display altitude and related
            information
     */
    //Container panel to contain the values 
    JPanel containerPanel = new JPanel();

    // Create separate panels for each value to be displayed
    JPanel altPanel = new JPanel();
    JPanel orientPanel = new JPanel();
    JPanel hVelocityPanel = new JPanel();
    JPanel vVelocityPanel = new JPanel();

    // Create labels for the values to be displayed
    JLabel altLabel = new JLabel("Altitude:");
    JLabel orientLabel = new JLabel("Orientation:");
    JLabel hVelocityLabel = new JLabel("H. Velocity:");
    JLabel vVelocityLabel = new JLabel("V. Velocity:");

    // Create text fields in which the actual values will be displayed
    JTextField altText = new JTextField("", 7);
    JTextField orientText = new JTextField("", 7);
    JTextField hVelocityText = new JTextField("", 7);
    JTextField vVelocityText = new JTextField("", 7);

    // Create panels that will contain the LEDs
    JPanel LEDPanel = new JPanel();
    JPanel greyLEDPanel = new JPanel();
    JPanel greenLEDPanel = new JPanel();
    JPanel orangeLEDPanel = new JPanel();
    JPanel redLEDPanel = new JPanel();

    // String to hold the path to the user's directory from which the game was launched
    String userDirectory = System.getProperty("user.dir") + "\\landerPackage\\";

    public AltitudeDisplay() {
        super();
        // Use gridlayout, 2 columns and as many rows as required
        this.setLayout(new GridLayout(0, 2));
        this.setBorder(BorderFactory.createTitledBorder("Altitude / Velocity Information:"));

        // Use gridlayout for containerPanel, 2 columns and as many rows as needed
        containerPanel.setLayout(new GridLayout(0, 1));

        // Set layout of the panels to flow layout, aligned 5px from left
        altPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        orientPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        hVelocityPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        vVelocityPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));

        // Set dimensions of the labels so they are aligned
        altLabel.setPreferredSize(new Dimension(80, 15));
        orientLabel.setPreferredSize(new Dimension(80, 15));
        hVelocityLabel.setPreferredSize(new Dimension(80, 15));
        vVelocityLabel.setPreferredSize(new Dimension(80, 15));

        // Add labels and values to the panels
        altPanel.add(altLabel);
        altPanel.add(altText);
        orientPanel.add(orientLabel);
        orientPanel.add(orientText);
        hVelocityPanel.add(hVelocityLabel);
        hVelocityPanel.add(hVelocityText);
        vVelocityPanel.add(vVelocityLabel);
        vVelocityPanel.add(vVelocityText);

        // Add panels to the container panel
        containerPanel.add(altPanel);
        containerPanel.add(orientPanel);
        containerPanel.add(hVelocityPanel);
        containerPanel.add(vVelocityPanel);

        // Add container panel and LED panel to the AltitudeDisplay panel
        this.add(containerPanel);
        this.add(LEDPanel);
        // Change layout of LEDPanel to grid, 4 columns
        LEDPanel.setLayout(new GridLayout(0,4));
        // Add individual LED panels to the LED panel
        LEDPanel.add(greyLEDPanel);
        LEDPanel.add(greenLEDPanel);
        LEDPanel.add(orangeLEDPanel);
        LEDPanel.add(redLEDPanel);

        // Load images
        try {
            // Load the images from the files into objects 
            BufferedImage greyLED = ImageIO.read(new File(userDirectory + "led-grey.png"));
            JLabel greyLEDLabel = new JLabel(new ImageIcon(greyLED));
            BufferedImage greenLED = ImageIO.read(new File(userDirectory + "led-green.png"));
            JLabel greenLEDLabel = new JLabel(new ImageIcon(greenLED));
            BufferedImage orangeLED = ImageIO.read(new File(userDirectory + "led-orange.png"));
            JLabel orangeLEDLabel = new JLabel(new ImageIcon(orangeLED));
            BufferedImage redLED = ImageIO.read(new File(userDirectory + "led-red.png"));
            JLabel redLEDLabel = new JLabel(new ImageIcon(redLED));

            // Add images to the LED panels
            greyLEDPanel.add(greyLEDLabel);
            greenLEDPanel.add(greenLEDLabel);
            orangeLEDPanel.add(orangeLEDLabel);
            redLEDPanel.add(redLEDLabel);

            // Log error messsage to console if the images do not load
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setAltitude(String newAltitude) {
        altText.setText(newAltitude + "m");
        /* TODO update display with altitude value */
    }

    public void setOrientation(String newOrientation) {
        orientText.setText(newOrientation);
    }

    public void setHVelocityText(String newHVelocityText) {
        hVelocityText.setText(newHVelocityText + "mph");
    }

    public void setVVelocityText(String newVVelocityText) {
        vVelocityText.setText(newVVelocityText + "mph");
    }

    public void changeImage(String imageToLoad) {
        // Clear the panel
        LEDPanel.removeAll();

        // Load images
        try {

            // If image to be loaded is the green LED, load and add that image to the LED panel
            if (imageToLoad.equals("green")) {
                BufferedImage greenLED = ImageIO.read(new File(userDirectory + "led-green.png"));
                JLabel greenLEDLabel = new JLabel(new ImageIcon(greenLED));
                LEDPanel.add(greenLEDLabel);
                // Else if image to be loaded is the orange LED, load and add that image to the LED panel
            } else if (imageToLoad.equals("orange")) {
                BufferedImage orangeLED = ImageIO.read(new File(userDirectory + "led-orange.png"));
                JLabel orangeLEDLabel = new JLabel(new ImageIcon(orangeLED));
                LEDPanel.add(orangeLEDLabel);
            } else if (imageToLoad.equals("red")) {
                BufferedImage redLED = ImageIO.read(new File(userDirectory + "led-red.png"));
                JLabel redLEDLabel = new JLabel(new ImageIcon(redLED));
                LEDPanel.add(redLEDLabel);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void changeImageTwo(String imageToLoad) {
        // If image to load is green
        if (imageToLoad.equals("green")) {
            // Hide all LED panels except green
            greenLEDPanel.setVisible(true);
            greyLEDPanel.setVisible(false);
            orangeLEDPanel.setVisible(false);
            redLEDPanel.setVisible(false);
            // else if image to load is orange
        } else if (imageToLoad.equals("orange")) {
            // Hide all LED panels except orange
            orangeLEDPanel.setVisible(true);
            greyLEDPanel.setVisible(false);
            greenLEDPanel.setVisible(false);
            redLEDPanel.setVisible(false);
            // else if image to load is red
        } else if (imageToLoad.equals("red")) {
            // Hide all LED panels except red
            redLEDPanel.setVisible(true);
            greyLEDPanel.setVisible(false);
            greenLEDPanel.setVisible(false);
            orangeLEDPanel.setVisible(false);
        }
    }

}
