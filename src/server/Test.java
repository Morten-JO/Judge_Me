package server;

import java.io.IOException;
import java.util.ArrayList;

import client.Connector;
import functionality.Picture;

public class Test {

	public static void main(String[] args) {
		try {
			Connector conn = new Connector();
			if(conn.login("kristofer", "testpass")){
				Picture[] pics = conn.PicturesIds();
				System.out.println("Len of pictures: "+pics.length);
				for(int i = 0; i < pics.length; i++){
					if(pics[i] != null) {
						System.out.println("Pics "+i+" is not null");
						System.out.println(pics[i].getID());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
