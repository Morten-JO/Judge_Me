package server;

import java.util.ArrayList;

public class ServerProfile {
	
	private String name, password, gender, email;
	
	private ArrayList<Integer> ownUploadedPictureIds = new ArrayList<Integer>();
	private ArrayList<Integer> reviewedPictureIDs =  new ArrayList<Integer>();
	
	public ServerProfile(String name, String password, String gender, String email){
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.email = email;
	}
	
	public void addReviewPictureID(int id){
		reviewedPictureIDs.add(id);
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
	

	public ArrayList<Integer> getReviewedPictureIDs() {
		return reviewedPictureIDs;
	}

	public void setReviewedPictureIDs(ArrayList<Integer> reviewedPictureIDs) {
		this.reviewedPictureIDs = reviewedPictureIDs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void addOwnUploadedId(int i){
		ownUploadedPictureIds.add(i);
	}
	
	public ArrayList<Integer> getOwnUploadedArrayList(){
		return ownUploadedPictureIds;
	}
	
	
}
