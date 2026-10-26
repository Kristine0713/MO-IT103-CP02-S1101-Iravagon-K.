## Overview
The MotorPH Employee Payroll System is a Java Swing desktop application with separate portals for employees and payroll staff. Employees can view their information and payroll. Payroll staff can view payroll reports and manage employee and attendance records.

## Requirements
- JDK 8 or newer
- **Preferred IDE: Apache NetBeans**
- The complete project source code
- The employee and attendance CSV files

## How to Run in NetBeans
1. Open Apache NetBeans.
2. Select **File > Open Project** and choose the full project folder.
3. In the project explorer, locate `src/main/java/com/mycompany/CP2MS1Grp10.java`.
4. Make sure the required CSV files are in `src/main/java/CSV/`.
5. Right-click `CP2MS1Grp10.java` and select **Run File**. If prompted for the main class, use `com.mycompany.CP2MS1Grp10`.
6. If the IDE asks for a working directory, use the project root folder.

## How to Run in other IDE
1. Open the **full project folder** in your Java IDE. Do not open individual `.java` files on their own.
2. In the project explorer, locate the `com.mycompany` package, usually under `src/main/java/com/mycompany/`.
3. Make sure the required CSV files are in `src/main/java/CSV/`.
4. Run `CP2MS1Grp10.java`. If your IDE asks for the main class, enter `com.mycompany.CP2MS1Grp10`.
5. If the IDE asks for a working directory, use the project root folder.

## Demo Login
- **Employee portal:** username `employee`, password `12345`
- **Payroll staff portal:** username `payroll_staff`, password `12345`
These are demo credentials defined in the application.

## Basic Navigation

**Employee portal**
1. Log in with the employee credentials.
2. Enter your employee number, and select Submit.
3. Select **View My Information** or **View My Payroll**.
4. Use **Back** to return to the employee menu, or **Logout** to return to the login screen.

**Payroll staff portal**
After logging in, choose one of these options:
- **View All Employee Payroll** — see payroll summaries for employees.
- **View One Employee Payroll** — enter an employee number when prompted
- **Manage Employee Records** — add a new employee, or select a row to edit or delete it. Use Save Changes to keep an edit, Cancel to leave edit mode, and Clear to reset the form.
- **Manage Attendance Records** — add, edit, or delete an attendance entry.
Use **Back** to return to the staff menu, or **Logout** to return to the login screen.

## Data Files
The application reads these files when it starts:
- `src/main/java/CSV/Employee Details.csv`
- `src/main/java/CSV/Attendance Record.csv`
Keep the CSV files in these locations and preserve their expected columns and order. You can open CSV files in a spreadsheet program to review them; back them up before making changes.

