package lab1;

import java.io.*;
import java.util.*;

    public class Main {

        // This function accepts a number and returns its square

        /**
         * Calculates the square of a number.
         *
         * @param number The number to square
         * @return The square of the number
         */
        public static int square(int number) {
            return number * number;
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // 8.1: Using two types of variables
            String name; // String variable
            int age; // Integer variable

            // 8.4: Obtaining user input
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            System.out.print("Enter your age: ");
            age = scanner.nextInt();

            // 8.8: Using a List to collect user inputs
            List<Integer> ages = new ArrayList<>();
            ages.add(age); // Adding user's age to the list

            // 8.3: Using a loop to iterate over a list
            System.out.println("You have entered the following ages:");
            for (int a : ages) {
                System.out.println(a);
            }

            // 8.2: Using a conditional statement
            if (age >= 18) {
                System.out.println("You are an adult.");
            } else {
                System.out.println("You are a minor.");
            }

            // 8.5: Printing information to the console
            System.out.println("Your name is: " + name);

            // 8.6: Writing information to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
                writer.write("Name: " + name + "\n");
                writer.write("Age: " + age + "\n");
                writer.write("Squared Age: " + square(age) + "\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }

            // 8.7: Gracefully handling errors using try/catch
            try {
                System.out.print("Enter a number to square: ");
                int numberToSquare = scanner.nextInt();
                System.out.println("The square of " + numberToSquare + " is " + square(numberToSquare));
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter an integer.");
            }

        }
    }