package server;

import java.net.Socket;

public class ServerClientActionHandler {
	
	private ServerClient client;
	
	public ServerClientActionHandler(ServerClient client){
		this.client = client;
	}
	
	public void HandleAction(){
		String message = client.getReader().readOldest();
		if(message.startsWith("login")){
			
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
