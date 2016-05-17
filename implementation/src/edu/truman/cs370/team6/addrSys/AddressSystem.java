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

public class AddressSystem
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JButton singleAddressButton = new JButton("Single Address");
		singleAddressButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUISingle guiSingle = new GUISingle();
			}
		});
		JButton batchAddressButton = new JButton("Multiple Addresses");
		batchAddressButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIBatch guiBatch = new GUIBatch();
			}
		});
		panel.add(singleAddressButton, BorderLayout.WEST);
		panel.add(batchAddressButton, BorderLayout.EAST);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}