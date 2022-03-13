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
public class Portfolio {
    private int IDPortf;
    private String NamePortf;
    private int IDAcc;
    private int IDTem;

    public Portfolio() {
    }

    public Portfolio(String NamePortf, int IDAcc, int IDTem) {
        
        this.NamePortf = NamePortf;
        this.IDAcc = IDAcc;
        this.IDTem = IDTem;
    }

    public int getIDPortf() {
        return IDPortf;
    }

    public void setIDPortf(int IDPortf) {
        this.IDPortf = IDPortf;
    }

    public String getNamePortf() {
        return NamePortf;
    }

    public void setNamePortf(String NamePortf) {
        this.NamePortf = NamePortf;
    }

    public int getIDAcc() {
        return IDAcc;
    }

    public void setIDAcc(int IDAcc) {
        this.IDAcc = IDAcc;
    }

    public int getIDTem() {
        return IDTem;
    }

    public void setIDTem(int IDTem) {
        this.IDTem = IDTem;
    }
    
    
}
