package model;

public class RegistrationApp {

	private CourseCatalogue catCourse;
	private DBManager dataBase;
	
	public RegistrationApp() {
		dataBase = new DBManager(); // Placed here by Dunsin
		catCourse = new CourseCatalogue(dataBase); // Placed dataBase here by Dunsin
	}
	
	public String searchCatCourses(String name, int num) { //Dunsin changed return type from void to String
		Course c = this.catCourse.searchCat(name, num);
		if(c == null) return "Course does not exist!";
		return c.output();
	}

	public String addCourse(String studentName, int studentNum, String courseName, int courseNum, int secNum) {// //CONSIDER CHANGING RETURN TYPE TO Student
		
		Student theStudent = dataBase.findStudent(studentName, studentNum);
		Course theCourse = dataBase.findCourse(courseName, courseNum);
		
		if (theStudent != null && theCourse != null && this.catCourse.addCourse(theStudent, theCourse, secNum)) {
			return "Course added successfully!\n";
		}

		return "Student course could not be added. Please try again.\n"; //REMOVE
	}

	public String listStudentCourses(String name, int id) {
		return this.catCourse.listStudentCourses(name, id);
	}

	public String removeStudentCourse(String studentName, int studentId, String courseName, int courseNum, int secNum) {
		if (this.catCourse.removeStudentCourse(studentName, studentId, courseName, courseNum, secNum))
			return "Student course removed!\n";
		else
			return "Student course could not be removed. Please try again.\n";
	}

	public String listAllCourses() {
		return catCourse.listAllCourses();
	}
	
	public DBManager getDataBase(){
		return dataBase;
	}
	
	public void addStudent(Student theStudent) {
		dataBase.addStudent(theStudent);
	}
}
