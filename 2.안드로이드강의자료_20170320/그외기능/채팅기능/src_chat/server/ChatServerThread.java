

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerThread extends Thread {
	private static List<ChatServerThread> threads = new ArrayList<ChatServerThread>();
	private Socket socket;//

	
	public ChatServerThread(Socket socket) {
		
		super();
		this.socket = socket;
		threads.add(this); //연결된 클라이언트의 소켓을 arraylist에 저장한다.
		System.out.println("socket is created");
	}


	public void run() {
		InputStream in = null;
		OutputStream out=null;
		BufferedWriter bw=null;
		PrintWriter pw=null;
		
		String message;
		int size;
		byte[] w = new byte[10240];
		try {
			
in = socket.getInputStream();
			out=socket.getOutputStream();
			bw=new BufferedWriter(new OutputStreamWriter(out));
			pw=new PrintWriter(bw,true);
			pw.println("welcome to connect server!!!");
			
			while (true) {
				try {
					
					size = in.read(w);
					
					if (size <= 0)
						throw new IOException();
				
					message = new String(w, 0, size, "UTF8");
					//클라이언트에게 받은 메시지를 모든 연결된 클라이언트에게 전송한다.
					sendMessageAll(message);
				} catch (IOException e) {
					e.printStackTrace();
					//socket.close();
					threads.remove(this);
					return;
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	
	public void sendMessageAll(String message) {
		ChatServerThread thread;
		for (int i = 0; i < threads.size(); i++) {
			thread = (ChatServerThread) threads.get(i);
			if (thread.isAlive())
				thread.sendMessage(this, message);
		}

	}

	
	public void sendMessage(ChatServerThread talker, String message) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream())), true);
			out.println(message);
			out.flush();
		} catch (IOException e) {
		}
	}
}
