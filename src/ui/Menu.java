package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu {
	public JButton addEngineerButton, addEquipmentButton, addJobOrderButton;
	public JPanel menuPanel;
	
	public JPanel getMenuComponent() {
		
		addEngineerButton = new JButton("Add Engineer");
		addEquipmentButton = new JButton("Add Equipment");
		addJobOrderButton = new JButton("Add Job Order");
		
		menuPanel = new JPanel(null);
		
		addEngineerButton.addActionListener(new addEngineerButtonAction());
		addEquipmentButton.addActionListener(new addEquipmentButtonAction());
		addJobOrderButton.addActionListener(new addJobOrderButtonAction());
		
		menuPanel.add(addEngineerButton);
		menuPanel.add(addEquipmentButton);
		menuPanel.add(addJobOrderButton);
		
		menuPanel.setBounds(0, 0, 200, 600);
		addEngineerButton.setBounds(50, 150, 150, 50);
		addEquipmentButton.setBounds(50, 250, 150, 50);
		addJobOrderButton.setBounds(50, 350, 150, 50);
		
		return menuPanel;
		
	}
	
	class addEngineerButtonAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
		}
	}
	
	class addEquipmentButtonAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
		}
	}
	
	class addJobOrderButtonAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			
		}
	}
}
