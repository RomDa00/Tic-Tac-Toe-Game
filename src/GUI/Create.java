package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import GUI.Connect.MyActionListner;
import serverSide.Server;

import javax.swing.JButton;

/**
 * 
 * @author Romain
 * @version 1.0
 *
 * Class description: Create GUI
 *
 */
public class Create {

	private JFrame CreateMenu;
	private JTextField textFieldPortNumber;
	private JTextField textFieldIpAddress;
	
	private JButton btnMainMenu = new JButton("Main Menu");
	private JButton btnCreate = new JButton("Create");

	private ServerSocket ServerSocket;
	private Socket ClientSocket;
	
	int portNumber = 5555;
	
	InetAddress IP = InetAddress.getLocalHost();
	String HostIpAddress = IP.getHostAddress();
	
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
					Create window = new Create();
					window.CreateMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Create()  throws UnknownHostException
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		CreateMenu = new JFrame();
		CreateMenu.setTitle("Host a game");
		CreateMenu.setType(Type.UTILITY);
		CreateMenu.setBounds(100, 100, 450, 166);
		CreateMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CreateMenu.getContentPane().setLayout(null);
		CreateMenu.setLocationRelativeTo(null);
		
		JLabel lblPortNumber = new JLabel("Port Number:");
		lblPortNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPortNumber.setBounds(28, 13, 146, 16);
		CreateMenu.getContentPane().add(lblPortNumber);
		
		textFieldPortNumber = new JTextField();
		textFieldPortNumber.setText(Integer.toString(portNumber));
		textFieldPortNumber.setEditable(false);
		textFieldPortNumber.setBounds(186, 13, 204, 22);
		CreateMenu.getContentPane().add(textFieldPortNumber);
		textFieldPortNumber.setColumns(10);
		
		JLabel lblIpAddress = new JLabel("Ip Address:");
		lblIpAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIpAddress.setBounds(28, 42, 114, 32);
		CreateMenu.getContentPane().add(lblIpAddress);
		
		textFieldIpAddress = new JTextField();
		textFieldIpAddress.setText(HostIpAddress);
		textFieldIpAddress.setEditable(false);
		textFieldIpAddress.setBounds(186, 50, 204, 22);
		CreateMenu.getContentPane().add(textFieldIpAddress);
		textFieldIpAddress.setColumns(10);
		
		btnMainMenu.addActionListener(actionListener);
		btnMainMenu.setBounds(28, 87, 97, 25);
		CreateMenu.getContentPane().add(btnMainMenu);
		
		btnCreate.addActionListener(actionListener);
		btnCreate.setBounds(293, 85, 97, 25);
		CreateMenu.getContentPane().add(btnCreate);
	}
	
	private class MyActionListner implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(btnMainMenu))
			{
				CreateMenu.dispose();
				MainMenu MM = new MainMenu();
				MM.main(null);
			}
			if(e.getSource().equals(btnCreate))
			{
				CreateMenu.dispose();
				GameBoard.main(null);
			}
		}	
	}
	
	private void CreateServer()
	{
		try 
		{
			ServerSocket = new ServerSocket(portNumber);
			ClientSocket = ServerSocket.accept();
			JOptionPane.showMessageDialog(null, "Opponent found!");
		} catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
