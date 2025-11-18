/*
题目描述:
给出若干个整数，询问其中是否有一对数的和等于给定的数。

输入格式:
第一行是整数n(0 < n ≤ 100,000)，表示有n个整数。
第二行是n个整数。整数的范围是在0到108之间。
第三行是一个整数m（0≤m≤230)，表示需要得到的和。

输出格式:
若存在和为m的数对，输出两个整数，小的在前，大的在后，中间用单个空格隔开。若有多个数对满足条件，选择数对中较小的数更小的。若找不到符合要求的数对，输出一行No。

样例输入:
4
2 5 1 4
6

样例输出:
1 5
 */
package com.Note.Algorithm1;

import java.util.Arrays;
import java.util.Scanner;

public class Practice7 {
    public static void main(String args[]) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt();
        int[] array=new int[n];
        for (int i=0;i< array.length;i++){
            array[i]= scanner.nextInt();
        }
        int m= scanner.nextInt();
        findNumber3(array,m);
    }

    private static void findNumber1(int[] array,int m){    //最原始方法
        if (m>108*2){
            System.out.println("No");
            return;
        }
        Arrays.sort(array);
        for (int i=0;i< array.length;i++){
            if(m<array[i]){
                break;
            }
            int temp=m-array[i];
            for (int j=i+1;j< array.length;j++){
                if (array[j]==temp) {
                    System.out.println(array[i] + " " + array[j]);
                    return;
                }
            }
        }
        System.out.println("No");
    }

    private static void findNumber2(int[] array,int m){    //利用Java的Arrays工具类的binarySearch查找配对的数
        if (m>108*2){
            System.out.println("No");
            return;
        }
        Arrays.sort(array);
        for(int i=0;i< array.length;i++){
            int index=Arrays.binarySearch(array, m-array[i]);
            if (index>=0 && index!=i){
                System.out.println(array[i] + " " + array[index]);
                return;
            }
        }
        System.out.println("No");
    }

    private static void findNumber3(int[] array,int m){  //哈希表思想
        int[] b=new int[2000];
        int index;
        for (int i=0;i< array.length;i++){
            index=array[i];
            b[index]++;
        }
        for (int i=0;i<=m/2;i++){
            if (b[i]>0){
                b[i]--;
                if (b[m-i]>0){
                    System.out.println(i + " " +(m-i));
                    return;
                }
            }
        }
        System.out.println("No");
    }
}
/*
总结：
1. findNumber3方法是一种哈希表思想，使用数组作为简单的哈希表，索引表示数值，值表示该数值出现的次数，用数组下标直接映射数值。它的本质是用额外的储存空间
降低时间复杂度，是一种空间换时间思想。适用于数据范围有限且可映射到数组索引
2. for (int i=0;i<=m/2;i++)是一种互补数思想，要找 a + b = m，等价于找 b = m - a。不盲目枚举所有数对，而是针对每个a直接定位其互补数b
 */