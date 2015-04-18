package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;

public class ReportMenuForm extends JPanel {

	public ReportMenuForm(final ClientDetailsBean clientDetailsBean,
			final JButton parentNextButton) {
		super();
		this.clientDetailsBean = clientDetailsBean;
		this.parentNextButton = parentNextButton;
		initComponents();
	}// constructor closed

	private void mergeButtonActionPerformed() {
		clientDetailsBean.setMergeFlag(true);
		clientDetailsBean.setInitialMergeFlag(false);
		// clientDetailsBean.setPreProdFlag(false);
		clientDetailsBean.setClientName(txtClientName.getText());
		
		enableReportSelectedFlag();
		DBUpgradeMain.dbUpgradeMain.initPanels(true);
		enableParentNextButton();
	}

	private void initialMergeButtonActionPerformed() {
		clientDetailsBean.setMergeFlag(false);
		clientDetailsBean.setInitialMergeFlag(true);
		// clientDetailsBean.setPreProdFlag(false);
		clientDetailsBean.setClientName(txtClientName.getText());

		enableReportSelectedFlag();
		DBUpgradeMain.dbUpgradeMain.initPanels(false);
		enableParentNextButton();
	}
	
	/*private void preprodButtonActionPerformed(){
		clientDetailsBean.setMergeFlag(false);
		clientDetailsBean.setInitialMergeFlag(false);
		clientDetailsBean.setPreProdFlag(true);

		enableReportSelectedFlag();
		enableParentNextButton();
	}*/

	private void enableParentNextButton() {
		parentNextButton.setEnabled(true);
	}

	private void enableReportSelectedFlag() {
		ClientDetailsBean.reportSelectedFlag = true;
	}

	private void initComponents() {

		super.setLayout(new BorderLayout());
		lblPanelName = new JLabel();
		mergeButton = new JRadioButton();
		initialMergeButton = new JRadioButton();
		// preProdButton = new JRadioButton();
		radioButtonGroup = new ButtonGroup();
		radioPanel = new JPanel();
		mainPanel = new JPanel();
		gbc = new GridBagConstraints();
		dummyLabel = new JLabel();

		lblPanelName
				.setText("<html> <body><h3><b>Steps in upgrade process<b></h3> <br><br> </body></html>");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		mergeButton.setText("Merge Analysis       ");
		mergeButton.setFont(myFont);
		mergeButton.setActionCommand("Merge Analysis");

		initialMergeButton.setText("Initial upgrade       ");
		initialMergeButton.setFont(myFont);
		initialMergeButton.setActionCommand("Initial upgrade");

		/*
		  preProdButton.setText("Pre-Production Upgrade");
		  preProdButton.setFont(myFont);
		  preProdButton.setActionCommand("Pre-Production Upgrade");
		 */

		lblClientName = new JLabel();
		lblClientName.setText("Client Name: ");
		lblClientName.setFont(myFont);

		txtClientName = new JTextField(10);
		txtClientName.setText("Test DB client");
		
		radioButtonGroup.add(mergeButton);
		radioButtonGroup.add(initialMergeButton);
		// radioButtonGroup.add(preProdButton);

		// Put the radio buttons in a column in a panel.
		radioPanel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.anchor = GridBagConstraints.WEST;

		// radioPanel.add(lblPanelName);
		radioPanel.add(dummyLabel, gbc);
		gbc.gridx++;
		radioPanel.add(mergeButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		radioPanel.add(dummyLabel, gbc);
		gbc.gridx++;
		radioPanel.add(initialMergeButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		radioPanel.add(lblClientName, gbc);
		gbc.gridx++;
		radioPanel.add(txtClientName, gbc);
		// radioPanel.add(preProdButton, gbc);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new JLabel("<html> <body><br><br> </body></html>"));
		mainPanel.add(radioPanel,  BorderLayout.PAGE_START);

		super.add(lblPanelName, BorderLayout.NORTH);
		super.add(mainPanel, BorderLayout.CENTER);

		mergeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!clientDetailsBean.isMergeFlag())
					mergeButtonActionPerformed();
			}
		});

		initialMergeButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!clientDetailsBean.isInitialMergeFlag())
							initialMergeButtonActionPerformed();
					}
				});
		/*preProdButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preprodButtonActionPerformed();
			}
		});*/
		
		/*txtClientName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null, "Invalid Character",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					e.consume();
				}
			}
		});*/
	}

	private static final long serialVersionUID = 1L;
	private JPanel radioPanel, mainPanel;
	private JLabel lblPanelName, lblClientName, dummyLabel;
	private JTextField txtClientName;
	private Font myFont = new Font("sansserif",Font.BOLD,12);
	private JRadioButton mergeButton, initialMergeButton;//, preProdButton;
	private ButtonGroup radioButtonGroup;
	private ClientDetailsBean clientDetailsBean;
	private JButton parentNextButton;
	private GridBagConstraints gbc;
	
	
	public JRadioButton getMergeButton() {
		return mergeButton;
	}

	public void setMergeButton(JRadioButton mergeButton) {
		this.mergeButton = mergeButton;
	}

	public JRadioButton getInitialMergeButton() {
		return initialMergeButton;
	}

	public void setInitialMergeButton(JRadioButton initialMergeButton) {
		this.initialMergeButton = initialMergeButton;
	}
	
}// class closed