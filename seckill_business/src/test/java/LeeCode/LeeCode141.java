package LeeCode;

import java.util.HashSet;

//141. 环形链表
public class LeeCode141 {
    public ListNode hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null){
            if(set.contains(head)){
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
