package server;

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
					client.getSender().addMessage("ok login");
				}
				else{
					client.getSender().addMessage("bad login");
				}
			}
		}
		else if(message.startsWith("create")){
			
		}
		else if(message.startsWith("picture")){
			if(message.contains("boy")){
				
			}
			else{
				
			}
		}
	}
	
}
