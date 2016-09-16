public class Solution {
    public char findTheDifference(String s, String t) {
        int xor = 0;
        for(int i=0; i<s.length(); i++)
            xor ^= (s.charAt(i) - 'a');
            
        for(int i=0; i<t.length(); i++)
            xor ^= (t.charAt(i) - 'a');
        return (char)(xor + 'a');
    }
}