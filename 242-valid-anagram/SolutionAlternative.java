public class Solution {
    // Alternative : Sorting
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        
        int n = s.length();
        char[] s1 =  s.toCharArray();
        Arrays.sort(s1);

        char[] t1 =  t.toCharArray();
        Arrays.sort(t1);
        
        for(int i=0; i<n; i++)
            if(s1[i] != t1[i])
                return false;
        return true;
    }
}