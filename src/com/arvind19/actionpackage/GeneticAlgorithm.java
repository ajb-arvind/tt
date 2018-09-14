/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arvind19.actionpackage;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 *
 * @author arvind
 */
public class GeneticAlgorithm {
    private Data data;

    public GeneticAlgorithm(Data data) {
        this.data = data;
    }
    
    public Population evolve(Population population){
        return mutatePopulation(crossOverPopulation(population));
    }
    
    Population crossOverPopulation(Population population){
        Population crossOverPopulation = new Population(population.getSchedules().size(),data);
        IntStream.range(0, Timetable.NUMB_OF_ELITE_SCHEDULES).forEach(x -> crossOverPopulation.getSchedules()
                                                                            .set(x,population.getSchedules().get(x)));
        IntStream.range(Timetable.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
            if(Timetable.CROSSOVER_RATE > Math.random()){
                Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                crossOverPopulation.getSchedules().set(x, crossOverSchedule(schedule1,schedule2));
            }
            else crossOverPopulation.getSchedules().set(x, population.getSchedules().get(x));
        });
        return crossOverPopulation;
    }
    
    Schedule crossOverSchedule(Schedule schedule1, Schedule schedule2){
        Schedule crossOverSchedule = new Schedule(data).initialize();
        IntStream.range(0, crossOverSchedule.getClasses().size()).forEach(x -> {
            if(Math.random() > 0.5) crossOverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
            else crossOverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
        });
        return crossOverSchedule;
    }
    
    Population mutatePopulation(Population population){
        Population mutatePopulation = new Population(population.getSchedules().size(), data);
        ArrayList<Schedule> schedules = mutatePopulation.getSchedules();
        IntStream.range(0, Timetable.NUMB_OF_ELITE_SCHEDULES).forEach(x -> schedules.set(x, population.getSchedules().get(x)));
        IntStream.range(Timetable.NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
            schedules.set(x, mutateSchedule(population.getSchedules().get(x)));
        });
        return mutatePopulation;
    }
    
    Schedule mutateSchedule(Schedule mutateSchedule){
        Schedule schedule = new Schedule(data).initialize();
        IntStream.range(0, mutateSchedule.getClasses().size()).forEach(x ->{
            if(Timetable.MUTATION_RATE > Math.random())
                mutateSchedule.getClasses().set(x, schedule.getClasses().get(x));
        });
        return mutateSchedule;
    }
    
    Population selectTournamentPopulation(Population population){
        Population tournamentPopulation = new Population(Timetable.TOURNAMENT_SELECTION_SIZE, data);
        IntStream.range(0, Timetable.TOURNAMENT_SELECTION_SIZE).forEach(x ->{
            tournamentPopulation.getSchedules().
                    set(x, population.getSchedules().get((int)(Math.random() * population.getSchedules().size())));
            
        });
        return tournamentPopulation;
    }
}
