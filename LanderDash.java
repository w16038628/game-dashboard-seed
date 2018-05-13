package landerPackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class LanderDash extends JFrame implements Runnable {

    public static final long serialVersionUID = 2L;

    public static void main(String[] args) throws UnknownHostException {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LanderDash(args[0], Integer.parseInt(args[1]));
            }
        });
    }

    /* Information from Lander to Display */
 /* TODO  Declare some variables to hold Information
        from the game controller to display
     */
    private InetSocketAddress address;

    /* Panel to display IP and port numnber */
    DatagramPanel connection = new DatagramPanel();
    // Declare Fuel and Altitude panels
    FuelDisplay fuelPanel = new FuelDisplay();
    AltitudeDisplay altitudeDisplay = new AltitudeDisplay();

    public LanderDash(String ip, int port) {
        // Create frame and set size, layout options etc
        super("Lunar Lander Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(
                new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Set Y Axis alignment of panels to centre
        connection.setAlignmentY(CENTER_ALIGNMENT);
        altitudeDisplay.setAlignmentY(CENTER_ALIGNMENT);
        fuelPanel.setAlignmentY(CENTER_ALIGNMENT);

        // Set X Axis alignment of panels to left
        connection.setAlignmentX(LEFT_ALIGNMENT);
        altitudeDisplay.setAlignmentX(LEFT_ALIGNMENT);
        fuelPanel.setAlignmentX(LEFT_ALIGNMENT);

        // Create DatagramPanel
        address = new InetSocketAddress(ip, port);
        connection.setAddress(address);

        // pack();
        setSize(400, 250);
        this.setResizable(false);
        setVisible(true);
        (new Thread(this)).start();

        // Add panels to the frame
        this.add(connection);
        this.add(altitudeDisplay);
        this.add(fuelPanel);

    }

    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(address);
            while (true) {
                /* set up socket for reception */
                if (socket != null) {
                    /* start with fresh datagram packet */
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    /* extract message and pick appart into
                       lines and key:value pairs
                     */
                    String message = new String(packet.getData());


                    /*TODO adjust message parsing, if you're not
                        using key:value pairs in a line oriented
                        protocol
                     */
                    String[] lines = message.trim().split("\n");
                    for (String l : lines) {
                        String[] pair = l.split(":");

                        // If the key is "altitude", set value of altitude field to new altitude
                        if (pair[0].equals("altitude")) {
                            altitudeDisplay.setAltitude(pair[1]);

                            // If key is "fuel"
                        } else if (pair[0].equals("fuel")) {

                            fuelPanel.setFuel(pair[1]);

                            // If key is "flying"
                        } else if (pair[0].equals("flying")) {
                            if (pair[1].equals("1")) {
                                altitudeDisplay.changeImageTwo("green");
                            } else {
                                altitudeDisplay.changeImageTwo("red");
                            }

                            // If key is "orientation", set value of orientation field to new orientation
                        } else if (pair[0].equals("orientation")) {
                            altitudeDisplay.setOrientation(pair[1]);

                            // If key is "Vx", set value of horizontal velocity to new horizontal velocity
                        } else if (pair[0].equals("Vx")) {
                            altitudeDisplay.setHVelocityText(pair[1]);

                            // If key is "Vy", set value of vertical velocity to new vertical velocity
                        } else if (pair[0].equals("Vy")) {
                            altitudeDisplay.setVVelocityText(pair[1]);
                        }

                    }
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                }
            }
        } catch (Exception e) {
            System.err.println(e);
            System.err.println("in LanderDash.run()");
            System.exit(-1);
        }
    }
}
