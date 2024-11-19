package telran.games;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Race {
    private int distance;
    private List<Racer> racers = new LinkedList<>();
    private List<Racer> table = new LinkedList<>();

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
                .forEach(i -> addRacerToRace(new Racer("Racer #%d".formatted(i))));
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

    public void setToTable(Racer racer) {
        table.add(racer);
    }

    public void printChampion() {
        System.out.printf("The champion is - %s", table.getFirst().getRacerName());
    }
}
