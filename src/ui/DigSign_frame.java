package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileView;

import encrypt_algo.*;
import fileIO.*;

import javax.swing.LayoutStyle.ComponentPlacement;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.awt.event.ActionEvent;

public class DigSign_frame extends JFrame {
	public static JTextField inputFile,outputFile,Tolerantpath_text,RSAKey_text,n_text;
	public static JTextArea textPane,textPane_1,origin_textPane,crypt_textPane;
	public static JRadioButton md5_Btn, sha_Btn, veri_Btn,fileSign_rBtn,stringSign_rBtn;
	public static int Mode;
	public static Button stringcrypt_Btn;
	public static JCheckBox TolerantPath_Box;
	public static JLabel RSAkey_Lab, n_Lab;
	public static JPanel panel,panel_5;
	public static String selectedFilename;
	public static boolean modal = true;
	
	public JLabel LblNewLabel_1_1,lblNewLabel_1;
	public JButton Sign_Btn,read_Btn,DirectorySelect_Tolerant,DirectorySelect_output;
	
	private JButton button;	
	private JButton Verify_Btn;
	private JScrollPane scrollPane,scrollPane_1,scrollPane_2,scrollPane_3;
	private String origin_text,crypt_text;
	
	protected String MSignString,MHashString;
	protected byte[] M,C,MSign,MHash;
	
