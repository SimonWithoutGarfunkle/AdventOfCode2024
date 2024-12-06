package org.simonwithoutgarfunkle.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AdventUtils {
    public static List<String> readInput(String path) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(path));
            result = reader.lines().toList();
            reader.close();
        } catch (IOException e) {
            log.info("Error reading input file: {}", e.getMessage());
        }
        return result;
    }

    public static char[][] symmetry(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] result = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;
    }

    public static char[][] convertListToCharMatrix(List<String> list) {
        char[][] result = new char[list.size()][list.get(0).length()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).toCharArray();
        }
        return result;
    }

    public static List<String> convertCharMatrixToList(char[][] matrix) {
        List<String> result = new ArrayList<>();
        for (char[] row : matrix) {
            result.add(new String(row));
        }
        return result;
    }

    public static void displayMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            log.info("{}", row);
        }
    }

    public static List<String> convertCharMatrixToDiagonalList(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        List<String> result = new ArrayList<>();

        for (int d = 0; d < rows + cols - 1; d++) {
            StringBuilder diagonal = new StringBuilder();
            for (int i = 0; i <= d; i++) {
                int j = d - i;
                if (i < rows && j < cols) {
                    diagonal.append(matrix[i][j]);
                }
            }
            result.add(diagonal.toString());
        }

        return result;
    }

    public static char[][] rotate90DegreesClockwise(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        char[][] result = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = matrix[i][j];
            }
        }
        return result;
    }
}
