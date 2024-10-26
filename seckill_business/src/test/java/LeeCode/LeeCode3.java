package LeeCode;

import java.util.HashMap;

//3. 无重复字符的最长子串 不会
public class LeeCode3 {


    public int lengthOfLongestSubstring(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        int i = -1;
        int max = 0;
        for (int j = 0; j < s.length();j++){
            if (map.containsKey(String.valueOf(s.charAt(j)))){
                i = Math.max(i,map.get(String.valueOf(s.charAt(j))));
            }
            map.put(String.valueOf(s.charAt(j)),j);
            max = Math.max(max,j-i);
        }
        return max;
    }
}
