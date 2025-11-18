package com.Note.Algorithm1;

public class InsertionSort {
    public static void main(String args[]){
        int[] arr=new int[]{4,7,1,3,9,4,6,2,4,8};
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        Sort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    private static void Sort(int[] arr){
        for (int end=0;end<arr.length-1;end++){
            int insertData=arr[end+1];
            int index=end;

            while(index>=0 && insertData<arr[index]){
                arr[index+1]=arr[index];
                index--;
            }
            arr[index+1]=insertData;
        }
    }
}
