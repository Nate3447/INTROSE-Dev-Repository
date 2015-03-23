package ui;

import classes.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
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
	public JPanel availabilityListPanel;	
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
	
	private Calendar calendar = Calendar.getInstance();
	
	
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
	
	/*---------------------------------------------------------------------------
	 ----------------------------ADD-ENGINEER------------------------------------
	 ----------------------------------------------------------------------------*/
	
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
				if(newEngineer.isValid() && database.checkDuplicateEngineer(newEngineer)) {
					database.addEngineer(newEngineer);
					engineerStatusLabel.setText("Add Successful!");
					engineerNameInput.setText("");
					specialtiesInput.setText("");
				}
				else {
					engineerStatusLabel.setText("Add Failed. Either the name already exists or invalid character type");
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
	
	public JTextField equipmentNameInput, typeInput,  
		calibrationMonthInput, calibrationDayInput, calibrationYearInput, serialNoInput;
	public JLabel equipmentStatusLabel;
	
	
	/*--------------------------------------------------------------------------------
	 ---------------------------------ADD-EQUIPMENT-----------------------------------
	 ---------------------------------------------------------------------------------*/
	
	public void setAddEquipmentPanel() {
		JLabel nameLabel = new JLabel("Name: ");
		JLabel typeLabel = new JLabel("Type: ");
		JLabel calibrationDateLabel = new JLabel("Calibration Date (mm/dd/yyyy): ");
		JLabel serialNoLabel = new JLabel("Serial Number: ");
		equipmentStatusLabel = new JLabel("");
		equipmentNameInput = new JTextField();
		typeInput = new JTextField();
		calibrationMonthInput = new JTextField();
		calibrationDayInput = new JTextField();
		calibrationYearInput = new JTextField();
		serialNoInput = new JTextField();
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		addEquipmentPanel = new JPanel(null);
		
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Equipment newEquipment = new Equipment(equipmentNameInput.getText(), 
							Integer.parseInt(typeInput.getText()), 
							Integer.parseInt(calibrationYearInput.getText()),
							Integer.parseInt(calibrationMonthInput.getText()),
							Integer.parseInt(calibrationDayInput.getText()),
							Integer.parseInt(serialNoInput.getText()));
					
					database.addEquipment(newEquipment);
					if(newEquipment.isValid()) {
						equipmentStatusLabel.setText("Add Successful!");
						equipmentNameInput.setText("");
						typeInput.setText("");
						calibrationMonthInput.setText("");
						calibrationDayInput.setText("");
						calibrationYearInput.setText("");
						serialNoInput.setText("");
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
				typeInput.setText("");
				calibrationMonthInput.setText("");
				calibrationDayInput.setText("");
				calibrationYearInput.setText("");
				serialNoInput.setText("");
			}
		});
		
		nameLabel.setBounds(20, 20, 100, 30);
		typeLabel.setBounds(20, 50, 100, 30);
		calibrationDateLabel.setBounds(20, 110, 170, 30);
		serialNoLabel.setBounds(20, 80, 100, 30);
		equipmentStatusLabel.setBounds(110, 250, 200, 40);
		equipmentNameInput.setBounds(120, 25, 220, 20);
		typeInput.setBounds(120, 55, 220, 20);
		serialNoInput.setBounds(120, 85, 220, 20);
		calibrationMonthInput.setBounds(200, 115, 30, 20);
		calibrationDayInput.setBounds(240, 115, 30, 20);
		calibrationYearInput.setBounds(280, 115, 45, 20);
		addButton.setBounds(100, 300, 70, 30);
		cancelButton.setBounds(200, 300, 70, 30);
		addEquipmentPanel.setBounds(640, 40, 360, 710);
		
		addEquipmentPanel.add(nameLabel);
		addEquipmentPanel.add(typeLabel);
		addEquipmentPanel.add(calibrationDateLabel);
		addEquipmentPanel.add(serialNoLabel);
		addEquipmentPanel.add(equipmentStatusLabel);
		addEquipmentPanel.add(equipmentNameInput);
		addEquipmentPanel.add(typeInput);
		addEquipmentPanel.add(calibrationMonthInput);
		addEquipmentPanel.add(calibrationDayInput);
		addEquipmentPanel.add(calibrationYearInput);
		addEquipmentPanel.add(serialNoInput);
		addEquipmentPanel.add(addButton);
		addEquipmentPanel.add(cancelButton);
		
	}
	
	public JTextField jobOrderNoInput, referenceNoInput, hospitalInput, addressInput, contactInput,
		contactNoInput;
	
	public JLabel jobStatusLabel;
	
	/*-----------------------------------------------------------------------------------
	 ----------------------------------ADD-JOB-ORDER-------------------------------------
	 ------------------------------------------------------------------------------------*/
	
	public void setAddJobOrderPanel() {
		JLabel jobOrderNoLabel = new JLabel("Order No.: ");
		JLabel referenceNoLabel = new JLabel("Reference No.: ");
		JLabel hospitalLabel = new JLabel("Hospital: ");
		JLabel addressLabel = new JLabel("Address");
		JLabel contactLabel = new JLabel("Contact Person: ");
		JLabel contactNoLabel = new JLabel("Contact No.: ");
		jobStatusLabel = new JLabel("");
		jobOrderNoInput = new JTextField();
		referenceNoInput = new JTextField();
		hospitalInput = new JTextField();
		addressInput = new JTextField();
		contactInput = new JTextField();
		contactNoInput = new JTextField();
		JButton addButton = new JButton("Add");
		JButton cancelButton = new JButton("Cancel");
		addJobOrderPanel = new JPanel(null);
	
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					JobOrder newJob = new JobOrder(Integer.parseInt(jobOrderNoInput.getText()), 
							Integer.parseInt(referenceNoInput.getText()),
							hospitalInput.getText(), addressInput.getText(), 
							contactInput.getText(), Integer.parseInt(contactNoInput.getText()));
					if(newJob.isValid()) {
						database.addJobOrder(newJob);
						jobStatusLabel.setText("Add Successful!");
						jobOrderNoInput.setText("");
						referenceNoInput.setText("");
						hospitalInput.setText("");
						addressInput.setText("");
						contactInput.setText("");
						contactNoInput.setText("");
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
				jobOrderNoInput.setText("");
				referenceNoInput.setText("");
				hospitalInput.setText("");
				addressInput.setText("");
				contactInput.setText("");
				contactNoInput.setText("");
			}
		});
		
		jobOrderNoLabel.setBounds(20, 20, 100, 30);
		referenceNoLabel.setBounds(20, 50, 100, 30);
		hospitalLabel.setBounds(20, 80, 100, 30);
		addressLabel.setBounds(20, 110, 100, 30);
		contactLabel.setBounds(20, 140, 100, 30);
		contactNoLabel.setBounds(20, 170, 100, 30);
		jobStatusLabel.setBounds(110, 285, 200, 40);
		jobOrderNoInput.setBounds(120, 25, 220, 20);
		referenceNoInput.setBounds(120, 55, 220, 20);
		hospitalInput.setBounds(120, 85, 220, 20);
		addressInput.setBounds(120, 115, 220, 20);
		contactInput.setBounds(120, 145, 220, 20);
		contactNoInput.setBounds(120, 175, 220, 20);
		addButton.setBounds(100, 335, 70, 30);
		cancelButton.setBounds(200, 335, 70, 30);
		addJobOrderPanel.setBounds(640, 40, 360, 710);

		addJobOrderPanel.add(jobOrderNoLabel);
		addJobOrderPanel.add(referenceNoLabel);
		addJobOrderPanel.add(hospitalLabel);
		addJobOrderPanel.add(addressLabel);
		addJobOrderPanel.add(contactLabel);
		addJobOrderPanel.add(contactNoLabel);
		addJobOrderPanel.add(jobStatusLabel);
		addJobOrderPanel.add(jobOrderNoInput);
		addJobOrderPanel.add(referenceNoInput);
		addJobOrderPanel.add(hospitalInput);
		addJobOrderPanel.add(addressInput);
		addJobOrderPanel.add(contactInput);
		addJobOrderPanel.add(contactNoInput);
		addJobOrderPanel.add(addButton);
		addJobOrderPanel.add(cancelButton);
	}

	public void setJobInfoPanel(JobOrder jobOrder) {
		
		JLabel jobOrderNoLabel = new JLabel("Order No.: " + jobOrder.getJobOrderNo());
		JLabel referenceNoLabel = new JLabel("Reference No. : " + jobOrder.getReferenceNo());
		JLabel hospitalLabel = new JLabel("Hospital: " + jobOrder.getHospital());
		JLabel addressLabel = new JLabel("Address: " + jobOrder.getAddress());
		JLabel contactLabel = new JLabel("Contact Person: " + jobOrder.getContactPerson());
		JLabel contactNoLabel = new JLabel("Contact No.: " + jobOrder.getContactNo());
		JList assignedEngineers = new JList();
		JList assignedEquipment = new JList();
		jobInfoPanel = new JPanel(null);
		JButton jobEditButton = new JButton("Edit");
		
		jobOrderNoLabel.setBounds(30, 20, 250, 30);
		referenceNoLabel.setBounds(320, 20, 250, 30);
		hospitalLabel.setBounds(30, 60, 400, 30);
		addressLabel.setBounds(30, 100, 400, 30);
		contactLabel.setBounds(30, 140, 250, 30);
		contactNoLabel.setBounds(320, 140, 250, 30);
		assignedEngineers.setBounds(30, 270, 260, 470);
		assignedEquipment.setBounds(310, 270, 260, 470);
		jobInfoPanel.setBounds(20, 0, 600, 750);
		
		jobInfoPanel.add(jobOrderNoLabel);
		jobInfoPanel.add(referenceNoLabel);
		jobInfoPanel.add(hospitalLabel);
		jobInfoPanel.add(addressLabel);
		jobInfoPanel.add(contactLabel);
		jobInfoPanel.add(contactNoLabel);
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

	public JScrollPane unscheduledJobScroller, availableEngineerScroller, availableEquipmentScroller;
	public JButton unscheduledJobsButton, availableEngineersButton, availableEquipmentButton;

	public void setAvailabilityListPanel() {
		
		JList unscheduledJobList, availableEngineerList, availableEquipmentList;
		
		availabilityListPanel = new JPanel(null);
		
		unscheduledJobList = new JList(database.getUnscheduledJobs().toArray());
		unscheduledJobList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		unscheduledJobScroller = new JScrollPane(unscheduledJobList);
		unscheduledJobScroller.setVisible(false);
		/*availableEngineerList = new JList(database.getAvailableEngineers().toArray());
		availableEngineerList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		availableEngineerScroller = new JScrollPane(availableEngineerList);
		availableEngineerScroller.setVisible(false);
		availableEquipmentList = new JList(database.getAvailableEquipment().toArray());
		availableEquipmentList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		availableEquipmentScroller = new JScrollPane(availableEquipmentList);
		availableEquipmentScroller.setVisible(false);*/
		
		unscheduledJobsButton = new JButton("Unscheduled Jobs");
		availableEngineersButton = new JButton("Available Engineers");
		availableEquipmentButton = new JButton("Available Equipment");
		
		
		unscheduledJobsButton.setBounds(5, 5, 350, 30);
		/*availableEngineersButton.setBounds(5, 35, 350, 30);
		availableEquipmentButton.setBounds(5, 65, 350, 30);*/
		
		unscheduledJobsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(unscheduledJobScroller.isVisible()) {
					unscheduledJobScroller.setVisible(false);
					availableEngineersButton.setLocation(5, 35);
					availableEquipmentButton.setLocation(5, 65);
				} else {
					unscheduledJobScroller.setVisible(true);
					availableEngineersButton.setLocation(5, 645);
					availableEquipmentButton.setLocation(5, 675);
					if (availableEngineerScroller.isVisible()) {
						availableEngineerScroller.setVisible(false);
					}
					if (availableEquipmentScroller.isVisible()) {
						availableEquipmentScroller.setVisible(false);
					}
				}
			}
		});
		/*availableEngineersButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(availableEngineerScroller.isVisible()) {
					availableEngineerScroller.setVisible(false);
					availableEquipmentButton.setLocation(5, 65);
				} else {
					availableEngineerScroller.setVisible(true);
					availableEngineersButton.setLocation(5, 35);
					availableEquipmentButton.setLocation(5, 675);
					if (unscheduledJobScroller.isVisible()) {
						unscheduledJobScroller.setVisible(false);
					}
					if (availableEquipmentScroller.isVisible()) {
						availableEquipmentScroller.setVisible(false);
					}
				}
			}
		});
		availableEquipmentButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(availableEquipmentScroller.isVisible()) {
					availableEquipmentScroller.setVisible(false);
				} else {
					availableEquipmentScroller.setVisible(true);
					availableEngineersButton.setLocation(5, 35);
					availableEquipmentButton.setLocation(5, 65);
					if (unscheduledJobScroller.isVisible()) {
						unscheduledJobScroller.setVisible(false);
					}
					if (availableEngineerScroller.isVisible()) {
						availableEngineerScroller.setVisible(false);
					}
				}
			}
		});*/

		unscheduledJobScroller.setBounds(5, 35, 350, 610);
		/*availableEngineerScroller.setBounds(5, 65, 350, 610);
		availableEquipmentScroller.setBounds(5, 95, 350, 610);*/
		
		availabilityListPanel.setBounds(640, 40, 360, 710);
		
		availabilityListPanel.add(unscheduledJobsButton);
		availabilityListPanel.add(unscheduledJobScroller);
		/*availabilityListPanel.add(availableEngineersButton);
		availabilityListPanel.add(availableEngineerScroller);
		availabilityListPanel.add(availableEquipmentButton);
		availabilityListPanel.add(availableEquipmentScroller);*/
		
	}
	
	// VIEW ENGINEERS PANEL COMPONENTS
	
	public void setViewEngineersPanel() {
		JPanel listPanel = new JPanel(new GridLayout(0, 1));
		database.getEngineerList();
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
		database.getEquipmentList();
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
		database.getJobOrderList();
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
		calendarTableScroll.setBounds(0, 100, 600, 490);
		
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
		
		//ArrayList<Integer> jobDays = database.getJobDays(month+1, year);
		
		//calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new TableRenderer(jobDays));
		
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
