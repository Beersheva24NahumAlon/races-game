package telran.games;

import java.time.LocalDateTime;
import java.util.Random;

public class Racer extends Thread {
    private static final int MAX_WAITING = 5;

    private int number;
    private int distance = 0;
    private LocalDateTime finishTime;
    private Race race;

    public Racer(int id) {
        this.number = id;
    }

    public int getNumber() {
        return number; 
    }

    public int getDistance() {
        return distance;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    private void makeStep() {
        distance++;
    }

    private void makeWaiting() {
        try {
            sleep(new Random().nextInt(MAX_WAITING));
        } catch (InterruptedException e) {
        }
    }

    public void setRace(Race race) {
        this.race = race;
    }

    @Override
    public void run() {
        int raceDistance = race.getDistance();
        while (distance < raceDistance) {
            makeStep();
            makeWaiting();
        }
        synchronized(race) {
            finishTime = LocalDateTime.now();
            race.resultTable.add(this);
        }
    }
}
