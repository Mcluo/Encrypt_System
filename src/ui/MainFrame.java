package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel panel1;
    private JLabel l;

    public MainFrame() {
        setTitle("DES/RSA����");
        setVisible(true);//���ô���ɼ�
        /*
         *����رչ���
         *EXIT_ON_CLOSE������ر�button�����Զ�����
         * DO_NOTHING_ON_CLOSE:���κβ���
         *HIDE_ON_CLOSE�����ش��壬����ֹͣ����
         * DISPOSE_ON_CLOSE:�ͷŴ�����Դ
         */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1000,500);
//        setLocation(200,200);
        setBounds(500, 200, 500, 600);

        setContentPane(panel1);//��ȡ��������
        setBackground(Color.white);
        JLabel l = new JLabel(" ");

        add(l);
        remove(l);
        validate();//��֤���������ˢ�£�
        setContentPane(panel1);//������������

        setResizable(false);//���ڹ�񲻿ɸı�
    }

    public static void main(String[] args) {
        MainFrame f = new MainFrame();
    }
}






