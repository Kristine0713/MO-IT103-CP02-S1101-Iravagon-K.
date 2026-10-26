package com.mycompany;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AttendanceDataHandler {

    private static final String ATTENDANCE_CSV_PATH =
            "src/main/java/CSV/Attendance Record.csv";

    private static final String CSV_HEADER =
            "Employee #,Last Name,First Name,Date,Log In,Log Out";

    /**
     * Appends a single new attendance record to the Attendance CSV file.
     * Creates the file (and header) automatically if it does not yet exist.
     * @param record the AttendanceRecord object to write
     * @return true if successful, false if an error occurred
     */
    public static boolean appendAttendanceToCSV(AttendanceRecord record) {

        File file = new File(ATTENDANCE_CSV_PATH);
        boolean isNewFile = !file.exists() || file.length() == 0;

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            if (isNewFile) {
                writer.write(CSV_HEADER);
                writer.newLine();
            }

            writer.write(formatRow(record));
            writer.newLine();
            return true;

        } catch (IOException e) {
            System.err.println("AttendanceDataHandler - write error: " + e.getMessage());
            return false;
        }
    }

    private static String formatRow(AttendanceRecord r) {
        return String.join(",",
                sanitize(r.getEmployeeNumber()),
                sanitize(r.getLastName()),
                sanitize(r.getFirstName()),
                sanitize(r.getDate()),
                sanitize(r.getLogIn()),
                sanitize(r.getLogOut())
        );
    }

    private static String sanitize(String value) {
        if (value == null) return "";
        String trimmed = value.trim();
        return trimmed.contains(",") ? "\"" + trimmed + "\"" : trimmed;
    }
}
