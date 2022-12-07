package project;

public class Database {
    private static String db_name;
    private static String db_user;
    private static String db_pass;

    public static String getDb_name() {return db_name;}

    public static void setDb_name(String db_name) {Database.db_name = db_name;}

    public static String getDb_user() {return db_user;}

    public static void setDb_user(String db_user) {Database.db_user = db_user;}

    public static String getDb_pass() {return db_pass;}

    public static void setDb_pass(String db_pass) {Database.db_pass = db_pass;}
}
