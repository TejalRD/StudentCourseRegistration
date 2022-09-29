package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Student {
    private int id; // Id of student
    private ArrayList<String> preferences; // Store the name of the courses preferred
    private ArrayList<String> enrolled; // Store the name of the courses in which student is enrolled
        
    public Student(int id) {
        /*
         * Constructor
         */
        this.id = id;
        this.preferences = new ArrayList<String>();
        this.enrolled = new ArrayList<String>();
    }

    public void enroll(String name) {
        /*
         * Enroll student in a given course name
         */
        this.enrolled.add(name);
    }

    public void addPreference(String name) {
        /*
         * Add course's name to the preferred courses
         */
        this.preferences.add(name);
    }

    /*
     * Getters and setters
     */
    public int getId() {
        return this.id;
    }

    public ArrayList<String> getPreferences() {
        return this.preferences;
    }

    /*
     * Methods
     */
    public int getSatisfactionRating(String[] coursesName) {
        /*
         * Returns the satisfaction rate of the student
         */
        int total = 0;

        // Loop through enrolled courses
        for(String name: enrolled) {
            // Get index
            int index = Arrays.asList(coursesName).indexOf(name);
            int score = 9 - index;
            total += score;
        }

        return total;
    }

    public String getDescription(String[] coursesName) {
        /*
         * Returns a string representation of the student
         * The format returned is:
         *      <student_id>:<course_1>,<course_2>,...,<course_n>::SatisfactionRating=<value>
         */
        String ret = String.format("%d:", id); // Id

        // Loop through enrolled courses
        for(int i = 0; i < this.enrolled.size(); i++) {
            ret += this.enrolled.get(i);
            if(i < this.enrolled.size() -1) {
                ret += ",";
            }
        }
        // Add satisfaction rating
        ret += "::SatisfactionRating=" + getSatisfactionRating(coursesName);

        return ret;
    }
}

