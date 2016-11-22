import PathFinding.AStar;
import PathFinding.TestNode;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Brent Blihovde
 */
public class Main {

    public static void main(String[] args) {

        int numRuns = 1000000;

        TestNode[] nodes = new TestNode[9];
        for (int i = 0; i < 9; i++) {
            nodes[i] = new TestNode(i%3,i/3);
        }

        nodes[0].setSuccessors(new TestNode[] {nodes[1],nodes[3]});
        nodes[1].setSuccessors(new TestNode[] {nodes[0],nodes[2],nodes[4]});
        nodes[2].setSuccessors(new TestNode[] {nodes[1],nodes[5]});
        nodes[3].setSuccessors(new TestNode[] {nodes[0],nodes[4],nodes[6]});
        nodes[4].setSuccessors(new TestNode[] {nodes[1],nodes[3],nodes[5],nodes[7]});
        nodes[5].setSuccessors(new TestNode[] {nodes[2],nodes[4],nodes[8]});
        nodes[6].setSuccessors(new TestNode[] {nodes[3],nodes[7]});
        nodes[7].setSuccessors(new TestNode[] {nodes[6],nodes[4],nodes[8]});
        nodes[8].setSuccessors(new TestNode[] {nodes[7],nodes[5]});


        AStar<TestNode> AS = new AStar<TestNode>(true);
        Random rand = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < 1000000; i++) {
            LinkedList<TestNode> path = AS.search(nodes[rand.nextInt(9)],nodes[rand.nextInt(9)]);
        }

        long endTime = System.nanoTime();

        float time = (endTime - startTime) / 1000000000f;

        DecimalFormat intFormatter = new DecimalFormat("#,###");
        DecimalFormat floatFormatter = new DecimalFormat("#,###.000");

        System.out.printf("Ran %s searches in %s seconds", intFormatter.format(numRuns), floatFormatter.format(time));

    }

}
