package com.vg.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class ReportMenuForm extends JPanel {

	private static final long serialVersionUID = 1L;

	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

	private JLabel lblPanelName;
	static String mergeString = "Merge Analysis";
	static String initialMergeString = "Initial upgrade";
	static String preProdString = "Pre-Production Upgrade";

	public ReportMenuForm(final ClientDetailsBean clientDetailsBean,
			final JButton parentNextButton, String lblPanelNameString) {

		super(new BorderLayout());

		String text = "<html> <body><h3> " + lblPanelNameString
				+ "</h3> <br><br><br> </body></html>";
		lblPanelName = new JLabel(text);
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		JRadioButton mergeButton = new JRadioButton(mergeString);
		mergeButton.setActionCommand(mergeString);

		JRadioButton initialMergeButton = new JRadioButton(initialMergeString);
		initialMergeButton.setActionCommand(initialMergeString);

		JRadioButton preProdButton = new JRadioButton(preProdString);
		preProdButton.setActionCommand(preProdString);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(mergeButton);
		group.add(initialMergeButton);
		group.add(preProdButton);

		// Put the radio buttons in a column in a panel.
		JPanel radioPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.WEST;

		radioPanel.add(lblPanelName);

		radioPanel.add(mergeButton, gbc);
		radioPanel.add(initialMergeButton, gbc);
		radioPanel.add(preProdButton, gbc);

		add(radioPanel, BorderLayout.NORTH);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		mergeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clientDetailsBean.setGenerateReportFlag(false);
				clientDetailsBean.setMergeFlag(true);
				clientDetailsBean.setInitialMergeFlag(false);
				clientDetailsBean.setPreProdFlag(false);

				ClientDetailsBean.reportSelectedFlag = true;
				parentNextButton.setEnabled(true);
			}
		});

		initialMergeButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {

						clientDetailsBean.setGenerateReportFlag(false);
						clientDetailsBean.setMergeFlag(false);
						clientDetailsBean.setInitialMergeFlag(true);
						clientDetailsBean.setPreProdFlag(false);

						ClientDetailsBean.reportSelectedFlag = true;
						parentNextButton.setEnabled(true);
					}
				});

		preProdButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clientDetailsBean.setGenerateReportFlag(false);
				clientDetailsBean.setMergeFlag(false);
				clientDetailsBean.setInitialMergeFlag(false);
				clientDetailsBean.setPreProdFlag(true);

				ClientDetailsBean.reportSelectedFlag = true;
				parentNextButton.setEnabled(true);
			}
		});

	}// constructor closed

}// class closed