import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager implements Constants {
	
	ArrayList <Course> courseList;
	ArrayList <Student> studentList;

	public DBManager () {
		courseList = new ArrayList<Course>();
		studentList = new ArrayList<Student>();
	}

	public ArrayList readFromDataBase() {
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
		studentList.add("John", 123);
		studentList.add("Mary", 343);
		studentList.add("Niyousha", 345);
		studentList.add("Dunsin", 987);
		studentList.add("Francis", 453);
		studentList.add("Joy", 278);
		studentList.add("Prince", 145);
		studentList.add("Helen", 943);
		
		for (Course i: courseList) {
			for (int j = 1; j <= NUMSECTIONS; j++) {
				i.addOffering(new CourseOffering(i, j, CAPACITY));
			}
		}

		return courseList;
	}

}
