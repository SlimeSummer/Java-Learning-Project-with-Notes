package com.Note;

import java.util.Arrays;
import java.util.Comparator;

public class Sort {
    public static void main(String[] args){
        Person[] people={
                new Person("Tom", 25),
                new Person("Alice", 22),
                new Person("Bob", 30),
                new Person("Charlie", 22)
        };

        //Arrays.sort(people);   //使用实现的comparable接口进行排序
        Arrays.sort(people, new Comparator<Person>() {    //通过使用匿名内部类实现Comparator接口排序
            @Override
            public int compare(Person p1, Person p2) {
                if (p1.age != p2.age)
                    return p2.age-p1.age;
                return p2.name.compareTo(p1.name);
            }
        });

        for (int i=0; i<4; i++)
            System.out.println(people[i].toString());
    }
}

class Person implements Comparable<Person> {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {   //实现Comparable接口的compareTo方法，自定义排序规则
        if (this.age != other.age)
            return other.age - this.age;
        return -(this.name.compareTo(other.name));
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

/*
总结：
1. Arrays.sort()方法使用时，sort()方法内部会自动调用compareTo()方法比较元素，因此如果使用sort()方法对自定义的类型实行排序，
该类型必须实现comparable接口，该接口是Java提供的一个排序接口，它允许对象自己定义如何与其他同类型的对象比较大小，通过重写接口的
compareTo()方法来自定义排序规则。Comparable是内部比较器，用于类自己实现，实现自然排序。
    int compareTo(T o)方法的规则是，当前对象<参数对象时（返回值为负），当前对象在前；当前对象>参数对象时(返回值为正)，当前对象在后。

2. Comparable接口和Comparator接口的区别在于：Comparable是内部比较器，需要类自己实现compareTo方法；Comparator是外部比较器，用于对两个对象的大小
实行排序, 需要实现compare方法。在sort方法中传入Comparator接口时，除了直接使用匿名内部类定制排序规则，还可以使用lambda表达式，例如：(p1, p2)->{
    if (p1.age != p2.age)
        return p2.age-p1.age;
    return p2.name.compareTo(p1.name);
}
 */
