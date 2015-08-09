package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
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
		clientDetailsBean.setCurrentVersion(txtCurrentVersion.getText());
		clientDetailsBean.setTargetVersion(txtTargetVersion.getText());
		clientDetailsBean.setPerformedBy(txtPerformedBy.getText());
		
		enableReportSelectedFlag();
		DBUpgradeMain.dbUpgradeMain.initPanels(true);
		enableParentNextButton();
	}

	private void initialMergeButtonActionPerformed() {
		clientDetailsBean.setMergeFlag(false);
		clientDetailsBean.setInitialMergeFlag(true);
		// clientDetailsBean.setPreProdFlag(false);
		clientDetailsBean.setClientName(txtClientName.getText());
		clientDetailsBean.setCurrentVersion(txtCurrentVersion.getText());
		clientDetailsBean.setTargetVersion(txtTargetVersion.getText());
		clientDetailsBean.setPerformedBy(txtPerformedBy.getText());
		
		enableReportSelectedFlag();
		DBUpgradeMain.dbUpgradeMain.initPanels(false);
		enableParentNextButton();
	}

	private void enableParentNextButton() {
		parentNextButton.setEnabled(true);
	}

	private void enableReportSelectedFlag() {
		ClientDetailsBean.reportSelectedFlag = true;
	}

	private void initComponents() {

		super.setLayout(new BorderLayout());
		lblPanelName = new JLabel();
		mergeAnalysisButton = new JRadioButton();
		initialMergeButton = new JRadioButton();
		patchUpgradeButton = new JRadioButton();
		radioButtonGroup = new ButtonGroup();
		radioPanel = new JPanel();
		mainPanel = new JPanel();
		gbc = new GridBagConstraints();
		dummyLabel = new JLabel();

		lblPanelName
				.setText("<html> <body><h3><b>Choose the option to proceed<b></h3> <br><br> </body></html>");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		mergeAnalysisButton.setText("Merge Analysis       ");
		mergeAnalysisButton.setFont(myFont);
		mergeAnalysisButton.setActionCommand("Merge Analysis");

		patchUpgradeButton.setText("Patch Upgrade       ");
		patchUpgradeButton.setFont(myFont);
		patchUpgradeButton.setActionCommand("Patch Upgrade");
		
		initialMergeButton.setText("Initial Upgrade       ");
		initialMergeButton.setFont(myFont);
		initialMergeButton.setActionCommand("Initial upgrade");

		lblClientName = new JLabel();
		lblClientName.setText("Client Prefix: ");
		lblClientName.setFont(myFont);
		
		lblCurrentVersion = new JLabel();
		lblCurrentVersion.setText("Current Version: ");
		lblCurrentVersion.setFont(myFont);

		lblTargetVersion = new JLabel();
		lblTargetVersion.setText("Target Version: ");
		lblTargetVersion.setFont(myFont);
		
		lblPerformedBy = new JLabel();
		lblPerformedBy.setText("Performed By: ");
		lblPerformedBy.setFont(myFont);
		
		txtClientName = new JTextField(10);
		txtClientName.setText("DEV");

		txtTargetVersion = new JTextField(10);
		txtTargetVersion.setText("HRMS2015");

		txtCurrentVersion = new JTextField(10);
		txtCurrentVersion.setText("4.16");

		txtPerformedBy = new JTextField(10);
		txtPerformedBy.setText("Upgrade Team");

		
		radioButtonGroup.add(mergeAnalysisButton);
		radioButtonGroup.add(patchUpgradeButton);
		radioButtonGroup.add(initialMergeButton);		
		

		// Put the radio buttons in a column in a panel.
		radioPanel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.anchor = GridBagConstraints.WEST;

		// radioPanel.add(lblPanelName);
		radioPanel.add(dummyLabel, gbc);
		gbc.gridx++;
		radioPanel.add(mergeAnalysisButton, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		radioPanel.add(dummyLabel, gbc);
		gbc.gridx++;
		radioPanel.add(patchUpgradeButton, gbc);

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
		
		gbc.gridx = 0;
		gbc.gridy++;
		radioPanel.add(lblCurrentVersion, gbc);
		gbc.gridx++;
		radioPanel.add(txtCurrentVersion, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		radioPanel.add(lblTargetVersion, gbc);
		gbc.gridx++;
		radioPanel.add(txtTargetVersion, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		radioPanel.add(lblPerformedBy, gbc);
		gbc.gridx++;
		radioPanel.add(txtPerformedBy, gbc);
		
		// radioPanel.add(preProdButton, gbc);

		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new JLabel("<html> <body><br><br> </body></html>"));
		mainPanel.add(radioPanel, BorderLayout.PAGE_START);

		super.add(lblPanelName, BorderLayout.NORTH);
		super.add(mainPanel, BorderLayout.CENTER);

		mergeAnalysisButton.addActionListener(new java.awt.event.ActionListener() {
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
	}

	private static final long serialVersionUID = 1L;
	private JPanel radioPanel, mainPanel;
	private JLabel lblPanelName, lblClientName, dummyLabel, lblCurrentVersion,lblTargetVersion,lblPerformedBy;
	private JTextField txtClientName, txtCurrentVersion,txtTargetVersion,txtPerformedBy;
	private Font myFont = new Font("sansserif", Font.BOLD, 12);
	private JRadioButton mergeAnalysisButton, initialMergeButton, patchUpgradeButton;
	private ButtonGroup radioButtonGroup;
	private ClientDetailsBean clientDetailsBean;
	private JButton parentNextButton;
	private GridBagConstraints gbc;

	public JRadioButton getMergeButton() {
		return mergeAnalysisButton;
	}

	public void setMergeButton(JRadioButton mergeButton) {
		this.mergeAnalysisButton = mergeButton;
	}

	public JRadioButton getInitialMergeButton() {
		return initialMergeButton;
	}

	public void setInitialMergeButton(JRadioButton initialMergeButton) {
		this.initialMergeButton = initialMergeButton;
	}

}// class closed