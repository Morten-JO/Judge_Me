package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerRun implements Runnable{
	//serverrun handles incoming connections
	private boolean serverListening;
	private ServerSocket mainSocket;
	private Thread thread;
	private ArrayList<ServerClient> clients;
	
	public ServerRun(){
		try {
			serverListening = true;
			mainSocket = new ServerSocket(8888);
			clients = new ArrayList<ServerClient>();
		} catch (IOException e) {
			System.out.println("Server already up, exitting program.");
			System.exit(0);
		}
		thread = new Thread(this);
	}
	
	public void startThread(){
		thread.start();
	}
	
	@Override
	public void run() {
		while(serverListening){
			try {
				Socket sock = mainSocket.accept();
				BufferedReader reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
				clients.add(new ServerClient(sock, reader, writer));
				System.out.println("Client added with IP: "+sock.getRemoteSocketAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
