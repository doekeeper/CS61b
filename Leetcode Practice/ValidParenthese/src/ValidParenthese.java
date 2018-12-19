/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets
 * 2. Open brackets must be closed in the correct order
 * Note that an empty string is also consider valid
 */
import static org.junit.Assert.*;
import org.junit.Test;

public class ValidParenthese {
    public boolean isValid(String s) {
        //TODO

        /**
         * PseudoCode as follows
         * 0. check the length of the string; if the length is odd, return false;
         * 0.5 check the first char if it is right bracket; If so, return false; if not, continue
         * 1. Find the first right bracket, such as ')', ']', '}', track its location i
         * 2. check if char at i-1 position match char at i; If not, return false; continue otherwise
         * 3. create new String containing all chars except chat at i and i-1;
         * 4. check if the length of the String is zero; if so, return true; continue otherwise;
         * 5. repeat step 0.5 - step 4;
         */
            String input = s;
            if (s.length() % 2 == 1) return false;  // return false if the length of the String is odd number
            int pos = 1;                                // track pos
            String newStr = "";                          // track new String
            while (!s.isEmpty()) {               // while loop until String is empty string
                if (s.charAt(0) == ')' || s.charAt(0) == '}'|| s.charAt(0) == ']') return false;
                if (s.indexOf(')') == -1 && s.indexOf('}') == -1 && s.indexOf(']') == -1) return false;
                // return false if the 1st char in the String is right bracket
                for (int i = 0; i < s.length(); i++) {// loop over the whole String to search the first right bracket
                    if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                        pos = i;
                        if (s.charAt(i) == ')' & s.charAt(i - 1) == '(') break;
                        else if (s.charAt(i) == ']' & s.charAt(i - 1) == '[') break;
                        else if (s.charAt(i) == '}' & s.charAt(i - 1) == '{') break;
                        else return false;
                    }
                }
                if (pos == s.length() - 1) newStr = s.substring(0, pos-1);
                else
                    try {
                        newStr = s.substring(0, pos-1) + s.substring(pos+1);
                    } catch(StringIndexOutOfBoundsException e) {
                        System.out.println("StringIndexOutOfBoundsException");
                        System.out.println("Original String is:" + input);
                        System.out.println("the length of s is:" + s.length());
                        System.out.println("pos is:" + pos);
                        break;
                    }
                s = newStr;
            }
            return true;
        }


    @Test
    public void testIsValid() {
        assertTrue(isValid("()"));
        assertTrue(isValid("()[]{}"));
        assertFalse(isValid("(]"));
        assertFalse(isValid("([)]"));
        assertTrue(isValid("{[]}"));
        assertFalse(isValid("(()("));
    }
}
