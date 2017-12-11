package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.SwingConstants;
import GUI.Connect;
import GUI.Connect.MyActionListner;
import utilities.InputListener;
import utilities.Message;

/**
 * 
 * @author Romain
 * @version 1.0
 *
 * Class description: Main menu GUI
 *
 */
public class GameBoard implements Observer
{

	private JFrame TicTacToe;
	
	JTextArea ChatArea;
	JTextField SendBox = new JTextField();
	JLabel TurnIndicatorText = new JLabel("");
	
	JButton SendChatButton = new JButton("Send");
	
	JButton GridButton1 = new JButton("?");
	JButton GridButton2 = new JButton("?");
	JButton GridButton3 = new JButton("?");
	JButton GridButton4 = new JButton("?");
	JButton GridButton5 = new JButton("?");
	JButton GridButton6 = new JButton("?");
	JButton GridButton7 = new JButton("?");
	JButton GridButton8 = new JButton("?");
	JButton GridButton9 = new JButton("?");
	
	JButton btnXassigner = new JButton("X");
	JButton btnOassigner = new JButton("O");
	JButton ButtonDisconnect = new JButton("Disconnect");
	
	int temp = (Math.random() <= 0.5) ? 1 : 2;
	
	String assignedLetter = "";
	String oppositeAssignedLetter = "";
	String showTurn = "Your Turn";
	String showOppositeturn = "Opponents turn";
	String givenIpAddress = "";
	
	String InputUsername = JOptionPane.showInputDialog("Input Username");
		
	JLabel HostUsernameText = new JLabel(InputUsername);
	JLabel OpponentUsernameText = new JLabel("-");

	private ActionListener actionListener = new MyActionListner();
	private ObjectOutputStream oos;
	private Socket connection;
	private InputListener inputListener;
	
	String Username;
	String OpponentUsername;
	
	/**
	 * Launch the application.
	 * @param object 
	 */
	public static void main(Object object) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					GameBoard window = new GameBoard();
					window.TicTacToe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameBoard() 
	{
		ConnectMe();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
	
		TicTacToe = new JFrame();
		TicTacToe.getContentPane().setBackground(Color.LIGHT_GRAY);
		TicTacToe.setTitle("Tic Tac Toe");
		TicTacToe.setBounds(100, 100, 1171, 689);
		TicTacToe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TicTacToe.getContentPane().setLayout(null);
		TicTacToe.setLocationRelativeTo(null);
		
		HostUsernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		HostUsernameText.setBounds(12, 0, 198, 31);
		TicTacToe.getContentPane().add(HostUsernameText);
		
		OpponentUsernameText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		OpponentUsernameText.setBounds(552, 0, 198, 31);
		TicTacToe.getContentPane().add(OpponentUsernameText);
		
		TurnIndicatorText.setForeground(Color.BLUE);
		TurnIndicatorText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		TurnIndicatorText.setBounds(125, 51, 516, 59);
		TicTacToe.getContentPane().add(TurnIndicatorText);
		
		GridButton1.addActionListener(actionListener);
		GridButton1.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton1.setForeground(Color.BLACK);
		GridButton1.setBounds(125, 123, 162, 146);
		TicTacToe.getContentPane().add(GridButton1);
		
		GridButton2.addActionListener(actionListener);
		GridButton2.setForeground(Color.BLACK);
		GridButton2.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton2.setBounds(305, 123, 162, 146);
		TicTacToe.getContentPane().add(GridButton2);
		
		GridButton3.addActionListener(actionListener);
		GridButton3.setForeground(Color.BLACK);
		GridButton3.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton3.setBounds(479, 123, 162, 146);
		TicTacToe.getContentPane().add(GridButton3);
		
		GridButton4.addActionListener(actionListener);
		GridButton4.setForeground(Color.BLACK);
		GridButton4.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton4.setBounds(125, 282, 162, 146);
		TicTacToe.getContentPane().add(GridButton4);
		
		GridButton5.addActionListener(actionListener);
		GridButton5.setForeground(Color.BLACK);
		GridButton5.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton5.setBounds(305, 282, 162, 146);
		TicTacToe.getContentPane().add(GridButton5);
		
		GridButton6.addActionListener(actionListener);
		GridButton6.setForeground(Color.BLACK);
		GridButton6.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton6.setBounds(479, 282, 162, 146);
		TicTacToe.getContentPane().add(GridButton6);
		
		GridButton7.addActionListener(actionListener);
		GridButton7.setForeground(Color.BLACK);
		GridButton7.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton7.setBounds(125, 441, 162, 146);
		TicTacToe.getContentPane().add(GridButton7);
		
		GridButton8.addActionListener(actionListener);
		GridButton8.setForeground(Color.BLACK);
		GridButton8.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton8.setBounds(305, 441, 162, 146);
		TicTacToe.getContentPane().add(GridButton8);
		
		GridButton9.addActionListener(actionListener);
		GridButton9.setForeground(Color.BLACK);
		GridButton9.setFont(new Font("Tahoma", Font.PLAIN, 85));
		GridButton9.setBounds(479, 441, 162, 146);
		TicTacToe.getContentPane().add(GridButton9);
		
		ButtonDisconnect.addActionListener(actionListener);
		ButtonDisconnect.setBounds(12, 606, 116, 25);
		TicTacToe.getContentPane().add(ButtonDisconnect);
		
		SendBox.setBounds(789, 606, 225, 22);
		TicTacToe.getContentPane().add(SendBox);
		
		SendChatButton.addActionListener(actionListener);
		SendChatButton.setBounds(1026, 605, 119, 25);
		TicTacToe.getContentPane().add(SendChatButton);
		
		ChatArea = new JTextArea();
		ChatArea.setEditable(false);
		ChatArea.setBounds(789, 41, 356, 546);
		TicTacToe.getContentPane().add(ChatArea);

		JLabel lblChat = new JLabel("Chat");
		lblChat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChat.setBounds(789, 7, 90, 16);
		TicTacToe.getContentPane().add(lblChat);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.BLACK);
		separator.setBackground(Color.BLACK);
		separator.setBounds(767, 0, 2, 644);
		TicTacToe.getContentPane().add(separator);

