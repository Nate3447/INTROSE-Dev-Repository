package ui;

import javax.swing.*;

public class ScheduledJobs {

	public JPanel jobsPanel;
	
	
	public JPanel getScheduledJobsComponent() {
		
		jobsPanel = new JPanel();
		jobsPanel.setSize(400, 400);
		
		return jobsPanel;
	}
}
