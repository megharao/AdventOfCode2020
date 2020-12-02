package com.advent.of.code.day2;

import java.util.List;

public class Problem1 {

    public long getCountOfValidPasswordsOld(List<PasswordPolicy> input) {
        return input.stream().filter(PasswordPolicy::isValidOld).count();
    }
}
