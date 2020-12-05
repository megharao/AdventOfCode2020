package com.advent.of.code.day5;

import java.lang.reflect.Array;
import java.util.*;

public class Problem2 {

    public int getMissingSeatId(List<String> input) {
        Map<Integer, List<Integer>> seats = new HashMap<>();
        for (String str : input) {
            double seatId = 0;
            double low = 0;
            double high = 127;
            double row = 0;
            double col = 0;
            double left = 0;
            double right = 7;
            for (int i = 0; i < 7; i++) {
                if (str.charAt(i) == 'F') {
                    if (i == 6) {
                        row = low;
                        break;
                    }
                    high = Math.floor((low + high) / 2);
                } else if (str.charAt(i) == 'B') {
                    if (i == 6) {
                        row = high;
                        break;
                    }
                    low = Math.ceil((low + high) / 2);
                }
            }
            for (int i = 7; i < 10; i++) {
                if (str.charAt(i) == 'R') {
                    if (i == 9) {
                        col = right;
                        break;
                    }
                    left = Math.ceil((left + right) / 2);
                } else if (str.charAt(i) == 'L') {
                    if (i == 9) {
                        col = left;
                        break;
                    }
                    right = Math.floor((left + right) / 2);
                }
            }
            if (seats.containsKey(((int) row))) {
                List<Integer> cols = seats.get(((int)row));
                cols.add(((int) col));
            } else {
                List<Integer> list = new ArrayList<>();
                list.add((((int) col)));
                seats.put(((int) row), list);
            }
        }
        List<Integer> rows = new ArrayList<>(seats.keySet());
        Collections.sort(rows);
//        for (Integer key : rows) {
//            System.out.print("Row:" + key);
//            List<Integer> cols = seats.get(key);
//            Collections.sort(cols);
//            for (Integer val : cols) {
//                System.out.print(" " + val);
//            }
//            System.out.println();
//        }
        int missingRow =0, missingCol = 0;
        for(Integer key : rows) {
            for(int i = 0; i < 8; i++) {
                if(!seats.get(key).contains(i)) {
                    missingRow = key;
                    missingCol = i;
                    return (missingRow * 8) + missingCol;
                }
            }
        }
        return 0;
    }
}
