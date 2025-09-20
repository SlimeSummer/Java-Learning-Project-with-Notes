package com.Contest;

import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入数字A，B（用空格隔开）");
        int ans=scanner.nextInt()+scanner.nextInt();
        System.out.println("ans="+ans);
        scanner.close();
    }
}
