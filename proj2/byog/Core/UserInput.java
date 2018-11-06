package byog.Core;


/* this class include relevant methods for user's input */
public class UserInput {

    /* return seeds from user's input - numbers between 'N' and 'S' */
    public int seed (String userInput) {
        String inputUpperCase = userInput.toUpperCase();
        String[] parts = inputUpperCase.split("N");
        parts = parts[1].split("S");
        String seed = parts[0];

        return Integer.parseInt(seed);
    }

    /* return move command from user's input - Strings between 'S' and ':Q'
     */
    public String userCommand(String userInput) {
        int length = userInput.length();
        int startPos = 0;
        int endPos = length - 1;
        int pos = 0;

        while (length > 0) {
            if (userInput.charAt(pos) == 'S') {
                startPos = pos;
                break;
            }
            pos++;
            length--;
        }
        pos = 0;
        while (length > 0) {
            if (userInput.charAt(pos) == ':') {
                endPos = pos;
                break;
            }
            pos++;
            length--;
        }
        return userInput.substring(startPos + 1, endPos - 1);
    }
}
