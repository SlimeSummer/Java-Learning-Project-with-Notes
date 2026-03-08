/*
题目描述：
农夫知道一头牛的位置，想要抓住它。农夫和牛都位于数轴上，农夫起始位于点N(0≤N≤100000)，牛位于点K(0≤K≤100000)。农夫有两种移动方式：
1、从X移动到X−1或X+1，每次移动花费一分钟
2、从X移动到2×X，每次移动花费一分钟
假设牛没有意识到农夫的行动，站在原地不动。农夫最少要花多少时间才能抓住牛？

输入格式：
两个整数，N和K。

输出格式：
一个整数，农夫抓到牛所要花费的最小分钟数。

样例输入：
5 17

样例输出：
4
 */
package com.Note.Algorithm1;

import java.util.Scanner;

public class BFS{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int N= scanner.nextInt();  //农夫位置
        int K= scanner.nextInt();  //牛位置
        int step;

        if(N>=K){
            step=N-K;
        }
        else{
            step=bfs(N,K);
        }
        System.out.println(step);
    }

    private static int bfs(int start,int target){
        Queue queue=new Queue(start);
        boolean[] visited=new boolean[100001];
        visited[start]=true;

        while (queue.front< queue.rear){
            QueueNode node=queue.read();
            int currentPos=node.pos;
            int currentStep=node.step;

            if (currentPos==target){
                return currentStep;
            }
            else{
                if (currentPos-1>=0 && !visited[currentPos-1]){
                    queue.add(currentPos-1,currentStep+1);
                    visited[currentPos-1]=true;
                }
                if (currentPos+1<=100000 && !visited[currentPos+1]){
                    queue.add(currentPos+1,currentStep+1);
                    visited[currentPos+1]=true;
                }
                if (currentPos*2<=100000 && !visited[currentPos*2]){
                    queue.add(currentPos*2,currentStep+1);
                    visited[currentPos*2]=true;
                }
            }
        }
        return -1;
    }
}

class Queue{
    int  front;
    int  rear;
    QueueNode[] queue;

    public Queue(int start){
        front=rear=0;
        queue=new QueueNode[200000];
        queue[rear]=new QueueNode();
        queue[rear].pos=start;
        queue[rear].step=0;
        rear++;
    }

    public void add(int pos, int step){
        queue[rear]=new QueueNode();
        queue[rear].pos=pos;
        queue[rear].step=step;
        rear++;
    }

    public QueueNode read(){
        QueueNode result=queue[front];
        front++;
        return result;
    }
}

class QueueNode{
    int step;
    int pos;
}

/*
总结：
1. 第60行代码中的queue=new QueueNode[200000]创建了一个QueueNode数组，但是数组中的元素都是空值，如果直接queue[rear].pos访问就会报错。这是
因为创建的是引用类型的数组，本质上它只是创建了一个存储引用地址指针的数组，实际上数组中的指针都全部指向null，因此需要通过new QueueNode()新建对应的对象，
然后将对象和指针关联起来
 */

