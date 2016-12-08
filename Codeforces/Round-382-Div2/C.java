/*
Famous Brazil city Rio de Janeiro holds a tennis tournament and Ostap Bender 
doesn't want to miss this event. There will be n players participating, and the 
tournament will follow knockout rules from the very first game. That means, 
that if someone loses a game he leaves the tournament immediately.

Organizers are still arranging tournament grid (i.e. the order games will 
happen and who is going to play with whom) but they have already fixed one rule: two 
players can play against each other only if the number of games one of them has 
already played differs by no more than one from the number of games the other 
one has already played. Of course, both players had to win all their games in 
order to continue participating in the tournament.

Tournament hasn't started yet so the audience is a bit bored. Ostap decided to 
find out what is the maximum number of games the winner of the tournament can take 
part in (assuming the rule above is used). However, it is unlikely he can deal with 
this problem without your help.

Input
The only line of the input contains a single integer n (2 ≤ n ≤ 1018) — the number 
of players to participate in the tournament.

Output
Print the maximum number of games in which the winner of the tournament can take part.

Examples
input
2
output
1
input
3
output
2
input
4
output
2
input
10
output
4
Note
In all samples we consider that player number 1 is the winner.

In the first sample, there would be only one game so the answer is 1.

In the second sample, player 1 can consequently beat players 2 and 3.

In the third sample, player 1 can't play with each other player as after he 
plays with players 2 and 3 he can't play against player 4, as he has 0 games played, 
while player 1 already played 2. Thus, the answer is 2 and to achieve we make pairs (1, 2) and (3, 4) and then clash the winners.
*/
import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;

public class C{
  public final static int MAX = 90;
  static long[] fib = new long[MAX];
  static long[] cumSum = new long[MAX];
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);    
    long n = scan.nextLong();

    fib[0] = 1;
    fib[1] = 1;
    for(int i=2; i<MAX; ++i){
      fib[i] = fib[i-1] + fib[i-2];
    }

    cumSum[0] = fib[0];
    for(int i=1; i<MAX; ++i){
      cumSum[i] = cumSum[i-1] + fib[i];
    }

    // binary search on cumSum
    // find smallest number less than n
    int l = 0, h = MAX-1;
    while(h - l > 3){
      int mid = (l+h)/2;
      if(cumSum[mid] < n){
        l = mid;
      }
      // cumSum[mid] >= n
      else{
        h = mid - 1;
      }
    }
    int i = l;
    for(i=l+1; i<=h; ++i){
      if(cumSum[i-1] < n && cumSum[i] >= n){
        break;
      }
    }
    System.out.println(i);
  }
}
