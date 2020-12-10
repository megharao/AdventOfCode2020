package com.advent.of.code.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Problem1 {

    public void getJoltedDiff(String file) throws IOException {
        List<Integer> input = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            input.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        reader.close();

        int diff1 = 0;
        int diff3 = 0;
        input.sort(Comparator.comparing(Integer::valueOf));
        int prev = 0;
        for(int i = 0; i < input.size(); i++ ) {
            Integer curr = input.get(i);
            if(curr - prev == 1) {
                diff1++;
            } else if(curr - prev == 3) {
                diff3++;
            }
            prev = curr;
        }
        diff3++;
        System.out.println(diff1);
        System.out.println(diff3);

        input.add(0);
        input.add(input.stream().max(Comparator.comparing(Integer::valueOf)).get() + 3);
        input.sort(Comparator.comparing(Integer::valueOf));
        int[] array = input.stream().mapToInt(i->i).toArray();
        getPathCount(array);
    }

    private void getPathCount(int[] x) {
        long[] dp = new long[x.length];
        dp[0] = 1;
        for(int i = 1; i <= x.length-1; i++) {
            dp[i] = 0;
            int j = i-1;
            while(j >= 0 && x[i] - x[j] <= 3) {
                dp[i] += dp[j];
                --j;
            }
        }
        System.out.println(dp[dp.length-1]);
    }
}
