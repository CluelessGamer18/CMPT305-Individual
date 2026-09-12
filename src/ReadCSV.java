/* This code is provided for use by students in CMPT 305 at MacEwan University.
 * Do not distribute or share this code outside the course. */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * This is modified code of the provided example code
 * that was provided in the labs.
 */
public class ReadCSV {
    public static void main(String[] args) {

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

    /**
     * Read a 2D String array and return the wards found within a specific row
     *
     * @param data - the 2D String array
     * @return the wards found with no duplicates as an array of strings
     */
    private static String[] findWards(String[][] data) {
        // Initialize the string array to collect the wards
        String[] wards;
        int currentPosition = 0;

        // Initialize the size of the ward array
        int initialSize = 10;
        wards = new String[initialSize];

        // Initialize a string array to split ward lists if needed
        String[] splitRow;

        // Loop through the 2D array
        for (String[] row : data) {

            // Update the size of the string array to keep it from overflowing
            if (currentPosition == wards.length) {
                wards = Arrays.copyOf(wards, wards.length + 1);
            }

            /**
             * If the row contains a "," it needs to be split before it can be added to the array
             * If it does not contain a comma but already contains the word or is an empty space do not add to the array
             * If the above cases are not true then add the current word to the next position in the array
             */
            if (row[6].contains(",")){
                // Split at the ", " to ensure that the item follwing the comma is properly addressed
                splitRow = row[6].split(", ");

                // Loop through the items in the new split array in order to put them into the ward array
                for (String item: splitRow){
                    // Duplicated code from outside the comma split code, could probably figure out a way to remove the need for duplicated code
                    if (currentPosition == wards.length) {
                        wards = Arrays.copyOf(wards, wards.length + 1);
                    }

                    if (Arrays.asList(wards).contains(item) || (item == "")) {

                    } else {
                        wards[currentPosition++] = item;
                    }

                }
            } else if (Arrays.asList(wards).contains(row[6]) || (row[6] == "")) {

            } else {
                wards[currentPosition++] = row[6];
            }

        }

        return Arrays.copyOf(wards, currentPosition);
    }

    /**
     * Read through the 2D String array and return the assessment classes found in row[10] of the file
     *
     * @param data - the 2D String array
     * @return the assessment classes found within the data file
     */
    private static String[] assessmentClasses(String[][] data) {
        String[] classes;
        int currentPosition = 0;

        // Initialize size of classes array
        int initialSize = 5;
        classes = new String[initialSize];

        // Loop through 2D string array and add each new assessment class to the array
        for (String[] row : data) {

            // Update size of array
            if (currentPosition == classes.length) {
                classes = Arrays.copyOf(classes, classes.length + 1);
            }

            // If the array already contains the current string do nothing, else add it to the array
            if (Arrays.asList(classes).contains(row[10])) {

            } else {
                classes[currentPosition++] = row[10];
            }

        }
        return Arrays.copyOf(classes, currentPosition);

    }

    /**
     * Read through 2D array data and return the lowest assessed value
     *
     * @param data - the 2D String array
     * @return the lowest value present in the dataset as an Integer
     */
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

    /**
     * Read through 2D array data and return the largest assessed value
     *
     * @param data - the 2D String array
     * @return the largest value present in the dataset as an Integer
     */
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
     * Print the number of entries in the data
     *
     * @param data - 2D array containing data
     */
    private static void printData(String[][] data) {

        System.out.println("The number of records: " + data.length); // Prints the number of records

    }

    /**
     * Print the names of each ward in the data as well as the number of wards present in the data
     *
     * @param wards - a String array
     */
    private static void printWards(String[] wards) {

        System.out.println("Wards: " + Arrays.toString(wards));
        System.out.println(wards.length);

    }

    /**
     * Print the names of the assessment classes found within the data
     *
     * @param classes - a String array
     */
    private static void printClasses(String[] classes) {
        System.out.println("Classes: " + Arrays.toString(classes));
    }

    /**
     * Print the lowest assessed value found within the data
     *
     * @param lowest - an Integer value
     */
    private static void printLowest(int lowest){
        System.out.println("The lowest assessment is: " + lowest);
    }

    /**
     * Print the largest assessed value found within the data
     *
     * @param largest - an Integer value
     */
    private static void printLargest(int largest){
        System.out.println("The largest assessment is:" + largest);
    }

}
