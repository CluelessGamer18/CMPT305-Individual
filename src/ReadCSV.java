/* This code is provided for use by students in CMPT 305 at MacEwan University.
 * Do not distribute or share this code outside the course. */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * An example of how to read and process a CSV file.
 * It uses that CsvParser class.
 * Copy both files into a package of your choice
 * This code uses only basic features of the Java language.
 * Feel free to modify this example.
 */
public class ReadCSV {
    public static void main(String[] args) {
        //String csvFileName = "stocks.csv";
        String csvFileName = "Property_Assessment_Data_2026.csv";

        try {
            String[][] data = readData(csvFileName);
            String[] wards = findWards(data);
            String[] classes = assessmentClasses(data);
            int lowest = lowestAssessed(data);
            int largest = largestAssessed(data);
            printData(data);
            printLowest(lowest);
            printLargest(largest);
            printWards(wards);
            printClasses(classes);
        } catch (IOException e) {
            System.out.println("Failed to read " + csvFileName);
        }
    }

    /**
     * Read the contents of a CSV file and return data as a 2D array of String.
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
                // A simple CSV line with fields that are not surrounded by double-quotes,
                // and all commas acting as separators, can be split using the String.plit() method.
                // String[] values = record.split(",");
                // Otherwise, a more general CSV parsing is required:
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

    private static String[] findWards(String[][] data) {
        String[] wards;
        int currentPosition = 0;

        int initialSize = 10;
        wards = new String[initialSize];

        String[] splitRow;
        for (String[] row : data) {

            if (currentPosition == wards.length) {
                wards = Arrays.copyOf(wards, wards.length + 1);
            }

            if (row[6].contains(",")){
                splitRow = row[6].split(", ");

                for (String item: splitRow){
                    if (currentPosition == wards.length) {
                        wards = Arrays.copyOf(wards, wards.length + 1);
                    }

                    if (Arrays.asList(wards).contains(item) || (item == "")) { // temp fix

                    } else {
                        wards[currentPosition++] = item;
                    }

                }
            } else if (Arrays.asList(wards).contains(row[6]) || (row[6] == "")) { // temp fix

            } else {
                wards[currentPosition++] = row[6];
            }

        }

        return Arrays.copyOf(wards, currentPosition);
    }

    private static String[] assessmentClasses(String[][] data) {
        String[] classes;
        int currentPosition = 0;

        int initialSize = 5;
        classes = new String[initialSize];

        for (String[] row : data) {

            if (currentPosition == classes.length) {
                classes = Arrays.copyOf(classes, classes.length + 1);
            }

            if (Arrays.asList(classes).contains(row[10])) {

            } else {
                classes[currentPosition++] = row[10];
            }

        }
        return Arrays.copyOf(classes, currentPosition);

    }

    private static Integer lowestAssessed(String[][] data) {
        int lowest = Integer.MAX_VALUE;

        for (String[] row : data) {
            int rowNumber = Integer.parseInt(row[7]);

            if (rowNumber <= lowest) {
                lowest = rowNumber;
            }
        }

        return lowest;
    }

    private static Integer largestAssessed(String[][] data) {
        int largest = Integer.MIN_VALUE;

        for (String[] row: data){
            int rowNumber = Integer.parseInt(row[7]);

            if (rowNumber >= largest){
                largest = rowNumber;
            }
        }

        return largest;
    }

    /**
     * Print all rows of data.
     *
     * @param data - 2D array containing data
     */
    private static void printData(String[][] data) {

        System.out.println("The number of records: " + data.length); // Prints the number of records

    }

    private static void printWards(String[] wards) {

        System.out.println("Wards: " + Arrays.toString(wards));
        System.out.println(wards.length);

    }

    // Prints the assessment classes
    private static void printClasses(String[] classes) {
        System.out.println("Classes: " + Arrays.toString(classes));
    }

    // Prints the lowest assessment value
    private static void printLowest(int lowest){
        System.out.println("The lowest assessment is: " + lowest);
    }

    // Prints the largest assessment value
    private static void printLargest(int largest){
        System.out.println("The largest assessment is:" + largest);
    }

}
