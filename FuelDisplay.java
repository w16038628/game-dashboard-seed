package landerPackage;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.*;

public class FuelDisplay extends JPanel {

    private static final long serialVersionUID = 42l;
    // Create label for the fuel value to be displayed
    JLabel fuelLabel = new JLabel("Fuel Level:");
    // Create text field in which the actual value will be displayed
    JTextField fuelText = new JTextField("", 7);

    /* TODO create GUI elements to display fuel state
            and related information
     */
    public FuelDisplay() {
        super();
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        this.setBorder(BorderFactory.createTitledBorder("Fuel Information:"));

        // Set dimensions of the label
        fuelLabel.setPreferredSize(new Dimension(80, 15));

        this.add(fuelLabel);
        this.add(fuelText);

    }

    public void setFuel(String newFuelLevel) {
        fuelText.setText(newFuelLevel + "%");
    }
}
