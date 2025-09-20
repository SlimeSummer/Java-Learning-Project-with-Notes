package com.Note;

import java.util.Scanner;

public class InputData {
    public static void main(String args[]){
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入数字A，B（用空格隔开）");
        int ans=scanner.nextInt()+scanner.nextInt();
        System.out.println("ans="+ans);
        scanner.close();
    }
}

/*
总结：
1. scanner类是java.util包中的一个工具类，作用是将输入信息分解成一个个token标记，并根据需要将它们转换成不同类型的数据，以供程序使用。scanner的
构造器参数中需要指定数据来源，如System.in。System.in代表标准输入，通常指键盘输入，System.in是数据来源，是连接到键盘的“管道”。程序通过这个
“管道”来接收用户从键盘上敲入的原始字节数据。
2. scanner的nextInt()方法的作用是：从输入流中读取下一个“标记”（token），并将其解析为一个 int 类型的整数。Scanner默认使用空白字符（空格、
制表符、换行符等）作为分隔符，将输入的字符串分割成一个个独立的“标记”。
*/
