package com.Note.Algorithm1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Practice4 {
    public static void main(String args[]) {
        Scanner scanner=new Scanner(System.in);
        List<Integer> inputArray=new ArrayList<>();

        int n=scanner.nextInt();
        for (int i=0;i<n;i++){            //分别输入数值
            inputArray.add(scanner.nextInt());
        }

        for (int i=0;i<inputArray.size();i++){      //分别对每个数值进行分解总数的处理，并打印
            System.out.println(possibilityCount(inputArray.get(i)));
        }
    }

    private static int possibilityCount(int n){
        if (n<=2){
            return 1;
        }
        int count=1;
        return possibilityCountHelper(n,2,count);
    }

    private static int possibilityCountHelper(int n,int factor,int count){     //possibilityCount附属方法
        for (int i=factor;i<n;i++){
            if (n/i>=i && n%i==0) {     //避免重复分解，且判断是否能够进行分解
                count++;
                count = possibilityCountHelper(n / i, i, count);   //对其中一个因数进行再次分解，递归出所有可能
            }
        }
        return count;
    }
}
