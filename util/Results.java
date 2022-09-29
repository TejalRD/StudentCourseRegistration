package studentCoursesBackup.util;

import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
    // List to store the results as strings to be written to a file
    private ArrayList<String> results;
    // List to store conflicts
    private ArrayList<String> conflicts;
    public Results() {
        results = new ArrayList<String>();
        conflicts = new ArrayList<String>();
    }

    @Override
    public void generateResults(ArrayList<Course> courses, ArrayList<Student> students) {
        // TODO Auto-generated method stub
        results.clear();

        // Generate an array with the name of all courses
        String[] coursesName = new String[courses.size()];
        int idx = 0;
        for(Course course: courses) {
            coursesName[idx] = course.getName();
            idx++;
        }

        int totalSatisfactionRating = 0;
        for(int i = 0; i < students.size(); i++) {
            totalSatisfactionRating += students.get(i).getSatisfactionRating(coursesName);
            results.add(students.get(i).getDescription(coursesName));
        }
        float avgSatisfactionRating = (float)totalSatisfactionRating / (float)students.size();
        results.add(String.format("AverageSatisfactionRating=%.2f", avgSatisfactionRating));
    }

    public ArrayList<String> getResults() {
        return results;
    }

    public ArrayList<String> getConflicts() {
        return conflicts;
    }

    @Override
    public void display(String content) {
        // TODO Auto-generated method stub
        /*
         * This method display the content to the console
         */
        System.out.println(content);
        
    }

    public void recordConflict(Student student, ArrayList<Course> courses, String courseName, int classTime) {
        // Find conflict and record
        for(Course course: courses) {
            // Check if student is enrolled in current course and that this is a different course
            if(course.isEnrolled(student) && !course.getName().equals(courseName)) {
                if(course.getClassTime() == classTime) {
                    conflicts.add(String.format("Student %d tryign to enter course %s" +
                        " which conflicts with %s (Class Time = %d)",
                        student.getId(),
                        courseName,
                        course.getName(),
                        classTime));
                }
            }
        }
    }
}
