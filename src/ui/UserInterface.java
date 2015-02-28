package ui;

import java.awt.*;
import javax.swing.*;

public class UserInterface {

	public JFrame interfaceFrame;
	public JPanel jobsPanel;
	public JPanel calendarPanel;
	public Calendar calendar;
	public ScheduledJobs scheduledJobs;
	public Container pane;
	
	public UserInterface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		interfaceFrame = new JFrame ("BEMC Smart Scheduler");
		interfaceFrame.setSize(1200, 800);
		pane = interfaceFrame.getContentPane();
		pane.setLayout(new BorderLayout());
		interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		calendar = new Calendar();
		scheduledJobs = new ScheduledJobs();
		
		calendarPanel = calendar.getCalendarComponent();
		jobsPanel = scheduledJobs.getScheduledJobsComponent();
		
		pane.add(jobsPanel, BorderLayout.WEST);
		pane.add(calendarPanel, BorderLayout.CENTER);
		
		interfaceFrame.setResizable(false);
		interfaceFrame.setVisible(true);
		
	}
	
}
