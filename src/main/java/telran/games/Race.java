package telran.games;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Race {
    private int distance;
    private LocalDateTime startTime;
    private List<Racer> racers = new LinkedList<>();
    List<Racer> resultTable = new LinkedList<>();
    final Object lock = new Object();

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
        startTime = LocalDateTime.now();
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

    public void printResults() {
        System.out.println("|-------|--------|--------------|");
        System.out.println("| Place | Number | Running time |");
        System.out.println("|-------|--------|--------------|");
        IntStream.range(0, resultTable.size())
                .forEach(i -> System.out.printf("| %5d | %6d | %9d ms |\n",
                        i + 1, resultTable.get(i).getNumber(), 
                        ChronoUnit.MILLIS.between(startTime, resultTable.get(i).getFinishTime())));
        System.out.println("|-------|--------|--------------|");
    }
}
