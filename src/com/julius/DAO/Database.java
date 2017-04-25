package com.julius.DAO;

import java.sql.*;

/**
 * Created by Julius on 29-03-2017.
 */
public class Database {
    private static Statement statement;
    private static Database mySqlConnectionSingleton;
    private Connection connection;

    private Database() {

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "userdb";
        String driver = "com.mysql.jdbc.Driver";
        String user = "root";
        String pass = "nolimit";

        try {
            Class.forName(driver).newInstance();
            this.connection = DriverManager.getConnection(url + dbName, user, pass);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    public static synchronized Database getDbCon() {
        if (mySqlConnectionSingleton == null) {
            mySqlConnectionSingleton = new Database();
        }
        return mySqlConnectionSingleton;
    }

    public static ResultSet query(String query){
        ResultSet res = null;
        try {
            statement = mySqlConnectionSingleton.connection.createStatement();
            res = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void modify(String query){
        try {
            statement = mySqlConnectionSingleton.connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevel(String name, String pass) {
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            while (rs.next()) {
                if (rs.getString("Username").equals(name) && rs.getString("Password").equals(pass)) {
                    return Integer.parseInt(rs.getString("Level"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void postMessage(String name, String message){
        int id = getUID(name);
        String sql = "INSERT INTO messages (ID, Message) values (?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, message);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String messages(){
        String sql = "SELECT * FROM messages";
        String messages = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            while (rs.next()) {
                messages += getName(rs.getInt("ID")) + " : " + rs.getString("message") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public void addUser(String name, String password){
        int level = 2;
        String sql = "INSERT INTO users (Username, Password, Level) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, level);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*
        int level  = 2;
        String query = "INSERT INTO users (Username, Password, Level) values('"+ name + "', '" + password + "', " + level + ");";
        db.modify(query);
        */
    }

    public void addAdmin(String name, String password){
        int level = 1;
        String query = "INSERT INTO users (Username, Password, Level) values('" + name + "', '" + password + "', " + level + "); ";
        modify(query);
    }

    public int getUID(String name){
        String sql = "SELECT ID FROM users WHERE Username='"+ name +"'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getName(int id){
        String sql = "SELECT Username FROM users WHERE ID=" + id + "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return rs.getString("Username");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

}
