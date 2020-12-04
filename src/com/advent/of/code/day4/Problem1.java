package com.advent.of.code.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Problem1 {

    public int findValidPassport(String passPortData) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(passPortData));
        String line = reader.readLine();
        int validPassports = 0;
        String nextLine;
        while(line != null) {
            while((nextLine = reader.readLine()) != null) {
                if(nextLine.length() > 0) {
                    line = line.concat(" ").concat(nextLine);
                } else {
                    break;
                }
            }
            Passport passport = getPassport(line);
            if(passport.isValid()) {
                validPassports++;
            }
            line = reader.readLine();
        }
        reader.close();
        return validPassports;
    }

    public Passport getPassport(String line) {
        String[] fields = line.split(" ");
        Passport passport = new Passport();
        for(String field: fields) {
            String[] data = field.split(":");
            switch (data[0]) {
                case "byr":
                    passport.setByr(Integer.parseInt(data[1]));
                    break;
                case "iyr":
                    passport.setIyr(Integer.parseInt(data[1]));
                    break;
                case "eyr":
                    passport.setEyr(Integer.parseInt(data[1]));
                    break;
                case "hgt":
                    passport.setHgt(data[1]);
                    break;
                case "hcl":
                    passport.setHcl(data[1]);
                    break;
                case "ecl":
                    passport.setEcl(data[1]);
                    break;
                case "pid":
                    passport.setPid(data[1]);
                    break;
                case "cid":
                    passport.setCid(Integer.parseInt(data[1]));
                    break;
            }
        }
        return passport;
    }

}
