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
            this.bound = 0;
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
        ItemNode u = new ItemNode(0, 0, 0), v;
        v = new ItemNode(-1, 0, 0);
        v.getBound();
        PQ.add(v);

        while (!PQ.isEmpty()) {
            v = PQ.remove(); // remove unexpanded node with best bound

            if (v.bound > maxProfit) { // see if node is still promising
                this.nodesVisited++;
                Item nextItem = allItems.get(v.level + 1);
                u.level = v.level + 1;
                u.profit = v.profit + nextItem.profit;
                u.weight = v.weight + nextItem.weight;

                if (v.level == -1) {
                    u.level = 0;
                }
                u.getBound();

                System.out.println(u);
                if (u.weight <= maxWeight && u.profit > maxProfit) {
                    maxProfit = u.profit;
                }

                if (u.bound > maxProfit) {
                    this.nodesVisited++;
                    PQ.add(new ItemNode(u.level, u.profit, u.weight));
                }

                u.weight = v.weight;
                u.profit = v.profit;
                u.getBound();
                // add u to PQ if it is promising
                if (u.bound > maxProfit) {
                    PQ.add(u);
                }
            }
        }
        return maxProfit;
    }

}
