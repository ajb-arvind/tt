package com.arvind19.actionpackage;

import java.util.ArrayList;
import com.arvind19.initpackage.Class;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author arvind
 */ 
public class Timetable {
    
    public static final int POPULATION_SIZE = 9;
    public static final double MUTATION_RATE = 0.1;
    public static final double CROSSOVER_RATE = 0.9;
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMB_OF_ELITE_SCHEDULES = 1;
    private int scheduleNumb = 0;
    private int classNumb = 1;
    private Data data;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        timetable.data = new Data();
        int generationNumber = 0;
        timetable.printAvailableData();
        System.out.println("> Generation #"+generationNumber);
        System.out.print(" Schedule # |                        ");
        System.out.print("Classes [dept,class,room, instructor, meeting-time]       ");
        System.out.println("                                            | Fitness | Conflicts");
        System.out.print("----------------------------------------------------------------------");
        System.out.println("--------------------------------------------------------------------");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(timetable.data);
        Population population = new Population(Timetable.POPULATION_SIZE, timetable.data).sortByFitness();
        population.getSchedules().forEach(schedule -> System.out.println("      "+timetable.scheduleNumb++ +
                                                                                    "      | "+ schedule +"  | "+
                                                                                     String.format("%.5f",schedule.getFitness()) +
                                                                                     " | "+schedule.getNumbOfConflict()));
        timetable.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
        timetable.classNumb = 1;
        
        while(population.getSchedules().get(0).getFitness() != 1.0){
            System.out.println("> Generation #"+ ++ generationNumber);
            System.out.print(" Schedule # |                        ");
            System.out.print("Classes [dept,class,room, instructor, meeting-time]       ");
            System.out.println("                                            | Fitness | Conflicts");
            System.out.print("----------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------");
            
            population = geneticAlgorithm.evolve(population).sortByFitness();
            timetable.scheduleNumb = 0;
           
            population.getSchedules().forEach(schedule -> System.out.println("      "+timetable.scheduleNumb++ +
                                                                                        "      | "+ schedule +"  | "+
                                                                                         String.format("%.5f",schedule.getFitness()) +
                                                                                         " | "+schedule.getNumbOfConflict()));
            timetable.printScheduleAsTable(population.getSchedules().get(0), generationNumber);
            timetable.classNumb = 1;
        }
    }
    
    private void printScheduleAsTable(Schedule schedule, int generation){
        ArrayList<Class> classes = schedule.getClasses();
        
        System.out.print("\n                    ");
        System.out.println("Class # | Dept| Course (number, max # of Student) | Room (Capacity) | Instructor(Id) | MeetingTime(id)");
        System.out.print("                                  ");
        System.out.print("-----------------------------------------------------");
        System.out.println("-------------------------------------------------------------");
        
        classes.forEach(x -> {
            int majorIndex = data.getDepartments().indexOf(x.getDept());
            int courseIndex = data.getCourses().indexOf(x.getCourse());
            int roomsIndex = data.getRooms().indexOf(x.getRoom());
            int instructorsIndex = data.getInstructors().indexOf(x.getInstrucor());
            int meetingTimeIndex = data.getMeetingTimes().indexOf(x.getMeetingTime());
            System.out.print("                        ");
            System.out.print(String.format(" %1$02d ", classNumb)+" | ");
            System.out.print(String.format(" %1$4s ", data.getDepartments().get(majorIndex).getName())+" | ");
            System.out.print(String.format(" %1$21s ", data.getCourses().get(courseIndex).getName()+
                                            " ("+data.getCourses().get(courseIndex).getNumber()+", "+
                                               x.getCourse().getMaxNumberOfStudent())+")             | ");
            System.out.print(String.format(" %1$10s ", data.getRooms().get(roomsIndex).getNumber()+
                                            " ("+x.getRoom().getSeatingCapacity())+")       | ");
            System.out.print(String.format(" %1$15s ", data.getInstructors().get(instructorsIndex).getName()+
                                            " ("+data.getInstructors().get(instructorsIndex).getId())+")     | ");
            
            System.out.println(data.getMeetingTimes().get(meetingTimeIndex).getName()+
                        "("+data.getMeetingTimes().get(meetingTimeIndex).getId());
            classNumb++;
        });
        if(schedule.getFitness() == 1) System.out.println(">Solution Found in "+(generation +1)+" generation");
        System.out.print("---------------------------------------------------------");
        System.out.println("---------------------------------------------------------");
        
    }
    
    public void printAvailableData(){
        System.out.println("Available Departments ->");
        data.getDepartments().forEach(x -> System.out.println("name: "+ x.getName()+", Courses: "+x.getCourses()));
        
        System.out.println("\n\nAvailable Courses ->");
        data.getCourses().forEach(x -> System.out.println("Course #"+x.getNumber()+", name"+x.getName()+", max number of student: "
        +x.getMaxNumberOfStudent()+", Instructor: "+x.getInstructor()));
        
        System.out.println("\n\nAvailable Rooms ->");
        data.getRooms().forEach(x -> System.out.println("room #"+x.getNumber()+", max number of seating capacity "+x.getSeatingCapacity()));
        
        System.out.println("\n\nAvailable Instructor ->");
        data.getInstructors().forEach(x -> System.out.println("id: "+x.getId()+", name: "+x.getName()));
        
        System.out.println("\n\nAvailable meeting Times: ");
        data.getMeetingTimes().forEach(x-> System.out.println("id: "+x.getId()+", Meeting Time: "+x.getName()));
        System.out.print("----------------------------------------------------------------");
        System.out.println("-----------------------------------------------------------------");
    }
    
}
