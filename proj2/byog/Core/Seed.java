package byog.Core;

/* helper class for collecting seed from user's input, and it can to distribute the same seed to all affected class/method
 */
public class Seed {
    private long seed;

    public Seed() {             // set default seed value to 0
        this.seed = 0;
    }
    public Seed(int seed) {     // set seed value to input, if there is any
        this.seed = seed;
    }


    // getters and setter
    public long getSeed() {
        return seed;
    }
}
