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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Field;
import model.Portfolio;
import model.PortfolioDetail;
import model.Template;

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

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, (pageIndex - 1) * pageSize + 1);
            stm.setInt(2, pageIndex * pageSize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PortfolioDetail pdt = new PortfolioDetail();

                pdt.setIDDetail(rs.getInt("IDDetail"));
                pdt.setNameUser(rs.getString("NameUser"));
                pdt.setField(rs.getString("Field"));
                pdt.setGender(rs.getBoolean("Gender"));
                pdt.setAddress(rs.getString("Address"));
                pdt.setPhone(rs.getLong("Phone"));
                pdt.setEmail(rs.getString("Email"));
                pdt.setDescription(rs.getString("Description"));
                pdt.setSkill(rs.getString("Skill"));
                pdt.setProject(rs.getString("Project"));

                Portfolio p = new Portfolio();
                p.setNamePortf(rs.getString("NamePortf"));

                Template t = new Template();
                t.setIDTem(rs.getInt("IDTem"));
                t.setName(rs.getString("Name"));

                Account a = new Account();
                a.setIDAcc(rs.getInt("IDAcc"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setIsAdmin(rs.getBoolean("isAdmin"));

                pdt.setAccount(a);
                pdt.setPortfolio(p);
                pdt.setTemplate(t);

                listPaging.add(pdt);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listPaging;
    }

    public int count() {
        String query = "SELECT COUNT(*) FROM PortfolioDetail";

        try {
            stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Field> getField() {
        ArrayList<Field> fieldList = new ArrayList<>();
        ArrayList<String> resultList = new ArrayList<>();

        String query = "SELECT Field FROM PortfolioDetail";

        try {
            stm = connection.prepareCall(query);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                resultList.add(rs.getString("Field"));
            }

            if (!resultList.isEmpty()) {
                int key = 1;
                for (String string : resultList) {
                    int index = 0;
                    for (String string1 : resultList) {
                        if (string.equals(string1)) {
                            index++;
                        }
                    }
                    if (index == 1) {
                        fieldList.add(new Field(key, string));
                        key++;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fieldList;
    }

    public int countSearch(String field, boolean gender) {
        String query = "SELECT COUNT(*) FROM PortfolioDetail WHERE Field LIKE ? AND Gender = ?";
        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, "%" + field + "%");
            stm.setBoolean(2, gender);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public int countSearchByField(String field) {
        String query = "SELECT COUNT(*) FROM PortfolioDetail WHERE Field LIKE ?";
        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, "%" + field + "%");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public int countSearchByGender(boolean gender) {
        String query = "SELECT COUNT(*) FROM PortfolioDetail WHERE Gender = ?";
        try {
            stm = connection.prepareStatement(query);
            stm.setBoolean(1, gender);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
    
    public ArrayList<PortfolioDetail> pagingSearch(int pageIndex, int pageSize, String field, boolean gender) {
        ArrayList<PortfolioDetail> listPaging = new ArrayList<>();
        String query = "SELECT IDDetail,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc AND Gender = ? AND Field LIKE ?) \n"
                + "  as Paging WHERE\n"
                + "  rownum >= ? AND rownum <= ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setBoolean(1, gender);
            stm.setString(2, "%"+ field + "%");
            stm.setInt(3, (pageIndex - 1) * pageSize + 1);
            stm.setInt(4, pageIndex * pageSize);
            
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PortfolioDetail pdt = new PortfolioDetail();

                pdt.setIDDetail(rs.getInt("IDDetail"));
                pdt.setNameUser(rs.getString("NameUser"));
                pdt.setField(rs.getString("Field"));
                pdt.setGender(rs.getBoolean("Gender"));
                pdt.setAddress(rs.getString("Address"));
                pdt.setPhone(rs.getLong("Phone"));
                pdt.setEmail(rs.getString("Email"));
                pdt.setDescription(rs.getString("Description"));
                pdt.setSkill(rs.getString("Skill"));
                pdt.setProject(rs.getString("Project"));

                Portfolio p = new Portfolio();
                p.setNamePortf(rs.getString("NamePortf"));

                Template t = new Template();
                t.setIDTem(rs.getInt("IDTem"));
                t.setName(rs.getString("Name"));

                Account a = new Account();
                a.setIDAcc(rs.getInt("IDAcc"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setIsAdmin(rs.getBoolean("isAdmin"));

                pdt.setAccount(a);
                pdt.setPortfolio(p);
                pdt.setTemplate(t);

                listPaging.add(pdt);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listPaging;
    }
    
    public ArrayList<PortfolioDetail> pagingSearchByGender(int pageIndex, int pageSize, boolean gender) {
        ArrayList<PortfolioDetail> listPaging = new ArrayList<>();
        String query = "SELECT IDDetail,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc AND Gender = ? ) \n"
                + "  as Paging WHERE\n"
                + "  rownum >= ? AND rownum <= ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setBoolean(1, gender);
            stm.setInt(2, (pageIndex - 1) * pageSize + 1);
            stm.setInt(3, pageIndex * pageSize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PortfolioDetail pdt = new PortfolioDetail();

                pdt.setIDDetail(rs.getInt("IDDetail"));
                pdt.setNameUser(rs.getString("NameUser"));
                pdt.setField(rs.getString("Field"));
                pdt.setGender(rs.getBoolean("Gender"));
                pdt.setAddress(rs.getString("Address"));
                pdt.setPhone(rs.getLong("Phone"));
                pdt.setEmail(rs.getString("Email"));
                pdt.setDescription(rs.getString("Description"));
                pdt.setSkill(rs.getString("Skill"));
                pdt.setProject(rs.getString("Project"));

                Portfolio p = new Portfolio();
                p.setNamePortf(rs.getString("NamePortf"));

                Template t = new Template();
                t.setIDTem(rs.getInt("IDTem"));
                t.setName(rs.getString("Name"));

                Account a = new Account();
                a.setIDAcc(rs.getInt("IDAcc"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setIsAdmin(rs.getBoolean("isAdmin"));

                pdt.setAccount(a);
                pdt.setPortfolio(p);
                pdt.setTemplate(t);

                listPaging.add(pdt);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listPaging;
    }
    
    public ArrayList<PortfolioDetail> pagingSearchByField(int pageIndex, int pageSize, String field) {
        ArrayList<PortfolioDetail> listPaging = new ArrayList<>();
        String query = "SELECT IDDetail,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc and Field like ?) \n"
                + "  as Paging WHERE\n"
                + "  rownum >= ? AND rownum <= ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, "%"+ field + "%");
            stm.setInt(2, (pageIndex - 1) * pageSize + 1);
            stm.setInt(3, pageIndex * pageSize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PortfolioDetail pdt = new PortfolioDetail();

                pdt.setIDDetail(rs.getInt("IDDetail"));
                pdt.setNameUser(rs.getString("NameUser"));
                pdt.setField(rs.getString("Field"));
                pdt.setGender(rs.getBoolean("Gender"));
                pdt.setAddress(rs.getString("Address"));
                pdt.setPhone(rs.getLong("Phone"));
                pdt.setEmail(rs.getString("Email"));
                pdt.setDescription(rs.getString("Description"));
                pdt.setSkill(rs.getString("Skill"));
                pdt.setProject(rs.getString("Project"));

                Portfolio p = new Portfolio();
                p.setNamePortf(rs.getString("NamePortf"));

                Template t = new Template();
                t.setIDTem(rs.getInt("IDTem"));
                t.setName(rs.getString("Name"));

                Account a = new Account();
                a.setIDAcc(rs.getInt("IDAcc"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setIsAdmin(rs.getBoolean("isAdmin"));

                pdt.setAccount(a);
                pdt.setPortfolio(p);
                pdt.setTemplate(t);

                listPaging.add(pdt);

            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listPaging;
    }
    
    

}
