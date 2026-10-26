package com.mycompany;

public class Employee {

    // Personal Info
    private final String employeeNumber; // sequential, pre-assigned
    private final String lastName;
    private final String firstName;
    private final String birthday;
    private final String phoneNumber;
   
    // Company Info
    private final String position;
    private final String sssNumber;
    private final String tinNumber;
    private final String philHealthNumber;
    private final double hourlySalary;

    public Employee(String employeeNumber, String lastName, String firstName, String birthday, String phoneNumber,String position, double hourlySalary, String sssNumber, String tinNumber, String philHealthNumber) {

        this.employeeNumber = employeeNumber;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        
        this.position = position;
        this.sssNumber = sssNumber;
        this.tinNumber = tinNumber;
        this.philHealthNumber = philHealthNumber;
        this.hourlySalary = hourlySalary;
        
    }

    //Getters
    public String getEmployeeNumber() { return employeeNumber; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getBirthday() { return birthday; }
    public String getPhoneNumber() { return phoneNumber; }
    
    public String getPosition() { return position; }
    public String getSssNumber() { return sssNumber; }
    public String getTinNumber() { return tinNumber; }
    public String getPhilHealthNumber() { return philHealthNumber; }
    public double getHourlySalary() { return hourlySalary; } 
}