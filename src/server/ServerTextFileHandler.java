package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ServerTextFileHandler {

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
	
	public void writeToServerPictureFile(ServerPicture picture){
		
	}
	
	public void writeToServerDataFile(ServerProfile profile){
		String str = profile.getName()+" "+profile.getPassword()+" "+profile.getGender()+" "+profile.getEmail();
		for(int i = 0; i < profile.getReviewedIds().size(); i++){
			str += " "+profile.getReviewedIds().get(i);
		}
		try {
			writer.write(str+"\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
