package ui;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class encrypt_frame_string extends JFrame{
	public encrypt_frame_string () {
		setSize(new Dimension(504, 600));
		setResizable(false);
		setTitle("DES/AES/RSA\u52A0\u89E3\u5BC6");
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new TitledBorder(null, "\u9009\u9879", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel label_1 = new JLabel("\u52A0\u5BC6\u65B9\u5F0F");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(10, 40, 48, 15);
		panel_4.add(label_1);
		
		JRadioButton DES_rBtn = new JRadioButton("DES\u52A0\u5BC6");
		DES_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		DES_rBtn.setBounds(58, 38, 71, 25);
		panel_4.add(DES_rBtn);
		
		JRadioButton AES_rBtn = new JRadioButton("AES\u52A0\u5BC6");
		AES_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		AES_rBtn.setBounds(130, 38, 71, 25);
		panel_4.add(AES_rBtn);
		
		JRadioButton RSA_rBtn = new JRadioButton("RSA\u52A0\u5BC6");
		RSA_rBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		RSA_rBtn.setBounds(201, 38, 71, 25);
		panel_4.add(RSA_rBtn);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(312, Short.MAX_VALUE)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addGap(166))
		);
		getContentPane().setLayout(groupLayout);
		
	}
}
