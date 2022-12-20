package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudioDB {

    public void displayAllStudios() {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - displaying all studios");

                String query = "select * from STUDIO";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String id = rs.getString("studio_id");
                    String name = rs.getString("name");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    Studio std = new Studio(name, info, cost);
                    std.display();
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
    public Studio getStudio(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting a studio");

                String query = "select * from STUDIO where STUDIO_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");

                    System.out.print("ID: ");
                    System.out.println(id);

                    return new Studio(name, info, cost);
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

        return new Studio();
    }
    public int getStudioCost(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting studio cost");

                String query = "select * from STUDIO where STUDIO_ID = " + id;  // query to be sent

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

    public HashMap<ArrayList<String>, ArrayList<Studio>> getListOfStudiosAndIDs() {
        ArrayList<Studio> studios;
        ArrayList<String> studioIDs;

        HashMap<ArrayList<String>, ArrayList<Studio>> studiosAndIDs = null;

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting list of studios/ids");

                String query = "SELECT * FROM studio";  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                studios = new ArrayList<Studio>();
                studioIDs = new ArrayList<String>();
                studiosAndIDs = new HashMap<ArrayList<String>, ArrayList<Studio>>();

                while (rs.next())
                {
                    String id = rs.getString("studio_id");
                    String name = rs.getString("name");
                    String info = rs.getString("contact_info");
                    int cost = rs.getInt("cost");


                    Studio studio = new Studio(name, info, cost);
                    studios.add(studio);
                    studioIDs.add(id);
                }

                studiosAndIDs.put(studioIDs, studios);
                return studiosAndIDs;
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

        return studiosAndIDs;
    }

    public void updateField(String id, String field, String new_value, boolean isNumeric) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - updating studio field");

                String query;

                if (isNumeric)
                    query = "update studio set " + field + " = " + new_value + " where studio_id = " + id;
                else
                    query = "update studio set " + field + " = '" + new_value + "' where studio_id = " + id;

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
}
