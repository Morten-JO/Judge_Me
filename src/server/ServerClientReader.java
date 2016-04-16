package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ServerClientReader implements Runnable{

	private ArrayList<String> receivedMessages;
	private Thread thread;
	private BufferedReader reader;
	private boolean isrunning;
	
	public ServerClientReader(BufferedReader reader){
		this.reader = reader;
		thread = new Thread(this);
		receivedMessages = new ArrayList<String>();
	}
	
	public void startThread(){
		isrunning = true;
		thread.start();
	}
	
	@Override
	public void run() {
		while(isrunning){
			try {
				receivedMessages.add(reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String readOldest(){
		String s = receivedMessages.get(0);
		receivedMessages.remove(0);
		return s;
	}
	
	public ArrayList<String> getMessages(){
		return receivedMessages;
	}
	
	public ServerPicture receivePicture(){
		return null;
	}
}
