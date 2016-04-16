package server;

public class ServerStart {

	public static void main(String[] args) {
		ServerPicture.pictureId = ServerTextFileHandler.getFreeId();
		new ServerRun().startThread();
	}
}
