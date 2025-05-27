/**
 * Abstract class representing a student with common properties.
 * This class serves as a base for specific types of students.
 */
public abstract class Student {
    /** The unique identifier for the student. */
    private String studentId;

    /** The student's first name. */
    private String firstName;

    /** The student's last name. */
    private String lastName;

    /** The student's email address. */
    private String email;

    /** The student's grade level, represented by the GradeLevel enum. */
    private GradeLevel grade;

    /** The section or class group the student belongs to. */
    private String section;

    /**
     * Constructs a new Student with the specified details.
     * 
     * @param studentId Unique identifier for the student.
     * @param firstName The first name of the student.
     * @param lastName The last name of the student.
     * @param email The email address of the student.
     * @param grade The grade level of the student.
     * @param section The section or class group of the student.
     */
    public Student(String studentId, String firstName, String lastName, String email, GradeLevel grade, String section) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.grade = grade;
        this.section = section;
    }

    /** 
     * Gets the student ID.
     * @return The student's unique identifier.
     */
    public String getStudentId() { return studentId; }

    /** 
     * Gets the student's first name.
     * @return The first name.
     */
    public String getFirstName() { return firstName; }

    /** 
     * Gets the student's last name.
     * @return The last name.
     */
    public String getLastName() { return lastName; }

    /** 
     * Gets the student's email.
     * @return The email address.
     */
    public String getEmail() { return email; }

    /** 
     * Gets the student's grade level.
     * @return The grade level enum.
     */
    public GradeLevel getGrade() { return grade; }

    /** 
     * Gets the student's section.
     * @return The section or class group.
     */
    public String getSection() { return section; }

    /** 
     * Sets the student ID.
     * @param studentId The unique identifier to set.
     */
    public void setStudentId(String studentId) { this.studentId = studentId; }

    /** 
     * Sets the first name.
     * @param firstName The first name to set.
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /** 
     * Sets the last name.
     * @param lastName The last name to set.
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /** 
     * Sets the email address.
     * @param email The email to set.
     */
    public void setEmail(String email) { this.email = email; }

    /** 
     * Sets the grade level.
     * @param grade The grade level to set.
     */
    public void setGrade(GradeLevel grade) { this.grade = grade; }

    /** 
     * Sets the section.
     * @param section The section to set.
     */
    public void setSection(String section) { this.section = section; }

    /**
     * Gets the full name by combining first and last names.
     * 
     * @return The full name of the student.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Abstract method to generate a QR code string representation for the student.
     * Must be implemented by subclasses to define the format of the QR content.
     * 
     * @return A string to be encoded in the QR code.
     */
    public abstract String toQRString();

    /**
     * Returns a string representation of the student object.
     * 
     * @return A formatted string containing student details.
     */
    @Override
    public String toString() {
        return String.format("Student{ID='%s', Name='%s', Email='%s', Grade='%s', Section='%s'}",
                studentId, getFullName(), email, grade.name(), section);
    }
}
