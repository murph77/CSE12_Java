/**
 * This file store the students' info
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 *
 * This file contains the methods that help to store the students' info
 */
import java.util.Objects;

/**
 * A class that contains method to keep track of students' info
 * Instance Variables:
 * firstName - the first name of the student.
 * lastNam - the last name of the student.
 * PID - the PID of the student.
 */
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Constructor to initialize the student’s information.
     * @param firstName
     * @param lastName
     * @param PID
     */
    public Student(String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID == null){
            String errorMsg = "Argument is null";
            throw new IllegalArgumentException("errorMsg"); 
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    /**
     * Return the student’s last name
     * @return the student’s last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Return the student’s first name
     * @return the student’s first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Return the student’s PID
     * @return the student’s PID
     */
    public String getPID() {
        return this.PID;
    }

    /**
     * Determine if two students have same info
     * @param student a student's info
     * @return true if the student's info = current student's info
     */
    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }
        try {
            Student oStudent = (Student) o;
            if (oStudent.getLastName().equals(this.getLastName())){
                if (oStudent.getFirstName().equals(this.getFirstName())){
                    if (oStudent.getPID().equals(this.getPID())){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Return the hash value for a student
     * @param firstName - the first name of the student.
     * @param lastNam - the last name of the student.
     * @param PID - the PID of the student.
     * @return the hash value
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, PID);
    }

    /**
     * Compare two students' value in a particular order
     * @param student a student's info
     * @return 0 if info are the same,
     * negative if current object comes before student o,
     * positive if current object comes after student o
     */
    @Override
    public int compareTo(Student o) {
        if (this.equals(o) == true){
            return 0;
        }
        int lastNameCompare = this.getLastName().compareTo(o.getLastName());
        if (lastNameCompare != 0){
            return lastNameCompare;
        }
        int firstNameCompare = this.getFirstName().compareTo(o.getFirstName());
        if (firstNameCompare != 0){
            return firstNameCompare;
        }
        int pidCompare = this.getPID().compareTo(o.getPID());
        return pidCompare;
    }
}
