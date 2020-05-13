package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel panel1;
    private JLabel l;

    public MainFrame() {
        setTitle("DES/RSA加密");
        setVisible(true);//设置窗体可见
        /*
         *窗体关闭规则
         *EXIT_ON_CLOSE：点击关闭button程序自动结束
         * DO_NOTHING_ON_CLOSE:无任何操作
         *HIDE_ON_CLOSE：隐藏窗体，但不停止程序
         * DISPOSE_ON_CLOSE:释放窗体资源
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1000,500);
//        setLocation(200,200);
        setBounds(500, 200, 500, 600);

        setContentPane(panel1);//获取窗体容器
        setBackground(Color.white);
        JLabel l = new JLabel(" ");

        add(l);
        remove(l);
        validate();//验证容器组件（刷新）
        setContentPane(panel1);//重新载入容器

        setResizable(false);//窗口规格不可改变
    }

    public static void main(String[] args) {
        MainFrame f = new MainFrame();
    }
}






