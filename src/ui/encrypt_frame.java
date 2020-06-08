package ui;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileView;

import encrypt_algo.AES;
import encrypt_algo.DES;
import encrypt_algo.RSA;
import fileIO.File_Obj;
import fileIO.ReadFile;
import fileIO.WriteFile;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class encrypt_frame extends JFrame {


	public static JTextField InputFile,OutputFile,TolerantPath_text,n_text,RSAKey_text;
	public static JRadioButton DES_rBtn, AES_rBtn,RSA_rBtn,decrypt_Btn;
	public static JPasswordField DESKey_text, AESKey_text;
	public static JButton Enc_Btn;
	public static ButtonGroup group;
	public static JLabel RSAkey_Lab, n_Lab;
	public static JCheckBox TolerantPath_Box;
	public static JPanel panel_5,panel;
	public static byte[] M,C;
	public static boolean modal = true;
	public static String origin_text,crypt_text,key,selectedFilename;
	public static int Mode;
	
	public JLabel lblNewLabel_1,lblNewLabel_1_1;
	public JTextArea origin_textPane,crypt_textPane;
	public JRadioButton file_rBtn,string_rBtn;
	public JButton DirectorySelect_output,DirectorySelect_Tolerant;
	public Button stringcrypt_Btn;
	
	private JPasswordField DESKeyConfirm_text,AESKeyConfirm_text;
	private JButton confirm_Btn,clear_Btn,clear_Btn_1,confirm_Btn_1;
	private ImageIcon image;
	private JPanel contentPane;
	private JCheckBox checkBox_2;
	private JCheckBox checkBox_3;
	private JCheckBox checkBox_4;

	/**
	 * Create the frame.
	 */
	public encrypt_frame() {
		setVisible(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(encrypt_frame.class.getResource("/images/\u6587\u4EF6\u52A0\u5BC6.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "确定要退出界面吗？", "退出界面", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					if(DigSign_frame.modal==false)
						DigSign_frame.modal=true;
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
		panel_5 = new JPanel();
		setSize(new Dimension(530, 746));
		setTitle("DES/AES/RSA\u52A0\u89E3\u5BC6");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 746);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(500, 2000));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(7, 188, 482, 101);
		panel_1.setBorder(
				new TitledBorder(null, "DES\u52A0\u5BC6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 426, 484, 116);
		panel_2.setBorder(new TitledBorder(null, "RSA\u5BC6\u94A5\u5BF9\u7684\u9009\u62E9", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		origin_textPane = new JTextArea();
		origin_textPane.setWrapStyleWord(true);
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(7, 296, 482, 123);
		panel_3.setBorder(
				new TitledBorder(null, "AES\u52A0\u5BC6", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		confirm_Btn = new JButton("\u786E\u8BA4");
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
		confirm_Btn.setBounds(24, 136, 76, 27);
		panel_5.add(confirm_Btn);
		
		clear_Btn = new JButton("\u6E05\u7A7A");
		clear_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				origin_textPane.setText("");
			}
		});
		clear_Btn.setBounds(118, 136, 76, 27);
		panel_5.add(clear_Btn);
		crypt_textPane = new JTextArea();
		crypt_textPane.setWrapStyleWord(true);
		confirm_Btn_1 = new JButton("\u786E\u8BA4");
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
		confirm_Btn_1.setBounds(256, 136, 76, 27);
		panel_5.add(confirm_Btn_1);
		
		clear_Btn_1 = new JButton("\u6E05\u7A7A");
		clear_Btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crypt_textPane.setText("");
			}
		});
		clear_Btn_1.setBounds(346, 136, 76, 27);
		panel_5.add(clear_Btn_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(5, 549, 486, 110);
		panel_4.setBorder(new TitledBorder(null, "\u9009\u9879", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JCheckBox DelSource_Box = new JCheckBox("\u52A0\u5BC6\u540E\u5220\u9664\u539F\u6587\u4EF6");
		JCheckBox DelCode_Box = new JCheckBox("\u89E3\u5BC6\u540E\u5220\u9664\u52A0\u5BC6\u6587\u4EF6");
		JCheckBox Prompt_Box = new JCheckBox("\u5220\u9664\u524D\u63D0\u793A");
		string_rBtn = new JRadioButton("\u5B57\u7B26\u4E32\u5904\u7406");
		file_rBtn = new JRadioButton("\u6587\u4EF6\u5904\u7406");
		stringcrypt_Btn = new Button("\uFF1C\uFF1E");
		
		Enc_Btn = new JButton("\u52A0\u5BC6");
		Enc_Btn.setBounds(19, 666, 110, 27);
		/**
		 * 点击加密按钮执行指定的加密算法和操作
		 */
		Enc_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 File file;
				try {
					File_Obj fo = new File_Obj();
					fo.CryptBtn();
					if(Prompt_Box.isSelected())
					{
						if(DelSource_Box.isSelected()) {
						int confirm = JOptionPane.showConfirmDialog(null, "确定要删除原文件吗？", "提示",JOptionPane.YES_NO_OPTION);
						if(confirm == JOptionPane.OK_OPTION) {
						file = new File(InputFile.getText());
						 if(file.delete())
							 JOptionPane.showMessageDialog(null, " 原文件已被删除！");
						}
						}
						else if(DelCode_Box.isSelected()) {
							int confirm = JOptionPane.showConfirmDialog(null, "确定要删除加密文件吗？","提示", JOptionPane.YES_NO_OPTION);
							if(confirm == JOptionPane.OK_OPTION) {
							file = new File(InputFile.getText());
							 if(file.delete())
								 JOptionPane.showMessageDialog(null, " 加密文件已被删除！");
							}
						}
					}
					else {
						 file = new File(InputFile.getText());
		            if(DelSource_Box.isSelected()||DelCode_Box.isSelected()) {
		            	file.delete();
		            	if(DelSource_Box.isSelected()) 
		                JOptionPane.showMessageDialog(null, " 原文件已被删除！");
		            	else if(DelCode_Box.isSelected())
		            		JOptionPane.showMessageDialog(null, " 原加密文件已被删除！");
		            }
					}
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		Enc_Btn.setFont(new Font("宋体", Font.PLAIN, 15));

		JButton Exit_Btn = new JButton("\u8DF3\u8F6C\u7B7E\u540D");
		Exit_Btn.setBounds(369, 666, 110, 27);
		Exit_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(DigSign_frame.modal==true) {                         //1
					DigSign_frame frame = new DigSign_frame();
					frame.setVisible(false);
				Object[] option_1 = {"签名","验证签名"};
					int Option_1 = JOptionPane.showOptionDialog(null, "请选择要继续进行的操作", "提示", JOptionPane.YES_NO_CANCEL_OPTION,
						 	JOptionPane.PLAIN_MESSAGE, null, option_1, option_1[0]);
					if(Option_1 == 0) {
				 if(string_rBtn.isSelected()){                           //2
					 Object[] option = { "加密信息", "原信息" ,"直接跳转"};
					 	int Option = JOptionPane.showOptionDialog(null, "请选择需要继续签名的内容", "提示", JOptionPane.YES_NO_CANCEL_OPTION,
					 	JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
				 	DigSign_frame.stringSign_rBtn.setSelected(true);
				 	DigSign_frame.panel.setVisible(false);
				 	DigSign_frame.panel_5.setVisible(true);
				 	DigSign_frame.stringcrypt_Btn.setLabel("＞");
				 	
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
				  	if(file_rBtn.isSelected())                      //2
				  	{
				  		//TODO:文件操作
				  		DigSign_frame.fileSign_rBtn.setSelected(true);
					 	DigSign_frame.panel.setVisible(true);
					 	DigSign_frame.panel_5.setVisible(false);
					 	DigSign_frame.veri_Btn.setSelected(true);
					 	frame.read_Btn.setEnabled(true);
					 	frame.Sign_Btn.setEnabled(false);
					 	frame.lblNewLabel_1.setEnabled(false);
					 	frame.outputFile.setEnabled(false);
					 	frame.DirectorySelect_output.setEnabled(false);
					 	DigSign_frame.TolerantPath_Box.setEnabled(false);
					 	frame.LblNewLabel_1_1.setEnabled(false);
						frame.Tolerantpath_text.setEditable(false);
						frame.DirectorySelect_Tolerant.setEnabled(false);
						frame.setVisible(true);
					 	modal = false;
				  }
				}
					else if (Option_1 == 1) {
					 	if(string_rBtn.isSelected()){
						 	DigSign_frame.stringSign_rBtn.setSelected(true);
						 	DigSign_frame.panel.setVisible(false);
						 	DigSign_frame.panel_5.setVisible(true);
						 	DigSign_frame.stringcrypt_Btn.setLabel("＜");
						 	Object[] option = { "加密信息", "原信息" ,"直接跳转"};
						 	int Option = JOptionPane.showOptionDialog(null, "请选择需要验证签名的内容", "提示", JOptionPane.YES_NO_CANCEL_OPTION,
						 	JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
						 	
						 	if (Option == 0){
						 		if((crypt_text = crypt_textPane.getText()).equals("")) {
						 			JOptionPane.showMessageDialog(null,"未读取到加密信息！");
						 			frame.dispose();
						 		}
						 		else{
						       		DigSign_frame.crypt_textPane.setText(crypt_text);
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
						  		DigSign_frame.crypt_textPane.setText(origin_text);
						  		frame.setVisible(true);
						  		modal = false;
						  	}
						  	}
						  	else
						  		frame.setVisible(true);
						 	modal = false;
						  }
					 	else if(file_rBtn.isSelected())
						  	{
						  		//TODO:文件操作
						  		DigSign_frame.fileSign_rBtn.setSelected(true);
							 	DigSign_frame.panel.setVisible(true);
							 	DigSign_frame.panel_5.setVisible(false);
								DigSign_frame.veri_Btn.setSelected(true);
								frame.read_Btn.setEnabled(false);
							 	frame.Sign_Btn.setEnabled(true);
							 	frame.lblNewLabel_1.setEnabled(true);
							 	frame.outputFile.setEnabled(true);
							 	frame.DirectorySelect_output.setEnabled(false);
							 	DigSign_frame.TolerantPath_Box.setEnabled(true);
							 	frame.LblNewLabel_1_1.setEnabled(true);
								frame.Tolerantpath_text.setEditable(false);
								frame.DirectorySelect_Tolerant.setEnabled(false);
							 	frame.setVisible(true);
							 	modal = false;
						  }
					 	else
					 		DigSign_frame.run();
					}
				}
				else if (DigSign_frame.modal==false) {//1
					DigSign_frame.modal =true;
					dispose();
				}
			}
		});
		Exit_Btn.setFont(new Font("宋体", Font.PLAIN, 15));

		JButton createkey_Btn = new JButton("\u4EA7\u751FRSA\u5BC6\u94A5\u5BF9");
		createkey_Btn.setBounds(137, 666, 131, 27);
		createkey_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyproduction_frame f = new keyproduction_frame();
				f.setVisible(true);
			}
		});
		panel = new JPanel();
		createkey_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		ButtonGroup group_1 = new ButtonGroup();
		JButton reset_Btn = new JButton("\u91CD\u7F6E");

		reset_Btn.setBounds(276, 666, 85, 27);
		reset_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputFile.setText("");
				OutputFile.setText("");
				DESKey_text.setText("");
				DESKey_text.setEnabled(true);
				DESKeyConfirm_text.setText(""); 
				DESKeyConfirm_text.setEnabled(true);
				AESKey_text.setText("");
				AESKey_text.setEnabled(true);
				AESKeyConfirm_text.setText("");
				AESKeyConfirm_text.setEnabled(true);
				RSAKey_text.setText("");
				RSAKey_text.setEnabled(true);
				n_text.setText("");
				n_text.setEnabled(true);
				AES_rBtn.setEnabled(true);
				DES_rBtn.setEnabled(true);
				RSA_rBtn.setEnabled(true);
				decrypt_Btn.setEnabled(true);
				group.clearSelection();
				origin_textPane.setText("");
				crypt_textPane.setText("");
				decrypt_Btn.setSelected(false);
				
			}
		});

		JLabel label_1 = new JLabel("\u52A0\u5BC6\u65B9\u5F0F");
		label_1.setBounds(14, 54, 48, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		DES_rBtn = new JRadioButton("DES\u52A0\u5BC6");
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
						String file = selectedfile.getName();
						if (file.contains("e")) {
							byte[] keybyte = input.getContent(read.getSelectedFile().toString());
							BigInteger Key = new BigInteger(keybyte);
							RSAKey_text.setText(Key.toString(16));
							encrypt_frame.RSAkey_Lab.setText("公钥(" + Key.toString(16).length() + ")");
						} else if (file.contains("d")) {
							byte[] keybyte = input.getContent(read.getSelectedFile().toString());
							BigInteger Key = new BigInteger(keybyte);
							RSAKey_text.setText(Key.toString(16));
							encrypt_frame.RSAkey_Lab.setText("私钥(" + Key.toString(16).length() + ")");
						}
						else
							JOptionPane.showMessageDialog(null, "导入的不是密钥文件，导入失败！");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
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
						encrypt_frame.n_Lab.setText("模数n(" + N.toString(16).length() + ")");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		JLabel label = new JLabel("\u6CE8\uFF1A\u5BC6\u94A5\u957F\u5EA6\u4E3A8\u4F4D");
		label.setBounds(284, 13, 128, 18);
		JLabel DESkeylabel = new JLabel("");
		DESKey_text = new JPasswordField();
		DESKey_text.setEchoChar('*');
		DESKey_text.setBounds(68, 36, 184, 24);
		DESKey_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(DESKey_text.getPassword().length!=0&&DESKey_text.getPassword().length!=8) {
					DESkeylabel.setText("密钥长度不符合要求！");
				}
				else
					DESkeylabel.setText("");
			}
		});
		DES_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DESKey_text.setEnabled(true);
				DESKeyConfirm_text.setEnabled(true);
				AESKey_text.setEnabled(false);
				AESKeyConfirm_text.setEnabled(false);
				RSAKey_text.setEnabled(false);
				createkey_Btn.setEnabled(false);
				importkey_btn.setEnabled(false);
				importn_btn.setEnabled(false);
				if(string_rBtn.isSelected()&&!decrypt_Btn.isSelected()) {
					stringcrypt_Btn.setLabel("＞");
				confirm_Btn.setVisible(true);
				clear_Btn.setVisible(true);
				confirm_Btn_1.setVisible(false);
				clear_Btn_1.setVisible(false);
				}
			}
		});
		DES_rBtn.setBounds(192, 24, 71, 25);
		DES_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));

		AES_rBtn = new JRadioButton("AES\u52A0\u5BC6");
		AES_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DESKey_text.setEnabled(false);
				DESKeyConfirm_text.setEnabled(false);
				AESKey_text.setEnabled(true);
				AESKeyConfirm_text.setEnabled(true);
				RSAKey_text.setEnabled(false);
				createkey_Btn.setEnabled(false);
				importkey_btn.setEnabled(false);
				importn_btn.setEnabled(false);
				if(string_rBtn.isSelected()&&!decrypt_Btn.isSelected()) {
					stringcrypt_Btn.setLabel("＞");
				confirm_Btn.setVisible(true);
				clear_Btn.setVisible(true);
				confirm_Btn_1.setVisible(false);
				clear_Btn_1.setVisible(false);
				}
			}
		});
		AES_rBtn.setBounds(192, 49, 71, 25);
		AES_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));

		RSA_rBtn = new JRadioButton("RSA\u52A0\u5BC6");
		RSA_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DESKey_text.setEnabled(false);
				DESKeyConfirm_text.setEnabled(false);
				AESKey_text.setEnabled(false);
				AESKeyConfirm_text.setEnabled(false);
				RSAKey_text.setEnabled(true);
				createkey_Btn.setEnabled(true);
				importkey_btn.setEnabled(true);
				importn_btn.setEnabled(true);
				if(string_rBtn.isSelected()&&!decrypt_Btn.isSelected()) {
					stringcrypt_Btn.setLabel("＞");
					confirm_Btn.setVisible(true);
					clear_Btn.setVisible(true);
					confirm_Btn_1.setVisible(false);
					clear_Btn_1.setVisible(false);
			}
			}
		});
		RSA_rBtn.setBounds(192, 76, 71, 25);
		RSA_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		/**
		 * Group the radio Buttons
		 */
		group = new ButtonGroup();
		group.add(DES_rBtn);
		group.add(AES_rBtn);
		group.add(RSA_rBtn);

		decrypt_Btn = new JRadioButton("\u89E3\u5BC6");
		DelSource_Box.setBounds(327, 29, 135, 25);
		DelSource_Box.setFont(new Font("宋体", Font.PLAIN, 12));
		panel_4.setLayout(null);
		panel_4.add(label_1);
		panel_4.add(DES_rBtn);
		panel_4.add(AES_rBtn);
		panel_4.add(RSA_rBtn);
		panel_4.add(DelSource_Box);
		panel_4.add(decrypt_Btn);

		DelCode_Box.setFont(new Font("宋体", Font.PLAIN, 12));
		DelCode_Box.setBounds(327, 54, 149, 25);
		panel_4.add(DelCode_Box);

		
		Prompt_Box.setFont(new Font("宋体", Font.PLAIN, 12));
		Prompt_Box.setBounds(327, 76, 135, 25);
		panel_4.add(Prompt_Box);

		
		decrypt_Btn.setFont(new Font("宋体", Font.PLAIN, 12));
		
		decrypt_Btn.addActionListener(new decrypt_Btn_Handler());
		decrypt_Btn.setBounds(269, 48, 59, 27);
		panel_4.add(decrypt_Btn);
		
		
		file_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(true);
				panel_5.setVisible(false);
				Enc_Btn.setEnabled(true);
				if(decrypt_Btn.isSelected())
					Enc_Btn.setText("解密");
				else
					Enc_Btn.setText("加密");
			}
		});
		file_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		file_rBtn.setBounds(74, 23, 82, 27);
		panel_4.add(file_rBtn);
		
		
		string_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stringcrypt_Btn.setLabel("＜＞");
				panel.setVisible(false);
				panel_5.setVisible(true);
				Enc_Btn.setEnabled(false);
				if(decrypt_Btn.isSelected()) {
					stringcrypt_Btn.setLabel("＜");
					confirm_Btn.setVisible(false);
					clear_Btn.setVisible(false);
					confirm_Btn_1.setVisible(true);
					clear_Btn_1.setVisible(true);
				}
				else if(AES_rBtn.isSelected()||DES_rBtn.isSelected()||RSA_rBtn.isSelected()) {
					stringcrypt_Btn.setLabel("＞");
					confirm_Btn.setVisible(true);
					clear_Btn.setVisible(true);
					confirm_Btn_1.setVisible(false);
					clear_Btn_1.setVisible(false);
				}
			}
		});
		string_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		string_rBtn.setBounds(74, 75, 89, 27);
		
		group_1.add(string_rBtn);
		group_1.add(file_rBtn);
		panel_4.add(string_rBtn);

		JLabel lblDes_2 = new JLabel("AES\u5BC6\u94A5");
		lblDes_2.setBounds(20, 38, 48, 15);
		lblDes_2.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel label_2 = new JLabel("\u6CE8\uFF1A\u5BC6\u94A5\u957F\u5EA6\u4E3A16\u4F4D");
		label_2.setBounds(282, 13, 154, 18);
		JLabel AESkeylabel = new JLabel("");
		AESKey_text = new JPasswordField();
		AESKey_text.setEchoChar('*');
		AESKey_text.setBounds(74, 33, 178, 24);
		AESKey_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(AESKey_text.getPassword().length!=0&&AESKey_text.getPassword().length!=16) 
					AESkeylabel.setText("密钥长度不符合要求！");
				else
					AESkeylabel.setText("");
			}
		});
		AESKey_text.setColumns(10);

		JLabel lblDes_1_1 = new JLabel("\u5BC6\u94A5\u786E\u8BA4");
		lblDes_1_1.setBounds(20, 75, 48, 15);
		lblDes_1_1.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel AESkeyConfirmlabel = new JLabel("");
		AESKeyConfirm_text = new JPasswordField();
		AESKeyConfirm_text.setEchoChar('*');
		AESKeyConfirm_text.setBounds(74, 70, 178, 24);
		AESKeyConfirm_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(AESKeyConfirm_text.getPassword().length!=0&&!Arrays.equals(AESKeyConfirm_text.getPassword(),AESKey_text.getPassword()))
					AESkeyConfirmlabel.setText("密钥前后不一致！");
				else
					AESkeyConfirmlabel.setText("");
			}
		});
		AESKeyConfirm_text.setColumns(10);

		
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));

		RSAKey_text = new JTextField();
		RSAKey_text.setEditable(false);
		RSAKey_text.setColumns(10);

		RSAkey_Lab = new JLabel("\u5BC6\u94A5");
		RSAkey_Lab.setFont(new Font("宋体", Font.PLAIN, 12));

		n_Lab = new JLabel("\u6A21\u6570");
		n_Lab.setFont(new Font("宋体", Font.PLAIN, 12));

		n_text = new JTextField();
		n_text.setEditable(false);
		n_text.setColumns(10);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(n_Lab, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(RSAkey_Lab, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(RSAKey_text, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(importkey_btn))
						.addGroup(Alignment.TRAILING,
								gl_panel_2.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(n_text, GroupLayout.PREFERRED_SIZE, 300,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(importn_btn, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(23, Short.MAX_VALUE)));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
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

		JLabel lblDes = new JLabel("DES\u5BC6\u94A5");
		lblDes.setBounds(14, 41, 48, 15);
		lblDes.setFont(new Font("宋体", Font.PLAIN, 12));
		DESKey_text.setColumns(10);

		JLabel lblDes_1 = new JLabel("\u5BC6\u94A5\u786E\u8BA4");
		lblDes_1.setBounds(14, 78, 48, 15);
		lblDes_1.setFont(new Font("宋体", Font.PLAIN, 12));
		JLabel DESkeyConfirmlabel = new JLabel("");
		DESkeyConfirmlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		DESKeyConfirm_text = new JPasswordField();
		DESKeyConfirm_text.setEchoChar('*');
		DESKeyConfirm_text.setBounds(68, 73, 184, 24);
		DESKeyConfirm_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(DESKeyConfirm_text.getPassword().length!=0&&!Arrays.equals(DESKeyConfirm_text.getPassword(), DESKey_text.getPassword()))
					DESkeyConfirmlabel.setText("密钥前后不一致！");
				else
					DESkeyConfirmlabel.setText("");
			}
		});
		DESKeyConfirm_text.setColumns(10);

		
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		contentPane.setLayout(null);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		panel_3.add(lblDes_2);
		panel_3.add(AESKey_text);
		panel_3.add(lblDes_1_1);
		panel_3.add(AESKeyConfirm_text);
		panel_3.add(label_2);
		

		AESkeylabel.setBounds(324, 36, 146, 18);
		panel_3.add(AESkeylabel);
		

		AESkeyConfirmlabel.setBounds(324, 73, 146, 18);
		panel_3.add(AESkeyConfirmlabel);
		
		checkBox_3 = new JCheckBox("\u663E\u793A");
		checkBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox_3.isSelected())
					AESKey_text.setEchoChar((char)0);
				else
					AESKey_text.setEchoChar('*');
			}
		});
		checkBox_3.setFont(new Font("宋体", Font.PLAIN, 11));
		checkBox_3.setBounds(253, 32, 51, 27);
		panel_3.add(checkBox_3);
		
		checkBox_4 = new JCheckBox("\u663E\u793A");
		checkBox_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox_4.isSelected())
					AESKeyConfirm_text.setEchoChar((char)0);
				else
					AESKeyConfirm_text.setEchoChar('*');
			}
		});
		checkBox_4.setFont(new Font("宋体", Font.PLAIN, 11));
		checkBox_4.setBounds(253, 69, 51, 27);
		panel_3.add(checkBox_4);
		String Imagepath = "/images/\u63D0\u793A .png";
		ImageIcon resource = setImageIconSize(Imagepath, 30, 30);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		panel_1.add(lblDes);
		panel_1.add(DESKey_text);
		panel_1.add(label);
		panel_1.add(lblDes_1);
		panel_1.add(DESKeyConfirm_text);
		
		
		DESkeylabel.setBounds(328, 36, 140, 18);
		panel_1.add(DESkeylabel);
		

		DESkeyConfirmlabel.setBounds(328, 72, 140, 18);
		panel_1.add(DESkeyConfirmlabel);
		
		JCheckBox checkBox_1 = new JCheckBox("\u663E\u793A");
		checkBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox_1.isSelected())
					DESKey_text.setEchoChar((char)0);
				else
					DESKey_text.setEchoChar('*');
			}
		});
		checkBox_1.setFont(new Font("宋体", Font.PLAIN, 11));
		checkBox_1.setBounds(253, 35, 51, 27);
		panel_1.add(checkBox_1);
		
		checkBox_2 = new JCheckBox("\u663E\u793A");
		checkBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkBox_2.isSelected())
					DESKeyConfirm_text.setEchoChar((char)0);
				else
					DESKeyConfirm_text.setEchoChar('*');
			}
		});
		checkBox_2.setFont(new Font("宋体", Font.PLAIN, 11));
		checkBox_2.setBounds(253, 72, 51, 27);
		panel_1.add(checkBox_2);
		
		
		panel_5.setBorder(new TitledBorder(null, "\u5B57\u7B26\u4E32\u8F93\u5165/\u8F93\u51FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(7, 18, 482, 171);
		contentPane.add(Enc_Btn);
		contentPane.add(createkey_Btn);
		contentPane.add(reset_Btn);
		contentPane.add(Exit_Btn);
		contentPane.add(panel_2);
		contentPane.add(panel_4);
		contentPane.add(panel_5);
		contentPane.add(panel);
		panel_5.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "\u539F\u5B57\u7B26\u4E32", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(14, 23, 198, 109);
		panel_5.add(panel_6);
		panel_6.setLayout(null);
		

		origin_textPane.setSize(new Dimension(176, 79));
		origin_textPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		origin_textPane.setBounds(14, 22, 176, 74);
		panel_6.add(origin_textPane);
		
		JScrollPane scrollPane = new JScrollPane(origin_textPane);
		scrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(14, 22, 170, 74);
		panel_6.add(scrollPane);
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u52A0\u5BC6\u5B57\u7B26\u4E32", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6_1.setBounds(246, 23, 198, 109);
		panel_5.add(panel_6_1);
		panel_6_1.setLayout(null);
		
		
		crypt_textPane.setSize(new Dimension(176, 79));
		crypt_textPane.setAlignmentX(0.0f);
		crypt_textPane.setBounds(14, 22, 176, 74);
		panel_6_1.add(crypt_textPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(crypt_textPane);
		scrollPane_1.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane_1.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane_1.setBounds(14, 23, 170, 73);
		panel_6_1.add(scrollPane_1);
		
		
		stringcrypt_Btn.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
					int flag = 0;
					 if(DES_rBtn.isSelected()) {
						 if(DESKey_text.getText().equals("")) {
							 JOptionPane.showMessageDialog(null, "未输入密钥！");
							 flag=1;
						 }
						 else {
						DES des = new DES(); 
						key = DESKey_text.getText();
						C = des.deal(M, key, 1);
						crypt_text = File_Obj.toStringMethod(C).toUpperCase();
					}}
					else if(AES_rBtn.isSelected()) {
						if(AESKey_text.getText().equals("")) {
							 JOptionPane.showMessageDialog(null, "未输入密钥！");
							 flag=1;
						}
						else {
						AES aes = new AES();
						key = AESKey_text.getText();
						C = File_Obj.intArrayTobyteArray( aes.AESEncrypt(M, key));
						crypt_text = File_Obj.toStringMethod(C).toUpperCase();
					}}
					else if(RSA_rBtn.isSelected()) {
						if(RSAKey_text.getText().equals("")) {
							 JOptionPane.showMessageDialog(null, "未输入密钥！");
							 flag=1;
						}
						else if(n_text.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "未输入模数！");
							flag=1;
						}
						else {
						RSA rsa = new RSA();
						BigInteger Mb = new BigInteger(M);
						String ntext,etext;
						ntext = n_text.getText();
						etext = RSAKey_text.getText();
						BigInteger n = new BigInteger(ntext,16);
						BigInteger eb = new BigInteger(etext,16);
						rsa.sete(eb);
						rsa.setn(n);
						BigInteger Cb = rsa.encode(Mb);
						crypt_text = File_Obj.toStringMethod(Cb.toByteArray()).toUpperCase();
					}}
					 if(flag==0)
					crypt_textPane.setText(crypt_text);
					}
						}}

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
						if(DES_rBtn.isSelected()) {
							DES des = new DES();
							key = DESKey_text.getText();
							M = des.deal(C, key, 0);
							origin_text = new String(M);
						}
						else if(AES_rBtn.isSelected()) {
							AES aes= new AES();
							key = AESKey_text.getText();
							M = File_Obj.intArrayTobyteArray(aes.AESDeEncrypt(C, key));
							origin_text = new String(M);
						}
						else if(RSA_rBtn.isSelected()) {
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
							origin_text = new String(Mb.toByteArray());
						}
						origin_textPane.setText(origin_text);
					}}
				}}
		});
		stringcrypt_Btn.setBounds(210, 64, 40, 25);
		panel_5.add(stringcrypt_Btn);
		
		
		
				
				panel.setBounds(7, 18, 482, 163);
				panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u52A0\u5BC6/\u89E3\u5BC6\u6587\u4EF6\u8F93\u5165", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				
						JLabel lblNewLabel = new JLabel("\u8F93\u5165\u6587\u4EF6");
						lblNewLabel.setBounds(20, 38, 48, 15);
						lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
						
								InputFile = new JTextField(30);
								InputFile.setBounds(74, 33, 301, 24);
								
										InputFile.setColumns(10);
										
												// image = new
												// ImageIcon(this.getClass().getResource("/images/mihome_select.png"));// 加载图片资源
												// Image img = image.getImage();// 获得此图标的Image
												// img = img.getScaledInstance(700, 400, Image.SCALE_AREA_AVERAGING);//
												// 将image压缩后得到压缩后的img
												// image.setImage(img);// 将图标设置为压缩后的图像
												JButton FileBrowse = new JButton("\u6D4F\u89C8\u6587\u4EF6", image);
												FileBrowse.setBounds(383, 34, 87, 23);
												TolerantPath_Box = new JCheckBox("\u8F93\u51FA\u6587\u4EF6\u7684\u9ED8\u8BA4\u8DEF\u5F84");
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
															InputFile.setText(fc1.getSelectedFile().toString());
															String oldPath = new String(fc1.getSelectedFile().toString());
															String prefix = oldPath.substring(oldPath.lastIndexOf("."));
															String newPath = new String();
															if (prefix.equals(".des") || (prefix.equals(".aes")) || (prefix.equals(".rsa"))) {
																Enc_Btn.setText("解密");
																if (prefix.equals(".des")) {
																	Mode = 1;// 判断是DES解密
																	AESKey_text.setEnabled(false);
																	AESKeyConfirm_text.setEnabled(false);
																	RSAKey_text.setEnabled(false);
																}
																if (prefix.equals(".aes")) {
																	Mode = 2;// 判断是AES解密
																	DESKey_text.setEnabled(false);
																	DESKeyConfirm_text.setEnabled(false);
																	RSAKey_text.setEnabled(false);
																}
																if (prefix.equals(".rsa")) {
																	Mode = 3;// 判断是RSA解密
																	AESKey_text.setEnabled(false);
																	AESKeyConfirm_text.setEnabled(false);
																	DESKey_text.setEnabled(false);
																	DESKeyConfirm_text.setEnabled(false);
																}
																label_1.setEnabled(false);
																DES_rBtn.setEnabled(false);
																AES_rBtn.setEnabled(false);
																RSA_rBtn.setEnabled(false);
																StringBuffer OldPath = new StringBuffer(oldPath);
																newPath = OldPath.delete(oldPath.lastIndexOf("."), oldPath.length()).toString();
															} else {
																Mode = 0;// 判断是加密
																label_1.setEnabled(true);
																DES_rBtn.setEnabled(true);
																AES_rBtn.setEnabled(true);
																RSA_rBtn.setEnabled(true);
																StringBuffer OldPath = new StringBuffer(oldPath);
																if (DES_rBtn.isSelected())
																	newPath = OldPath.append(".des").toString();
																else if (AES_rBtn.isSelected())
																	newPath = OldPath.append(".aes").toString();
																else if (RSA_rBtn.isSelected()) {
																	newPath = OldPath.append(".rsa").toString();
																	ReadFile f1 = new ReadFile();
																	try {
																		byte[] M = f1.getContent(fc1.getSelectedFile().toString());
																		BigInteger MB = new BigInteger(M);
																		String MBS = MB.toString(16);
																		int len = MBS.length();
																		JOptionPane.showMessageDialog(FileBrowse, "RSA算法的模数n的位数必须大于" + len);
																	} catch (IOException e1) {
																		e1.printStackTrace();
																	}
																}
															}
															if(!TolerantPath_Box.isSelected())
															OutputFile.setText(newPath); 
														} else {
															// 选择取消按钮
															InputFile.setText("");
														}
													}
												});
												FileBrowse.setFont(new Font("宋体", Font.PLAIN, 12));
												
														lblNewLabel_1 = new JLabel("\u8F93\u51FA\u6587\u4EF6");
														lblNewLabel_1.setBounds(20, 67, 48, 15);
														lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
														
																OutputFile = new JTextField();
																OutputFile.setBounds(74, 64, 300, 24);
																OutputFile.setColumns(10);
																
																		DirectorySelect_output = new JButton("\u9009\u62E9\u76EE\u5F55");
																		DirectorySelect_output.addActionListener(new ActionListener() {
																			public void actionPerformed(ActionEvent e) {
																				OutputFile.setText("");
																				JFileChooser fc1 = new JFileChooser(File_Obj.getProjectpath() + "\\file");
																				fc1.setFileView(new FileView() {
																					public Icon getIcon(File f) {
																						return fc1.getFileSystemView().getSystemIcon(f);
																					}
																				});
																				int returnVal = fc1.showOpenDialog(new encrypt_frame());
																				if (returnVal == JFileChooser.APPROVE_OPTION) {
																					// 正常选择文件
																					OutputFile.setText(fc1.getSelectedFile().toString());
																				} else {
																					// 选择取消按钮
																					OutputFile.setText("未选择文件");
																				}
																			}
																		});
																		DirectorySelect_output.setBounds(382, 65, 88, 23);
																		DirectorySelect_output.setFont(new Font("宋体", Font.PLAIN, 12));
																		
																				
																		
																				TolerantPath_Box.setBounds(20, 97, 137, 25);
																				TolerantPath_Box.setFont(new Font("宋体", Font.PLAIN, 12));
																				
																						lblNewLabel_1_1 = new JLabel("\u8F93\u51FA\u6587\u4EF6\u9ED8\u8BA4\u8DEF\u5F84");
																						lblNewLabel_1_1.setEnabled(false);
																						lblNewLabel_1_1.setBounds(31, 129, 100, 15);
																						lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 12));
																						
																								TolerantPath_text = new JTextField();
																								TolerantPath_text.setEnabled(false);
																								TolerantPath_text.setBounds(137, 126, 232, 20);
																								TolerantPath_text.setColumns(10);
																								
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
																													TolerantPath_text.setText(fc1.getSelectedFile().toString());
																												} else {
																													// 选择取消按钮
																													TolerantPath_text.setText("未选择文件");
																												}
																											}
																										});
																										DirectorySelect_Tolerant.setEnabled(false);
																										
																												DirectorySelect_Tolerant.setBounds(383, 125, 87, 23);
																												DirectorySelect_Tolerant.setFont(new Font("宋体", Font.PLAIN, 12));
																												panel.setLayout(null);
																												panel.add(lblNewLabel);
																												panel.add(InputFile);
																												panel.add(FileBrowse);
																												panel.add(TolerantPath_Box);
																												panel.add(lblNewLabel_1);
																												panel.add(OutputFile);
																												panel.add(DirectorySelect_output);
																												panel.add(lblNewLabel_1_1);
																												panel.add(TolerantPath_text);
																												panel.add(DirectorySelect_Tolerant);
																												TolerantPath_Box.addActionListener(new ActionListener() {
																													public void actionPerformed(ActionEvent e) {
																														if (TolerantPath_Box.isSelected()) {
																															lblNewLabel_1_1.setEnabled(true);
																															TolerantPath_text.setEnabled(true);
																															DirectorySelect_Tolerant.setEnabled(true);
																															lblNewLabel_1.setEnabled(false);
																															OutputFile.setEnabled(false);
																															DirectorySelect_output.setEnabled(false);
																														} else {
																															lblNewLabel_1_1.setEnabled(false);
																															TolerantPath_text.setEnabled(false);
																															DirectorySelect_Tolerant.setEnabled(false);
																															lblNewLabel_1.setEnabled(true);
																															OutputFile.setEnabled(true);
																															DirectorySelect_output.setEnabled(true);
																														}
																													}
																												});
	}
	private class decrypt_Btn_Handler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(decrypt_Btn.isSelected()) {
				if(file_rBtn.isSelected()) {
				Enc_Btn.setText("解密");
				DES_rBtn.setEnabled(false);
				AES_rBtn.setEnabled(false);
				RSA_rBtn.setEnabled(false);
			}
			else if(string_rBtn.isSelected()){
				stringcrypt_Btn.setLabel("＜");
				confirm_Btn.setVisible(false);
				clear_Btn.setVisible(false);
				confirm_Btn_1.setVisible(true);
				clear_Btn_1.setVisible(true);
		}
			}
			else {
				if(file_rBtn.isSelected()) {
					Enc_Btn.setText("加密");
					DES_rBtn.setEnabled(true);
					AES_rBtn.setEnabled(true);
					RSA_rBtn.setEnabled(true);
				}
				else if(string_rBtn.isSelected()) {
					if(DES_rBtn.isSelected()||AES_rBtn.isSelected()||RSA_rBtn.isSelected()) {
					stringcrypt_Btn.setLabel("＞");
					confirm_Btn.setVisible(true);
					clear_Btn.setVisible(true);
					confirm_Btn_1.setVisible(false);
					clear_Btn_1.setVisible(false);
					}
					else
						stringcrypt_Btn.setLabel("＜＞");
					DES_rBtn.setEnabled(true);
					AES_rBtn.setEnabled(true);
					RSA_rBtn.setEnabled(true);
				}
			}
		}
	}
	/**
	 * Launch the application.
	 */
	public static void run() {
		encrypt_frame frame = new encrypt_frame();
		frame.setVisible(true);
		encrypt_frame.panel.setVisible(true);
		encrypt_frame.panel_5.setVisible(false);
		initial(frame);
	}
	
	private static void initial(encrypt_frame frame) {
		try {			
			Object[] option = { "加密", "解密" };
			int Option = JOptionPane.showOptionDialog(null, "初始化选择：请选择你想进行的操作", "提示", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, option, option[0]);
			if (Option == 0)
				JOptionPane.showMessageDialog(null, "请先在主面板的选项中选择加密算法");
			if (Option == 1) {
				encrypt_frame.AES_rBtn.setEnabled(false);
				encrypt_frame.DES_rBtn.setEnabled(false);
				encrypt_frame.RSA_rBtn.setEnabled(false);
				encrypt_frame.decrypt_Btn.setSelected(true);
				Enc_Btn.setText("解密");
				JOptionPane.showMessageDialog(null, "解密算法会根据文件格式自动识别。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  ImageIcon setImageIconSize(String path, int width, int height) {
		if (width == 0 || height == 0) {
			return new ImageIcon(this.getClass().getResource(path));
		}
		ImageIcon icon = new ImageIcon(this.getClass().getResource(path));
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}

	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		f.setVisible(true);
	}
}
