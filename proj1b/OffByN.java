public class OffByN implements CharacterComparator {
    //declare required field
    int N;

    // paramaterized constructor
    public OffByN(int N) {
        this.N = N;
    }
    /** Returns true if characters are equal by the rules of the implementing class. */
    public boolean equalChars(char x, char y) {
        if (x - y == N || x - y == -N) {
            return true;
        } else {
            return false;
        }
    }
}

