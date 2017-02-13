/*
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
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
    public int[] findValueMostElement(TreeNode root) {
        if(root == null)    return new int[0];
        
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!Q.isEmpty()){
            int size = Q.size();
            int max = Integer.MIN_VALUE;
            for(int i=0; i<size; ++i){
                TreeNode f = Q.remove();
                max = Math.max(max, f.val);
                if(f.left != null)
                    Q.add(f.left);
                if(f.right != null)
                    Q.add(f.right);
            }
            result.add(max);
        }
        
        int[] r =  new int[result.size()];
        int i = 0;
        for(int t : result)
            r[i++] = t;
        return r;
    }
}