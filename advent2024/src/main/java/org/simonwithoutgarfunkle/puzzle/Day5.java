package org.simonwithoutgarfunkle.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.simonwithoutgarfunkle.AdventUtils;

import java.util.*;

@Slf4j
public class Day5 {
    String inputSource = "src/main/resources/input1day5.txt";
    private final List<String> input = AdventUtils.readInput(inputSource);

    private List<String> rules = new ArrayList<>();

    private List<String> updates = new ArrayList<>();

    private final Map<String, List<String>> notBefore = new HashMap<>();
    private final Map<String, List<String>> notAfter = new HashMap<>();
    int finalResult = 0;

    public void solvePuzzle1() {
        int splitter = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).trim().isEmpty()) {
                splitter = i;
                break;
            }
        }

        rules = input.subList(0, splitter);
        updates = input.subList(splitter + 1, input.size());

        log.info("Rules: {}", rules);
        log.info("Updates: {}", updates);

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
        log.info("Final result: {}", finalResult);
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
        for (int i = 0 ; i < updates.length; i++) {
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
                log.error("No rules for {}", updates[i]);
            }

        }
        return isValid;
    }

}