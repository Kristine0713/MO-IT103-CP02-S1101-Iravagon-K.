package com.mycompany;

public class EmployeeFinder { 

    public static Employee find(String employeeNumber) {

        for (Employee e : DataManagement.employees) {

            if (e.getEmployeeNumber().equals(employeeNumber)) {
                return e;
            }
        }

        return null;
    }
}