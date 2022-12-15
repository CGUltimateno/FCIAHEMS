package project;

import java.util.ArrayList;
import java.util.HashMap;

public class Catering_Service
{
    private String company_name;
    private String contact_info;
    private String speciality;
    private int days;
    private int charges;

    public Catering_Service()
    {
        this.company_name = "";
        this.contact_info = "";
        this.speciality = "";
        this.days = 0;
        this.charges = 0;
    }

    public Catering_Service(String company_name, String contact_info, String speciality, int days, int charges) {
        this.company_name = company_name;
        this.contact_info = contact_info;
        this.speciality = speciality;
        this.days = days;
        this.charges = charges;
    }

    public String getCompany_name() {
        return company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getContact_info() {
        return contact_info;
    }
    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }

    public int getCharges() {
        return charges;
    }
    public void setCharges(int charges) {
        this.charges = charges;
    }

    public void display() {
        System.out.println("Company name = " + company_name);
        System.out.println("Contact info = " + contact_info);
        System.out.println("Speciality = " + speciality);
        System.out.println("Days order needs to be placed before = " + days);
        System.out.println("Charges = " + charges);
    }

    public Catering_Service getCaterer(String caterer_id) {
        CateringServicesDB obj = new CateringServicesDB();
        return obj.getCatering(caterer_id);
    }

    public HashMap<ArrayList<String>, ArrayList<Catering_Service>> getListOfCaterersAndIDs() {
        CateringServicesDB obj = new CateringServicesDB();
        return obj.getListOfCaterersAndIDs();
    }

    // edit one field
    public void editCateringField(String id, String field, String new_value, boolean isNumeric) {
        CateringServicesDB obj = new CateringServicesDB();
        obj.updateField(id,field,new_value, isNumeric);
    }
}
