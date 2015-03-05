package ui;

import javax.swing.*;
import classes.JobOrder;

public class JobOrderPanel extends JPanel {
	
	public JLabel orderNoLabel;
	public JLabel referenceNoLabel;
	public JLabel hospitalLabel;
	public JLabel jobDateLabel;
	
	public JobOrderPanel(JobOrder jobOrder) {
		orderNoLabel.setText("Order No.: " + jobOrder.getOrderNo());
		referenceNoLabel.setText("Reference No.: " + jobOrder.getReferenceNo());
		hospitalLabel.setText("Hospital: " + jobOrder.getHospital());
		jobDateLabel.setText("Job Date: " + jobOrder.getJobDate());
		
		this.add(orderNoLabel);
		this.add(referenceNoLabel);
		this.add(hospitalLabel);
		this.add(jobDateLabel);
		
	}
}
