package ui;

import javax.swing.*;
import classes.Equipment;

public class EquipmentPanel extends JPanel {
	
	public JLabel nameLabel;
	public JLabel idNumberLabel;
	public JLabel locationLabel;
	public JLabel borrowDateLabel;
	public JLabel returnDateLabel;
	public JLabel calibrationDateLabel;
	public JLabel borrowerNameLabel;
	
	public EquipmentPanel() {
		nameLabel = new JLabel();
	}
	
	public EquipmentPanel(Equipment equipment) {
		nameLabel = new JLabel("Equipment: " + equipment.getName());
		idNumberLabel = new JLabel("ID No.: " + equipment.getIDNumber());
		locationLabel = new JLabel("Location: " + equipment.getLocation());
		borrowDateLabel = new JLabel("Borrow Date: " + equipment.getBorrowDate());
		returnDateLabel = new JLabel("Return Date: " + equipment.getReturnDate());
		calibrationDateLabel = new JLabel("Calibration Date: " + equipment.getCalibrationDate());
		borrowerNameLabel = new JLabel("Borrower Name: " + equipment.getBorrowerName());
		
		this.add(nameLabel);
		this.add(idNumberLabel);
		this.add(locationLabel);
		this.add(borrowDateLabel);
		this.add(returnDateLabel);
		this.add(calibrationDateLabel);
		this.add(borrowerNameLabel);
		
		this.setSize(340, 100);
	}
}
