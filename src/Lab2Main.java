import csv_classes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
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
        try{
            String[][] data = readData(csv);
            System.out.print("Descriptive statistics of all property assessments\n");
            PropertyAssessments assessments = PropertyAssessments.buildAssessments(data);
            System.out.println(assessments.getAssessments().get(58));
        } catch (IOException e){
            System.err.println("Error: can't open file " + csv);
        }
        /*
         * Before output check for invalid states:
         *  - file does not exist
         *  - use System.err.println() to get the red text for the error message
         *
         * Required outputs:
         *  - String telling user what it is displaying
         *  - n (number of entries)
         *  - min (lowest property value)
         *  - max (largest property value)
         *  - range (difference between max and min)
         *  - mean (average property value)
         *  - median (center most property value)
         */


//        System.out.print("Find a property assessment by account number: ");
//        int accNum = scanner.nextInt();
//        scanner.nextLine(); // Needed for invisible \n in nextInt()
//        /*
//         * Before output check for invalid states:
//         *  - account number does not exist
//         *  - user tries to enter a non-numeric answer
//         *
//         * Required outputs:
//         * - Account number
//         * - Address
//         * - Assessed Value
//         * - Assessment class (list)
//         * - Neighbourhood: NAME (ward)
//         * - Location (Lat, Long)
//         */
//        System.out.println("Account number: " + accNum);
//
//        System.out.print("Find statistics by neighbourhood: ");
//        String neighbourhood = scanner.nextLine();
//
//        /*
//         * Before output check for invalid states:
//         * - user inputs a number
//         * - user inputs a neighbourhood that does not exist
//         *
//         * Required outputs:
//         * - Same as csv stats (seen above account number)
//         */
//        System.out.println("Statistics (neighbourhood = " + neighbourhood + ")");




    }

    /**
     * Read the contents of a CSV file and return data as a 2D array of String.
     * This function is taken from Lab 1 of CMPT 305, credit goes to Dr Mees.
     *
     * @param csvFileName - the CSV file name
     * @return the values in the CSV file as an array of String arrays
     * @throws IOException - IO exception
     */
    private static String[][] readData(String csvFileName) throws IOException {
        String[][] data;
        int currentIndex = 0;
        // Try-with-resources statement to create a stream to read the CSV file. Automatically closes the resource.
        try (BufferedReader reader = Files.newBufferedReader(Path.of(csvFileName))) {
            // Skip the header - this assumes the first line is a header
            reader.readLine();

            // Create 2D array to store all rows of data as String
            int initialSize = 100;
            data = new String[initialSize][];

            // Read the file line by line and store all rows into a 2D array
            String record;
            while ((record = reader.readLine()) != null) {
                // Parse the record into fields
                String[] values = CsvParser.parseCSVLine(record);

                // Check if the array is full
                if (currentIndex == data.length)
                // Array is full, create and copy all values to a larger array
                {
                    data = Arrays.copyOf(data, data.length * 2);
                }

                data[currentIndex++] = values;
            }
        }

        // Remove empty rows in the array and return it
        return Arrays.copyOf(data, currentIndex);
    }
}
