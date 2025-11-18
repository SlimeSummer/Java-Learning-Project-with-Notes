package com.Note.Algorithm1;

import java.util.*;

public class Practice5 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> arr=new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr.add(scanner.nextInt());
        }
        System.out.println(findMaxSequence3(arr));
    }

    private static long findMaxSequence1(List<Integer> arr){     //递归
        if (arr.size()==0){
            return 0;
        }

        long max=0;
        for (int i=0;i< arr.size();i++){
            long temp_max=findMaxSequenceHelper1(arr,i,0,0);
            max=temp_max>max ? temp_max : max;
        }
        return max;
    }

    private static long findMaxSequenceHelper1(List<Integer> arr, int index, long max, long sum) {
        if (index >= arr.size()) {
            return max;
        }

        sum += arr.get(index);
        if (sum > max) {
            max = sum;
        }
        max=findMaxSequenceHelper1(arr, index + 1, max, sum);
        return max;
    }

    private static long findMaxSequence2(List<Integer> arr){
        long max=0;
        for (int i=0;i<arr.size();i++){
            int sum=0;
            for (int j=i;j< arr.size();j++){
                sum+=arr.get(j);
                if (sum>max){
                    max=sum;
                }
            }
        }
        return max;
    }

    private static long findMaxSequence3(List<Integer> arr){     //Kadane算法
        long max=-1000000,tempMax=0;
        for (int i=0;i<arr.size();i++){
            tempMax=Math.max(arr.get(i),tempMax+ arr.get(i));
            max=Math.max(max,tempMax);
        }
        return max;
    }
}
