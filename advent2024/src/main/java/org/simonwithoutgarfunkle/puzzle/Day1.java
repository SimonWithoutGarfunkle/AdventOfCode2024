package org.simonwithoutgarfunkle.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.simonwithoutgarfunkle.utils.AdventUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Day1 {
    String inputSource = "src/main/resources/input1day1.txt";

    private final List<String> input = AdventUtils.readInput(inputSource);
    public void solvePuzzle1() {
        var leftList = input.stream()
                .mapToInt(line -> Integer.parseInt(line.split("\\s+")[0])).sorted()
                .toArray();
        var rightList = input.stream()
                .mapToInt(line -> Integer.parseInt(line.split("\\s+")[1])).sorted()
                .toArray();

        log.info("leftList: {}", leftList);
        log.info("rightList: {}", rightList);
        int result = 0;
        for (int index = 0; index < leftList.length; index++) {
            if (leftList[index] < rightList[index]) {
                result += rightList[index] - leftList[index];
            } else {
                result += leftList[index] - rightList[index];
            }
        }
        log.info("And the answer for puzzle 1 is ... {}", result);
    }

    public void solvePuzzle2() {
        AtomicInteger similarity = new AtomicInteger();
        var leftList = input.stream()
                .mapToInt(line -> Integer.parseInt(line.split("\\s+")[0])).sorted()
                .toArray();
        var rightList = input.stream()
                .mapToInt(line -> Integer.parseInt(line.split("\\s+")[1])).sorted()
                .toArray();

        Map<Integer, Integer> digestLeftList = Arrays.stream(leftList).collect(HashMap::new, (map, i) -> map.put(i, map.getOrDefault(i, 0) + 1), HashMap::putAll);
        Map<Integer, Integer> digestRightList = Arrays.stream(rightList).collect(HashMap::new, (map, i) -> map.put(i, map.getOrDefault(i, 0) + 1), HashMap::putAll);

        digestLeftList.forEach((key, value) -> {
            if (digestRightList.containsKey(key)) {
                similarity.addAndGet(key * value * digestRightList.get(key));
            }
        });


        log.info("And the answer for puzzle 2 is ... {}", similarity.get());
    }
}
