package studentCoursesBackup.util;

import java.util.ArrayList;

public class Utils {
    public static boolean timeOverlaps(Student student, ArrayList<Course> courses,
        String courseName, int classTime) {
        /*
         * This function checks if a given classTime overlaps for courses already ...
         * ... being taken by student
         */
        // Loop over courses
        for(Course course: courses) {
            // Check if student is enrolled in current course and that this is a different course
            if(course.isEnrolled(student) && !course.getName().equals(courseName)) {
                if(course.getClassTime() == classTime) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Course> getCoursesEnrolled(Student student, ArrayList<Course> courses) {
        /*
         * Returns an ArrayList of all courses that student is enrolled in
         */
        ArrayList<Course> enrolled = new ArrayList<Course>();
        for(Course course: courses) {
            if(course.isEnrolled(student)) {
                enrolled.add(course);
            }
        }
        return enrolled;
    }
}
