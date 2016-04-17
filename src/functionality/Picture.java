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
	
	public String getDes(){
		return description;
	}
	
	public int getID(){
		return ID;
	}
	
	public int getLikes(){
		return likes;
	}
	
	public int getPasses(){
		return passes;
	}
	
	public void increaseLikes(){
		likes++;
	}
	
	public void increasePasses(){
		passes++;
	}
	
	public BufferedImage getImage(){
		return picture;
	}
	
}
