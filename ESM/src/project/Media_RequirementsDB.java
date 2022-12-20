package project;
import java.sql.*;
import java.util.Scanner;

public class Media_RequirementsDB
{
    public String addMediaRequirement()
    {
        Scanner input = new Scanner(System.in);

        int photography, videography, album, drone, crane;

        System.out.print("Photography (0: No 1: Yes):");
        photography = input.nextInt();

        System.out.print("Videography (0: No 1: Yes):");
        videography = input.nextInt();

        System.out.print("Album printing (0: No 1: Yes): ");
        album = input.nextInt();

        System.out.print("Drone (0: No 1: Yes): ");
        drone = input.nextInt();

        System.out.print("Crane (0: No 1: Yes): ");
        crane = input.nextInt();

        Media_Requirements mr = new Media_Requirements(photography, videography,  album,  drone, crane);

        ////////// DB IMPLEMENTATION ///////////////

        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null) {
                System.out.println("Database - adding media requirement");

                String query = "select * from MEDIA_REQUIREMENTS";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("media_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into MEDIA_REQUIREMENTS(media_id, photography, videography, album, drone, crane)" +
                        "values('" + new_id + "'," + mr.getPhotography() + "," + mr.getVideography() + "," + mr.getAlbum_printing() +
                         "," + mr.getDrone() + "," + mr.getCrane() + ")";

                // System.out.println(query);

                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                return new_id;
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


    public String addMediaRequirement(Media_Requirements mr) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        ) {
            if (conn != null) {
                System.out.println("Database - adding media requirement object");

                String query = "select * from MEDIA_REQUIREMENTS";  // selects all data from database

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                String last_id = "10000";

                while (rs.next()) {                                            // reads a row
                    last_id = rs.getString("media_id");             // reads an id
                }

                int temp = Integer.parseInt(last_id);
                temp++;
                String new_id = Integer.toString(temp);

                query = "insert into MEDIA_REQUIREMENTS(media_id, photography, videography, album, drone, crane)" +
                        "values('" + new_id + "'," + mr.getPhotography() + "," + mr.getVideography() + "," +
                        mr.getAlbum_printing() + "," + mr.getDrone() + "," + mr.getCrane() + ")";

                // System.out.println(query);

                stmt.executeUpdate(query);
                stmt.executeUpdate("commit");

                return new_id;

            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public void removeMediaRequirement(String id)
    {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - removing media requirement");

                String query = "delete from MEDIA_REQUIREMENTS where MEDIA_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
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

    public Media_Requirements getMediaRequirement(String id) {
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database.getDb_name(), Database.getDb_user(), Database.getDb_pass())
        )
        {
            if (conn != null)
            {
                System.out.println("Database - getting media requirement");

                String query = "select * from MEDIA_REQUIREMENTS where MEDIA_ID = " + id;  // query to be sent

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next())
                {
                    int photography = rs.getInt("photography");
                    int videography = rs.getInt("videography");
                    int album = rs.getInt("album");
                    int drone = rs.getInt("drone");
                    int crane = rs.getInt("crane");

                    System.out.print("ID: ");
                    System.out.println(id);

                    return new Media_Requirements(photography, videography,  album, drone, crane);
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

        return new Media_Requirements();
    }

}
