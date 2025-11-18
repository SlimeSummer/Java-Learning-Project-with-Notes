package com.Note.Algorithm1;

import java.util.Arrays;

public class MergeSort {
    public static void main(String args[]){
        int[] arr=new int[]{4,7,1,3,9,4,6,2,4,8};
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        mergeSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    private static void mergeSort(int[] arr){
        if (arr.length>1){
            int mid=arr.length/2;
            int[] leftArray=Arrays.copyOfRange(arr,0,mid);
            int[] rightArray=Arrays.copyOfRange(arr,mid,arr.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(arr,leftArray,rightArray);
        }
    }

    private static void merge(int[] arr,int[] leftArray,int[] rightArray){
        int i=0,j=0,k=0;

        for (k=0;i<arr.length;k++){
            if (i<leftArray.length && j<rightArray.length) {
                if (leftArray[i] <= rightArray[j]) {
                    arr[k] = leftArray[i];
                    i++;
                } else {
                    arr[k] = rightArray[j];
                    j++;
                }
            }

            if (i<leftArray.length){
                arr[k]=leftArray[i];
                i++;
            }
            if (j<rightArray.length){
                arr[k]=rightArray[j];
                j++;
            }
        }
    }
}
