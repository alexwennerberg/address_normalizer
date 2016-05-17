package edu.truman.cs370.team6.addrSys;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GUIBatch
{
	private static JTextField inputText;
	private static JTextField outputText;
	private static JTextArea responseArea;
	
	public GUIBatch()
	{
		JFrame frame = new JFrame();
		frame.setLayout( new GridLayout (4, 1));
		
		JPanel inputPanel = new JPanel(new FlowLayout());
		JLabel inputLabel = new JLabel("Input file location:");
		inputText = new JTextField(50);
		JButton inputButton = new JButton("Browse");
		inputButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(new JFrame());
				inputText.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		inputPanel.add(inputLabel);
		inputPanel.add(inputText);
		inputPanel.add(inputButton);
		
		JPanel outputPanel = new JPanel(new FlowLayout());
		JLabel outputLabel = new JLabel("Output location:");
		outputText = new JTextField(50);
		JButton outputButton = new JButton("Browse");
		outputButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(new JFrame());
				outputText.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		outputPanel.add(outputLabel);
		outputPanel.add(outputText);
		outputPanel.add(outputButton);
		
		JPanel submitPanel = new JPanel(new FlowLayout());
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				clearFields();
			}
		});
		JButton submitButton = new JButton("Normalize");
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					normalizeBatch(inputText.getText().trim(), 
							outputText.getText().trim());
				    clearFields();
				}
				catch (IOException ioe) {}
			}
		});
		submitPanel.add(clearButton);
		submitPanel.add(submitButton);
		
		JPanel responsePanel = new JPanel(new FlowLayout());
		responseArea = new JTextArea();
		responseArea.setOpaque(false);
		responsePanel.add(responseArea);
		
		frame.add(inputPanel);
		frame.add(outputPanel);
		frame.add(submitPanel);
		frame.add(responsePanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private static void clearFields()
	{
		inputText.setText("");
		outputText.setText("");
	}
	
	private static void normalizeBatch(String inName, String outName)
	    throws IOException
	{
		BufferedReader inFile = new BufferedReader(new FileReader(inName));
		BufferedWriter outFile = new BufferedWriter(new FileWriter(outName));
		int normalizedCount = 0;
		while (inFile.ready())
		{
			boolean normalized;
		    Address nextAddress = new Address(inFile.readLine());
		    try
		    {
		    	normalized = nextAddress.normalize();
		    } 
		    catch(NullPointerException npe)
		    {
		    	normalized = false;
		    }
		    
		    if (normalized)
		    {
		    	String addressOutput = nextAddress.formatFileLine();
		        outFile.write(addressOutput, 0, addressOutput.length());
		        outFile.newLine();
		        normalizedCount++;
		    }
		}
		outFile.close();
		alertSuccess(normalizedCount);
	}
	
	private static void alertSuccess(int count)
	{
		responseArea.setText("Normalization successful.\n" + 
						count + " addresses normalized.");
	}
}