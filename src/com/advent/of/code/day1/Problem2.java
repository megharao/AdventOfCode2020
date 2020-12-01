package com.advent.of.code.day1;

import java.util.ArrayList;
import java.util.List;

public class Problem2 {
    public List<Integer> findThreeIntSumToTarget(List<Integer> input, int target) {
        List<Integer> nums = new ArrayList<>(3);
        for(int i = 0; i < input.size(); i++) {
            for(int j = i+1; j < input.size(); j++) {
                for(int k = j+1; k < input.size(); k++) {
                    if(input.get(i) + input.get(j) + input.get(k) == target) {
                        nums.add(input.get(i));
                        nums.add(input.get(j));
                        nums.add(input.get(k));
                        return nums;
                    }
                }
            }
        }
        return nums;
    }
}
