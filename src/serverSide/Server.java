package serverSide;

import java.net.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * 
 * @author Romain
 * @version 1.0
 *
 * Class description: Server
 *
 */
public class Server
{
	static ServerSocket serverSocket;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		ArrayList<Socket> socketList = new ArrayList<Socket>(3);

		try
		{
			serverSocket = new ServerSocket(5555);
			JOptionPane.showMessageDialog(null, "Server Is up and running");
			while(true)
			{
				Socket clientSocket = serverSocket.accept();
				
				socketList.add(clientSocket);
				
				if(socketList.size() == 2)
				{
					ClientHandler clientHandler = new ClientHandler(
							socketList.get(0),socketList.get(1));
					Thread handlerThread = new Thread(clientHandler);
					handlerThread.start();
					socketList.clear();
				}
			}
		}
		catch (SocketException e)
		{

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void shutdownServer()
	{
		try 
		{
			serverSocket.close();
			JOptionPane.showMessageDialog(null, "Server stopped");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
