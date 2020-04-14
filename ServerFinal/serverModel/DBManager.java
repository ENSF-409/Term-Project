package model;

import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager implements Constants {

	ArrayList <Course> courseList;
	ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
		init();
	}
	
	//public addCourse(Course theCourse){ // add new courses to the database
	//	courseList.add(theCourse)
	//}
	
	private void init() {
		studentList.add(new Student("John", 123));
		studentList.add(new Student("Mary", 343));
		studentList.add(new Student("Niyousha", 345));
		studentList.add(new Student("Dunsin", 987));
		studentList.add(new Student("Francis", 453));
		studentList.add(new Student("Joy", 278));
		studentList.add(new Student("Prince", 145));
		studentList.add(new Student("Helen", 943));
		
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		courseList.add(new Course ("ENGG", 209));
		courseList.add(new Course ("ENGG", 213));
		courseList.add(new Course ("CHEM", 209));
		courseList.add(new Course ("MATH", 211));
		courseList.add(new Course ("MATH", 277));
		courseList.add(new Course ("MATH", 275));
		courseList.add(new Course ("ENGG", 201));
		courseList.add(new Course ("ENGG", 200));
		courseList.add(new Course ("ENGG", 225));
		courseList.add(new Course ("ENGG", 202));
		
	
		for (Course i: courseList) {// All this code below should be determined by the administator
			for (int j = 1; j <= NUMSECTIONS; j++) {
				i.addOffering(new CourseOffering(i, j, CAPACITY));
			}
		}
	}
	
	public void addStudent(Student theStudent){ // Add new students to the database
		int checker = 0;
		for(Student s : studentList){
			if(s.equals(theStudent)) checker++;
		}
		if(checker == 0) studentList.add(theStudent);
	}
	
	public String sendStudentList(){
		String s = "All the students currently in the data base are:\n";
		for(Student student : studentList){
			s += student.toString() + "\n";
		}
		return s;
	}
	
	public String sendCourseList(){
		String s = "All the courses currently in the data base are:\n";
		for(Course course : courseList){
			s += course.toString() + "\n";
		}
		return s;
	}
		
	
	public Student findStudent(String studentName, int studentId) {
		for (Student s: studentList) {
			if (s.getStudentName().equalsIgnoreCase(studentName) && s.getStudentId() == studentId)
				return s;
		}
		
		return null;
	}
	
	public Course findCourse(String courseName, int courseNum) {
		for (Course c: courseList) {
			if (c.getCourseName().equalsIgnoreCase(courseName) && c.getCourseNum() == courseNum)
				return c;
		}
		return null;
	}
	
	public ArrayList<Student> readStudentListFromDataBase() {
		return studentList;
	}
	
	public ArrayList<Student> getStudentListFromDataBase() {
		return studentList;
	}
	
	public ArrayList<Course> readCourseListFromDataBase() {
		return courseList;
	}

}
