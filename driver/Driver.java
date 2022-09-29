package studentCoursesBackup.driver;

import java.io.IOException;
import java.util.ArrayList;

import studentCoursesBackup.util.Course;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.Student;
import studentCoursesBackup.util.Utils;

/**
 * @author placeholder
 *
 */
public class Driver {
	public static void main(String[] args) throws IOException {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

	    if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}") || args[2].equals("${arg2}")
				|| args[3].equals("${arg3}") || args[4].equals("${arg4}")) {

			System.err.println("Error: Incorrect number of arguments. Program accepts 5 argumnets.");
			System.exit(0);
		}

		// Get name of files from arguments
		String prefsFilename = args[0];
		String infoFilename = args[1];
		String resultsFilename = args[2];
		String conflictsFilename = args[3];
		String logFilename = args[4];
		
        // Create FileProcessor object
		FileProcessor processor = new FileProcessor(prefsFilename, infoFilename, resultsFilename, conflictsFilename, logFilename);
		ArrayList<Student> students = processor.readStudentsPrefs();
		ArrayList<Course> courses = processor.readCoursesInfo();

        // Now, assign

		// Create Results object
		// Write results
		Results results = new Results();

        for(Student student: students) {
            // Loop over preferences
            for(String pref: student.getPreferences()) {
                // If student is enrolled in 3 courses, break loop
                if(Utils.getCoursesEnrolled(student, courses).size() == 3) {
                    break;
                }
                // Get course by name
                Course course = null;
                for(Course c: courses) {
                    if(c.getName().equals(pref)) {
                        course = c;
                        break;
                    }
                }

                if(course != null) {
                    if(!course.isFull()) {
                        // Check if course overlaps
                        if(!Utils.timeOverlaps(student, courses, course.getName(), course.getClassTime())) {
                            student.enroll(course.getName());
                            course.addStudent(student);
                        } else {
							// Conflicts
							results.recordConflict(student, courses, course.getName(), course.getClassTime());
						}
                    } else {
                        processor.log(String.format("Student %d trying to enter course %s, but course is full.\n", student.getId(), course.getName()));
                    }
                    
                } else {
                    processor.log(String.format("Student %d trying to enter course %s, but course does not exists.\n", student.getId(), pref));
                }
            }
        }

        // Write errorLog
		processor.writeLog();

		
		results.generateResults(courses, students);
		processor.writeResults(results);
        
    }
}
