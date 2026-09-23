import csv_classes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Lab2Main {

    public static void main(String[] args){

        /*
         * Main simply executes functions, all data
         * that is needed to be found will be found using
         * calls to PropertyAssessments class.
         *
         * This also needs to make the call to the CSV reader
         * to gather the data required to import to the class.
         */

        // Scanner is used to receive user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String csv = scanner.nextLine();
        PropertyAssessments assessments;
        try{
            assessments = PropertyAssessments.fromCsv(csv);
        } catch (IOException e){
            System.err.println("Error: can't open file " + csv);
            return;
        }

        System.out.print("Descriptive statistics of all property assessments\n");
        printStatistics(assessments);
        searchByAccountNumber(scanner, assessments);
        filterByNeighbourHood(scanner, assessments);

    }

    private static void printStatistics(PropertyAssessments assessments){
        System.out.println("n = " + assessments.size());
        System.out.println("min = " + "$" + String.format("%,d",assessments.min()));
        System.out.println("max = " + "$" + String.format("%,d",assessments.max()));
        System.out.println("range = " + "$" + String.format("%,d",assessments.range()));
        System.out.println("mean = " + "$" + String.format("%,d",assessments.mean()));
        System.out.println("median = " + "$" + String.format("%,d",assessments.median()));
    }

    private static void searchByAccountNumber(Scanner scanner, PropertyAssessments assessments){
        System.out.print("\nFind a property assessment by account number: ");
        if (!scanner.hasNextInt()){
            System.out.println("Invalid account number");
            scanner.nextLine();
            return;
        }
        int accNum = scanner.nextInt();
        scanner.nextLine();

        PropertyAssessment pa = assessments.filterById(accNum);
        if (pa == null){
            System.out.println("Property is not found");
        } else {
            System.out.println("Account number = " + pa.getAccountNumber());
            System.out.println("Address = " + pa.getAddress());
            System.out.println("Assessed value = " + pa.getAssessedValue());
            System.out.println("Assessment class = " + pa.getAssessmentClasses());
            System.out.println("Neighbourhood = " + pa.getNeighbourhood());
            System.out.println("Location = " + pa.getLocation());
        }
    }

    private static void filterByNeighbourHood(Scanner scanner, PropertyAssessments assessments) {
        System.out.print("\nFind statistics by neighbourhood: ");
        String name = scanner.nextLine();

        PropertyAssessments filter = assessments.filterByNeighbourhood(name.toUpperCase());
        if (filter.size() == 0){
            System.out.println("Neighbourhood is not found");
            return;
        }

        System.out.println("Statistics (neighbourhood = " + name + ")");
        printStatistics(filter);

    }

}
