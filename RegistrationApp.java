
public class RegistrationApp {

	private CourseCatalogue catCourse;
	
	public RegistrationApp() {
		catCourse = new CourseCatalogue();
	}
	
	public void searchCatCourses(String name, int num) {
		Course c = this.catCourse.searchCat(name, num);
		c.output();
	}

	public void addCourse(String studentName, int studentNum, String courseName, int courseNum, int secNum) {
		if (this.catCourse.addCourse(studentName, studentNum, courseName, courseNum, secNum))
			System.out.println("Course added successfully!\n");
		else
			System.out.println("Student course could not be added. Please try again.\n");
	}

	public void listStudentCourses(String name, int id) {
		this.catCourse.listStudentCourses(name, id);
	}

	public void removeStudentCourse(String studentName, int studentId, String courseName, int courseNum, int secNum) {
		if (this.catCourse.removeStudentCourse(studentName, studentId, courseName, courseNum, secNum))
			System.out.println("Student course removed!\n");
		else
			System.out.println("Student course could not be removed. Please try again.\n");
	}

	public void listAllCourses() {
		catCourse.listAllCourses();
	}
}
