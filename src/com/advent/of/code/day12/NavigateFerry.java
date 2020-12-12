package com.advent.of.code.day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NavigateFerry {
    int eastWest=0, northSouth=0;
    public List<String> readInput(String file) throws IOException {
        List<String> input = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null) {
            input.add(line);
            line = reader.readLine();
        }
        return input;
    }

    public void calculateWaypointDist(List<String> input) {
        eastWest = 10;
        northSouth = 1;
        int currEastWest = 0;
        int currNorthSouth = 0;
        char prevDirection = 'E';
        for(String str: input) {
            char direction = str.charAt(0);
            int steps = Integer.parseInt(str.substring(1));
            switch(direction) {
                case 'F':
                    if(prevDirection == 'E') {
                        currEastWest += steps * eastWest;
                        currNorthSouth += steps * northSouth;
                    }
                    else if(prevDirection == 'N') {
                        currNorthSouth += steps * northSouth;
                        currEastWest += steps * eastWest;
                    }
                    else if(prevDirection == 'W') {
                        currEastWest -= steps * eastWest;
                        currNorthSouth -= steps * eastWest;
                    }
                    else {
                        currNorthSouth -= steps * eastWest;
                        currEastWest -= steps * eastWest;
                    }
                    break;
                case 'N':
                    northSouth += steps;
                    break;
                case 'S':
                    northSouth -= steps;
                    break;
                case 'E':
                    eastWest += steps;
                    break;
                case 'W':
                    eastWest -= steps;
                    break;
                case 'L':
                    while(steps != 0) {
                        int temp = northSouth;
                        northSouth = eastWest;
                        eastWest = -temp;
                        steps = steps-90;
//                        prevDirection = 'S';
                    }
                    break;
                case 'R':
                    while(steps != 0) {
                        int temp = northSouth;
                        northSouth = -eastWest;
                        eastWest = temp;
                        steps = steps-90;
//                        prevDirection = 'S';
                    }
                    break;
            }
        }
        System.out.println("Manhattan distance: " + currEastWest + " " + currNorthSouth);
    }

    public void calculateManhattanDist(List<String> input) {
        char prevDirection = 'E';
        for(String str: input) {
            char direction = str.charAt(0);
            int steps = Integer.parseInt(str.substring(1));
            switch(direction) {
                case 'F':
                    updateDist(prevDirection, steps);
                    break;
                case 'N':
                    northSouth += steps;
                    break;
                case 'S':
                    northSouth -= steps;
                    break;
                case 'E':
                    eastWest += steps;
                    break;
                case 'W':
                    eastWest -= steps;
                    break;
                case 'L':
                    while(steps != 0) {
                        if (prevDirection == 'E') {
                            prevDirection = 'N';
                        } else if (prevDirection == 'W') {
                            prevDirection = 'S';
                        } else if (prevDirection == 'N') {
                            prevDirection = 'W';
                        } else {
                            prevDirection = 'E';
                        }
                        steps = steps - 90;
                    }
                    break;
                case 'R':
                    while(steps != 0) {
                        if (prevDirection == 'E') {
                            prevDirection = 'S';
                        } else if (prevDirection == 'W') {
                            prevDirection = 'N';
                        } else if (prevDirection == 'N') {
                            prevDirection = 'E';
                        } else {
                            prevDirection = 'W';
                        }
                        steps = steps - 90;
                    }
                    break;
            }
        };
        System.out.println("Manhattan distance: " + eastWest + " " + northSouth);
    }

    public void updateDist(char prevDirection, int steps){
        if(prevDirection == 'E') {
            eastWest += steps;
        } else if(prevDirection == 'W') {
            eastWest -= steps;
        } else if(prevDirection == 'N') {
            northSouth += steps;
        } else {
            northSouth -= steps;
        }
    }
}
