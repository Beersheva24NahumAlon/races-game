package telran.games;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Race {
    private int distance;
    private List<Racer> racers = new LinkedList<>();
    private AtomicInteger championNumber = null;

    public int getDistance() {
        return distance;
    }

    public Race(int distance) {
        this.distance = distance;
    }

    private void addRacerToRace(Racer racer) {
        racers.add(racer);
        racer.setRace(this);
    }

    public void setupRace(int nRacers) {
        IntStream.range(1, nRacers + 1)
                .forEach(i -> addRacerToRace(new Racer(i)));
    }

    public void startRace() {
        startRacers();
        waitRacers();
    }

    private void startRacers() {
        racers.stream().forEach(Racer::start);
    }

    private void waitRacers() {
        racers.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
            }
        });
    }

    public void setChampion(Racer racer) {
        if (championNumber == null) {
            championNumber = new AtomicInteger();
            championNumber.set(racer.getNumber());
        }
    }

    public void printChampion() {
        System.out.printf("The champion is racer #%d", championNumber.get());
    }
}
