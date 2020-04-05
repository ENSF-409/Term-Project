import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager implements Constants {
	
	ArrayList <Course> courseList;

	public DBManager () {
		courseList = new ArrayList<Course>();
	}

	public ArrayList readFromDataBase() {
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		
		for (Course i: courseList) {
			for (int j = 1; j <= NUMSECTIONS; j++) {
				i.addOffering(new CourseOffering(i, j, CAPACITY));
			}
		}

		return courseList;
	}

}
