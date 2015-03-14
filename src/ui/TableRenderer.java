package ui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import classes.JobOrder;

public class TableRenderer extends DefaultTableCellRenderer {
	private ArrayList<Integer> jobDays;
	
	public TableRenderer(ArrayList<Integer> jobDays) {
		this.jobDays = jobDays;
	}
	
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column) {
    	super.getTableCellRendererComponent(table, value, selected, focused, row, column);
    	if(jobDays.contains(value)) {
    		setBackground(Color.ORANGE);
    	} else if (column == 0 || column == 6) {
        	setBackground(new Color(220,220,255));
    	} else {
        	setBackground(Color.WHITE);
    	}
    	setBorder(null);
    	setForeground(Color.black);
    	return this;  
    }
    
    
}