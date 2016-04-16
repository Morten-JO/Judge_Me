package functionality;

import java.awt.Image;

public class Picture {

	Image picture;
	int likes, passes, ID;
	String gender;
	
	Picture(Image picture, int ID, String gender){
		this.picture = picture;
		this.ID = ID;
		this.gender = gender;
	}
	
	int getID(){
		return ID;
	}
	
	int getLikes(){
		return likes;
	}
	
	int getPasses(){
		return passes;
	}
	
	void increaseLikes(){
		likes++;
	}
	
	void increasePasses(){
		passes++;
	}
	
}
