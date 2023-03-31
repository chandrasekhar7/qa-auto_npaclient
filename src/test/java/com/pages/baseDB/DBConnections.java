package com.pages.baseDB;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pages.utilities.ApplicationProperties;
import com.netpay.exceptions.TARuntimeException;

public class DBConnections  {
	

    public static Connection connection = null;
    protected static ApplicationProperties prop;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;
    
    protected DBConnections() {
    	
    }
    
    public static void getConnection() throws FileNotFoundException, IOException {
        try {
        	prop = ApplicationProperties.getInstance("db");
            Class.forName(prop.getProperty("DB_DRIVER"));
        } catch (ClassNotFoundException e) {
            throw new TARuntimeException("Please check the path of DB2 driver: " + e.getMessage());
        }

        try {
            switch(System.getenv("Env")){
                case "Test02":
                    connection = DriverManager.getConnection(prop.getProperty("DB_HOST"), prop.getProperty("DB_USER"), prop.getProperty("DB_PASS"));
                    break;
                case "SV7":
                    connection = DriverManager.getConnection(prop.getProperty("DB_HOST_SV7"), prop.getProperty("DB_USER_SV7"), prop.getProperty("DB_PASS_SV7"));
                    break;
                default:
                	throw new TARuntimeException("the environment provided is wrong");
            }
        } catch (SQLException e) {
            throw new TARuntimeException("Database connection failed. \nReason: " + e.getMessage());
        }

    }

    public static void executeQuery(String query) {
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            throw new TARuntimeException("Unable to execute the given query. \nReason: " + e.getMessage());
        }
    }
    public static void executeQueryUpdate(String query){
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new TARuntimeException("Unable to execute the given query. \nReason: " + e.getMessage());
        }
    }
    public static String getValueWithColumnName(String query, String columnName) throws FileNotFoundException, IOException {

        getConnection();
        executeQuery(query);
        boolean records = false;
        String value = null;

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    records = true;
                    value = resultSet.getString(columnName);
                }
            }
            if (records == false) {
                throw new TARuntimeException("No records found with the given query.");
            }
            return value;
        } catch (Exception e) {
            throw new TARuntimeException("Unable to get the results. \nReason: " + e.getMessage());
        }

    }

}

