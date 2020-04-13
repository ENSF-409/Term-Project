package model;

public class Registration {
	private Student theStudent;
	private CourseOffering theOffering;
	
	void completeRegistration (Student st, CourseOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}
	
	private void addRegistration () {// Consider changing this to return type Student
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
		//
		//NOTICE ME NOTICE ME NOTICE ME
		//
		//
		// The reason for this is explained in case "5" line 67 of BackEnd.java
		//
		//
		//return theStudent;
	}
	
	public Student getTheStudent() {
		return theStudent;
	}
	
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}

	public String listStudentCourses(String name, int id) {
		String s = "";
		if (validateStudent(name, id)) {
			s += theOffering.getTheCourse().getCourseName() + " " + theOffering.getTheCourse().getCourseNum();
			s += ": Section " + theOffering.getSecNum() + "\n";
		}
		//else
		//	s = "\n"; //"Student does not exist!"; I did this because if a student doesn't exist for a particular course in the courseCatalogue
		return s;								// only send an empty string
	}
	
	public boolean validateStudent(String name, int id) {
		if (this.theStudent.getStudentName().equals(name) && 
				this.theStudent.getStudentId() == id) {
			return true;
		}
		else 
			return false;
	}

	public boolean validateCourse(String courseName, int courseNum) {
		if (this.theOffering.getTheCourse().getCourseName() == courseName &&
				this.theOffering.getTheCourse().getCourseNum() == courseNum)
			return true;
		else
			return false;
	}

	@Override
	public String toString () {
		String st = "\n";
		st += "Student: " + getTheStudent().toString() + "\n";
		st += "The Offering: " + getTheOffering().toString() + "\n";
		st += "\n-----------\n";
		return st;	
	}

}
