package telran.games;

import java.util.Random;

public class Racer extends Thread {
    private static final int MAX_WAITING = 5;

    private String name;
    private int distance = 0;
    private Race race;

    public Racer(String name) {
        this.name = name;
    }

    public String getRacerName() {
        return name; 
    }

    public int getDistance() {
        return distance;
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
        while (distance < race.getDistance()) {
            makeStep();
            makeWaiting();
        }
        race.setToTable(this);
    }
}
