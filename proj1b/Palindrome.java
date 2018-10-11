public class Palindrome {

    /** parse the input string to an array of chars (with ArrayDeque)
     * @param word
     * @return Deque</Character>
     */
    public Deque<Character> wordToDeque(String word){

        Deque<Character> charDeque = new ArrayDeque<>();  //create an a new ArrayDeque for storing parsed chars
        int i = 0;
        while(i<word.length()) {
            charDeque.addLast(word.charAt(i));
            i++;
        }
        return charDeque;
    }

    /** check if input string is palindrome, if yes, return true;
     * @param word
     * @return
     */
    public boolean isPalindrome (String word) {
        Deque<Character> charDeque = wordToDeque(word);     //parse input string into Deque of characters
        while(charDeque.size()>1) {
            if(charDeque.removeFirst() == charDeque.removeLast()) {}
            else {
                return false;
            }
        }

        return true;
    }

    /** return true if input string is palindrome
     * @param word
     * @param cc
     * @return boolean value
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> charDeque = wordToDeque(word);
        while(charDeque.size()>1) {
            if(cc.equalChars(charDeque.removeFirst(), charDeque.removeLast())) {}
            else {
                return false;
            }
        }
        return true;
    }
}