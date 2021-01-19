import java.util.Random;

public class NetworkSimulator {
    // This is the number of entities in the simulator
    public static final int NUMNODES = 4;

    // These constants are possible events
    public static final int FROMLAYER2 = 0;
    public static final int LINKCHANGE = 1;

    // Parameters of the simulation
    private boolean linkChanges;
    private static int traceLevel;
    //private static EventList eventList;
    private static Random rand;

    // Data used for the simulation
    private Node[] entity;
    public static int[][] cost;
    private static double time;

}
