package com.kt.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class ClientGui implements ActionListener{

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfEnter;
	private JTextArea taContent;
	
	private Socket socket;
	private String temp = "";
	private String response;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClientGui window = new ClientGui();
		String host = JOptionPane.showInputDialog(null,"Enter the ip address");
		window.frame.setVisible(true);
		window.Connect(host);
		
	}

	/**
	 * Create the application.
	 */
	public ClientGui() {
		initialize();
	}
	
	/**
	 * Create the method;
	 */
	public void Connect(String host){
		try {
			socket  = new Socket(host, 2222);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			while( (response = br.readLine())  != null){
				temp += response + "\n";
				taContent.setText(temp);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 377);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfName = new JTextField();
		tfName.setText("Enter The Name");
		tfName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfName.setColumns(10);
		tfName.setBackground(Color.PINK);
		tfName.setBounds(0, 0, 576, 36);
		frame.getContentPane().add(tfName);
		
		taContent = new JTextArea();
		taContent.setBackground(Color.CYAN);
		taContent.setBounds(0, 37, 576, 253);
		frame.getContentPane().add(taContent);
		
		tfEnter = new JTextField();
		tfEnter.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfEnter.setColumns(10);
		tfEnter.setBackground(Color.WHITE);
		tfEnter.setBounds(0, 285, 576, 53);
		tfEnter.addActionListener(this);
		frame.getContentPane().add(tfEnter);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(tfEnter)){
			try {
				PrintStream ps = new PrintStream(socket.getOutputStream());
				String content = tfName.getText().toUpperCase() + ": " + tfEnter.getText() + "\n";
				temp += content + "\n";
				taContent.setText(temp);
				ps.println(content);
				tfEnter.setText("");
				tfEnter.requestFocus();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
