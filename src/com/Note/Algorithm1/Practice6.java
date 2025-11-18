package com.Note.Algorithm1;

import java.util.Scanner;

public class Practice6 {
    public static void main(String args[]) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt();
        int r= scanner.nextInt();
        printCombination(n,r);
    }

    private static void printCombination(int n, int r){
        int round=0;
        int[] combArray=new int[r];
        printCombinationHelper(n,r,0,1,combArray);
    }

    private static void printCombinationHelper(int n, int r,int round, int start,int[] combArray){
        if (round==r){
            for (int i=0;i<r;i++) {
                System.out.printf("%3s",combArray[i]);
            }
            System.out.println();
            return;
        }

        for (int i=start;i<=n;i++){
            combArray[round]=i;
            printCombinationHelper(n,r,round+1,i+1,combArray);
        }
    }
}
