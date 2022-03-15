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

    public Template getTemplateByID(int id) {

        String query = "SELECT * FROM Template WHERE IDTem = ?";
        Template template = new Template();
        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                template.setIDTem(rs.getInt("IDTem"));
                template.setName(rs.getString("Name"));
                return template;
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
        String query = "SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
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
                p.setIDPortf(rs.getInt("IDPortf"));
                p.setIDAcc(rs.getInt("IDAcc"));
                p.setIDTem(rs.getInt("IDTem"));

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
                for (int i = 0; i < resultList.size(); i++) {
                    int index = 0;
                    for (int j = i; j >= 0; j--) {
                        if (resultList.get(j).equals(resultList.get(i))) {
                            index++;
                        }
                    }
                    if (index == 1) {
                        fieldList.add(new Field(key, resultList.get(i)));
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
            while (rs.next()) {
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
            while (rs.next()) {
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
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public ArrayList<PortfolioDetail> pagingSearch(int pageIndex, int pageSize, String field, boolean gender) {
        ArrayList<PortfolioDetail> listPaging = new ArrayList<>();
        String query = "SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc AND Gender = ? AND Field LIKE ?) \n"
                + "  as Paging WHERE\n"
                + "  rownum >= ? AND rownum <= ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setBoolean(1, gender);
            stm.setString(2, "%" + field + "%");
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
                p.setIDPortf(rs.getInt("IDPortf"));
                p.setIDAcc(rs.getInt("IDAcc"));
                p.setIDTem(rs.getInt("IDTem"));

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
        String query = "SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
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
                p.setIDPortf(rs.getInt("IDPortf"));
                p.setIDAcc(rs.getInt("IDAcc"));
                p.setIDTem(rs.getInt("IDTem"));

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
        String query = "SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc and Field like ?) \n"
                + "  as Paging WHERE\n"
                + "  rownum >= ? AND rownum <= ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, "%" + field + "%");
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
                p.setIDPortf(rs.getInt("IDPortf"));
                p.setIDAcc(rs.getInt("IDAcc"));
                p.setIDTem(rs.getInt("IDTem"));

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

    public void insertPortf(PortfolioDetail p) {
        String query1 = "INSERT INTO Portfolio(NamePortf, IDAcc,IDTem) VALUES(?,?,?)";
        try {
            stm = connection.prepareStatement(query1);
            stm.setString(1, p.getPortfolio().getNamePortf());
            stm.setInt(2, p.getAccount().getIDAcc());
            stm.setInt(3, p.getTemplate().getIDTem());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertPorftDetail(PortfolioDetail p) {
        String query2 = "insert into PortfolioDetail(IDPortf,NameUser,Gender,Description,Field,Skill,Project,Address,Phone,Email)\n"
                + "values( (select IDPortf from (SELECT TOP 1 * FROM Portfolio ORDER BY IDPortf DESC)as p) ,?,?,?,?,?,\n"
                + "?,?,?,?)";
        try {
            stm = connection.prepareStatement(query2);
            stm.setString(1, p.getNameUser());
            stm.setBoolean(2, p.isGender());
            stm.setString(3, p.getDescription());
            stm.setString(4, p.getField());
            stm.setString(5, p.getSkill());
            stm.setString(6, p.getProject());
            stm.setString(7, p.getAddress());
            stm.setLong(8, p.getPhone());
            stm.setString(9, p.getEmail());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<PortfolioDetail> pagingSearchByAccount(int pageIndex, int pageSize, int idAcc) {
        ArrayList<PortfolioDetail> listPaging = new ArrayList<>();
        String query = "SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc "
                + "AND b.IDAcc = ?) \n"
                + "  as Paging WHERE\n"
                + "  rownum >= ? AND rownum <= ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idAcc);
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
                p.setIDPortf(rs.getInt("IDPortf"));
                p.setIDAcc(rs.getInt("IDAcc"));
                p.setIDTem(rs.getInt("IDTem"));

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

    public int countByAccount(int id) {
        String query = "SELECT count(*) \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc "
                + "AND b.IDAcc = ?) \n"
                + "  as Paging";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public PortfolioDetail getPDTByIDPD(int idPD) {
        PortfolioDetail pdt = new PortfolioDetail();
        String query = "SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
                + "  FROM \n"
                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc "
                + "AND a.IDDetail = ?) \n"
                + "  as Paging ";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idPD);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

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
                p.setIDPortf(rs.getInt("IDPortf"));
                p.setIDAcc(rs.getInt("IDAcc"));
                p.setIDTem(rs.getInt("IDTem"));

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

                return pdt;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void updateP(PortfolioDetail pdt, int idP) {
        String query = "UPDATE Portfolio SET NamePortf = ? WHERE IDPortf = ?";
        try {
            stm = connection.prepareCall(query);
            stm.setString(1, pdt.getPortfolio().getNamePortf());
            stm.setInt(2, idP);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePDT(PortfolioDetail pdt, int idPD) {
        String query = " UPDATE PortfolioDetail SET Address = ?, Description = ?, Email = ?, Field = ?, "
                + "Gender = ?, NameUser = ?, Phone = ?, Project = ?,\n"
                + "  Skill = ? WHERE IDDetail = ?";

        try {
            stm = connection.prepareCall(query);
            stm.setString(1, pdt.getAddress());
            stm.setString(2, pdt.getDescription());
            stm.setString(3, pdt.getEmail());
            stm.setString(4, pdt.getField());
            stm.setBoolean(5, pdt.isGender());
            stm.setString(6, pdt.getNameUser());
            stm.setLong(7, pdt.getPhone());
            stm.setString(8, pdt.getProject());
            stm.setString(9, pdt.getSkill());
            stm.setInt(10, idPD);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public PortfolioDetail getPDTByIDPDHome(int idPD) {
//        PortfolioDetail pdt = new PortfolioDetail();
//        String query = "SELECT IDDetail,IDPortf,NamePortf,NameUser,Field,Gender,Address,Phone,Email,Description,Skill,Project,IDTem,Name,IDAcc,Username,Password,isAdmin \n"
//                + "  FROM \n"
//                + "  (SELECT ROW_NUMBER() OVER (ORDER BY IDDetail ASC) as rownum,a.IDDetail,a.IDPortf,b.NamePortf, a.NameUser,a.Field,a.Gender,a.Address,a.Phone,a.Email,\n"
//                + "  a.Description,a.Skill,a.Project,c.IDTem,c.Name,d.IDAcc,d.Username,d.Password,d.isAdmin\n"
//                + "  FROM PortfolioDetail a, Portfolio b, Template c, Account d WHERE a.IDPortf = b.IDPortf and b.IDTem = c.IDTem and b.IDAcc = d.IDAcc "
//                + "AND a.IDDetail = ?) \n"
//                + "  as Paging ";
//
//        try {
//            stm = connection.prepareStatement(query);
//            stm.setInt(1, idPD);
//
//            ResultSet rs = stm.executeQuery();
//
//            while (rs.next()) {
//
//                pdt.setIDDetail(rs.getInt("IDDetail"));
//                pdt.setNameUser(rs.getString("NameUser"));
//                pdt.setField(rs.getString("Field"));
//                pdt.setGender(rs.getBoolean("Gender"));
//                pdt.setAddress(rs.getString("Address"));
//                pdt.setPhone(rs.getLong("Phone"));
//                pdt.setEmail(rs.getString("Email"));
//                pdt.setDescription(rs.getString("Description"));
//                pdt.setSkill(rs.getString("Skill"));
//                pdt.setProject(rs.getString("Project"));
//
//                Portfolio p = new Portfolio();
//                p.setNamePortf(rs.getString("NamePortf"));
//                p.setIDPortf(rs.getInt("IDPortf"));
//                p.setIDAcc(rs.getInt("IDAcc"));
//                p.setIDTem(rs.getInt("IDTem"));
//                
//                Template t = new Template();
//                t.setIDTem(rs.getInt("IDTem"));
//                t.setName(rs.getString("Name"));
//
//                Account a = new Account();
//                a.setIDAcc(rs.getInt("IDAcc"));
//                a.setUsername(rs.getString("Username"));
//                a.setPassword(rs.getString("Password"));
//                a.setIsAdmin(rs.getBoolean("isAdmin"));
//
//                pdt.setAccount(a);
//                pdt.setPortfolio(p);
//                pdt.setTemplate(t);
//
//                return pdt;
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return null;
//    }
    public void deletePD(int idPD) {
        String query = "DELETE FROM PortfolioDetail WHERE IDDetail = ?";
        try {
            stm = connection.prepareCall(query);
            stm.setInt(1, idPD);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteP(int idP) {
        String query = "DELETE FROM Portfolio WHERE IDPortf = ?";
        try {
            stm = connection.prepareCall(query);
            stm.setInt(1, idP);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Account> pagingAccount(int pageIndex, int pageSize, int idAcc) {
        ArrayList<Account> pagingAccs = new ArrayList<>();
        String query = "select * from \n"
                + "  (select ROW_NUMBER() over (order by IDAcc asc) as rownum, IDAcc, Username, \n"
                + "  Password, isAdmin from Account where IDAcc <> ?) as pagingAcc where rownum >= ?\n"
                + "   and rownum <= ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idAcc);
            stm.setInt(2, (pageIndex - 1) * pageSize + 1);
            stm.setInt(3, pageIndex * pageSize);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Account a = new Account();
                a.setIDAcc(rs.getInt("IDAcc"));
                a.setUsername(rs.getString("Username"));
                a.setPassword(rs.getString("Password"));
                a.setIsAdmin(rs.getBoolean("isAdmin"));

                pagingAccs.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pagingAccs;
    }

    public int countAccounts(int idAcc) {
        String query = "SELECT count(*) \n"
                + "  FROM \n"
                + " Account WHERE IDAcc <> ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idAcc);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateAccount(Account account) {
        String query = "UPDATE Account SET Username = ?, Password = ?, isAdmin = ? WHERE IDAcc = ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            stm.setBoolean(3, account.isIsAdmin());
            stm.setInt(4, account.getIDAcc());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteAccount(int idAcc) {
        String query = "delete from Account where IDAcc = ?";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idAcc);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAccountPortf(int idAcc) {
        String query = "delete from Portfolio where IDPortf in \n"
                + "   (select IDPortf from Portfolio where IDAcc = ?)";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idAcc);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAccountDetail(int idAcc) {
        String query = "delete from PortfolioDetail where IDDetail in \n"
                + "   ( select IDDetail\n"
                + "   from PortfolioDetail\n"
                + "   where IDPortf in (select IDPortf from Portfolio where IDAcc = ?) )";

        try {
            stm = connection.prepareStatement(query);
            stm.setInt(1, idAcc);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
