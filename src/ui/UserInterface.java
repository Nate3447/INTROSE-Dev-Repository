package ui;

import classes.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;


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
		listCardsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		listCardsPanel.add(availabilityListPanel, AVAILABILITY_LIST);
		listCardsPanel.add(viewEngineersPanel, ENGINEERS);
		listCardsPanel.add(addEngineerPanel, ADD_ENGINEER);
		listCardsPanel.add(viewEquipmentPanel, EQUIPMENT);
		listCardsPanel.add(addEquipmentPanel, ADD_EQUIPMENT);
		listCardsPanel.add(viewJobOrdersPanel, JOB_ORDERS);
		listCardsPanel.add(addJobOrderPanel, ADD_JOB);
		listCardsPanel.setBounds(640, 40, 360, 710);
		
	}
	
	public JTextField engineerNameInput, specialtiesInput;
	public JLabel engineerStatusLabel = new JLabel("");
	
	public void setAddEngineerPanel() {
		JLabel nameLabel = new JLabel("Name: "); 
		JLabel specialtiesLabel = new JLabel("Specialties: ");
		engineerNameInput = new JTextField("");
		specialtiesInput = new JTextField("");
		JButton addButton = new JButton("Add");
		JButton clearButton = new JButton("Clear");
		addEngineerPanel = new JPanel(null);
		
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Engineer newEngineer = new Engineer(engineerNameInput.getText(), specialtiesInput.getText());
				if(newEngineer.isValid()) {
					database.addEngineer(newEngineer);
					engineerStatusLabel.setText("Add Successful!");
					engineerNameInput.setText("");
					specialtiesInput.setText("");
				}
				else {
					engineerStatusLabel.setText("Add Failed. Empty Fields.");
				}
			}
		});
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				engineerStatusLabel.setText("");
				engineerNameInput.setText("");
				specialtiesInput.setText("");
			}
		});
		
		nameLabel.setBounds(40, 20, 60, 30);
		specialtiesLabel.setBounds(40, 45, 60, 30);
		engineerNameInput.setBounds(120, 25, 180, 20);
		specialtiesInput.setBounds(120, 50, 180, 20);
		engineerStatusLabel.setBounds(100, 200, 200, 40);
		addButton.setBounds(50, 300, 70, 30);
		clearButton.setBounds(200, 300, 70, 30);
		addEngineerPanel.setBounds(640, 40, 360, 710);
		
		addEngineerPanel.add(nameLabel);
		addEngineerPanel.add(specialtiesLabel);
		addEngineerPanel.add(engineerNameInput);
		addEngineerPanel.add(specialtiesInput);
		addEngineerPanel.add(addButton);
		addEngineerPanel.add(clearButton);
		addEngineerPanel.add(engineerStatusLabel);
	}
	
	public JTextField equipmentNameInput, idNumberInput, locationInput, borrowerNameInput, 
		borrowMonthInput, borrowDayInput, borrowYearInput, returnMonthInput, returnDayInput,
		returnYearInput, calibrationMonthInput, calibrationDayInput, calibrationYearInput;
	public JLabel equipmentStatusLabel;
	
	public void setAddEquipmentPanel() {
		JLabel nameLabel = new JLabel("Name: ");
		JLabel idNumberLabel = new JLabel("ID No.: ");
		JLabel locationLabel = new JLabel("Location");
		JLabel borrowerNameLabel = new JLabel("Borrower Name: ");
		JLabel borrowDateLabel = new JLabel("Borrow Date (mm/dd/yy): ");
		JLabel returnDateLabel = new JLabel("Return Date (mm/dd/yy): ");
		JLabel calibrationDateLabel = new JLabel("Calibration Date (mm/dd/yy): ");
		equipmentStatusLabel = new JLabel("");
		equipmentNameInput = new JTextField();
		idNumberInput = new JTextField();
		locationInput = new JTextField();
		borrowerNameInput = new JTextField(); 
		borrowMonthInput = new JTextField();
		borrowDayInput = new JTextField();
		borrowYearInput = new JTextField();
		returnMonthInput = new JTextField();
		returnDayInput = new JTextField();
		returnYearInput = new JTextField(); 
		calibrationMonthInput = new JTextField();
		calibrationDayInput = new JTextField();
		calibrationYearInput = new JTextField();
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		addEquipmentPanel = new JPanel(null);
		
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Equipment newEquipment = new Equipment(equipmentNameInput.getText(), idNumberInput.getText(), 
							locationInput.getText(), Integer.parseInt(borrowYearInput.getText()), Integer.parseInt(borrowMonthInput.getText()), 
							Integer.parseInt(borrowDayInput.getText()), Integer.parseInt(returnYearInput.getText()), Integer.parseInt(returnMonthInput.getText()), 
							Integer.parseInt(returnDayInput.getText()), Integer.parseInt(calibrationYearInput.getText()), Integer.parseInt(calibrationMonthInput.getText()), 
							Integer.parseInt(calibrationDayInput.getText()), borrowerNameInput.getText());
					database.addEquipment(newEquipment);
					if(newEquipment.isValid()) {
						equipmentStatusLabel.setText("Add Successful!");
						equipmentNameInput.setText("");
						idNumberInput.setText("");
						locationInput.setText("");
						borrowerNameInput.setText("");
						borrowMonthInput.setText("");
						borrowDayInput.setText("");
						borrowYearInput.setText("");
						returnMonthInput.setText("");
						returnDayInput.setText("");
						returnYearInput.setText("");
						calibrationMonthInput.setText("");
						calibrationDayInput.setText("");
						calibrationYearInput.setText("");
					}
					else {
						equipmentStatusLabel.setText("Add Failed. Empty Fields");
					}
				} catch(NumberFormatException a) {
					equipmentStatusLabel.setText("Add Failed. Invalid Input.");;
				}
			}
		});
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				equipmentNameInput.setText("");
				idNumberInput.setText("");
				locationInput.setText("");
				borrowerNameInput.setText("");
				borrowMonthInput.setText("");
				borrowDayInput.setText("");
				borrowYearInput.setText("");
				returnMonthInput.setText("");
				returnDayInput.setText("");
				returnYearInput.setText("");
				calibrationMonthInput.setText("");
				calibrationDayInput.setText("");
				calibrationYearInput.setText("");
			}
		});
		
		nameLabel.setBounds(20, 20, 100, 30);
		idNumberLabel.setBounds(20, 50, 100, 30);
		locationLabel.setBounds(20, 80, 100, 30);
		borrowerNameLabel.setBounds(20, 110, 100, 30);
		borrowDateLabel.setBounds(20, 140, 150, 30);
		returnDateLabel.setBounds(20, 170, 150, 30);
		calibrationDateLabel.setBounds(20, 200, 150, 30);
		equipmentStatusLabel.setBounds(110, 250, 200, 40);
		equipmentNameInput.setBounds(120, 25, 220, 20);
		idNumberInput.setBounds(120, 55, 220, 20);
		locationInput.setBounds(120, 85, 220, 20);
		borrowerNameInput.setBounds(120, 115, 220, 20);
		borrowMonthInput.setBounds(180, 145, 30, 20);
		borrowDayInput.setBounds(220, 145, 30, 20);
		borrowYearInput.setBounds(260, 145, 30, 20);
		returnMonthInput.setBounds(180, 175, 30, 20);
		returnDayInput.setBounds(220, 175, 30, 20);
		returnYearInput.setBounds(260, 175, 30, 20);
		calibrationMonthInput.setBounds(180, 205, 30, 20);
		calibrationDayInput.setBounds(220, 205, 30, 20);
		calibrationYearInput.setBounds(260, 205, 30, 20);
		addButton.setBounds(100, 300, 70, 30);
		cancelButton.setBounds(200, 300, 70, 30);
		addEquipmentPanel.setBounds(640, 40, 360, 710);
		
		addEquipmentPanel.add(nameLabel);
		addEquipmentPanel.add(idNumberLabel);
		addEquipmentPanel.add(locationLabel);
		addEquipmentPanel.add(borrowerNameLabel);
		addEquipmentPanel.add(borrowDateLabel);
		addEquipmentPanel.add(returnDateLabel);
		addEquipmentPanel.add(calibrationDateLabel);
		addEquipmentPanel.add(equipmentStatusLabel);
		addEquipmentPanel.add(equipmentNameInput);
		addEquipmentPanel.add(idNumberInput);
		addEquipmentPanel.add(locationInput);
		addEquipmentPanel.add(borrowerNameInput);
		addEquipmentPanel.add(borrowMonthInput);
		addEquipmentPanel.add(borrowDayInput);
		addEquipmentPanel.add(borrowYearInput);
		addEquipmentPanel.add(returnMonthInput);
		addEquipmentPanel.add(returnDayInput);
		addEquipmentPanel.add(returnYearInput);
		addEquipmentPanel.add(calibrationMonthInput);
		addEquipmentPanel.add(calibrationDayInput);
		addEquipmentPanel.add(calibrationYearInput);
		addEquipmentPanel.add(addButton);
		addEquipmentPanel.add(cancelButton);
		
		
	}
	
	public JTextField orderNoInput, referenceNoInput, hospitalInput, addressInput, contactInput,
		contactNoInput, jobMonthInput, jobDayInput, jobYearInput, completionMonthInput, completionDayInput,
		completionYearInput;
	
	public JLabel jobStatusLabel;
	
	public void setAddJobOrderPanel() {
		JLabel orderNoLabel = new JLabel("Order No.: ");
		JLabel referenceNoLabel = new JLabel("Reference No.: ");
		JLabel hospitalLabel = new JLabel("Hospital: ");
		JLabel addressLabel = new JLabel("Address");
		JLabel contactLabel = new JLabel("Contact Person: ");
		JLabel contactNoLabel = new JLabel("Contact No.: ");
		JLabel jobDateLabel = new JLabel("Job Date (mm/dd/yy): ");
		JLabel completionDateLabel = new JLabel("Completion Date (mm/dd/yy): ");
		jobStatusLabel = new JLabel("");
		orderNoInput = new JTextField();
		referenceNoInput = new JTextField();
		hospitalInput = new JTextField();
		addressInput = new JTextField();
		contactInput = new JTextField();
		contactNoInput = new JTextField();
		jobMonthInput = new JTextField();
		jobDayInput = new JTextField();
		jobYearInput = new JTextField();
		completionMonthInput = new JTextField();
		completionDayInput = new JTextField();
		completionYearInput = new JTextField();
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		addJobOrderPanel = new JPanel(null);
		
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JobOrder newJob = new JobOrder(orderNoInput.getText(), referenceNoInput.getText(),
							Integer.parseInt(jobYearInput.getText()), Integer.parseInt(jobMonthInput.getText()), 
							Integer.parseInt(jobDayInput.getText()), Integer.parseInt(completionYearInput.getText()), 
							Integer.parseInt(completionMonthInput.getText()), Integer.parseInt(completionDayInput.getText()), 
							hospitalInput.getText(), addressInput.getText(), contactInput.getText(), contactNoInput.getText());
					if(newJob.isValid()) {
						database.addJobOrder(newJob);
						jobStatusLabel.setText("Add Successful!");
						orderNoInput.setText("");
						referenceNoInput.setText("");
						hospitalInput.setText("");
						addressInput.setText("");
						contactInput.setText("");
						jobMonthInput.setText("");
						jobDayInput.setText("");
						jobYearInput.setText("");
						completionMonthInput.setText("");
						completionDayInput.setText("");
						completionYearInput.setText("");
					}
					else {
						jobStatusLabel.setText("Add Failed. Empty Fields.");
					}
				} catch(NumberFormatException a) {
					jobStatusLabel.setText("Add Failed. Invalid Input.");
				}
			}
		});
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderNoInput.setText("");
				referenceNoInput.setText("");
				hospitalInput.setText("");
				addressInput.setText("");
				contactInput.setText("");
				jobMonthInput.setText("");
				jobDayInput.setText("");
				jobYearInput.setText("");
				completionMonthInput.setText("");
				completionDayInput.setText("");
				completionYearInput.setText("");
			}
		});
		
		orderNoLabel.setBounds(20, 20, 100, 30);
		referenceNoLabel.setBounds(20, 50, 100, 30);
		hospitalLabel.setBounds(20, 80, 100, 30);
		addressLabel.setBounds(20, 110, 100, 30);
		contactLabel.setBounds(20, 140, 100, 30);
		contactNoLabel.setBounds(20, 170, 100, 30);
		jobDateLabel.setBounds(20, 200, 150, 30);
		completionDateLabel.setBounds(20, 230, 150, 30);
		jobStatusLabel.setBounds(110, 285, 200, 40);
		orderNoInput.setBounds(120, 25, 220, 20);
		referenceNoInput.setBounds(120, 55, 220, 20);
		hospitalInput.setBounds(120, 85, 220, 20);
		addressInput.setBounds(120, 115, 220, 20);
		contactInput.setBounds(120, 145, 220, 20);
		contactNoInput.setBounds(120, 175, 220, 20);
		jobMonthInput.setBounds(180, 205, 30, 20);
		jobDayInput.setBounds(220, 205, 30, 20);
		jobYearInput.setBounds(260, 205, 30, 20);
		completionMonthInput.setBounds(180, 235, 30, 20);
		completionDayInput.setBounds(220, 235, 30, 20);
		completionYearInput.setBounds(260, 235, 30, 20);
		addButton.setBounds(100, 335, 70, 30);
		cancelButton.setBounds(200, 335, 70, 30);
		addJobOrderPanel.setBounds(640, 40, 360, 710);
		
		addJobOrderPanel.add(orderNoLabel);
		addJobOrderPanel.add(referenceNoLabel);
		addJobOrderPanel.add(hospitalLabel);
		addJobOrderPanel.add(addressLabel);
		addJobOrderPanel.add(contactLabel);
		addJobOrderPanel.add(contactNoLabel);
		addJobOrderPanel.add(jobDateLabel);
		addJobOrderPanel.add(completionDateLabel);
		addJobOrderPanel.add(jobStatusLabel);
		addJobOrderPanel.add(orderNoInput);
		addJobOrderPanel.add(referenceNoInput);
		addJobOrderPanel.add(hospitalInput);
		addJobOrderPanel.add(addressInput);
		addJobOrderPanel.add(contactInput);
		addJobOrderPanel.add(contactNoInput);
		addJobOrderPanel.add(jobMonthInput);
		addJobOrderPanel.add(jobDayInput);
		addJobOrderPanel.add(jobYearInput);
		addJobOrderPanel.add(completionMonthInput);
		addJobOrderPanel.add(completionDayInput);
		addJobOrderPanel.add(completionYearInput);
		addJobOrderPanel.add(addButton);
		addJobOrderPanel.add(cancelButton);
	}

	public void setJobInfoPanel(JobOrder jobOrder) {
		
		JLabel orderNoLabel = new JLabel("Order No.: " + jobOrder.getOrderNo());
		JLabel referenceNoLabel = new JLabel("Reference No. : " + jobOrder.getReferenceNo());
		JLabel hospitalLabel = new JLabel("Hospital: " + jobOrder.getHospital());
		JLabel addressLabel = new JLabel("Address: " + jobOrder.getAddress());
		JLabel contactLabel = new JLabel("Contact Person: " + jobOrder.getContactPerson());
		JLabel contactNoLabel = new JLabel("Contact No.: " + jobOrder.getContactNo());
		JLabel jobDateLabel = new JLabel("Job Date: " + jobOrder.getJobDate());
		JLabel completionDateLabel = new JLabel("Completion Date: " + jobOrder.getCompletionDate());
		JList assignedEngineers = new JList();
		JList assignedEquipment = new JList();
		jobInfoPanel = new JPanel(null);
		JButton jobEditButton = new JButton("Edit");
		
		orderNoLabel.setBounds(30, 20, 250, 30);
		referenceNoLabel.setBounds(320, 20, 250, 30);
		hospitalLabel.setBounds(30, 60, 400, 30);
		addressLabel.setBounds(30, 100, 400, 30);
		contactLabel.setBounds(30, 140, 250, 30);
		contactNoLabel.setBounds(320, 140, 250, 30);
		jobDateLabel.setBounds(30, 180, 250, 30);
		completionDateLabel.setBounds(30, 220, 250, 30);
		assignedEngineers.setBounds(30, 270, 260, 470);
		assignedEquipment.setBounds(310, 270, 260, 470);
		jobInfoPanel.setBounds(20, 0, 600, 750);
		
		jobInfoPanel.add(orderNoLabel);
		jobInfoPanel.add(referenceNoLabel);
		jobInfoPanel.add(hospitalLabel);
		jobInfoPanel.add(addressLabel);
		jobInfoPanel.add(contactLabel);
		jobInfoPanel.add(contactNoLabel);
		jobInfoPanel.add(jobDateLabel);
		jobInfoPanel.add(completionDateLabel);
		jobInfoPanel.add(assignedEngineers);
		jobInfoPanel.add(assignedEquipment);
	}
	
	public void setMenuBar() {
		JLabel menuLabel;
		JComboBox menuDropDown;
		
		menuBar = new JPanel(null);
		menuBar.setBounds(640, 0, 360, 40);
		menuLabel = new JLabel("Menu: ");
		menuLabel.setBounds(10, 10, 40, 20);
		menuLabel.setHorizontalAlignment(SwingConstants.LEFT);
		String[] comboBoxItems = { AVAILABILITY_LIST, ENGINEERS, ADD_ENGINEER, EQUIPMENT, ADD_EQUIPMENT, JOB_ORDERS, ADD_JOB };
		menuDropDown = new JComboBox(comboBoxItems);
		menuDropDown.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				switch((String)e.getItem()) {
					case AVAILABILITY_LIST: setAvailabilityListPanel(); 
						listCardsPanel.remove(availabilityListPanel);
						listCardsPanel.add(availabilityListPanel, AVAILABILITY_LIST);
						break;
					case ENGINEERS: setViewEngineersPanel(); 
						listCardsPanel.remove(viewEngineersPanel);
						listCardsPanel.add(viewEngineersPanel, ENGINEERS);
						break;
					case ADD_ENGINEER: setAddEngineerPanel(); 
						listCardsPanel.remove(addEngineerPanel);
						listCardsPanel.add(addEngineerPanel, ADD_ENGINEER);
						break;
					case EQUIPMENT: setViewEquipmentPanel(); 
						listCardsPanel.remove(viewEquipmentPanel);
						listCardsPanel.add(viewEquipmentPanel, EQUIPMENT);
						break;
					case ADD_EQUIPMENT: setAddEquipmentPanel(); 
						listCardsPanel.remove(addEquipmentPanel);
						listCardsPanel.add(addEquipmentPanel, ADD_EQUIPMENT);
						break;
					case JOB_ORDERS: setViewJobOrdersPanel(); 
						listCardsPanel.remove(viewJobOrdersPanel);
						listCardsPanel.add(viewJobOrdersPanel, JOB_ORDERS);
						break;
					case ADD_JOB: setAddJobOrderPanel(); 
						listCardsPanel.remove(addJobOrderPanel);
						listCardsPanel.add(addJobOrderPanel, ADD_JOB);
						break;
				}
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

		availabilityListPanel = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		availabilityListPanel.setBounds(640, 40, 360, 710);
		
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
		JPanel listPanel = new JPanel(new GridLayout(0, 1));
		ArrayList<Engineer> engineers = getEngineers();
		EngineerPanel engineerPanel = new EngineerPanel();
		int i;

		for(i=0; i<engineers.size(); i++) {
			engineerPanel = new EngineerPanel(engineers.get(i));
			listPanel.add(engineerPanel);
		}
		
		viewEngineersPanel = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		viewEngineersPanel.setBounds(640, 40, 360, 710);
	}
	
	public ArrayList<Engineer> getEngineers() {
		ArrayList<Engineer> engineers;
		
		engineers = database.getEngineerList();
		
		return engineers;
	}
	
	// VIEW EQUIPMENT PANEL METHODS
	
	public void setViewEquipmentPanel() {
		JPanel listPanel = new JPanel(new GridLayout(0, 1));
		ArrayList<Equipment> equipment = getEquipment();
		EquipmentPanel equipmentPanel = new EquipmentPanel();
		int i;

		for(i=0; i<equipment.size(); i++) {
			equipmentPanel = new EquipmentPanel(equipment.get(i));
			listPanel.add(equipmentPanel);
		}
		
		viewEquipmentPanel = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		viewEquipmentPanel.setBounds(640, 40, 360, 710);
	}
	
	public ArrayList<Equipment> getEquipment() {
		ArrayList<Equipment> equipment;
		
		equipment = database.getEquipmentList();
		
		return equipment;
	}
	
	// JOB ORDERS PANEL METHODS
	
	public void setViewJobOrdersPanel() {
		JPanel listPanel = new JPanel(new GridLayout(0, 1));
		ArrayList<JobOrder> jobOrders = getJobOrders();
		JobOrderPanel jobOrderPanel = new JobOrderPanel();
		int i;

		listPanel.setSize(360,  jobOrderPanel.getHeight() * jobOrders.size() + 10);
		for(i=0; i<jobOrders.size(); i++) {
			jobOrderPanel = new JobOrderPanel(jobOrders.get(i));
			listPanel.add(jobOrderPanel);
		}
		
		viewJobOrdersPanel = new JScrollPane(listPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		viewJobOrdersPanel.setBounds(640, 40, 360, 710);
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
		database.setJobsOnMonth(month);
		ArrayList<JobOrder> jobsOnMonth = database.getJobsOnMonth();
		
			
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
		
		ArrayList<Integer> jobDays = new ArrayList<Integer>();
		if(!(jobsOnMonth.isEmpty())) {
			jobDays.add(jobsOnMonth.get(0).getJobDate().getDay());
			for(i = 1; i < jobsOnMonth.size(); i++) {
				if(!(jobDays.contains(jobsOnMonth.get(i).getJobDate().getDay()))) {
					jobDays.add(jobsOnMonth.get(i).getJobDate().getDay());
				}
			}
		}
		
		calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer(jobDays));
		
		// make clickable days with jobs
		
		
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
