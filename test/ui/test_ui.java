package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
/**
 * JTextField jt = new JTextField(int columns);//���е�ָ�������Ĳ�������û��ʲô��
 * @author 18465
 *
 */
public class test_ui {
	@Disabled
	@Test
	public void testJTextField() {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setTitle("MD5");
		jf.setSize(400, 400);
		jf.setLayout(new BorderLayout());
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(3);
		jf.setResizable(false);
		jf.setBackground(Color.DARK_GRAY);
	    java.awt.FlowLayout f1=new java.awt.FlowLayout();
	    jf.setLayout(f1);
		JTextField jt = new JTextField(30);
		jf.add(jt);
		while(jf.isVisible()){
			 try{
			  Thread.sleep(1000);//��ѭ���н���CPUռ��
			 } catch(Exception e){
			  e.printStackTrace();
			 }
			}
	}
	@Test
	public void test_MainFrame() {
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		while(mf.isVisible()){
			 try{
			  Thread.sleep(1000);//��ѭ���н���CPUռ��
			 } catch(Exception e){
			  e.printStackTrace();
			 }}
	}
}
