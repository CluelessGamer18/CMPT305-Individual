package csv_classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class PropertyAssessments {
    private List<PropertyAssessment> assessments;

    public PropertyAssessments(){
        this.assessments = new ArrayList<>();
    }

    public static PropertyAssessments fromCsv(String filename) throws IOException{
        String[][] data = readData(filename);
        return buildAssessments(data);
    }

    public static PropertyAssessments buildAssessments(String[][] data){
        PropertyAssessments result  = new PropertyAssessments();
        for (String[] row: data) {
            int accountNumber = Integer.parseInt(row[0]);
            String suite = row[1];
            int houseNumber = parseIntOrDefault(row[2], 0);
            String streetName = row[3];
            int neighbourhoodId = parseIntOrDefault(row[4], 0);
            String neighbouhoodName = row[5];
            String ward = row[6];
            int assessedValue = Integer.parseInt(row[7]);
            String taxClass = row[8];
            boolean garage = row[9].trim().equalsIgnoreCase("Y");
            List<AssessmentClass> classes = new ArrayList<>();
            double latitude = Double.parseDouble(row[16]);
            double longitude = Double.parseDouble(row[17]);

            for (int i=0; i<3; i++){
                int index = 10 + i;
                String name = row[index].trim();
                if (!name.isEmpty()) {
                    classes.add(new AssessmentClass(row[index], Integer.parseInt(row[index+3])));
                }
            }

            Neighbourhood neighbourhood = new Neighbourhood(neighbourhoodId, neighbouhoodName, ward);
            Address address = new Address(suite, houseNumber, streetName);
            Location location = new Location(latitude, longitude);

            result.addAssessment(new PropertyAssessment(accountNumber, assessedValue, garage, taxClass, address, neighbourhood, classes, location));

        }
        return result;

    }

    public void addAssessment(PropertyAssessment assessment){
        assessments.add(assessment);
    }

    public List<PropertyAssessment> getAssessments(){
        return new ArrayList<>(assessments);
    }

    public PropertyAssessment filterById(int accountId){
        for (PropertyAssessment pa: assessments){
            if (pa.getAccountNumber() == accountId){
                return pa;
            }
        }
        return null;
    }

    public PropertyAssessments filterByNeighbourhood(String name){
        PropertyAssessments result = new PropertyAssessments();
        for (PropertyAssessment pa: assessments){
            if (Objects.equals(pa.getNeighbourhood().getNeighbourhoodName(), name)){
                result.addAssessment(pa);
            }
        }
        return result;
    }

    public long sum(){
        long total = 0;
        for (PropertyAssessment pa: assessments){
            total += pa.getAssessedValueRaw();
        }
        return total;
    }

    public int min(){
        if (assessments.isEmpty()){
            return 0;
        }
        int min = assessments.get(0).getAssessedValueRaw();
        for (PropertyAssessment pa: assessments){
            if (pa.getAssessedValueRaw() < min){
                min = pa.getAssessedValueRaw();
            }
        }
        return min;
    }

    public int size(){
        int size = 0;
        if (assessments.isEmpty()) {
            return size;
        }

        for (PropertyAssessment pa: assessments){
            size += 1;
        }
        return size;
    }

    public int max(){
        if (assessments.isEmpty()){
            return 0;
        }
        int max = assessments.get(0).getAssessedValueRaw();
        for (PropertyAssessment pa: assessments){
            if (pa.getAssessedValueRaw() > max){
                max = pa.getAssessedValueRaw();
            }
        }
        return max;
    }

    public int range(){
        return max() - min();
    }

    public long mean(){
        if (assessments.isEmpty()){
            return 0;
        }

        return  sum() / assessments.size();
    }

    public int median(){
        if (assessments.isEmpty()){
            return 0;
        }

        List<Integer> values= new ArrayList<>();
        for (PropertyAssessment pa: assessments){
            values.add(pa.getAssessedValueRaw());
        }

        Collections.sort(values);

        int mid = values.size() / 2;
        if (values.size() % 2 != 0){
            return values.get(mid);
        }
        return (values.get(mid - 1) + values.get(mid)) / 2;

    }

    /**
     * A helper function to check whether a row is blank before comparing it as an integer value.
     * Default value should be set to 0.
     * @param s
     * @param defaultValue
     * @return
     */
    private static int parseIntOrDefault(String s, int defaultValue){
        if (s == null || s.isBlank()) return defaultValue;
        return Integer.parseInt(s.trim());
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
