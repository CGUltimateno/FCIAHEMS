package project;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// This class is inherited by Admin
// A super class that has information variables to store information about the employees. Every employee will be given
// a unique employee id (5 digit).

// Employee -> EmployeeDB -> Admin
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

public class Employee
{
    protected String emp_id;
    protected String name;
    protected String dob;
    protected String phone_no;
    protected String email;
    protected String account_number;          //Bank Details
    protected String wage_type;               //Hourly/Daily/Monthly
    protected int wage_rate;
    protected String admin_id;

    String fname, lname;

    public Employee()
    {
        emp_id = name = dob = phone_no = email = account_number = wage_type = admin_id = "";
        this.wage_rate = 0;
        fname = lname = "";
    }

    public Employee(String id)
    {
        emp_id = id;
        name = dob = phone_no = email = account_number = wage_type = admin_id = fname = lname = "";
        this.wage_rate = 0;
    }


    public Employee(String emp_id, String name, String dob, String phone_no, String email, String account_number, String wage_type, int wage_rate, String admin_id) {
        this.emp_id = emp_id;
        this.name = name;
        this.dob = dob;
        this.phone_no = phone_no;
        this.email = email;
        this.account_number = account_number;
        this.wage_type = wage_type;
        this.wage_rate = wage_rate;
        this.admin_id = admin_id;

        fname = name.contains(" ") ? name.split(" ")[0] : name;
        lname = name.contains(" ") ? name.split(" ")[1] : "";
    }

    public Employee(String name, String dob, String phone_no, String email, String account_number, String wage_type, int wage_rate, String admin_id) {
        this.emp_id = "";
        this.name = name;
        this.dob = dob;
        this.phone_no = phone_no;
        this.email = email;
        this.account_number = account_number;
        this.wage_type = wage_type;
        this.wage_rate = wage_rate;
        this.admin_id = admin_id;

        fname = name.contains(" ") ? name.split(" ")[0] : name;
        lname = name.contains(" ") ? name.split(" ")[1] : "";
    }

    public Employee(Employee emp) {
        this.emp_id = emp.getEmp_id();
        this.name = emp.getName();
        this.dob = emp.getDob();
        this.phone_no = emp.getPhone_no();
        this.email = emp.getEmail();
        this.account_number = emp.getAccount_number();
        this.wage_type = emp.getWage_type();
        this.wage_rate = emp.getWage_rate();
        this.admin_id = emp.getAdmin_id();

        fname = name.contains(" ") ? name.split(" ")[0] : name;
        lname = name.contains(" ") ? name.split(" ")[1] : "";
    }

    ///////////////////////////////////////////

    public String getEmp_id()
    {
        return emp_id;
    }
    public void setEmp_id(String emp_id)
    {
        this.emp_id = emp_id;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getDob()
    {
        return dob;
    }
    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public String getPhone_no()
    {
        return phone_no;
    }
    public void setPhone_no(String phone_no)
    {
        this.phone_no = phone_no;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAccount_number()
    {
        return account_number;
    }
    public void setAccount_number(String account_number)
    {
        this.account_number = account_number;
    }

    public String getWage_type()
    {
        return wage_type;
    }
    public void setWage_type(String wage_type)
    {
        this.wage_type = wage_type;
    }

    public int getWage_rate()
    {
        return wage_rate;
    }
    public void setWage_rate(int wage_rate)
    {
        this.wage_rate = wage_rate;
    }

    public String getAdmin_id() {
        return admin_id;
    }
    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }

    public void display() {
        System.out.print("ID: ");
        System.out.println(emp_id);

        System.out.print("Admin ID: ");
        System.out.println(admin_id);

        System.out.print("Name: ");
        System.out.println(name);

        System.out.print("Date of birth: ");
        System.out.println(dob);

        System.out.print("Phone number ");
        System.out.println(phone_no);

        System.out.print("Email: ");
        System.out.println(email);

        System.out.print("Account Number: ");
        System.out.println(account_number);

        System.out.print("Wage Type: ");
        System.out.println(wage_type);

        System.out.print("Wage Rate: ");
        System.out.println(wage_rate);

        System.out.print("Admin ID: ");
        System.out.println(admin_id);

    }

    ////////// CALLING DATABASE METHODS /////////////

    public void getEmployeeRecord(String id) {
        empdb obj = new empdb();
        Employee emp = obj.getEmployee(id);

        this.emp_id = id;
        this.name = emp.getName();
        this.dob = emp.getDob();
        this.phone_no = emp.getPhone_no();
        this.email = emp.getEmail();
        this.account_number = emp.getAccount_number();
        this.wage_type = emp.getWage_type();
        this.wage_rate = emp.getWage_rate();
        this.admin_id = emp.getAdmin_id();
    }

    public void editEmployee() {
        empdb obj = new empdb();
        obj.editEmployee(this.emp_id);
    }

    // Employee logging in
    public boolean employeeLogin() {
        String id, pass;

        Scanner input = new Scanner(System.in);

        System.out.print("Logging in. Enter your employee ID: ");
        id = input.nextLine();

        System.out.print("Enter your password: ");
        pass = input.nextLine();

        empdb obj = new empdb();
        return obj.employeeLogin(id, pass);
    }

    public boolean employeeLogin(String id, String pass) {
        empdb obj = new empdb();
        return obj.employeeLogin(id, pass);
    }

    // Employee changing password
    public void changePassword() {
        String id, old_pass, new_pass;

        Scanner input = new Scanner(System.in);

        System.out.print("Changing password. Enter your employee ID: ");
        id = input.nextLine();

        System.out.print("Enter your current password: ");
        old_pass = input.nextLine();

        System.out.print("Enter your new password: ");
        new_pass = input.nextLine();

        empdb obj = new empdb();
        obj.changeEmployeePassword(id, old_pass, new_pass);
    }

    public boolean changePassword(String curr, String neww) {
        empdb obj = new empdb();
        return obj.changeEmployeePassword(emp_id,curr,neww);
    }

    // edit one field
    public void editEmployeeAccountField(String id, String field, String new_value, boolean isNumeric) {
        empdb obj = new empdb();
        obj.updateField(id,field,new_value, isNumeric);
    }

    // reset employee object
    public void clear() {
        emp_id = name = dob = phone_no = email = account_number = wage_type = admin_id = "";
        this.wage_rate = 0;
    }

    // check is an employee is a manager
    public static boolean isAdmin(String id) {
        empdb obj = new empdb();
        return obj.isAdmin(id);
    }

    public ArrayList<Employee> getListOfEmployees() {
        empdb obj = new empdb();
        return obj.getAllEmployees();
    }

    public void addEmployee() {
        empdb obj = new empdb();
        emp_id = obj.insertEmployee(this);
    }

    public void deleteEmployee() {
        empdb obj = new empdb();
        obj.removeEmployee(this.emp_id);
    }

    public Employee getEmployee(String id) {
        empdb obj = new empdb();
        return obj.getEmployee(id);
    }

    public static boolean checkIfEmailRegistered(String email) {
        empdb obj = new empdb();
        return !(obj.getEmployeeByEmail(email).getEmp_id().isEmpty());
    }
}