		btnXassigner.addActionListener(actionListener);
		btnXassigner.setBounds(305, 0, 54, 37);
		TicTacToe.getContentPane().add(btnXassigner);
		
		btnOassigner.setBounds(413, 0, 54, 37);
		btnOassigner.addActionListener(actionListener);
		TicTacToe.getContentPane().add(btnOassigner);
	}
	
	public class MyActionListner implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Message message = new Message(InputUsername,SendBox.getText(), new Date());
			if(e.getSource() == SendChatButton);
			{
				try 
				{
					oos.writeObject(message);
					ChatArea.append(message.getMessage() + " - " + message.getTimeStamp() + "\n");
					SendBox.setText("");
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton1)
			{
				try 
				{
					GridButton1.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton1",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton2)
			{
				try 
				{
					GridButton2.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton2",null));
					GridButton2.setEnabled(false);
					GridButton1.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton3)
			{
				try 
				{
					GridButton3.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton3",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton4)
			{
				try 
				{
					GridButton4.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton4",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton5)
			{
				try 
				{
					GridButton5.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton5",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton6)
			{
				try 
				{
					GridButton6.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton6",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton7)
			{
				try 
				{
					GridButton7.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton7",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton8)
			{
				try 
				{
					GridButton8.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton8",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == GridButton9)
			{
				try 
				{
					GridButton9.setText(assignedLetter);
					TurnIndicatorText.setText(showOppositeturn + " = " + oppositeAssignedLetter);
					oos.writeObject(new Message(InputUsername,"GridButton9",null));
					GridButton1.setEnabled(false);
					GridButton2.setEnabled(false);
					GridButton3.setEnabled(false);
					GridButton4.setEnabled(false);
					GridButton5.setEnabled(false);
					GridButton6.setEnabled(false);
					GridButton7.setEnabled(false);
					GridButton8.setEnabled(false);
					GridButton9.setEnabled(false);
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btnXassigner)
			{
				try 
				{
					oos.writeObject(new Message(InputUsername,"btnXassigner",null));
					btnXassigner.setEnabled(false);
					btnOassigner.setEnabled(false);
					GridButton1.setEnabled(true);
					GridButton2.setEnabled(true);
					GridButton3.setEnabled(true);
					GridButton4.setEnabled(true);
					GridButton5.setEnabled(true);
					GridButton6.setEnabled(true);
					GridButton7.setEnabled(true);
					GridButton8.setEnabled(true);
					GridButton9.setEnabled(true);
					assignedLetter = "X";
					oppositeAssignedLetter = "O";
					TurnIndicatorText.setText("You are " + assignedLetter);
					
				} catch (IOException e1) 
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btnOassigner)
			{
				try 
				{
					oos.writeObject(new Message(InputUsername,"btnOassigner",null));
					btnXassigner.setEnabled(false);
					btnOassigner.setEnabled(false);
					GridButton1.setEnabled(true);
					GridButton2.setEnabled(true);
					GridButton3.setEnabled(true);
					GridButton4.setEnabled(true);
					GridButton5.setEnabled(true);
					GridButton6.setEnabled(true);
					GridButton7.setEnabled(true);
					GridButton8.setEnabled(true);
					GridButton9.setEnabled(true);
					assignedLetter = "O";
					oppositeAssignedLetter = "X";
					TurnIndicatorText.setText("You are " + assignedLetter);

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			if(e.getSource() == ButtonDisconnect)
			{
				try 
				{
					int disconnectPopUp = JOptionPane.showConfirmDialog(null, "Are you sure you want to disconnect?", "Waiting", JOptionPane.YES_OPTION);
					if(disconnectPopUp == JOptionPane.YES_OPTION)
					{
						oos.writeObject(new Message(InputUsername,"ButtonDisconnect",null));
						disconnect();
					}
					else
					{
						
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			winCheck();
		}
	}
	
	public void ConnectMe()
	{
		try 
		{	
			givenIpAddress = JOptionPane.showInputDialog("Enter ip address");
			System.out.println(givenIpAddress);
			connection = new Socket(givenIpAddress,5555);
			
			oos = new ObjectOutputStream(connection.getOutputStream());
			
			inputListener = new InputListener(connection,GameBoard.this);
			Thread t1 = new Thread(inputListener);
			t1.start();
			
			GridButton1.setEnabled(false);
			GridButton2.setEnabled(false);
			GridButton3.setEnabled(false);
			GridButton4.setEnabled(false);
			GridButton5.setEnabled(false);
			GridButton6.setEnabled(false);
			GridButton7.setEnabled(false);
			GridButton8.setEnabled(false);
			GridButton9.setEnabled(false);
			TurnIndicatorText.setText("Choose your letter");
		} 
		catch (UnknownHostException e) 
		{
			JOptionPane.showMessageDialog(null, "Host not found");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "IO Exception / Server not started");
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		Message message = (Message)arg1;
		String msg = message.getUser()+": "+message.getMessage()+" ("+message.getTimeStamp()+")";
		ChatArea.append(msg + "\n");

		OpponentUsernameText.setText(message.getUser());
		
		if(message.getMessage().equals("btnXassigner"))
		{
			btnXassigner.setEnabled(false);
		}
		if(message.getMessage().equals("btnOassigner"))
		{
			btnOassigner.setEnabled(false);
		}
		if(message.getMessage().equals("GridButton1"))
		{
			GridButton1.setText(oppositeAssignedLetter);
			GridButton1.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton2"))
		{
			GridButton2.setText(oppositeAssignedLetter);
			GridButton2.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton3"))
		{
			GridButton3.setText(oppositeAssignedLetter);
			GridButton3.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton4"))
		{
			GridButton4.setText(oppositeAssignedLetter);
			GridButton4.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton5"))
		{
			GridButton5.setText(oppositeAssignedLetter);
			GridButton5.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton6"))
		{
			GridButton6.setText(oppositeAssignedLetter);
			GridButton6.setEnabled(false);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton7"))
		{
			GridButton7.setText(oppositeAssignedLetter);
			GridButton7.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton8"))
		{
			GridButton8.setText(oppositeAssignedLetter);
			GridButton8.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("GridButton9"))
		{
			GridButton9.setText(oppositeAssignedLetter);
			GridButton9.setEnabled(false);
			TurnIndicatorText.setText(showTurn + " = " + assignedLetter);
			tileDecider();
		}
		if(message.getMessage().equals("ButtonDisconnect"))
		{
			int decision = JOptionPane.showConfirmDialog(null, "Other player has disconnected " + "\n" + " Search for other player?","Opponent disconnected",JOptionPane.YES_NO_OPTION);
			if(decision == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		winCheck();
	}
	public boolean winCheck()
	{
		boolean winner = false;
		if(GridButton1.getText().equals(assignedLetter) && GridButton2.getText().equals(assignedLetter) && GridButton3.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton4.getText().equals(assignedLetter) && GridButton5.getText().equals(assignedLetter) && GridButton6.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton7.getText().equals(assignedLetter) && GridButton8.getText().equals(assignedLetter) && GridButton9.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton1.getText().equals(assignedLetter) && GridButton4.getText().equals(assignedLetter) && GridButton7.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton2.getText().equals(assignedLetter) && GridButton5.getText().equals(assignedLetter) && GridButton8.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton3.getText().equals(assignedLetter) && GridButton6.getText().equals(assignedLetter) && GridButton9.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton3.getText().equals(assignedLetter) && GridButton5.getText().equals(assignedLetter) && GridButton7.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton1.getText().equals(assignedLetter) && GridButton5.getText().equals(assignedLetter) && GridButton9.getText().equals(assignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.GREEN);
			TurnIndicatorText.setText("You Win!");
			int winnerPopUp = JOptionPane.showConfirmDialog(null, "YOU WIN!!! \n Play again?", "WINNER", JOptionPane.YES_OPTION);
			if(winnerPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton1.getText().equals(oppositeAssignedLetter) && GridButton2.getText().equals(oppositeAssignedLetter) && GridButton3.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton4.getText().equals(oppositeAssignedLetter) && GridButton5.getText().equals(oppositeAssignedLetter) && GridButton6.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton7.getText().equals(oppositeAssignedLetter) && GridButton8.getText().equals(oppositeAssignedLetter) && GridButton9.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton1.getText().equals(oppositeAssignedLetter) && GridButton4.getText().equals(oppositeAssignedLetter) && GridButton7.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton2.getText().equals(oppositeAssignedLetter) && GridButton5.getText().equals(oppositeAssignedLetter) && GridButton8.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton3.getText().equals(oppositeAssignedLetter) && GridButton6.getText().equals(oppositeAssignedLetter) && GridButton9.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton3.getText().equals(oppositeAssignedLetter) && GridButton5.getText().equals(oppositeAssignedLetter) && GridButton7.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(GridButton1.getText().equals(oppositeAssignedLetter) && GridButton5.getText().equals(oppositeAssignedLetter) && GridButton9.getText().equals(oppositeAssignedLetter))
		{
			winner = true;
			TurnIndicatorText.setForeground(Color.RED);
			TurnIndicatorText.setText("You Lose...");
			int loserPopUp = JOptionPane.showConfirmDialog(null, "You lost... \n Play again?", "lost", JOptionPane.YES_OPTION);
			if(loserPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		else if(!GridButton1.getText().equals("?") && !GridButton2.getText().equals("?") && !GridButton3.getText().equals("?") && !GridButton4.getText().equals("?") &&
				!GridButton5.getText().equals("?") && !GridButton6.getText().equals("?") && !GridButton7.getText().equals("?") && !GridButton8.getText().equals("?") &&
				!GridButton9.getText().equals("?"))
		{
			TurnIndicatorText.setText("Draw");
			int drawPopUp = JOptionPane.showConfirmDialog(null, "Draw \n Play again?", "Draw", JOptionPane.YES_OPTION);
			if(drawPopUp == JOptionPane.YES_OPTION)
			{
				TicTacToe.dispose();
				main(null);
			}
			else
			{
				disconnect();
			}
		}
		return winner;
		}
	
	private void disconnect()
	{
		try 
		{
			oos.close();
			connection.close();
			inputListener = null;
			System.exit(0);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	

	
	private void tileDecider()
	{
		if(GridButton1.getText() == "?")
		{
			GridButton1.setEnabled(true);
		}
		if(GridButton2.getText() == "?")
		{
			GridButton2.setEnabled(true);
		}
		if(GridButton3.getText() == "?")
		{
			GridButton3.setEnabled(true);
		}
		if(GridButton4.getText() == "?")
		{
			GridButton4.setEnabled(true);
		}
		if(GridButton5.getText() == "?")
		{
			GridButton5.setEnabled(true);
		}
		if(GridButton6.getText() == "?")
		{
			GridButton6.setEnabled(true);
		}
		if(GridButton7.getText() == "?")
		{
			GridButton7.setEnabled(true);
		}
		if(GridButton8.getText() == "?")
		{
			GridButton8.setEnabled(true);
		}
		if(GridButton9.getText() == "?")
		{
			GridButton9.setEnabled(true);
		}
	}
}
