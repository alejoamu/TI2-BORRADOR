package model;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearch<T> {

    public BinarySearch() {

    }

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

    public ArrayList<T> searchObjectsByProperty(ArrayList<T> list, Comparator<T> comparator, T target) {
        // Search for objects with the same property value using binary search
        ArrayList<T> result = new ArrayList<>();
        int index = search(list, comparator, target, 0, list.size() - 1);

        if (index != -1) {
            // Add the object at the found index to the result list
            result.add(list.get(index));

            // Search for any other objects with the same property value that appear before the found index
            int leftIndex = index - 1;
            while (leftIndex >= 0 && comparator.compare(list.get(leftIndex), result.get(0)) == 0) {
                result.add(0, list.get(leftIndex));
                leftIndex--;
            }

            // Search for any other objects with the same property value that appear after the found index
            int rightIndex = index + 1;
            while (rightIndex < list.size() && comparator.compare(list.get(rightIndex), result.get(result.size() - 1)) == 0) {
                result.add(list.get(rightIndex));
                rightIndex++;
            }
        }
        return result;
    }

    public ArrayList<T> searchRangeOrInterval(ArrayList<T> list, Comparator<T> comparator, T minValue, T maxValue, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            // If we've narrowed the search down to one element, and it doesn't match, return an empty list
            return null;
        }

        // Find the middle index of the current range
        int midIndex = (lowIndex + highIndex) / 2;
        T midValue = list.get(midIndex);

        // Use the comparator function to compare the target value to the middle value
        int compareMin = comparator.compare(midValue, minValue);
        int compareMax = comparator.compare(midValue, maxValue);

        if (compareMin < 0) {
            // If the middle value is less than the minimum value, search the right half of the range
            return searchRangeOrInterval(list, comparator, minValue, maxValue, midIndex + 1, highIndex);
        } else if (compareMax > 0) {
            // If the middle value is greater than the maximum value, search the left half of the range
            return searchRangeOrInterval(list, comparator, minValue, maxValue, lowIndex, midIndex - 1);
        } else {
            // If the middle value is within the specified range, recursively search both halves of the range
            ArrayList<T> results = new ArrayList<>();
            int left = midIndex;
            while (left > lowIndex && comparator.compare(list.get(left - 1), minValue) >= 0) {
                left--;
            }
            int right = midIndex;
            while (right < highIndex && comparator.compare(list.get(right + 1), maxValue) <= 0) {
                right++;
            }
            for (int i = left; i < right + 1; i++) {
                results.add(list.get(i));
            }
            return results;
        }
    }

}
