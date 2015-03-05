package ui;

import javax.swing.*;
import classes.Engineer;

public class EngineerPanel extends JPanel {

	public JLabel nameLabel;
	public JLabel specialties;	
	
	public EngineerPanel() {
		nameLabel = new JLabel();
		specialties = new JLabel();
		
		this.add(nameLabel);
		this.add(specialties);
		
		this.setSize(0, 0);
		
	}
	
	public EngineerPanel(Engineer engineer) {
		nameLabel = new JLabel("Name: " + engineer.getName());
		specialties = new JLabel("Specialties: " + specialties);
		
		this.add(nameLabel);
		this.add(specialties);
		
		this.setSize(340, 100);
		
	}
}
