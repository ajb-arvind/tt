/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arvind19.actionpackage;

import com.arvind19.initpackage.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author arvind
 */
public class Data {
    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> courses;
    private ArrayList<Department> departments;
    private ArrayList<MeetingTime> meetingTimes;
    private int numberOfClasses = 0;

    public Data() {
        initialize();
    }
    
    private Data initialize(){
        Room room1 = new Room("R1", 25);
        Room room2 = new Room("R2", 45);
        Room room3 = new Room("R3", 35);
        rooms = new ArrayList<Room>(Arrays.asList(room1,room2,room3));
        
        MeetingTime meetingTime1 = new MeetingTime("MT1","MWF 09.00 - 10.00");
        MeetingTime meetingTime2 = new MeetingTime("MT2","MWF 10.00 - 11.00");
        MeetingTime meetingTime3 = new MeetingTime("MT3","TTH 09.00 - 10.30");
        MeetingTime meetingTime4 = new MeetingTime("MT4","TTH 10.30 - 12.00");
        meetingTimes = new ArrayList<MeetingTime>(Arrays.asList(meetingTime1,meetingTime2,meetingTime3,meetingTime4));
        
        Instructor instructor1 = new Instructor("I1","Sir1");
        Instructor instructor2 = new Instructor("I2","Sir2");
        Instructor instructor3 = new Instructor("I3","Sir3");
        Instructor instructor4 = new Instructor("I4","Sir4");
        instructors = new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2,instructor3,instructor4));
        
        Course course1 = new Course("C1", "101K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2)), 25);
        Course course2 = new Course("C2", "102K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2,instructor3)), 35);
        Course course3 = new Course("C3", "103K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor2)), 25);
        Course course4 = new Course("C4", "104K", new ArrayList<Instructor>(Arrays.asList(instructor3,instructor4)), 30);
        Course course5 = new Course("C5", "105K", new ArrayList<Instructor>(Arrays.asList(instructor4)), 35);
        Course course6 = new Course("C6", "106K", new ArrayList<Instructor>(Arrays.asList(instructor1,instructor3)), 45);
        Course course7 = new Course("C7", "107K", new ArrayList<Instructor>(Arrays.asList(instructor2,instructor4)), 45);
        courses = new ArrayList<Course>(Arrays.asList(course1,course2,course3,course4,course5,course6,course7));

        Department dep1 = new Department("MATH", new ArrayList<Course>(Arrays.asList(course1,course3)));
        Department dep2 = new Department("EE", new ArrayList<Course>(Arrays.asList(course2,course4, course5)));
        Department dep3 = new Department("PHY", new ArrayList<Course>(Arrays.asList(course6,course7)));
        departments = new ArrayList<Department>(Arrays.asList(dep1,dep2,dep3));
        departments.forEach(x -> numberOfClasses += getCourses().size());
        return this;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public ArrayList<MeetingTime> getMeetingTimes() {
        return meetingTimes;
    }

    public int getNumberOfClasses() {
        return numberOfClasses;
    }
    
}
