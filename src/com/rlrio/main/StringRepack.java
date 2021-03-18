package com.rlrio.main;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringRepack {

    public static void main(String[] args) {
        try {
            System.out.println(repack(args[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("String is empty.");
        }
    }

    public static String repack(String s) {
        try {
            if (isValid(s)) {
                return match(s);
            } else {
                throw new StringRepackException("String is not valid.");
            }
        } catch (StringRepackException e) {
            System.out.println(e.getMessage());
        }
        return match(s);
    }

    private static String match(String s) {
        Pattern p = Pattern.compile("(\\d+)\\[+(\\w*)\\]+");
        Matcher matcher = p.matcher(s);
        while (matcher.find()) {
            String replace = multi(matcher.group(1) + matcher.group(2));
            s = s.replace(matcher.group(), replace);
            match(s);
        }
        s = s.replaceAll("\\[", "").replaceAll("\\]", "").trim();
        return multi(s);
    }

    private static String multi(String s) {
        if (s.matches("\\d+\\w+")) {
            Pattern p = Pattern.compile("(\\d+)(\\w+)");
            Matcher matcher = p.matcher(s);
            if (matcher.find()) {
                String replace = matcher.group(2).repeat(Integer.parseInt(matcher.group(1)));
                s = s.replace(matcher.group(), replace);
            }
        }
        return s;
    }

    private static boolean isValid(String s) {
        return s.matches("\\d+\\[[0-9a-zA-Z|\\[|\\]]+\\][a-zA-Z]*") && isBalanced(s);
    }

    private static boolean isBalanced(String s) {
        return s.chars().mapToDouble(i -> i == '[' ? 1 : i == ']' ? -1 : 0).reduce(0,
                (a, b) -> a == 0 && b == -1 ? Double.NaN : a + b) == 0;
    }

}