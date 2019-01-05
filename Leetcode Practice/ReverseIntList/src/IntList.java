import org.junit.Test;
import static org.junit.Assert.*;

/**
 * class IntList with naked representation, with methods such as of(int[] args) (static method) and reverse() method.
 */

public class IntList {
    private int first;      // first element of the list
    private IntList rest;   // remaining element of the list

    // constructors
    public IntList(int first, IntList rest) {
        this.first = first;
        this.rest = rest;
    }
    public IntList() {
        this(-1, null);
    }

    public static IntList of(int[] args) {
        IntList result, p;
        if (args.length == 0) {
            return null;
        } else {
            result = new IntList(args[0], null);
        }
        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.rest) {
            p.rest = new IntList(args[k], null);
        }
        return result;
    }

    public static IntList reverse(IntList input) {

        return reverseHelper(input, null);
    }

    private static IntList reverseHelper(IntList p, IntList q) {        // p is a stack that we pop from, q is a stack we push onto
        if (p == null) return q;
        else {
            IntList rest = p.rest;
            p.rest = q;
            return reverseHelper(rest, p);
        }
    }

    /*
    @Test
    public void testReverse(){
        IntList C = new IntList();
        int[] input = new int[] {0, 1, 2, 3};
        IntList A = IntList.of(input);
        int[] output = new int[] {3, 2, 1, 0};
        IntList B = IntList.of(output);
        assertEquals(A, reverse(B));
    }
    */

    /**
     * Print all parameters stored in the input IntList
     * @param input
     */
    public static void print(IntList input) {
        IntList p = input;
        if (p.rest == null) {
            System.out.println(p.first);
        } else {
            System.out.println(p.first);
            print(p.rest);
        }

    }

    public static void main(String args[]) {
        int[] input = new int[] {0, 1, 2, 3};
        IntList A = IntList.of(input);
        IntList B = IntList.reverse(A);
        //print(A);
        print(B);
    }
}
