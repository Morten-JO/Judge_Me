package functionality;

import java.awt.Image;

public class Picture {

	Image picture;
	int likes, passes, ID;
	
	Picture(Image picture, int ID){
		this.picture = picture;
		this.ID = ID;
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
