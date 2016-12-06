/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the 
lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, 
since a node can be a descendant of itself according to the LCA definition.
*/
public class Solution {
    class Pair{
        public int numNodes;
        public TreeNode ancestor;
        public Pair(int n, TreeNode a){
            numNodes = n;
            ancestor = a;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorHelper(root, p, q).ancestor;
    }
    
    public Pair lowestCommonAncestorHelper(TreeNode node, TreeNode p, TreeNode q){
        if(node == null)
            return new Pair(0, null);
        Pair l = lowestCommonAncestorHelper(node.left, p, q);
        if(l.numNodes == 2){
            return l;
        } 
        Pair r = lowestCommonAncestorHelper(node.right, p, q);
        if(r.numNodes == 2){
            return r;
        } 
        int n = l.numNodes + r.numNodes + (node == p ? 1 : 0) + (node == q ? 1 : 0);
        return n == 2 ? new Pair(2, node) : new Pair(n, null);
    }
}

// Alternative: Assuming both nodes are present in the tree
class Solution2 {
    class Pair{
        public boolean isAncestor;
        public TreeNode node;
        public Pair(TreeNode a, boolean n) {
            isAncestor = n;
            node = a;
        }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Pair r = lowestCommonAncestorHelper(root, p, q);
        return r.isAncestor ? r.node : null;
    }
    
    public Pair lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q){
        if(root == null)                return new Pair(null, false);
        
        if(root == p && root == q)      return new Pair(root, true);
        
        Pair l = lowestCommonAncestorHelper(root.left, p, q);
        // Found common ancestor
        if(l.isAncestor)        return l;

        Pair r = lowestCommonAncestorHelper(root.right, p, q);
        // Found common ancestor
        if(r.isAncestor)        return r;
        
        if(l.node != null && r.node != null)
            return new Pair(root, true);
        if(root == p || root == q){
            // At p or q and found the other node in one of the subtrees
            return new Pair(root, l.node != null || r.node != null);
        }
        else{
            return new Pair(l.node != null ? l.node : r.node, false);
        }
    }
}

class Solution3 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q || root == null)      return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q); 
        TreeNode right = lowestCommonAncestor(root.right, p, q); 
        return left == null ? right : right == null ? left : root;
    }
}

class Solution4 {
    // Alternative: Iterative Version
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        
        Queue<TreeNode> Q = new LinkedList<>();
        Q.add(root);
        while(!parent.containsKey(p) || !parent.containsKey(q)){
            TreeNode t = Q.remove();
            if(t.left != null){
                parent.put(t.left, t);
                Q.add(t.left);
            }
            if(t.right != null){
                parent.put(t.right, t);
                Q.add(t.right);
            }
        }
        
        Set<TreeNode> ancestors = new HashSet<>();
        while(p != null){
            ancestors.add(p);
            p = parent.get(p);
        }
        while(!ancestors.contains(q))
            q = parent.get(q);
        return q;
    }
}