package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import GUI.MainMenu.MyActionListner;
import serverSide.Server;
import utilities.InputListener;

import javax.swing.JButton;

import GUI.GameBoard;

/**
 * 
 * @author Romain
 * @version 1.0
 *
 * Class description: Connect GUI
 *
 */
public class Connect 
{

	private JFrame ConnectMenu;
	private JTextField textFieldPortNumber;
	private JTextField textFieldIpAddress;
	
	private JButton btnMainMenu = new JButton("Main Menu");
	private JButton btnSearch = new JButton("Search");

	private ObjectOutputStream oos;

	String ChoosenUsername = "";

	private ActionListener actionListener = new MyActionListner();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Connect window = new Connect();
					window.ConnectMenu.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Connect() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ConnectMenu = new JFrame();
		ConnectMenu.setTitle("Connect");
		ConnectMenu.setType(Type.UTILITY);
		ConnectMenu.setBounds(100, 100, 450, 195);
		ConnectMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ConnectMenu.getContentPane().setLayout(null);
		ConnectMenu.setLocationRelativeTo(null);
		
		JLabel lblPortNumber = new JLabel("Port Number:");
		lblPortNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPortNumber.setBounds(23, 21, 151, 25);
		ConnectMenu.getContentPane().add(lblPortNumber);
		
		JLabel lblIpAddress = new JLabel("Ip Address:");
		lblIpAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIpAddress.setBounds(25, 66, 151, 25);
		ConnectMenu.getContentPane().add(lblIpAddress);
		
		textFieldPortNumber = new JTextField();
		textFieldPortNumber.setEditable(false);
		textFieldPortNumber.setText("5555");
		textFieldPortNumber.setBounds(183, 27, 204, 22);
		ConnectMenu.getContentPane().add(textFieldPortNumber);
		textFieldPortNumber.setColumns(10);
		
		textFieldIpAddress = new JTextField();
		textFieldIpAddress.setEnabled(false);
		textFieldIpAddress.setBackground(Color.LIGHT_GRAY);
		textFieldIpAddress.setBounds(183, 72, 204, 22);
		ConnectMenu.getContentPane().add(textFieldIpAddress);
		textFieldIpAddress.setColumns(10);
		
		btnMainMenu.addActionListener(actionListener);
		btnMainMenu.setBounds(23, 117, 97, 25);
		ConnectMenu.getContentPane().add(btnMainMenu);
		
		btnSearch.addActionListener(actionListener);
		btnSearch.setBounds(294, 114, 97, 25);
		ConnectMenu.getContentPane().add(btnSearch);
	}

	public class MyActionListner implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{				
			if(e.getSource().equals(btnMainMenu))
			{
				ConnectMenu.dispose();
				MainMenu MM = new MainMenu();
				MM.main(null);
			}
			if(e.getSource().equals(btnSearch))
			{
				//int GivenPortNumber = Integer.parseInt(textFieldPortNumber.getText());
				ConnectMenu.dispose();
				GameBoard.main(null);
			}
		}	
	}
}
