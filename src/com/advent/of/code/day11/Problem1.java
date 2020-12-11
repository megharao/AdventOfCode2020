package com.advent.of.code.day11;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Problem1 {
    private static Map<Point, Boolean> seatMap = populateInput();
    private static int xLen;
    private static int yLen;

    public static Map<Point, Boolean> populateInput() {
        Map<Point, Boolean> data = new HashMap<>();
        List<String> input = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("resources/Day11Input.txt"));
            String line = reader.readLine();
            while(line != null) {
                input.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        xLen = input.get(0).length() - 1;
        yLen = input.size() - 1;

        for(int y = 0; y < input.size(); y++) {
            for(int x = 0; x < input.get(y).toCharArray().length; x++) {
                if(input.get(y).charAt(x) == 'L') {
                    data.put(new Point(x, y), false);
                }
            }
        }
        return data;
    }

    public static boolean isUpdated() {
        boolean updated = false;
        Map<Point, Boolean> updatedMap = new HashMap<>();
        for(Map.Entry<Point, Boolean> seat : seatMap.entrySet()) {
            if(!seat.getValue() && countOccupiedSeats(seat.getKey()) == 0) {
                updatedMap.put(seat.getKey(), true);
                updated = true;
            } else if (seat.getValue() && countOccupiedSeats(seat.getKey()) >= 4) {
                updatedMap.put(seat.getKey(), false);
                updated = true;
            } else {
                updatedMap.put(seat.getKey(), seat.getValue());
            }
        }
        seatMap = updatedMap;
        return updated;
    }

    private static int countOccupiedSeats(Point seat) {
        return (int) Arrays.stream(Direction.values()).map(
                d -> new Point(seat.x + d.offsetX, seat.y + d.offsetY))
                .filter(occupiedSeat -> seatMap.containsKey(occupiedSeat) && seatMap.get(occupiedSeat))
                .limit(4).count();
    }

    public static void print() {
        System.out.println("Part 1: occupied seats: " + seatMap.keySet().stream().filter(occupied -> seatMap.get(occupied)).count());
    }

    public static boolean isUpdated2() {
        boolean changed = false;
        Map<Point, Boolean> newSeatMap = new HashMap<>();
        for (Map.Entry<Point, Boolean> seat : seatMap.entrySet()) {
            if (!seat.getValue()) {
                if (countVisibleOccupiedSeats(seat) == 0) {
                    newSeatMap.put(seat.getKey(), true);
                    changed = true;
                } else {
                    newSeatMap.put(seat.getKey(), seat.getValue());
                }
            } else if (countVisibleOccupiedSeats(seat) >= 5) {
                newSeatMap.put(seat.getKey(), false);
                changed = true;
            } else {
                newSeatMap.put(seat.getKey(), seat.getValue());
            }
        }
        seatMap = newSeatMap;
        return changed;
    }

    private static int countVisibleOccupiedSeats(Map.Entry<Point, Boolean> seat) {
        return (int) Arrays.stream(Direction.values())
                .filter(d -> occupiedSeatVisible(new Point(seat.getKey()), d))
                .limit(5).count();
    }

    private static boolean occupiedSeatVisible(Point p, Direction d) {
        do {
            p.move(p.x + d.offsetX, p.y + d.offsetY);
            if (seatMap.containsKey(p)) {
                return seatMap.get(p);
            }
        } while (p.x >= 0 && p.y >= 0 && p.x <= xLen && p.y <= yLen);
        return false;
    }

    enum Direction {
        N(0, -1),
        NE(1, -1),
        E(1, 0),
        SE(1, 1),
        S(0, 1),
        SW(-1, 1),
        W(-1, 0),
        NW(-1, -1);

        public final int offsetX;
        public final int offsetY;

        Direction(int offsetX, int offsetY) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }
}
