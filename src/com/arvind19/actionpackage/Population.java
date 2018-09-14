/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arvind19.actionpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.IntStream;

/**
 *
 * @author arvind
 */
public class Population {
    private ArrayList<Schedule> schedules;

    public Population(int size, Data data) {
        schedules = new ArrayList<Schedule>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initialize()));
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }
    
    public Population sortByFitness(){
        Collections.sort(schedules, new SortbyFitness());
                /*(Schedule o1, Schedule o2) -> /*{
                int returnValue = 0;
                if(Double.valueOf(o1.getFitness()) > Double.valueOf(o1.getFitness())) returnValue = -1;
                });
                //Double.valueOf(o1.getFitness()).compareTo(Double.valueOf(o2.getFitness())));*/
        return this;
    }
}

class SortbyFitness implements Comparator<Schedule> 
{ 
    // Used for sorting in ascending order of 
    // roll number 
    @Override
    public int compare(Schedule a, Schedule b) 
    { 
        int returnValue = 0;
        if(a.getFitness() > b.getFitness()) returnValue = -1;
        else if (a.getFitness() < b.getFitness()) returnValue = 1;
        return returnValue;
    } 
} 
