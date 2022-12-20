package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class VenueDB {

    public void displayAllVenues() {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying all venues");

                String query = "select * from venue";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String id = rs.getString("venue_id");
                    String name = rs.getString("name");
                    String loc = rs.getString("location");
                    String addr = rs.getString("address");
                    String cap = rs.getString("max_capacity");
                    String desc = rs.getString("description");
                    String cat = rs.getString("category");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    System.out.print("Name: ");
                    System.out.println(name);

                    System.out.print("Location: ");
                    System.out.println(loc);

                    System.out.print("Address: ");
                    System.out.println(addr);

                    System.out.print("Max capacity: ");
                    System.out.println(cap);

                    System.out.print("Description: ");
                    System.out.println(desc);

                    System.out.print("Category: ");
                    System.out.println(cat);

                    System.out.print("Contact Info: ");
                    System.out.println(info);

                    System.out.print("Cost: ");
                    System.out.println(cost);

                    System.out.println();
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
    }
    public Venue getVenue(String id)  {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting a venue");

                String query = "select * from venue where VENUE_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String loc = rs.getString("location");
                    String addr = rs.getString("address");
                    int cap = rs.getInt("max_capacity");
                    String desc = rs.getString("description");
                    String cat = rs.getString("category");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    return new Venue(name, loc, addr, cap, desc, cat, info, cost);
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

        return new Venue();
    }
    public int getVenueCost(String id)  {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting venue cost");

                String query = "select * from venue where VENUE_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    return rs.getInt("cost");
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

        return 0;
    }

    public void updateField(String id, String field, String new_value, boolean isNumeric) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - updating venue field");

                String query;

                if (isNumeric)
                    query = "update venue set " + field + " = " + new_value + " where venue_id = " + id;
                else
                    query = "update venue set " + field + " = '" + new_value + "' where venue_id = " + id;

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

    public HashMap<ArrayList<String>, ArrayList<Venue>> getListOfVenuesAndIDs() {
        ArrayList<Venue> venues;
        ArrayList<String> venueIDs;

        HashMap<ArrayList<String>, ArrayList<Venue>> venuesAndIDs = null;

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of venues/ids");

                String query = "SELECT * FROM venue";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                venues = new ArrayList<Venue>();
                venueIDs = new ArrayList<String>();
                venuesAndIDs = new HashMap<ArrayList<String>, ArrayList<Venue>>();

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String loc = rs.getString("location");
                    String addr = rs.getString("address");
                    int cap = rs.getInt("max_capacity");
                    String desc = rs.getString("description");
                    String cat = rs.getString("category");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");
                    String id = rs.getString("venue_id");

                    Venue venue = new Venue(name, loc, addr, cap, desc, cat, info, cost);
                    venues.add(venue);
                    venueIDs.add(id);
                }

                venuesAndIDs.put(venueIDs,venues);
                return venuesAndIDs;
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

        return venuesAndIDs;
    }
}
