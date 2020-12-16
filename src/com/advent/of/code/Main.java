package com.advent.of.code;

import com.advent.of.code.day1.Day1;
import com.advent.of.code.day12.NavigateFerry;
import com.advent.of.code.day14.Masking;
import com.advent.of.code.day15.RambunctiousRecitation;
import com.advent.of.code.day16.TicketTranslation;
import com.advent.of.code.day2.Day2;
import com.advent.of.code.day2.PasswordPolicy;
import com.advent.of.code.day3.Problem1;
import com.advent.of.code.day5.Day5;
import com.advent.of.code.day5.Problem2;
import com.advent.of.code.day8.Instrn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
//        callDay1();
//        callDay2();
//        callDay3();
//        callDay4();
//        callDay5();
//        callDay6();
//        callDay7();
//        callDay8();
//        callDay9();
//        callDay10();
//        callDay11();
//        callDay12();
//        callDay13();
//        callDay14();
//        callDay15();
        TicketTranslation.problem1("resources/Day16Input.txt");
    }

    private static void callDay15() {
        RambunctiousRecitation ram = new RambunctiousRecitation();
        List<Integer> input = new ArrayList<>();
        input.add(8);
        input.add(11);
        input.add(0);
        input.add(19);
        input.add(1);
        input.add(2);
        ram.printNum(input,30000000);
    }

    private static void callDay14() {
        System.out.println(Masking.runA(Masking.getInput()));
        System.out.println(Masking.runB(Masking.getInput()));
    }

    private static void callDay13() throws IOException {
        com.advent.of.code.day13.Problem1 prob1 = new com.advent.of.code.day13.Problem1();
        prob1.readInput("resources/Day13Input.txt");
    }

    private static void callDay12() throws IOException {
        NavigateFerry navigateFerry = new NavigateFerry();
        navigateFerry.calculateWaypointDist(navigateFerry.readInput("resources/Day12Input.txt"));
    }

    private static void callDay11() {
        //        while(true) {
//            if(!com.advent.of.code.day11.Problem1.isUpdated()) {
//                break;
//            }
//        }
        while(true) {
            if(!com.advent.of.code.day11.Problem1.isUpdated2()) {
                break;
            }
        }
        com.advent.of.code.day11.Problem1.print();
    }

    private static void callDay10() throws IOException {
        com.advent.of.code.day10.Problem1 problem1 = new com.advent.of.code.day10.Problem1();
        problem1.getJoltedDiff("resources/Day10Problem1Input.txt");
    }

    private static void callDay9() throws IOException {
        com.advent.of.code.day9.Problem1 prob1 = new com.advent.of.code.day9.Problem1();
        List<Long> input = prob1.readInput("resources/Day9Problem1Input.txt");
        Long num = prob1.getNum(input);
        System.out.println(num);
        System.out.println(prob1.getSubArraySum(input, num));
    }

    private static void callDay8() throws IOException {
        com.advent.of.code.day8.Problem1 problem1 = new com.advent.of.code.day8.Problem1();
        List<Instrn> instrnList = problem1.readInput("resources/Day8Problem1Input.txt");
        System.out.println(problem1.getGlobalAccValue(instrnList));
    }

    private static void callDay7() throws IOException {
//        com.advent.of.code.day7.Problem1 prob1 = new com.advent.of.code.day7.Problem1();
//        Map<String, List<String>> bagMap = prob1.readInput("resources/Day7Problem1Input.txt");
//        bagMap.entrySet().stream().forEach(System.out::println);
//        System.out.println(prob1.getNumOfBags(bagMap, "shiny gold"));
        com.advent.of.code.day7.Problem2 prob2 = new com.advent.of.code.day7.Problem2();
        Map<String, List<String>> bagMap = prob2.readInput("resources/Day7Problem1Input.txt");
        bagMap.entrySet().stream().forEach(System.out::println);
        System.out.println(prob2.countBags(bagMap, "shiny gold"));
    }

    private static void callDay6() throws IOException {
        com.advent.of.code.day6.Problem1 prob1 = new com.advent.of.code.day6.Problem1();
        System.out.println(prob1.getTotalCountGroupAns("resources/Day6Problem1Input.txt"));
    }

    private static void callDay5() throws IOException {
        Day5 day5 = new Day5();
//        com.advent.of.code.day5.Problem1 prob1 = new com.advent.of.code.day5.Problem1();
//        System.out.println(prob1.getHighestSeatId(day5.readInput("resources/Day5Problem1Input.txt")));
        Problem2 problem2 = new Problem2();
        System.out.print(problem2.getMissingSeatId(day5.readInput("resources/Day5Problem1Input.txt")));
    }

    private static void callDay4() throws IOException {
        com.advent.of.code.day4.Problem1 prob1 = new com.advent.of.code.day4.Problem1();
        System.out.println(prob1.findValidPassport("resources/Day4Problem1Input.txt"));
    }

    private static void callDay3() throws IOException {
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
