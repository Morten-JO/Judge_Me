package server;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ServerClientReader implements Runnable{

	private ArrayList<String> receivedMessages;
	private Thread thread;
	private BufferedReader reader;
	private boolean isrunning;
	private boolean isReceivingPicture = false;
	
	public ServerClientReader(BufferedReader reader){
		this.reader = reader;
		thread = new Thread(this);
		receivedMessages = new ArrayList<String>();
	}
	
	public void startThread(){
		isrunning = true;
		thread.start();
	}
	
	public void close(){
		isrunning = false;
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(isrunning){
			while(isReceivingPicture){
				
			}
			try {
				if(reader.ready()){
					receivedMessages.add(reader.readLine());
				}
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
	
	public BufferedImage receivePicture(Socket socket){
		isReceivingPicture = true;
		BufferedImage img = null;
		try {
			img = ImageIO.read(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		isReceivingPicture = false;
		return img;
	}
}
