package com.Note.BinaryTreeWithGUI;

import javax.swing.*;
import java.awt.*;
import static com.Note.BinaryTreeWithGUI.MainSystem.*;

public class WindowsGenerator {
    //方法：对顶层主窗口进行统一的初始化，窗口标题为“title”
    protected static JFrame createMainWindow(String title){
        JFrame frame=new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setSize(600,480);
        frame.setLocation(400,150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }

    //方法：对附属小窗口进行统一的初始化，窗口标题为“title”
    protected static JFrame createSmallWindow(String title){
        JFrame frame=new JFrame(title);
        frame.setLayout(new BorderLayout());
        frame.setSize(360,150);
        frame.setLocation(500,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        return frame;
    }

    //方法：对附属错误提示小窗口进行统一的初始化，窗口标题为“错误”，需传入一个错误提示文本作为参数
    protected static JFrame createErrorWindow(String text){
        JFrame frame=createSmallWindow("错误");
        JLabel lable=new JLabel(text);
        JButton button=new JButton("好的");
        frame.add(lable,BorderLayout.CENTER);
        frame.add(button,BorderLayout.SOUTH);
        button.addActionListener(ee->frame.dispose());
        return frame;
    }

    //方法：对关闭当前窗口的附属保存提示小窗口进行统一的初始化，窗口标题为”提示“
    protected static JFrame createSaveOrNotWindow(BinaryTree tree){
        JFrame frame=createSmallWindow("提示");

        //添加按钮等各个组件，形成上下两个模块
        JLabel label=new JLabel("是否保存数据？");
        JButton button1=new JButton("是");
        JButton button2=new JButton("否");
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(button1);
        panel.add(button2);

        //将两个模块放进windows窗口中
        frame.add(label,BorderLayout.CENTER);
        frame.add(panel,BorderLayout.SOUTH);

        //设置按钮功能
        button1.addActionListener(e->{
            frame.dispose();
            WindowsGenerator.createSaveWindow(tree);
        });
        button2.addActionListener(e->System.exit(0));

        return frame;
    }

    //方法：对保存窗口进行统一的初始化，窗口标题为”保存数据“
    protected static JFrame createSaveWindow(BinaryTree tree){
        JFrame frame= WindowsGenerator.createSmallWindow("保存数据");

        //添加按钮等各个组件
        JLabel label=new JLabel("请输入保存的文件名");
        JTextField textField=new JTextField();
        JButton button1=new JButton("保存");
        JButton button2=new JButton("取消");

        //将文字提示文字和文本域拼装成一个模块，用于放在窗口中间
        JPanel panel1=new JPanel();
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(label);
        panel1.add(textField);

        //将按钮拼装成一个模块，用于放在窗口最下方
        JPanel panel2=new JPanel();
        panel2.setLayout(new GridLayout(1,2));
        panel2.add(button1);
        panel2.add(button2);

        //将创建好的两个模块放进windows窗口中
        frame.add(panel1,BorderLayout.CENTER);
        frame.add(panel2,BorderLayout.SOUTH);

        //设置按钮功能
        button1.addActionListener(ex->{
            String filename=textField.getText().trim();
            if (contentIsValid(filename)) {
                tree.saveToFile(filename);
                frame.dispose();

                //弹出新的窗口提示保存成功
                JFrame smallFrame=WindowsGenerator.createSmallWindow("提示");
                JLabel lable=new JLabel("保存成功");
                JButton button=new JButton("好的");
                smallFrame.add(lable,BorderLayout.CENTER);
                smallFrame.add(button,BorderLayout.SOUTH);
                button.addActionListener(ee->smallFrame.dispose());
            }
            else{
                //自定义错误提示窗口
                WindowsGenerator.createErrorWindow("请输入有效的文件名！");
            }
        });
        button2.addActionListener(ex->frame.dispose());
        return frame;
    }

    //方法：对读取窗口进行统一的初始化，窗口标题为”读取数据“
    protected static JFrame createLoadWindow(BinaryTree tree){
        JFrame frame=WindowsGenerator.createSmallWindow("读取数据");

        //添加按钮等各个组件
        JLabel label=new JLabel("请输入读取的文件名");
        JTextField textField=new JTextField();
        JButton button1=new JButton("读取");
        JButton button2=new JButton("取消");

        //将文字提示文字和文本域拼装成一个模块，用于放在窗口中间
        JPanel panel1=new JPanel();
        panel1.setLayout(new GridLayout(2,1));
        panel1.add(label);
        panel1.add(textField);

        //将按钮拼装成一个模块，用于放在窗口最下方
        JPanel panel2=new JPanel();
        panel2.setLayout(new GridLayout(1,2));
        panel2.add(button1);
        panel2.add(button2);

        //将创建好的两个模块放进windows窗口中
        frame.add(panel1,BorderLayout.CENTER);
        frame.add(panel2,BorderLayout.SOUTH);

        //设置按钮功能
        button1.addActionListener(e -> {
            try {
                String filename = textField.getText().trim();
                if (contentIsValid(filename)) {
                    tree.loadFromFile(filename);
                    frame.dispose();

                    //弹出新的窗口提示读取成功
                    JFrame smallFrame = WindowsGenerator.createSmallWindow("提示");
                    JLabel lable = new JLabel("读取成功");
                    JButton button = new JButton("好的");
                    smallFrame.add(lable, BorderLayout.CENTER);
                    smallFrame.add(button, BorderLayout.SOUTH);
                    button.addActionListener(ee -> smallFrame.dispose());
                } else {
                    //自定义错误提示窗口
                    WindowsGenerator.createErrorWindow("请输入有效的文件名！");
                }
            }catch(Exception ex){
                WindowsGenerator.createErrorWindow("读取失败："+ex.getMessage());
            }
        });
        button2.addActionListener(e->frame.dispose());
        return frame;
    }
}
/*
总结：
1. 要想结束整个程序，可以使用System.exit(0)方法。
 */
