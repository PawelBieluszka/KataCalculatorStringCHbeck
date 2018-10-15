package kata.calculator.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*author: Paweł Biełuszka*/

public class CalculatorStringTest {

    CalculatorString cs;

    @Before
    public void init(){
        cs = new CalculatorString();
    }

    @Test
    public void emptyStringReturnsZero() throws Exception {
        assertEquals(cs.add(""), 0);
    }

    @Test
    public void singleValueWithComa() throws Exception {
        assertEquals(cs.add("2"), 2);
    }

    @Test
    public void doubleValueWithComa() throws Exception {
        assertEquals(cs.add("1,2"), 3);
    }

    @Test
    public void moreValuesWithComa() throws Exception {
        assertEquals(cs.add("1,2,3"), 6);
        assertEquals(cs.add("1,2,3,9"), 15);
        assertEquals(cs.add("100,,,,,4,,,2,,,,,,7,,,,9"), 122);
    }

    @Test
    public void moreValuesWithNewLine() throws Exception {
        assertEquals(cs.add("1\n2,3"), 6);
        assertEquals(cs.add("1\n2\n3,9"), 15);
        assertEquals(cs.add("100\n\n4\n2\n7,9"), 122);
    }

    @Test
    public void tagOfnewLineAtTheEnd() throws Exception {
        assertEquals(cs.add("1,\n"), 1);
    }

    @Test
    public void moreValuesWithDifferentDelimiters() throws Exception {
        assertEquals(cs.add("//\n1;2"), 3);
    }

    @Test
    public void callingNegativesNumbersThrowsException() throws Exception {
        assertEquals(cs.add("\n-1,-2"), -3);
    }

    @Test
    public void biggerNumbersIgnored() throws Exception {
        assertEquals(cs.add("2,1001"), 2);
        assertEquals(cs.add("2,1000"), 1002);
        assertEquals(cs.add("//2;1000"), 1002);
        assertEquals(cs.add("//2;1010;4"), 6);
        assertEquals(cs.add("//;\n1;1010;2"), 3);
    }

    @Test
    public void allowedAnyLengthDelimeters() throws Exception {
        assertEquals(cs.add("//[***]\n1***2***3"), 6);
        assertEquals(cs.add("//[%%%]\n1%%%2%%%3"), 6);
        assertEquals(cs.add("//&&&&1&&2&3"), 6);
    }

    @Test
    public void allowedMultipleDelimeters() throws Exception {
        assertEquals(cs.add("//[*][%]\n1*2%3"), 6);
    }



}