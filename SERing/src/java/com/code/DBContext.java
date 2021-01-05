package com.code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBContext {

    private final String severName = "127.0.0.1";
    private final String dbName = "sqldatabase";
    private final String portNumber = "3306";
    private final String userID = "root";
    private final String password = "18091996";
    private Connection connection;
    private ResultSet rs;
    private Statement statement;

    public DBContext() {
    }

    public void getconnection() throws Exception {
        String url = "jdbc:mysql://" + severName + ":" + portNumber + "/" + dbName;
        connection = DriverManager.getConnection(url, userID, password);
    }

    public ResultSet getConnectionStatement(String query) throws Exception {
        //connection = getconnection();
        getconnection();
        statement = connection.createStatement();
        rs = statement.executeQuery(query);
        return rs;
    }
    public void closeConnection() throws SQLException{
        rs.close();
        statement.close();
        connection.close();
        
        
    }

    //If i > 0 Success, else fail
    public int preStatement(String query) throws SQLException, Exception {
        //Connection connection = getconnection();
        getconnection();
        PreparedStatement preStatement = connection.prepareStatement(query);
        int i = preStatement.executeUpdate();
        connection.close();
        preStatement.close();
        return i;
    }
}
