package server;

import java.awt.image.BufferedImage;

public class ServerPicture {
	public static int pictureId;
	private BufferedImage img;
	private int id;
	private int likes, dislikes;
	private String gender;
	private String description;
	
	public ServerPicture(BufferedImage img, int id, String description, int likes, int dislikes, String gender){
		this.img = img;
		this.id = id;
		this.description = description;
		this.likes = likes;
		this.dislikes = dislikes;
		this.gender = gender;
	}
	
	public BufferedImage getImage(){
		return img;
	}
	
	public int getId(){
		return id;
	}
	
	public String getDescription(){
		return description;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
