/* 快速排序、随机数、枚举 */
package com.Note.Algorithm1;

import java.util.*;

public class QuickSort {
    public static void main(String args[]) {
        int[] arr=new int[]{4,7,1,3,9,4,6,2,4,8};
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        quickSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    private static void quickSort(int[] arr){
        quickSortHelper(arr,0,arr.length-1);
    }

    private static void quickSortHelper(int[] arr,int left,int right){
        if (left<right) {
            Random rand=new Random();
            int pivot=0;

            switch (rand.nextInt(3)){
                case 0:
                    pivot=partition1(arr, left, right);
                    break;
                case 1:
                    pivot=partition2(arr, left, right);
                    break;
                case 2:
                    pivot=partition3(arr, left, right);
                    break;
            }

            quickSortHelper(arr, left, pivot - 1);
            quickSortHelper(arr, pivot + 1, right);
        }
    }

    private static int partition1(int[] arr,int left,int right){
        int base=arr[left];
        while(left<right){
            while(left<right && arr[right]>=base){
                right--;
            }
            arr[left]=arr[right];
            while(left<right && arr[left]<=base){
                left++;
            }
            arr[right]=arr[left];
        }
        arr[left]=base;
        return left;
    }

    private static int partition2(int[] arr,int left,int right){
        int base=arr[left];
        String control="right";

        while(left<right) {
            if (control.equals("right")) {
                while (left < right) {
                    if (arr[right] < base) {
                        arr[left] = arr[right];
                        control = "left";
                        break;
                    }
                    right--;
                }
            }
            if (control.equals("left")){
                while(left<right){
                    if (arr[left]>base) {
                        arr[right] = arr[left];
                        control = "right";
                        break;
                    }
                    left++;
                }
            }
        }

        arr[left]=base;
        return left;
    }

    enum Control{LEFT,RIGHT}
    private static int partition3(int[] arr,int left,int right){
        int base=arr[left];
        Control control= Control.RIGHT;

        while(left<right) {
            if (control==Control.RIGHT) {
                while (left < right) {
                    if (arr[right] < base) {
                        arr[left] = arr[right];
                        control=Control.LEFT;
                        break;
                    }
                    right--;
                }
            }
            if (control==Control.LEFT){
                while(left<right){
                    if (arr[left]>base) {
                        arr[right] = arr[left];
                        control=Control.RIGHT;
                        break;
                    }
                    left++;
                }
            }
        }

        arr[left]=base;
        return left;
    }
}
