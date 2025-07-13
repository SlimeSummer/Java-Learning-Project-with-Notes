/*
给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
示例 1： 链表1：2->4->3  链表2：5->6->4
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
* */
package com.LeetCode;

public class Sum2 {
    public static void main(String[] args){
        linkedlist l1=new linkedlist(342);
        linkedlist l2=new linkedlist(465);
    }
}

class linkedlist{
    private int data;
    private listnode root;
    public linkedlist(int data){
        this.data=data;
        if (root!=null){

        }
        else{
            root=new listnode(data%10);

        }
    }
}

class listnode{
    public int data;
    public listnode next;
    public listnode(int data){
        this.data=data;
    }
}

class solution{

}