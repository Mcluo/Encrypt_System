package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	private JPanel panel1;
	private JLabel l;
	private encrypt_frame ef;

	public MainFrame() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳���", "�˳�ϵͳ", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		getContentPane().setName("panel1");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MainFrame.class.getResource("/images/dsi \u6570\u636E\u52A0\u5BC6.png")));
		setTitle("DES/RSA����");
		setVisible(true);// ���ô���ɼ�
		/*
		 * ����رչ��� EXIT_ON_CLOSE������ر�button�����Զ����� DO_NOTHING_ON_CLOSE:���κβ���
		 * HIDE_ON_CLOSE�����ش��壬����ֹͣ���� DISPOSE_ON_CLOSE:�ͷŴ�����Դ
		 */
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// setSize(1000,500);
		// setLocation(200,200);
		setBounds(500, 200, 500, 277);

		// setContentPane(panel1);//��ȡ��������
		// setBackground(Color.white);
		// validate();//��֤���������ˢ�£�
		// setContentPane(panel1);//������������

		setResizable(false);
		JRadioButton radioButton = new JRadioButton("\u6587\u4EF6\u52A0\u5BC6");

		JRadioButton radioButton_1 = new JRadioButton("\u6570\u5B57\u7B7E\u540D");
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u64CD\u4F5C\u9009\u62E9",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (radioButton.isSelected()) {
					encrypt_frame.run();
				}

				if (radioButton_1.isSelected()) {
					DigSign_frame df = new DigSign_frame();
					df.setVisible(true);
				}

			}
		});
		button.setFont(new Font("����", Font.PLAIN, 18));

		JButton button_1 = new JButton("\u9000\u51FA");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(button_1, "��ʾ", "ȷ���˳�ϵͳ��", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.OK_OPTION)
					System.exit(0);
			}
		});
		button_1.setFont(new Font("����", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panel,
								GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup().addGap(122).addComponent(button).addGap(79)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(20, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(button)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(20, Short.MAX_VALUE)));

		JLabel label = new JLabel(" ");
		String Imagepath = "/images/\u7CFB\u7EDF.png";
		ImageIcon resource = setImageIconSize(Imagepath, 85, 85);
		label.setIcon(resource);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(53)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addGap(50).addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(radioButton_1).addComponent(radioButton))
								.addContainerGap(179, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				gl_panel.createSequentialGroup().addGap(30)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup().addComponent(radioButton).addGap(18)
										.addComponent(radioButton_1)))
						.addContainerGap(32, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
	}

	public ImageIcon setImageIconSize(String path, int width, int height) {
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
