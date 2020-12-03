package com.advent.of.code.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    public static List<Integer> readInput(String file) throws IOException {
        List<Integer> input = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            input.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        reader.close();
        return input;
    }

    public static int getProduct(List<Integer> res) {
        return res.stream().reduce(1, (a,b) -> a*b);
    }
}
