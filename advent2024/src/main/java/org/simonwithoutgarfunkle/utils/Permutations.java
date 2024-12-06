package org.simonwithoutgarfunkle.utils;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static List<int[]> getPermutations(int[] array) {
        List<int[]> result = new ArrayList<>();
        permute(array, 0, result);
        return result;
    }

    private static void permute(int[] array, int start, List<int[]> result) {
        if (start == array.length - 1) {
            result.add(array.clone());
        } else {
            for (int i = start; i < array.length; i++) {
                swap(array, start, i);
                permute(array, start + 1, result);
                swap(array, start, i); // backtrack
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
