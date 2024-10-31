package LeeCode;


////21. 合并两个有序链表  K神题解
public class LeeCode21_new {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode(0);
        ListNode cur = listNode;

        while (list1 != null && list2 != null){
            if(list1.val > list2.val){
                cur.next = list2;
                list2 = list2.next;
                cur = cur.next;

            }else {
                cur.next = list1;
                list1 = list1.next;
                cur = cur.next;
            }
        }

        cur.next = list1==null ?  list2 : list1;
        return listNode.next;

    }
}
