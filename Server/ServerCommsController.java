
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
			pool = Executors.newCachedThreadPool(); //create threads as needed
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void communicateWithClient() throws IOException{
		try {
			while(true) {
				FrontEndedit theStudentFrontEnd = new FrontEndedit(serverSocket.accept()); //Create new FrontEndedit instance for every new client
				pool.execute(FrontEndedit);
			}
		}catch(Exception e) {
			e.printStackTrace();
			pool.shutdown();
		}
		
		
	}
	
}
