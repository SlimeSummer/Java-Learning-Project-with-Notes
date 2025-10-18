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
        LinkedList l1=new LinkedList(9999999);
        LinkedList l2=new LinkedList(9999);
        l1.printNodeList();
        l2.printNodeList();
        LinkedList l3=new LinkedList(Sum2Solution.addTwoNumbers(l1.getRoot(),l2.getRoot()));
        l3.printNodeList();
        System.out.println(l3.getData());
    }
}

//管理整个链表的类
class LinkedList {
    private int data;
    private ListNode root;

    public LinkedList(int data){       //由数值数据构造
        this.data=data;
        root=nodeProcess(data);
    }
    public LinkedList(ListNode root){   //由现有的链表的根节点构造,并解析相应的数值数据保存
        this.root=root;
        StringBuilder string=new StringBuilder();
        while(root!=null){
            string.append(root.val);
            root=root.next;
        }
        data=Integer.valueOf(string.reverse().toString());
    }

    //将数值数据转换为链表形式的处理方法，递归原理。对象被构造时会自动调用并自动完成处理
    private ListNode nodeProcess(int temp){
        ListNode node=new ListNode(temp%10);
        if (temp/10!=0) {
            node.next = nodeProcess(temp / 10);
        }
        return node;
    }

    public ListNode getRoot(){
        return root;
    }

    public int getData(){
        return data;
    }

    public void printNodeList(){    //以链表形式打印出数据的方法，供外部调用
        traverse(root);
        System.out.println();
    }

    private void traverse(ListNode node){  //printNodeList()附属方法，遍历每个结点
        if (node!=null){
            System.out.print(node.val);
            if (node.next!=null){
                System.out.print("->");
                traverse(node.next);
            }
        }
    }
}

class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int val){
        this.val=val;
    }
    public ListNode(int val,ListNode next){
        this.val=val;
        this.next=next;
    }
}

class Sum2Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null && l2==null){
            return null;
        }
        return addNode(l1,l2,false);
    }

    private static ListNode addNode(ListNode l1, ListNode l2,boolean carry) {  //addTwoNumbers附属方法
        int sum;
        if (l1!=null){
            if (l2==null){
                l2=new ListNode(0);
            }
            sum= l1.val+ l2.val;
            if (carry==true){
                sum+=1;
            }
            if (sum>=10){
                ListNode node=new ListNode(sum-10);
                node.next=addNode(l1.next,l2.next,true);
                return node;
            }
            else{
                ListNode node=new ListNode(sum);
                node.next=addNode(l1.next,l2.next,false);
                return node;
            }
        }

        if (l2!=null){
            if (l1==null){
                l1=new ListNode(0);
            }
            sum= l1.val+ l2.val;
            if (carry==true){
                sum+=1;
            }
            if (sum>=10){
                ListNode node=new ListNode(sum-10);
                node.next=addNode(l1.next,l2.next,true);
                return node;
            }
            else{
                ListNode node=new ListNode(sum);
                node.next=addNode(l1.next,l2.next,false);
                return node;
            }
        }
        if (carry==true){
            return new ListNode(1);
        }
        return null;
    }
}