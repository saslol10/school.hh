package org.lol.Task1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n, m, s;
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] array_line = line.split(" ");
        n = Integer.parseInt(array_line[0]);
        m = Integer.parseInt(array_line[1]);
        s = Integer.parseInt(array_line[2]);
        int[] a = new int[n];
        int[] b = new int[m];
        for (int i = 0; i < Math.max(n, m); i++) {
            line = in.nextLine();
            array_line = line.split(" ");
            if (!array_line[0].equals("-")) {
                a[i] = Integer.parseInt(array_line[0]);
            }
            if (!array_line[1].equals("-")) {
                b[i] = Integer.parseInt(array_line[1]);
            }
        }
        int sum_a = 0;
        int sum_b;
        int c_a, c_b;
        int result = 0;
        for (int i = 0; i < n; i++) {
            sum_a += a[i];
            sum_b = 0;
            c_a = i + 1;
            for (int j = 0; j < m; j++) {
                sum_b += b[j];
                c_b = j + 1;
                if (s - sum_a >= 0) {
                    if (result < c_a){
                        result=c_a;
                    }
                }
                if (s - sum_b >= 0) {
                    if (result < c_b){
                        result=c_b;
                    }
                    if (s - sum_b - sum_a >= 0) {
                        if (result < c_a + c_b) {
                            result = c_a + c_b;
                        }
                    }

                }

            }

        }
        System.out.println(result);
    }
}