package server;

import java.awt.image.BufferedImage;

public class ServerPicture {
	
	private BufferedImage img;
	private int id;
	private String description;
	
	public ServerPicture(BufferedImage img, int id, String description){
		this.img = img;
		this.id = id;
		this.description = description;
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
	
}
