package project;

import java.util.*;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// This class will store/edit/remove the Admin's information The information will be stored in AdminDB
// It is inherited from Employee
// Admin -> AdminDB
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class Admin extends Employee
{

    public Admin()
    {
        super();
    }

    /////////////// CALLING EMPLOYEE DATABASE METHODS ///////////////

    public void addEmployee()                               //Add a new employee
    {
        empdb obj = new empdb();
        emp_id = obj.insertEmployee(this);
    }

    public void deleteEmployee()                            //Delete an existing Employee
    {
        empdb obj = new empdb();
        String id;
        System.out.print("Removing an employee. Enter employee ID: ");
        Scanner input = new Scanner(System.in);
        id = input.nextLine();

        obj.removeEmployee(id);
    }


}
