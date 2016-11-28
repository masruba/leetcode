/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
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
    // Time: O(logn)
    // Binary Search
    public int closestValue(TreeNode root, double target) {
        TreeNode cur = root;    
        int r = -1;
        double minDiff = Double.MAX_VALUE;
        while(cur != null){
            double diff = Math.abs((double)cur.val - target);
            if(minDiff > diff){
                minDiff = diff;
                r = cur.val;
            }
            // All nodes in the right subtree will have larger value than root, 
            // So diff will be greater if we traverse the right subtree and we can prune
            if((double)cur.val > target){
                cur = cur.left;
            }
            // As cur.val <= target, all nodes in the left subtree is less than cur and 
            // diff will be greater if we traverse the left subtree
            else{
                cur = cur.right;
            }
        }
        return r;
    }
}