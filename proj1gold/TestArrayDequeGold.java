import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    boolean status = true;
    double token;
    @Test
    public void testArrayDeque() {
        ArrayDequeSolution ads = new ArrayDequeSolution<Integer>();
        StudentArrayDeque sad = new StudentArrayDeque<Integer>();
        while (true) {
            token = StdRandom.uniform();
            if(token > 0 && token < 0.25) {                 // test addFirst method
                int randInt = StdRandom.uniform(100);
                ads.addFirst(randInt);
                sad.addFirst(randInt);
                assertEquals(ads, sad);
            } else if (token >= 0.25 && token < 0.5) {      // test addLast method
                int randInt = StdRandom.uniform(100);
                ads.addLast(randInt);
                sad.addLast(randInt);
                assertEquals(ads, sad);
            } else if (token >= 0.5 && token < 0.75 && !ads.isEmpty() && !sad.isEmpty()) {      // test remove First method, Deque cannot be empty
                assertEquals(ads.removeFirst(), sad.removeFirst());
                assertEquals(ads, sad);
            } else if (token >= 0.75 && token < 1 && !ads.isEmpty() && !sad.isEmpty()) {
                assertEquals(ads.removeLast(), sad.removeLast());
                assertEquals(ads, sad);
            }
        }
    }
}
