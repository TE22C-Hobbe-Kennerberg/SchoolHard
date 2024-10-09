import java.util.Dictionary;
import java.util.UUID;

public abstract class Student extends Person {
    private final String studentID;

    public Student(String firstName, String lastName, int startYear){
        this.firstName = firstName;
        this.lastName = lastName;
        // Creates a universally unique ID for each student to later be used in dictionary.
        studentID = UUID.randomUUID().toString();
    }
    public String getStudentID() {
        return studentID;
    }


}