package com.mycompany;

public class Verification {
    
    public String verify(String username, String password){
        
        if (username.equals("employee") && password.equals("12345")){
            return "employee";
        }
        
        else if (username.equals("payroll_staff") && password.equals("12345")){
            return "payroll_staff";
        }
        
        else {
            return null;
        }
        
    }
}