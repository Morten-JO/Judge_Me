package server;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		System.out.println(ServerTextFileHandler.UserFileExist("kristofer"));
		System.out.println(ServerTextFileHandler.PictureExist(1));
		System.out.println(ServerTextFileHandler.PictureDataExists(1));
	}
	
	

}
