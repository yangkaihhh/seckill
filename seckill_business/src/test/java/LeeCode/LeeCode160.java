package LeeCode;


import java.util.HashSet;

//160. 相交链表 不会
public class LeeCode160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode A = headA;

        ListNode B = headB;

        while(A != B){
            A = A != null ? A.next : headB;
            B = B != null ? B.next : headA;
        }
        return A;
    }

    public class ListNode {
     int val;
      ListNode next;
     ListNode(int x) {
         val = x;
          next = null;
      }
  }
}
