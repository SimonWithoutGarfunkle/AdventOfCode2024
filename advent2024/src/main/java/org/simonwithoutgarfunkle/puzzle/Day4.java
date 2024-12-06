package org.simonwithoutgarfunkle.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.simonwithoutgarfunkle.utils.AdventUtils;

import java.util.Arrays;
import java.util.List;

import static org.simonwithoutgarfunkle.utils.AdventUtils.*;

@Slf4j
public class Day4 {

    private final String input = "src/main/resources/input1day4.txt";
    private final List<String> lines = AdventUtils.readInput(input);

    private final char[][] matrix = convertListToCharMatrix(lines);

    private int answer2 = 0;

    public void solvePuzzle1() {
        int result;

        List<String> reversed = convertCharMatrixToList(symmetry(convertListToCharMatrix(lines)));
        List<String> diagonale = convertCharMatrixToDiagonalList(convertListToCharMatrix(lines));
        List<String> otherSideDiagonale = convertCharMatrixToDiagonalList(rotate90DegreesClockwise(convertListToCharMatrix(reversed)));
        log.info("Other side diagonale: {}", otherSideDiagonale);

        result = countXmasList(lines, reversed, diagonale, otherSideDiagonale);

        log.info("And the answer for puzzle 1 is ... {}", result);
    }

    private int countXmas(String line) {
        return StringUtils.countMatches(line, "XMAS") + StringUtils.countMatches(line, "SAMX");
    }

    @SafeVarargs
    private int countXmasList(List<String>... lists) {
        return Arrays.stream(lists)
                .flatMap(List::stream)
                .mapToInt(this::countXmas)
                .sum();
    }

    public void solvePuzzle2() {
        for (int x = 1; x < matrix.length - 1; x++) {
            for (int y = 1; y < matrix[x].length - 1; y++) {
                if (matrix[x][y] == 'A') {
                    controlX(x, y);
                }
            }
        }

        log.info("And the answer for puzzle 2 is ... {}", answer2);
    }

    public void controlX(int x, int y) {
        String diagonal = "" + matrix[x-1][y-1] + 'A' + matrix[x+1][y+1];
        if ("MAS".equals(diagonal) || "SAM".equals(diagonal)) {
            String otherDiagonal = "" + matrix[x-1][y+1] + 'A' + matrix[x+1][y-1];
            if ("MAS".equals(otherDiagonal) || "SAM".equals(otherDiagonal)) {
                answer2++;
            }
        }
    }
}
