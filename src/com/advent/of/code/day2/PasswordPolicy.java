package com.advent.of.code.day2;

public class PasswordPolicy {
    private int minOccurance;
    private int maxOccurance;
    private String letterToCheck;
    private String password;

    public PasswordPolicy(int minOccurance, int maxOccurance, String letterToCheck, String password) {
        this.minOccurance = minOccurance;
        this.maxOccurance = maxOccurance;
        this.letterToCheck = letterToCheck;
        this.password = password;
    }

    @Override
    public String toString() {
        return "PasswordPolicy{" +
                "minOccurance=" + minOccurance +
                ", maxOccurance=" + maxOccurance +
                ", letterToCheck='" + letterToCheck + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getMinOccurance() {
        return minOccurance;
    }

    public int getMaxOccurance() {
        return maxOccurance;
    }

    public String getLetterToCheck() {
        return letterToCheck;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValidOld() {
        long count = this.getPassword().chars()
                .filter(c -> c == this.getLetterToCheck().charAt(0))
                .count();
        return count >= this.getMinOccurance() && count <= this.getMaxOccurance();
    }

    public boolean isValid() {
        boolean firstOcc = this.getPassword().charAt(this.getMinOccurance()-1) == this.getLetterToCheck().charAt(0);
        boolean secOcc = this.getPassword().charAt(this.getMaxOccurance()-1) == this.getLetterToCheck().charAt(0);
        return (firstOcc && !secOcc) || (!firstOcc && secOcc);
    }

}