	public DigSign_frame() {
		setLocation(new Point(90, 90));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DigSign_frame.class.getResource("/images/\u7B7E\u540D.png")));
		setResizable(false);
		getContentPane().setName("panel1");
		setSize(new Dimension(594, 587));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "确定要退出界面吗？", "退出界面", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					if(encrypt_frame.modal==false)
						encrypt_frame.modal=true;
					modal = true;
					dispose();
				}
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
					if (modal) {
						requestFocus();
						}
				}
		});
		read_Btn = new JButton("\u8BFB\u53D6");
		read_Btn.setBounds(95, 512, 63, 27);
		read_Btn.setEnabled(false);
		Sign_Btn = new JButton("\u7B7E\u540D");
		Sign_Btn.setBounds(14, 512, 63, 27);
		Sign_Btn.setEnabled(false);
		Sign_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Obj fo = new File_Obj();
				try {
					textPane.setText("");
					fo.SignBtn();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		Verify_Btn = new JButton("\u9A8C\u8BC1");
		Verify_Btn.setBounds(176, 512, 63, 27);
		Verify_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Obj fo = new File_Obj();
				try {
					textPane_1.setText("");
					fo.VeriBtn();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		Verify_Btn.setEnabled(false);

		JButton createkey_Btn = new JButton("\u4EA7\u751FRSA\u5BC6\u94A5\u5BF9");
		createkey_Btn.setBounds(247, 512, 131, 27);
		createkey_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyproduction_frame f = new keyproduction_frame();
				f.setVisible(true);
			}
		});

		JButton exit_Btn = new JButton("\u8DF3\u8F6C\u52A0\u89E3\u5BC6");
		exit_Btn.setBounds(477, 512, 107, 27);
		exit_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(encrypt_frame.modal==false) {
					encrypt_frame.modal=true;
					dispose();
				}
				else if (encrypt_frame.modal==true) {
					encrypt_frame frame = new encrypt_frame();
					frame.setVisible(false);
					Object[] option_1 = {"加密","解密"};
					int Option_1 = JOptionPane.showOptionDialog(null, "请选择要继续进行的操作", "提示", JOptionPane.YES_NO_CANCEL_OPTION,
						 	JOptionPane.PLAIN_MESSAGE, null, option_1, option_1[0]);
					if(Option_1 == 0) {
				 if(stringSign_rBtn.isSelected()){                           //2
					 Object[] option = { "签名信息", "原信息" ,"直接跳转"};
					 	int Option = JOptionPane.showOptionDialog(null, "请选择需要继续签名的内容", "提示", JOptionPane.YES_NO_CANCEL_OPTION,
					 	JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
				 	frame.string_rBtn.setSelected(true);
				 	encrypt_frame.panel.setVisible(false);
				 	encrypt_frame.panel_5.setVisible(true);
				 	frame.stringcrypt_Btn.setLabel("＞");
					if (Option == 0){
				 		if((crypt_text = crypt_textPane.getText()).equals("")) {
				 			JOptionPane.showMessageDialog(null,"未读取到加密信息！");
				 			frame.dispose();
				 		}
				 		else{
				       		DigSign_frame.origin_textPane.setText(crypt_text);
				       		frame.setVisible(true);
				       		modal = false;
				 }
				 }
				 	else if (Option == 1){
				  	if((origin_text = origin_textPane.getText()).equals("")) {
				 			JOptionPane.showMessageDialog(null,"未读取到原信息！");
				 			frame.dispose();
				  	}
				  	else {
				  		DigSign_frame.origin_textPane.setText(origin_text);
				  		frame.setVisible(true);
				  		modal = false;
				  	}
				  	}
				  	else
				  		frame.setVisible(true);
					modal = false;
				 }
				 else if(fileSign_rBtn.isSelected())                      //2
				  	{
				  		//TODO:文件操作
				  		frame.file_rBtn.setSelected(true);
					 	encrypt_frame.panel.setVisible(true);
					 	encrypt_frame.panel_5.setVisible(false);
					 	frame.lblNewLabel_1.setEnabled(false);
					 	frame.OutputFile.setEnabled(false);
					 	frame.DirectorySelect_output.setEnabled(false);
					 	encrypt_frame.TolerantPath_Box.setEnabled(false);
					 	frame.lblNewLabel_1_1.setEnabled(false);
						encrypt_frame.TolerantPath_text.setEditable(false);
						frame.DirectorySelect_Tolerant.setEnabled(false);
						frame.setVisible(true);
					 	modal = false;
				  }
				 else
					 encrypt_frame.run();
				}
				}
			}
		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(14, 189, 570, 116);
		panel_2.setBorder(new TitledBorder(null, "RSA\u5BC6\u94A5\u5BF9\u7684\u9009\u62E9", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		n_Lab = new JLabel("\u6A21\u6570");
		n_Lab.setFont(new Font("宋体", Font.PLAIN, 12));

		RSAkey_Lab = new JLabel("\u5BC6\u94A5");
		RSAkey_Lab.setFont(new Font("宋体", Font.PLAIN, 12));

		RSAKey_text = new JTextField();
		RSAKey_text.setEditable(false);
		RSAKey_text.setColumns(10);

		JButton importkey_btn = new JButton("\u5BFC\u5165");
		importkey_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser read = new JFileChooser(File_Obj.getProjectpath() + "\\key");
				read.setFileView(new FileView() {
					public Icon getIcon(File f) {
						return read.getFileSystemView().getSystemIcon(f);
					}
				});
				int returnVal = read.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ReadFile input = new ReadFile();
					try {
						File selectedfile = read.getSelectedFile();
						String name = selectedfile.getName();
						if (name.contains("e")) {
							byte[] keybyte = input.getContent(read.getSelectedFile().toString());
							BigInteger Key = new BigInteger(keybyte);
							RSAKey_text.setText(Key.toString(16));
							DigSign_frame.RSAkey_Lab.setText("公钥(" + Key.toString(16).length() + ")");
						} else if (name.contains("d")) {
							byte[] keybyte = input.getContent(read.getSelectedFile().toString());
							BigInteger Key = new BigInteger(keybyte);
							RSAKey_text.setText(Key.toString(16));
							DigSign_frame.RSAkey_Lab.setText("私钥(" + Key.toString(16).length() + ")");
						}
						else
							JOptionPane.showMessageDialog(null, "导入的不是密钥文件,导入失败!");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		n_text = new JTextField();
		n_text.setEditable(false);
		n_text.setColumns(10);

		JButton importn_btn = new JButton("\u5BFC\u5165");
		importn_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser read = new JFileChooser(File_Obj.getProjectpath() + "\\key");
				read.setFileView(new FileView() {
					public Icon getIcon(File f) {
						return read.getFileSystemView().getSystemIcon(f);
					}
				});
				int returnVal = read.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					ReadFile input = new ReadFile();
					try {
						byte[] nbyte = input.getContent(read.getSelectedFile().toString());
						BigInteger N = new BigInteger(nbyte);
						n_text.setText(N.toString(16));
						DigSign_frame.n_Lab.setText("模数n(" + N.toString(16).length() + ")");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 484, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
								.addComponent(n_Lab, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(RSAkey_Lab, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 60,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(RSAKey_text, GroupLayout.PREFERRED_SIZE, 300,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(importkey_btn))
								.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(n_text, GroupLayout.PREFERRED_SIZE, 300,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(importn_btn, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGap(0, 116, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(10)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(RSAKey_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(importkey_btn)
								.addComponent(RSAkey_Lab, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(n_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(importn_btn)
								.addComponent(n_Lab, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_2.setLayout(gl_panel_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(14, 318, 570, 83);
		panel_1.setBorder(new TitledBorder(null, "\u9009\u9879", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(14, 408, 266, 97);
		panel_3.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6458\u8981\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(298, 408, 265, 97);
		panel_3_1.setBorder(
				new TitledBorder(null, "\u9A8C\u8BC1\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		read_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Obj fo = new File_Obj();
				try {
					textPane.setText("");
					fo.read_Btn();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		button = new JButton("\u91CD\u7F6E");
		button.setBounds(406, 512, 63, 27);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputFile.setText("");
				outputFile.setText("");
				RSAKey_text.setText("");
				n_text.setText("");
				textPane.setText("");
				textPane_1.setText("");
				md5_Btn.setEnabled(true);
				sha_Btn.setEnabled(true);
				veri_Btn.setEnabled(true);
			}
		});
	

		md5_Btn = new JRadioButton("MD5");
		md5_Btn.setBounds(196, 35, 59, 27);
		md5_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!veri_Btn.isSelected()) {
					Verify_Btn.setEnabled(false);
					read_Btn.setEnabled(false);
					Sign_Btn.setEnabled(true);
				}
			}
		});
		md5_Btn.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		sha_Btn = new JRadioButton("SHA");
		sha_Btn.setBounds(288, 35, 76, 27);
		sha_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!veri_Btn.isSelected()) {
					Verify_Btn.setEnabled(false);
					read_Btn.setEnabled(false);
					Sign_Btn.setEnabled(true);
				}
			}
		});
		JButton clear_Btn_1 = new JButton("\u6E05\u7A7A");
		JButton confirm_Btn_1 = new JButton("\u786E\u8BA4");
		JButton clear_Btn = new JButton("\u6E05\u7A7A");
		JButton confirm_Btn = new JButton("\u786E\u8BA4");
		stringcrypt_Btn = new Button("\uFF1C\uFF1E");
		sha_Btn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		veri_Btn = new JRadioButton("\u9A8C\u8BC1");
		veri_Btn.setBounds(370, 35, 59, 27);
		veri_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (veri_Btn.isSelected()) {
					stringcrypt_Btn.setLabel("＜");
					clear_Btn_1.setVisible(true);
					confirm_Btn_1.setVisible(true);
					clear_Btn.setVisible(false);
					confirm_Btn.setVisible(false);
					Verify_Btn.setEnabled(true);
					read_Btn.setEnabled(true);
					Sign_Btn.setEnabled(false);
					lblNewLabel_1.setEnabled(false);
					outputFile.setEnabled(false);
					DirectorySelect_output.setEnabled(false);
					TolerantPath_Box.setEnabled(false);
					LblNewLabel_1_1.setEnabled(false);
					Tolerantpath_text.setEditable(false);
					DirectorySelect_Tolerant.setEnabled(false);
				} else {
					stringcrypt_Btn.setLabel("＞");
					clear_Btn_1.setVisible(false);
					confirm_Btn_1.setVisible(false);
					clear_Btn.setVisible(true);
					confirm_Btn.setVisible(true);
					Verify_Btn.setEnabled(false);
					read_Btn.setEnabled(false);
					Sign_Btn.setEnabled(true);
					lblNewLabel_1.setEnabled(true);
					outputFile.setEnabled(true);
					DirectorySelect_output.setEnabled(true);
					TolerantPath_Box.setEnabled(true);
					LblNewLabel_1_1.setEnabled(true);
					Tolerantpath_text.setEditable(true);
					DirectorySelect_Tolerant.setEnabled(true);
				}
			}
		});
		ButtonGroup group = new ButtonGroup();
		getContentPane().setLayout(null);
		group.add(md5_Btn);
		group.add(sha_Btn);

		veri_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		panel_5 = new JPanel();
		fileSign_rBtn = new JRadioButton("\u6587\u4EF6\u7B7E\u540D");
		fileSign_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_5.setVisible(false);
			}
		});
		fileSign_rBtn.setBounds(62, 20, 89, 27);
		fileSign_rBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		stringSign_rBtn = new JRadioButton("\u5B57\u7B26\u4E32\u7B7E\u540D");
		stringSign_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_5.setVisible(true);
				if (!veri_Btn.isSelected()) 
					stringcrypt_Btn.setLabel("＞");
			}
		});
		ButtonGroup group_1 = new ButtonGroup();
		group_1.add(fileSign_rBtn);
		group_1.add(stringSign_rBtn);
		
		stringSign_rBtn.setBounds(62, 47, 103, 27);
		stringSign_rBtn.setFont(new Font("宋体", Font.PLAIN, 15));
		panel_1.setLayout(null);
		panel_1.add(fileSign_rBtn);
		panel_1.add(stringSign_rBtn);
		panel_1.add(md5_Btn);
		panel_1.add(sha_Btn);
		panel_1.add(veri_Btn);
		getContentPane().add(panel_1);
		
		crypt_textPane = new JTextArea();
		JPanel panel_6_1 = new JPanel();
		panel_6_1.add(crypt_textPane);
		
		
		JPanel panel_6 = new JPanel();
		origin_textPane = new JTextArea();
		panel_6.add(origin_textPane);
		getContentPane().add(panel_2);
		getContentPane().add(Sign_Btn);
		getContentPane().add(read_Btn);
		getContentPane().add(Verify_Btn);
		getContentPane().add(createkey_Btn);
		getContentPane().add(button);
		getContentPane().add(exit_Btn);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		textPane = new JTextArea();
		textPane.setEditable(false);
		panel_3.add(textPane);
		scrollPane_2 = new JScrollPane(textPane);
		scrollPane_2.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane_2.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_2.setBounds(14, 23, 238, 61);
		panel_3.add(scrollPane_2);
		

		textPane.setBounds(14, 23, 238, 61);

		getContentPane().add(panel_3_1);
		panel_3_1.setLayout(null);
		textPane_1 = new JTextArea();
		textPane_1.setEditable(false);
		panel_3_1.add(textPane_1);
		scrollPane_3 = new JScrollPane(textPane_1);
		scrollPane_3.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane_3.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_3.setBounds(14, 24, 237, 60);
		panel_3_1.add(scrollPane_3);
		
		
		textPane_1.setBounds(14, 24, 237, 60);
		
		

		panel_5.setBounds(14, 13, 556, 171);
		getContentPane().add(panel_5);
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(null, "\u5B57\u7B26\u4E32\u8F93\u5165/\u8F93\u51FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
				
				confirm_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(confirm_Btn.getText().equals("确认")) {
							origin_textPane.setEnabled(false);
							confirm_Btn.setText("取消");
							}
							else if(confirm_Btn.getText().equals("取消")) {
								origin_textPane.setEnabled(true);
								confirm_Btn.setText("确认");
							}
					}
				});
				confirm_Btn.setBounds(72, 131, 76, 27);
				panel_5.add(confirm_Btn);
				
				
				clear_Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						origin_textPane.setText("");
					}
				});
				clear_Btn.setBounds(166, 131, 76, 27);
				panel_5.add(clear_Btn);
				
				
				confirm_Btn_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(confirm_Btn_1.getText().equals("确认")) {
							crypt_textPane.setEnabled(false);
							confirm_Btn_1.setText("取消");
							}
							else if(confirm_Btn_1.getText().equals("取消")) {
								crypt_textPane.setEnabled(true);
								confirm_Btn_1.setText("确认");
							}
					}
				});
				confirm_Btn_1.setBounds(304, 131, 76, 27);
				panel_5.add(confirm_Btn_1);
				
				
				clear_Btn_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						crypt_textPane.setText("");
					}
				});
				clear_Btn_1.setBounds(394, 131, 76, 27);
				panel_5.add(clear_Btn_1);
				
				
				panel_6.setLayout(null);
				panel_6.setBorder(new TitledBorder(null, "\u539F\u5B57\u7B26\u4E32", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_6.setBounds(26, 18, 234, 109);
				panel_5.add(panel_6);
				
				
				origin_textPane.setSize(new Dimension(176, 79));
				origin_textPane.setAlignmentX(0.0f);
				origin_textPane.setBounds(14, 22, 206, 74);
				
				scrollPane = new JScrollPane(origin_textPane);
				scrollPane.setBounds(14, 23, 206, 73);
				scrollPane.setHorizontalScrollBarPolicy(
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scrollPane.setVerticalScrollBarPolicy(
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

				panel_6.add(scrollPane);

				
				
				panel_6_1.setLayout(null);
				panel_6_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7B7E\u540D\u5B57\u7B26\u4E32", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_6_1.setBounds(294, 18, 225, 109);
				panel_5.add(panel_6_1);
				
				
				crypt_textPane.setSize(new Dimension(176, 79));
				crypt_textPane.setAlignmentX(0.0f);
				crypt_textPane.setBounds(14, 22, 197, 74);
				
				scrollPane_1 = new JScrollPane(crypt_textPane);
				scrollPane_1.setHorizontalScrollBarPolicy(
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						scrollPane_1.setVerticalScrollBarPolicy(
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollPane_1.setBounds(14, 22, 197, 74);
				panel_6_1.add(scrollPane_1);
			
				stringcrypt_Btn.addActionListener(new ActionListener() {


					public void actionPerformed(ActionEvent e) {
						if(stringcrypt_Btn.getLabel().equals("＞")) {
							if(confirm_Btn.getText().equals("确认"))
								JOptionPane.showMessageDialog(stringcrypt_Btn, "请先确认输入的明文字符串!");
							else {
							confirm_Btn.setVisible(true);
							clear_Btn.setVisible(true);
							confirm_Btn_1.setVisible(false);
							clear_Btn_1.setVisible(false);
							if((origin_text = origin_textPane.getText()).equals(""))
								JOptionPane.showMessageDialog(stringcrypt_Btn, "未读取到明文字符串!");
								else {
							M = origin_text.getBytes();
							
							if(!md5_Btn.isSelected()&&!sha_Btn.isSelected())
								JOptionPane.showMessageDialog(null, "请先选择签名算法！");
							else {
								int flag = 0;
							 if(md5_Btn.isSelected()) {
								 //TODO:md5算法实现
								 MD5 md5 = new MD5();
								 md5.digest(M);
								 MHash = md5.outbyteArray();
								 MHashString = md5.outString();
								 textPane.setText(MHashString);
							 }
							 else if (sha_Btn.isSelected()) {
								 //TODO:sha算法实现
								 SHA sha = new SHA();
									String MString = File_Obj.toStringMethod(M);
									sha.encrypt(MString);
									MHash = sha.toByteArray();
									MHashString = sha.toString();
									textPane.setText(MHashString);
							 }
								if(RSAKey_text.getText().equals("")) {
									 JOptionPane.showMessageDialog(null, "未输入密钥！");
									 flag=1;
								}
								else if(n_text.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "未输入模数！");
									flag=1;
								}
								else {
									if(RSAkey_Lab.getText().contains("公"))
										JOptionPane.showMessageDialog(null, "请用私钥签名！");
									else {
								RSA rsa = new RSA();
								BigInteger Mb = new BigInteger(MHash);
								String ntext,etext;
								ntext = n_text.getText();
								etext = RSAKey_text.getText();
								BigInteger n = new BigInteger(ntext,16);
								BigInteger eb = new BigInteger(etext,16);
								rsa.sete(eb);
								rsa.setn(n);
								BigInteger Cb = rsa.encode(Mb);
								MSignString = File_Obj.toStringMethod(Cb.toByteArray()).toUpperCase();
							}
								if(flag==0)
									crypt_textPane.setText(MSignString);
								}
							}
								}}}

						else if(stringcrypt_Btn.getLabel().equals("＜")) {					
							if(confirm_Btn_1.getText().equals("确认"))
								JOptionPane.showMessageDialog(stringcrypt_Btn, "请先确认输入的密文字符串!");
							else {
							confirm_Btn.setVisible(false);
							clear_Btn.setVisible(false);
							confirm_Btn_1.setVisible(true);
							clear_Btn_1.setVisible(true);
							if((crypt_text = crypt_textPane.getText()).equals(""))
								JOptionPane.showMessageDialog(stringcrypt_Btn, "未读取到密文字符串!");
							else {
								C = File_Obj.StringToByteArray(crypt_text);
								int flag = 0;
								if(!md5_Btn.isSelected()&&!sha_Btn.isSelected())
									JOptionPane.showMessageDialog(null, "请先选择签名算法！");
								else {
								 if(RSAKey_text.getText().equals("")) {
									 JOptionPane.showMessageDialog(null, "未输入密钥！");
									 flag=1;
								}
								else if(n_text.getText().equals("")) {
									JOptionPane.showMessageDialog(null, "未输入模数！");
									flag=1;
								}
								else {
									if(RSAkey_Lab.getText().contains("私"))
										JOptionPane.showMessageDialog(null, "请用公钥验证签名！");
									else {
									RSA rsa = new RSA();
									String ntext,dtext;
									BigInteger Cb = new BigInteger(C);
									ntext = n_text.getText();
									dtext = RSAKey_text.getText();
									BigInteger n = new BigInteger(ntext,16);
									BigInteger db = new BigInteger(dtext,16);
									rsa.setd(db);
									rsa.setn(n);
									BigInteger Mb = rsa.decode(Cb);
									origin_text = "";
									if(md5_Btn.isSelected()) {
									for (int i = 0; i < 32; i++)
										origin_text += Integer.toHexString(0xFF & (int) Mb.toByteArray()[i]).toUpperCase();// 大写输出字母
								}
								 else if(sha_Btn.isSelected())
									 for (int i = 0; i < 40; i++)
											origin_text += Integer.toHexString(0xFF & (int) Mb.toByteArray()[i]).toUpperCase();// 大写输出字母
								textPane_1.setText(origin_text);
								if(textPane_1.getText().equals(textPane.getText()))
									JOptionPane.showMessageDialog(null, "验证通过！");
								else
									JOptionPane.showMessageDialog(null, "验证不通过！");
							}}
						}
							}}}
					}});
				stringcrypt_Btn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
				stringcrypt_Btn.setBounds(258, 59, 40, 25);
				panel_5.add(stringcrypt_Btn);
				getContentPane().add(panel_5);
				panel = new JPanel();
				panel.setBounds(14, 13, 570, 163);
				getContentPane().add(panel);
				panel.setLayout(null);
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"\u6570\u5B57\u7B7E\u540D\u6587\u4EF6\u8F93\u5165/\u8F93\u51FA", TitledBorder.LEADING, TitledBorder.TOP,
						null, new Color(0, 0, 0)));
				
						JLabel lblNewLabel = new JLabel("\u8F93\u5165\u6587\u4EF6");
						lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
						lblNewLabel.setBounds(20, 38, 48, 15);
						panel.add(lblNewLabel);
						inputFile = new JTextField(10);
						inputFile.setBounds(74, 33, 301, 24);
						panel.add(inputFile);
						TolerantPath_Box = new JCheckBox("\u8F93\u51FA\u6587\u4EF6\u7684\u9ED8\u8BA4\u8DEF\u5F84");
						JButton FileBrowse = new JButton("\u6D4F\u89C8\u6587\u4EF6", null);
						FileBrowse.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JFileChooser fc1 = new JFileChooser(File_Obj.getProjectpath() + "\\file");
								fc1.setFileView(new FileView() {
									public Icon getIcon(File f) {
										return fc1.getFileSystemView().getSystemIcon(f);
									}
								});
								int returnVal = fc1.showOpenDialog(null);
								if (returnVal == JFileChooser.APPROVE_OPTION) {
									// 正常选择文件
									File selectedFile = fc1.getSelectedFile();
									selectedFilename = selectedFile.getName();
									inputFile.setText(fc1.getSelectedFile().toString());
									String oldPath = new String(fc1.getSelectedFile().toString());
									StringBuffer OldPath = new StringBuffer(oldPath);
									String prefix = oldPath.substring(oldPath.lastIndexOf("."));
									String newPath = new String();
									if (prefix.equals(".md5") || (prefix.equals(".sha"))) {
										Mode = 1;
										Sign_Btn.setEnabled(false);
										Verify_Btn.setEnabled(true);
										read_Btn.setEnabled(true);
										newPath = OldPath.delete(oldPath.lastIndexOf("."), oldPath.length()).toString();
									} else {
										if (md5_Btn.isSelected())
											newPath = OldPath.append(".md5").toString();
										if (sha_Btn.isSelected())
											newPath = OldPath.append(".sha").toString();
										Mode = 0;// 判断是加密
										md5_Btn.setEnabled(true);
										sha_Btn.setEnabled(true);
										Sign_Btn.setEnabled(true);
										Verify_Btn.setEnabled(false);
									}
									ReadFile f1 = new ReadFile();
									try {
										byte[] M = f1.getContent(fc1.getSelectedFile().toString());
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									if(!TolerantPath_Box.isSelected())
									outputFile.setText(newPath);
								} 
							}
						});
						
								FileBrowse.setFont(new Font("宋体", Font.PLAIN, 12));
								FileBrowse.setBounds(383, 34, 87, 23);
								panel.add(FileBrowse);
								
										
										TolerantPath_Box.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												if (TolerantPath_Box.isSelected()) {
													LblNewLabel_1_1.setEnabled(true);
													Tolerantpath_text.setEnabled(true);
													DirectorySelect_Tolerant.setEnabled(true);
													lblNewLabel_1.setEnabled(false);
													outputFile.setEnabled(false);
													DirectorySelect_output.setEnabled(false);
												} else {
													LblNewLabel_1_1.setEnabled(false);
													Tolerantpath_text.setEnabled(false);
													DirectorySelect_Tolerant.setEnabled(false);
													lblNewLabel_1.setEnabled(true);
													outputFile.setEnabled(true);
													DirectorySelect_output.setEnabled(true);
												}
											}
										});
										TolerantPath_Box.setFont(new Font("宋体", Font.PLAIN, 12));
										TolerantPath_Box.setBounds(20, 97, 137, 25);
										panel.add(TolerantPath_Box);
										
												lblNewLabel_1 = new JLabel("\u8F93\u51FA\u6587\u4EF6");
												lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
												lblNewLabel_1.setBounds(20, 67, 48, 15);
												panel.add(lblNewLabel_1);
												
														outputFile = new JTextField();
														outputFile.setColumns(10);
														outputFile.setBounds(74, 64, 300, 24);
														panel.add(outputFile);
														
																DirectorySelect_output = new JButton("\u9009\u62E9\u76EE\u5F55");
																DirectorySelect_output.addActionListener(new ActionListener() {
																	public void actionPerformed(ActionEvent e) {
																		JFileChooser fc1 = new JFileChooser(File_Obj.getProjectpath() + "\\file");
																		fc1.setFileView(new FileView() {
																			public Icon getIcon(File f) {
																				return fc1.getFileSystemView().getSystemIcon(f);
																			}
																		});
																		int returnVal = fc1.showOpenDialog(new encrypt_frame());
																		if (returnVal == JFileChooser.APPROVE_OPTION) {
																			// 正常选择文件
																			outputFile.setText(fc1.getSelectedFile().toString());
																		} else {
																			// 选择取消按钮
																			outputFile.setText("未选择文件");
																		}
																	}
																});
																DirectorySelect_output.setFont(new Font("宋体", Font.PLAIN, 12));
																DirectorySelect_output.setBounds(382, 65, 88, 23);
																panel.add(DirectorySelect_output);
																
																		LblNewLabel_1_1 = new JLabel("\u8F93\u51FA\u6587\u4EF6\u9ED8\u8BA4\u8DEF\u5F84");
																		LblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 12));
																		LblNewLabel_1_1.setEnabled(false);
																		LblNewLabel_1_1.setBounds(31, 129, 100, 15);
																		panel.add(LblNewLabel_1_1);
																		
																				Tolerantpath_text = new JTextField();
																				Tolerantpath_text.setEnabled(false);
																				Tolerantpath_text.setColumns(10);
																				Tolerantpath_text.setBounds(137, 126, 232, 20);
																				panel.add(Tolerantpath_text);
																				
																						DirectorySelect_Tolerant = new JButton("\u9009\u62E9\u76EE\u5F55");
																						DirectorySelect_Tolerant.addActionListener(new ActionListener() {
																							public void actionPerformed(ActionEvent e) {
																								JFileChooser fc1 = new JFileChooser(File_Obj.getProjectpath() + "\\file");
																								fc1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//将JFileChooser选择模式指定为选择目录
																								fc1.setFileView(new FileView() {
																									public Icon getIcon(File f) {
																										return fc1.getFileSystemView().getSystemIcon(f);
																									}
																								});
																								int returnVal = fc1.showOpenDialog(null);
																								if (returnVal == JFileChooser.APPROVE_OPTION) {
																									// 正常选择文件
																									Tolerantpath_text.setText(fc1.getSelectedFile().toString());
																								} 
																							}
																						});
																						DirectorySelect_Tolerant.setFont(new Font("宋体", Font.PLAIN, 12));
																						DirectorySelect_Tolerant.setEnabled(false);
																						DirectorySelect_Tolerant.setBounds(383, 125, 87, 23);
																						panel.add(DirectorySelect_Tolerant);
		setTitle("数字签名");
		setVisible(true);// 设置窗体可见
	}
	public static void initial(DigSign_frame frame) {
		try {			
			Object[] option = { "签名", "验证" };
			
			int Option = JOptionPane.showOptionDialog(null, "初始化选择：请选择你想进行的操作", "提示", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
			if (Option == 0) {
				JOptionPane.showMessageDialog(null, "请先在主面板的选项中选择签名算法");
				frame.Sign_Btn.setEnabled(true);
				frame.read_Btn.setEnabled(false);
				frame.Verify_Btn.setEnabled(false);
				frame.fileSign_rBtn.setSelected(true);
				
			}
			if (Option == 1) {
				frame.Sign_Btn.setEnabled(false);
				frame.read_Btn.setEnabled(true);
				frame.Verify_Btn.setEnabled(true);
				veri_Btn.setSelected(true);
				outputFile.setEnabled(false);
				frame.DirectorySelect_output.setEnabled(false);
				TolerantPath_Box.setEnabled(false);
				JOptionPane.showMessageDialog(null, "签名算法会根据文件格式自动识别。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void run() {
		DigSign_frame frame = new DigSign_frame();
		DigSign_frame.panel.setVisible(true);
		DigSign_frame.panel_5.setVisible(false);
		initial(frame);
	}
}
