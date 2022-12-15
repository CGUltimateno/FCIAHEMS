package project;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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

                    ServiceProvider sp = new ServiceProvider(id, name, phone,  acc);
                    return sp;
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

        ServiceProvider empty = new ServiceProvider();
        return empty;
    }
    public void editsp(String id) {
        ServiceProvider sp = this.getSP(id);

        Scanner input = new Scanner(System.in);

        System.out.println("Enter employee details: ");

        System.out.print("Enter name: ");
        sp.setName(input.nextLine());

        System.out.print("Enter phone no: ");
        sp.setPhone_no(input.nextLine());

        System.out.print("Enter account number: ");
        sp.setAccount_number(input.nextLine());

        this.removesp(id);
        this.insertsp(sp, id);
    }
    public void removesp(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - removing Service Provider");

                this.deletePasswordRecord(id);

                String query = "delete from sp where sp_id = " + id;  // selects all data from database

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
    public void insertsp(ServiceProvider sp, String id) {

        sp.setSp_id(id);

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - inserting employee with id");

                Statement stmt = conn.createStatement();

                String query ="insert into sp(sp_id, name, phone_no, account_number)" +
                        "values('" + sp.getSp_id() + "','" + sp.getName() + "','" + sp.getPhone_no() + "','" +   sp.getAccount_number() + "')";

                //System.out.println(query);
                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                this.addPasswordRecord(sp.getSp_id(), sp.getSp_id());
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

    public void deletePasswordRecord(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - deleting service provider password record");

                String query = "delete from SPPASS where SP_PASS = " + id;  // selects all data from database

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

    public void addPasswordRecord(String id, String pass) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - adding service provider password record");

                Statement stmt = conn.createStatement();

                String query = "insert into SPPASS(SP_ID, PASSWORD)" + "values('" + id + "','" + pass + "')";
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
}
