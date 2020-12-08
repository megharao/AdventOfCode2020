package com.advent.of.code.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem2 {
    public Map<String, List<String>> readInput(String file) throws IOException {
        Map<String, List<String>> bagMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while (line != null) {
            String[] bags = line.split("bags contain");
            String[] otherBags = bags[1].split(",");
            List<String> otherBagList = new ArrayList<>();
            for (String otherBag : otherBags) {
                otherBagList.add(otherBag.replace('.', '\0').
                        replaceAll("bags", "").
                        replaceAll("bag", "").trim());
            }
            bagMap.put(bags[0].trim(), otherBagList);
            line = reader.readLine();
        }
        reader.close();
        return bagMap;
    }

    public int countBags(Map<String, List<String>> bagMap, String target) {
            int count = 0;
            List<String> bags = bagMap.get(target);
            for(String bag: bags) {
                if(bag.equals("no other")) {
                    return 0;
                }
                String num = bag.substring(0,1);
                count += Integer.parseInt(num) + Integer.parseInt(num) * countBags(bagMap, bag.substring(2));
            }
            return count;
    }
}
