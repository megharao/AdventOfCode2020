package com.advent.of.code;

import com.advent.of.code.day1.Problem2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        List<Integer> input = readInput("resources/Day1Problem1Input.txt");
//        Problem1 prob = new Problem1();
//        List<Integer> res = prob.findTwoIntSumToTarget(input, 2020);
        Problem2 prob = new Problem2();
        List<Integer> res = prob.findThreeIntSumToTarget(input, 2020);
        System.out.print(getProduct(res));
    }

    private static int getProduct(List<Integer> res) {
        return res.stream().reduce(1, (a,b) -> a*b);
    }

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
}
