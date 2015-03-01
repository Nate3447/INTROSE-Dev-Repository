package ui;

import java.awt.*;
import javax.swing.*;

public class UserInterface {

	public JFrame interfaceFrame;
	public JPanel jobsPanel;
	public JPanel calendarPanel;
	public JPanel menuPanel;
	public Calendar calendar;
	public ScheduledJobs scheduledJobs;
	public Menu menu;
	public Container pane;
	
	public UserInterface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		interfaceFrame = new JFrame ("BEMC Smart Scheduler");
		interfaceFrame.setSize(1200, 800);
		pane = interfaceFrame.getContentPane();
		pane.setLayout(null);
		interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menu = new Menu();
		calendar = new Calendar();
		scheduledJobs = new ScheduledJobs();

		menuPanel = menu.getMenuComponent();
		calendarPanel = calendar.getCalendarComponent();
		jobsPanel = scheduledJobs.getScheduledJobsComponent();
		
		pane.add(menuPanel);
		pane.add(calendarPanel);
		pane.add(jobsPanel);
		
		interfaceFrame.setResizable(false);
		interfaceFrame.setVisible(true);
		
	}
	
}
