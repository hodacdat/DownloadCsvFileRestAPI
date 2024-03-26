package com.example.demo;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //bt1
        try {
            String result1 = BT1();
            System.out.println(result1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //bt2
        try {
            String result2 = BT2();
            System.out.println(result2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //bt3
        try {
            int result3 = BT3();
            System.out.println(result3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String BT1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input W, K, D number");
        int W = sc.nextInt();
        int K = sc.nextInt();
        int D = sc.nextInt();
        if (W < 2 || W > 100 || K < 1 || K > W - 1 || D < 1 || D > 100) {
            return "Input false";
        }

        if (D < K || D < Math.abs(W - K)) {
            return "No";
        } else {
            return "Yes";
        }
    }

    public static String BT2() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input N");
        int N = sc.nextInt();
        if (N < 1 || N > Math.pow(10, 5)) {
            return "Input N must be in 1 - 10^5";
        }

        int[] A = new int[N];
        int[] B = new int[N];

        System.out.println("Input " + N + " temperatures for A");
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            if (A[i] < 0 || A[i] > 100) {
                return "Input number for temprature must be in 0 - 100";
            }
        }

        System.out.println("Input " + N + " temperatures for B");
        for (int i = 0; i < N; i++) {
            B[i] = sc.nextInt();
            if (B[i] < 0 || B[i] > 100) {
                return "Input number for temprature must be in 0 - 100";
            }
        }

        double averageA = calculateAverage(A);
        double averageB = calculateAverage(B);

        if (averageB > averageA) {
            return "B";
        } else if (averageB < averageA) {
            return "A";
        } else return "same";
    }

    private static double calculateAverage(int[] temperatures) {
        double sum = 0;
        for (int temperature : temperatures) {
            sum += temperature;
        }
        return sum / temperatures.length;
    }

    public static int BT3() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input number N");

        int N = sc.nextInt();
        if (N < 1 || N > 100) {
            System.out.println("Input N must be in 1 - 100");
            return -1;
        }

        Set<Integer> listResult = new HashSet<>();
        for (int a = 1; a <= N; a++) {
            for (int b = 1; b <= N; b++) {
                int result = (int) Math.pow(a, 2) * b;
                listResult.add(result);
            }
        }
        return listResult.size();
    }
}
