package de.programming.challenge;

import java.util.Collections;
import java.util.List;

/**
 * This class is a general number utility.
 * Can be expanded with the necessary methods.
 *
 * @author cananaucristian
 */
public class NumberUtil {

    /**
     * Returns an integer with the smallest value of a list.
     *
     * @param list A list of integers.
     * @return minIndex
     */
    public static int minIndex(List<Integer> list) {
        return list.indexOf(Collections.min(list));
    }

    /**
     * Returns the difference between two numbers.
     * For the absolute difference, the negative numbers are converted into positive ones.
     *
     * @param firstNumber First number for the calculation difference.
     * @param secondNumber Second number for the calculation difference.
     * @param isAbsoluteDifference Should the negative number be converted into a positive one.
     * @return Difference between two given numbers.
     */
    public static int getDifferenceBetweenTwoNumbers(int firstNumber, int secondNumber, boolean isAbsoluteDifference) {
        if(isAbsoluteDifference) {
            return Math.abs(firstNumber - secondNumber);
        }else {
            return firstNumber - secondNumber;
        }
    }
}
