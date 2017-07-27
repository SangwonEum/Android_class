

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

class ChatClient extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea edtView;
	private JTextField edtSend;
	private Socket socket;
	private InputStream in;

	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.setVisible(true);
		client.connect("192.168.0.100", 6001);
	}
	
	public ChatClient() {
		
		setSize(300, 300);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		
		edtView = new JTextArea("", 10, 25);
		edtView.setEditable(false);
		pane.add(new JScrollPane(edtView,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));//

		
		edtSend = new JTextField("", 25);
		pane.add(edtSend);
		edtSend.addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent ae) {
		try {
			if (socket != null && socket.isConnected()) {
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true); //
				out.println("admin : " + edtSend.getText());
				out.flush();
				edtSend.setText("");
			}
		} catch (Exception e) {
		}
	}

	
	public void connect(String host, int port) {
		String text;
		int size;
		byte[] w = new byte[10240];
		try {
			
			InetAddress addr = InetAddress.getByName(host);
			socket = new Socket(addr, port);
			in = socket.getInputStream();

			while (socket != null && socket.isConnected()) {
				size = in.read(w);
				if (size <= 0)
					continue;
				text = new String(w, 0, size, "UTF8");
				edtView.append(text + "\n");
			}
		} catch (UnknownHostException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	
	public void append_edtView(String mess) {
		edtView.append(mess);
		edtView.setCaretPosition(edtView.getText().length());
	}

	
}
