package org.simonwithoutgarfunkle.puzzle;

import lombok.extern.slf4j.Slf4j;

import static org.simonwithoutgarfunkle.utils.AdventUtils.*;

@Slf4j
public class Day6 {
    String inputSource = "src/main/resources/input1day6.txt";
    private final char[][] input;

    public Day6() {
        input = convertListToCharMatrix(readInput(inputSource));
    }

    public void solvePuzzle1() {
        int result = 0;
        int[]starter = findChar(input, '^');
        log.info("Starter : {}", starter);

        log.info("Final result for question 1 : {}", result);
    }

    public void solvePuzzle2() {
        int result = 0;

        log.info("Final result for question 2 : {}", result);
    }
}
