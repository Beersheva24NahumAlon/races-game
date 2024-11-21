package telran.games;

import telran.view.*;

public class Main {
    public static void main(String[] args) {
        InputOutput io = new StandardInputOutput();
        int nRacers = io.readDouble("Enter count of racers", "Wrong count of racers").intValue();
        int distance = io.readDouble("Enter distance of race", "Wrong distance of race").intValue();
        Race race = new Race(distance);
        race.setupRace(nRacers);
        race.startRace();
        race.printResults();
    }
}