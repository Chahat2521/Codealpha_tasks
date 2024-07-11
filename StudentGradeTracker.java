import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            ArrayList<Double> grades = new ArrayList<>();

            System.out.println("Welcome to the Student Grade Tracker!");
            System.out.print("Enter the number of students: ");
            int numStudents = input.nextInt();

            for (int i = 0; i < numStudents; i++) {
                System.out.print("Enter grades for student " + (i + 1) + ": ");
                double grade = input.nextDouble();
                grades.add(grade);
            }

            double sum = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);

            for (double grade : grades) {
                sum += grade;
                if (grade > highest) {
                    highest = grade;
                }
                if (grade < lowest) {
                    lowest = grade;
                }
            }

            double average = sum / grades.size();

            System.out.println("Grade Statistics:");
            System.out.println("Average: " + String.format("%.2f", average));
            System.out.println("Highest: " + String.format("%.2f", highest));
            System.out.println("Lowest: " + String.format("%.2f", lowest));
        }
    }
}