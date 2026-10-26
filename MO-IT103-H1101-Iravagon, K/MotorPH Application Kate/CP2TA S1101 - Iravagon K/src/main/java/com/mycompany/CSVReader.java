package com.mycompany;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVReader {
    
    // Paths.get automatically uses '\' on Windows and '/' on macOS/Linux
    private final Path employeeDetailsPath = Paths.get("src", "main", "java", "CSV", "Employee Details.csv");
    private final Path attendanceDetailsPath = Paths.get("src", "main", "java", "CSV", "Attendance Record.csv");
    
    // Fallback strings to maintain compatibility if other classes access them directly
    public final String employeeDetailsCSV = employeeDetailsPath.toString();
    public final String attendanceDetailsCSV = attendanceDetailsPath.toString();
    
    // ArrayList to store data from csv files
    public ArrayList<Employee> employeeData = new ArrayList<>();
    public ArrayList<AttendanceRecord> attendanceData = new ArrayList<>();
    
    public void readCSVFiles() { // Method for reading CSV files uploaded
        String line;
        
        // Check if file exists first to avoid confusing exceptions on macOS
        if (!Files.exists(employeeDetailsPath)) {
            System.err.println("CRITICAL ERROR: Employee CSV file not found at path: " + employeeDetailsPath.toAbsolutePath());
            return;
        }
        
        // We use InputStreamReader paired with StandardCharsets.UTF_8 for system-independent encoding
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(employeeDetailsPath.toFile()), StandardCharsets.UTF_8))) {
            
            br.readLine(); // Skip header row
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Split values with comma but ignores commas inside ""
                
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();
                }
                
                double hourlySalary = 0.0;
                if (data.length > 18 && !data[18].isEmpty()) {
                    // Try-Catch block for hourly salary data
                    try {
                        hourlySalary = Double.parseDouble(data[18]);
                    } catch (NumberFormatException e) {
                        System.out.println("Error display hourly rate for employee " + data[0]);
                    }
                }
                
                // Updated Employee constructor with SSS, TIN, Philhealth
                Employee employee = new Employee(
                    (data.length > 0) ? data[0] : "",    // Employee Number
                    (data.length > 1) ? data[1] : "",    // Last Name
                    (data.length > 2) ? data[2] : "",    // First Name
                    (data.length > 3) ? data[3] : "",    // Birthday
                    (data.length > 5) ? data[5] : "",    // Phone Number
                    (data.length > 11) ? data[11] : "",  // Position
                    hourlySalary,
                    (data.length > 6) ? data[6] : "",    // SSS Number
                    (data.length > 8) ? data[8] : "",    // TIN Number
                    (data.length > 7) ? data[7] : ""     // PhilHealth Number
                );                       
                
                employeeData.add(employee);
            }
            
        } catch (Exception e) {
            System.err.println("Error reading Employee CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void readAttendanceCSV() { // Method for reading attendance CSV
        String line;
        
        // Verify path safety
        if (!Files.exists(attendanceDetailsPath)) {
            System.err.println("CRITICAL ERROR: Attendance CSV file not found at path: " + attendanceDetailsPath.toAbsolutePath());
            return;
        }
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(attendanceDetailsPath.toFile()), StandardCharsets.UTF_8))) {
            
            br.readLine(); // Skip header row
            
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "").trim();
                }

                AttendanceRecord attendance = new AttendanceRecord(
                    (data.length > 0) ? data[0] : "",
                    (data.length > 1) ? data[1] : "",
                    (data.length > 2) ? data[2] : "",
                    (data.length > 3) ? data[3] : "",
                    (data.length > 4) ? data[4] : "",
                    (data.length > 5) ? data[5] : ""
                );

                attendanceData.add(attendance);
            }

        } catch (Exception e) {
            System.err.println("Error reading Attendance CSV: " + e.getMessage());
            e.printStackTrace();
        }
    }
        
    public ArrayList<Employee> getEmployeeData() {
        return employeeData; // Returns the list of employees from CSV
    }

    public ArrayList<AttendanceRecord> getAttendanceData() {
        return attendanceData; // Returns the data from the Attendance CSV
    }
}
