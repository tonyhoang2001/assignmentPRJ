/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author LTC
 */
public class PortfolioDetail {

    private int IDDetail;
    private int IDPortf;
    private String NameUser;
    private boolean Gender;
    private String Description;
    private String Field;
    private String Skill;
    private String Project;
    private String Address;
    private long Phone;
    private String Email;

    public PortfolioDetail() {
    }

    public PortfolioDetail(int IDDetail, int IDPortf, String NameUser, boolean Gender, String Description, String Field, String Skill, String Project, String Address, long Phone, String Email) {
        this.IDDetail = IDDetail;
        this.IDPortf = IDPortf;
        this.NameUser = NameUser;
        this.Gender = Gender;
        this.Description = Description;
        this.Field = Field;
        this.Skill = Skill;
        this.Project = Project;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
    }

    public int getIDDetail() {
        return IDDetail;
    }

    public void setIDDetail(int IDDetail) {
        this.IDDetail = IDDetail;
    }

    public int getIDPortf() {
        return IDPortf;
    }

    public void setIDPortf(int IDPortf) {
        this.IDPortf = IDPortf;
    }

    public String getNameUser() {
        return NameUser;
    }

    public void setNameUser(String NameUser) {
        this.NameUser = NameUser;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getField() {
        return Field;
    }

    public void setField(String Field) {
        this.Field = Field;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String Skill) {
        this.Skill = Skill;
    }

    public String getProject() {
        return Project;
    }

    public void setProject(String Project) {
        this.Project = Project;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public long getPhone() {
        return Phone;
    }

    public void setPhone(long Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
