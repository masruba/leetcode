/*
Given a string, find if there is any sub-sequence that repeats itself.
A sub-sequence of a string is defined as a sequence of characters generated by deleting some characters in the string without changing the order of the remaining characters.

Input: 
string

Output:

0/1
0 -> No
1 -> Yes 
Example:

abab ------> yes, ab is repeated. So, return 1. 
abba ------> No, a and b follow different order. So, return 0. 
NOTE : sub-sequence length should be greater than or equal to 2
*/
// LCS Variant

/*
Our task is to find a repeating subsequence. 
Or rather, lets say if we can find the longest repeating subsequence. If the length > 1, we return 0.

Now, to find longest repeating subsequence, lets try finding the longest common subsequence between the string A and itself ( LCS(A, A) ). 
The only restriction we want to impose is that you cannot match a character with its replica in the other string. 
In other words, if S1 and S2 are the replicas of the string for which we want to find LCS, S1[i] != S2[i] for all index i.
*/

public class RepeatingSubSequence {
  public int anytwo(String s) {
      int n = s.length();
      // LCS between the same string
      int[][] lcs = new int[n+1][n+1];
      
      for(int i=1; i<=n; i++){
          for(int j=1; j<=n; j++){
              if(i != j && s.charAt(i-1) == s.charAt(j-1)){
                  lcs[i][j] = lcs[i-1][j-1] + 1; 
              } 
              else{
                  lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
              }
          }
      }
      return lcs[n][n] >= 2 ? 1 : 0;
  }
}