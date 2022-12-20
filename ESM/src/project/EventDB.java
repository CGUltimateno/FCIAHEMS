package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EventDB {
    public String selectVenue() {
        System.out.println("All the venues we have available: ");
        VenueDB obj = new VenueDB();
        obj.displayAllVenues();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the ID of your chosen venue: ");

        return input.nextLine();
    }

    public String selectCateringService() {
        System.out.println("All the catering services we have available: ");
        CateringServicesDB obj = new CateringServicesDB();
        obj.displayAllCatering();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the ID of your chosen catering service: ");

        return input.nextLine();
    }

    public String selectMenu() {
        System.out.println("All the pre-built menus available: ");
        MenuDB obj = new MenuDB();
        obj.displayAllMenus();

        String m = "";

        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to:\n1. Select a pre-built menu\n2. Customise your own menu");
        int opt = 0;

        // Validating user input
        while (opt != 1 && opt != 2){
            System.out.print("Enter 1 or 2: ");
            opt = input.nextInt();

            if (opt == 1) {
                System.out.print("Enter the ID of your chosen menu: ");
                m = input.nextLine();
            }

            else if (opt == 2) {
                m = obj.addMenu();
            }
        }

        return m;
    }

    public String selectStudio() {
        System.out.println("All the studios we have available: ");
        StudioDB obj = new StudioDB();
        obj.displayAllStudios();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the ID of your chosen studio: ");

        return input.nextLine();
    }

    public String enterMediaRequirements() {
        Media_RequirementsDB obj = new Media_RequirementsDB();
        return obj.addMediaRequirement();
    }

    public int getChosenMenuCost(String id) {
        MenuDB obj = new MenuDB();
        return obj.getMenuCost(id);
    }

    public int getChosenVenueCost(String id) {
        VenueDB obj = new VenueDB();
        return obj.getVenueCost(id);
    }

    public int getChosenCateringServiceCost(String id) {
        CateringServicesDB obj = new CateringServicesDB();
        return obj.getCateringCharges(id);
    }

    public int getChosenStudioCost(String id) {
        StudioDB obj = new StudioDB();
        return obj.getStudioCost(id);
    }

    public void addEvent(String name, String type, String date, int guests, int total_cost, String start, String end, String cust_id,
                         String venue_id, String studio_id, String menu_id, String caterer_id,
                         String media_id, int approved) {

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - adding an event");

                String query = "select * from event";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("event_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "INSERT into EVENT(event_id, name, type, event_date, guests, total_cost, starting_time, ending_time," +
                        "cust_id, venue_id, studio_id, menu_id, caterer_id, media_id, approved)" + "values('" + new_id + "','" + name + "','" + type + "'," +
                        "STR_TO_DATE('" + date + "','%d/%m/%Y')" + "," + Integer.toString(guests) + "," + Integer.toString(total_cost) + ",'" + start + "','" +
                        end + "','" + cust_id + "','" + venue_id + "','" + studio_id + "','" + menu_id + "','" + caterer_id + "','" + media_id + "'," + Integer.toString(approved) + ")";

                //     System.out.println("query = " + query);

                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");
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

    }
    public Event getEvent(String id, int IDtype) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting an event");

                String query = "";

                if (IDtype == 0)
                    query = " SELECT * FROM event where event_id = " + id;  // query to be sent
                else if (IDtype == 1)
                    query = " SELECT * FROM event where cust_id = " + id;

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String date = rs.getString("event_date");
                    int guests = rs.getInt("guests");
                    int cost = rs.getInt("total_cost");
                    String start = rs.getString("starting_time");
                    String end = rs.getString("ending_time");
                    String venue_id = rs.getString("venue_id");
                    String studio_id = rs.getString("studio_id");
                    String menu_id = rs.getString("menu_id");
                    String caterer_id = rs.getString("caterer_id");
                    int approved = rs.getInt("approved");

                    Bill bill = new Bill();
                    bill.setMenu_cost(this.getChosenMenuCost(menu_id));
                    bill.setVenue_cost(this.getChosenVenueCost(venue_id));
                    bill.setCatering_cost(this.getChosenCateringServiceCost(caterer_id));
                    bill.setStudio_cost(this.getChosenStudioCost(studio_id));
                    bill.totalCost(guests);

                    Event obj = new Event(name, type, date, guests, start, end, bill, cost);
                    obj.setApproved(approved);
                    return obj;
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

        return new Event();
    }

    public ArrayList<Event> getListOfEvents() {
        ArrayList<Event> eventList = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of all events");

                String query = " SELECT * FROM event";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String id = rs.getString("event_id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String date = rs.getString("event_date");
                    int guests = rs.getInt("guests");
                    String start = rs.getString("starting_time");
                    String end = rs.getString("ending_time");
                    String venue_id = rs.getString("venue_id");
                    String studio_id = rs.getString("studio_id");
                    String menu_id = rs.getString("menu_id");
                    String caterer_id = rs.getString("caterer_id");
                    int approved = rs.getInt("approved");
                    int verified = rs.getInt("verified");

                    Bill bill = new Bill();
                    bill.setMenu_cost(this.getChosenMenuCost(menu_id));
                    bill.setVenue_cost(this.getChosenVenueCost(venue_id));
                    bill.setCatering_cost(this.getChosenCateringServiceCost(caterer_id));
                    bill.setStudio_cost(this.getChosenStudioCost(studio_id));
                    bill.totalCost(guests);

                    Event obj = new Event(id,name,type,date,guests,start,end,bill);
                    obj.setApproved(approved);
                    obj.setVerified(verified);
                    eventList.add(obj);
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

        return eventList;
    }
    public ArrayList<Event> getListOfApprovedEvents() {
        ArrayList<Event> ApprovedeventList = new ArrayList<>();

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of all events");

                String query = " Select * from event where approved = 1;";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String id = rs.getString("event_id");
                    String name = rs.getString("name");
                    String type = rs.getString("type");
                    String date = rs.getString("event_date");
                    int guests = rs.getInt("guests");
                    String start = rs.getString("starting_time");
                    String end = rs.getString("ending_time");
                    String venue_id = rs.getString("venue_id");
                    String studio_id = rs.getString("studio_id");
                    String menu_id = rs.getString("menu_id");
                    String caterer_id = rs.getString("caterer_id");
                    int approved = rs.getInt("approved");
                    int verified = rs.getInt("verified");

                    Bill bill = new Bill();
                    bill.setMenu_cost(this.getChosenMenuCost(menu_id));
                    bill.setVenue_cost(this.getChosenVenueCost(venue_id));
                    bill.setCatering_cost(this.getChosenCateringServiceCost(caterer_id));
                    bill.setStudio_cost(this.getChosenStudioCost(studio_id));
                    bill.totalCost(guests);

                    Event obj = new Event(id,name,type,date,guests,start,end,bill);
                    obj.setApproved(approved);
                    obj.setVerified(verified);
                    ApprovedeventList.add(obj);
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

        return ApprovedeventList;
    }
    public HashMap<String, String> getEventIDs(String id, int IDtype) {
        HashMap<String, String> ids = null;

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting all IDs associated with an event");

                String query = "";

                if (IDtype == 0)
                    query = " SELECT * FROM event where event_id = " + id;  // query to be sent
                else if (IDtype == 1)
                    query = " SELECT * FROM event where cust_id = " + id;

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String event_id = rs.getString("event_id");
                    String venue_id = rs.getString("venue_id");
                    String studio_id = rs.getString("studio_id");
                    String menu_id = rs.getString("menu_id");
                    String caterer_id = rs.getString("caterer_id");
                    String media_id = rs.getString("media_id");
                    String cust_id = rs.getString("cust_id");

                    ids = new HashMap<String, String>();

                    ids.put("event_id", event_id);
                    ids.put("cust_id", cust_id);
                    ids.put("venue_id", venue_id);
                    ids.put("studio_id", studio_id);
                    ids.put("menu_id", menu_id);
                    ids.put("caterer_id", caterer_id);
                    ids.put("media_id", media_id);

                    return ids;
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

        return ids;
    }

    public void removeEvent(String id)
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - removing an event");

                String query = "delete from event where event_id = " + id;  // selects all data from database

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

    public boolean isDateBooked(String date) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - comparing dates");

                String query = " SELECT event_date FROM event";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String e_date = rs.getString("event_date");

                    if (e_date.equals(date))
                        return true;
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

    public String getCustomerEmailByEventID(String event_id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting customer email by event ID");

                String query = "select c.email from customer c, event e where c.cust_id in (select cust_id from event where event_id = " + event_id + ")";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    return rs.getString("email");
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

        return "";
    }
}
