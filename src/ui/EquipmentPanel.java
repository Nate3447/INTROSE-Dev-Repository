package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.LineBorder;

import classes.Equipment;

public class EquipmentPanel extends JPanel {
	
	private String type;
	
	public JLabel nameLabel;
	public JLabel typeLabel;
	public JLabel calibrationDateLabel;
	public JLabel serialNoLabel;
	
	public EquipmentPanel() {
		nameLabel = new JLabel();
	}
	
	public EquipmentPanel(Equipment equipment) {
		
		this.setLayout(null);
		this.setBorder(new LineBorder (Color.WHITE, 2));
		
		nameLabel = new JLabel("Equipment: " + equipment.getName());
		if(equipment.getType() == 1){
			type = "formula";
		} else {
			type = "type";
		}
		typeLabel = new JLabel("Type: " + type);
		calibrationDateLabel = new JLabel("Calibration Date: " + equipment.getCalibrationDate());
		serialNoLabel = new JLabel("Serial Number: " + equipment.getSerialNo());
		
		this.add(nameLabel);
		this.add(typeLabel);
		this.add(calibrationDateLabel);
		this.add(serialNoLabel);
		
		nameLabel.setBounds(10, 5, 240, 20);
		typeLabel.setBounds(10, 25, 150, 20);
		calibrationDateLabel.setBounds(10, 45, 150, 20);
		serialNoLabel.setBounds(10, 65, 250, 20);
		this.setSize(340, 90);
		this.setPreferredSize(new Dimension(340, 90));
	}
}
