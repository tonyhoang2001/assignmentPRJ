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
public class Template {
    private int IDTem;
    private String Name;

    public Template() {
    }

    public Template(int IDTem, String Name) {
        this.IDTem = IDTem;
        this.Name = Name;
    }

    public int getIDTem() {
        return IDTem;
    }

    public void setIDTem(int IDTem) {
        this.IDTem = IDTem;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
}
