package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClient implements Runnable{

	private ServerProfile profile;
	private Thread thread;
	private boolean isRunning;
	private Socket socket;
	private ServerClientReader reader;
	private ServerClientSender sender;
	private ServerClientActionHandler handler;
	
	public ServerClient(Socket socket, BufferedReader reader, PrintWriter writer){
		this.socket = socket;
		this.reader = new ServerClientReader(reader);
		this.sender = new ServerClientSender(writer);
		this.thread = new Thread(this);
		handler = new ServerClientActionHandler(this);
	}
	
	public void startThread(){
		reader.startThread();
		System.out.println("o");
		sender.startThread();
		System.out.println("wot");
		isRunning = true;
		System.out.println("ok");
		thread.start();
		System.out.println("oh");
	}
	
	public void closeEverything(){
		reader.close();
		sender.close();
		isRunning = false;
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.userDataPath, true);
		hand.writeToServerDataFile(this.profile);
		
	}

	@Override
	public void run() {
		while(isRunning){
			if(reader.getMessages().size() > 0){
				handler.HandleAction();
			}
			else{
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public ServerClientSender getSender(){
		return sender;
	}
	
	public ServerClientReader getReader(){
		return reader;
	}
	
	public void setProfile(ServerProfile prof){
		this.profile = prof;
	}
	
	public ServerProfile getProfile(){
		return profile;
	}
	
	public Socket getSocket(){
		return socket;
	}
	
}
