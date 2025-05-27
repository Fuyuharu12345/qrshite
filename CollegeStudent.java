/**
 * Represents a college student, which is a specialized type of Student.
 * This class demonstrates inheritance by extending the Student class.
 */
public class CollegeStudent extends Student {

    /**
     * Constructs a CollegeStudent instance with the specified details.
     * 
     * @param studentId the unique identifier for the student
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param email the student's email address
     * @param grade the student's grade level as a GradeLevel enum
     * @param section the section or class the student belongs to
     */
    public CollegeStudent(String studentId, String firstName, String lastName, String email, GradeLevel grade, String section) {
        super(studentId, firstName, lastName, email, grade, section);
    }

    /**
     * Returns the string representation of this CollegeStudent
     * formatted for QR code encoding.
     * This method overrides the parent class implementation
     * to specify the type as "College Student".
     * 
     * @return formatted string containing college student details for QR code
     */
    @Override
    public String toQRString() {
        return String.format("College Student\nID: %s\nName: %s\nEmail: %s\nGrade: %s\nSection: %s",
                getStudentId(), getFullName(), getEmail(), getGrade(), getSection());
    }
}

