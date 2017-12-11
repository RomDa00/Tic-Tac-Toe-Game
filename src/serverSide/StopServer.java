package serverSide;

import java.io.IOException;
import java.net.ServerSocket;
import javax.swing.JOptionPane;

public class StopServer 
{
	static ServerSocket serverSocket;
	public static void main(String[] args) 
	{
		if(serverSocket == null)
		{
			System.out.println("null");
		}
		else
		{
			System.out.println("not null");
		}
		System.out.println(serverSocket.isClosed());
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
