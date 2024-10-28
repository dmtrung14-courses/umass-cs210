package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.Adult;
import src.Child;

public class test {
    Child child = new Child(null, null, null, 0, null);
    @Test
    public void TestAdult01(){
        assertEquals(false, child.isTeen(2023));
    }
    
}
