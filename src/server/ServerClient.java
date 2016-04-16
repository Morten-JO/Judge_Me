package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClient implements Runnable{

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
	
	
	private class ServerClientReader implements Runnable{

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
	
	private class ServerClientSender implements Runnable{

		private ArrayList<String> toSendMessages;
		private Thread thread;
		private PrintWriter writer;
		private boolean isrunning;
		
		public ServerClientSender(PrintWriter writer){
			this.writer = writer;
			thread = new Thread(this);
			toSendMessages = new ArrayList<String>();
		}
		
		public void startThread(){
			isrunning = true;
			thread.start();
		}
		
		@Override
		public void run() {
			while(isrunning){
				if(toSendMessages.size() > 0){
					for(int i = 0; i < toSendMessages.size(); i++){
						writer.println(toSendMessages.get(0));
						toSendMessages.remove(0);
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
		
		public void addMessage(String s){
			toSendMessages.add(s);
		}
		
		public void sendPicture(ServerPicture picture){
			
		}
		
	}
	
	public ServerClientSender getSender(){
		return sender;
	}
	
	public ServerClientReader getReader(){
		return reader;
	}
	
}
