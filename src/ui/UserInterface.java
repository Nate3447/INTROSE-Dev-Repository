package ui;

import classes.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class UserInterface {
	
	private Database database;
	
	public final static String AVAILABILITY_LIST = "View Availability Lists";
	public final static String ENGINEERS = "View Engineers";
	public final static String JOB_ORDERS = "View Job Orders";
	public final static String EQUIPMENT = "View Equipment";
	public final static String ADD_ENGINEER = "Add Engineer";
	public final static String ADD_EQUIPMENT = "Add Equipment";
	public final static String ADD_JOB = "Add Job Order";

	public JFrame interfaceFrame;
	public Container pane;
	public JPanel menuBar;
	public JPanel listCardsPanel;
	public JScrollPane availabilityListPanel;	
	public JScrollPane viewEngineersPanel;
	public JScrollPane viewJobOrdersPanel;
	public JScrollPane viewEquipmentPanel;
	public JPanel jobInfoPanel;
	public JPanel addEngineerPanel;
	public JPanel addEquipmentPanel;
	public JPanel addJobOrderPanel;
	
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
	
	
	public UserInterface(Database database) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		this.database = database;
		
		interfaceFrame = new JFrame ("BEMC Smart Scheduler");
		interfaceFrame.setSize(1020, 800);
		pane = interfaceFrame.getContentPane();
		pane.setLayout(null);
		interfaceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		setCalendarPanel();
		setMenuBar();
		setAvailabilityListPanel();
		setViewEngineersPanel();
		setViewEquipmentPanel();
		setViewJobOrdersPanel();
		setAddEngineerPanel();
		setAddEquipmentPanel();
		setAddJobOrderPanel();
		setListCardsPanel();
		
		pane.add(calendarPanel);
		pane.add(menuBar);
		pane.add(listCardsPanel);
		
		interfaceFrame.setResizable(false);
		interfaceFrame.setVisible(true);
		
	}
	
	public void setListCardsPanel() {
		
		listCardsPanel = new JPanel(new CardLayout());
		listCardsPanel.add(availabilityListPanel, AVAILABILITY_LIST);
		listCardsPanel.add(viewEngineersPanel, ENGINEERS);
		listCardsPanel.add(viewEquipmentPanel, EQUIPMENT);
		listCardsPanel.add(viewJobOrdersPanel, JOB_ORDERS);
		listCardsPanel.add(addEngineerPanel, ADD_ENGINEER);
		listCardsPanel.add(addEquipmentPanel, ADD_EQUIPMENT);
		listCardsPanel.add(addJobOrderPanel, ADD_JOB);
		
		listCardsPanel.setBounds(640, 40, 360, 710);
		
	}
	
	public void setAddEngineerPanel() {
		JLabel nameLabel, specialtiesLabel;
		JTextField nameInput, specialtiesInput;
		JButton addButton, cancelButton;
		
		
	}
	
	public void setAddEquipmentPanel() {
		JLabel nameLabel, idNumberLabel, locationLabel, borrowerNameLabel;
		JLabel borrowDateLabel, borrowMonthLabel, borrowDayLabel, borrowYearLabel;
		JLabel returnDateLabel, returnMonthLabel, returnDayLabel, returnYearLabel;
		JLabel calibrationDateLabel, calibrationMonthLabel, calibrationDayLabel, calibrationYearLabel;
		JTextField nameInput, idNumberInput, locationInput, borrowerNameInput; 
		JTextField borrowMonthInput, borrowDayInput, borrowYearInput;
		JTextField returnMonthInput, returnDayInput, returnYearInput; 
		JTextField calibrationMonthInput, calibrationDayInput, calibrationYearInput;
		JButton addButton, cancelButton;
		
	}
	
	public void setAddJobOrderPanel() {
		JLabel orderNoLabel, referenceNoLabel, hospitalLabel, addressLabel, contactLabel, contactNoLabel;
		JLabel jobMonthLabel, jobDayLabel, jobYearLabel;
		JLabel completionMonthLabel, completionDayLabel, completionYearLabel;
		
		
	}
	
	public void setMenuBar() {
		JLabel menuLabel;
		JComboBox menuDropDown;
		
		menuBar = new JPanel(null);
		menuBar.setBounds(640, 0, 360, 40);
		menuLabel = new JLabel("Menu: ");
		menuLabel.setBounds(10, 10, 40, 20);
		menuLabel.setHorizontalAlignment(SwingConstants.LEFT);
		String[] comboBoxItems = { AVAILABILITY_LIST, ENGINEERS, EQUIPMENT, JOB_ORDERS };
		menuDropDown = new JComboBox(comboBoxItems);
		menuDropDown.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				CardLayout c = (CardLayout)(listCardsPanel.getLayout());
				c.show(listCardsPanel, (String)e.getItem());
			}
		});
		menuDropDown.setBounds(50, 10, 150, 20);

		menuBar.add(menuLabel);
		menuBar.add(menuDropDown);
	}
	
	// AVAILABILITY LISTS PANEL COMPONENTS

	public JList unscheduledJobList, availableEngineerList, availableEquipmentList;
	
	public void setAvailabilityListPanel() {
		
		JPanel listPanel;
		JButton unscheduledJobsButton, availableEngineersButton, availableEquipmentButton;
			
		listPanel = new JPanel(null);

		unscheduledJobList = new JList();
		availableEngineerList = new JList();
		availableEquipmentList = new JList();
		
		unscheduledJobsButton = new JButton("Unscheduled Jobs");
		unscheduledJobsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshAvailabilityList();
				if(unscheduledJobList.isVisible()) {
					unscheduledJobList.setVisible(false);
				} else {
					unscheduledJobList.setVisible(true);
				}
			}
		});
		unscheduledJobsButton.setHorizontalAlignment(SwingConstants.LEFT);
		availableEngineersButton = new JButton("Available Engineers");
		availableEngineersButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshAvailabilityList();
				if(availableEngineerList.isVisible()) {
					availableEngineerList.setVisible(false);
				} else {
					availableEngineerList.setVisible(true);
				}
			}
		});
		availableEngineersButton.setHorizontalAlignment(SwingConstants.LEFT);
		availableEquipmentButton = new JButton("Available Equipment");
		availableEquipmentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshAvailabilityList();
				if(availableEquipmentList.isVisible()) {
					availableEquipmentList.setVisible(false);
				} else {
					availableEquipmentList.setVisible(true);
				}
			}
		});
		availableEquipmentButton.setHorizontalAlignment(SwingConstants.LEFT);

		availabilityListPanel = new JScrollPane(listPanel);
		availabilityListPanel.setBounds(640, 40, 360, 750);
		
		listPanel.add(unscheduledJobsButton);
		listPanel.add(unscheduledJobList);
		listPanel.add(availableEngineersButton);
		listPanel.add(availableEngineerList);
		listPanel.add(availableEquipmentButton);
		listPanel.add(availableEquipmentList);
		
		refreshLists(listPanel, unscheduledJobsButton, availableEngineersButton, availableEquipmentButton, 
				unscheduledJobList, availableEngineerList, availableEquipmentList);
		
	}

	public void refreshAvailabilityList() {
		
	}

	public void refreshLists(JPanel listPanel, JButton unscheduledJobsButton, JButton availableEngineersButton, JButton availableEquipmentButton, 
			JList unscheduledJobList, JList availableEngineerList, JList availableEquipmentList) {
		unscheduledJobsButton.setBounds(5, 0, 200, 30);
		availableEngineersButton.setBounds(5, unscheduledJobsButton.getHeight() + unscheduledJobList.getHeight(), 200, 30);
		availableEquipmentButton.setBounds(5, unscheduledJobsButton.getHeight() + unscheduledJobList.getHeight() 
				+ availableEngineersButton.getHeight() + availableEngineerList.getHeight(), 200, 30);
		listPanel.setBounds(5, 5, 350, unscheduledJobsButton.getHeight() + unscheduledJobList.getHeight() 
				+ availableEngineersButton.getHeight() + availableEngineerList.getHeight() 
				+ availableEquipmentButton.getHeight() + availableEquipmentList.getHeight());
	}
	
	/*
	
	public ArrayList<JobOrder> getUnscheduledJobOrders() {
		ArrayList<JobOrder> jobOrders;
		
		
		
		return jobOrders;
	}
	
	public ArrayList<Engineer> getAvailableEngineers() {
		ArrayList<Engineer> engineers;
		
		
		
		return engineers;
	}
	
	public ArrayList<Equipment> getAvailableEquipment() {
		ArrayList<Equipment> equipment;
		
		
		
		return equipment;
	}
	
	*/
	
	// VIEW ENGINEERS PANEL COMPONENTS
	
	public void setViewEngineersPanel() {
		int y = 5;
		JPanel listPanel = new JPanel();
		ArrayList<Engineer> engineers = getEngineers();
		EngineerPanel engineerPanel = new EngineerPanel();
		int i;
		
		for(i=0; i<engineers.size(); i++) {
			engineerPanel = new EngineerPanel(engineers.get(i));
			listPanel.add(engineerPanel);
			engineerPanel.setLocation(5, y);
			y += engineerPanel.getHeight();
		}
		if(i > 0) {
			listPanel.setSize(360, engineerPanel.getHeight() * engineers.size() + 10);
		}
		
		viewEngineersPanel = new JScrollPane(listPanel);
		viewEngineersPanel.setBounds(640, 40, 360, 750);
	}
	
	public ArrayList<Engineer> getEngineers() {
		ArrayList<Engineer> engineers;
		
		engineers = database.getEngineerList();
		
		return engineers;
	}
	
	// VIEW EQUIPMENT PANEL METHODS
	
	public void setViewEquipmentPanel() {
		int y = 5;
		JPanel listPanel = new JPanel();
		ArrayList<Equipment> equipment = getEquipment();
		EquipmentPanel equipmentPanel = new EquipmentPanel();
		int i;
		
		for(i=0; i<equipment.size(); i++) {
			equipmentPanel = new EquipmentPanel(equipment.get(i));
			listPanel.add(equipmentPanel);
			equipmentPanel.setLocation(5, y);
			y += equipmentPanel.getHeight();
		}
		if(i > 0) {
			listPanel.setSize(360, equipmentPanel.getHeight() * equipment.size() + 10);
		}
		
		viewEquipmentPanel = new JScrollPane(listPanel);
		viewEquipmentPanel.setBounds(640, 40, 360, 750);
	}
	
	public ArrayList<Equipment> getEquipment() {
		ArrayList<Equipment> equipment;
		
		equipment = database.getEquipmentList();
		
		return equipment;
	}
	
	// JOB ORDERS PANEL METHODS
	
	public void setViewJobOrdersPanel() {
		int y = 5;
		JPanel listPanel = new JPanel();
		ArrayList<JobOrder> jobOrders = getJobOrders();
		JobOrderPanel jobOrderPanel = new JobOrderPanel();
		int i;
		
		for(i=0; i<jobOrders.size(); i++) {
			jobOrderPanel = new JobOrderPanel(jobOrders.get(i));
			listPanel.add(jobOrderPanel);
			jobOrderPanel.setLocation(5, y);
			y += jobOrderPanel.getHeight();
		}
		if(i > 0) {
			listPanel.setSize(360,  jobOrderPanel.getHeight() * jobOrders.size() + 10);
		}
		
		viewJobOrdersPanel = new JScrollPane(listPanel);
		viewJobOrdersPanel.setBounds(640, 40, 360, 750);
	}
	
	public ArrayList<JobOrder> getJobOrders() {
		ArrayList<JobOrder> jobOrders;
		
		jobOrders = database.getJobOrderList();
		
		return jobOrders;
	}
	
	// CALENDAR PANEL METHODS
	
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
