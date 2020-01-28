package com.test;

public class LongestSequesnce {

    public static void main(String[] args) {
        System.out.println(findLongestString("abcdeaftryaaart"));
    }

    static String findLongestString(String in) {
        String out = "";
        for (int i = 0; i < in.length(); i++) {
            for (int j = i + 1; j < in.length(); j++) {
                String fr = in.substring(i, j);
                if (isGood(fr) && fr.length() > out.length()) {
                    out = fr;
                }
            }
        }
        return out;
    }

    static Boolean isGood(String in) {
        for (int i = 0; i < in.length(); i++) {
            Character cur = in.charAt(i);
            for (int j = i + 1; j < in.length(); j++) {
                if (cur == in.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
