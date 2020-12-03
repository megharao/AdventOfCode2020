package com.advent.of.code.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
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
