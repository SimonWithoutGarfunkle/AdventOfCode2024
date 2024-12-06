package org.simonwithoutgarfunkle.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.simonwithoutgarfunkle.utils.AdventUtils;

import java.util.*;

import static org.simonwithoutgarfunkle.utils.Permutations.getPermutations;

@Slf4j
public class Day5 {
    String inputSource = "src/main/resources/input1day5.txt";
    private final List<String> input;

    private final List<String> rules;

    private final List<String> updates;

    private final Map<String, List<String>> notBefore = new HashMap<>();
    private final Map<String, List<String>> notAfter = new HashMap<>();
    int finalResult = 0;

    int answer2 = 0;

    public Day5() {
        input = AdventUtils.readInput(inputSource);
        int splitter = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).trim().isEmpty()) {
                splitter = i;
                break;
            }
        }
        rules = input.subList(0, splitter);
        updates = input.subList(splitter + 1, input.size());
    }

    public void solvePuzzle1() {
        rules.forEach(line -> {
            String[] parts = line.split("\\|");
            if (notBefore.containsKey(parts[0])) {
                notBefore.get(parts[0]).add(parts[1]);
            } else {
                notBefore.put(parts[0], new ArrayList<>(Arrays.asList(parts[1])));
            }

            if (notAfter.containsKey(parts[1])) {
                notAfter.get(parts[1]).add(parts[0]);
            } else {
                notAfter.put(parts[1], new ArrayList<>(Arrays.asList(parts[0])));
            }
        });
        updates.stream().filter(this::validateUpdates).forEach(this::addMiddle);
        log.info("Final result for question 1 : {}", finalResult);
    }

    private void addMiddle(String line) {
        String[] parts = line.split(",");
        finalResult += Integer.parseInt(parts[parts.length / 2]);
    }

    private boolean validateUpdates(String updates) {
        return validateUpdate(updates.split(","));
    }

    private boolean validateUpdate(String[] updates) {
        boolean isValid = true;
        for (int i = 0; i < updates.length; i++) {
            String[] before = Arrays.copyOfRange(updates, 0, i);
            String[] after = Arrays.copyOfRange(updates, Math.min(i + 1, updates.length), updates.length);
            try {
                for (String item : notBefore.get(updates[i])) {
                    if (Arrays.asList(before).contains(item)) {
                        isValid = false;
                    }
                }
                for (String item : notAfter.get(updates[i])) {
                    if (Arrays.asList(after).contains(item)) {
                        isValid = false;
                    }
                }
            } catch (NullPointerException e) {
                log.debug("No rules for {}", updates[i]);
            }

        }
        return isValid;
    }

    public void solvePuzzle2() {
        List<int[]> invalids = updates.stream().filter(line -> !validateUpdates(line)).map(line -> {
            String[] elements = line.split(",");
            int[] result = new int[elements.length];
            for (int i = 0; i < elements.length; i++) {
                result[i] = Integer.parseInt(elements[i]);
            }
            return result;
        }).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        invalids.forEach(line -> {
            log.info("working on line {}", Arrays.toString(line));
            getPermutations(line).forEach(permutation -> {
                if (validateUpdate(Arrays.stream(permutation).mapToObj(String::valueOf).toArray(String[]::new))) {
                    answer2 += permutation[permutation.length / 2];
                }
            });
        });


        log.info("Answer 2: {}", answer2);
    }
}