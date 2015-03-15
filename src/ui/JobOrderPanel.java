package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.LineBorder;

import classes.JobOrder;

public class JobOrderPanel extends JPanel {
	
	public JLabel orderNoLabel;
	public JLabel referenceNoLabel;
	public JLabel jobDateLabel;
	public JLabel hospitalLabel;
	
	public JobOrderPanel() {
		orderNoLabel = new JLabel();
	}
	
	public JobOrderPanel(JobOrder jobOrder) {
		
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.WHITE, 2));
		
		orderNoLabel = new JLabel("Order No.: " + jobOrder.getOrderNo());
		referenceNoLabel = new JLabel("Reference No.: " + jobOrder.getReferenceNo());
		jobDateLabel = new JLabel("Job Date: " + jobOrder.getJobDate());
		hospitalLabel = new JLabel("Hospital: " + jobOrder.getHospital());
		
		this.add(orderNoLabel);
		this.add(referenceNoLabel);
		this.add(jobDateLabel);
		this.add(hospitalLabel);
		
		orderNoLabel.setBounds(10, 5, 150, 20);
		referenceNoLabel.setBounds(160, 5, 150, 20);
		jobDateLabel.setBounds(10, 30, 150, 20);
		hospitalLabel.setBounds(10, 55, 250, 20);
		this.setSize(340, 100);
		this.setPreferredSize(new Dimension(340, 100));
		
	}
}
