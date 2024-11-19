package telran.games;

public class Main {
    private static final int N_RACERS = 5;
    private static final int DISTANCE = 30;
    
    public static void main(String[] args) {
        Race race = new Race(DISTANCE);
        race.setupRace(N_RACERS);
        race.startRace();
        race.printChampion();
    }
}