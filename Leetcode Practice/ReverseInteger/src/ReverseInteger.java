/**
 * LeetCode #7 - Easy
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example 1: input 123; output 321
 * Example 2: input -123; output -321
 * Example 3: input 120; output 21;
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:[-2^31, 2^32-1].
 * For the purpose of this problem, assume that your function returns when he reversed integer overflows.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class ReverseInteger {
    public int reverse(int x) {
        // TODO
        long result = 0;
        while (x != 0) {
            result = (result * 10) + (x % 10);
            x = x /10;
        }
        return (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE ? 0: (int) result);
    }


    @Test
    public void testReverse() {
        assertEquals(123, reverse(321));
        assertEquals(-123, reverse(-321));
        assertEquals(21, reverse(120));
    }
}