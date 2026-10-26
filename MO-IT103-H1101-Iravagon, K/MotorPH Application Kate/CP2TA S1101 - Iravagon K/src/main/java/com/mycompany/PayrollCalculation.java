package com.mycompany;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PayrollCalculation {

    public double computeHours(LocalTime login, LocalTime logout) {

        LocalTime graceTime      = LocalTime.of(8, 10);
        LocalTime regularDayStart = LocalTime.of(8, 0);
        LocalTime regularDayEnd   = LocalTime.of(17, 0);

        if (logout.isAfter(regularDayEnd))    logout = regularDayEnd;
        if (login.isBefore(regularDayStart))  login  = regularDayStart;
        if (!login.isAfter(graceTime))        login  = regularDayStart;

        long minutesWorked = Duration.between(login, logout).toMinutes();

        if (minutesWorked > 60) minutesWorked -= 60;
        else minutesWorked = 0;

        double hours = minutesWorked / 60.0;
        return Math.min(hours, 8.0);
    }

    public double calculateGross(double payHours, double hourlySalary) {
        return payHours * hourlySalary;
    }

    // BUG FIX: Pag-IBIG employee share is capped at ₱100/month
    public double computePagibig(double monthlyGross) {
        if (monthlyGross >= 1000 && monthlyGross <= 1500) return Math.min(monthlyGross * 0.01, 100.0);
        else if (monthlyGross > 1500)                     return Math.min(monthlyGross * 0.02, 100.0);
        return 0;
    }

    public double computeSSS(double monthlyGross) {
        double range        = 3250;
        double contribution = 135;

        if (monthlyGross < 3250)  return 135;
        if (monthlyGross >= 24750) return 1125;

        while (monthlyGross >= range) {
            range        += 500;
            contribution += 22.50;
        }
        return contribution;
    }

    public double computePhilHealth(double monthlyGross) {
        if (monthlyGross <= 10000) return 300.0 / 2;
        else if (monthlyGross < 60000) return (monthlyGross * 0.03) / 2;
        else return 1800.0 / 2;
    }

    public double computeWithholding(double monthlyGross) {
        if (monthlyGross < 20832)  return 0;
        else if (monthlyGross < 33333)  return (monthlyGross - 20832) * 0.20;
        else if (monthlyGross < 66667)  return ((monthlyGross - 33333) * 0.25) + 2500;
        else if (monthlyGross < 166667) return ((monthlyGross - 66667) * 0.30) + 10833;
        else if (monthlyGross < 666667) return ((monthlyGross - 166667) * 0.32) + 40833.33;
        else                            return ((monthlyGross - 666667) * 0.35) + 200833.33;
    }

    public Payroll computePayroll(Employee employee,
                                  ArrayList<AttendanceRecord> attendanceData) {

        double totalHours = 0;

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");

        for (AttendanceRecord record : attendanceData) {

            if (!record.getEmployeeNumber().equals(employee.getEmployeeNumber()))
                continue;

            try {

                LocalTime login  = LocalTime.parse(record.getLogIn(), timeFormat);
                LocalTime logout = LocalTime.parse(record.getLogOut(), timeFormat);

                totalHours += computeHours(login, logout);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        totalHours = roundvalue(totalHours);

        double gross       = roundvalue(calculateGross(totalHours, employee.getHourlySalary()));
        double pagibig     = roundvalue(computePagibig(gross));
        double sss         = roundvalue(computeSSS(gross));
        double philhealth  = roundvalue(computePhilHealth(gross));
        double withholding = roundvalue(computeWithholding(gross));
        double net         = roundvalue(gross - (pagibig + sss + philhealth + withholding));

        return new Payroll(employee, totalHours, gross, pagibig, sss, philhealth, withholding, net);
    }

    public Payroll computePayrollByMonth(Employee employee,
                                         ArrayList<AttendanceRecord> attendanceData,
                                         int month) {

        double totalHours = 0;

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");

        for (AttendanceRecord record : attendanceData) {

            if (!record.getEmployeeNumber().equals(employee.getEmployeeNumber()))
                continue;

            try {
                LocalDate date = LocalDate.parse(record.getDate(), dateFormat);

                if (date.getMonthValue() != month)
                    continue;

                LocalTime login  = LocalTime.parse(record.getLogIn(), timeFormat);
                LocalTime logout = LocalTime.parse(record.getLogOut(), timeFormat);

                totalHours += computeHours(login, logout);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        totalHours = roundvalue(totalHours);

        double gross       = roundvalue(calculateGross(totalHours, employee.getHourlySalary()));
        double pagibig     = roundvalue(computePagibig(gross));
        double sss         = roundvalue(computeSSS(gross));
        double philhealth  = roundvalue(computePhilHealth(gross));
        double withholding = roundvalue(computeWithholding(gross));
        double net         = roundvalue(gross - (pagibig + sss + philhealth + withholding));

        return new Payroll(employee, totalHours, gross, pagibig, sss, philhealth, withholding, net);
    }

    private double roundvalue(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}