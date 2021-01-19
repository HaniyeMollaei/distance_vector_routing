public class Node_2 extends Node {
    @Override
    public void update(Packet p) {

    }

    @Override
    public void linkCostChangeHandler(int whichLink, int newCost) {

    }

    @Override
    public void printDT()
    {
        System.out.println();
        System.out.println("           via");
        System.out.println(" D2 |   0   1   3");
        System.out.println("----+------------");
        for (int i = 0; i < NetworkSimulator.NUMNODES; i++)
        {
            if (i == 2)
            {
                continue;
            }

            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMNODES; j++)
            {
                if (j == 2)
                {
                    continue;
                }

                if (distanceTable[i][j] < 10)
                {
                    System.out.print("   ");
                }
                else if (distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else
                {
                    System.out.print(" ");
                }

                System.out.print(distanceTable[i][j]);
            }
            System.out.println();
        }
    }
}
