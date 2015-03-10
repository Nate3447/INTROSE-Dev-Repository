package ui;

import javax.swing.*;
import classes.Engineer;

public class EngineerPanel extends JPanel {

	public JLabel nameLabel;
	public JLabel specialties;	
	
	public EngineerPanel() {
		nameLabel = new JLabel();
	}
	
	public EngineerPanel(Engineer engineer) {
		
		this.setLayout(null);
		
		nameLabel = new JLabel("Name: " + engineer.getName());
		specialties = new JLabel("Specialties: " + engineer.getSpecialties());
		
		this.add(nameLabel);
		this.add(specialties);
		
		nameLabel.setBounds(10, 5, 250, 40);
		specialties.setBounds(10, 50, 250, 40);
		this.setSize(340, 100);
		
	}
}
