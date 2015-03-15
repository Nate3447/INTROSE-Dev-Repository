package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.LineBorder;

import classes.Engineer;

public class EngineerPanel extends JPanel {

	public JLabel nameLabel;
	public JLabel specialties;	
	
	public EngineerPanel() {
		nameLabel = new JLabel();
	}
	
	public EngineerPanel(Engineer engineer) {
		
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.WHITE, 2));
		
		nameLabel = new JLabel("Name: " + engineer.getName());
		specialties = new JLabel("Specialties: " + engineer.getSpecialties());
		
		this.add(nameLabel);
		this.add(specialties);
		
		nameLabel.setBounds(10, 5, 250, 20);
		specialties.setBounds(10, 30, 250, 20);
		this.setSize(340, 60);
		this.setPreferredSize(new Dimension(340, 60));
		
	}
}
