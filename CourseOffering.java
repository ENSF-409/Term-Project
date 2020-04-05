import java.util.*;

public class CourseOffering {
	
	private int secNum;
	private int secCap;
	private Course theCourse;
	private ArrayList <Registration> offeringRegList;
	
	public CourseOffering (Course theCourse, int secNum, int secCap) {
		this.setTheCourse(theCourse);
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		offeringRegList = new ArrayList <Registration>();
	}
	
	public int getSecNum() {
		return secNum;
	}
	
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	
	public int getSecCap() {
		return secCap;
	}
	
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	public Course getTheCourse() {
		return theCourse;
	}
	
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}
	
	public boolean addCourse(String studentName, int studentId) {
		Registration newReg = new Registration();
		newReg.completeRegistration((new Student(studentName, studentId)), this);
		if (newReg.validateStudent(studentName, studentId))
			return true;
		else
			return false;
	}

	public void addRegistration(Registration registration) {
		offeringRegList.add(registration);
	}
	
	public void listStudentCourses(String name, int id) {
		for (Registration i: offeringRegList) {
			i.listStudentCourses(name, id);
		}
	}
	
	public boolean removeStudentCourse(String studentName, int studentId){
		boolean status = false;
		Registration index = null;
		
		for (Registration i: offeringRegList) {
			if(i.validateStudent(studentName, studentId)){
				index = i;
			}
		}
		
		if (index != null) {
			offeringRegList.remove(index);
			status = true;
		}
		
		return status;
	}
	
	private String listAllStudents() {
		String str = "Students: \n";
		for (Registration r: offeringRegList) {
			str += r.getTheStudent().getStudentName() + "\n";
		}
		return str;
	}
		
	@Override
	public String toString () {
		String st = "\n";
		st += "Section Number: " + getSecNum() + ", Section Capacity: "+ getSecCap() +"\n";
		st += listAllStudents();
		return st;
	}

	

}
