package mdx.kata.stringcalc;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTest {
    @Test
	public void failingTest() throws Exception {
    	assertTrue("this will fail", false);
	}
    
    @Test
	public void passingTest() throws Exception {
    	assertTrue("this will pass", true);
	}
}
