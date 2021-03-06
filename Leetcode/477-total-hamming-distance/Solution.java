/*
The Hamming distance between two integers is the number of positions at which 
the corresponding bits are different.

Now your job is to find the total Hamming distance between all pairs of the given 
numbers.

Example:
Input: 4, 14, 2

Output: 6

Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
showing the four bits relevant in this case). So the answer will be:
HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
Note:
Elements of the given array are in the range of 0 to 10^9
Length of the array will not exceed 10^4.
*/

public class Solution {
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int[] cnt = new int[32];
        
        // For each bit position, count the number of the set bits
        for(int i=31; i>=0; i--){
            for(int num : nums){
                if((num & (1<<i)) != 0)
                    cnt[i]++;
            }
        }
        // Number of integers = n
        // For each bit i, if number of set bits = k, then number of 0 bit = n - k
        // then ith bit contributes n*(n-k) to the total result
        int result = 0;
        for(int i=31; i>=0; i--){
            result += (n-cnt[i])*cnt[i];
        }        
        return result;
    }
}
