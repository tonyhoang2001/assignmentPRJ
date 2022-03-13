/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author LTC
 */
public class DBContext {

    private Connection connection;
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=assignmentPRJ";
    private String user = "sa";
    private String pass = "123456";
    private PreparedStatement stm;

    public DBContext() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Account> getAllAccount() {
        List<Account> listAcc = new ArrayList<>();
        String query = "SELECT * FROM Account";
        try {
            stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                Account account = new Account();
                account.setIDAcc(rs.getInt("IDAcc"));
                account.setUsername(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));
                account.setIsAdmin(rs.getBoolean("isAdmin"));
                listAcc.add(account);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAcc;
    }
    
    public Account getAccountByUsername(String username) {
        
        String query = "SELECT * FROM Account WHERE Username = ?";
        Account account = new Account();
        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                account.setIDAcc(rs.getInt("IDAcc"));
                account.setUsername(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));
                account.setIsAdmin(rs.getBoolean("isAdmin"));
                return account;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    

}
