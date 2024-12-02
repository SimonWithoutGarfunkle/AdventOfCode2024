package org.simonwithoutgarfunkle;

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
}
