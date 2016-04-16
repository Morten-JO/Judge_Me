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
	public static String imageBoypath = "serverimgs/";
	public static String imageGirlpath = "serverimgsfemale/";
	
	private File file;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public ServerTextFileHandler(String path, boolean write){
		try{
			file = new File(path);
			if(write){
				writer = new BufferedWriter(new FileWriter(file));
			}
			else{
				reader = new BufferedReader(new FileReader(file));
			}
			
			
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
		String str = picture.getId()+" "+picture.getLikes()+" "+picture.getDislikes()+" "+picture.getGender()+" "+picture.getDescription();
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
		for(int i = 0; i < profile.getReviewedPictureIDs().size(); i++){
			str += " "+profile.getReviewedPictureIDs().get(i);
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
			picture.setLikes(Integer.parseInt(parts[1]));
			picture.setDislikes(Integer.parseInt(parts[2]));
			picture.setGender(parts[3]);
			picture.setDescription(str.substring(parts[0].length()+4+parts[1].length()+parts[2].length()+parts[3].length(), str.length()));
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
		boolean tr = new File("serverimgs/"+id+".png").exists();
		if(!tr){
			tr = new File("serverimgsfemale/"+id+".png").exists();
		}
		return tr;
	}
	
	public static boolean PictureDataExists(int id){
		return new File("serverimgdata/"+id+".txt").exists();
	}
	
	public static int[] getListOfBoyIds(){
		File[] listOfFiles = new File("serverimgs/").listFiles();
		if(listOfFiles.length == 0){
			return null;
		}
		int[] ids = new int[listOfFiles.length];
		int i = 0;
		for(File file : listOfFiles) {
			if(file.isFile()){
				ids[i++] = Integer.parseInt(file.getName().substring(0, file.getName().length()-4));
			}
		}
		return ids;
	}
	
	public static int[] getListOfGirlIds(){
		File[] listOfFiles = new File("serverimgsfemale/").listFiles();
		if(listOfFiles.length == 0){
			return null;
		}
		int[] ids = new int[listOfFiles.length];
		int i = 0;
		for(File file : listOfFiles) {
			if(file.isFile()){
				ids[i++] = Integer.parseInt(file.getName().substring(0, file.getName().length()-4));
			}
		}
		return ids;
	}
	
	public static int getFreeId(){
		File[] listOfFiles = new File("serverimgdata/").listFiles();
		if(listOfFiles.length == 0){
			return 1;
		}
		int i = 0;
		int freeId = 1;
		for(File file : listOfFiles) {
			if(file.isFile()){
				freeId++;
			}
		}
		return freeId;
	}
	
	public void close(){
		if(writer == null){
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
