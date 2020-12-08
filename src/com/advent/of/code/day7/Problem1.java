package com.advent.of.code.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Problem1 {

    public Map<String, List<String>> readInput(String file) throws IOException {
        Map<String, List<String>> bagMap = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            String[] bags = line.split("bags contain");
            String[] otherBags = bags[1].split(",");
            List<String> otherBagList = new ArrayList<>();
            for(String otherBag: otherBags) {
                otherBagList.add(otherBag.replace('.', '\0').
                        replaceAll("bags", "").
                        replaceAll("bag", "").
                        replaceAll("[0-9]", "").trim());
            }
            bagMap.put(bags[0].trim(), otherBagList);
            line = reader.readLine();
        }
        reader.close();
        return bagMap;
    }

    public int getNumOfBags(Map<String, List<String>> bagMap, String target) {
        int count = 0;
        Queue<String> visited = new LinkedList<>();
        Set<String> visitedFlag = new HashSet<>();
        for (Map.Entry<String, List<String>> stringListEntry : bagMap.entrySet()) {
            if (stringListEntry.getValue().contains(target)) {
                if (!visited.contains(stringListEntry.getKey())) {
                    visited.add(stringListEntry.getKey());
                    visitedFlag.add(stringListEntry.getKey());
                    count++;
                }
            }
        }
        Iterator<String> iter = visited.iterator();
        while(iter.hasNext()) {
            String visit = visited.remove();
            Iterator<Map.Entry<String, List<String>>> mapIter = bagMap.entrySet().iterator();
            while(mapIter.hasNext()) {
                Map.Entry<String, List<String>> entry = mapIter.next();
                    if(entry.getValue().contains(visit)) {
                        if(!visitedFlag.contains(entry.getKey())) {
                            visited.add(entry.getKey());
                            visitedFlag.add(entry.getKey());
                            count++;
                        }
                    }
                }
        }
        return count;
    }
}
