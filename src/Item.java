import java.util.*;

public class Item {

    public int profit = 0;
    public int weight = 0;
    public double ratio = 0;

    public static Comparator<Item> ratioComparator = new Comparator<Item>() {
        @Override
        public int compare(Item i1, Item i2) {
            if (i1.ratio > i2.ratio) return -1;
            else if (i2.ratio > i1.ratio) return 1;
            return 0;
        }
    };

    public Item(int profit, int weight) {
        this.profit = profit;
        this.weight = weight;
        if (weight != 0) {
            this.ratio = profit / weight;
        } else {
            ratio = 0;
        }
    }

    @Override
    public String toString() {
        return ("{Profit: " + this.profit + " | Weight: " + this.weight + "}");
    }

    public static ArrayList<Item> createArray(int n) {
        int i = 0;
        ArrayList<Item> array = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        while (i < n) {
            System.out.print("What is the weight of the item? ");
            int w = in.nextInt();
            System.out.print("What is the value of the item? ");
            int p = in.nextInt();
            System.out.println("-------------------------------");
            array.add(new Item(p, w));
            i++;
        }
        Collections.sort(array, ratioComparator);
        return array;
    }
}
