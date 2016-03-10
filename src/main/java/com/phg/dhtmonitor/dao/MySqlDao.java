package com.phg.dhtmonitor.dao;

import com.phg.dhtmonitor.model.Dht;
import com.phg.dhtmonitor.model.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by milesporter on 2/21/16.
 */
@Component
public class MySqlDao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    @Value("${com.phg.jdbc.url}")
    public String DB_URL;  // = "jdbc:mysql://localhost/dhtmonitor";

    //  Database credentials
    @Value("${com.phg.jdbc.username}")
    public String USER; // = "root";

    @Value("${com.phg.jdbc.password}")
    public String PASS; // = "Jazz4Bass!";


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

    public boolean saveMeasurement(Measurement measurement) {
        boolean success = false;
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement("insert into dhtmonitor.measurement (sensor, attribute, val, serverts) values (?,?,?,?)");
            preparedStmt.setString(1, measurement.getSensor());
            preparedStmt.setString(2, measurement.getAttribute());
            preparedStmt.setFloat(3, measurement.getVal());
            preparedStmt.setLong(4, measurement.getTimestamp());
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

    public ArrayList<Measurement> getLastByCount(int count){
        ArrayList<Measurement> measurements = new ArrayList<>();
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("SELECT sensor, attribute, val, serverts from dhtmonitor.measurement order by id desc");
            ResultSet rs = ps.executeQuery();
            int i=0;
            while ( (rs.next()) && (i<count) ) {
                Measurement m = new Measurement(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getLong(4));
                measurements.add(m);
                i++;
            }
        } catch(Exception exp) {
            System.out.println("Error getting data." + exp.toString() + ": " + exp.getMessage());

        } finally {
            try {
                conn.close();
            } catch (Exception exp) {
                System.out.println("Error closing database connection.");
            }
        }
        return measurements;

    }

    public ArrayList<Measurement> getLastByCountAndServer(String server, int count){
        ArrayList<Measurement> measurements = new ArrayList<>();
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("SELECT sensor, attribute, val, serverts from where sensor like {?} dhtmonitor.measurement order by id desc");
            ps.setString(1, server);
            ResultSet rs = ps.executeQuery();
            int i=0;
            while ( (rs.next()) && (i<count) ) {
                Measurement m = new Measurement(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getLong(4));
                measurements.add(m);
                i++;
            }
        } catch(Exception exp) {
            System.out.println("Error getting data." + exp.toString() + ": " + exp.getMessage());

        } finally {
            try {
                conn.close();
            } catch (Exception exp) {
                System.out.println("Error closing database connection.");
            }
        }
        return measurements;

    }

    public ArrayList<String> getSensors(){
        ArrayList<String> sensors = new ArrayList<>();
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("SELECT distinct sensor from dhtmonitor.measurement order by sensor");
            ResultSet rs = ps.executeQuery();
            int i=0;
            while ( rs.next() ) {
                sensors.add(rs.getString(1));
                i++;
            }
        } catch(Exception exp) {
            System.out.println("Error getting unique sensors data." + exp.toString() + ": " + exp.getMessage());

        } finally {
            try {
                conn.close();
            } catch (Exception exp) {
                System.out.println("Error closing database connection.");
            }
        }
        return sensors;

    }

    public ArrayList<String> getAttributesBySensors(String sensor){
        ArrayList<String> attribute = new ArrayList<>();
        Connection conn = getConnection();

        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("SELECT distinct attribute from dhtmonitor.measurement where sensor like (?) order by attribute");
            ps.setString(1, sensor);
            ResultSet rs = ps.executeQuery();
            int i=0;
            while ( rs.next() ) {
                attribute.add(rs.getString(1));
                i++;
            }
        } catch(Exception exp) {
            System.out.println("Error getting attributes for sensor data." + exp.toString() + ": " + exp.getMessage());

        } finally {
            try {
                conn.close();
            } catch (Exception exp) {

                System.out.println("Error closing database connection.");
            }
        }
        return attribute;

    }

    public ArrayList<Measurement> getLastBySeconds(int seconds) {
        ArrayList<Measurement> d = new ArrayList<>();
        //  Query the db
        return d;
    }

    public ArrayList<Measurement> getLastBySecondsandServer(String server, int seconds) {
        ArrayList<Measurement> d = new ArrayList<>();
        //  Query the db
        return d;
    }

}

