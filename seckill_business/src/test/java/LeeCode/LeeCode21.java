package LeeCode;

//21. 合并两个有序链表
public class LeeCode21 {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode a  = new ListNode(0);
        ListNode listNode = a;
        for (int i=0;i<=200;i++){
            if( list1 != null && list1.val == i ){
                add(listNode , list1.val);
                while (list1 != null &&  list1.val == list1.next.val){
                    list1 = list1.next;
                    add(listNode , list1.val);
                }
            }
            if(list2 != null && list2.val == i){
                add(listNode , list2.val);
                while (list2 != null &&  list2.val == list2.next.val){
                    list2 = list2.next;
                    add(listNode , list2.val);
                }
            }

        }
      return  a.next;
    }

    public boolean add(ListNode head , int a) {  //添加新节点
        ListNode newNode = new ListNode(a);//实例化一个新节点a
        if (head == null) { //若头节点为空，新节点赋值给头节点
            head = newNode;
            return true;
        }
        ListNode cur = head;  //若头节点不为空，用cur代替head进行操作。防止修改head的值
        while (cur.next != null) { //遍历到最后一个节点
            cur = cur.next;
        }
        cur.next = newNode ; //让上一个节点的next指向新节点，完成连接
        return true;
    }
}
