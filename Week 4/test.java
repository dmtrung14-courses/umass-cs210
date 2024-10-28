
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class test{
    @Test
    public void testOne(){
        assertEquals(13, Math.max(2, 13));
    }
    @Test
    public void testTwo(){
        assertTrue(Math.max(2,13) >12);
    }
}