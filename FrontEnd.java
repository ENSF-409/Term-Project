import java.util.Scanner;

public class FrontEnd {
	
	private RegistrationApp regApp;
	private Scanner scan;
	
	public FrontEnd() {
		regApp = new RegistrationApp();
		scan = new Scanner(System.in);
	}
	
	public void printMenuChoices() {
		System.out.println("Please choose from one of the following options: ");
		System.out.println("1. Search catalogue courses.");
		System.out.println("2. Add course to student courses.");
		System.out.println("3. Remove course from student courses.");
		System.out.println("4. View all courses in catalogue");
		System.out.println("5. View all courses taken by student.");
		System.out.println("6. Quit.");
		System.out.println("\nPlease enter your selection: ");
	}
	
	public void menu() {
		while (true) {
			printMenuChoices();
			int choice = scan.nextInt();
			scan.nextLine();
			
			switch (choice) {
			case 1:
				searchCatCourses();
				break;
			case 2:
				addCourse();
				break;
			case 3:
				removeStudentCourse();
				break;
			case 4:
				listCatCourses();
				break;
			case 5:
				listStudentCourses();
				break;
			case 6:
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
		regApp.listAllCourses();
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
		String studentName = getStudentName();
		int studentId = getStudentId();
		String courseName = getCourseName();
		int courseNum = getCourseNum();
		int secNum = getSecNum();
		regApp.addCourse(studentName, studentId, courseName, courseNum, secNum);
	}

	private void searchCatCourses() {
		String name = getCourseName();
		int num = getCourseNum();
		regApp.searchCatCourses(name, num);	
	}

	private String getCourseName() {
		System.out.println("Please enter the name of the course: ");
		String line;
		line = scan.nextLine();
		return line;
	}
	
	private int getCourseNum() {
		System.out.println("Please enter the course number: ");
		int courseNum;
		courseNum = scan.nextInt();
		scan.nextLine();
		return courseNum;
	}
	
	private String getStudentName() {
		System.out.println("Please enter the name of the student: ");
		String line;
		line = scan.nextLine();
		return line;
	}
	
	private int getStudentId() {
		System.out.println("Please enter the student Id: ");
		int id;
		id = scan.nextInt();
		scan.nextLine();
		return id;
	}
	
	private int getSecNum() {
		System.out.println("Please enter the course section number: ");
		int secNum;
		secNum = scan.nextInt();
		scan.nextLine();
		return secNum;
	}

	public static void main (String [] args) {
		FrontEnd end = new FrontEnd();
		end.menu();
	}
}
