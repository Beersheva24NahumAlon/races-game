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
    private List<Racer> resultTable = new LinkedList<>();

    public int getDistance() {
        return distance;
    }

    public Race(int distance) {
        this.distance = distance;
    }

    public void setupRace(int nRacers) {
        IntStream.range(1, nRacers + 1)
                .forEach(i -> racers.add(new Racer(i, this)));
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
                .forEach(i -> {
                        Racer racer = resultTable.get(i);
                        System.out.printf("| %5d | %6d | %9d ms |\n",
                        i + 1, racer.getNumber(), ChronoUnit.MILLIS.between(startTime, racer.getFinishTime()));
                });
        System.out.println("|-------|--------|--------------|");
    }

    public void addRacerToResultTable(Racer racer) {
        resultTable.add(racer);
    }
}
