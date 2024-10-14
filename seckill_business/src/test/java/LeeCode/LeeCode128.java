package LeeCode;

import java.util.*;

//128. 最长连续序列   不会
public class LeeCode128 {

    public int longestConsecutive(int[] nums) {
        HashSet<Integer> integers = new HashSet<>();
        //去重
        for (int num : nums) {
            integers.add(num);
        }

        int max = 0;

        for (Integer integer : integers) {
            int cur = 1;
            //当有前一个数字j，则表示是连续的，且长度必比该数字为头部时长。跳过该数字，扫描到 j 时，再计算长度。
            if(integers.contains(integer - 1)){
                continue;
            }else {
                //当该数字没有前一个数字，则判断为可以为头部，扫描到下一个数字，判断是否为连续，若为连续，则长度加一，若为不连续，则跳出循环。
                while (true){
                    integer++;
                    if(integers.contains(integer)){
                        cur++;
                    }else{
                        break;
                    }
                }
                if (cur > max){
                    max = cur;
                }
            }
        }

        return max;
    }
}
