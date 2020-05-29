package ui;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

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
import java.io.IOException;
import java.math.BigInteger;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.WindowAdapter;

public class encrypt_frame extends JFrame {

	private JPanel contentPane;
    public static JTextField InputFile;
	public static JTextField OutputFile;
	private JTextField TolerantPath_text;
	public static JTextField DESKey_text,AESKey_text,RSAkey_text;
	private JTextField DESKeyConfirm_text;
	public static int Mode;
	private JTextField AESKeyConfirm;
	public static ButtonGroup group;
	public static JRadioButton DES_rBtn;
	public static JRadioButton AES_rBtn;
	public static JRadioButton RSA_rBtn;
	public static JButton Enc_Btn;
	public static JLabel RSAkey_Lab,n_Lab;
	public static JTextField n_text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					encrypt_frame frame = new encrypt_frame();
					frame.setVisible(true);
					Object[] option = {"加密","解密"};
					int Option = JOptionPane.showOptionDialog(frame,"初始化选择：请选择你想进行的操作","提示",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,option,option[0]);
					if(Option == 0)
						JOptionPane.showMessageDialog(null, "请先在主面板的选项中选择加密算法");
					if(Option ==1) {
						encrypt_frame.AES_rBtn.setEnabled(false);
						encrypt_frame.DES_rBtn.setEnabled(false);
						encrypt_frame.RSA_rBtn.setEnabled(false);
						encrypt_frame.Enc_Btn.setText("解密");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public encrypt_frame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				 int i=JOptionPane.showConfirmDialog(null, "确定要退出系统吗？", "退出系统", JOptionPane.YES_NO_OPTION);
				 if(i==JOptionPane.YES_OPTION){
				 System.exit(0);
			}}
		});
		
		setSize(new Dimension(500, 763));
		setTitle("DES/AES/RSA\u52A0\u89E3\u5BC6");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u52A0\u5BC6/\u89E3\u5BC6\u6587\u4EF6\u8F93\u5165", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "DES\u52A0\u5BC6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "RSA\u5BC6\u94A5\u5BF9\u7684\u9009\u62E9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "AES\u52A0\u5BC6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u9009\u9879", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
	    Enc_Btn = new JButton("\u52A0\u5BC6");
		/**
		 * 点击加密按钮执行指定的加密算法和操作
		 */
		Enc_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File_Obj fo = new File_Obj();
					fo.CryptBtn();
					
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		Enc_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JButton Exit_Btn = new JButton("\u9000\u51FA");
		Exit_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(Exit_Btn,"提示", "确认退出吗？",JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.OK_OPTION)
					dispose();
			}
		});
		Exit_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JButton createkey_Btn = new JButton("\u4EA7\u751FRSA\u5BC6\u94A5\u5BF9");
		createkey_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyproduction_frame f = new keyproduction_frame();
				f.setVisible(true);
			}
		});
		createkey_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addComponent(Enc_Btn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(createkey_Btn)
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addComponent(Exit_Btn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Enc_Btn)
						.addComponent(Exit_Btn)
						.addComponent(createkey_Btn))
					.addContainerGap())
		);
		
		JLabel label_1 = new JLabel("\u52A0\u5BC6\u65B9\u5F0F");
		label_1.setBounds(10, 40, 48, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		 DES_rBtn = new JRadioButton("DES\u52A0\u5BC6");
		 JButton importkey_btn = new JButton("\u5BFC\u5165");
		 importkey_btn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		JFileChooser read = new JFileChooser(File_Obj.getProjectpath()+"\\key");			
				int returnVal = read.showOpenDialog(null);
				if (returnVal==read.APPROVE_OPTION) {
					ReadFile input = new ReadFile();
					try {
						byte[] keybyte = input.getContent(read.getSelectedFile().toString());
						BigInteger Key = new BigInteger(keybyte);
						RSAkey_text.setText(Key.toString(16));
						if (encrypt_frame.RSA_rBtn.isSelected()&&encrypt_frame.RSA_rBtn.isEnabled()) {
							encrypt_frame.RSAkey_Lab.setText("公钥("+Key.toString(16).length()+")");
						}
						else if(!encrypt_frame.RSA_rBtn.isEnabled())
							encrypt_frame.RSAkey_Lab.setText("私钥("+Key.toString(16).length()+")");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
		 	}
		 });
			JButton importn_btn = new JButton("\u5BFC\u5165");
			importn_btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser read = new JFileChooser(File_Obj.getProjectpath()+"\\key");			
					int returnVal = read.showOpenDialog(null);
					if (returnVal==read.APPROVE_OPTION) {
						ReadFile input = new ReadFile();
						try {
							byte[] nbyte = input.getContent(read.getSelectedFile().toString());
							BigInteger N = new BigInteger(nbyte);
							n_text.setText(N.toString(16));
							encrypt_frame.n_Lab.setText("模数n("+N.toString(16).length()+")");
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
		DESKey_text = new JTextField();
		DES_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DESKey_text.setEnabled(true);
			DESKeyConfirm_text.setEnabled(true);
				AESKey_text.setEnabled(false);
				AESKeyConfirm.setEnabled(false);
				RSAkey_text.setEnabled(false);
				createkey_Btn.setEnabled(false);
				importkey_btn.setEnabled(false);
				importn_btn.setEnabled(false);
			}
		});
		DES_rBtn.setBounds(58, 38, 71, 25);
		DES_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		
		AES_rBtn = new JRadioButton("AES\u52A0\u5BC6");
		AES_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			DESKey_text.setEnabled(false);
			DESKeyConfirm_text.setEnabled(false);
			AESKey_text.setEnabled(true);
				AESKeyConfirm.setEnabled(true);
			RSAkey_text.setEnabled(false);
			createkey_Btn.setEnabled(false);
			importkey_btn.setEnabled(false);
			importn_btn.setEnabled(false);
			}
		});
		AES_rBtn.setBounds(130, 38, 71, 25);
		AES_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		
		RSA_rBtn = new JRadioButton("RSA\u52A0\u5BC6");
		RSA_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			DESKey_text.setEnabled(false);
			DESKeyConfirm_text.setEnabled(false);
			AESKey_text.setEnabled(false);
				AESKeyConfirm.setEnabled(false);
			RSAkey_text.setEnabled(true);
			createkey_Btn.setEnabled(true);
			importkey_btn.setEnabled(true);
			importn_btn.setEnabled(true);
			}
		});
		RSA_rBtn.setBounds(201, 38, 71, 25);
		RSA_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		/**
		 * Group the radio Buttons
		 */
		group = new ButtonGroup ();
		group.add(DES_rBtn);
	      group.add(AES_rBtn);	
	      group.add(RSA_rBtn);
     
		JCheckBox DelSource_Box = new JCheckBox("\u52A0\u5BC6\u540E\u5220\u9664\u539F\u6587\u4EF6");
		DelSource_Box.setBounds(291, 9, 135, 25);
		DelSource_Box.setFont(new Font("宋体", Font.PLAIN, 12));
		panel_4.setLayout(null);
		panel_4.add(label_1);
		panel_4.add(DES_rBtn);
		panel_4.add(AES_rBtn);
		panel_4.add(RSA_rBtn);
		panel_4.add(DelSource_Box);
		
		JCheckBox DelCode_Box = new JCheckBox("\u89E3\u5BC6\u540E\u5220\u9664\u52A0\u5BC6\u6587\u4EF6");
		DelCode_Box.setFont(new Font("宋体", Font.PLAIN, 12));
		DelCode_Box.setBounds(291, 34, 159, 25);
		panel_4.add(DelCode_Box);
		
		JCheckBox Prompt_Box = new JCheckBox("\u5220\u9664\u524D\u63D0\u793A");
		Prompt_Box.setFont(new Font("宋体", Font.PLAIN, 12));
		Prompt_Box.setBounds(291, 56, 135, 25);
		panel_4.add(Prompt_Box);
		
		JLabel lblDes_2 = new JLabel("AES\u5BC6\u94A5");
		lblDes_2.setFont(new Font("宋体", Font.PLAIN, 12));
		
		AESKey_text = new JTextField();
		AESKey_text.setColumns(10);
		
		JLabel lblDes_1_1 = new JLabel("\u5BC6\u94A5\u786E\u8BA4");
		lblDes_1_1.setFont(new Font("宋体", Font.PLAIN, 12));
		
		AESKeyConfirm = new JTextField();
		AESKeyConfirm.setColumns(10);
		
		JLabel label_2 = new JLabel("\u6CE8\uFF1A\u5BC6\u94A5\u957F\u5EA6\u4E3A16\u4F4D");
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblDes_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(AESKey_text, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblDes_1_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AESKeyConfirm, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addGap(5)
									.addComponent(lblDes_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
								.addComponent(AESKey_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(13)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(AESKeyConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDes_1_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(label_2)
							.addGap(23)))
					.addGap(22))
		);
		panel_3.setLayout(gl_panel_3);
		
		RSAkey_text = new JTextField();
		RSAkey_text.setEditable(false);
		RSAkey_text.setColumns(10);
		
		RSAkey_Lab = new JLabel("\u5BC6\u94A5");
		RSAkey_Lab.setFont(new Font("宋体", Font.PLAIN, 12));
		
		
		
		n_Lab = new JLabel("\u6A21\u6570");
		n_Lab.setFont(new Font("宋体", Font.PLAIN, 12));
		
		n_text = new JTextField();
		n_text.setEditable(false);
		n_text.setColumns(10);
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(n_Lab, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(RSAkey_Lab, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(RSAkey_text, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(importkey_btn))
						.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(n_text, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(importn_btn, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(RSAkey_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(importkey_btn)
						.addComponent(RSAkey_Lab, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(n_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(importn_btn)
						.addComponent(n_Lab, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblDes = new JLabel("DES\u5BC6\u94A5");
		lblDes.setFont(new Font("宋体", Font.PLAIN, 12));
		
		
		DESKey_text.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO:判断是否是8位，不是弹出对话框提醒。
				
			}
		});
		DESKey_text.setColumns(10);
		
		JLabel lblDes_1 = new JLabel("\u5BC6\u94A5\u786E\u8BA4");
		lblDes_1.setFont(new Font("宋体", Font.PLAIN, 12));
		
		DESKeyConfirm_text = new JTextField();
		DESKeyConfirm_text.setColumns(10);
		
		JLabel label = new JLabel("\u6CE8\uFF1A\u5BC6\u94A5\u957F\u5EA6\u4E3A8\u4F4D");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDes, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(DESKey_text, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(label))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblDes_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(DESKeyConfirm_text, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDes, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(DESKey_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDes_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
						.addComponent(DESKeyConfirm_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(26)
					.addComponent(label))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u6587\u4EF6");
		lblNewLabel.setBounds(20, 38, 48, 15);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		
		InputFile = new JTextField();
		InputFile.setBounds(74, 33, 301, 24);
		
		InputFile.setColumns(10);
		
		JButton FileBrowse = new JButton("\u6D4F\u89C8\u6587\u4EF6");
		FileBrowse.setBounds(383, 34, 87, 23);
		FileBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc1 = new JFileChooser(File_Obj.getProjectpath());			
				int returnVal = fc1.showOpenDialog(null);
				if (returnVal==fc1.APPROVE_OPTION) {
					//正常选择文件
					InputFile.setText(fc1.getSelectedFile().toString());
					String oldPath = new String(fc1.getSelectedFile().toString());
					String prefix = oldPath.substring(oldPath.lastIndexOf("."));
					String newPath = new String();
					if (prefix.equals(".des")||(prefix.equals(".aes"))||(prefix.equals(".rsa"))) {
						Enc_Btn.setText("解密");
						if(prefix.equals(".des")) {
						Mode = 1;//判断是DES解密
						AESKey_text.setEnabled(false);
						AESKeyConfirm.setEnabled(false);
						RSAkey_text.setEnabled(false);
						}
						if(prefix.equals(".aes")) {
							Mode = 2;//判断是AES解密
							DESKey_text.setEnabled(false);
							DESKeyConfirm_text.setEnabled(false);
							RSAkey_text.setEnabled(false);
						}
						if(prefix.equals(".rsa")) {
							Mode =3;//判断是RSA解密
							AESKey_text.setEnabled(false);
							AESKeyConfirm.setEnabled(false);
							DESKey_text.setEnabled(false);
							DESKeyConfirm_text.setEnabled(false);
						}
					label_1.setEnabled(false);
					DES_rBtn.setEnabled(false);
					AES_rBtn.setEnabled(false);
					RSA_rBtn.setEnabled(false);
					StringBuffer OldPath = new StringBuffer(oldPath);
					newPath = OldPath.delete(oldPath.lastIndexOf("."),oldPath.length()).toString();
					}
					else {
						Mode = 0;//判断是加密
						label_1.setEnabled(true);
						DES_rBtn.setEnabled(true);
						AES_rBtn.setEnabled(true);
						RSA_rBtn.setEnabled(true);
						StringBuffer OldPath = new StringBuffer(oldPath);
						if (DES_rBtn.isSelected())
					newPath = OldPath.append(".des").toString();
						else if(AES_rBtn.isSelected())
							newPath = OldPath.append(".aes").toString();
						else if(RSA_rBtn.isSelected()) {
							newPath = OldPath.append(".rsa").toString();
							ReadFile f1 = new ReadFile();
							try {
							byte[] M = f1.getContent(fc1.getSelectedFile().toString());
							BigInteger MB = new BigInteger(M);
							String MBS = MB.toString(16);
							int len = MBS.length();
							JOptionPane.showMessageDialog(FileBrowse, "RSA算法的模数n的位数必须大于"+len);
							}catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
					OutputFile.setText(newPath);
				}
				else {
					//选择取消按钮
					InputFile.setText("未选择文件");
			}
				}
		});
		FileBrowse.setFont(new Font("宋体", Font.PLAIN, 12));
		
		JLabel lblNewLabel_1 = new JLabel("\u8F93\u51FA\u6587\u4EF6");
		lblNewLabel_1.setBounds(20, 67, 48, 15);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
		
		OutputFile = new JTextField();
		OutputFile.setBounds(74, 64, 300, 24);
		OutputFile.setColumns(10);
		
		JButton DirectorySelect_output = new JButton("\u9009\u62E9\u76EE\u5F55");
		DirectorySelect_output.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc1 = new JFileChooser(File_Obj.getProjectpath());			
				int returnVal = fc1.showOpenDialog(null);
				if (returnVal==fc1.APPROVE_OPTION) {
					//正常选择文件
					OutputFile.setText(fc1.getSelectedFile().toString());
				}
				else {
					//选择取消按钮
					OutputFile.setText("未选择文件");
			}
			}
		});
		DirectorySelect_output.setBounds(382, 65, 88, 23);
		DirectorySelect_output.setFont(new Font("宋体", Font.PLAIN, 12));
		
		JCheckBox TolerantPath_Box = new JCheckBox("\u8F93\u51FA\u6587\u4EF6\u7684\u9ED8\u8BA4\u8DEF\u5F84");
		
		

		TolerantPath_Box.setBounds(20, 97, 137, 25);
		TolerantPath_Box.setFont(new Font("宋体", Font.PLAIN, 12));
		
		JLabel lblNewLabel_1_1 = new JLabel("\u8F93\u51FA\u6587\u4EF6\u9ED8\u8BA4\u8DEF\u5F84");
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setBounds(31, 129, 100, 15);
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 12));
		
		TolerantPath_text = new JTextField();
		TolerantPath_text.setEnabled(false);
		TolerantPath_text.setBounds(137, 126, 232, 20);
		TolerantPath_text.setColumns(10);
		
		JButton DirectorySelect_Tolerant = new JButton("\u9009\u62E9\u76EE\u5F55");
		DirectorySelect_Tolerant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc1 = new JFileChooser("d:\\");			
				int returnVal = fc1.showOpenDialog(null);
				if (returnVal==fc1.APPROVE_OPTION) {
					//正常选择文件
					TolerantPath_text.setText(fc1.getSelectedFile().toString());
				}
				else {
					//选择取消按钮
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
		contentPane.setLayout(gl_contentPane);
		TolerantPath_Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TolerantPath_Box.isSelected()) {
				lblNewLabel_1_1.setEnabled(true);
				TolerantPath_text.setEnabled(true);
				DirectorySelect_Tolerant.setEnabled(true);
				lblNewLabel_1.setEnabled(false);
				OutputFile.setEnabled(false);
				DirectorySelect_output.setEnabled(false);
				}
				else {
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
}
