/*
题目描述:
一般来说，一个正整数可以拆分成若干个正整数的和。例如，1 = 1，10 =  1 + 2 + 3 + 4 等。
对于正整数 n 的一种特定拆分，我们称它为“优秀的”，当且仅当在这种拆分下，n 被分解为了若干个不同的 2 的正整数次幂。注意，一个数 x 能被表 示成 2 的
正整数次幂，当且仅当 x 能通过正整数个 2 相乘在一起得到。例如，10 = 8 + 2 = 23 + 21 是一个优秀的拆分。但是，7 = 4 + 2 + 1 =  22 + 21 + 20
就不是一个优秀的拆分，因为 1 不是 2 的正整数次幂。现在，给定正整数 n，你需要判断这个数的所有拆分中，是否存在优秀的 拆分。若存在，请你给出具体的拆分方案。

输入格式:
输入只有一行，一个正整数 n，代表需要判断的数。

输出格式:
如果这个数的所有拆分中，存在优秀的拆分。那么，你需要从大到小输出 这个拆分中的每一个数，相邻两个数之间用一个空格隔开。可以证明，在规定 了拆分数字的顺序
后，该拆分方案是唯一的。 若不存在优秀的拆分，输出“-1”（不包含双引号）。
 */
package com.Note.Algorithm1;

import java.util.*;

public class Practice2 {
    public static void main(String args[]) {
        //P2Solution1.main();
        //P2Solution2.main();
        P2Solution3.main();
    }
}

//我的方法
class P2Solution1{
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        int[] termsArray=new int[50];
        int index=0;
        process(scanner.nextInt(),termsArray,index);
        if (termsArray[0]!=-1) {
            for (int i = 0; termsArray[i] != 0; i++) {
                System.out.print(termsArray[i] + " ");
            }
        }
        else{
            System.out.print(-1);
        }
    }

    private static int process(int n, int[] termsArray,int index){
        int term=2;
        while(n>=2){
            if (term==n){
                termsArray[index]=term;
                index++;
                return term;
            }
            else {
                if (term < n && term*2>n) {
                    termsArray[index]=term;
                    index++;
                    return term+process(n-term,termsArray,index);
                }
                else {
                    term=term*2;
                }
            }
        }
        termsArray[0]=-1;
        return n;
    }
}
//改进后的方法
class P2Solution2{
    public static void main(){
        Scanner scanner = new Scanner(System.in);
        List<Integer> termsArray=new ArrayList<>();
        boolean success=process(scanner.nextInt(),termsArray);
        if (success){
            for (int i:termsArray){
                System.out.print(i+" ");
            }
        }
        else{
            System.out.print(-1);
        }
    }

    private static boolean process(int n,List<Integer> termsArray){
        if (n==2){
            termsArray.add(2);
            return true;
        }
        if (n<2){
            return false;
        }
        int term=2;
        while(term*2<=n){
            term=term*2;
        }
        termsArray.add(term);
        return process2(n-term,termsArray);
    }

    private static boolean process2(int n,List<Integer> termsArray){
        if (n==0){
            return true;
        }
        if (n<2){
            return false;
        }
        int term=2;
        while(term*2<=n){
            term=term*2;
        }
        termsArray.add(term);
        return process2(n-term,termsArray);
    }
}
//参考方法
class P2Solution3{
    public static void main(){
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        if (n%2!=0){
            System.out.println(-1);
        }

        StringBuilder output=new StringBuilder();
        for (int i=30;i>=1;i--){
            int term=1<<i;
            if (n>=term){
                if (output.length()!=0){
                    output.append(" ");
                }
                output.append(term);
                n-=term;
            }
        }
        System.out.println(output);
    }
}


