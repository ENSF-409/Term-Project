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
				+ this.toString());
	}	
	
	public boolean addCourse(String studentName, int studentId, int secNum) { //CONSIDER CHANGING RETURN TYPE TO Student
		boolean status = false; // consider CHANGING TO NULL AND MAKING IT TYPE Student
		for (CourseOffering i: offeringList) {
			if (i.getSecNum() == secNum){
				//status = i.addCourse(studentName, studentId);
				if (i.addCourse(studentName, studentId)) //REMOVE
					status = true; //REMOVE
			}
		}
		return status;
	}
	
	public String listStudentCourses(String name, int id) {
		String s = "";
		for(CourseOffering i: offeringList) {
			s += i.listStudentCourses(name, id);
		}
	}
	
	public boolean removeStudentCourse(String studentName, int studentId, int secNum) {
		boolean status = false;
		for (CourseOffering i: offeringList) {
			if(i.getSecNum() == secNum)
				status = i.removeStudentCourse(studentName, studentId);
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

