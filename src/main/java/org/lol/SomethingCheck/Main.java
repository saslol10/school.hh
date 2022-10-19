package org.lol.SomethingCheck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<String> coordsOfPoint = new ArrayList<>();
    public static void main(String[] args) {
        //Input
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] array_line = line.split(" ");
        //n, m
        int n, m;
        n = Integer.parseInt(array_line[0]);
        m = Integer.parseInt(array_line[1]);
        int[][] a = new int[m][n];
        //a[i][j]
        for (int i = 0; i < m; i++) {
            line = in.nextLine();
            array_line = line.split(" ");
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(array_line[j]);
            }
        }

        int s = 0;
        int result = 0;
        int countOf1 = 0;

        //x - rows - m
        //y - columns - n
        int top_l_x = 0, top_l_y = 0;
        int btm_r_x = 0, btm_r_y = 0;

        int i = 0;
        int j = 0;

        double eff = 0;

        while(i < m)
        {
            j = 0;
            while(j < n){
                if(a[i][j] == 1)
                    if(!coordsOfPoint.contains(i+ "."+j)){
                        coordsOfPoint.add(i+"."+j);
                        top_l_x = i;
                        top_l_y = j;
                        btm_r_x = i;
                        btm_r_y = j;
                        int[] coords = {top_l_x, top_l_y, btm_r_x, btm_r_y};
                        coords = ifOne(i, j, coords, m , n, a);
                        top_l_x = coords[0];
                        top_l_y = coords[1];
                        btm_r_x = coords[2];
                        btm_r_y = coords[3];

                        //Подсчет единиц
                        for (int k_i = top_l_x; k_i < (btm_r_x + 1); k_i++) {
                            for (int k_j = top_l_y; k_j < (btm_r_y + 1); k_j++) {
                                if (a[k_i][k_j] == 1) {
                                    countOf1++;
                                }
                            }
                        }
                        //Подсчет площади
                        s = ((top_l_x) - (btm_r_x + 1)) * ((top_l_y) - (btm_r_y + 1));

                        //Выборка лучшего региона
                        if(eff < (double)countOf1/(double)s && countOf1 > 1) {
                            eff = (double) countOf1 / (double) s;
                            result = s;
                        }
                        if(eff == (double)countOf1/(double)s && countOf1 > 1){
                            if(s > result){
                                result = s;
                            }
                        }

                        countOf1 = 0;

                    }
                j++;
            }
            i++;
        }
        System.out.println(result);
    }
    public static int[] ifOne(int i, int j, int[] coords, int m, int n, int[][] a){
        List<int[]> arrStack = new ArrayList<>();
        arrStack.add(new int[]{i, j});
        int s_i;
        int s_j;
        int e_i;
        int e_j;
        for (int l = 0; l < arrStack.size(); l++){
            int[] t = arrStack.get(l);
            s_i = Math.max(t[0] - 1, 0);
            e_i = Math.min(t[0] + 2, m);
            //поиск соседей и соседей соседей (<= 8 соседей)
            for(;s_i < e_i; s_i++){
                s_j = Math.max(t[1] - 1, 0);
                e_j = Math.min(t[1] + 2, n);
                for(;s_j< e_j; s_j++){
                    if(a[s_i][s_j] == 1){
                        if(!coordsOfPoint.contains(s_i+ "."+s_j)) {
                            coordsOfPoint.add(s_i + "." + s_j);
                            if (coords[0] > s_i) coords[0] = s_i;
                            if (coords[1] > s_j) coords[1] = s_j;
                            if (coords[2] < s_i) coords[2] = s_i;
                            if (coords[3] < s_j) coords[3] = s_j;
                            arrStack.add(new int[] {s_i, s_j});
                        }
                    }
                }
            }

        }

        return coords;
    }
}

