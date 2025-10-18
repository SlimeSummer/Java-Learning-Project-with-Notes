/*
题目描述：
某校大门外长度为L的马路上有一排树，每两棵相邻的树之间的间隔都是1米。我们可以把马路看成一个数轴，马路的一端在数轴0的位置，另一端在L的位置；数轴上的每个
整数点，即0，1，2，……，L，都种有一棵树。
由于马路上有一些区域要用来建地铁。这些区域用它们在数轴上的起始点和终止点表示。已知任一区域的起始点和终止点的坐标都是整数，区域之间可能有重合的部分。现在
要把这些区域中的树（包括区域端点处的两棵树）移走。你的任务是计算将这些树都移走后，马路上还有多少棵树。

输入格式：
输入的第一行有两个整数L（1 <= L <= 10000）和 M（1 <= M <= 100），L代表马路的长度，M代表区域的数目，L和M之间用一个空格隔开。接下来的M行每行包含
两个不同的整数，用一个空格隔开，表示一个区域的起始点和终止点的坐标。

输出格式：
输出包括一行，这一行只包含一个整数，表示马路上剩余的树的数目。
 */
package com.Note.Algorithm1;

import java.util.Scanner;

public class Practice1 {
    public static void main(String args[]){
        solution2();
    }

    private static void solution1(){
        Scanner scanner=new Scanner(System.in);
        int[] tree=new int[scanner.nextInt()+1];
        for (int i=0;i<tree.length;i++){
            tree[i]=1;
        }

        int[][] m=new int[scanner.nextInt()][2];
        for (int i=0;i<m.length;i++){
            m[i][0]=scanner.nextInt();
            m[i][1]=scanner.nextInt();
        }

        int count=tree.length;
        for (int i=0;i<m.length;i++){
            for (int j=m[i][0];j<=m[i][1];j++){
                if (tree[j]==1){
                    tree[j]=0;
                    count--;
                }
            }
        }
        System.out.println(count);
    }

    private static void solution2(){
        Scanner scanner=new Scanner(System.in);
        int l= scanner.nextInt();
        int m=scanner.nextInt();

        boolean[] trees=new boolean[l+1];
        for (int i=0;i<trees.length;i++){
            trees[i]=true;
        }

        for (int i=0;i<m;i++){
            int start=scanner.nextInt();
            int end=scanner.nextInt();
            for (int j=start;j<=end;j++){
                trees[j]=false;
            }
        }

        int count=0;
        for (int i=0;i<trees.length;i++){
            if (trees[i]==true){
                count++;
            }
        }
        System.out.println(count);
        scanner.close();
    }
}