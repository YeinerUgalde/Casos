package Server.PlayerServer;

import java.io.IOException;

import Server.Comunication.SocketServer;

public class Server {

	public static void main(String[] args) throws Exception {
		SocketServer server = new SocketServer();
		server.startListening();
		
		
		while (true) {
			try {
				
				
				//System.out.print("Mensaje servidor\n");
				//server.sendMsg("Mensaje Servidor");
				if (server.input1!= null) {
					//String mensajeC = server.input1.readLine();
					String mensajeC1 = server.input1.readLine();
					if (server.output2 != null) {
						server.sendMsg2(mensajeC1);
					}
					
					System.out.println(mensajeC1);
				}
				if (server.input2!= null) {
					//String mensajeC = server.input1.readLine();
					String mensajeC2 = server.input2.readLine();
					if (server.output1 != null) {
						server.sendMsg1(mensajeC2);
					}
					System.out.println(mensajeC2);
				}
				
		
				Thread.sleep(2);
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.print("Error servidor");
			}
		}
	}

}
