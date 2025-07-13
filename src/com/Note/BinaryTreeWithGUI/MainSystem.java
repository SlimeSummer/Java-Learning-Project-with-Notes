package com.Note.BinaryTreeWithGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainSystem {
    public static void main(String args[]){
        BinaryTree tree=new BinaryTree();
        SwingUtilities.invokeLater(()->mainGUI(tree));
    }

    private static void mainGUI(BinaryTree tree){
        // 设置全局字体大小
        Font font = new Font("Dialog", Font.PLAIN, 24);
        UIManager.put("Label.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextField.font", font);

        //创建一个Windows窗口
        JFrame frame= WindowsGenerator.createMainWindow("学生成绩管理系统");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //创建Windows窗口上方用于信息显示相关的模块
        JTextArea textArea=new JTextArea(4,10);
        textArea.setEditable(false);
        JScrollPane scrollPane=new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //创建Windows窗口下方用于操作的各个组件
        JTextField textField1=new JTextField(10);
        JTextField textField2=new JTextField(10);
        JTextField textField3=new JTextField(10);
        JLabel id=new JLabel("ID");
        JLabel name=new JLabel("名字");
        JLabel score=new JLabel("成绩");
        JButton button=new JButton("录入");
        JButton button2=new JButton("遍历");
        JButton button3=new JButton("删除");
        JButton button4=new JButton("查找");
        JButton button5=new JButton("保存数据");
        JButton button6=new JButton("读取数据");

        //给Windows窗口中的按钮添加实际功能
        addFunction1(button,textArea,tree,textField1,textField2,textField3);
        addFunction2(button2,textArea,tree);
        addFunction3(button3,textArea,tree,textField1);
        addFunction4(button4,textArea,tree,textField1,textField2,textField3);
        addFunction5(button5,tree);
        addFunction6(button6,tree);

        //将Windows窗口下方用于操作的各个组件拼接成一个模块
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(6,2));
        panel.add(id);
        panel.add(textField1);
        panel.add(name);
        panel.add(textField2);
        panel.add(score);
        panel.add(textField3);
        panel.add(button);
        panel.add(button3);
        panel.add(button4);
        panel.add(button2);
        panel.add(button5);
        panel.add(button6);

        //将创建好的信息显示、操作模块放进Windows窗口中
        frame.add(scrollPane,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.SOUTH);

        //给windows窗口添加关闭后弹出提示框功能
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                WindowsGenerator.createSaveOrNotWindow(tree);
            }
        });
    }

    //内部方法：设置鼠标按下按钮后的效果（“录入”按钮）
    private static void addFunction1(JButton button,JTextArea textArea, BinaryTree tree,
                                    JTextField textField1,JTextField textField2,JTextField textField3){
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String idString=textField1.getText().trim();
                    String name = textField2.getText().trim();
                    String scoreString = textField3.getText().trim();

                    if (contentIsValid(idString) && contentIsValid(name) && contentIsValid(scoreString)) {
                        int id=Integer.valueOf(idString);
                        int score=Integer.valueOf(scoreString);
                        tree.add(new Student(id,name,score));
                        textArea.append("输入成功：\n");
                        textArea.append("id: " + id + " 名字: " + name + " 成绩: " + score + "\n\n");
                        textField1.setText("");
                        textField2.setText("");
                        textField3.setText("");
                    } else {
                        textArea.append("输入有误，请重新输入\n");
                    }
                } catch (Exception ex){
                    textArea.append(ex.getMessage()+"\n");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    //内部方法：设置鼠标按下按钮后的效果（“遍历”按钮）
    private static void addFunction2(JButton button,JTextArea textArea, BinaryTree tree){
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textArea.append("遍历结果为：\n");
                tree.traverse(textArea);
                textArea.append("\n");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    //内部方法：设置鼠标按下按钮后的效果（“删除”按钮）
    private static void addFunction3(JButton button,JTextArea textArea,BinaryTree tree,JTextField textField1){
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String idString = textField1.getText().trim();
                    if (contentIsValid(idString)) {
                        int id = Integer.valueOf(idString);
                        tree.delete(id);
                        textArea.append("删除成功\n\n");
                    }
                }catch (Exception ex){
                    textArea.append(ex.getMessage()+"\n");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    //内部方法：设置鼠标按下按钮后的效果（“查找”按钮）
    private static void addFunction4(JButton button,JTextArea textArea,BinaryTree tree,
                                     JTextField textField1,JTextField textField2,JTextField textField3){
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String idString = textField1.getText().trim();
                    String name = textField2.getText().trim();
                    String scoreString = textField3.getText().trim();

                    if (contentIsValid(idString)) {
                        int id=Integer.valueOf(idString);
                        textArea.append("查找结果为：\n");
                        textArea.append(tree.searchId(id).toString());
                        textArea.append("耗费步骤数："+tree.getSearchStep()+"\n\n");
                    }
                    else {
                        if (contentIsValid(name)) {
                            textArea.append("查找结果为：\n");
                            Student[] printArray = tree.searchName(name);
                            int i = 0;
                            while (i < printArray.length) {
                                textArea.append(printArray[i].toString());
                                i++;
                            }
                            textArea.append("\n");
                        }
                        else {
                            if (contentIsValid(scoreString)) {
                                int score = Integer.valueOf(scoreString);
                                textArea.append("查找结果为：\n");
                                Student[] printArray=tree.searchScore(score);
                                for (int i=0;i< printArray.length;i++){
                                    textArea.append(printArray[i].toString());
                                }
                                textArea.append("\n");
                            }
                        }
                    }
                } catch(Exception ex){
                    textArea.append(ex.getMessage()+"\n");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    //内部方法：设置鼠标按下按钮后的效果（“保存数据”按钮）
    private static void addFunction5(JButton button,BinaryTree tree){
        button.addActionListener(e -> WindowsGenerator.createSaveWindow(tree));
    }

    //内部方法：设置鼠标按下按钮后的效果（“读取数据”按钮）
    private static void addFunction6(JButton button,BinaryTree tree){
        button.addActionListener(e -> WindowsGenerator.createLoadWindow(tree));
    }

    //工具方法：判断文本框中输入的数据是否合理
    protected static boolean contentIsValid(Object data){
        if (data instanceof Integer){
            int num=(int)data;
            if (num>=0) {
                return true;
            }
            else{
                return false;
            }
        }
        if (data instanceof String){
            String name=(String)data;
            if (name!=null && !name.trim().equals("")){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }
}
/*
总结：
1. SwingUtilities类下invokeLater()方法的方法签名为：public static void invokeLater(Runnable doRun)，需要传入一个Runnable类型的接口
作为参数。而Runnable接口只有一个方法：void run()，它不需要接收任何参数，因此在使用lambda表达式实现这个接口时不需要传入任何一个参数，直接写出run()
方法的方法体中需要实现的具体代码即可。这里SwingUtilities.invokeLater(()->gui(tree));的()->gui(tree)就是一个lambda表达式，它代表无需传入
任何参数，而run()方法的实现细节为执行一个gui(tree)方法。
2. button.addMouseListener(new MouseListener() {………………})这里是匿名内部类的使用。addMouseListener()方法的方法签名为：
addMouseListener(MouseListener l)，功能是给特定的组件注册事件监听器。需要传入一个MouseListener接口作为参数。MouseListener接口有5个抽象
方法，因此不能使用lambda表达式，只能使用匿名内部类。new MouseListener() {………………}这里是直接把MouseListener的具体实现细节写在了代码块中以实现接口。
反思：
（1）匿名内部类就是在传入一个接口作为参数时，直接就地写出该接口的具体实现细节，而不用专门定义一个实现类去实现这个接口后再进行参数传入。因为是就地临时写
的实现细节，没有专门去定义一个新类，因此没有名字，所以就是匿名的，因为这个类被放在了某个类的方法中的参数括号里运行，所以是内部类。
（2）Java中的匿名内部类和lambda表达式的出现，是借鉴了面向过程编程语言的函数式编程特定风格，在面向对象的语言中保留一些特殊的面向过程语言的特性，可以在
一定程度上优化面向对象语言的短板，提高编程语言的灵活性。类似于社会主义制度的国家引入资本主义制度的市场经济，优化体系结构。
3. JFrame类里面有两个字段：EXIT_ON_CLOSE和DISPOSE_ON_CLOSE。其中EXIT_ON_CLOSE是关闭掉整个程序，而DISPOSE_ON_CLOSE只是关掉当前的窗口。
因此次要的窗口在设置时应该设置成：frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);如果误设成EXIT_ON_CLOSE，那么关掉小窗口会
关掉所有的窗口。

**/


