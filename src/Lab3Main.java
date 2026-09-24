import csv_classes.*;

import java.io.IOException;
import java.util.Scanner;

public class Lab3Main {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String csv = scanner.nextLine();
        PropertyAssessments assessments;
        try{
            assessments = PropertyAssessments.fromCsv(csv);
        } catch (IOException e){
            System.out.println("Error in opening " + csv);
            return;
        }

        neighbourhoodName(scanner, assessments);
        assessmentClass(scanner, assessments);
    }

    public static void neighbourhoodName(Scanner scanner, PropertyAssessments assessments){
        System.out.print("\nPlease enter a neighbourhood name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty() || new Scanner(name).hasNextInt()){
            System.out.println("Invalid negihbourhood name");
            return;
        }

        PropertyAssessments filter = assessments.filterByNeighbourhood(name.toUpperCase());
        if (filter.size() == 0){
            System.out.println("Neighbourhood is not found");
            return;
        }

        System.out.println("There are " + String.format("%,d",filter.size()) + " properties in " + name);
        System.out.println("The mean value is CAD " + String.format("%,d", filter.mean()));
        System.out.println("The median value is CAD " + String.format("%,d", filter.median()));
    }

    public static void assessmentClass(Scanner scanner, PropertyAssessments assessments){
        System.out.print("\nPlease enter an assessment class: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty() || new Scanner(name).hasNextInt()){
            System.out.println("Invalid assessment class");
        }

        PropertyAssessments filter = assessments.filterByAssessmentClass(name.toLowerCase());

        if (filter.size() == 0){
            System.out.println("Sorry, can't find " + name + " properties");
            return;
        }

        System.out.println("There are " + String.format("%,d", filter.size()) + " " + name + " properties in Edmonton");
        System.out.println("The min value is CAD " + String.format("%,d", filter.min()));
        System.out.println("The max value is CAD " + String.format("%,d", filter.max()));
    }
}
