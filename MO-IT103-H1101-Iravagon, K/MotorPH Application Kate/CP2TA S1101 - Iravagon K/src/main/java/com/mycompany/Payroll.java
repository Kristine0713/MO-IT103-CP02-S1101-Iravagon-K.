package com.mycompany;

public class Payroll {

    private Employee employee;
    private double hoursWorked;
    private double gross;
    private double pagibig;
    private double sss;
    private double philhealth;
    private double withholding;
    private double net;

    public Payroll(Employee employee, double hoursWorked, double gross, double pagibig,
                   double sss, double philhealth, double withholding, double net) {

        this.employee = employee;
        this.hoursWorked = hoursWorked;
        this.gross = gross;
        this.pagibig = pagibig;
        this.sss = sss;
        this.philhealth = philhealth;
        this.withholding = withholding;
        this.net = net;
    }

    public Employee getEmployee() { return employee; }
    public double getHoursWorked() { return hoursWorked; }
    public double getGross() { return gross; }
    public double getPagibig() { return pagibig; }
    public double getSSS() { return sss; }
    public double getPhilhealth() { return philhealth; }
    public double getWithholding() { return withholding; }
    public double getNet() { return net; }
}