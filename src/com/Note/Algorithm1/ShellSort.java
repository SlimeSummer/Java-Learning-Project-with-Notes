package com.Note.Algorithm1;

public class ShellSort {
    public static void main(String args[]) {
        int[] arr=new int[]{4,7,1,3,9,4,6,2,4,8};
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        shellSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    private static void shellSort(int[] arr){   //个人风格希尔排序
        for (int gap=arr.length/2;gap>0;gap/=2) {
            for (int group=0;group<gap;group++){
                for (int end=group;end+gap<arr.length;end+=gap){
                    int nextData=arr[end+gap];
                    int index=end;
                    while(index>=group && arr[index]>nextData){
                        arr[index+gap]=arr[index];
                        index-=gap;
                    }
                    arr[index+gap]=nextData;
                }
            }
        }
    }

    private static void shellSort2(int[] arr){  //标准风格希尔排序
        for (int gap=arr.length/2;gap>0;gap/=2) {
            for (int i=gap;i < arr.length;i++) {
                int temp = arr[i];
                int j=i;
                while (j>=gap && arr[j - gap]>temp) {
                    arr[j]=arr[j - gap];
                    j-=gap;
                }
                arr[j] = temp;
            }
        }
    }
}
