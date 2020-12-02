package com.advent.of.code.day2;

import java.util.List;

public class Problem2 {
    public long getCountOfValidPasswords(List<PasswordPolicy> input) {
        return input.stream().filter(PasswordPolicy::isValid).count();
    }
}
