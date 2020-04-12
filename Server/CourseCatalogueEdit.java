import java.util.ArrayList;

public class CourseCatalogue implements Constants{
	
	private ArrayList <Course> courseList;
	
	public CourseCatalogue (DBManager dataBase) {
		setCourseList(dataBase.readFromDataBase());
	}
	
	public boolean addCourse(String studentName, int studentId, String courseName, int courseNum, int secNum) {
		boolean status = false;
		Course c = searchCat(courseName, courseNum);
		if (c != null && validateSecNum(secNum)) {
			for (Course i: courseList) {
				if (i.equals(c))
					if (i.addCourse(studentName, studentId, secNum))
						status = true;
			}
		}
		return status;
	}

	private boolean validateSecNum(int secNum) {
		if (secNum > 0 && secNum <= NUMSECTIONS)
			return true;
		else {
			System.out.println("Invalid section number!");
			return false;
		}
	}

	//private void loadFromDataBase() {
	//	DBManager db = new DBManager();
	//	setCourseList(db.readFromDataBase());
	//}
	
	public void createCourseOffering (Course c, int secNum, int secCap) {
		if (c!= null) {
			CourseOffering theOffering = new CourseOffering (c, secNum, secCap);
			c.addOffering(theOffering);
		}
	}
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				//c.output();
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}
	//Typically, methods that are called from other methods of the class
	//are private and are not exposed for use by other classes.
	//These methods are refereed to as helper methods or utility methods
	private void displayCourseNotFoundError() {
		System.err.println("Course does not exist!");
	}
	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	public void listStudentCourses(String name, int id) {
		System.out.println("Student courses are the following: \n");
		for (Course i: courseList) {
			i.listStudentCourses(name, id);
		}
		
	}
	
	public boolean removeStudentCourse(String studentName, int studentId, String courseName, int courseNum, int secNum) {
		boolean status = false;
		Course c = searchCat(courseName, courseNum);
		if (c != null) {
			if (c.removeStudentCourse(studentName, studentId, secNum)) {
				status = true;
			}
		}		
		return status;
	}
	
	public String listAllCourses() { //Dunsin changed this to return String
		return this.toString();
	}
	
	@Override
	public String toString () {
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c.toString(); 
			st += "\n";
		}
		return st;
	}





	
}
