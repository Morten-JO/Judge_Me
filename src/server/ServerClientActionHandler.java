package server;

import java.awt.image.BufferedImage;

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
				ServerTextFileHandler file = new ServerTextFileHandler(ServerTextFileHandler.userDataPath+parts[1]+".txt");
				ServerProfile prof = file.readProfile();
				if(prof.getName().equals(parts[1]) && prof.getPassword().equals(parts[2])){
					client.setProfile(prof);
					client.getSender().fastSend("ok login");
				}
				else{
					client.getSender().fastSend("bad login");
				}
			}
			else{
				client.getSender().fastSend("bad login");
			}
		}
		else if(message.startsWith("create")){
			String[] parts = message.split(" ");
			if(!ServerTextFileHandler.UserFileExist(parts[1])){
				ServerTextFileHandler file = new ServerTextFileHandler(ServerTextFileHandler.userDataPath+parts[1]+".txt");
				ServerProfile prof = new ServerProfile(parts[1], parts[2], parts[3], parts[4]);
				if(parts[2].length() < 6){
					client.getSender().fastSend("bad password create");
				}else if(file.writeToServerDataFile(prof)){
					client.getSender().fastSend("ok create");
				}
				else{
					client.getSender().fastSend("bad name create");
				}
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
				ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imageBoypath+idToReview+".png");
				ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
				hand.readServerPicture(pic);
				hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+idToReview+".txt");
				hand.readServerPictureData(pic);
				if(freereview){
					client.getSender().fastSend("ok u get picture");
					client.getSender().fastSend("pictureinformation "+pic.getId()+" "+pic.getGender()+" "+pic.getLikes()+" "+pic.getDislikes()+" "+pic.getDescription()+" startpicturesend");
					client.getSender().sendPicture(pic.getImage(), "png", client.getSocket());
					client.getSender().fastSend("endpicturesend");
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
				ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imageGirlpath+idToReview+".png");
				ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
				hand.readServerPicture(pic);
				hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+idToReview+".txt");
				hand.readServerPictureData(pic);
				if(freereview){
					client.getSender().fastSend("ok u get picture");
					client.getSender().fastSend("pictureinformation "+pic.getId()+" "+pic.getGender()+" "+pic.getLikes()+" "+pic.getDislikes()+" "+pic.getDescription()+" startpicturesend");
					client.getSender().sendPicture(pic.getImage(), "png", client.getSocket());
					client.getSender().fastSend("endpicturesend");
				}
				else{
					client.getSender().fastSend("ok u get no picture");
				}
			}
		}
		else if(message.startsWith("upload")){
			String[] mes = message.split(" ");
			if(mes[1].equals("boy")){
				ServerPicture prof = new ServerPicture(null, ServerPicture.pictureId, "", 0, 0, "male");
				String desc = message.substring(2+mes[0].length()+mes[1].length(), message.length());
				prof.setDescription(desc);
				client.getSender().fastSend("ok start send");
				BufferedImage img = client.getReader().receivePicture(client.getSocket());
				if(img != null){
					prof.setImg(img);
					client.getSender().fastSend("ok receive");
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+prof.getId()+".txt");
					hand.writeToSeverPictureData(prof);
					hand = new ServerTextFileHandler(ServerTextFileHandler.imageBoypath+prof.getId()+".png");
					hand.writeToServerPicture(prof, "png");
					client.getSender().fastSend("ok receive");
					ServerPicture.pictureId++;
				}
				else{
					client.getSender().fastSend("fail receive");
				}
			}
			else{
				ServerPicture prof = new ServerPicture(null, ServerPicture.pictureId, "", 0, 0, "female");
				String desc = message.substring(2+mes[0].length()+mes[1].length(), message.length());
				prof.setDescription(desc);
				client.getSender().fastSend("ok start send");
				BufferedImage img = client.getReader().receivePicture(client.getSocket());
				if(img != null){
					prof.setImg(img);
					client.getSender().fastSend("ok receive");
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+prof.getId()+".txt");
					hand.writeToSeverPictureData(prof);
					hand = new ServerTextFileHandler(ServerTextFileHandler.imageGirlpath+prof.getId()+".png");
					hand.writeToServerPicture(prof, "png");
					client.getSender().fastSend("ok receive");
					ServerPicture.pictureId++;
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
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+id+".txt");
					ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
					hand.readServerPictureData(pic);
					pic.setLikes(pic.getLikes()+1);
					hand.writeToSeverPictureData(pic);
					client.getSender().fastSend("liked ok");
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
					ServerTextFileHandler hand = new ServerTextFileHandler(ServerTextFileHandler.imgDataPath+id+".txt");
					ServerPicture pic = new ServerPicture(null, 0, null, 0, 0, null);
					hand.readServerPictureData(pic);
					pic.setLikes(pic.getLikes()-1);
					hand.writeToSeverPictureData(pic);
					client.getSender().fastSend("disliked ok");
				}
				else{
					client.getSender().fastSend("disliked fail");
				}
			} catch (Exception e){
				client.getSender().fastSend("disliked fail");
			}
		}
	}
	
}
