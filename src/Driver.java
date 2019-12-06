import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("How many items are available to take?");
        ArrayList<Item> array = Item.createArray(in.nextInt());

        System.out.print("What is the maximum weight the bag can hold?");
        int W = in.nextInt();

        BruteForceKnapsack bruteForceKnapsack = new BruteForceKnapsack(W, array);
        bruteForceKnapsack.knapsack(-1,0,0);

        System.out.println("BRUTE FORCE:");
        System.out.println("Max Profit: " + bruteForceKnapsack.getMaxProfit());
        System.out.println("Nodes Visited: " + bruteForceKnapsack.getNodesVisited());

        for(int i = 0; i < bruteForceKnapsack.bestSet.length; i++){
            if(bruteForceKnapsack.bestSet[i] == 'y'){ bruteForceKnapsack.taken.add(bruteForceKnapsack.allItems.get(i)); }
            else { bruteForceKnapsack.notTaken.add(bruteForceKnapsack.allItems.get(i)); }
        }
        System.out.println("Items Taken: " + Arrays.toString(bruteForceKnapsack.taken.toArray()));
        System.out.println("Items Not Taken: " + Arrays.toString(bruteForceKnapsack.notTaken.toArray()));


        BacktrackingKnapsack btk = new BacktrackingKnapsack(W, array);

        btk.knapsack(-1, 0, 0);

        System.out.println("\n\nBACKTRACKING:");
        System.out.println("Max Profit:" + btk.getMaxProfit());
        System.out.println("Nodes visited: " + btk.getNodesVisited());

        for(int i = 0; i < btk.bestSet.length; i++){
            if(btk.bestSet[i] == 'y'){ btk.taken.add(btk.allItems.get(i)); }
            else { btk.notTaken.add(btk.allItems.get(i)); }
        }
        System.out.println("Items Taken: " + Arrays.toString(btk.taken.toArray()));
        System.out.println("Items Not Taken: " + Arrays.toString(btk.notTaken.toArray()));


        System.out.println("\n\nBRANCH AND BOUND:");
        BBKnapsack knapsack = new BBKnapsack();
        knapsack.allItems = array;
        knapsack.maxWeight = W;
        int maxProfit = knapsack.knapsack3();
        System.out.println("Max Profit: " + maxProfit);
        System.out.println("Nodes Visited: " + knapsack.nodesVisited);
    }
}
