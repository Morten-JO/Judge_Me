package server;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ServerClientActionHandler {
	
	private ServerClient client;
	
	public ServerClientActionHandler(ServerClient client){
		this.client = client;
	}
	
	public void HandleAction(){
		String message = client.getReader().readOldest();
		if(message.startsWith("login")){
			String[] parts = message.split(" ");
			if(ServerTextFileHandler.UserFileExist(parts[1])){
				ServerTextFileHandler file = new ServerTextFileHandler(ServerTextFileHandler.userDataPath+parts[1]+".txt", false);
				ServerProfile prof = file.readProfile();
				if(prof.getName().equals(parts[1]) && prof.getPassword().equals(parts[2])){
					client.setProfile(prof);
					client.getSender().fastSend("ok login");
				}
				else{
					client.getSender().fastSend("bad login");
				}
				file.close();
			}
			else{
				client.getSender().fastSend("bad login");
			}
		}
		else if(message.startsWith("create")){
			String[] parts = message.split(" ");
			if(!ServerTextFileHandler.UserFileExist(parts[1])){
				ServerTextFileHandler file = new ServerTextFileHandler(ServerTextFileHandler.userDataPath+parts[1]+".txt", true);
				ServerProfile prof = new ServerProfile(parts[1], parts[2], parts[3], parts[4]);
				if(parts[2].length() < 6){
					client.getSender().fastSend("bad password create");
				}else if(file.writeToServerDataFile(prof)){
					client.getSender().fastSend("ok create");
				}
				else{
					client.getSender().fastSend("bad name create");
				}
				file.close();
			}
			else{
				client.getSender().fastSend("bad name create");
			}
		}
		else if(message.startsWith("picture")){
			if(client.getProfile() == null){
				client.getSender().addMessage("not logged in");
			} else if(message.contains("boy")){
				int[] ids = ServerTextFileHandler.getListOfBoyIds();
				boolean freereview = false;
				int idToReview = 0;
				for(int i = 0; i < ids.length; i++){
					boolean reviewed = false;
					for(int x = 0; x < client.getProfile().getReviewedPictureIDs().size(); x++){
						if(client.getProfile().getReviewedPictureIDs().get(x) == ids[i]){
							reviewed = true;
						}
					}
					if(!reviewed){
						freereview = true;
						idToReview = ids[i];
						break;
					}
				}
				if(freereview){
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imageBoypath+idToReview+".png", false);
					ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
					hand.readServerPicture(pic);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+idToReview+".txt", false);
					hand.readServerPictureData(pic);
					client.getSender().fastSend("ok u get picture");
					client.getSender().fastSend("pictureinformation "+pic.getId()+" "+pic.getGender()+" "+pic.getLikes()+" "+pic.getDislikes()+" "+pic.getDescription()+" startpicturesend");
					client.getSender().sendPicture(pic.getImage(), "png", client.getSocket());
					client.getSender().fastSend("endpicturesend");
					hand.close();
				}
				else{
					client.getSender().fastSend("ok u get no picture");
				}
				
			}
			else if(message.contains("girl")){
				int[] ids = ServerTextFileHandler.getListOfGirlIds();
				boolean freereview = false;
				int idToReview = 0;
				for(int i = 0; i < ids.length; i++){
					boolean reviewed = false;
					for(int x = 0; x < client.getProfile().getReviewedPictureIDs().size(); x++){
						if(client.getProfile().getReviewedPictureIDs().get(x) == ids[i]){
							reviewed = true;
						}
					}
					if(!reviewed){
						freereview = true;
						idToReview = ids[i];
						break;
					}
				}
				if(freereview){
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imageGirlpath+idToReview+".png", false);
					ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
					hand.readServerPicture(pic);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+idToReview+".txt", false);
					hand.readServerPictureData(pic);
					client.getSender().fastSend("ok u get picture");
					client.getSender().fastSend("pictureinformation "+pic.getId()+" "+pic.getGender()+" "+pic.getLikes()+" "+pic.getDislikes()+" "+pic.getDescription()+" startpicturesend");
					client.getSender().sendPicture(pic.getImage(), "png", client.getSocket());
					client.getSender().fastSend("endpicturesend");
					hand.close();
				}
				else{
					client.getSender().fastSend("ok u get no picture");
				}
			}
		}
		else if(message.startsWith("upload")){
			System.out.println("first thing");
			String[] mes = message.split(" ");
			if(mes[1].equals("boy")){
				System.out.println("got into boy");
				ServerPicture prof = new ServerPicture(null, ServerPicture.pictureId, "", 0, 0, "male");
				String desc = message.substring(2+mes[0].length()+mes[1].length(), message.length());
				prof.setDescription(desc);
				System.out.println("before start");
				client.getSender().fastSend("ok start send");
				System.out.println("sent start");
				BufferedImage img = client.getReader().receivePicture(client.getSocket());
				if(img != null){
					System.out.println("IMAGE IS NOT NULL");
					prof.setImg(img);
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+prof.getId()+".txt", true);
					hand.writeToSeverPictureData(prof);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.imageBoypath+prof.getId()+".png", true);
					hand.writeToServerPicture(prof, "png");
					client.getSender().fastSend("ok receive");
					ServerPicture.pictureId++;
					hand.close();
				}
				else{
					System.out.println("IMAGE IS NULL");
					client.getSender().fastSend("fail receive");
				}
			}
			else{
				System.out.println("got into girl");
				ServerPicture prof = new ServerPicture(null, ServerPicture.pictureId, "", 0, 0, "female");
				String desc = message.substring(2+mes[0].length()+mes[1].length(), message.length());
				prof.setDescription(desc);
				client.getSender().fastSend("ok start send");
				BufferedImage img = client.getReader().receivePicture(client.getSocket());
				if(img != null){
					prof.setImg(img);
					client.getSender().fastSend("ok receive");
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+prof.getId()+".txt", true);
					hand.writeToSeverPictureData(prof);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.imageGirlpath+prof.getId()+".png", true);
					hand.writeToServerPicture(prof, "png");
					client.getSender().fastSend("ok receive");
					ServerPicture.pictureId++;
					hand.close();
				}
				else{
					client.getSender().fastSend("fail receive");
				}
			}
		}
		else if(message.startsWith("like")){
			String[] mes = message.split(" ");
			try{
				int id = Integer.parseInt(mes[1]);
				boolean isAlreadyReviewed = false;
				for(int i = 0; i < client.getProfile().getReviewedPictureIDs().size(); i++){
					if(id == client.getProfile().getReviewedPictureIDs().get(i)){
						isAlreadyReviewed = true;
						break;
					}
				}
				if(!isAlreadyReviewed){
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+id+".txt", false);
					ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
					hand.readServerPictureData(pic);
					pic.setLikes(pic.getLikes()+1);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+id+".txt", true);
					hand.writeToSeverPictureData(pic);
					client.getSender().fastSend("liked ok");
					client.getProfile().addReviewPictureID(id);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.userDataPath+client.getProfile().getName()+".txt", true);
					hand.writeToServerDataFile(client.getProfile());
					hand.close();
				}
				else{
					client.getSender().fastSend("liked fail");
				}
			} catch (Exception e){
				client.getSender().fastSend("liked fail");
			}
		}
		else if(message.startsWith("dislike")){
			String[] mes = message.split(" ");
			try{
				int id = Integer.parseInt(mes[1]);
				boolean isAlreadyReviewed = false;
				for(int i = 0; i < client.getProfile().getReviewedPictureIDs().size(); i++){
					if(id == client.getProfile().getReviewedPictureIDs().get(i)){
						isAlreadyReviewed = true;
						break;
					}
				}
				if(!isAlreadyReviewed){
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+id+".txt", false);
					ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
					hand.readServerPictureData(pic);
					pic.setLikes(pic.getLikes()-1);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+id+".txt", true);
					hand.writeToSeverPictureData(pic);
					client.getProfile().addReviewPictureID(id);
					client.getSender().fastSend("disliked ok");
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.userDataPath+client.getProfile().getName()+".txt", true);
					hand.writeToServerDataFile(client.getProfile());
					hand.close();
				}
				else{
					client.getSender().fastSend("disliked fail");
				}
			} catch (Exception e){
				client.getSender().fastSend("disliked fail");
			}
		}
		else if(message.startsWith("idprofile")){
			client.getSender().fastSend(client.getProfile().getOwnUploadedArrayList().size()+" pictures");
			for(int i = 0; i < client.getProfile().getOwnUploadedArrayList().size(); i++){
				if(ServerTextFileHandler.PictureExist(client.getProfile().getOwnUploadedArrayList().get(i))){
					String firstpath = "";
					if(ServerTextFileHandler.PictureIsGirl(client.getProfile().getOwnUploadedArrayList().get(i))){
						firstpath = ServerTextFileHandler.imageGirlpath;
					}
					else{
						firstpath = ServerTextFileHandler.imageBoypath;
					}
					ServerTextFileHandler hand = new ServerTextFileHandler(firstpath+client.getProfile().getOwnUploadedArrayList().get(i)+".png", false);
					ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
					hand.readServerPicture(pic);
					hand.close();
					hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+client.getProfile().getOwnUploadedArrayList().get(i)+".txt", false);
					hand.readServerPictureData(pic);
					client.getSender().fastSend("ok u get picture");
					client.getSender().fastSend("pictureinformation "+pic.getId()+" "+pic.getGender()+" "+pic.getLikes()+" "+pic.getDislikes()+" "+pic.getDescription()+" startpicturesend");
					client.getSender().sendPicture(pic.getImage(), "png", client.getSocket());
					client.getSender().fastSend("endpicturesend");
					hand.close();
				}
			}
		}
		else if(message.startsWith("profiledata")){
			if(client != null){
				client.getSender().fastSend(client.getProfile().getName()+ " "+client.getProfile().getPassword()+" "+client.getProfile().getGender()+" "+client.getProfile().getEmail());
			}
			else{
				client.getSender().fastSend("fail profile data");
			}
		}
	}
	
}
