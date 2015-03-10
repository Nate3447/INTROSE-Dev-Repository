package ui;

import javax.swing.*;
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
		
		orderNoLabel = new JLabel("Order No.: " + jobOrder.getOrderNo());
		referenceNoLabel = new JLabel("Reference No.: " + jobOrder.getReferenceNo());
		jobDateLabel = new JLabel("Job Date: " + jobOrder.getJobDate());
		hospitalLabel = new JLabel("Hospital: " + jobOrder.getHospital());
		
		this.add(orderNoLabel);
		this.add(referenceNoLabel);
		this.add(jobDateLabel);
		this.add(hospitalLabel);
		
		orderNoLabel.setBounds(10, 5, 150, 40);
		referenceNoLabel.setBounds(160, 5, 150, 40);
		jobDateLabel.setBounds(10, 50, 150, 40);
		hospitalLabel.setBounds(10, 95, 150, 40);
		this.setSize(340, 100);
	}
	
}
