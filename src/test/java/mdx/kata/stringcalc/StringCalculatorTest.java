package mdx.kata.stringcalc;

import org.junit.Test;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Marcin Deryło <marcinderylo@gmail.com>
 */
public class StringCalculatorTest {
    @Test
    public void givenEmptyStringReturnsZero() throws Exception {
        String emptyString = "";
        assertResultForGivenInputStringIs(emptyString, 0);
    }

    @Test
    public void givenSingleNumberReturnsItsValue() throws Exception {
        String singleNumber = "123";
        int valueOfTheNumber = 123;
        assertResultForGivenInputStringIs(singleNumber, valueOfTheNumber);
    }

    @Test
    public void givenPairOfComaSeparatedNumbersReturnTheirSum() throws Exception {
        String pairOfNumbers = "5,13";
        int sumOfNumbers = 5 + 13;
        assertResultForGivenInputStringIs(pairOfNumbers, sumOfNumbers);
    }

    @Test
    public void givenMoreThanTwoComaSeparatedNumbersReturnsTheirSum() throws Exception {
        String fourNumbers = "1,2,3,4";
        int sumOfNumbers = 1 + 2 + 3 + 4;
        assertResultForGivenInputStringIs(fourNumbers, sumOfNumbers);
    }

    @Test
    public void allowsUsingNewLinesAsSeparators() throws Exception {
        String numbersSeparatedWithNewLines = "3\n4\n6";
        int sumOfNumbers = 3 + 4 + 6;
        assertResultForGivenInputStringIs(numbersSeparatedWithNewLines, sumOfNumbers);
    }

    @Test
    public void allowsMixingComasOrNewLinesAsSeparators() throws Exception {
        String numbersSeparatedWithNewLinesAndCommas = "2,3\n4";
        int sumOfNumbers = 2 + 3 + 4;
        assertResultForGivenInputStringIs(numbersSeparatedWithNewLinesAndCommas, sumOfNumbers);
    }

    @Test
    public void usesCustomSeparatorDefinedInFirstLineOfInput() throws Exception {
        String numbersWithCustomSeparator = "//;\n5;6;8";
        int sumOfNumbers = 5 + 6 + 8;
        assertResultForGivenInputStringIs(numbersWithCustomSeparator, sumOfNumbers);
    }

    @Test
    public void givenCustomSeparatorButNoNumbersReturnsZero() throws Exception {
        String customSeparatorWithNoNumbers = "//;\n";
        assertResultForGivenInputStringIs(customSeparatorWithNoNumbers, 0);
    }

    @Test
    public void givenCustomSeparatorButJustOneNumberReturnsThatNumberValue() throws Exception {
        String customSeparatorWithNoNumbers = "//;\n9";
        assertResultForGivenInputStringIs(customSeparatorWithNoNumbers, 9);
    }

    @Test
    public void regexSpecialCharactersCanBeSeparatorsToo() throws Exception {
        String dotSeparatedNumbers = "//.\n17.18";
        assertResultForGivenInputStringIs(dotSeparatedNumbers, 17 + 18);
    }

    @Test
    public void exceptionIsThrownWhenNegativeNumberIsFound() throws Exception {
        String numbersWithNegativeNumber = "5,4,-2";
        assertInputStringCausesExceptionToBeThrownWithMessage(numbersWithNegativeNumber, "Negatives not allowed: [-2]");
    }

    @Test
    public void exceptionIsThrownWhenMultipleNegativesAreFound() throws Exception {
        String numbersWithNegativeNumber = "5,-4,-2";
        assertInputStringCausesExceptionToBeThrownWithMessage(numbersWithNegativeNumber, "Negatives not allowed: [-4, -2]");
    }

    @Test
    public void numberGreaterThan1000IsIgnored() throws Exception {
        String singleBigNumber = "1001";
        assertResultForGivenInputStringIs(singleBigNumber, 0);
    }

    @Test
    public void multipleNumbersGreaterThan1000AreIgnored() throws Exception {
        String singleBigNumber = "1001,1,1002";
        assertResultForGivenInputStringIs(singleBigNumber, 1);
    }

    @Test
    public void numbersUpTo1000AreSummedUp() throws Exception {
        String singleBigNumber = "1000,1";
        assertResultForGivenInputStringIs(singleBigNumber, 1000 + 1);
    }

    private void assertInputStringCausesExceptionToBeThrownWithMessage(String inputString, String expectedErrorMsg) {
        try {
            calculateSum(inputString);
            fail("exception should have been thrown");
        } catch (Exception e) {
            assertEquals("failure message", expectedErrorMsg, e.getMessage());
        }

    }

    private void assertResultForGivenInputStringIs(String inputString, int expectedResult) {
        int actualResult = calculateSum(inputString);
        assertEquals(format("sum of numbers in input string \"%s\"", inputString), expectedResult, actualResult);
    }

    private int calculateSum(String inputString) {
        return new StringCalculator().add(inputString);
    }
}
