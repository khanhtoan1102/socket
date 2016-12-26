package com.kt.server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.Color;
import javax.swing.JTextArea;

public class ServerGui implements ActionListener{

	private JFrame frame;
	private JTextField tfName;
	private JTextField tfEnter;
	private JTextArea taContent;
	
	private String temp = "";
	private ServerSocket server;
	private Socket socket;
	private String request;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ServerGui window = new ServerGui();
		window.frame.setVisible(true);
		window.Serve();
	}

	/**
	 * Create the application.
	 */
	public ServerGui() {
		initialize();
	}
	
	/**
	 * Create the method
	 */
	public void Serve(){
		try {
			server = new ServerSocket(2222);
			while(true){
				socket = server.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while((request = br.readLine()) != null){
					temp += request + "\n";
					taContent.setText(temp);
				}
			}
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
		frame.setBounds(100, 100, 592, 384);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tfName = new JTextField();
		tfName.setBounds(0, 0, 576, 36);
		tfName.setText("Enter The Name");
		tfName.setBackground(Color.PINK);
		tfName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		frame.getContentPane().add(tfName);
		tfName.setColumns(10);
		
		taContent = new JTextArea();
		taContent.setBackground(Color.CYAN);
		taContent.setBounds(0, 35, 576, 256);
		frame.getContentPane().add(taContent);
		
		tfEnter = new JTextField();
		tfEnter.setFont(new Font("Times New Roman", Font.BOLD, 14));
		tfEnter.setColumns(10);
		tfEnter.setBackground(Color.WHITE);
		tfEnter.setBounds(0, 292, 576, 53);
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
				ps.println(content);
				taContent.setText(temp);
				tfEnter.setText("");
				tfEnter.requestFocus();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
