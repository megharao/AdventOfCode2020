package com.advent.of.code.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem1 {

    public List<Long> readInput(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        List<Long> input = new ArrayList<>();
        while(line != null) {
            input.add(Long.parseLong(line));
            line = reader.readLine();
        }
        reader.close();
        return input;
    }

    public Long getNum(List<Long> input) {
        Long num = 0L;
        for(int i = 25; i < input.size(); i++) {
            List<Long> trimmedInput = input.subList(i-25, i);
            if(!checkPairExists(trimmedInput, input.get(i))) {
                num = input.get(i);
                return num;
            }
        }
        return num;
    }

    private boolean checkPairExists(List<Long> input, Long target) {
        for(int i = 0; i < input.size(); i++) {
            Long val = target - input.get(i);
            if(input.contains(val) && val != input.get(i)) {
                return true;
            }
        }
        return false;
    }

    public Long getSubArraySum(List<Long> input, Long target) {
        Long curr_sum;
        for(int i = 0; i < input.size(); i++) {
            curr_sum = input.get(i);
            for(int j = i + 1; j <= input.size(); j++) {
                if(curr_sum.equals(target)) {
                    Long min = input.subList(i,j).stream().min(Comparator.comparing(Long::valueOf)).get();
                    Long max = input.subList(i,j).stream().max(Comparator.comparing(Long::valueOf)).get();
                    return (min + max);
                }
                if(curr_sum > target || j == input.size()) {
                    break;
                }
                curr_sum = curr_sum + input.get(j);
            }
        }
        return 0L;
    }

}
