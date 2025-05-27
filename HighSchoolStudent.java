/**
 * Represents a high school student, a specific type of Student.
 * This class extends the Student class to demonstrate inheritance.
 */
public class HighSchoolStudent extends Student {

    /**
     * Constructs a HighSchoolStudent with the specified details.
     * 
     * @param studentId the unique identifier for the student
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param email the student's email address
     * @param grade the student's grade level as a GradeLevel enum
     * @param section the section or class the student belongs to
     */
    public HighSchoolStudent(String studentId, String firstName, String lastName, String email, GradeLevel grade, String section) {
        super(studentId, firstName, lastName, email, grade, section);
    }

    /**
     * Returns a formatted string representation of the high school student
     * to be used in QR code generation.
     * This method overrides the base class method to specify the student type.
     * 
     * @return formatted string containing high school student details for QR code
     */
    @Override
    public String toQRString() {
        return String.format("High School Student\nID: %s\nName: %s\nEmail: %s\nGrade: %s\nSection: %s",
                getStudentId(), getFullName(), getEmail(), getGrade(), getSection());
    }
}
