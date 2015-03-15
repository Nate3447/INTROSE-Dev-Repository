package ui;

import java.awt.Dimension;

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
		
		this.setLayout(null);
		
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
		
		nameLabel.setBounds(10, 5, 240, 20);
		idNumberLabel.setBounds(240, 5, 250, 20);
		locationLabel.setBounds(10, 30, 250, 20);
		borrowDateLabel.setBounds(10, 80, 150, 20);
		returnDateLabel.setBounds(200, 80, 150, 20);
		calibrationDateLabel.setBounds(10, 105, 150, 20);
		borrowerNameLabel.setBounds(10, 55, 250, 20);
		this.setSize(340, 150);
		this.setPreferredSize(new Dimension(340, 150));
	}
}
