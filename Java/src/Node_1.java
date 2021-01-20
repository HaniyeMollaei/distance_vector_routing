public class Node_1 extends Node {

    int[][] distanceTable1 = new int[NetworkSimulator.NUMNODES]
            [NetworkSimulator.NUMNODES];
    Boolean[] neighbors1 = new Boolean[NetworkSimulator.NUMNODES];

    public void rtinit1() {
        // Initialize distance table to INFINITY and neighbors array to NO for all edges
        int i, j;
        for (i = 0; i < NetworkSimulator.NUMNODES; i++) {
            for (j = 0; j <  NetworkSimulator.NUMNODES; j++) {
                distanceTable1[i][j] =  NetworkSimulator.INFINITY;
            }
            neighbors1[i] = false;
        }

        // Update distance table for neighbors
        for (i = 0; i < NetworkSimulator.NUMNODES ; i++) {
            if (NetworkSimulator.cost[0][i] < NetworkSimulator.INFINITY) {
                distanceTable1[0][i] = NetworkSimulator.cost[0][i];
                if (i != 0 )
                    neighbors1[i] = true;
            }
        }

        System.out.println("node 0 initial distance vector: "+ distanceTable1[0][0]+" "+
                distanceTable1[0][1]+" "+ distanceTable1[0][2]+" "+  distanceTable1[0][3] );

        // Update neighbors
        sendRoutePackets1();
    }

    public void rtupdate1( Packet pkt ) {
        System.out.println("rtupdate0() called, by a pkt received from Sender id: " + pkt.getSource());

        // Current shortest distance to node of origin
        int destDistance = distanceTable1[0][pkt.getSource()];

        // Only want to send out new packets once, so wait until loop is finished
        Boolean changedFlag = false;


        for (int i = 0; i < NetworkSimulator.NUMNODES; i++) {
            int oldDistance = distanceTable1[0][i];
            int newDistance = destDistance + pkt.getMincost(i);
            if (newDistance < oldDistance) {
                changedFlag = true;
                distanceTable1[0][i] = newDistance;
            }
            System.out.println("node 0 current distance vector: " + distanceTable1[0][0]+" "+
                    distanceTable1[0][1]+" "+ distanceTable1[0][2]+" "+  distanceTable1[0][3]);
        }
        if (changedFlag) {
            sendRoutePackets1();
        }
    }


    public void sendRoutePackets1() {

        // Send RoutePackets to all neighbors
        for (int i = 0; i < NetworkSimulator.NUMNODES; i++) {
            if (neighbors1[i] == false) continue;

            System.out.println("node 0 sends packet to node" + i +"with: "+ distanceTable1[0][0]+" "+
                    distanceTable1[0][1]+" "+ distanceTable1[0][2]+" "+  distanceTable1[0][3] );
            // Create and initialize route packet structure, except for destid.
            int[] minCosts = new int[NetworkSimulator.NUMNODES];
            for (int j = 0 ; j < NetworkSimulator.NUMNODES ; j++ ){
                minCosts[j]= distanceTable1[0][j];
            }
            Packet pkt = new Packet(0 , i , minCosts);

            //TODO
            NetworkSimulator.toNode(pkt);
        }
    }

    public void printDT()
    {
        System.out.println();
        System.out.println("           via");
        System.out.println(" D0 |   1   2   3");
        System.out.println("----+------------");
        for (int i = 1; i < NetworkSimulator.NUMNODES; i++)
        {
            System.out.print("   " + i + "|");
            for (int j = 1; j < NetworkSimulator.NUMNODES; j++)
            {
                if (distanceTable1[i][j] < 10)
                {
                    System.out.print("   ");
                }
                else if (distanceTable1[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else
                {
                    System.out.print(" ");
                }

                System.out.print(distanceTable1[i][j]);
            }
            System.out.println();
        }
    }


}
