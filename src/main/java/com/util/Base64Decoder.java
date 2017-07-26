package com.util;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Base64Decoder extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Base 64 Decoder";
	private Dimension FRAME_DIMENSION = new Dimension(800, 200);
	private int LINE_HEIGHT = 30;
	private int LINE_GAP = 5;
	
	private JPanel row1;
	private JPanel row2;
	private JPanel row3;
	private JLabel lblCif;
	private JLabel lblAssertion;
	private JLabel lblTokenGenerator;
	private JLabel lblRegion;
	private JButton btnGenerate;
	private JButton btnBrowse;
	private JTextField txtPath;
	private JTextField txtCif;
	private JTextField txtRegion;
	private JTextField txtAssertion;
	
	public Base64Decoder() {
		initComponents();
	}
	
	public void initComponents() {
		row1 = new JPanel();
		row2 = new JPanel();
		row3 = new JPanel();
		lblCif = new JLabel("Enter CIF");
		lblAssertion = new JLabel("Assertion");
		lblTokenGenerator = new JLabel("SAML Token Generator Path");
		lblRegion = new JLabel("Region (like expraptor-test)");
		txtCif = new JTextField();
		txtAssertion = new JTextField();
		txtRegion = new JTextField();
		txtPath = new JTextField("C:\\SAMLToken\\GenerateSAMLToken.exe");
		btnGenerate = new JButton("Generate");
		btnBrowse = new JButton("Browse");
		
		initRow(row1);
		initRow(row2);
		initRow(row3);
		
		txtPath.setPreferredSize(new Dimension((int)(FRAME_DIMENSION.getWidth()*0.6), LINE_HEIGHT));
		txtCif.setPreferredSize(new Dimension((int)FRAME_DIMENSION.getWidth()/2, LINE_HEIGHT));
		txtAssertion.setPreferredSize(new Dimension((int)(FRAME_DIMENSION.getWidth()*0.8), LINE_HEIGHT));
		txtAssertion.setSize(new Dimension((int)(FRAME_DIMENSION.getWidth()*0.8), LINE_HEIGHT));
		
		row1.add(lblTokenGenerator);
		row1.add(txtPath);
		row1.add(btnBrowse);
		
		row2.add(lblCif);
		row2.add(txtCif);
		row2.add(btnGenerate);
		
		row3.add(lblAssertion);
		row3.add(txtAssertion);
		
		GridLayout layout = new GridLayout(3, 1);
		setLayout(layout);
		add(row1);
		add(row2);
		add(row3);
		
		setSize(FRAME_DIMENSION);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle(TITLE);
		
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnGenerateActionPerformed(e);
			}
		});
	}
	
	private void initRow(JPanel row) {
		row.setPreferredSize(new Dimension((int)FRAME_DIMENSION.getWidth()-20, LINE_HEIGHT + LINE_GAP));		
		row.setSize(new Dimension((int)FRAME_DIMENSION.getWidth()-20, LINE_HEIGHT + LINE_GAP));
		row.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void btnGenerateActionPerformed(ActionEvent e) {
		String cif = txtCif.getText();
		String region = txtRegion.getText();
		if (null != cif && cif.length()>0) {
			ProcessBuilder pb = new ProcessBuilder(txtPath.getText(), region, cif);
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		        new Base64Decoder();
		    }
		});
	}
}
