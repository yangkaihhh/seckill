package LeeCode;

import java.util.ArrayList;
import java.util.List;
//94. 二叉树的中序遍历
public class LeeCode94 {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversal2(list, root);
        return list;
    }

    void inorderTraversal2(List<Integer> list , TreeNode root) {
        if(root == null){
            return ;
        }
        inorderTraversal2(list,root.left);
        list.add(root.val);
        inorderTraversal2(list,root.right);
    }
}
