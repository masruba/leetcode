/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.
*/

/*
left(i,j) = max(left(i-1,j), cur_left), cur_left can be determined from the current row
right(i,j) = min(right(i-1,j), cur_right), cur_right can be determined from the current row
height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1';
height(i,j) = 0, if matrix[i][j]=='0'
*/

public class MaximalRectangle {
    // height -  number of '1's above including the current one. 
    // The value of left & right means the boundaries of the rectangle which contains the current point with a height of value height.
    // https://discuss.leetcode.com/topic/6650/share-my-dp-solution    
    // Alternative: DP
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if(r == 0)
            return 0;
        int c = matrix[0].length;
        int[] height = new int[c];
        Arrays.fill(height, 0);
        
        int[] left = new int[c];
        Arrays.fill(left, 0);
        
        int[] right = new int[c];
        Arrays.fill(right, c);
        
        int maxArea = 0;
        for(int i=0; i<r; i++){
            // Compute height
            for(int j=0; j<c; j++){
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }
            
            // left boundary
            int curLeft = 0;
            for(int j=0; j<c; j++){
                if(matrix[i][j] == '1')
                    left[j] =  Math.max(left[j] /* prev */, curLeft);
                else{
                    left[j] = 0;
                    curLeft = j + 1;
                }
            }
            // right boundary
            int curRight = c;
            for(int j=c-1; j>=0; j--){
                if(matrix[i][j] == '1')
                    right[j] =  Math.min(right[j] /* prev */, curRight);
                else{
                    right[j] = c;
                    curRight = j;
                }
            } 
            
            for(int j = 0; j<c; j++){
                maxArea = Math.max(maxArea, (right[j] - left[j])*height[j]);
            }
        }
        return maxArea;
    }
}

public class MaximalRectangleStack {
    // if any previous height is higher than current height, then we don't need to remember that previous height 
    public int largestRectangleArea(int[] heights){
        int n = heights.length;
        // stack contains indices
        Deque<Integer> stk = new LinkedList<>();
        int maxArea = 0;
        for(int i=0; i<=n; i++){
            // stack is not empty (i is either past last index or height of A[i] < height of top of the stack)
            while(!stk.isEmpty() && (i == n || heights[i] < heights[stk.peekFirst()])){
                int height = heights[stk.removeFirst()];
                // if stack is empty then the rectangle can extend to 0
                // Notice the pop: i-stk.top()-1 extend the rectangle to the earlier height 
                maxArea = Math.max(maxArea, height * (stk.isEmpty() ? i :  i - stk.peekFirst() - 1));
            }
            stk.addFirst(i);
        }
        return maxArea;
    }
    //  convert the 2D matrix. if current row-col is '0', simple treat this row-col in histogram as 0.
    public int maximalRectangle(char[][] matrix) {
        int r = matrix.length;
        if(matrix == null || r == 0 || matrix[0].length == 0)
            return 0;
        int c = matrix[0].length;

        int[] hist = new int[c];
        int maxArea = 0;
        for(int i=0; i<r; i++){
          for(int j=0; j<c; j++){
              if(i == 0){
                  hist[j] = matrix[i][j] - '0';
                  continue;
              }
              if(matrix[i][j] == '0')
                  hist[j] = 0;
              else
                  hist[j] += 1; 
          }
          maxArea = Math.max(maxArea, largestRectangleArea(hist));
        }
        return maxArea;
    }
}