package org.simonwithoutgarfunkle.puzzle;

import lombok.extern.slf4j.Slf4j;
import org.simonwithoutgarfunkle.AdventUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Day3 {
    String inputSource = "src/main/resources/input1day3.txt";
    private final String input = AdventUtils.readInput(inputSource).getFirst();

    public void solvePuzzle1() {
        String pattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        List<String> validStrings = new ArrayList<>();
        while (m.find()) {
            validStrings.add(m.group(0));
        }

        long result = validStrings.stream()
                .mapToLong(this::mul)
                .reduce(0, Long::sum);
        log.info("And the answer for puzzle 1 is ... {}", result);
    }

    public void solvePuzzle2() {
        String pattern = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(removeDont(input));
        List<String> validStrings = new ArrayList<>();
        while (m.find()) {
            validStrings.add(m.group(0));
        }
        long result = validStrings.stream()
                .mapToLong(this::mul)
                .reduce(0, Long::sum);
        log.info("And the answer for puzzle 2 is ... {}", result);
    }

    private String removeDont(String line) {
        String pattern = "don't\\(\\).*?do\\(\\)";
        return line.replaceAll(pattern, "");
    }


    private long mul(String line) {
        String[] parts = line.substring(4, line.length()-1).split(",");
        return Long.parseLong(parts[0]) * Long.parseLong(parts[1]);
    }
}
