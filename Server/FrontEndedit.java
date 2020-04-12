import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FrontEndedit implements Runnable{
	
	private RegistrationApp regApp;
	private Socket aSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	
	
	public FrontEndedit(Socket s) {
		regApp = new RegistrationAppEdit();
		aSocket = s;
		
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
		}catch(IOException e) {
			System.out.println(e);
		}
		
		
	}
	
	// public void printMenuChoices() {
		// System.out.println("Please choose from one of the following options: ");
		// System.out.println("1. Search catalogue courses.");
		// System.out.println("2. Add course to student courses.");
		// System.out.println("3. Remove course from student courses.");
		// System.out.println("4. View all courses in catalogue");
		// System.out.println("5. View all courses taken by student.");
		// System.out.println("6. Quit.");
		// System.out.println("\nPlease enter your selection: ");
	// }
	
	public void menu() {
		while (true) {
			
			String choiceFromServer = socketIn.readLine();
			if(choiceFromServer != 4 && choiceFromServer != null) sendString("valid");
			
			switch (choiceFromServer) {
			case "1":
				searchCatCourses();
				break;
			case "2":
				addCourse(); // Talk to Niyousha to finds out how she will send the serialized Student via socket
				break;
			case "3":
				removeStudentCourse(); // Talk to Niyousha to finds out how she will send the serialized Student via socket
				break;
			case "4":
				listCatCourses();
				break;
			case "5":
				listStudentCourses(); // Talk to Niyousha to finds out how she will send the serialized Student via socket
				break;
			case "6":
				System.out.println("\nGood Bye!");
				return;
			default:
				System.out.println("\nInvalid selection. Please try again");
				break;
			}
		}
	}
	
	private void listStudentCourses() {
		String name = getStudentName();
		int id = getStudentId();
		regApp.listStudentCourses(name, id);
	}

	private void listCatCourses() {
		sendString(regApp.listAllCourses());
	}

	private void removeStudentCourse() {
		String studentName = getStudentName();
		int studentId = getStudentId();
		String courseName = getCourseName();
		int courseNum = getCourseNum();
		int secNum = getSecNum();
		regApp.removeStudentCourse(studentName, studentId, courseName, courseNum, secNum);
	}

	private void addCourse() {
		String addCourseParameters[] = socketIn.readLine().split(" ");
		String studentName = addCourseParameters[0];
		int studentId = Integer.parseInt(addCourseParameters[1]);
		String courseName = addCourseParameters[2];
		int courseNum = Integer.parseInt(addCourseParameters[3]);
		int secNum = Integer.parseInt(addCourseParameters[4]);
		sendString(regApp.addCourse(studentName, studentId, courseName, courseNum, secNum));
	}

	private void searchCatCourses() {
		String searchCatCoursesParameters[] = socketIn.readLine().split(" ");
		String name = searchCatCoursesParameters[0];
		int num = Integer.parseInt(searchCatCoursesParameters[1]);
		sendString(regApp.searchCatCourses(name, num));	
	}

	
	
	public void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}
	
	@Override
	public void run() {
		try {
			menu();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main (String [] args) {
		FrontEnd end = new FrontEnd();
		end.menu();
	}
}