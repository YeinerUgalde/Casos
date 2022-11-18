package Server.Comunication;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import JSON.JSONDeserializing;
import JSON.JSONSerializing;
import UIController.Controller;

public class SocketClient extends Thread {
	
	private Controller controller = Controller.getInstance();
	private JSONSerializing json = new JSONSerializing();
	private JSONDeserializing des = new JSONDeserializing();
	
	public int ID;
	
	private Socket conexion;
	
    private PrintWriter output1;
    private DataOutputStream output2;
    
    private DataOutputStream binOutput;
    
    private BufferedReader input;
    //private DataInputStream input;
    
    private boolean listen = false;
    
	public SocketClient(String pIpAddress, int pPort) throws Exception {
		try {
			Socket socket = new Socket( pIpAddress, pPort);
			setSocket(socket);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public SocketClient(Socket pConexion) throws Exception {
		setSocket(pConexion);
	}
	
	public void setSocket(Socket pConexion) throws Exception {
		this.conexion = pConexion;
		
		output1 = new PrintWriter(pConexion.getOutputStream());
		output2 = new DataOutputStream(pConexion.getOutputStream());
		
		this.input = new BufferedReader(new InputStreamReader(pConexion.getInputStream()));
		//input = new DataInputStream(pConexion.getInputStream());
		
		
		this.binOutput = new DataOutputStream(pConexion.getOutputStream());
		this.listen = true;
		
		this.start();
	}
	
	public void close() {
		this.listen = false;
	}
	
	public void run() {
		while (this.listen) {
			try {
				
				String msg = this.input.readLine();
			
				processMessage(msg);
		
				//sendMsg(msg+ "Cliente");
				
				Thread.sleep(1);
			}
			catch (Exception ex) {
				ex.printStackTrace();
				System.out.print("Error cliente");
			}
		}
	}
	
	public void processMessage(String pMsg) throws JSONException {
		// aqui pueden dos tipos de procesadores
		// el que esta del lado del cliente y el del lado del server
		// ese proccesor mejor por dependency injection
		//DataOutputStream recibirDatos = new DataOutputStream(binOutput);
		//controller.reportCoords2(ID, ID);
		
	
		if(pMsg == null)return;
		//System.out.println(pMsg);
		JSONObject object = json.parseJSON(pMsg);
		//System.out.println(object);
		//des.Deserializing(object);
		
		//String[] xy = pMsg.split(",");
		
		//int x = Integer.parseInt(xy[0]); // 123
		//int y = Integer.parseInt(xy[1]); // 654321
		if (controller.isEnabled()) {
			des.Deserializing(object);
			//controller.reportCoords2(x, y);
			//System.out.print("x: "+x);
			//System.out.println(" y: "+y);
		}
		
	}
	
	public void processMessage(byte[] pMsg) {
		System.out.println(pMsg);
		pMsg = null;
	}

	public void sendMsg(String pMsg) {
		try {
			
			this.output1.write(pMsg+"\n");
			
			//this.output2.writeChars(pMsg + "\n");
			this.output1.flush();	
			//this.output2.flush();
			
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

	
	
}

