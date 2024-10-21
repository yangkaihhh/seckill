package LeeCode;

import javafx.beans.binding.When;
//11. 盛最多水的容器  不过
public class LeeCode11 {

    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i != j){
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return max;
    }
}
