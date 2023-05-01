package model;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearch<T> {

    public BinarySearch() {

    }

    /**
     * Recursive binary search algorithm that takes an ArrayList of objects and a
     * comparator function that maps an object to the attribute you want to search for.
     *
     * @param list         The ArrayList to search
     * @param comparator   The comparator function that maps an object to the attribute you want to search for
     * @param targetValue  The value of the attribute you want to find
     * @param lowIndex     The lowest index to search
     * @param highIndex    The highest index to search
     * @return             The index of the object that has the target attribute value, or -1 if not found
     */
    public int search(ArrayList<T> list, Comparator<T> comparator, T targetValue, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            // If we've narrowed the search down to one element, and it doesn't match, return -1
            return -1;
        }

        // Find the middle index of the current range
        int midIndex = (lowIndex + highIndex) / 2;
        T midValue = list.get(midIndex);

        // Use the comparator function to compare the target value to the middle value
        int compareResult = comparator.compare(targetValue, midValue);

        if (compareResult < 0) {
            // If the target value is less than the middle value, search the left half of the range
            return search(list, comparator, targetValue, lowIndex, midIndex - 1);
        } else if (compareResult > 0) {
            // If the target value is greater than the middle value, search the right half of the range
            return search(list, comparator, targetValue, midIndex + 1, highIndex);
        } else {
            // If the target value is equal to the middle value, we've found our match
            return midIndex;
        }
    }

}
