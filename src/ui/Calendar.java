package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calendar {
	/* Date Variables */
	
	private int yearBound, monthBound, dayBound, currentYear, currentMonth;
	
	private boolean hasJob = true;
	
	/* GUI Components */
	public JLabel monthLabel, yearLabel, changeMonthLabel, changeYearLabel;
	public JButton prevButton, nextButton;
	public JComboBox monthCombo, yearCombo;
	public JScrollPane calendarTableScroll;
	public JPanel calendarPanel;
	// public Container pane;
	
	public JTable calendarTable;
	public DefaultTableModel modelCalendarTable;
	
	public JPanel getCalendarComponent() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {}
		
		
		// pane.setLayout(null);
		
		monthLabel = new JLabel();
		yearLabel = new JLabel();
		changeMonthLabel = new JLabel();
		changeYearLabel = new JLabel();
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
				if(hasJob) {
					// open window
					openDayWindow();
				}
			}
		});
		

		calendarTableScroll = new JScrollPane(calendarTable);
		calendarPanel = new JPanel(null);

		prevButton.addActionListener(new prevButtonAction());
		nextButton.addActionListener(new nextButtonAction());
		yearCombo.addActionListener(new yearComboAction());
		monthCombo.addActionListener(new monthComboAction());
		
		// pane.add(calendarPanel);
		
		calendarPanel.add(monthLabel);
		calendarPanel.add(yearLabel);
		calendarPanel.add(changeMonthLabel);
		calendarPanel.add(changeYearLabel);
		calendarPanel.add(yearCombo);
		calendarPanel.add(monthCombo);
		calendarPanel.add(prevButton);
		calendarPanel.add(nextButton);
		
		calendarPanel.setBounds(0, 0, 600, 600);
		monthLabel.setBounds(550-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(monthLabel.getWidth()+monthLabel.getX()-120, 50, 200, 50);
		changeMonthLabel.setBounds(20, 150, 150, 50);
		changeYearLabel.setBounds(20, 50, 150, 50);
		yearCombo.setBounds(720, 610, 160, 40);
		monthCombo.setBounds(720, 670, 160, 40);
		prevButton.setBounds(270, 50, 100, 50);
		nextButton.setBounds(770, 50, 100, 50);
		
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
		
		
	
		
		refreshCalendar(monthBound, yearBound);
		
		return calendarPanel;
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
		monthLabel.setBounds(550-monthLabel.getPreferredSize().width/2, 50, 200, 50);
		yearLabel.setBounds(monthLabel.getWidth()+monthLabel.getX()-120, 50, 200, 50);
	            
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
	
	public void openDayWindow() {
		
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
