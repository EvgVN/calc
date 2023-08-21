package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner reader = new Scanner(System.in);
    static String input = reader.nextLine();
    static String[] elm = input.split(" ");
    static String[] act = {"+", "-", "*", "/"};
    static String[] arb = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static String[] rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) throws Exception {
        String output = calc(input);
        System.out.println(output);
    }

    static String count(int num1, int num2, String[] elm, String[] act) {
        int result = 0;
        if (elm[1].equals(act[0])) {
            result = num1 + num2;
        } else if (elm[1].equals(act[1])) {
            result = num1 - num2;
        } else if (elm[1].equals(act[2])) {
            result = num1 * num2;
        } else if (elm[1].equals(act[3])) {
            result = num1 / num2;
        }
        return Integer.toString(result);
    }

    public static String calc(String input) throws Exception {
        String output = "";
        boolean foundAct = Arrays.asList(act).contains(elm[1]);
        if (!foundAct || elm.length != 3) {
            throw new Exception("!foundAct || elm.length != 3");
        } else {
            boolean foundArb = Arrays.asList(arb).contains(elm[0]) && Arrays.asList(arb).contains(elm[2]);
            boolean foundRim = Arrays.asList(rim).contains(elm[0]) && Arrays.asList(rim).contains(elm[2]);
            if (foundRim) {
                elm = rimToArb();
                int num1 = Integer.parseInt(elm[0]);
                int num2 = Integer.parseInt(elm[2]);
                output = count(num1, num2, elm, act);
                if(Integer.parseInt(output) <= 0){
                    throw new Exception("Rim result <= 0");
                }
                int outputRim = Integer.parseInt(output);
                return arbToRim(outputRim);
            } else if (foundArb) {
            } else {
                throw new Exception("!foundArb || !foundRim");
            }
            int num1 = Integer.parseInt(elm[0]);
            int num2 = Integer.parseInt(elm[2]);
            output = count(num1, num2, elm, act);
        }
        return output;
    }

    public static String[] rimToArb() {
        elm[0] = arb[Arrays.asList(rim).indexOf(elm[0])];
        elm[2] = arb[Arrays.asList(rim).indexOf(elm[2])];
        return elm;
    }

    public static String arbToRim(int num) {
        int[] arbLit = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rimLit = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder rimLiter = new StringBuilder();
        for (int i = 0; i < arbLit.length; i++) {
            while (num >= arbLit[i]) {
                num -= arbLit[i];
                rimLiter.append(rimLit[i]);
            }
        }
        return rimLiter.toString();
    }
}
