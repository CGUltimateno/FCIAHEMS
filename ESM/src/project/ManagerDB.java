package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class ManagerDB {
    public void approveEvent(String event_id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database: Manager approving event");

                String query = "UPDATE event SET approved = 1 WHERE event_id = '" + event_id + "'";

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
