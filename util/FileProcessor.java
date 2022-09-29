package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileProcessor {
    private String prefsFilename;
    private String infoFilename;
    private String resultsFilename;
    private String conflictsFilename;
    private String logFilename;
    private ArrayList<String> errorLog;
	public FileProcessor(String prefsFilename, String infoFilename, String resultsFilename, String conflictsFilename, String logFilename) {
        this.prefsFilename = prefsFilename;
        this.infoFilename = infoFilename;
        this.resultsFilename = resultsFilename;
        this.conflictsFilename = conflictsFilename;
        this.logFilename = logFilename;

        // The errorLog is a list of strings where each string is a line
        this.errorLog = new ArrayList<String>();
    }

    public ArrayList<Student> readStudentsPrefs() throws IOException {
        ArrayList<Student> students = new ArrayList<Student>();
        // Read student info
        BufferedReader reader = new BufferedReader(new FileReader(prefsFilename));
        String line;
        while((line = reader.readLine()) != null) {
            // Split
            String[] data = line.split(" ");
            int studentId = Integer.valueOf(data[0]);
            Student student = new Student(studentId);
            for(int i = 1; i < data.length; i++) {
                student.addPreference(data[i]);
            }

            students.add(student);

        }
        reader.close();
        return students;
    }

    public ArrayList<Course> readCoursesInfo() throws IOException {
        ArrayList<Course> courses = new ArrayList<Course>();
        // Read course info
        BufferedReader reader = new BufferedReader(new FileReader(infoFilename));
        String line;
        while((line = reader.readLine()) != null) {
            // Split
            String[] data = line.split(":");
            String name = data[0];
            int capacity = Integer.valueOf(data[1]);
            int classTime = Integer.valueOf(data[2]);

            Course course = new Course(name, capacity, classTime);
            courses.add(course);
        }
        reader.close();
        return courses;
    }

    public void log(String line) {
        // Add a new line to the error log
        errorLog.add(line);
    }

    public void writeLog() throws IOException {
        /*
         * Write error log to file
         */
        BufferedWriter writer = new BufferedWriter(new FileWriter(logFilename));
        for(String line: errorLog) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    public void writeResults(Results results) throws IOException {
        /*
         * Given an object Results, write its content to a file and also write the conflicts
         */


        BufferedWriter writer = new BufferedWriter(new FileWriter(resultsFilename));
        for(String line: results.getResults()) {
            writer.write(line + "\n");
        }
        writer.close();

        writer = new BufferedWriter(new FileWriter(conflictsFilename));
        for(String line: results.getConflicts()) {
            writer.write(line + "\n");
        }
        writer.close();
    }
    
}
