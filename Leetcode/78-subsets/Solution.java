/*
Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

public class Solution {
    // Binary trick
    // logb(n) = log(n) / log(b)
    public static double getLog2(int n){
        return Math.log(n) / Math.log(2);
    }
    // No of subsets = 2^n
    // Get the lowest set bit, s = n & ~(n-1)
    // Say, s = 2^4 = 16 = (Binary 10000)
    // Get the index of the lowest set bit = log2(s) = log2(16) = log2(2^4) = 4
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int nSubsets = (1<<nums.length);
        for(int i=0; i<nSubsets; i++){
            int n = i;
            
            List<Integer> t = new ArrayList<Integer>(); 
            while(n != 0){
                // get the lowest set bit
                int lowestSetBit = n & ~(n-1);
                int index = (int)getLog2(lowestSetBit);
                t.add(nums[index]);
                // clear last set bit
                n = n & (n-1);
            }
            result.add(t);
        }
        return result;
    }
}

class Solution2 {
    // Alternative
    public List<Integer> convertToSet(String s, int[] nums){
        List<Integer> r = new ArrayList<>();
        int j = 0;
        for(int i=s.length()-1; i>=0; --i){
            if(s.charAt(i) == '1'){
                r.add(nums[j]);
            }
            j++;
        }
        return r;
    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(n == 0)
            return result;
        Queue<String> Q = new LinkedList<>();
        String x = "1";
        Q.add(x);
        
        result.add(new ArrayList<>());
        while(!Q.isEmpty()){
            String front = Q.remove();
            result.add(convertToSet(front, nums));
            if(front.length() < n){
                Q.add(front + "0");
                Q.add(front + "1");
            }
        }
        return result;
    }
}
