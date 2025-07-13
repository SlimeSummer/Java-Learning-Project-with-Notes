package com.Note;

public class BinaryTree1 {
    public static void main(String[] args){
        Tree tree=new Tree();
        tree.add(new Student(4,"bob"));
        tree.add(new Student(2,"nancy"));
        tree.add(new Student(1,"tom"));
        tree.add(new Student(3,"jack"));
        tree.add(new Student(8,"amy"));
        tree.add(new Student(5,"sam"));
        tree.add(new Student(6,"john"));
        tree.add(new Student(7,"lucy"));
        tree.traverse();
        System.out.println(tree.search(8)+" step:"+tree.getSearchStep());
        System.out.println(tree.search(7)+" step:"+tree.getSearchStep());
    }
}

class Tree{
    private Node root;
    private int searchStep;

    public void add(Student student){  //二叉树插入数据
        if (root!=null){
            addNode(student,root);
        }
        else{
            root=new Node(student);
        }
    }

    private void addNode(Student student,Node root){     //这个是tree类中add()方法的一个附属方法，是用于辅助add()方法的一个辅助方法，因此用private
        if (student.getId()<root.getData().getId()){
            if (root.getLeft()!=null){
                addNode(student,root.getLeft());
            }
            else{
                root.setLeft(new Node(student));
            }
        }
        if (student.getId()>root.getData().getId()){
            if (root.getRight()!=null){
                addNode(student,root.getRight());
            }
            else{
                root.setRight(new Node(student));
            }
        }
        if (student.getId()==root.getData().getId()) {
            throw new IllegalArgumentException("invalid id");
        }
    }

    public void traverse(){     //二叉树遍历
        if (root!=null){
            traverseNode(root);
        }
        else{
            System.out.println("null");
        }
    }

    private void traverseNode(Node root){   //traverse()方法的附属方法
        if (root.getLeft()!=null){
            traverseNode(root.getLeft());
        }
        System.out.println(root.getData().getId()+" "+root.getData().getName());
        if (root.getRight()!=null){
            traverseNode(root.getRight());
        }
    }

    public Student search(int data){   //查找数据
        if (root!=null){
            searchStep=0;
            return searchNode(data,root);
        }
        else{
            throw new IllegalArgumentException("null");
        }
    }

    private Student searchNode(int id,Node root){    //search()方法附属方法
        if (id==root.getData().getId()){
            searchStep++;
            return root.getData();
        }
        if (id<root.getData().getId()){
            if (root.getLeft()!=null){
               searchStep++;
               return searchNode(id,root.getLeft());
            }
        }
        if (id>root.getData().getId()){
            if (root.getRight()!=null){
                searchStep++;
                return searchNode(id,root.getRight());
            }
        }
        throw new IllegalArgumentException("illegal data");
    }

    public int getSearchStep() {   //查询查找执行步骤
        return searchStep;
    }
}

class Node{
    private Student data;
    private Node left;
    private Node right;
    public Node(Student data){
        this.data=data;
    }
    public Student getData(){
        return data;
    }
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }
    public void setLeft(Node left){
        this.left=left;
    }
    public void setRight(Node right){
        this.right=right;
    }
}

class Student{
    private int id;
    private String name;
    Student(int id,String name){
        this.id=id;
        this.name=name;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    @Override
    public String toString(){
        return id+"->"+name;
    }
}
