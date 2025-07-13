package com.Note.BinaryTreeWithGUI;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class BinaryTree {
    private Node root;
    private int nodeNum=0;
    private int searchStep=0;

    public void add(Student student){      //供外部调用的add()方法
        root=addNode(student,root);
    }

    private Node addNode(Student data,Node node){          //add()方法的附属方法
        if (node!=null){
            if (data.getId()<node.data.getId()){
                node.left=addNode(data,node.left);
            }
            if (data.getId()>node.data.getId()){
                node.right=addNode(data,node.right);
            }
            if (data.getId()==node.data.getId()){
                throw new IllegalArgumentException("输入失败，该ID已存在");
            }
        }
        else{
            nodeNum++;
            return new Node(data);
        }
        return node;
    }

    public void delete(int id){    //供外部调用的删除方法
        root=deleteNode(id,root);
    }

    private Node deleteNode(int id, Node node){
        if (node!=null){
            if (id<node.data.getId()){
                node.left=deleteNode(id,node.left);
            }
            if (id>node.data.getId()){
                node.right=deleteNode(id,node.right);
            }
            if (id==node.data.getId()){
                nodeNum--;
                if (node.left==null){
                    if (node.right==null){
                        return null;
                    }
                    else{
                        return node.right;
                    }
                }
                else{
                    if (node.right==null){
                        return node.left;
                    }
                    else{
                        node.data=findMin(node.right).data;
                        node.right=deleteNode(node.data.getId(),node.right);
                        return node;
                    }
                }
            }
            return node;
        }
        else {
            throw new NoSuchElementException("没有该数据！");
        }
    }

    private Node findMin(Node node){   //内部方法，查找最小子节点
        while (node.left!=null){
            node=node.left;
        }
        return node;
    }

    public void traverse(JTextArea textArea){      //供外部调用的遍历方法
        traverseNode(root,textArea);
    }

    private void traverseNode(Node node,JTextArea textArea){   //traverse()方法的附属方法
        if (node!=null) {
            traverseNode(node.left,textArea);
            textArea.append(node.data.toString());
            traverseNode(node.right,textArea);
        }
    }

    public Student searchId(int id){  //供外部调用的id查找方法
        searchStep=0;
        Node result=searchIdNode(id,root);
        if (result!=null){
            return result.data;
        }
        else{
            throw new NoSuchElementException("查找失败！搜索结果为空");
        }
    }

    private Node searchIdNode(int id,Node node){  //searchId附属方法
        searchStep++;
        if (node!=null){
            if (id==node.data.getId()){
                return node;
            }
            if (id<node.data.getId()){
                return searchIdNode(id,node.left);
            }
            if (id>node.data.getId()){
                return searchIdNode(id,node.right);
            }
        }
        return null;
    }

    public Student[] searchName(String name){   //供外部调用的按姓名查找方法
        int i=0;
        Node[] searchNameNodeArray=new Node[nodeNum];
        i=searchNameNode(name,root,i,searchNameNodeArray);
        if (i==0){
            throw new NoSuchElementException("查找失败！搜索结果为空");
        }
        Student[] student=new Student[i];
        for (int j=0;j<i;j++){
            student[j]=searchNameNodeArray[j].data;
        }
        return student;
    }

    private int searchNameNode(String name, Node node,int i,Node[] searchNameNodeArray){  //searchName附属方法
        if (node!=null){
            if (node.left!=null){
                i=searchNameNode(name, node.left,i, searchNameNodeArray);
            }
            if (name.equals(node.data.getName())){
                searchNameNodeArray[i]=node;
                i++;
            }
            if (node.right!=null){
                i=searchNameNode(name,node.right,i, searchNameNodeArray);
            }
        }
        return i;
    }

    public Student[] searchScore(int score){  //供外部调用的按分数查找方法
        int i=0;
        Node[] searchScoreNodeArray=new Node[nodeNum];
        i=searchScoreNode(score,root,i,searchScoreNodeArray);
        if (i==0){
            throw new NoSuchElementException("查找失败！搜索结果为空");
        }
        Student[] student=new Student[i];
        for (int j=0; j<i; j++){
            student[j]=searchScoreNodeArray[j].data;
        }
        return student;
    }

    private int searchScoreNode(int score, Node node, int i, Node[] searchScoreNodeArray){  //searchScore附属方法
        if (node!=null){
            if (node.left!=null){
                i=searchScoreNode(score,node.left,i,searchScoreNodeArray);
            }
            if (node.data.getScore()==score){
                searchScoreNodeArray[i]=node;
                i++;
            }
            if (node.right!=null){
                i=searchScoreNode(score,node.right,i,searchScoreNodeArray);
            }
        }
        return i;
    }

    public int getNodeNum(){   //获取二叉树的数据节点数
        return nodeNum;
    }

    public int getSearchStep(){  //获取查找步骤数
        return searchStep;
    }

    // 保存数据到文件
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            List<Student> students = new ArrayList<>();
            collectStudents(root, students);
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void collectStudents(Node node, List<Student> students) {   //saveToFile附属方法
        if (node != null) {
            collectStudents(node.left, students);
            students.add(node.data);
            collectStudents(node.right, students);
        }
    }

    // 从文件读取数据
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            @SuppressWarnings("unchecked")
            List<Student> students = (List<Student>) ois.readObject();
            this.root = null;
            this.nodeNum = 0;
            for (Student student : students) {
                this.add(student);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new NoSuchElementException("文件名不存在！");
        }
    }
}

class Node{
    public Student data;
    public Node left;
    public Node right;
    public Node(Student data){
        this.data=data;
    }
}

class Student implements Serializable{
    private int id;
    private String name;
    private int score;

    public Student(int id, String name, int score){
        this.id=id;
        this.name=name;
        this.score=score;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }

    @Override
    public String toString() {
        return "id: " + id + " 名字: " + name + " 成绩: " + score + "\n";
    }
}
/*
总结：
1. BinaryTree类中的add()方法没有任何其他行为，而是把处理的行为转接到了内部的addNode()方法中，这种模式被称为“委托模式”，其中add()方法是一个公用
的接口方法，供外部调用，并把方法的具体实现细节委托到内部的addNode()方法中。这是一种分离接口与实现的理念。add()方法的“接口”概念是一个更为广泛的概念，
它是一种设计思想和理念，和Java语法体系中的“接口”既有相似性，也有一定区别。Java中的“接口”概念就是从这种“接口”的设计思想和理念中发展出来的，核心思想是
隐藏内部实现细节，与此同时提供相同的行为契约。因此当谈到“接口”时，需要区别所谈论的接口是属于哪个层面上、哪个语境下的“接口”，避免混淆。
2. Java中的封装、多态、继承等概念不仅是Java的一种语言特性，还应该从这种语言特性出发，发散成为一种编程的核心思想方法，融入到自己日常的编程行为和思考模
式中。甚至是发展成自己的一个思考体系和哲学体系，在这些核心思想的指导下进行任何一种行为活动（数码绘画、音乐创作等）。
**/