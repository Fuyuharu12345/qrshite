import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of students and handles QR code generation for them.
 * Provides methods to add, remove, find, display students, and generate their QR codes.
 */
public class StudentManager {
    /** List that holds all student objects. */
    private List<Student> students;

    /** QR code generator used to create QR images for students. */
    private QRCodeGenerator qrGenerator;

    /**
     * Constructs a new StudentManager with an empty student list and
     * initializes the QRCodeGenerator instance.
     */
    public StudentManager() {
        this.students = new ArrayList<>();
        this.qrGenerator = new QRCodeGenerator();
    }

    /**
     * Adds a student to the collection.
     * 
     * @param student The Student object to add.
     */
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully: " + student.getFullName());
    }

    /**
     * Removes a student from the collection by their student ID.
     * 
     * @param studentId The ID of the student to remove.
     * @return True if a student was removed; false otherwise.
     */
    public boolean removeStudent(String studentId) {
        return students.removeIf(student -> student.getStudentId().equals(studentId));
    }

    /**
     * Finds a student by their student ID.
     * 
     * @param studentId The ID of the student to find.
     * @return The Student object if found; null otherwise.
     */
    public Student findStudentById(String studentId) {
        return students.stream()
                .filter(student -> student.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns a copy of the list of all students.
     * 
     * @return A list of all Student objects.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    /**
     * Generates a QR code image for a single student identified by student ID.
     * The QR code image is saved to a file.
     * 
     * @param studentId The ID of the student to generate the QR code for.
     * @return True if the QR code was generated and saved successfully; false otherwise.
     */
    public boolean generateQRCodeForStudent(String studentId) {
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Student not found with ID: " + studentId);
            return false;
        }

        BufferedImage qrImage = qrGenerator.generateQRCode(student.toQRString());
        String fileName = String.format("qr_codes/%s_%s_QR.png",
                student.getStudentId(),
                student.getFullName().replaceAll("\\s+", "_"));

        boolean success = qrGenerator.saveQRCode(qrImage, fileName);
        System.out.println(success ?
                "QR code generated successfully for " + student.getFullName() + " -> " + fileName
                : "Failed to generate QR code for " + student.getFullName());
        return success;
    }

    /**
     * Generates QR code images for all students in the collection.
     * Provides a summary of how many codes were successfully generated.
     */
    public void generateQRCodesForAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available to generate QR codes.");
            return;
        }

        System.out.println("Generating QR codes for all students...");
        int successCount = 0;
        for (Student student : students) {
            if (generateQRCodeForStudent(student.getStudentId())) {
                successCount++;
            }
        }
        System.out.printf("Successfully generated %d out of %d QR codes.\n", successCount, students.size());
    }

    /**
     * Displays all students currently in the collection.
     * Prints a formatted list to the console.
     */
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return;
        }

        System.out.println("\n=== All Students ===");
        for (int i = 0; i < students.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, students.get(i));
        }
        System.out.println("==================\n");
    }
}
