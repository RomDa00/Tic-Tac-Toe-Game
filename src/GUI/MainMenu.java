package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import GUI.Connect;
import GUI.Create;
import serverSide.Server;

public class MainMenu {

	private JFrame MainFrameMainMenu;
	private JButton btnExit = new JButton("Exit");
	private JButton btnCreateSession = new JButton("Create Session");
	private JButton btnConnectToSession = new JButton("Connnect to session");

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
					MainMenu window = new MainMenu();
					window.MainFrameMainMenu.setVisible(true);
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
	public MainMenu() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainFrameMainMenu = new JFrame();
		MainFrameMainMenu.setTitle("Main Menu");
		MainFrameMainMenu.setBounds(100, 100, 450, 357);
		MainFrameMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrameMainMenu.getContentPane().setLayout(null);
		MainFrameMainMenu.setLocationRelativeTo(null);
		
		JLabel lblTicTacToe = new JLabel("Tic Tac Toe");
		lblTicTacToe.setBounds(0, 0, 432, 60);
		lblTicTacToe.setForeground(Color.BLUE);
		lblTicTacToe.setFont(new Font("Tahoma", Font.PLAIN, 49));
		lblTicTacToe.setHorizontalAlignment(SwingConstants.CENTER);
		MainFrameMainMenu.getContentPane().add(lblTicTacToe);
		
		btnConnectToSession.addActionListener(actionListener);
		btnConnectToSession.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConnectToSession.setBounds(103, 73, 223, 65);
		
		MainFrameMainMenu.getContentPane().add(btnConnectToSession);
		
		btnCreateSession.addActionListener(actionListener);
		btnCreateSession.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCreateSession.setBounds(103, 151, 223, 65);
		MainFrameMainMenu.getContentPane().add(btnCreateSession);
		
		btnExit.addActionListener(actionListener);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(103, 229, 223, 65);
		MainFrameMainMenu.getContentPane().add(btnExit);
	}
	
	public class MyActionListner implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(btnConnectToSession))
			{
				MainFrameMainMenu.dispose();
				Connect connectGUI = new Connect();
				connectGUI.main(null);
			}
			if(e.getSource().equals(btnCreateSession))
			{
				MainFrameMainMenu.dispose();
				Create createGUI;
				try 
				{
					createGUI = new Create();
					createGUI.main(null);
				} catch (UnknownHostException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource().equals(btnExit))
			{
				System.exit(0);
			}
		}	
	}
	
	public void closeWindow()
	{
		MainFrameMainMenu.dispose();
		main(null);
	}
}
