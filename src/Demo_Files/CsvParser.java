package Demo_Files;/* This code is provided for use by students in CMPT 305 at MacEwan University.
 * Do not distribute or share this code outside the course. */

import java.util.Arrays;

public class CsvParser {
    /**
     * Parses a record from a CSV file into fields.
     * This version uses regular String arrays.
     *
     * <p>
     * This method handles escaped quotes correctly ({@code ""}) and throws an exception if:</p>
     * <ul>
     *   <li>a quote is detected inside an unquoted field</li>
     *   <li>the record is not terminated with a closing quote.</li>
     * </ul>
     * <p>
     * This method parses a CSV file line by line and therefore does not fully comply with
     * RFC&nbsp;4180, which allows fields to contain line breaks.
     * </p>
     * <p>
     * Note: If a field contains an internal double quote, the entire field must be enclosed in double quotes.
     * <a href="https://inventivehq.com/blog/handling-special-characters-in-csv-files">
     *     handling-special-characters-in-csv-files</a>
     * </p>
     * <p>Written by Philip Mees for CMPT&nbsp;305</p>
     *
     * @param record a record from a CSV file
     * @return the fields in the record
     * @throws IllegalArgumentException if double quotes are misplaced
     */
    public static String[] parseCSVLine(String record) {
        int index = 0;
        int initialSize = 10;
        String[] fields = new String[initialSize];
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < record.length(); i++) {
            char c = record.charAt(i);

            if (c == '"') {
                if (inQuotes) {
                    if (i + 1 < record.length() && record.charAt(i + 1) == '"') {
                        // Escaped quote ("")
                        current.append('"');
                        i++; // Skip the second quote
                    } else {
                        // Closing quote
                        inQuotes = false;
                    }
                } else {
                    // Opening quote (only valid at start of field)
                    if (!current.isEmpty()) {
                        throw new IllegalArgumentException("Quote in unquoted field");
                    }
                    inQuotes = true;
                }
            } else if (c == ',' && !inQuotes) {
                // End of field

                // Check if the array is full
                if (index == fields.length)
                // Array is full, create and copy all values to a larger array
                {
                    fields = Arrays.copyOf(fields, fields.length * 2);
                }

                fields[index++] = current.toString();
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        if (inQuotes) {
            // Missing closing quote
            throw new IllegalArgumentException("Unterminated quoted field");
        }

        // Check if the array is full
        if (index == fields.length)
        // Array is full, create and copy all values to a larger array
        {
            fields = Arrays.copyOf(fields, fields.length * 2);
        }
        // Add last field
        fields[index++] = current.toString();

        // return an array copy without unused elements.
        return Arrays.copyOf(fields, index);
    }
}
