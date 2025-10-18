/*
题目描述:
居然有假币！！！
事情是这样的，现在猪肉涨了，但是农民的工资却不见涨啊，没钱怎么买猪肉啊。老王这就去买猪肉，结果找来的零钱中有假币！！！可惜老王一不小心把它混进了一堆真币
里面去了。现在知道假币的重量比真币的质量要轻。给你一个天平，请用最快的时间把那个可恶的假币找出来。

输入格式:
输入有多行,每一行的值为硬币的数目n,1≤n≤2^30,输入0结束程序

输出格式:
最少要称多少次一定能把那个假币找出来。输出对应输入行数.

样例输入:
3
12
0
样例输出:
1
3
 */
package com.Note.Algorithm1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Practice3 {
    public static void main(String args[]) {
        //P3Solution1.main();
        P3Solution2.main();
    }
}

//我的方法
class P3Solution1{
    public static void main(){
        Scanner scanner = new Scanner(System.in);
        int[] output=new int[2000];
        int index=0;
        while(true){
            long n=scanner.nextLong();
            if (n!=0){
                output[index]=process(n);
                index++;
            }
            else{
                break;
            }
        }
        for (int i=0;i<index;i++){
            System.out.println(output[i]);
        }
    }

    private static int process(long n){
        int count=0;
        if (n==0 || n==1){
            return 0;
        }
        else if (n==2 || n==3){
            return 1;
        }
        else if (n%3==0){
            count++;
            count=process2(n/3,count);
        }
        else if (n%3!=0){
            count++;
            count=process2(n/3+1,count);
        }
        return count;
    }

    private static int process2(long n,int count){
        if (n==0 || n==1){
            return count;
        }
        else if (n==2 || n==3){
            count++;
        }
        else if (n%3==0){
            count++;
            count=process2(n/3,count);
        }
        else if (n%3!=0){
            count++;
            count=process2(n/3+1,count);
        }
        return count;
    }
}
//参考方法
class P3Solution2{
    public static void main() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> output = new ArrayList<>();   //动态数组
        while (true) {
            long n=scanner.nextLong();
            if (n==0){
                break;
            }
            else{
                output.add(process(n));
            }
        }

        for (int i:output){    //foreach用法
            System.out.println(i);
        }
    }

    private static int process(long n){
        if (n==0 || n==1){
            return 0;
        }
        int count=0;
        while (n>=2){    //使用循环替代递归
            if (n%3==0){
                n=n/3;
            }
            else{
                n=n/3+1;
            }
            count++;
        }
        return count;
    }
}
