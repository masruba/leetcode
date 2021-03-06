/*
Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {
    // O(n) time & O(n) space
    // Returns the last node in the subtree
    public TreeNode flattenTree(TreeNode node){
        if(node == null)
            return null;
        if(node.left == null){
            if(node.right == null)
                return node;
            return flattenTree(node.right);
        }
        TreeNode left = flattenTree(node.left);
        if(node.right == null){
            node.right = node.left;
            node.left = null;
            return left;
        }
        left.right = node.right;
        node.right = node.left;
        node.left = null;
        // left.right contains the previous right subtree
        return flattenTree(left.right);
        
    }
    public void flatten(TreeNode root) {
        flattenTree(root);
    }
}

class Solution2 {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        if(prev != null)
            prev.right = root;
        
        prev = root;
        TreeNode right = root.right;        
        flatten(root.left);
        root.left = null;
        flatten(right);
    }
}


// Time : O(1) Space
class Solution3 {
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        
        TreeNode cur = root;
        while(cur != null){
            if(cur.left != null){
                // find the right most node of left child
                TreeNode prev = cur.left;
                while(prev.right != null){
                    prev = prev.right;
                }
                
                prev.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}

class Solution4 {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
