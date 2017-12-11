package serverSide;

import java.util.*;
import java.net.*;
import java.io.*;
import utilities.*;

/**
 * 
 * @author Romain
 * @version 1.0
 *
 * Class description:
 *
 */
public class ClientHandler implements Runnable, Observer
{
	//Attributes
	private ObjectOutputStream			oos1,oos2;
	private Socket						cs1,cs2;
	private InputListener				lis1,lis2;
	
	//Constructors
	/**
	 * @param cs1
	 * @param cs2
	 */
	public ClientHandler(Socket cs1, Socket cs2)
	{
		this.cs1 = cs1;
		this.cs2 = cs2;
		
		try
		{
			oos1 = new ObjectOutputStream(cs1.getOutputStream());
			oos2 = new ObjectOutputStream(cs2.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		lis1 = new InputListener(1,cs1,this);
		lis2 = new InputListener(2,cs2,this);
		
		Thread t1 = new Thread(lis1);
		t1.start();
		Thread t2 = new Thread(lis2);
		t2.start();
		
		try
		{
			Message m = new Message("Connected","You can start chatting!",new Date());
			oos1.writeObject(m);
			oos2.writeObject(m);
			
			while(cs1.isConnected() && cs2.isConnected());
			
			cs1.close();
			cs2.close();
			oos1.close();
			oos2.close();
		}
		catch (SocketException e1)
		{
			
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observable, Object arg)
	{
		InputListener listener = (InputListener)observable;
		Message message = (Message)arg;
		
		try
		{
			if(listener.getListenerNumber() == 1)
			{
				oos2.writeObject(message);
			}
			else
			{
				oos1.writeObject(message);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
