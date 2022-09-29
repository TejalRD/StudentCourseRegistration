package studentCoursesBackup.util;

import java.util.ArrayList;

public class Course {
    private String name; // Name of course
    private int capacity; // Capacity
    private int classTime; // classTime
    private ArrayList<Student> enrolled; // Students enrolled in this course

    // Default constructor
    public Course(String name, int capacity, int classTime) {
        this.name = name;
        this.capacity = capacity;
        this.classTime = classTime;
        enrolled = new ArrayList<Student>();
    }
    
    public boolean isEnrolled(Student student) {
        /*
         * Returns true if a given student is enrolled in this course
         */
        // Check if student is enrolled in the course
        for(Student st: enrolled) {
            if(st.getId() == student.getId()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        /*
         * Returns a boolean indicating if the course is full
         */
        return enrolled.size() == capacity;
    }

    public void addStudent(Student student) {
        /*
         * Add a new student to the course
         */
        this.enrolled.add(student);
    }

    public ArrayList<Student> getStudentsEnrolled() {
        /*
         * Get a list of all students enrolled in the course
         */
        return this.enrolled;
    }

    /*
     * Getters and setters
     */
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getClassTime() {
        return this.classTime;
    }

    public void setClassTime(int classTime) {
        this.classTime = classTime;
    }

    /*
     * toString method
     */
    @Override
    public String toString() {
        String ret = String.format("Name: %s, Capacity: %d, Class Time: %d\n", name, capacity, classTime);
        ret += String.format("Enrolled: %d students", enrolled.size());
        if(enrolled.size() > 0) {
            for(Student student: enrolled) {
                ret += "\n" + student.toString();
            }
        }
        return ret;
    }
}
