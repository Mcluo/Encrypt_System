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

import javax.swing.JLabel;
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
import java.io.IOException;
import java.awt.event.ItemEvent;

public class encrypt_frame extends JFrame {

	private JPanel contentPane;
    public static JTextField InputFile;
	public static JTextField OutputFile;
	private JTextField TolerantPath_text;
	public static JTextField DESKey_text,AESKey_text,InputFile_RSA;
	private JTextField DESKeyConfirm_text;
	public static int Mode;
	private JTextField AESKeyConfirm;
	public static ButtonGroup group;
	public static JRadioButton DES_rBtn;
	public static JRadioButton AES_rBtn;
	public static JRadioButton RSA_rBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					encrypt_frame frame = new encrypt_frame();
					frame.setVisible(true);
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
		setTitle("DES/AES/RSA\u52A0\u89E3\u5BC6");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 694);
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
		
		JButton Enc_Btn = new JButton("\u52A0\u5BC6");
		/**
		 * 点击加密按钮执行指定的加密算法和操作
		 */
		Enc_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					File_Obj fo = new File_Obj();
					
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		Enc_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		
		JButton Exit_Btn = new JButton("\u9000\u51FA");
		Exit_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(82)
					.addComponent(Enc_Btn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addComponent(Exit_Btn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(128, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
					.addContainerGap())
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
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Enc_Btn)
						.addComponent(Exit_Btn))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel label_1 = new JLabel("\u52A0\u5BC6\u65B9\u5F0F");
		label_1.setBounds(10, 40, 48, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		JButton FileBrowse_RSA = new JButton("\u6D4F\u89C8\u6587\u4EF6");
		FileBrowse_RSA.setFont(new Font("宋体", Font.PLAIN, 12));
		 DES_rBtn = new JRadioButton("DES\u52A0\u5BC6");
		DESKey_text = new JTextField();
		DES_rBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DESKey_text.setEnabled(true);
			DESKeyConfirm_text.setEnabled(true);
				AESKey_text.setEnabled(false);
				AESKeyConfirm.setEnabled(false);
				InputFile_RSA.setEnabled(false);
				FileBrowse_RSA.setEnabled(false);
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
			InputFile_RSA.setEnabled(false);
				FileBrowse_RSA.setEnabled(false);
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
			InputFile_RSA.setEnabled(true);
				FileBrowse_RSA.setEnabled(true);
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
		
		InputFile_RSA = new JTextField();
		InputFile_RSA.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u8F93\u5165\u6587\u4EF6");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 12));
		
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(InputFile_RSA, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(FileBrowse_RSA, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addComponent(InputFile_RSA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(1)
							.addComponent(FileBrowse_RSA, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(43, Short.MAX_VALUE))
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
		FileBrowse.setBounds(383, 34, 81, 23);
		FileBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc1 = new JFileChooser("d:\\");			
				int returnVal = fc1.showOpenDialog(null);
				if (returnVal==fc1.APPROVE_OPTION) {
					//正常选择文件
					InputFile.setText(fc1.getSelectedFile().toString());
					String oldPath = new String(fc1.getSelectedFile().toString());
					String prefix = oldPath.substring(oldPath.lastIndexOf("."));
					String newPath = new String();
					if (prefix.equals(".des")||(prefix.equals(".aes"))||(prefix.equals(".rsa"))) {
						Mode = 1;//判断是解密
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
						else if(RSA_rBtn.isSelected())
							newPath = OldPath.append(".rsa").toString();
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
				JFileChooser fc1 = new JFileChooser("d:\\");			
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
		DirectorySelect_output.setBounds(382, 65, 82, 23);
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
