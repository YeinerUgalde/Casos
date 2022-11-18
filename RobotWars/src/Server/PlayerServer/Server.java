package Server.PlayerServer;

import java.io.IOException;

import Server.Comunication.SocketServer;

public class Server {

	public static void main(String[] args) throws Exception {
		SocketServer server = new SocketServer();
		server.startListening();
		String mensajeC1 = "";
		String mensajeC2 = "";
		
		Boolean orden = false;
		String c1 = "";
		while (true) {
			try {
				
				
				//System.out.print("Mensaje servidor\n");
				//server.sendMsg("Mensaje Servidor");
				if (server.input1!= null) {
					//String mensajeC = server.input1.readLine();
					mensajeC1 = server.input1.readLine();
					
				
					//System.out.println(mensajeC1);
					if (server.output2 != null && mensajeC2 != "") {
						if(c1 != "") {
							server.sendMsg2(c1);
							c1 = "";
							
						}
						server.sendMsg2(mensajeC1);
						System.out.println(mensajeC1);
						
						
					}
					if(!orden) {
						c1 = "0,0:::::1";
						orden = true;
					}
					
				}
				if (server.input2!= null ) {
					//String mensajeC = server.input1.readLine();
					mensajeC2 = server.input2.readLine();
					//System.out.println(mensajeC1);
					if (server.output1 != null && mensajeC1!= "") {
						server.sendMsg1(mensajeC2);
						System.out.println(mensajeC2);
					}
					
				}
				
		
				Thread.sleep(1);
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("Error servidor");
			}
		}
	}

}
