package com.advent.of.code.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Problem1 {

    public List<Integer> findTwoIntSumToTarget(List<Integer> input, int target) {
        List<Integer> nums = new ArrayList<>(2);
        for(int i = 0; i < input.size(); i++) {
            for(int j = i+1; j < input.size(); j++) {
                if(input.get(i) + input.get(j) == target) {
                    nums.add(input.get(i));
                    nums.add(input.get(j));
                    return nums;
                }
            }
        }
        return nums;
    }
}
