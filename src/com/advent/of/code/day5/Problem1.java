package com.advent.of.code.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Problem1 {

    public double getHighestSeatId(List<String> input) {
        double highestSeatId = 0;
        for(String str : input) {
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
                    high =  Math.floor((low + high) / 2);
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
//            System.out.println("Row:" + row);
//            System.out.println("Col:" + col);
            seatId = (row * 8) + col;
            if (seatId > highestSeatId) {
                highestSeatId = seatId;
            }
        }
        return highestSeatId;
    }

}
