/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arvind19.initpackage;

import java.util.ArrayList;

/**
 *
 * @author arvind
 */
public class Course {
    private String number;
    private String name = null;
    private int maxNumberOfStudent;
    private ArrayList<Instructor> instructor;

    public Course(String number, String name, ArrayList<Instructor> instructor, int maxNumberOfStudent) {
        this.number = number;
        this.name = name;
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.instructor = instructor;
    }
    
    public String getNumber() { return number; }

    public String getName() { return name; }

    public int getMaxNumberOfStudent() { return maxNumberOfStudent; }

    public ArrayList<Instructor> getInstructor() { return instructor; }
    
    public String toString(){ return name; }
}
