package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectWithDB {

    private final String username = "root";
    private final String password = "manager";

    public Connection con;
    public Statement stmt;

    public ConnectWithDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/buildathondb",
                    username,
                    password
            );

            stmt = con.createStatement();

            System.out.println("Connected to MySQL successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
//public class ConnectWithDB {
//    private final String username = "bt";
//    private final String password = "bt";
//    // GRANT CONNECT, RESOURCE TO bt;
//    public Connection con;
//    public Statement stmt;
//    public ConnectWithDB() {
//        try {
//            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
//            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", username, password);
//            stmt = con.createStatement();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
