package com.advent.of.code.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 {

    public List<String> readInput(String file) throws IOException {
        List<String> input = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            input.add(line);
            line = reader.readLine();
        }
        reader.close();
        return input;
    }
}
