package org.simonwithoutgarfunkle.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.simonwithoutgarfunkle.AdventUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Day2 {
    String inputSource = "src/main/resources/input1day2.txt";
    private final List<String> input = AdventUtils.readInput(inputSource);

    public void solvePuzzle1() {
        List<List<Integer>> data = input.stream()
                .map(line -> Arrays.stream(line.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .toList();
        long result;
        log.info("data: {}", data);
        result = data.stream().filter(this::isSafe).count();
        log.info("And the answer for puzzle 1 is ... {}", result);
    }

    private boolean isSafe(List<Integer> row) {
        return hasUniqueElements(row) && (isSortedAscending(row) || isSortedDescending(row)) && hasValidGaps(row);
    }

    private boolean hasUniqueElements(List<Integer> row) {
        return row.size() == new HashSet<>(row).size();
    }

    private boolean isSortedAscending(List<Integer> row) {
        return row.equals(row.stream().sorted().collect(Collectors.toList()));
    }

    private boolean isSortedDescending(List<Integer> row) {
        return row.equals(row.stream().sorted((a, b) -> b - a).collect(Collectors.toList()));
    }

    private boolean hasValidGaps(List<Integer> row) {
        for (int i = 0; i < row.size() - 1; i++) {
            if (Math.abs(row.get(i) - row.get(i + 1)) > 3) {
                return false;
            }
        }
        return true;
    }

    public void solvePuzzle2() {
        List<List<Integer>> data = input.stream()
                .map(line -> Arrays.stream(line.split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()))
                .toList();

        long result = data.stream().filter(line -> variant(line).stream().anyMatch(this::isSafe)).count();
        log.info("And the answer for puzzle 2 is ... {}", result);

    }

    private List<List<Integer>> variant(List<Integer> row) {
        List<List<Integer>> variants = new ArrayList<>();
        for (int i = 0; i < row.size(); i++) {
            List<Integer> copy = new ArrayList<>(row);
            copy.remove(i);
            variants.add(copy);
        }
        variants.add(row);
        return variants;
    }
}
