package project;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
public class SPDB {
    public ServiceProvider getSP(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting an employee");

                String query = "select * from sp where sp_id = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {                                            // reads a row
                    String name = rs.getString("name");             // finds a column in the row
                    String phone = rs.getString("phone_no");
                    String acc = rs.getString("account_number");

                    return new ServiceProvider(id, name, phone,  acc);
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

        return new ServiceProvider();
    }
    public void updateField(String id, String field, String new_value, boolean isNumeric) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - updating Service Provider field");

                String query;

                if (!isNumeric)
                    query = "update SP set " + field + " = '" + new_value + "' where SP_ID = " + id;
                else
                    query = "update SP set " + field + " = " + new_value + " where SP_ID = " + id;

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

    public boolean splogin(String id, String pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - attemptingg Service Provider login");

                String query = "select * from SPPASS where SP_ID = " + id;  // query to be sent

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
    public boolean changeSPPassword(String id, String old_pass, String new_pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - changing SP password");

                String query = "update SPPASS set PASSWORD = " + new_pass + " where SP_ID = " + id + " and PASSWORD = " + old_pass;

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
    public void verifyEvent(String event_id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database: SP approving event's date");

                String query = "UPDATE event SET verified = 1 WHERE event_id = '" + event_id + "'";

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
