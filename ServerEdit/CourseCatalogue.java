package model;

import java.util.ArrayList;

public class CourseCatalogue implements Constants{
	
	private ArrayList <Course> courseList;
	
	public CourseCatalogue (DBManager dataBase) {
		setCourseList(dataBase.readCourseListFromDataBase());
	}
	
	public boolean addCourse(Student theStudent, Course theCourse, int secNum) {
		boolean status = false;
		if (validateSecNum(secNum)) {
			for (Course i: courseList) {
				if (i.equals(theCourse))
					if (i.addCourse(theStudent.getStudentName(), theStudent.getStudentId(), secNum))
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
		return null;
	}

	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	public String listStudentCourses(String name, int id) {
		String s = "Student courses are the following: \n";
		for (Course i: courseList) {
			s += i.listStudentCourses(name, id);
		}
		return s;
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
