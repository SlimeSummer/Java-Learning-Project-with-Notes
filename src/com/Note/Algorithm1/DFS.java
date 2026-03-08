/*
题目描述：
给出一个roe×col的大写字母矩阵，一开始的位置为左上角，你可以向上下左右四个方向移动，并且不能移向曾经经过的字母。问最多可以经过几个字母。

输入格式：
第一行，输入字母矩阵行数R和列数S，1≤R,S≤20。
接着输出RR行SS列字母矩阵。

输出格式：
最多能走过的不同字母的个数。

样例输入：
3 6
HFDFFB
AJHGDH
DGAGEH

样例输出：
6
 */
package com.Note.Algorithm1;

import java.util.Scanner;

public class DFS {
    static char[][] charMatrix;
    static boolean[] charFlag=new boolean[26];
    static int[][] move=new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    static int maxCount=0;

    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int row= scanner.nextInt();
        int column= scanner.nextInt();
        charMatrix=new char[row][column];
        for (int i=0;i<row;i++){
            String line= scanner.next();
            for (int j=0;j<column;j++){
                charMatrix[i][j]=line.charAt(j);
            }
        }

        int result=startMove();
        System.out.println(result);
    }

    private static int startMove(){
        int flagIndex=charMatrix[0][0]-'A';
        charFlag[flagIndex]=true;
        dfs(0,0,1);
        return maxCount;
    }

    private static void dfs(int row, int column, int count){
        if(count>maxCount){
            maxCount=count;
        }

        for (int i=0;i<4;i++){
            int nrow=row+move[i][0];
            int ncol=column+move[i][1];

            if (nrow>=0 && nrow<charMatrix.length && ncol>=0 && ncol<charMatrix[0].length){
                int flagIndex=charMatrix[nrow][ncol]-'A';

                if (!charFlag[flagIndex]){
                    charFlag[flagIndex]=true;
                    dfs(nrow,ncol,count+1);
                    charFlag[flagIndex]=false;  //回溯时将状态恢复
                }
            }
        }
    }
}
