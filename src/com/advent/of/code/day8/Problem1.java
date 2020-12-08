package com.advent.of.code.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Problem1 {

    public List<Instrn> readInput(String file) throws IOException {
        List<Instrn> instrnList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            instrnList.add(new Instrn(line, false));
            line = reader.readLine();
        }
        reader.close();
        return instrnList;
    }

    public int getGlobalAccValue(List<Instrn> instrnList) {
        int acc = 0;
        int i =0;
        List<Integer> visited = new ArrayList<>();
        while(i < 626 && !instrnList.get(i).isVisited()) {
            switch(instrnList.get(i).getInstruction().substring(0,3)) {
                case "nop":
                    instrnList.get(i).setVisited(true);
                    visited.add(i);
                    i++;
                    break;
                case "acc":
                    instrnList.get(i).setVisited(true);
                    String sign = instrnList.get(i).getInstruction().substring(4,5);
                    int value = Integer.parseInt(instrnList.get(i).getInstruction().substring(4));
                    acc = performOp(sign, value, acc);
                    visited.add(i);
                    i++;
                    break;
                case "jmp":
                    instrnList.get(i).setVisited(true);
                    String signJmp = instrnList.get(i).getInstruction().substring(4,5);
                    int valueJmp = Integer.parseInt(instrnList.get(i).getInstruction().substring(4));
                    visited.add(i);
                    i = performOp(signJmp, valueJmp, i);
                    break;
            }
        }
        for(Integer index: visited) {
            System.out.print(index+1 + ",");
        }
        System.out.println();
        System.out.println("Size: " + visited.size());
        System.out.println("i: " + i);
        return acc;
    }

    public int performOp(String sign, int value, int acc) {
        acc += value;
        return acc;
    }

}
