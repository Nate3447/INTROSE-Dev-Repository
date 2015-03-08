package ui;

import javax.swing.*;

import classes.JobOrder;

public class JobOrderPanel extends JPanel {
	
	public JLabel orderNoLabel;
	public JLabel referenceNoLabel;
	public JLabel hospitalLabel;
	public JLabel jobDateLabel;
	
	public JobOrderPanel() {
		;
	}
	
	public JobOrderPanel(JobOrder jobOrder) {
		orderNoLabel = new JLabel("Order No.: " + jobOrder.getOrderNo());
		referenceNoLabel = new JLabel("Reference No.: " + jobOrder.getReferenceNo());
		hospitalLabel = new JLabel("Hospital: " + jobOrder.getHospital());
		jobDateLabel = new JLabel("Job Date: " + jobOrder.getJobDate());
		
		this.add(orderNoLabel);
		this.add(referenceNoLabel);
		this.add(hospitalLabel);
		this.add(jobDateLabel);
		
		this.setSize(340, 100);
	}
}
