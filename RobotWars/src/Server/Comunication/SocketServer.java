package Server.Comunication;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServer implements Runnable {
	private final int PORT_NUMBER = 10000;
	private boolean listening;
	public ArrayList<Socket> clientes = new ArrayList<Socket>();
	
	//private PrintWriter output;
	public BufferedReader input1;
	public BufferedReader input2;

	public PrintWriter output1;
	public PrintWriter output2;
	private DataOutputStream binOutput;
    
	
	public SocketServer() {
		listening = true;
	}
	
	public void startListening() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public void stopListening() {
		listening = false;
		
	}
	
	public void sendMsg1(String pMsg) {
		try {
			if (output1 == null) return;
			this.output1.write(pMsg+"\n");
			this.output1.flush();	
			
		} catch (Exception ex) {
			
		}
	}
	
	public void sendMsg2(String pMsg) {
		try {
			if (output2 == null) return;
			this.output2.write(pMsg+"\n");
			this.output2.flush();	
			
		} catch (Exception ex) {
			
		}
	}
	
	public void sendMsg(byte[] pMsg) {
		try {
			this.binOutput.write(pMsg);
			this.output1.flush();		
		} catch (Exception ex) {
			
		}
	}
	
	public void run() {
		try {
			// este es el server especifico que escucha los entrantes
			ServerSocket socketListener = new ServerSocket(PORT_NUMBER);
		
			while (listening) {

				Socket connection = socketListener.accept();
				
				
				SocketClient client = new SocketClient(connection);
				client.ID = clientes.size();
			
				
				if (clientes.size() == 0) {
					input1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					output1 = new PrintWriter(connection.getOutputStream());
				}else {
					input2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					output2 = new PrintWriter(connection.getOutputStream());
					
				}
				
				
	
				clientes.add(connection);
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
	}
}
