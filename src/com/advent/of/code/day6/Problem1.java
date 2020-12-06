package com.advent.of.code.day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem1 {

    public int getTotalCountGroupAns(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        String nextLine;
        int count = 0;
        int groupCount = 1;
        while(line != null) {
            while((nextLine = reader.readLine()) != null) {
                if(nextLine.length() > 0) {
                    line = line.concat(nextLine);
                    groupCount++;
                } else {
//                    count += getCountOfUniqueAns(line);
                    count += getCountOfCommonAns(line,groupCount);
                    line = "";
                    groupCount = 0;
                }
            }
//            count += getCountOfUniqueAns(line);
            count += getCountOfCommonAns(line,groupCount);
            line = reader.readLine();
        }
        reader.close();
        return count;
    }

    public int getCountOfUniqueAns(String groupData) {
        Set<String> ans = new HashSet<>();
        for(char c: groupData.toCharArray()){
            ans.add(String.valueOf(c));
        }
        return ans.size();
    }

    public int getCountOfCommonAns(String groupData, int groupCount) {
        Map<String, Integer> ans = new HashMap<>();
        for(char c: groupData.toCharArray()){
            if(ans.containsKey(String.valueOf(c))) {
                int count = ans.get(String.valueOf(c));
                ans.put(String.valueOf(c), ++count);
            } else {
                ans.put(String.valueOf(c), 1);
            }
        }
        return (int) ans.entrySet().stream().filter(entry -> entry.getValue().equals(groupCount)).count();
    }
}
