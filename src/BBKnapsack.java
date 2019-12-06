import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BBKnapsack {

    public ArrayList<Item> allItems = null;
    ArrayList<Item> taken = null;
    ArrayList<Item> notTaken = null;
    public int maxWeight;
    public int nodesVisited = 0;

    private class ItemNode extends Item implements Comparable<ItemNode> {
        public int level;
        public double bound;

        public ItemNode(int level, int price, int weight) {
            super(price, weight);
            this.bound = this.getBound();
            this.level = level;
        }

        @Override
        public int compareTo(ItemNode itemNode) {
            if (this.bound > itemNode.bound) {
                return -1;
            } else if (this.bound < itemNode.bound) {
                return 1;
            }
            return 0;
        }

        private double getBound() {
            int j = this.level + 1;

            double bound = this.profit;
            int totalWeight = this.weight;
            while (j < allItems.size() && (totalWeight + allItems.get(j).weight <= maxWeight)) {
                Item item = allItems.get(j);
                totalWeight += item.weight;
                bound += item.profit;
                j++;
            }
            if (j < allItems.size()) {
                bound += (maxWeight - totalWeight) * allItems.get(j).ratio;
            }
            this.bound = bound;
            return bound;
        }
    }

    public int knapsack3() {
        PriorityQueue<ItemNode> PQ = new PriorityQueue<>();
        int maxProfit = 0;
        ItemNode checkedNode;
        checkedNode = new ItemNode(-1, 0, 0);
        checkedNode.getBound();
        PQ.add(checkedNode);

        while (!PQ.isEmpty()) {
            checkedNode = PQ.remove(); // remove unexpanded node with best bound
            //w = { 2, 5, 10, 5 } p = { 40, 30, 50, 10 }
            if (checkedNode.bound > maxProfit && checkedNode.level < allItems.size()-1) { // see if node is still promising
                this.nodesVisited++;
                Item nextItem = allItems.get(checkedNode.level + 1); //get next item in array
                //create child node and find its bound
                ItemNode u = new ItemNode(checkedNode.level+1, checkedNode.profit + nextItem.profit, checkedNode.weight + nextItem.weight);
                if(checkedNode.level == -1){
                    u.level = 0;
                }


                if (u.weight <= maxWeight && u.profit > maxProfit) {
                    maxProfit = u.profit;
                }

                if (u.bound > maxProfit) {
                    PQ.add(new ItemNode(u.level, u.profit, u.weight));
                }

                //check node without accepting the next item (go right)
                u.weight = checkedNode.weight;
                u.profit = checkedNode.profit;
                u.getBound();

                // add u to PQ if it is promising
                if (u.bound > maxProfit) {
                    nodesVisited++;
                    PQ.add(new ItemNode(u.level, u.profit, u.weight));
                }
            }
        }
        return maxProfit;
    }

}
