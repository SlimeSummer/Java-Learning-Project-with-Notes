package com.practice;

import java.io.PrintStream;
import java.util.*;

public class Practice {
    public static void main (String[] args) {
        Scanner s=new Scanner(System.in);
        int i=0;
        while(s.hasNext()){
            System.out.print(s.next()+" ");
            i++;
        }
        s.close();
        System.out.println(i);
    }
}
