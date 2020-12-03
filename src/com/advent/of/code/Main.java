package com.advent.of.code;

import com.advent.of.code.day1.Day1;
import com.advent.of.code.day2.Day2;
import com.advent.of.code.day2.PasswordPolicy;
import com.advent.of.code.day3.Problem1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//        callDay1();
//        callDay2();
        Problem1 prob1 = new Problem1();
        System.out.println(prob1.findNumOfTrees("resources/Day3Problem1Input.txt"));
    }

    private static void callDay2() throws IOException {
        List<PasswordPolicy> input = Day2.readPwdPolicyInput("resources/Day2Problem1Input.txt");
//        com.advent.of.code.day2.Problem1 prob1 = new com.advent.of.code.day2.Problem1();
//        System.out.println(prob1.getCountOfValidPasswordsOld(input));
        com.advent.of.code.day2.Problem2 prob2 = new com.advent.of.code.day2.Problem2();
        System.out.print(prob2.getCountOfValidPasswords(input));
    }

    private static void callDay1() throws IOException {
        List<Integer> input = Day1.readInput("resources/Day1Problem1Input.txt");
        com.advent.of.code.day1.Problem1 prob1 = new com.advent.of.code.day1.Problem1();
        List<Integer> res1 = prob1.findTwoIntSumToTarget(input, 2020);
        System.out.print(Day1.getProduct(res1));
        com.advent.of.code.day1.Problem2 prob2 = new com.advent.of.code.day1.Problem2();
        List<Integer> res2 = prob2.findThreeIntSumToTarget(input, 2020);
        System.out.print(Day1.getProduct(res2));
    }
}
