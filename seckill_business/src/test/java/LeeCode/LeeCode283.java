package LeeCode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

//283. 移动零 两次过 ,效率很低  题解有更高效方法。
//使用for (int i = 0; i < nums.length -n; i++)的循环时，使用测试用例 [0,0,1]不通过，当 nums[0]为0时，后面的数字会全部前移，
// 所以应当再次判断nums[i]是否为0，若为0，则继续遍历往前移一步，若不为0，则i++。
public class LeeCode283 {

    @Test
    public void moveZeroes(int[] nums) {
        int n = 0;
        for (int i = 0; i < nums.length -n; ){
            if(nums[i] == 0){
                n++;
                for(int j = i + 1; j < nums.length; j++){
                    nums[j-1] = nums[j];
                }
                nums[nums.length - 1] = 0;
            }else {
                i++;
            }
        }
    }
}
