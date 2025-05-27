import java.util.Scanner;

/**
 * Main application class for managing students and generating their QR codes.
 * Provides a console-based menu for user interaction.
 */
public class StudentQRCodeApp {
    /** Manages student records and QR code generation. */
    private StudentManager studentManager;

    /** Scanner object to read user input from the console. */
    private Scanner scanner;

    /**
     * Constructs a new StudentQRCodeApp instance.
     * Initializes the StudentManager and Scanner.
     */
    public StudentQRCodeApp() {
        this.studentManager = new StudentManager();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the main menu options to the user.
     */
    private void displayMenu() {
        System.out.println("\n=== Student QR Code Generator ===");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Generate QR Code for Single Student");
        System.out.println("4. Generate QR Codes for All Students");
        System.out.println("5. Remove Student");
        System.out.println("6. Exit");
        System.out.print("Choose an option (1-6): ");
    }

    /**
     * Interactively prompts the user to add a new student.
     * Includes validation for duplicate student IDs and grade level selection.
     */
    private void addStudentInteractive() {
        System.out.println("\n=== Add New Student ===");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine().trim();
        if (studentManager.findStudentById(studentId) != null) {
            System.out.println("Error: Student with ID " + studentId + " already exists!");
            return;
        }

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter Section: ");
        String section = scanner.nextLine().trim();

        System.out.println("Enter Grade Level (choose number):");
        GradeLevel[] values = GradeLevel.values();
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%d. %s\n", i + 1, values[i]);
        }
        int gradeChoice = Integer.parseInt(scanner.nextLine().trim());
        GradeLevel grade = values[gradeChoice - 1];

        Student student;
        if (grade.name().startsWith("COLLEGE")) {
            student = new CollegeStudent(studentId, firstName, lastName, email, grade, section);
        } else {
            student = new HighSchoolStudent(studentId, firstName, lastName, email, grade, section);
        }

        studentManager.addStudent(student);
    }

    /**
     * Prompts the user for a student ID and generates a QR code for that student.
     */
    private void generateSingleQRCode() {
        System.out.print("\nEnter Student ID to generate QR code: ");
        String studentId = scanner.nextLine().trim();
        studentManager.generateQRCodeForStudent(studentId);
    }

    /**
     * Prompts the user for a student ID and attempts to remove the student from the system.
     */
    private void removeStudent() {
        System.out.print("\nEnter Student ID to remove: ");
        String studentId = scanner.nextLine().trim();

        if (studentManager.removeStudent(studentId)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found with ID: " + studentId);
        }
    }

    /**
     * Runs the main application loop, displaying the menu and handling user input.
     */
    public void run() {
        System.out.println("Welcome to Student QR Code Generator!");

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": addStudentInteractive(); break;
                case "2": studentManager.displayAllStudents(); break;
                case "3": generateSingleQRCode(); break;
                case "4": studentManager.generateQRCodesForAllStudents(); break;
                case "5": removeStudent(); break;
                case "6":
                    System.out.println("Thank you for using Student QR Code Generator!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1-6.");
            }
        }
    }

    /**
     * Application entry point.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new StudentQRCodeApp().run();
    }
}
