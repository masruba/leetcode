public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        List<Integer> r = new ArrayList<Integer>();
        int x, y;
        int i = 0, j = 0;
        while(i<nums1.length && j<nums2.length){
            x = nums1[i];
            y = nums2[j];
            if(x == y){
                r.add(x);
                i++;
                j++;
            } 
            else if(x < y){
                i++;
            }
            else
                j++;
        }

        int[] t = new int[r.size()];
        for(i=0; i<r.size(); i++){
            t[i]= r.get(i);
        }
        return t;
    }
}