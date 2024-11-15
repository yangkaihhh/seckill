package LeeCode;

//104二叉树深度
public class LeeCode104 {
    public int maxDepth(TreeNode root) {
        if(root==null) return 0;
        return Math.max(maxDepth(root.left)+1,maxDepth(root.right)+1);
    }
}
