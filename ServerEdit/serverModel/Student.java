package serverModel;

import java.io.Serializable;
import java.util.ArrayList;

// TODO Documentation

public class Student implements Constants, Serializable{
	
	static final long serialVersionUID = 1;
	private String studentName;
	private int studentId;
	//private ArrayList<CourseOffering> offeringList;
	private ArrayList<Registration> studentRegList;
	
	public Student (String studentName, int studentId) {
		this.setStudentName(studentName);
		this.setStudentId(studentId);
		studentRegList = new ArrayList<Registration>();
	}
	
	public void addRegistration(Registration registration) {
		if (validateReg())
			this.studentRegList.add(registration);
		else
			System.out.println("Sorry, this student is already registered "
					+ "for the maximum number of courses.");
	}
	
	private boolean validateReg() {
		if (studentRegList.size() < MAXCOURSES)
			return true;
		else
			return false;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}	
	
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentId() + "\n\n";
		return st;
	}
	
	@Override
    	public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        	if (o == this) { 
            		return true; 
       		 } 
  
        /* Check if o is an instance of Student or not 
          "null instanceof [type]" also returns false */
        	if (o instanceof Student) { 
           		 return true; 
       		 } 
        	return false;
	}
}
