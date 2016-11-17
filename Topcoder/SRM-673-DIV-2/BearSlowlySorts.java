/*

Problem Statement
    
Limak is a little polar bear. He has a int[] w containing a sequence of N distinct numbers. He wants to sort this sequence into ascending order.
 
Limak knows some fast sorting algorithms but in the real world such knowledge sometimes isn't enough. In order to sort the sequence w Limak must physically move the numbers into their correct places. Such a thing can be hard for a little bear.
 
In a single move Limak can take all elements he can reach and sort them into ascending order. The problem is that Limak's arms are too short. Regardless of where he stands, he can only reach N-1 consecutive elements of w. Hence, in each move he can either sort all elements except for the last one, or all elements except for the first one.
 
Limak can make the moves in any order he likes. Compute and return the smallest number of moves necessary to sort the given sequence w.
Definition
    
Class:
BearSlowlySorts
Method:
minMoves
Parameters:
int[]
Returns:
int
Method signature:
int minMoves(int[] w)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Constraints
-
w will contain between 3 and 50 elements, inclusive.
-
Each element in w will be between 1 and 1000, inclusive.
-
w will contain distinct elements.
Examples
0)

    
{2,6,8,5}
Returns: 1
Limak can sort this sequence in a single move. All he needs to do is to sort all elements except for the first one. In this single move Limak will pick up the elements {6,8,5} and reorder them into {5,6,8}. This will change the given w into {2,5,6,8}, and that is a sorted sequence.
1)

    
{4,3,1,6,2,5}
Returns: 2
One of the shortest ways is to sort first N-1 numbers first to get {1,2,3,4,6,5} and then to sort the last N-1 numbers.
2)

    
{93,155,178,205,213,242,299,307,455,470,514,549,581,617,677}
Returns: 0
This sequence is already sorted, no moves are necessary.
3)

    
{50,20,30,40,10}
Returns: 3

4)

    
{234,462,715,596,906,231,278,223,767,925,9,526,369,319,241,354,317,880,5,696}
Returns: 2

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;


public class BearSlowlySorts
{
  public int minMoves(int[] w){
    int n = w.length;
    int[] copy = new int[n];
    System.arraycopy(w, 0, copy, 0, n);
    Arrays.sort(copy);
    if(Arrays.equals(w, copy))
      return 0;
    
    int min = copy[0];
    int max = copy[n-1];
    System.out.println(min + " " + max);
    if(w[0] == min || w[n-1] == max)  
      return 1; 
    if(w[0] == max && w[n-1] == min)  
      return 3;
    return 2; 
  }
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!