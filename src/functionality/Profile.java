package functionality;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Profile {
	
	String profileName, password, gender, mail;
	Image profilePicture;
	List<Picture> pictures = new ArrayList<Picture>();
	List<Integer> reviewedPictureIDs =  new ArrayList<Integer>();
	
	Profile(String profileName, Image profilePicture, String gender, String mail){
		this.profileName = profileName;
		this.profilePicture = profilePicture;
		this.gender = gender;
		this.mail = mail;
	}
	
	String getProfileName(){
		return profileName;
	}
	
	Image getProfilePicture(){
		return profilePicture;
	}
	
	String getGender(){
		return gender;
	}
	
	String getMail(){
		return mail;
	}
	
	List<Picture> getPictures(){
		return pictures;
	}
	
	List<Integer> getPictureIDs(){
		List<Integer> pictureIDs =  new ArrayList<Integer>();
		for(Picture picture : pictures){
			pictureIDs.add(picture.getID());
		}
		return pictureIDs;
	}
	
	List<Integer> getReviewedPictureIDs(){
		return reviewedPictureIDs;
	}
	
}
