package controller;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerCommsController{
	
	private ServerSocket serverSocket; //socket to connect server with client
	private ExecutorService threadPool; //create multiple threads for different users to use services at the same time
	
	ServerCommsController(int port){
		try{
			serverSocket = new ServerSocket(port); //port should be same as client [9090]
			threadPool = Executors.newCachedThreadPool(); //create threads as needed
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void communicateWithClient() throws IOException{
		try {
			while(true) {
				BackEnd theBackEnd = new BackEnd(serverSocket.accept()); //Creates new BackEnd instance for every new ComController
				threadPool.execute(theBackEnd);
			}
		}catch(Exception e) {
			e.printStackTrace();
			threadPool.shutdown();
		}
	}
}
