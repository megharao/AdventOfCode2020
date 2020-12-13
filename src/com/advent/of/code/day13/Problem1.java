package com.advent.of.code.day13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem1 {

    public void readInput(String file) throws IOException {
        List<Integer> busIds = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
//        long timestamp = Long.parseLong(line);
//        line = reader.readLine();
//        printTheFirstBus(line, timestamp);
        printTheTimeStampforSeq(line);
    }

    private void printTheTimeStampforSeq(String line) {
        List<String> busIdStrings = Arrays.asList(line.split(","));
        List<Integer> busIds = busIdStrings.stream().filter(s -> !s.equals("x"))
                .map(s->Integer.parseInt(s)).collect(Collectors.toList());
        List<Integer> busIdOffsets = new ArrayList<>(busIds.size());
        for (int i = 0; i < busIdStrings.size(); i++)
        {
            if (!busIdStrings.get(i).equals("x"))
            {
                busIdOffsets.add(i % busIds.get(busIdOffsets.size()));
            }
        }
        long time = 0;
        long advanceBy = busIds.get(0);
        int correctBusIndex = 0;
        while (true)
        {
            boolean found = true;
            for (int i = correctBusIndex + 1; i < busIds.size(); i++)
            {
                int busId = busIds.get(i);
                if (timeLeft(busId, time) == busIdOffsets.get(i))
                {
                    advanceBy *= busId;
                    correctBusIndex = i;
                }
                else
                {
                    found = false;
                    break;
                }
            }
            if (found)
            {
                break;
            }
            time += advanceBy;
        }

        long answer = time;
        System.out.println(answer);
    }

    private void printTheFirstBus(String line, long timestamp) {
        List<Integer> busIds;
        busIds = Arrays.stream(line.split(",")).filter(str -> !str.equals("x"))
                .map(str -> Integer.parseInt(str)).collect(Collectors.toList());
        List<Integer> busTimeLeft = busIds.stream().map(id -> timeLeft(id, timestamp))
                .collect(Collectors.toList());
        int minWaitTime = busTimeLeft.get(0);
        int minWaitTimeId = busIds.get(0);
        for (int i = 1; i < busIds.size(); i++)
        {
            if (busTimeLeft.get(i) < minWaitTime)
            {
                minWaitTime = busTimeLeft.get(i);
                minWaitTimeId = busIds.get(i);
            }
        }
        long answer = minWaitTime * minWaitTimeId;
        System.out.println(answer);
    }


    static int timeLeft(int busId, long time)
    {
        int timeLeft = (int)(time % busId);
        if (timeLeft > 0)
        {
            timeLeft = busId - timeLeft;
        }
        return timeLeft;
    }
}
