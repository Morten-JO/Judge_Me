package functionality;

import java.awt.image.BufferedImage;

public class Picture {

	BufferedImage picture;
	int likes, passes, ID;
	String gender,description;
	
	public Picture(BufferedImage picture, int ID, String gender, int likes, int passes, String des){
		this.picture = picture;
		this.ID = ID;
		this.gender = gender;
		this.likes = likes;
		this.passes = passes;
		this.description = des;
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
	
	public BufferedImage getImage(){
		return picture;
	}
	
}
