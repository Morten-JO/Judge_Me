package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector {
	private Socket s = null; 
	private DataOutputStream os = null; 
	private DataInputStream is = null;
	private BufferedReader in = null;
	
	public Connector() throws IOException{
		try{
	s = new Socket("localhost",8888);
	in = new BufferedReader(new InputStreamReader(s.getInputStream()));
	  
	//os = new DataOutputStream(s.getOutputStream());
	//is = new DataInputStream(s.getInputStream());
		}
		catch(UnknownHostException e){
			System.err.println("Don't know about host: hostname");
		} catch (IOException e){
			  System.err.println("Couldn't get I/O for the connection to: hostname");
		}
}
	private boolean connectionOk(){
		if (s != null && os != null && is != null) return true; 
		else
		return false;
	}
	
	public void login(String username, String password){
		if (connectionOk()){
			try{
				os.writeBytes("login "+username+" "+password+"/r /n");
				String answer = in.readLine();
				if ( answer ==  "ok login"){
					System.out.println("login successful");
				}
				else System.out.println("Bad login, try again");
				
			}
		catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
		}
		
		
	}
		else System.out.println("Connection bad");
	}
	
	public void createUser(String username, String password){
		if (connectionOk()){
			try{
				os.writeBytes("login "+username+" "+password+"/r /n");
				String answer = in.readLine();
				if ( answer ==  "ok create"){
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
	}
}
