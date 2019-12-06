import java.util.*;

public class BacktrackingKnapsack {
    private int maxProfit = 0;
    private int maxWeight = 0;
    private int numBest = 0;
    private char[] include = null;
    private char[] bestSet = null;
    private int nodesVisited = 0;
    ArrayList<Item> allItems = null;
    ArrayList<Item> taken = null;
    ArrayList<Item> notTaken = null;

    public BacktrackingKnapsack(int maxWeight, ArrayList<Item> allItems){
        this.maxWeight = maxWeight;
        this.allItems = allItems;
        this.include = new char[allItems.size()];
        this.bestSet = include.clone();
        this.taken = new ArrayList<>();
        this.notTaken = new ArrayList<>();
    }

    public void knapsack(int i, int profit, int weight){
        if(weight <= maxWeight && profit > maxProfit){
            this.maxProfit = profit;
            this.numBest = i;
            this.bestSet = include.clone();
        }
        if(promising(profit, weight, i)){
            Item nextItem = allItems.get(i+1);
            include[i+1] = 'y';
            knapsack(i+1, profit + nextItem.profit, weight + nextItem.weight);
            include[i+1] = 'n';
            knapsack(i+1, profit, weight);
        }
    }

    public boolean promising(int profit, int weight, int i){
        int j, k, totalWeight;
        double bound;
        if(weight >= maxWeight) {return false;}
        else{
            this.nodesVisited++;
            j = i+1;
            bound = profit;
            totalWeight = weight;
            while(j < allItems.size() && (totalWeight + allItems.get(j).weight <= maxWeight)) {
                Item item = allItems.get(j);
                totalWeight += item.weight;
                bound += item.profit;
                j++;
            }
            k = j;
            if(k < allItems.size()){
                bound += (maxWeight-totalWeight) * allItems.get(k).ratio;
            }
        }
        return bound > maxProfit;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("How many items are available to take?");
        ArrayList<Item> array = Item.createArray(in.nextInt());
        System.out.println("What is the maximum weight the bag can hold?");
        int W = in.nextInt();

        BacktrackingKnapsack btk = new BacktrackingKnapsack(W, array);

        btk.knapsack(-1, 0, 0);

        System.out.println("Max Profit:" + btk.maxProfit);
        System.out.println("Nodes visited: " + btk.nodesVisited);

        for(int i = 0; i < btk.bestSet.length; i++){
            if(btk.bestSet[i] == 'y'){ btk.taken.add(btk.allItems.get(i)); }
            else { btk.notTaken.add(btk.allItems.get(i)); }
        }
        System.out.println("Items Taken: " + Arrays.toString(btk.taken.toArray()));
        System.out.println("Items Not Taken: " + Arrays.toString(btk.notTaken.toArray()));
    }
}
