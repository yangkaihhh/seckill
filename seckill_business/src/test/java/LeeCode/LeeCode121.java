package LeeCode;

//121. 买卖股票的最佳时机
public class LeeCode121 {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = 0;
        for (int i = 0; i < prices.length; i++){
            if(min < prices[i]){
                continue;
            }else {
                min = Math.min(min, prices[i]);
                for (int j = i + 1; j < prices.length; j++) {
                    max = Math.max(max, prices[j] - prices[i]);
                }
            }
        }
        return max;
    }
}
