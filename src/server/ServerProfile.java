package server;

import java.util.ArrayList;
import java.util.List;

public class ServerProfile {
	
	private String name, password, gender, email;
	private ArrayList<Integer> reviewedPictureIDs =  new ArrayList<Integer>();
	
	public ServerProfile(){
		
	}
	
	public String getName(){
		return name;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String getEmail(){
		return email;
	}
	
	public ArrayList<Integer> getReviewedIds(){
		return reviewedPictureIDs;
	}
	
}
