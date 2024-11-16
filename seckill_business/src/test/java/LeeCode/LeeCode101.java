package LeeCode;

//101. 对称二叉树
public class LeeCode101 {

    public boolean isSymmetric(TreeNode root) {

        return root == null || cure(root.left , root.right);
    }

    boolean cure(TreeNode left, TreeNode right){
        if(left == null || right == null) return true;
        if(left.val != right.val || left != null || right != null) return false;
        return cure(left.left, right.right) && cure(left.right, right.left);
    }
}
