package ui;

import javax.swing.*;
import classes.Engineer;

public class EngineerPanel extends JPanel {

	public JLabel nameLabel;
	public JLabel specialties;	
	
	public EngineerPanel(Engineer engineer) {
		nameLabel.setText("Name: " + engineer.getName());
		specialties.setText("Specialties: ");
		
		this.add(nameLabel);
		this.add(specialties);
		
	}
}
