public class OffByOne implements CharacterComparator {

    /** Returns true if characters are equal by the rules of the implementing class. */
    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == 1 || x - y == -1) {
            return true;
        } else {
            return false;
        }
    }
}
