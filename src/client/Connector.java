package client;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


import javax.imageio.ImageIO;

import functionality.Picture;

public class Connector {
	private Socket s = null; 
	private DataOutputStream os = null; 
	
	private BufferedReader in = null;
	private Picture pic;
	
	public Connector() throws IOException{
		try{
	s = new Socket("localhost",8888);
	in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	os = new DataOutputStream(s.getOutputStream());

		}
		catch(UnknownHostException e){
			System.err.println("Don't know about host: hostname");
		} catch (IOException e){
			  System.err.println("Couldn't get I/O for the connection to: hostname");
		}
}
	
	
	public boolean login(String username, String password){
		boolean result = false;
			try{
				os.writeBytes("login "+username+" "+password+"\r\n");
				System.out.println("Wrote something");
				String answer = in.readLine();
				if (answer.equals("ok login")){
					System.out.println("login successful");
					result = true;
				}
				else{
					System.out.println("Bad login, try again");
					result = false;
				}
			}
		catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
		}
		
		return result;
	}
	public void createUser(String username, String password, String gender, String email){
		
			try{
				os.writeBytes("create "+username+" "+password+" "+gender+" "+email+"\r\n");
				String answer = in.readLine();
				if (answer ==  "ok create"){
					System.out.println("User is create and login successful");
				}
					else if ( answer == "bad name create") {
						System.out.println("user already exists");
					}
					else if ( answer == "bad password create"){
						System.out.println("password to weak");
					}
						
			}
			catch (IOException e){
				   System.err.println("IOException:  " + e);
			}
		
				}
	
	public void sendMsg (String msg) {
		try {
			os.writeBytes(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Boolean selectMale () {
		Boolean res = false;
		try {
			
			sendMsg("picture boy\r\n");
			String test = in.readLine();
			System.out.println("test: "+test);
			if (test.startsWith("ok")){
				String info = in.readLine();
				res = true;
				int id = Integer.parseInt(info.split(" ")[1]);
				String gender = info.split(" ")[2];
				int likes = Integer.parseInt(info.split(" ")[3]);
				int dislikes = Integer.parseInt(info.split(" ")[4]);
				String des = info.split(" ")[5];
				
				BufferedImage image = ImageIO.read(s.getInputStream());
				pic = new Picture (image,id,gender,likes,dislikes,des);
				in.readLine();
				
			}
			res = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
		
	}
	
	public Boolean selectFemale () {
		boolean res = false;
		try {
			sendMsg("picture girl\r\n");
			String test = in.readLine();
			if (test.startsWith("ok")){
				String info = in.readLine();
				res = true;
				int id = Integer.parseInt(info.split(" ")[1]);
				String gender = info.split(" ")[2];
				int likes = Integer.parseInt(info.split(" ")[3]);
				int dislikes = Integer.parseInt(info.split(" ")[4]);
				String des = info.split(" ")[5];
				
				BufferedImage image = ImageIO.read(s.getInputStream());
				pic = new Picture (image,id,gender,likes,dislikes,des);
				in.readLine(); 
				
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return res;
	}
	
	public boolean likePicture(){
		sendMsg("like "+pic.getID()+"\r\n");
		String res;
		try {
			res = in.readLine();
			if(res.equals("liked ok")){
				System.out.println("Just liked picture: "+pic.getID());
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean dislikePicture(){
		sendMsg("dislike "+pic.getID()+"\r\n");
		String res;
		try {
			res = in.readLine();
			if(res.equals("disliked ok")){
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean uploadPictureBoy (String des, String url){
		try {
			sendMsg("upload boy "+des+"\r\n");
			String result = in.readLine();
			System.out.println(result);
			if(result.equals("ok start send")){
				BufferedImage image = ImageIO.read(new File(url));
				 ImageIO.write(image, "png", s.getOutputStream());
				String sendtres = in.readLine();
				System.out.println(sendtres);
				if (sendtres.equals("ok receive")){
					return true;
				}
			}	
		}
		 catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public Boolean uploadPictureGirl (String des, String url){
		try {
			sendMsg("upload girl "+des+"\r\n");
			String result = in.readLine();
			System.out.println(result);
			if(result.equals("ok start send")){
				BufferedImage image = ImageIO.read(new File(url));
				 ImageIO.write(image, "png", s.getOutputStream());
				String sendtres = in.readLine();
				System.out.println(sendtres);
				if (sendtres.equals("ok receive")){
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
	
	

