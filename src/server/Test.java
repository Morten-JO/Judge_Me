package server;

import java.io.IOException;
import java.util.ArrayList;

import client.Connector;

public class Test {

	public static void main(String[] args) {
		try {
			Connector conn = new Connector();
			if(conn.login("kristofer", "testpass")){
				System.out.println("logged in!");
	//			conn.UploadPictureBoy("suck it fransie", "C:/Users/Morten/Documents/ClassDiagram_CDIO_1.png");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
