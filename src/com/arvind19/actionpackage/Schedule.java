/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arvind19.actionpackage;
import com.arvind19.initpackage.Class;
import com.arvind19.initpackage.*;
import java.util.ArrayList;

/**
 *
 * @author arvind
 */
public class Schedule {
    private ArrayList<Class> classes;

    
    private int classNumb = 0;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int numbOfConflict = 0;

    private Data data;

    public Data getData() {
        return data;
    }

    public Schedule(Data data) {
        this.data = data;
        classes = new ArrayList<Class>(data.getNumberOfClasses());
    }
    
    public Schedule initialize(){
        new ArrayList<Department>(data.getDepartments()).forEach(dept -> {
            dept.getCourses().forEach(course -> {
                Class newClass = new Class(classNumb++, dept, course);
                newClass.setMeetingTime(data.getMeetingTimes().get((int)(data.getMeetingTimes().size()* Math.random())));
                newClass.setRoom(data.getRooms().get((int)(data.getRooms().size()* Math.random())));
                newClass.setInstrucor(data.getInstructors().get((int)(data.getRooms().size()* Math.random())));
                classes.add(newClass);
            });
        });
        return this;
    }
    
    public ArrayList<Class> getClasses() {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness() {
        if(isFitnessChanged = true){
            fitness = calculateFitness();
            isFitnessChanged = false;
        }
        return fitness;
    }
    
    public int getNumbOfConflict() { return numbOfConflict; }

    private double calculateFitness() {
        numbOfConflict = 0;
        classes.forEach(x ->{
            if(x.getRoom().getSeatingCapacity()< x.getCourse().getMaxNumberOfStudent())numbOfConflict++;
            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
                if(x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId()){
                    if(x.getRoom() == y.getRoom()) numbOfConflict++;
                    if(x.getInstrucor()== y.getInstrucor()) numbOfConflict++;
                }
            });
        });
        return 1/(double)(numbOfConflict + 1);
    }
    
    public String toString(){
        String returnValue = new String();
        for(int x=0; x< classes.size() -1; x++) returnValue += classes.get(x) + ",";
        returnValue += classes.get(classes.size()-1);
        return returnValue;
    }
    
}
