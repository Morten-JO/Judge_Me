package server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClient implements Runnable{

	private ServerProfile profile;
	private Thread thread;
	private boolean isRunning;
	private Socket socket;
	private ServerClientReader reader;
	private ServerClientSender sender;
	
	public ServerClient(Socket socket, BufferedReader reader, PrintWriter writer){
		this.socket = socket;
		this.reader = new ServerClientReader(reader);
		this.sender = new ServerClientSender(writer);
		this.thread = new Thread(this);
	}
	
	public void startThread(){
		reader.startThread();
		sender.startThread();
		isRunning = true;
		thread.run();
	}

	@Override
	public void run() {
		while(isRunning){
			if(reader.getMessages().size() > 0){
				for(int i = 0; i < reader.getMessages().size(); i++){
					System.out.println("Message #"+i+" : "+reader.getMessages().get(i));
				}
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
	
	public void setProfile(){
		
	}
	
}
