/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uit.ensak.scraping;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author Fighter
 */
public class Notifications {
    public  void operationOK() {
//String message = "Operation effectuee avec succes";
String header = "Operation effectuee avec succes :)";
JFrame frame = new JFrame();
frame.setSize(300,125);
frame.setLayout(new GridBagLayout());
GridBagConstraints constraints = new GridBagConstraints();
constraints.gridx = 0;
constraints.gridy = 0;
constraints.weightx = 1.0f;
constraints.weighty = 1.0f;
constraints.insets = new Insets(5, 5, 5, 5);
constraints.fill = GridBagConstraints.BOTH;
JLabel headingLabel = new JLabel(header);
ImageIcon icon = new ImageIcon("C:\\Users\\Fighter\\Desktop\\good.png");
//ImageIcon icon2 = new ImageIcon("good.png");
headingLabel .setIcon(icon); // --- use image icon you want to be as heading image.
headingLabel.setOpaque(false);
frame.add(headingLabel, constraints);
constraints.gridx++;
constraints.weightx = 0f;
constraints.weighty = 0f;
constraints.fill = GridBagConstraints.NONE;
constraints.anchor = GridBagConstraints.NORTH;
//JButton cloesButton = new JButton("X");
//cloesButton.setMargin(new Insets(1, 4, 1, 4));
//cloesButton.setFocusable(false);
//frame.add(cloesButton, constraints);
constraints.gridx = 0;
constraints.gridy++;
constraints.weightx = 1.0f;
constraints.weighty = 1.0f;
constraints.insets = new Insets(5, 5, 5, 5);
constraints.fill = GridBagConstraints.BOTH;
//JLabel messageLabel = new JLabel("<HtMl>"+message);
//frame.add(messageLabel, constraints);
//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
frame.setVisible(true);
    }
    
    public  void operationKO() {
//String message = "Operation effectuee avec succes";
String header = "Operation non aboutie OOPS  :(";
JFrame frame = new JFrame();
frame.setSize(300,125);
frame.setLayout(new GridBagLayout());
GridBagConstraints constraints = new GridBagConstraints();
constraints.gridx = 0;
constraints.gridy = 0;
constraints.weightx = 1.0f;
constraints.weighty = 1.0f;
constraints.insets = new Insets(5, 5, 5, 5);
constraints.fill = GridBagConstraints.BOTH;
JLabel headingLabel = new JLabel(header);
ImageIcon icon = new ImageIcon("C:\\Users\\Fighter\\Desktop\\no.png");
//ImageIcon icon2 = new ImageIcon("good.png");
headingLabel .setIcon(icon); // --- use image icon you want to be as heading image.
headingLabel.setOpaque(false);
frame.add(headingLabel, constraints);
constraints.gridx++;
constraints.weightx = 0f;
constraints.weighty = 0f;
constraints.fill = GridBagConstraints.NONE;
constraints.anchor = GridBagConstraints.NORTH;
//JButton cloesButton = new JButton("X");
//cloesButton.setMargin(new Insets(1, 4, 1, 4));
//cloesButton.setFocusable(false);
//frame.add(cloesButton, constraints);
constraints.gridx = 0;
constraints.gridy++;
constraints.weightx = 1.0f;
constraints.weighty = 1.0f;
constraints.insets = new Insets(5, 5, 5, 5);
constraints.fill = GridBagConstraints.BOTH;
//JLabel messageLabel = new JLabel("<HtMl>"+message);
//frame.add(messageLabel, constraints);
//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
frame.setVisible(true);
    }
    
}
