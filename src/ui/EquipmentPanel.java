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
	
	public EquipmentPanel(Equipment equipment) {
		nameLabel.setText("Equipment: " + equipment.getName());
		idNumberLabel.setText("ID No.: " + equipment.getIDNumber());
		locationLabel.setText("Location: " + equipment.getLocation());
		borrowDateLabel.setText("Borrow Date: " + equipment.getBorrowDate());
		returnDateLabel.setText("Return Date: " + equipment.getReturnDate());
		calibrationDateLabel.setText("Calibration Date: " + equipment.getCalibrationDate());
		borrowerNameLabel.setText("Borrower Name: " + equipment.getBorrowerName());
		
		this.add(nameLabel);
		this.add(idNumberLabel);
		this.add(locationLabel);
		this.add(borrowDateLabel);
		this.add(returnDateLabel);
		this.add(calibrationDateLabel);
		this.add(borrowerNameLabel);
	}
}
