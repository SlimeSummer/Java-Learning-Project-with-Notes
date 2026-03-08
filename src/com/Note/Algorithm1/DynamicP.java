package com.Note.Algorithm1;

import java.util.Scanner;

public class DynamicP {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int m=scanner.nextInt();
        int[] w=new int[n+1];
        int[] v=new int[n+1];

        for (int i=1;i<=n;i++){
            w[i]=scanner.nextInt();
            v[i]=scanner.nextInt();
        }
        dp1(n,m,w,v);
        dp2(n,m,w,v);
    }

    //方法1
    private static void dp1(int n, int m,int[] w,int[] v){
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if (j<w[i]){
                    dp[i][j]=dp[i-1][j];
                }
                else{
                    dp[i][j]=getMax(dp[i-1][j],v[i]+dp[i-1][j-w[i]]);
                }
            }
        }
        printMatrix(dp,n,m);
        System.out.println("\n"+dp[n][m]+"\n");
    }

    //方法2
    private static void dp2(int n, int m,int[] w,int[] v){
        int[][] dp=new int[m+1][n+1];
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if (i<w[j]){
                    dp[i][j]=dp[i][j-1];
                }
                else{
                    dp[i][j]=getMax(dp[i][j-1],v[j]+dp[i-w[j]][j-1]);
                }
            }
        }
        printMatrix(dp,m,n);
        System.out.println("\n"+dp[m][n]);
    }

    private static int getMax(int a,int b){
        return (a>=b)?a:b;
    }

    //打印矩阵
    private static void printMatrix(int[][] dp,int n,int m){
        for(int i=0;i<=n;i++) {
            for (int j = 0; j <= m; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }
}
