package project;

public class LoggedInUsers {
    private static String cust_id;
    private static Customer cust = new Customer();

    private static String emp_id;
    private static Employee emp = new Employee();

    private static String sp_id;
    private static ServiceProvider sp = new ServiceProvider();

    ////////////// CUST METHODS ////////////////////

    public static String getCust_id() {
        return cust_id;
    }


    public static void initCust(String idOrEmail) {
        if (idOrEmail.indexOf('@') == -1)
            cust_id = idOrEmail;

        else {
            cust_id = cust.getCustomerIDbyEmail(idOrEmail);
        }

        cust.getCustomerDetails(cust_id);
    }

    public static void clearCust() {
        cust_id = "";
        cust.clear();
    }

    public static Customer getCust() {
        return cust;
    }

    public static void setCust(Customer cust) {
        LoggedInUsers.cust = cust;
    }

            ///////////// EMP METHODS //////////////////

    public static Employee getEmp() {return emp;}
    public static void setEmp(Employee emp) {
        LoggedInUsers.emp = emp;
    }

    public static void initEmp(String id) {
        emp_id = id;
        emp.getEmployeeRecord(emp_id);
    }

    public static void clearEmp() {
        emp_id = "";
        emp.clear();
    }
    ///////////// SP METHODS //////////////////
    public static ServiceProvider getsp() {
        return sp;
    }
    public static void initsp(String id) {
        sp_id = id;
        sp.getSPRecord(sp_id);
    }

    public static void setsp(ServiceProvider sp) {
        LoggedInUsers.sp = sp;
    }
}
