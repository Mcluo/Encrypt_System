package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileView;

import fileIO.File_Obj;
import fileIO.ReadFile;

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
	public static JTextField inputFile;
	public static JTextField outputFile;
	public static JTextField Tolerantpath_text;
	public static JTextField RSAkey_text;
	public static JTextField n_text;
	public static JRadioButton md5_Btn, sha_Btn, veri_Btn;
	public static int Mode;
	public static JLabel RSAkey_Lab, n_Lab;
	public static JTextPane textPane, textPane_1;
	private JLabel LblNewLabel_1_1, lblNewLabel_1;
	private JButton DirectorySelect_Tolerant, DirectorySelect_output;
	private JButton button, read_Btn;

	public DigSign_frame() {
		setLocation(new Point(90, 90));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(DigSign_frame.class.getResource("/images/\u7B7E\u540D.png")));
		setResizable(false);
		getContentPane().setName("panel1");
		setSize(new Dimension(594, 558));
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\u6570\u5B57\u7B7E\u540D\u6587\u4EF6\u8F93\u5165/\u8F93\u51FA", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));

		JLabel lblNewLabel = new JLabel("\u8F93\u5165\u6587\u4EF6");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(20, 38, 48, 15);
		panel.add(lblNewLabel);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "确定要退出界面吗？", "退出界面", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION) {
					dispose();
				}
			}
		});
		read_Btn = new JButton("\u8BFB\u53D6");
		read_Btn.setEnabled(false);
		inputFile = new JTextField(10);
		inputFile.setBounds(74, 33, 301, 24);
		panel.add(inputFile);
		JButton Sign_Btn = new JButton("\u7B7E\u540D");
		Sign_Btn.setEnabled(false);
		Sign_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Obj fo = new File_Obj();
				try {
					fo.SignBtn();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		JButton Verify_Btn = new JButton("\u9A8C\u8BC1");
		Verify_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Obj fo = new File_Obj();
				try {
					fo.VeriBtn();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		Verify_Btn.setEnabled(false);

		JButton createkey_Btn = new JButton("\u4EA7\u751FRSA\u5BC6\u94A5\u5BF9");
		createkey_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyproduction_frame f = new keyproduction_frame();
				f.setVisible(true);
			}
		});

		JButton exit_Btn = new JButton("\u9000\u51FA");
		exit_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(exit_Btn, "提示", "确认退出吗？", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.OK_OPTION)
					dispose();
			}
		});
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
					outputFile.setText(newPath);
				} else {
					// 选择取消按钮
					inputFile.setText("未选择文件");
				}
			}
		});

		FileBrowse.setFont(new Font("宋体", Font.PLAIN, 12));
		FileBrowse.setBounds(383, 34, 87, 23);
		panel.add(FileBrowse);

		JCheckBox TolerantPath_Box = new JCheckBox("\u8F93\u51FA\u6587\u4EF6\u7684\u9ED8\u8BA4\u8DEF\u5F84");
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
				fc1.setFileView(new FileView() {
					public Icon getIcon(File f) {
						return fc1.getFileSystemView().getSystemIcon(f);
					}
				});
				int returnVal = fc1.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					// 正常选择文件
					Tolerantpath_text.setText(fc1.getSelectedFile().toString());
				} else {
					// 选择取消按钮
					Tolerantpath_text.setText("未选择文件");
				}
			}
		});
		DirectorySelect_Tolerant.setFont(new Font("宋体", Font.PLAIN, 12));
		DirectorySelect_Tolerant.setEnabled(false);
		DirectorySelect_Tolerant.setBounds(383, 125, 87, 23);
		panel.add(DirectorySelect_Tolerant);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "RSA\u5BC6\u94A5\u5BF9\u7684\u9009\u62E9", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		n_Lab = new JLabel("\u6A21\u6570");
		n_Lab.setFont(new Font("宋体", Font.PLAIN, 12));

		RSAkey_Lab = new JLabel("\u5BC6\u94A5");
		RSAkey_Lab.setFont(new Font("宋体", Font.PLAIN, 12));

		RSAkey_text = new JTextField();
		RSAkey_text.setEditable(false);
		RSAkey_text.setColumns(10);

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
						byte[] keybyte = input.getContent(read.getSelectedFile().toString());
						BigInteger Key = new BigInteger(keybyte);
						RSAkey_text.setText(Key.toString(16));
						if (DigSign_frame.veri_Btn.isSelected()) {
							DigSign_frame.RSAkey_Lab.setText("公钥(" + Key.toString(16).length() + ")");
						} else if (DigSign_frame.md5_Btn.isSelected() || DigSign_frame.sha_Btn.isSelected())
							DigSign_frame.RSAkey_Lab.setText("私钥(" + Key.toString(16).length() + ")");
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
										.addComponent(RSAkey_text, GroupLayout.PREFERRED_SIZE, 300,
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
								.addComponent(RSAkey_text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
		panel_1.setBorder(new TitledBorder(null, "\u9009\u9879", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(
				new TitledBorder(null, "\u7B7E\u540D\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBorder(
				new TitledBorder(null, "\u9A8C\u8BC1\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		read_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File_Obj fo = new File_Obj();
				try {
					fo.read_Btn();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		button = new JButton("\u91CD\u7F6E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputFile.setText("");
				outputFile.setText("");
				RSAkey_text.setText("");
				n_text.setText("");
				textPane.setText("");
				textPane_1.setText("");
				md5_Btn.setEnabled(true);
				sha_Btn.setEnabled(true);
				veri_Btn.setEnabled(true);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 560,
										Short.MAX_VALUE)
								.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
												.addComponent(Sign_Btn).addGap(18).addComponent(read_Btn).addGap(18)
												.addComponent(Verify_Btn).addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(createkey_Btn).addGap(28).addComponent(button)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(exit_Btn))
										.addGroup(Alignment.LEADING,
												groupLayout.createSequentialGroup()
														.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 266,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(panel_3_1, GroupLayout.PREFERRED_SIZE,
																265, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE).addGap(13)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addComponent(panel_3_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(Sign_Btn)
								.addComponent(read_Btn).addComponent(Verify_Btn).addComponent(createkey_Btn)
								.addComponent(exit_Btn).addComponent(button))
						.addContainerGap()));

		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		GroupLayout gl_panel_3_1 = new GroupLayout(panel_3_1);
		gl_panel_3_1.setHorizontalGroup(gl_panel_3_1.createParallelGroup(Alignment.LEADING).addComponent(textPane_1,
				GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE));
		gl_panel_3_1.setVerticalGroup(gl_panel_3_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3_1.createSequentialGroup()
						.addComponent(textPane_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_3_1.setLayout(gl_panel_3_1);

		textPane = new JTextPane();
		textPane.setEditable(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addComponent(textPane,
				GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(Alignment.LEADING).addComponent(textPane,
				GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE));
		panel_3.setLayout(gl_panel_3);

		md5_Btn = new JRadioButton("MD5");
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
		sha_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!veri_Btn.isSelected()) {
					Verify_Btn.setEnabled(false);
					read_Btn.setEnabled(false);
					Sign_Btn.setEnabled(true);
				}
			}
		});
		sha_Btn.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		veri_Btn = new JRadioButton("\u9A8C\u8BC1");
		veri_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (veri_Btn.isSelected()) {
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
		group.add(md5_Btn);
		group.add(sha_Btn);

		veri_Btn.setFont(new Font("宋体", Font.PLAIN, 15));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(67).addComponent(md5_Btn).addGap(33)
						.addComponent(sha_Btn, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(veri_Btn)
						.addContainerGap(86, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(md5_Btn)
								.addComponent(sha_Btn).addComponent(veri_Btn))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		getContentPane().setLayout(groupLayout);
		setTitle("数字签名");
		setVisible(true);// 设置窗体可见
	}
}
