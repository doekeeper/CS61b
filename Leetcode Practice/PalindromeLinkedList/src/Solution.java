
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.Assert.*;


class Solution {
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> SLL = new ArrayDeque<Integer>();
        while (head != null) {
            SLL.add(head.val);
            head = head.next;
        }
        while (SLL.size() != 0) {
            // int first = SLL.getFirst();
            // int last = SLL.getLast();
            if(SLL.peekFirst() != SLL.peekLast()) return false;
            if (SLL.size() == 1) return true;
            SLL.removeFirst();
            SLL.removeLast();
        }
        return true;
    }

    @Test
    public void testIsPalindrome() {
        ListNode head = new ListNode(-129);
        head.next = new ListNode(-129);
        assertTrue(isPalindrome(head));
    }

}