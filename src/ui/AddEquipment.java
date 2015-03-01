package ui;

import javax.swing.*;

public class AddEquipment {
	
	public JPanel addEquipmentPanel;
	public JLabel nameLabel, idNumberLabel, locationLabel, 
		borrowDateLabel, returnDateLabel, calibrationDateLabel, borrowerNameLabel;

	public JPanel getAddEquipmentComponent() {
		
		nameLabel = new JLabel("Equipment: ");
		idNumberLabel = new JLabel("Id No: ");
		locationLabel = new JLabel("Location: ");
		borrowDateLabel = new JLabel("Borrow Date: ");
		returnDateLabel = new JLabel("Return Date: ");
		calibrationDateLabel = new JLabel("Calibration Date: ");
		borrowerNameLabel = new JLabel("Borrower Name: ");
		
		addEquipmentPanel = new JPanel();
		
		addEquipmentPanel.add(nameLabel);
		addEquipmentPanel.add(idNumberLabel);
		addEquipmentPanel.add(locationLabel);
		addEquipmentPanel.add(borrowDateLabel);
		addEquipmentPanel.add(returnDateLabel);
		addEquipmentPanel.add(calibrationDateLabel);
		addEquipmentPanel.add(borrowerNameLabel);
		
		addEquipmentPanel.setBounds(0, 0, 0, 0);
		nameLabel.setBounds(0, 0, 0, 0);
		idNumberLabel.setBounds(0, 0, 0, 0);
		locationLabel.setBounds(0, 0, 0, 0);
		borrowDateLabel.setBounds(0, 0, 0, 0);
		returnDateLabel.setBounds(0, 0, 0, 0);
		calibrationDateLabel.setBounds(0, 0, 0, 0);
		borrowerNameLabel.setBounds(0, 0, 0, 0);
		
		return addEquipmentPanel;
	}
	
}
