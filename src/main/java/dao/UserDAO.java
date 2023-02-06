/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.User;
import utils.IConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import service.UserService;
import utils.DBManager;

/**
 *
 * @author bemerson
 */
public class UserDAO {
    
    public User getUserByEmail(String email) {

        DBManager dmbgr = new DBManager();
        Connection con = dmbgr.getConnection();
        int userId = 0;
        String password = null;
        String fName = null;
        String lName = null;
        String userType = null;
        User tempUser = new User();

        String query = "SELECT * FROM USERDATA WHERE EMAIL=" + "'" + email + "'";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userId = (rs.getInt(1));
                password = (rs.getString(3));
                fName = (rs.getString(4));
                lName = (rs.getString(5));
                userType = (rs.getString(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        tempUser.setEmail(email);
        tempUser.setId(userId);
        tempUser.setFirstName(fName);
        tempUser.setLastName(lName);
        tempUser.setPassword(password);
        tempUser.setUserType(userType);
        return tempUser;

    }
    
    public User getUserById(long userId) {

        DBManager dmbgr = new DBManager();
        Connection con = dmbgr.getConnection();
        String password = null;
        String email = null;
        String fName = null;
        String lName = null;
        String userType = null;
        User tempUser = new User();

        String query = String.format("SELECT * FROM USERDATA WHERE USER_ID=%d",userId);
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                email = (rs.getString(2));
                password = (rs.getString(3));
                fName = (rs.getString(4));
                lName = (rs.getString(5));
                userType = (rs.getString(6));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        tempUser.setId(userId);
        tempUser.setEmail(email);
        tempUser.setFirstName(fName);
        tempUser.setLastName(lName);
        tempUser.setPassword(password);
        tempUser.setUserType(userType);
        return tempUser;

    }

    public ArrayList<User> getAllUsers() {

        DBManager dm = new DBManager();
        Connection con = dm.getConnection();
        int userId = 0;
        String password = null;
        String email = null;
        String fName = null;
        String lName = null;
        String userType = null;
        ArrayList<User> userData = new ArrayList();

        String query = "SELECT * FROM USERDATA";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userId = (rs.getInt(1));
                email = (rs.getString(2));
                password = (rs.getString(3));
                fName = (rs.getString(4));
                lName = (rs.getString(5));
                userType = (rs.getString(6));
                User tempUser = new User();
                tempUser.setEmail(email);
                tempUser.setId(userId);
                tempUser.setFirstName(fName);
                tempUser.setLastName(lName);
                tempUser.setPassword(password);
                tempUser.setUserType(userType);
                userData.add(tempUser);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

      
        return userData;

    }
    
    public void insertUser(User newUser){
        
        DBManager dmbgr = new DBManager();
        Connection con = dmbgr.getConnection();
        Statement stmt = null;
        
        try {
        
            stmt = con.createStatement();
            String sql = String.format("INSERT INTO USERDATA(EMAIL,PASSWORD,FNAME,LNAME,USERTYPE) " + 
                            "VALUES('%s','%s','%s','%s','%s')",newUser.getEmail(),newUser.getPassword(),newUser.getFirstName(),newUser.getLastName(),newUser.getUserType());
            stmt.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
         }finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void updateUser(User newUser){
        
        DBManager dmbgr = new DBManager();
        Connection con = dmbgr.getConnection();
        Statement stmt = null;
        
        try {
        
            stmt = con.createStatement();
            String sql = String.format("UPDATE USERDATA SET email='%s',password='%s',fname='%s',lname='%s',usertype='%s' where user_id=%d ",newUser.getEmail(),newUser.getPassword(),newUser.getFirstName(),newUser.getLastName(),newUser.getUserType(),newUser.getId());
            stmt.executeUpdate(sql);
        }
        catch (SQLException e){
         }finally {
            try {
                stmt.close();
                con.close();
            } catch (SQLException e) {
            }
        }
        
    }
    
    public void deleteUser(long userId){
        
        DBManager dmbgr = new DBManager();
        Connection con = dmbgr.getConnection();
        Statement stmt = null;
        
        try {
        
            stmt = con.createStatement();
            String sql = String.format("DELETE FROM USERDATA WHERE USER_ID=%d",userId);
            stmt.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
         }finally {
            try {
                stmt.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

   

}
