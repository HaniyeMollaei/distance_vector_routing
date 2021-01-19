public abstract class Node {

    // Each entity will have a distance table
    protected int[][] distanceTable = new int[NetworkSimulator.NUMNODES]
            [NetworkSimulator.NUMNODES];


    // The update function.
    public abstract void update(Packet p);

    // The link cost change handlder.  only Entity0 and Entity1 will need this
    public abstract void linkCostChangeHandler(int whichLink, int newCost);

    // Print the distance table of the current entity.
    protected abstract void printDT();
}
