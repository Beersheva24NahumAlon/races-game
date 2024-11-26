package telran.games;

import java.time.LocalDateTime;
import java.util.Random;

public class Racer extends Thread {
    private static final int MAX_WAITING = 5;

    private int number;
    private int distance = 0;
    private LocalDateTime finishTime;
    private final Race race;

    public Racer(int id, Race race) {
        this.number = id;
        this.race = race;
    }

    public int getNumber() {
        return number; 
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    private void makeStep() {
        distance++;
    }

    private void makeWaiting() {
        Random random = new Random();
        try {
            sleep(random.nextInt(MAX_WAITING));
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void run() {
        int raceDistance = race.getDistance();
        while (distance < raceDistance) {
            makeStep();
            makeWaiting();
        }
        try {
            race.lock.lock();
            finishTime = LocalDateTime.now();
            race.addRacerToResultTable(this);    
        } finally {
            race.lock.unlock();
        }
    }
}
