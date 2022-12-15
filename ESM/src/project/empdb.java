package project;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class empdb
{

    public void displayAllEmployees()
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying all employees");

                String query = "select * from employee";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                                                   // reads a row
                    String name = rs.getString("name");                  // finds a column in the row
                    String dob = rs.getString("dob");
                    String phone = rs.getString("phone_no");
                    String email = rs.getString("email");
                    String acc = rs.getString("account_number");
                    String wt = rs.getString("wage_type");
                    String wr = rs.getString("wage_rate");
                    String id = rs.getString("emp_id");
                    String admin_id = rs.getString("admin_id");

                    Employee emp = new Employee(id, name, dob, phone, email, acc, wt, Integer.parseInt(wr), admin_id);
                    emp.display();
                }
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee getEmployee(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting an employee");

                String query = "select * from employee where emp_id = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String name = rs.getString("name");             // finds a column in the row
                    String dob = rs.getString("dob");
                    String phone = rs.getString("phone_no");
                    String email = rs.getString("email");
                    String acc = rs.getString("account_number");
                    String wt = rs.getString("wage_type");
                    String wr = rs.getString("wage_rate");
                    String admin_id = rs.getString("admin_id");

                    Employee emp = new Employee(id, name, dob, phone, email, acc , wt, Integer.parseInt(wr), admin_id);
                    return emp;
                }
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        Employee empty = new Employee();
        return empty;
    }

    public void displayEmployee(String id)
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying an employee");

                String query = "select * from employee where emp_id = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String name = rs.getString("name");             // finds a column in the row
                    String dob = rs.getString("dob");
                    String phone = rs.getString("phone_no");
                    String email = rs.getString("email");
                    String acc = rs.getString("account_number");
                    String wt = rs.getString("wage_type");
                    String wr = rs.getString("wage_rate");
                    String admin_id = rs.getString("admin_id");

                    Employee emp = new Employee(id, name, dob, phone, email, acc, wt, Integer.parseInt(wr), admin_id);
                    emp.display();
                }
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee() {
        Employee emp = new Employee();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Admin ID: ");
        emp.setAdmin_id(input.nextLine());

        System.out.print("Enter name: ");
        emp.setName(input.nextLine());

        System.out.print("Enter date of birth (dd/mm/yy): ");
        emp.setDob(input.nextLine());

        System.out.print("Enter phone no: ");
        emp.setPhone_no(input.nextLine());

        System.out.print("Enter email: ");
        emp.setEmail(input.nextLine());

        System.out.print("Enter account number: ");
        emp.setAccount_number(input.nextLine());

        System.out.print("Enter wage type (hourly/daily/monthly): ");
        emp.setWage_type(input.nextLine());

        System.out.print("Enter wage rate: ");
        emp.setWage_rate(input.nextInt());
        

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - inserting an employee");

                String query = "select * from employee";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("emp_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                emp.setEmp_id(new_id);

                query = "insert into employee(emp_id, name, dob, email, phone_no, account_number, wage_type, wage_rate, admin_id)" +
                        "values('" + emp.getEmp_id() + "','" + emp.getName() + "'," + "STR_TO_DATE('" + emp.getDob() + "','%d/%m/%y')" + ",'" + emp.getEmail() +
                        "','" + emp.getPhone_no() + "','" +  emp.getAccount_number() + "','" + emp.getWage_type() + "'," +
                        Integer.toString(emp.getWage_rate()) + ",'" + emp.getAdmin_id() + "')";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                this.addPasswordRecord(emp.getEmp_id(), emp.getEmp_id());
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee(Employee emp, String id) {

        emp.setEmp_id(id);

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - inserting employee with id");

                Statement stmt = conn.createStatement();

                String query ="insert into employee(emp_id, name, dob, email, phone_no, account_number, wage_type, wage_rate, admin_id)" +
                        "values('" + emp.getEmp_id() + "','" + emp.getName() + "'," + "STR_TO_DATE('" + emp.getDob() + "','%d/%m/%y')" + ",'" + emp.getEmail() +
                        "','" + emp.getPhone_no() + "','" +   emp.getAccount_number() + "','" + emp.getWage_type() + "'," +
                        emp.getWage_rate() + "," + emp.getAdmin_id() + "')";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                this.addPasswordRecord(emp.getEmp_id(), emp.getEmp_id());
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeEmployee(String id)
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - removing employee");

                this.deletePasswordRecord(id);

                String query = "delete from employee where emp_id = " + id;  // selects all data from database

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeAdmin(String emp_id, String admin_id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - changing Admin id");

                String query = "UPDATE EMPLOYEE SET admin_id = " + admin_id + " WHERE EMP_ID = " + emp_id;

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editEmployee(String id) {
        Employee emp = this.getEmployee(id);

        Scanner input = new Scanner(System.in);

        System.out.println("Enter employee details: ");

        System.out.print("Enter Admin ID: ");
        emp.setAdmin_id(input.nextLine());

        System.out.print("Enter name: ");
        emp.setName(input.nextLine());

        System.out.print("Enter date of birth (dd/mm/yy): ");
        emp.setDob(input.nextLine());

        System.out.print("Enter phone no: ");
        emp.setPhone_no(input.nextLine());

        System.out.print("Enter email: ");
        emp.setEmail(input.nextLine());

        System.out.print("Enter account number: ");
        emp.setAccount_number(input.nextLine());

        System.out.print("Enter wage type (hourly/daily/monthly): ");
        emp.setWage_type(input.nextLine());

        System.out.print("Enter wage rate: ");
        emp.setWage_rate(input.nextInt());

        this.removeEmployee(id);
        this.insertEmployee(emp, id);
    }

    public void updateField(String id, String field, String new_value, boolean isNumeric) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - updating employee field");

                String query;

                if (!isNumeric)
                    query = "update EMPLOYEE set " + field + " = '" + new_value + "' where EMP_ID = " + id;
                else
                    query = "update EMPLOYEE set " + field + " = " + new_value + " where EMP_ID = " + id;

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns true if password correct against that id, false otherwise
    public boolean employeeLogin(String id, String pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - attemptingg employee login");

                String query = "select * from EMPLOYEEPASS where EMP_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String db_pass = "";

                while (rs.next()) {
                    db_pass = rs.getString("password");
                }

                if (pass.equals(db_pass))
                    return true;
            }

            else
            {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    // Returns true if password changed successfully, false otherwise
    public boolean changeEmployeePassword(String id, String old_pass, String new_pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - changing employee password");

                String query = "update EMPLOYEEPASS set PASSWORD = " + new_pass + " where EMP_ID = " + id + " and PASSWORD = " + old_pass;

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);

                return true;
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void deletePasswordRecord(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - deleting employee password record");

                String query = "delete from EMPLOYEEPASS where EMP_ID = " + id;  // selects all data from database

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);

                query = "commit";
                stmt.executeUpdate(query);
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Default password is same as emp_id when employee is created
    public void addPasswordRecord(String id, String pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - adding employee password record");

                Statement stmt = conn.createStatement();

                String query = "insert into EMPLOYEEPASS(EMP_ID, PASSWORD)" + "values('" + id + "','" + pass + "')";
                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isAdmin(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - checking if employee is a Admin");

                String query = "select admin_id from employee";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String admin_id = rs.getString("admin_id");

                    if (admin_id != null) {
                        if (admin_id.equals(id))
                            return true;
                    }
                }
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Employee> getAllEmployees()
    {
        ArrayList<Employee> employeeList = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass()))
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of all employees");

                String query = "select * from employee";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                                                   // reads a row
                    String name = rs.getString("name");                  // finds a column in the row
                    String dob = rs.getString("dob");
                    String phone = rs.getString("phone_no");
                    String email = rs.getString("email");
                    String acc = rs.getString("account_number");
                    String wt = rs.getString("wage_type");
                    String wr = rs.getString("wage_rate");
                    String id = rs.getString("emp_id");
                    String aid = rs.getString("admin_id");

                    Employee emp = new Employee(id, name, dob, phone, email,acc, wt, Integer.parseInt(wr), aid);
                    employeeList.add(emp);
                }
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    public String insertEmployee(Employee emp) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - inserting an employee object");

                String query = "select * from employee";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("emp_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                emp.setEmp_id(new_id);

                query = "insert into employee(emp_id, name, dob, email, phone_no, account_number, wage_type, wage_rate, admin_id)" +
                        "values('" + emp.getEmp_id() + "','" + emp.getName() + "'," + "STR_TO_DATE('" + emp.getDob() + "','%d/%m/%y')" + ",'" + emp.getEmail() +
                        "','" + emp.getPhone_no() + "','" +  emp.getAccount_number() + "','" + emp.getWage_type() + "'," +
                        Integer.toString(emp.getWage_rate()) + ",'" + emp.getAdmin_id() + "')";


                System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                this.addPasswordRecord(emp.getEmp_id(), emp.getEmp_id());

                return new_id;
            }

            else {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public Employee getEmployeeByEmail(String email) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting an employee by email");

                String query = "select * from employee where email = '" + email + "'";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String name = rs.getString("name");             // finds a column in the row
                    String dob = rs.getString("dob");
                    String phone = rs.getString("phone_no");
                    String acc = rs.getString("account_number");
                    String wt = rs.getString("wage_type");
                    String wr = rs.getString("wage_rate");
                    String aid = rs.getString("admin_id");
                    String id = rs.getString("emp_id");

                    return new Employee(id, name, dob, phone, email, acc, wt, Integer.parseInt(wr), aid);
                }
            }

            else
            {
                System.out.println("Failed to make connection!");
            }
        }

        catch (SQLException e)
        {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new Employee();
    }
}