package byog.Core;

import java.util.Random;

/* generate direction */
public class DirectionPicker {

    String[] directionRecorder;

    public DirectionPicker() {
        this.directionRecorder = new String[4];
    }

    private String direction;

    /* randomly pick direction */
    public static String pickRandomDirection() {
        Random RANDOM = new Random();
        switch(RANDOM.nextInt(8)) {
            case 0:
                return "EAST";
            case 1:
                return "WEST";
            case 2:
                return "NORTH";
            case 3:
                return "SOUTH";
            case 4:
                return "EAST";
            case 5:
                return "EAST";
            case 6:
                return "WEST";
            case 7:
                return "WEST";
            default:
                return null;
        }
    }

}
