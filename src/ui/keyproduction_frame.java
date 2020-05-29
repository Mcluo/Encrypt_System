package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import encrypt_algo.RSA;
import fileIO.File_Obj;
import fileIO.WriteFile;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class keyproduction_frame extends JDialog{
	private JTextField p_text;
	private JTextField q_text;
	private JTextField key_text;
	private JTextField createdp_text;
	private JTextField createdq_text;
	private JTextField e_text;
	private JTextField d_text;
	private int key_length;
	private JTextField n_text;
	private int eisoutput,disoutput,nisoutput =0;
	public keyproduction_frame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				 int i=JOptionPane.showConfirmDialog(null, "确定要退出吗？", "退出界面", JOptionPane.YES_NO_OPTION);
				 if(i==JOptionPane.YES_OPTION){
				 System.exit(0);
				 }
			}
		});
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(570, 558));
		setTitle("\u5BC6\u94A5\u5BF9\u7684\u4EA7\u751F");
		RSA rsa = new RSA();
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u957F\u5EA6\u8BBE\u5B9A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u4EA7\u751F\u7D20\u6570p", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton Createp_btn = new JButton("\u751F\u6210");
		Createp_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p_length = Integer.parseInt(p_text.getText()); 
				rsa.findp(p_length);
				createdp_text.setText(rsa.getp());//十六进制表示大素数p
			}
		});
		JLabel e_lable = new JLabel("\u516C\u94A5e");
		JLabel d_lable = new JLabel("\u79C1\u94A5d");
		createdp_text = new JTextField();
		createdp_text.setEditable(false);
		createdp_text.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(Createp_btn)
					.addGap(73)
					.addComponent(createdp_text, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(createdp_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Createp_btn))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		JLabel n_lable = new JLabel("\u6A21\u6570n");
		JButton inputkey_Btn = new JButton("\u5BFC\u5165\u5BC6\u94A5");
		inputkey_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (encrypt_frame.RSA_rBtn.isSelected()&&encrypt_frame.RSA_rBtn.isEnabled()) {
				encrypt_frame.RSAkey_text.setText(e_text.getText());
				File_Obj.e = rsa.gete();
//				File_Obj.d = rsa.getd();
//				File_Obj.n = rsa.getn();
				encrypt_frame.RSAkey_Lab.setText("公钥("+rsa.getE().length()+")");
//				encrypt_frame.n_Lab.setText("模数("+rsa.getN().length()+")");
			}
				else if(!encrypt_frame.RSA_rBtn.isSelected()&&encrypt_frame.RSA_rBtn.isEnabled())
					JOptionPane.showMessageDialog(inputkey_Btn, "请先在加/解密界面选择加密模式！");
				else{
					encrypt_frame.RSAkey_text.setText(d_text.getText());
					File_Obj.d = rsa.getd();
					encrypt_frame.RSAkey_Lab.setText("私钥("+rsa.getD().length()+")");
				}
			}
		});
		
		JButton finish_Btn = new JButton("\u5B8C\u6210");
		finish_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nisoutput==0) 
					JOptionPane.showMessageDialog(finish_Btn, "模数还未保存，请先保存！");
					else if(eisoutput==0) 
						JOptionPane.showMessageDialog(finish_Btn, "公钥还未保存，请先保存！");
						else if(disoutput==0)
							JOptionPane.showMessageDialog(finish_Btn, "私钥还未保存，请先保存！");
				else {
				int confirm = JOptionPane.showConfirmDialog(finish_Btn, "提示", "确认密钥生成完成?", JOptionPane.YES_NO_OPTION); 
				if(confirm == JOptionPane.OK_OPTION)
					dispose();
			}
				}
		});
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u4EA7\u751F\u7D20\u6570q", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JButton Createq_btn = new JButton("\u751F\u6210");
		Createq_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int q_length = Integer.parseInt(q_text.getText());
			    rsa.findq(q_length);
				createdq_text.setText(rsa.getq());
			}
		});
		
		createdq_text = new JTextField();
		createdq_text.setEditable(false);
		createdq_text.setColumns(10);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 464, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(Createq_btn)
					.addGap(73)
					.addComponent(createdq_text, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 55, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(createdq_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Createq_btn))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
		n_text = new JTextField();
		n_text.setEditable(false);
		n_text.setColumns(10);
		JButton keyproduction_Btn = new JButton("\u751F\u6210\u5BC6\u94A5\u5BF9");
		keyproduction_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rsa.keypro();
				System.out.println(rsa.gete().toString(16).length());
				System.out.println(rsa.getd().toString(16).length());
				e_text.setText(rsa.gete().toString(16));
				d_text.setText(rsa.getd().toString(16));
				n_text.setText(rsa.getN());
				e_lable.setText("公钥e("+rsa.gete().toString(16).length()+")");
				d_lable.setText("私钥d("+rsa.getd().toString(16).length()+")");
				n_lable.setText("模数n("+rsa.getN().length()+")");
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u5BC6\u94A5\u5BF9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton outputn_Btn = new JButton("\u5BFC\u51FA");
		outputn_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nisoutput = 1;
				JFileChooser estorage = new JFileChooser(File_Obj.getProjectpath()+"\\key");			
				int returnVal = estorage.showOpenDialog(null);
				if (returnVal==estorage.APPROVE_OPTION) {
					WriteFile outputn = new WriteFile();
					try {
						outputn.createFile(estorage.getSelectedFile().toString(), rsa.getn().toByteArray());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				}
		});
		

		

		GroupLayout gl_panel_1_1_1 = new GroupLayout(panel_1_1_1);
		gl_panel_1_1_1.setHorizontalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(n_lable)
					.addGap(55)
					.addComponent(n_text, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(outputn_Btn)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1_1_1.setVerticalGroup(
			gl_panel_1_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1_1_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(n_lable)
						.addComponent(n_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(outputn_Btn))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1_1_1.setLayout(gl_panel_1_1_1);
		
		JButton inputn_Btn = new JButton("\u5BFC\u5165\u6A21\u6570");
		inputn_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Obj.n = rsa.getn();
				encrypt_frame.n_Lab.setText("模数("+rsa.getN().length()+")");
				encrypt_frame.n_text.setText(rsa.getN());
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(55)
							.addComponent(inputn_Btn)
							.addGap(64)
							.addComponent(inputkey_Btn)
							.addPreferredGap(ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
							.addComponent(finish_Btn)
							.addGap(98))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(86, Short.MAX_VALUE))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(118)
					.addComponent(keyproduction_Btn, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(224, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1_1_1, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(keyproduction_Btn)
					.addGap(18)
					.addComponent(panel_1_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(finish_Btn)
						.addComponent(inputn_Btn)
						.addComponent(inputkey_Btn))
					.addContainerGap())
		);
		

		
		e_text = new JTextField();
		e_text.setEditable(false);
		e_text.setColumns(10);
		

		
		d_text = new JTextField();
		d_text.setEditable(false);
		d_text.setColumns(10);
		
		JButton outpute_Btn_1 = new JButton("\u5BFC\u51FA");
		outpute_Btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eisoutput = 1;
				JFileChooser estorage = new JFileChooser(File_Obj.getProjectpath()+"\\key");			
				int returnVal = estorage.showOpenDialog(null);
				if (returnVal==estorage.APPROVE_OPTION) {
					WriteFile outputn = new WriteFile();
					try {
						outputn.createFile(estorage.getSelectedFile().toString(), rsa.gete().toByteArray());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JButton outputd_Btn_1 = new JButton("\u5BFC\u51FA");
		outputd_Btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disoutput = 1;
				JFileChooser estorage = new JFileChooser(File_Obj.getProjectpath()+"\\key");			
				int returnVal = estorage.showOpenDialog(null);
				if (returnVal==estorage.APPROVE_OPTION) {
					WriteFile outputn = new WriteFile();
					try {
						outputn.createFile(estorage.getSelectedFile().toString(), rsa.getd().toByteArray());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(e_lable)
						.addComponent(d_lable))
					.addGap(52)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(e_text, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
						.addComponent(d_text, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(outpute_Btn_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addComponent(outputd_Btn_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(e_lable)
						.addComponent(e_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(outpute_Btn_1))
					.addGap(21)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(d_lable)
						.addComponent(d_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(outputd_Btn_1))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblp = new JLabel("\u7D20\u6570p");
		lblp.setFont(new Font("宋体", Font.PLAIN, 14));
		
		JLabel lblq = new JLabel("\u7D20\u6570q");
		lblq.setFont(new Font("宋体", Font.PLAIN, 14));
		
		p_text = new JTextField();
		p_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(isNumeric(p_text.getText())) {
					if(q_text.getText().equals(""))
						key_length=Integer.parseInt(p_text.getText());
					else 
					key_length=Integer.parseInt(p_text.getText())+Integer.parseInt(q_text.getText());
					key_text.setText(String.valueOf(key_length));
				}
			}
		});
		p_text.addFocusListener(new FocusAdapter() {

		});
		
		p_text.setColumns(10);
		
		q_text = new JTextField();
		q_text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(isNumeric(q_text.getText())) {
					if(p_text.getText().equals(""))
						key_length=Integer.parseInt(q_text.getText());
					else 
					key_length=Integer.parseInt(p_text.getText())+Integer.parseInt(q_text.getText());
					key_text.setText(String.valueOf(key_length));
				}
			}
		});
		q_text.setColumns(10);
		
		JLabel lblq_1 = new JLabel("\u5BC6\u94A5");
		
		key_text = new JTextField();
		key_text.setEditable(false);
		key_text.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("(\u5C0F\u4E8E\u7B49\u4E8Ep,q\u957F\u5EA6\u4E4B\u548C)");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(p_text, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblq, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(q_text, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblq_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(key_text, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel)
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblp)
						.addComponent(p_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblq)
						.addComponent(q_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblq_1)
						.addComponent(key_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}
	public static boolean isNumeric(String str) {//利用正则判断字符串是否是数字
		  Pattern pattern =Pattern.compile("[0-9]*");
		  return pattern.matcher(str).matches();
		}
}
