package serverModel;

import java.util.ArrayList;

public class Course {

	private String courseName;
	private int courseNum;
	private ArrayList<Course> preReq;
	private ArrayList<CourseOffering> offeringList;

	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}

	public void addOffering(CourseOffering offering) {
		if (offering != null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			offeringList.add(offering);
		}
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
	public ArrayList <CourseOffering> getCourseOffering() {
		return this.offeringList;
	}
	
	public CourseOffering getCourseOfferingAt(int i) {
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}
	
	public String output() {
		return "The course information is as follows:"
				+ this.toString();
	}	
	
	public boolean addCourse(Student theStudent, int secNum) { 
		boolean status = false; 
		for (CourseOffering i: offeringList) {
			if (i.getSecNum() == secNum && i.addCourse(theStudent)) 
					status = true; 
			}
		return status;
	}
		
	public String listStudentCourses(String name, int id) {
		String s = "";
		for(CourseOffering i: offeringList) {
			s += i.listStudentCourses(name, id);
		}
		return s;
	}
	
	public boolean removeStudentCourse(Student theStudent, int secNum) {
		boolean status = false;
		for (CourseOffering i: offeringList) {
			if(i.getSecNum() == secNum)
				status = i.removeStudentCourse(theStudent);
		}
		return status;
	}
		
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c.toString();
		st += "\n-------\n";
		return st;
	}
}

