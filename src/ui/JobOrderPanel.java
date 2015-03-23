package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.LineBorder;

import classes.JobOrder;

public class JobOrderPanel extends JPanel {
	
	private int jobOrderId;
	private int jobOrderNo;
	private int referenceNo;
	private String hospital;
	private String address;
	private String contactPerson;
	private int contactNo;
	private String status;
	//status = 0 is not completed
	//status = 1 is completed
	
	public JLabel jobOrderNoLabel;
	public JLabel referenceNoLabel;
	public JLabel hospitalLabel;
	public JLabel addressLabel;
	public JLabel contactPersonLabel;
	public JLabel contactNoLabel;
	public JLabel statusLabel;
	
	public JobOrderPanel() {
		jobOrderNoLabel = new JLabel();
	}
	
	public JobOrderPanel(JobOrder jobOrder) {
		
		this.setLayout(null);
		this.setBorder(new LineBorder(Color.WHITE, 2));
		
		jobOrderNoLabel = new JLabel("Order No.: " + jobOrder.getJobOrderNo());
		referenceNoLabel = new JLabel("Reference No.: " + jobOrder.getReferenceNo());
		hospitalLabel = new JLabel("Hospital: " + jobOrder.getHospital());
		addressLabel = new JLabel("Address: " + jobOrder.getAddress());
		contactPersonLabel = new JLabel("Contact Person: " + jobOrder.getContactPerson());
		contactNoLabel = new JLabel("Contact Number: " + jobOrder.getContactNo());
		if( jobOrder.getStatus() == 1){
			status = "completed";
		}else{
			status = "not completed";
		}
		statusLabel = new JLabel("Status: " + status);
		
		this.add(jobOrderNoLabel);
		this.add(referenceNoLabel);
		this.add(hospitalLabel);
		this.add(addressLabel);
		this.add(contactPersonLabel);
		this.add(contactNoLabel);
		this.add(statusLabel);
		
		jobOrderNoLabel.setBounds(10, 5, 150, 20);
		referenceNoLabel.setBounds(160, 5, 150, 20);
		hospitalLabel.setBounds(10, 25, 150, 20);
		addressLabel.setBounds(10, 45, 250, 20);
		contactPersonLabel.setBounds(10, 65, 150, 20);
		contactNoLabel.setBounds(10, 85, 150, 20);
		statusLabel.setBounds(10, 105, 150, 20);
		this.setSize(340, 130);
		this.setPreferredSize(new Dimension(340, 130));
		
	}
}
