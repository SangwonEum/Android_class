

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static void main(String[] args) {
		ChatServer server = new ChatServer();
		server.start(6001);
	}
	public void start(int port) {
		ServerSocket server;// 
		Socket socket;
		ChatServerThread thread;

		try {
			server = new ServerSocket(port);
			System.err.println("server started in port " + port);
			while (true) {
				try {
				
					socket = server.accept();
					System.out.println("a client is connected");
					
					thread = new ChatServerThread(socket);
					thread.start();
				} catch (IOException e) {
				}
			}
		} catch (IOException e) {
			
		}
	}

	
}
