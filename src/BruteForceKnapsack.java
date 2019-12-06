import java.util.ArrayList;

public class BruteForceKnapsack {
    private int maxProfit = 0;
    private int maxWeight = 0;
    private int numBest = 0;
    private char[] include = null;
    public char[] bestSet = null;
    private int nodesVisited = 0;
    public ArrayList<Item> allItems = null;
    public ArrayList<Item> taken = null;
    public ArrayList<Item> notTaken = null;

    public BruteForceKnapsack(int maxWeight, ArrayList<Item> allItems){
        this.maxWeight = maxWeight;
        this.allItems = allItems;
        this.include = new char[allItems.size()];
        this.bestSet = include.clone();
        this.taken = new ArrayList<>();
        this.notTaken = new ArrayList<>();
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public int getNodesVisited() {
        return nodesVisited;
    }

    public char[] getBestSet() {
        return bestSet;
    }

    public void knapsack(int i, int profit, int weight){
        this.nodesVisited++;
        if(weight <= maxWeight && profit > maxProfit){
            this.maxProfit = profit;
            this.numBest = i;
            this.bestSet = include.clone();
        }
        if(i < this.allItems.size()-1){
            Item nextItem = allItems.get(i+1);
            include[i+1] = 'y';
            knapsack(i+1, profit + nextItem.profit, weight + nextItem.weight);
            include[i+1] = 'n';
            knapsack(i+1, profit, weight);
        }
    }
}
