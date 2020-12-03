package com.advent.of.code.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;

public class Problem1 {

    public int findNumOfTrees(String mapData) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(mapData));
        String line = reader.readLine();
        int countTrees = 0;
        int index = 0;
        //Right 1, down 2 sample
        while(line != null) {
            if(line.charAt(index) == '#') {
                countTrees++;
            }
            index = (index + 1) % line.length();
            line = reader.readLine();
            line = reader.readLine();
        }
        reader.close();
        return countTrees;
    }
}
