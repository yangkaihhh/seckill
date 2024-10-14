package LeeCode;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//49. 字母异位词分组  一次过
public class LeeCode49 {
    @Test
    public List<List<String>>  test(String[] strs){
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if (map.containsKey(new String(chars))) {
                map.get(new String(chars)).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(new String(chars), list);
            }
        }
        List<List<String>> lists = new ArrayList<>(map.values());
        return lists;
    }
}
