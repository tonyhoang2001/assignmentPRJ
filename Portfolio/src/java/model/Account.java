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
public class Account {
    private int IDAcc;
    private String Username;
    private String Password;
    private boolean isAdmin;

    public Account(int IDAcc, String Username, String Password, boolean isAdmin) {
        this.IDAcc = IDAcc;
        this.Username = Username;
        this.Password = Password;
        this.isAdmin = isAdmin;
    }

    public Account() {
    }

    public int getIDAcc() {
        return IDAcc;
    }

    public void setIDAcc(int IDAcc) {
        this.IDAcc = IDAcc;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
}
