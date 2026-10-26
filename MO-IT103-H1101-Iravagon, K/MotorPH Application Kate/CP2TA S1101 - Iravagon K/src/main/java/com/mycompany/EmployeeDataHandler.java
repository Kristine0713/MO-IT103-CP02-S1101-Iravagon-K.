package com.mycompany;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeDataHandler {

    // Registry CSV file
    private static final String REGISTRY_CSV_PATH =
            "src/main/java/CSV/MotorPH_Employee_Registry.csv";

    // Updated header with government IDs
    private static final String CSV_HEADER =
            "Employee Number,Last Name,First Name,Birthday,Phone Number,Position,SSS Number,TIN Number,PhilHealth Number,Hourly Salary";

    /**
     * Appends a single new employee record to the registry CSV file.
     * Creates the file (and header) automatically if it does not yet exist.
     * @param emp the Employee object containing the details to be written to the CSV
     * @return true if the record was successfully appended, false if an error occurred
     */
    public static boolean appendEmployeeToCSV(Employee emp) {

        File file = new File(REGISTRY_CSV_PATH);
        boolean isNewFile = !file.exists() || file.length() == 0;

        // Make sure the destination directory exists
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            // Write header only when the file is brand new / empty
            if (isNewFile) {
                writer.write(CSV_HEADER);
                writer.newLine();
            }

            writer.write(formatRow(emp));
            writer.newLine();
            return true;

        } catch (IOException e) {
            System.err.println("EmployeeDataHandler - write error: " + e.getMessage());
            return false;
        }
    }

    /** Formats one Employee as a CSV row, quoting any field that contains a comma. */
    private static String formatRow(Employee emp) {
        return String.join(",",
                sanitize(emp.getEmployeeNumber()),
                sanitize(emp.getLastName()),
                sanitize(emp.getFirstName()),
                sanitize(emp.getBirthday()),
                sanitize(emp.getPhoneNumber()),
                sanitize(emp.getPosition()),
                sanitize(emp.getSssNumber()),
                sanitize(emp.getTinNumber()),
                sanitize(emp.getPhilHealthNumber()),
                String.format("%.2f", emp.getHourlySalary())
        );
    }

    /** Wraps the value in quotes if it contains a comma; trims whitespace. */
    private static String sanitize(String value) {
        if (value == null) return "";
        String trimmed = value.trim();
        return trimmed.contains(",") ? "\"" + trimmed + "\"" : trimmed;
    }
}