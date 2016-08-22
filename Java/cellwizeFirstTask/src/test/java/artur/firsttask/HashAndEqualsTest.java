package artur.firsttask;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public class HashAndEqualsTest {
    @Test
    public void hashAndEqualsTest() {
        Point one = new Point(0,0);
        Point two = new Point(0,0);
        System.out.println(one.hashCode());
        System.out.println(two.hashCode());
        assertEquals("true", true, (one.hashCode() == two.hashCode()));
        assertEquals("true", true, (one.equals(two)));
    }
}
