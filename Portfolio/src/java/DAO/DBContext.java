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
import model.PortfolioDetail;

/**
 *
 * @author LTC
 */
public class DBContext {

    protected Connection connection;
    private String url = "jdbc:sqlserver://localhost:1433;databaseName=assignmentPRJ";
    private String user = "sa";
    private String pass = "123456";
    protected PreparedStatement stm;

    public DBContext() {
        int i = 0;
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

            while (rs.next()) {
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

            while (rs.next()) {
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

    public void insertAccount(Account account) {
        String query = "INSERT INTO Account (Username, Password, isAdmin) VALUES (?,?,?)";
        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            stm.setBoolean(3, account.isIsAdmin());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<PortfolioDetail> paging(int pageIndex, int pageSize) {
        ArrayList<PortfolioDetail> listPaging = new ArrayList<>();
        String query = "SELECT IDDetail,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc) \n"
                + "  as Paging WHERE\n"
                + "  rownum >= ? AND rownum <= ?";
        
        
        
        return listPaging;
    }

}
