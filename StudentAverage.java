import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class StudentAverage {
    public static void main(String[] args) throws IOException {        

        Scanner input = new Scanner(System.in);
        FileWriter writer = new FileWriter("students.json");

        System.out.print("How many students are there? ");//establishes, or fills numberOfStudents with desired number
        int numberOfStudents = input.nextInt();

        writer.write("[\n");   // start JSON array

        for (int i = 1; i <= numberOfStudents; i++) {// for loop int i =1; i <= numberOfStudents; i++) in effect, fills i, i=1 then if <= numberOfStudents, continue, i++ add one with each iteration
            String oddStudent = "Student PK is Odd";
            String evenStudent = "Student PK is Even";
            String studentPKPrint;
                if (i % 2 == 0) {
                    studentPKPrint = evenStudent;
                } else {
                    studentPKPrint = oddStudent;
                }
            System.out.println("\nStudent " + i);

            System.out.print("Enter Student Number: ");//added a student id entry as it was the only thing I understood:)            
            String studentId = input.next();

                while (!studentId.matches("S\\d{6}")) {
                    System.out.println("Student ID must be in the format S123456");
                    System.out.print("Enter Student Number again: ");
                    studentId = input.next();
                }

        System.out.print("Enter total marks: ");
            int totalMarks = input.nextInt();
        System.out.print("Enter number of subjects: ");
            int subjects = input.nextInt();
                if (subjects == 0) {
                    System.out.println("Student must have subject entry");
                    continue;
                }

            double average = Math.round(((double) totalMarks / subjects) * 100) / 100.0;// applied extra rounding feature "because I could" just to have more stuff

            String grade;

            if (average >= 75) {
                grade = "Distinction";
            } else if (average >= 50) {
                grade = "Pass";
            } else {
                grade = "Fail";
            }

            writer.write("  {\n");
            writer.write("    \"student\": " + i + ",\n");
            writer.write("    \"studentOddOrEven\": \"" + studentPKPrint + "\",\n");
            writer.write("    \"studentId\": \"" + studentId + "\",\n");
            writer.write("    \"average\": " + String.format("%.2f", average) + ",\n");
            writer.write("    \"grade\": \"" + grade + "\"\n");
            writer.write("  }");

            if (i < numberOfStudents) {
                writer.write(",");
            }

            writer.write("\n");
        }

        writer.write("]");

        writer.close();
        input.close();
    }
}