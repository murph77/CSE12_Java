/**
 * This file store the students registered for particular courses
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 *
 * This file contains the methods that help to store the students
 * registered for this particular course in the form of a HashSet.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

/**
 * A class that contains method to keep track of student enrolled in courses
 * Instance Variables:
 * enrolled - collection of students that are enrolled in this course
 * capacity - maximum number of students that can be enrolled in this course
 * department - this course falls under this department
 * number - the course number
 */
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * Constructor to initialize the course’s information
     * @param department
     * @param nunber
     * @param description
     */
    public Course(String department, String number, String description,
    int capacity){
        enrolled = new HashSet<Student>();
        if (department == null || number == null || description == null){
            String errorMsg = "Argument is null";
            throw new IllegalArgumentException("errorMsg");
        }
        if (capacity <= 0){
            String errorMsg2 = "Capacity is not positive";
            throw new IllegalArgumentException("errorMsg2");
        } else {
            this.capacity = capacity;
            this.department = department;
            this.number = number;
            this.description = description;
        }
    }

    /**
     * Return the department of a course
     * @return the department of a course
     */
    public String getDepartment(){
        return this.department;
    }

    /**
     * Return the number of a course
     * @return the number of a course
     */
    public String getNumber(){
        return this.number;
    }

    /**
     * Return the description of a course
     * @return the description of a course
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Return the capacity of a course
     * @return the capacity of a course
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * Add the student to the course.
     * @param student the student's info
     * @return true if student is enrolled
     */
    public boolean enroll(Student student) {
        if (student == null){
            String errorMsg = "Student is null";
            throw new IllegalArgumentException(errorMsg);
        }
        if (enrolled.contains(student) == true){
            return false;
        }
        if (enrolled.size() < capacity && enrolled.contains(student) == false){
            enrolled.add(student);
        }
        if (enrolled.contains(student) == true){
            return true;
        }
        return false;
    }

    /**
     * Remove the student from the course.
     * @param student the student's info
     * @return true if student is unenrolled
     */
    public boolean unenroll(Student student) {
        if (student == null){
            String errorMsg = "Student is null";
            throw new IllegalArgumentException(errorMsg);
        }
        if (enrolled.contains(student) == false){
            return false;
        }
        if (enrolled.contains(student) == true){
            enrolled.remove(student);
        }
        if (enrolled.contains(student) == false){
            return true;
        }
        return false;
    }

    /**
     * Remove all student from the course.
     */
    public void cancel() {
        enrolled.clear();
    }

    /**
     * Determine if the course is at its capacity,
     * @return true if the course is full
     */
    public boolean isFull() {
        if (enrolled.size() == capacity){
            return true;
        }
        return false;
    }

    /**
     * Return the current number of enrolled students
     * @return number of enrolled students
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }

    /**
     * Return the number of available seats in the course
     * @return the number of available seats in the course
     */
    public int getAvailableSeats() {
        int availableSeats = capacity - enrolled.size();
        return availableSeats;
    }

    /**
     * Return a list of all the students enrolled in this course
     * @return a list of all the students enrolled in this course
     */
    public HashSet<Student> getStudents() {
        HashSet copy = (HashSet) enrolled.clone();
        return copy;
    }

    /**
     * Return a sorted list of all the students enrolled in this course
     * @return a sorted list of all the students enrolled in this course
     */
    public ArrayList<Student> getRoster() {
        Iterator enrolledIter = this.enrolled.iterator();
        ArrayList<Student> returnList = new ArrayList<Student>();
        while (enrolledIter.hasNext()) {
            returnList.add((Student) enrolledIter.next());
        }
        Collections.sort(returnList);
        return returnList;
    }

    /**
     * Return a string representation for a Course object
     * @return a string representation for a Course object
     */
    public String toString() {
        String classString = String.format("%s %s [%d]\n%s", department,
            number, capacity, description);
        return classString;
    }
}

