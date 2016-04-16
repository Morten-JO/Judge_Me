package server;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ServerClientSender implements Runnable{

	private ArrayList<String> toSendMessages;
	private Thread thread;
	private PrintWriter writer;
	private boolean isrunning;
	private boolean sendingPicture = false;
	
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
			while(sendingPicture){
			}
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
	
	public void sendPicture(BufferedImage picture, String type, Socket socket){
		try {
			sendingPicture = true;
			ImageIO.write(picture, type, socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		sendingPicture = false;
	}
	
	public void fastSend(String s){
		writer.println(s);
	}
	
}