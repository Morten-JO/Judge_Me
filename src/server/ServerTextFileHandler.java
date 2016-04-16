package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ServerTextFileHandler {

	public static String userDataPath = "serverdata/";
	public static String imgDataPath = "serverimgdata/";
	public static String imagepath = "serverimgs/";
	
	private File file;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public ServerTextFileHandler(String path){
		try{
			file = new File(path);
			reader = new BufferedReader(new FileReader(file));
			writer = new BufferedWriter(new FileWriter(file));
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public boolean writeToServerPicture(ServerPicture picture, String type){
		try {
			ImageIO.write(picture.getImage(), type, file);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeToSeverPictureData(ServerPicture picture){
		String str = picture.getId()+" "+picture.getDescription();
		try {
			writer.write(str+"\r\n");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean writeToServerDataFile(ServerProfile profile){
		String str = profile.getName()+" "+profile.getPassword()+" "+profile.getGender()+" "+profile.getEmail();
		for(int i = 0; i < profile.getReviewedIds().size(); i++){
			str += " "+profile.getReviewedIds().get(i);
		}
		try {
			writer.write(str+"\r\n");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ServerProfile readProfile(){
		try {
			String str = reader.readLine();
			String[] parts = str.split(" ");
			ServerProfile prof = new ServerProfile(parts[0], parts[1], parts[2], parts[3]);
			for(int i = 4; i < parts.length; i++){
				prof.addReviewPictureID(Integer.parseInt(parts[i]));
			}
			return prof;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean readServerPictureData(ServerPicture picture){
		try {
			String str = reader.readLine();
			String[] parts = str.split(" ");
			picture.setId(Integer.parseInt(parts[0]));
			picture.setDescription(str.substring(parts[0].length()+1, str.length()));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean readServerPicture(ServerPicture picture){
		try {
			picture.setImg(ImageIO.read(file));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean UserFileExist(String name){
		return new File("serverdata/"+name+".txt").exists();
	}
	
	public static boolean PictureExist(int id){
		return new File("serverimgs/"+id+".png").exists();
	}
	
	public static boolean PictureDataExists(int id){
		return new File("serverimgdata/"+id+".txt").exists();
	}
}
