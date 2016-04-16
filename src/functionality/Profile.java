package functionality;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	String profileName, password, gender, mail;
	List<Picture> pictures = new ArrayList<Picture>();
	List<Integer> reviewedPictureIDs =  new ArrayList<Integer>();
	
	public Profile(String profileName, String gender, String mail){
		this.profileName = profileName;
		this.gender = gender;
		this.mail = mail;
	}
	
	public String getProfileName(){
		return profileName;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String getMail(){
		return mail;
	}
	
	public List<Picture> getPictures(){
		return pictures;
	}
	
	public List<Integer> getPictureIDs(){
		List<Integer> pictureIDs =  new ArrayList<Integer>();
		for(Picture picture : pictures){
			pictureIDs.add(picture.getID());
		}
		return pictureIDs;
	}
	
	public List<Integer> getReviewedPictureIDs(){
		return reviewedPictureIDs;
	}
	
}
