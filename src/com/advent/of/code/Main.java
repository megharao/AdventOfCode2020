package com.advent.of.code;

import com.advent.of.code.day1.Problem1;
import com.advent.of.code.day1.Problem2;
import com.advent.of.code.day2.PasswordPolicy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
//        callDay1();
        List<PasswordPolicy> input = readPwdPolicyInput("resources/Day2Problem1Input.txt");
//        com.advent.of.code.day2.Problem1 prob1 = new com.advent.of.code.day2.Problem1();
//        System.out.println(prob1.getCountOfValidPasswordsOld(input));
        com.advent.of.code.day2.Problem2 prob2 = new com.advent.of.code.day2.Problem2();
        System.out.print(prob2.getCountOfValidPasswords(input));
    }

    private static void callDay1() throws IOException {
        List<Integer> input = readInput("resources/Day1Problem1Input.txt");
        Problem1 prob1 = new Problem1();
        List<Integer> res1 = prob1.findTwoIntSumToTarget(input, 2020);
        System.out.print(getProduct(res1));
        Problem2 prob2 = new Problem2();
        List<Integer> res2 = prob2.findThreeIntSumToTarget(input, 2020);
        System.out.print(getProduct(res2));
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

    public static List<PasswordPolicy> readPwdPolicyInput(String file) throws IOException {
        List<PasswordPolicy> input = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            String[] lineData = line.split(" ");
            String[] minMaxOcc = lineData[0].split("-");
            PasswordPolicy p = new PasswordPolicy(Integer.parseInt(minMaxOcc[0]), Integer.parseInt(minMaxOcc[1]), lineData[1].substring(0,1), lineData[2]);
            input.add(p);
            line = reader.readLine();
        }
        reader.close();
        return input;
    }
}
