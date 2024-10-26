package LeeCode;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//15. 三数之和      不会
public class LeeCode15 {

    @Test
    public void threeSum() {
        int[] nums = {0,0,0};

//        -4 -1 -1 -0  1 2
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++){

            int k = nums.length  -1;
            int j = i + 1;
            if (i > 0 && nums[i] == nums[i-1]) continue;
            if(j == k){
                System.out.println(lists);
//                return lists;
            }
            if(nums[i] > 0) {
                System.out.println(lists);
//                return lists;
            }
            while (j < k) {
                int n = nums[i] + nums[j] + nums[k];
                if (n == 0) {
                    List<Integer> integers = Arrays.asList(nums[i], nums[j], nums[k]);
                    lists.add(integers);
                    while (j < k && nums[j] == nums[++j]) ;
                    while (j < k && nums[k] == nums[--k]) ;
                } else if (n > 0) {
                    while (j < k && nums[k] == nums[--k]) ;
                } else {
                    while (j < k && nums[j] == nums[++j]) ;
                }
            }
        }
//        return lists;
    }
}
