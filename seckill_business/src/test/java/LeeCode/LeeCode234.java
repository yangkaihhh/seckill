package LeeCode;

import org.springframework.test.web.reactive.server.HeaderAssertions;

import java.util.ArrayList;
import java.util.HashMap;

//234. 回文链表
public class LeeCode234 {

    public boolean isPalindrome(ListNode head) {
        int size  = 0 ;
        ListNode cur =head;
        while (cur != null){
            cur = cur.next;
            size++;
        }
        if(size == 1){
            return true;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 1;
        int j =0;
        ListNode pre =head;
        while (pre != null) {
            if (size % 2 > 0) {
                if (i <= size /2 + 1) {
                    map.put(i, pre.val);
                    j = i++;
                }else {
                    if(map.get(--j) != pre.val){
                        return false;
                    }
                }
            } else {
                if (i <= size / 2) {
                    map.put(i, pre.val);
                    j = i++;
                } else {
                    if (map.get(j--) != pre.val) {
                        return false;
                    }
                }
            }
            pre = pre.next;
        }
        return true;
    }



      public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

}
