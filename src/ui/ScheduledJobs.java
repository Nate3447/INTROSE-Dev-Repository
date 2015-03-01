package ui;

import javax.swing.*;

public class ScheduledJobs {

	public JPanel jobsPanel;
	public JLabel orderNoLabel, referenceNoLabel, jobDateLabel, completionDateLabel, 
		hospitalLabel, addressLabel, contactPersonLabel, contactNoLabel;
	
	
	public JPanel getScheduledJobsComponent() {
		
		orderNoLabel = new JLabel("Order No: ");
		referenceNoLabel = new JLabel("Reference No: ");
		jobDateLabel = new JLabel("Job Date: ");
		completionDateLabel = new JLabel("Completion Date");
		hospitalLabel = new JLabel("Hospital: ");
		addressLabel = new JLabel("Address: ");
		contactPersonLabel = new JLabel("Contact Person: ");
		contactNoLabel = new JLabel("Contact No: ");
		
		jobsPanel = new JPanel(null);
		
		jobsPanel.add(orderNoLabel);
		jobsPanel.add(referenceNoLabel);
		jobsPanel.add(jobDateLabel);
		jobsPanel.add(completionDateLabel);
		jobsPanel.add(hospitalLabel);
		jobsPanel.add(addressLabel);
		jobsPanel.add(contactPersonLabel);
		jobsPanel.add(contactNoLabel);
		
		jobsPanel.setBounds(900, 0, 200, 600);
		orderNoLabel.setBounds(50, 50, 100, 40);
		referenceNoLabel.setBounds(50, 100, 100, 40);
		jobDateLabel.setBounds(50, 150, 50, 40);
		completionDateLabel.setBounds(50, 200, 100, 40);
		hospitalLabel.setBounds(50, 250, 100, 40);
		addressLabel.setBounds(50, 300, 100, 40);
		contactPersonLabel.setBounds(50, 350, 100, 40);
		contactNoLabel.setBounds(50, 400, 100, 40);
		
		return jobsPanel;
	}
}
