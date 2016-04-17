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
import functionality.Profile;

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
	
	public Profile profileOfLogin(){
		try {
			os.writeBytes("profiledata\r\n");
			String answer = in.readLine();
			String[] bits = answer.split(" ");
			Profile prof = new Profile(bits[0], bits[2], bits[3]);
			return prof;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String createUser(String username, String password, String gender, String email){
			String back = null;
			try{
				os.writeBytes("create "+username+" "+password+" "+gender+" "+email+"\r\n");
				String answer = in.readLine();
				if (answer.equals("ok create")){
					back ="User is create and login successful";
				}
				else if (answer.equals("bad name create")) {
					back ="user already exists";
				}
				else if (answer.equals("bad password create")){
					back = "password to weak";
				}
						
			}
			catch (IOException e){
				   System.err.println("IOException:  " + e);
			}
			return back;
		
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
			if (test.equals("ok u get picture")){
				String info = in.readLine();
				res = true;
				int id = Integer.parseInt(info.split(" ")[1]);
				String gender = info.split(" ")[2];
				int likes = Integer.parseInt(info.split(" ")[3]);
				int dislikes = Integer.parseInt(info.split(" ")[4]);
				String[] splits = info.split(" ");
				String des = info.substring(5 + splits[0].length() + splits[1].length() + splits[2].length() + splits[3].length() + splits[4].length(), info.length()-17);
				
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
	
	public Boolean selectFemale () {
		boolean res = false;
		try {
			sendMsg("picture girl\r\n");
			String test = in.readLine();
			if (test.equals("ok u get picture")){
				String info = in.readLine();
				res = true;
				int id = Integer.parseInt(info.split(" ")[1]);
				String gender = info.split(" ")[2];
				int likes = Integer.parseInt(info.split(" ")[3]);
				int dislikes = Integer.parseInt(info.split(" ")[4]);
				String[] splits = info.split(" ");
				String des = info.substring(5 + splits[0].length() + splits[1].length() + splits[2].length() + splits[3].length() + splits[4].length(), info.length()-17);
				
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
		
		if (pic != null){
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
		}
		return false;
		
		}
	
	public boolean dislikePicture(){
		if (pic != null){
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
		}
		return false;
	}
	
	public Boolean uploadPictureBoy (String des, String url){
		try {
			System.out.println("firat");
			sendMsg("upload boy "+des+"\r\n");
			System.out.println("second");
			String result = in.readLine();
			System.out.println(result);
			if(result.equals("ok start send")){
				System.out.println("fourth");
				BufferedImage image = ImageIO.read(new File(url));
				System.out.println("five");
				 ImageIO.write(image, "png", s.getOutputStream());
				 System.out.println("six");
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
	public Picture getPicture (){
		return pic;
		
	}
	
	public Picture[] PicturesIds (){
		sendMsg("idprofile\r\n");
		String result = null;
		try {
			result = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Picture[] pics = new Picture[Integer.parseInt(result.split(" ")[0])];
		for (int i = 0; i <  Integer.parseInt(result.split(" ")[0]); i++) {
			String ok = null;
			try {
				ok = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("oki received first");
			if ( ok.equals("ok u get picture")){
				String info = null;
				BufferedImage image = null; 
				try {
					info = in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("oki received second");
				int id = Integer.parseInt(info.split(" ")[1]);
				String gender = info.split(" ")[2];
				int likes = Integer.parseInt(info.split(" ")[3]);
				int dislikes = Integer.parseInt(info.split(" ")[4]);
				String[] splits = info.split(" ");
				String des = info.substring(5 + splits[0].length() + splits[1].length() + splits[2].length() + splits[3].length() + splits[4].length(), info.length() - 17);
				
				
				try {
					image = ImageIO.read(s.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("oki got picture");
				pics[i] = new Picture (image,id,gender,likes,dislikes,des);
				
				try {
					in.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Completed one cycle");
			}
		}
		return pics;
		
	}
}
	
	

