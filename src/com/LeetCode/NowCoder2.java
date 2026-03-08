//题目链接：https://www.xfxcy.com/p/X1019?tid=692465affdd1a52d3130a5cc
package com.LeetCode;

import java.util.*;

public class NowCoder2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        for (int i=0; i<n; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            students.add(new Student(a, b, c,i+1));
        }
        scanner.close();

        Collections.sort(students, (s1, s2) ->{   //给sort()方法传入comparator接口，定义排序规则
            if (s1.sum != s2.sum)
                return s2.sum - s1.sum;
            if (s1.a != s2.a)
                return s2.a - s1.a;
            if (s1.b != s2.b)
                return s2.b - s1.b;
            if (s1.c != s2.c)
                return s2.c - s1.c;
            return s1.id - s2.id;
        });

        for (int i=0; i<n ;i++){
            Student s = students.get(i);
            System.out.println(s.id + " " + s.sum + " " + s.a + " " + s.b + " " + s.c);
        }

    }

    static class Student{
        int id, a, b, c, sum;
        public Student(int a, int b, int c, int id){
            this.a = a;
            this.b = b;
            this.c = c;
            this.id = id;
            sum = a + b + c;
        }
    }
}
