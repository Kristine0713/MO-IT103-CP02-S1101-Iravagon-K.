package com.mycompany;

import java.util.ArrayList;

public class DataManagement {
    
    public static ArrayList<Employee> employees;
    public static ArrayList<AttendanceRecord> attendance;

    public static void loadData(){
        
        //Creates a csvreader object
        CSVReader reader = new CSVReader();
        
        // Reader for CSV files
        reader.readCSVFiles();
        reader.readAttendanceCSV();
        
        //Stores data to public static
        employees = reader.getEmployeeData();
        attendance = reader.getAttendanceData();
    }
    
}
