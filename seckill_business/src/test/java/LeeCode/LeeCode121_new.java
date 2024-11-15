package LeeCode;

public class LeeCode121_new {
    public int maxProfit(int[] prices) {

        int max = Integer.MAX_VALUE , min =0;
        for (int price : prices) {
            Math.min(min, price);
            max = Math.max(max, price - min);
        }
        return max;
    }
}
