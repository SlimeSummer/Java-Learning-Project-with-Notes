package com.LeetCode;

import java.util.*;

public class NowCoder1 {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int x=scanner.nextInt();
        Map<Integer, Integer> map=new HashMap<>();

        for(int i=0; i<n; i++){
            int val= scanner.nextInt();
            map.put(val, map.getOrDefault(val,0)+1);
        }

        int max=0;
        for (int count : map.values()){
            if (count > max)
                max=count;
        }

        System.out.println(max- map.getOrDefault(x,0));
    }
}
