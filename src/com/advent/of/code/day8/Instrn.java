package com.advent.of.code.day8;

public class Instrn {
    String instruction;
    boolean visited;

    public Instrn(String instruction, boolean visited) {
        this.instruction = instruction;
        this.visited = visited;
    }

    public String getInstruction() {
        return instruction;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Instrn{" +
                "instruction='" + instruction + '\'' +
                ", visited=" + visited +
                '}';
    }
}
