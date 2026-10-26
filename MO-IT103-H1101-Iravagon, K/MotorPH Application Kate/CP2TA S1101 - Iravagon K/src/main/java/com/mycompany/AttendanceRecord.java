package com.mycompany;

public class AttendanceRecord {

    private String employeeNumber;
    private String lastName;
    private String firstName;
    private String date;
    private String logIn;
    private String logOut;

    public AttendanceRecord(String employeeNumber, String lastName, String firstName,
                            String date, String logIn, String logOut) {
        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.date = date;
        this.logIn = logIn;
        this.logOut = logOut;
    }

    public String getEmployeeNumber() { return employeeNumber; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getDate() { return date; }
    public String getLogIn() { return logIn; }
    public String getLogOut() { return logOut; }
}