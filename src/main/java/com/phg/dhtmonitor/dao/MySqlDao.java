package com.phg.dhtmonitor.dao;

import com.phg.dhtmonitor.model.Dht;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by milesporter on 2/21/16.
 */
@Component
public class MySqlDao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/dhtmonitor";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "p@55w0^d";


    private Statement stmt = null;

    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception exp) {
            System.out.println("Error creating database connection.");
            exp.printStackTrace(System.out);
        }
        return conn;
    }

    public boolean saveDht(Dht dht) {
        boolean success = false;
        try {
            Connection conn = getConnection();
            stmt = conn.createStatement();
            String sql;
            sql = "insert into dhtmonitor.dht (temperature, humidity, serverts) values (?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(sql);
            preparedStmt.setFloat(1, dht.getTemperature());
            preparedStmt.setFloat(2, dht.getHumidity());
            preparedStmt.setLong(3, dht.getTimestamp());
            preparedStmt.execute();
            conn.close();
            success = true;
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
        return success;
    }

    public ArrayList<Dht> getLastByCount(int count){
        ArrayList<Dht> d = new ArrayList<>();
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT temperature, humidity, serverts from DHT order by id;");
            int i=0;
            while ( (rs.next()) && (i<count) ) {
                Dht dht = new Dht(rs.getFloat(1), rs.getFloat(2), rs.getLong(3));
                d.add(dht);
                i++;
            }
        } catch(Exception exp) {
            System.out.println("Error getting data.");
        } finally {
            try {
                conn.close();
            } catch (Exception exp) {
                System.out.println("Error closing database connection.");
            }
        }
        return d;

    }

    public ArrayList<Dht> getLastBySeconds(int seconds) {
        ArrayList<Dht> d = new ArrayList<>();
        //  Query the db
        return d;
    }

}

