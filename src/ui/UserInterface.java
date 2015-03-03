package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UserInterface {

	public JFrame interfaceFrame;
	public Container pane;
	
	// calendarPanel Components

	public JPanel calendarPanel;
	
	boolean hasJob = false;
	private int yearBound, monthBound, dayBound, currentYear, currentMonth;
	
	public JLabel monthLabel, yearLabel, changeMonthLabel, changeYearLabel;
	public JButton prevButton, nextButton;
	public JComboBox monthCombo, yearCombo;
	public JScrollPane calendarTableScroll;
	public JTable calendarTable;
	public DefaultTableModel modelCalendarTable;
	
	// availabilityListPanel Components
	
	public JPanel availabilityListPanel;
	
	public JPanel menuBar;
	public JLabel menuLabel;
	public JComboBox menuDropDown;
	public JScrollPane listsScrollPane;
	public JButton unscheduledJobsButton, availableEngineersButton, availableEquipmentButton;
	public JList unscheduledJobList, availableEngineerList, availableEquipmentList;
	
	// engineerListPanel Components
	
	public JPanel viewEngineersPanel;
	
	public JScrollPane engineerListPanel;
	
	// jobOrderListPanel Components

	public JPanel viewJobOrdersPanel;
	
	public JPanel jobOrderListPanel;
	
	// equipmentListPanel Components
	
	public JPanel viewEquipmentPanel;
	
	public JPanel equipmentListPanel;
	
	// jobInfoPanel Components

	public JPanel jobInfoPanel;
	
	
	public UserInterface() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		interfaceFrame = new JFrame ("BEMC Smart Scheduler");
		interfaceFrame.setSize(1000, 800);
		pane = interfaceFrame.getContentPane();
		pane.setLayout(null);
		interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setCalendarPanel();
		setAvailabilityListPanel();
		// setEngineerPanel(); 
		// setEngineerPanel();
		// setJobInfoPanel();
		
		
		
		pane.add(calendarPanel);
		pane.add(availabilityListPanel);
		
		interfaceFrame.setResizable(false);
		interfaceFrame.setVisible(true);
		
	}

	public void setAvailabilityListPanel() {
		menuBar = new JPanel(new FlowLayout());
		menuBar.setBounds(0, 0, 360, 40);
		menuLabel = new JLabel("Menu: ");
		menuDropDown = new JComboBox();
		listsScrollPane = new JScrollPane();
		listsScrollPane.setLayout(new BoxLayout(listsScrollPane, BoxLayout.Y_AXIS));
		listsScrollPane.setBounds(0, 43, 360, 700);
		unscheduledJobsButton = new JButton("Unscheduled Jobs");
		availableEngineersButton = new JButton("Available Engineers");
		availableEquipmentButton = new JButton("Available Equipment");
		unscheduledJobList = new JList();
		availableEngineerList = new JList();
		availableEquipmentList = new JList();

		availabilityListPanel = new JPanel(null);
		availabilityListPanel.setSize(360, 750);
		availabilityListPanel.setLocation(620, 0);
		availabilityListPanel.setLayout(null);
		
		availabilityListPanel.add(menuBar);
		availabilityListPanel.add(listsScrollPane);
		menuBar.add(menuLabel);
		menuBar.add(menuDropDown);
		listsScrollPane.add(unscheduledJobsButton);
		listsScrollPane.add(unscheduledJobList);
		listsScrollPane.add(availableEngineersButton);
		listsScrollPane.add(availableEngineerList);
		listsScrollPane.add(availableEquipmentButton);
		listsScrollPane.add(availableEquipmentList);
		
		unscheduledJobsButton.setSize(100, 50);
		availableEngineersButton.setSize(100, 50);
		availableEquipmentButton.setSize(100, 50);
	}

	public void refreshLists() {
		
	}
	
	public void setCalendarPanel() {
		
		monthLabel = new JLabel();
		yearLabel = new JLabel();
		changeMonthLabel = new JLabel("Change month: ");
		changeYearLabel = new JLabel("Change year: ");
		monthCombo = new JComboBox();
		yearCombo = new JComboBox();
		prevButton = new JButton("<<");
		nextButton = new JButton(">>");
		modelCalendarTable = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		calendarTable = new JTable(modelCalendarTable);
		calendarTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int column = calendarTable.getSelectedColumn();
				int row = calendarTable.getSelectedRow();
				modelCalendarTable.getValueAt(row, column);
				if(hasJob) {
					// open window
				}
			}
		});
		
		calendarTableScroll = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		prevButton.addActionListener(new prevButtonAction());
		nextButton.addActionListener(new nextButtonAction());
		yearCombo.addActionListener(new yearComboAction());
		monthCombo.addActionListener(new monthComboAction());
		
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(changeMonthLabel);
		calendarPanel.add(changeYearLabel);
		calendarPanel.add(yearCombo);
		calendarPanel.add(monthCombo);
		calendarPanel.add(prevButton);
		calendarPanel.add(nextButton);
		calendarPanel.add(calendarTableScroll);
		
		calendarPanel.setBounds(20, 0, 600, 750);
		monthLabel.setBounds(250-monthLabel.getPreferredSize().width/2, 25, 200, 50);
		yearLabel.setBounds(monthLabel.getWidth()+monthLabel.getX()-120, 25, 200, 50);
		changeMonthLabel.setBounds(300, 660, 150, 50);
		changeYearLabel.setBounds(300, 600, 150, 50);
		yearCombo.setBounds(400, 610, 160, 40);
		monthCombo.setBounds(400, 670, 160, 40);
		prevButton.setBounds(0, 20, 100, 50);
		nextButton.setBounds(500, 20, 100, 50);
		calendarTableScroll.setBounds(0, 100, 600, 490);;
		
		GregorianCalendar cal = new GregorianCalendar();
		dayBound = cal.get(GregorianCalendar.DAY_OF_MONTH);
		monthBound = cal.get(GregorianCalendar.MONTH);
		yearBound = cal.get(GregorianCalendar.YEAR);
		currentMonth = monthBound; 
		currentYear = yearBound;
		
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
		for (int i=0; i<7; i++){
			modelCalendarTable.addColumn(headers[i]);
		}
		
		calendarTable.getParent().setBackground(calendarTable.getBackground()); //Set background
		calendarTable.getTableHeader().setResizingAllowed(false);
		calendarTable.getTableHeader().setReorderingAllowed(false);
		
		calendarTable.setColumnSelectionAllowed(true);
		calendarTable.setRowSelectionAllowed(true);
		calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		calendarTable.setRowHeight(76);
		modelCalendarTable.setColumnCount(7);
		modelCalendarTable.setRowCount(6);
		
		for (int i = yearBound-100; i <= yearBound+100; i++)
        {
			yearCombo.addItem(String.valueOf(i));
        }
		
		String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		for(int i = 0; i < 12; i++) {
			monthCombo.addItem(months[i]);
		}
		monthCombo.setSelectedIndex(monthBound);
		
		refreshCalendar(monthBound, yearBound);
		
	}
	
	
	public void refreshCalendar(int month, int year) {
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som, i, j;
			
		prevButton.setEnabled(true);
		nextButton.setEnabled(true);
		if (month == 0 && year <= yearBound-10)
			prevButton.setEnabled(false);
		if (month == 11 && year >= yearBound+100)
	    	nextButton.setEnabled(false);
	            
		monthLabel.setText(months[month]);
		yearLabel.setText(""+year);
		monthLabel.setBounds(250-monthLabel.getPreferredSize().width/2, 25, 200, 50);
		yearLabel.setBounds(monthLabel.getWidth()+monthLabel.getX()-120, 25, 200, 50);
	            
		yearCombo.setSelectedItem(""+year);
		monthCombo.setSelectedItem(""+month);
		
		for (i = 0; i < 6; i++)
			for (j = 0; j < 7; j++)
				modelCalendarTable.setValueAt(null, i, j);
		
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		for (i = 1; i <= nod; i++) {
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			modelCalendarTable.setValueAt(i, row, column);
		}
		
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer());
	}

	class prevButtonAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (currentMonth == 0) {
				currentMonth = 11;
				currentYear -= 1;
			}
			else {
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	
	class nextButtonAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (currentMonth == 11) {
				currentMonth = 0;
				currentYear += 1;
			}
			else {
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear);
		}
	}
	
	class yearComboAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (yearCombo.getSelectedItem() != null) {
				String b = yearCombo.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear);
			}
		}
	}
	
	class monthComboAction implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			if (monthCombo.getSelectedItem() != null) {
				String b = monthCombo.getSelectedItem().toString();
				switch(b) {
					case "January": currentMonth = 0; break;
					case "February": currentMonth = 1; break;
					case "March": currentMonth = 2; break;
					case "April": currentMonth = 3; break;
					case "May": currentMonth = 4; break;
					case "June": currentMonth = 5; break;
					case "July": currentMonth = 6; break;
					case "August": currentMonth = 7; break;
					case "September": currentMonth = 8; break;
					case "October": currentMonth = 9; break;
					case "November": currentMonth = 10; break;
					case "Devember": currentMonth = 11; break;
				}
				refreshCalendar(currentMonth, currentYear);
			}
		}
	}
}
