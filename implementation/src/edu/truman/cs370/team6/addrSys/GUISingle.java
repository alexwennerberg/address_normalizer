package edu.truman.cs370.team6.addrSys;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class GUISingle
{
	private JTextField streetAddressField = new JTextField(50);
	private JTextField cityField = new JTextField(50);
	private JTextField stateField = new JTextField(50);
	private JTextField zipFiveField = new JTextField(50);
	private JTextField zipFourField = new JTextField(50);
	private JTextArea outputArea = new JTextArea("");

	public GUISingle()
	{
		JFrame frame = new JFrame();
		frame.setLayout( new GridLayout (7, 1));
		
		JPanel streetAddressPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel streetAddressLabel = new JLabel("Enter the street address:", 
				SwingConstants.LEFT);
		streetAddressPanel.add(streetAddressLabel);
		streetAddressPanel.add(streetAddressField);
		
		JPanel cityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel cityLabel = new JLabel("Enter the city:", 
				SwingConstants.LEFT);
		cityPanel.add(cityLabel);
		cityPanel.add(cityField);
		
		JPanel statePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel stateLabel = new JLabel("Enter the state:", 
				SwingConstants.LEFT);
		statePanel.add(stateLabel);
		statePanel.add(stateField);
		
		JPanel zipFivePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel zipFiveLabel = new JLabel("Enter the zip 5:", 
				SwingConstants.LEFT);
		zipFivePanel.add(zipFiveLabel);
		zipFivePanel.add(zipFiveField);
		
		JPanel zipFourPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel zipFourLabel = new JLabel("Enter the zip 4:", 
				SwingConstants.LEFT);
		zipFourPanel.add(zipFourLabel);
		zipFourPanel.add(zipFourField);
		
		JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		outputArea.setOpaque(false);
		outputPanel.add(outputArea);
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				streetAddressField.setText("");
				cityField.setText("");
				stateField.setText("");
				zipFiveField.setText("");
				zipFourField.setText("");
			}
		});
		JButton normalizeButton = new JButton("Normalize");
		normalizeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String data = streetAddressField.getText() + "\t" + 
					cityField.getText() + "\t" + stateField.getText() + "\t" +
					zipFiveField.getText() + "\t" + zipFourField.getText();
				outputArea.setText(prepAddress(data));
			}
		});
		buttonPanel.add(clearButton);
		buttonPanel.add(normalizeButton);
		
		frame.add(streetAddressPanel);
		frame.add(cityPanel);
		frame.add(statePanel);
		frame.add(zipFivePanel);
		frame.add(zipFourPanel);
		frame.add(buttonPanel);
		frame.add(outputPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	private String prepAddress(String data)
	{
		Address singleAddress = new Address(data);
		singleAddress.normalize();
		return singleAddress.getStreet() + "\n" +
				singleAddress.getCity() + " " + singleAddress.getState() +
				" " + singleAddress.getZipFive() + "-" + 
				singleAddress.getZipFour();
	}
}