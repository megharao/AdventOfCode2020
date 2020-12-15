package com.advent.of.code.day15;

import java.util.HashMap;
import java.util.List;

public class RambunctiousRecitation {

    public void printNum(List<Integer> data, int rounds) {
        HashMap<Integer, Integer> numToIndex = new HashMap<>();
        for(int i = 0; i < data.size(); i++) {
            numToIndex.put(data.get(i), i+1);
        }
        int turn = data.size() + 1;
        int nextNum = 0;
        while(turn < rounds) {
            if(numToIndex.containsKey(nextNum)) {
                int diff = turn - numToIndex.get(nextNum);
                numToIndex.put(nextNum, turn);
                nextNum = diff;
            } else {
                numToIndex.put(nextNum,turn);
                nextNum = 0;
            }
            turn++;
        }
        System.out.println(nextNum);
    }
}
