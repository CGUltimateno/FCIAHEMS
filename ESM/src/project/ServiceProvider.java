package project;


public class ServiceProvider {
    protected String sp_id;
    protected String name;
    protected String phone_no;
    protected String account_number;

    String fname, lname;

    public ServiceProvider()
    {
        sp_id = name = phone_no = account_number = "";
        fname = lname = "";
    }

    public ServiceProvider(String id)
    {
        sp_id = id;
        name = phone_no = account_number = fname = lname = "";
    }

    public ServiceProvider(String sp_id, String name, String phone_no, String account_number) {
        this.sp_id = sp_id;
        this.name = name;
        this.phone_no = phone_no;
        this.account_number = account_number;

        fname = name.contains(" ") ? name.split(" ")[0] : name;
        lname = name.contains(" ") ? name.split(" ")[1] : "";
    }
    public ServiceProvider(String name, String phone_no, String account_number) {
        this.sp_id = "";
        this.name = name;
        this.phone_no = phone_no;
        this.account_number = account_number;

        fname = name.contains(" ") ? name.split(" ")[0] : name;
        lname = name.contains(" ") ? name.split(" ")[1] : "";
    }
    public ServiceProvider(ServiceProvider sp){
        this.sp_id = sp.getsp_id();
        this.name = sp.getName();
        this.account_number = sp.getAccount_number();
        this.phone_no = sp.getPhone_no();

        fname = name.contains(" ") ? name.split(" ")[0] : name;
        lname = name.contains(" ") ? name.split(" ")[1] : "";
    }

 //////////////////////////////////
    public String getsp_id() {
        return sp_id;
    }
    public void setSp_id(String sp_id) {
        this.sp_id = sp_id;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
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
        System.out.println(sp_id);

        System.out.print("Name: ");
        System.out.println(name);

        System.out.print("Phone number ");
        System.out.println(phone_no);

        System.out.print("Account Number: ");
        System.out.println(account_number);
    }

    ////////// CALLING DATABASE METHODS /////////////
    public boolean changePassword(String curr, String neww) {
        SPDB obj = new SPDB();
        return obj.changeSPPassword(sp_id,curr,neww);
    }
    public void getSPRecord(String id) {
        SPDB obj = new SPDB();
        ServiceProvider sp = obj.getSP(id);

        this.sp_id = id;
        this.name = sp.getName();
        this.phone_no = sp.getPhone_no();
        this.account_number = sp.getAccount_number();
    }

    public boolean splogin(String id, String pass) {
        SPDB obj = new SPDB();
        return obj.splogin(id, pass);
    }
    public void editServiceProviderAccountField(String id, String field, String new_value, boolean isNumeric) {
        SPDB obj = new SPDB();
        obj.updateField(id,field,new_value, isNumeric);
    }
    public void verifyEvent(String eventid) {
        SPDB obj = new SPDB();
        obj.verifyEvent(eventid);
    }
}
