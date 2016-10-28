/*
You are given two linked lists representing two non-negative numbers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
// Alternative
public class Solution {
    // Get the length of the list
    public int getLength(ListNode l){
        int len = 0;
        ListNode cur = l;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        return len;
    }

    ListNode result;
    int carry;
    public void add(ListNode l1, int n1, ListNode l2, int n2){
        // Base cases
        if(n1 == 0 && n2 == 0)
            return;
        
        if(n1 > n2){
            add(l1.next, n1-1, l2, n2);
        }
        else if(n1 < n2){
            add(l1, n1, l2.next, n2-1);
        }
        else
            add(l1.next, n1-1, l2.next, n2-1);
        
        int a = n1 >= n2 ? l1.val : 0;
        int b = n1 <= n2 ? l2.val : 0;
        int sum = (a + b + carry);
        ListNode node = new ListNode(sum % 10);

        // update carry
        carry = sum / 10;
        // Always append at the head
        node.next = result;
        // Make this node the new head
        result = node;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = getLength(l1);
        int n2 = getLength(l2);
        
        result = null;
        add(l1, n1, l2, n2);
        
        if(carry > 0){
            ListNode node = new ListNode(1);
            node.next = result;
            result = node;
        }
        
        return result;
    }
}