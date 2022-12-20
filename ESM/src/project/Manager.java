package project;

public class Manager extends Employee
{

    public Manager()
    {
        super();
    }



    public void approveEvent(String eventid) {
        ManagerDB obj = new ManagerDB();
        obj.approveEvent(eventid);
    }
}
