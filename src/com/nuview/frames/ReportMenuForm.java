package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

import com.nuview.model.ClientDetailsBean;

public class ReportMenuForm extends JPanel {

	public ReportMenuForm(final ClientDetailsBean clientDetailsBean,
			final JButton parentNextButton) {
		super();
		this.clientDetailsBean = clientDetailsBean;
		this.parentNextButton = parentNextButton;
		initComponents();
		
	}// constructor closed
	
	private void mergeButtonActionPerformed(){
		clientDetailsBean.setMergeFlag(true);
		clientDetailsBean.setInitialMergeFlag(false);
		clientDetailsBean.setPreProdFlag(false);

		enableReportSelectedFlag();
		enableParentNextButton();
	}
	
	private void initialMergeButtonActionPerformed(){
		clientDetailsBean.setMergeFlag(false);
		clientDetailsBean.setInitialMergeFlag(true);
		clientDetailsBean.setPreProdFlag(false);

		enableReportSelectedFlag();
		enableParentNextButton();
	}
	
	private void preprodButtonActionPerformed(){
		clientDetailsBean.setMergeFlag(false);
		clientDetailsBean.setInitialMergeFlag(false);
		clientDetailsBean.setPreProdFlag(true);

		enableReportSelectedFlag();
		enableParentNextButton();
	}
	
	private void enableParentNextButton(){
		parentNextButton.setEnabled(true);
	}
	
	private void enableReportSelectedFlag(){
		ClientDetailsBean.reportSelectedFlag = true;
	}
	
	private void initComponents(){
		
		super.setLayout(new BorderLayout());
		lblPanelName = new JLabel();
		mergeButton = new JRadioButton();
		initialMergeButton = new JRadioButton();
		preProdButton = new JRadioButton();
		radioButtonGroup = new ButtonGroup();
		radioPanel = new JPanel();
		gbc = new GridBagConstraints();
		
		lblPanelName.setText("<html> <body><h3><b>Steps in upgrade process<b></h3> <br><br> </body></html>");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);
		
		mergeButton.setText("Merge Analysis");
		mergeButton.setFont(myFont);
		mergeButton.setActionCommand("Merge Analysis");
		
		initialMergeButton.setText("Initial upgrade");
		initialMergeButton.setFont(myFont);
		initialMergeButton.setActionCommand("Initial upgrade");
		
		preProdButton.setText("Pre-Production Upgrade");
		preProdButton.setFont(myFont);
		preProdButton.setActionCommand("Pre-Production Upgrade");

		radioButtonGroup.add(mergeButton);
		radioButtonGroup.add(initialMergeButton);
		radioButtonGroup.add(preProdButton);

		// Put the radio buttons in a column in a panel.
		radioPanel.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.anchor = GridBagConstraints.WEST;
		radioPanel.add(lblPanelName);
		radioPanel.add(mergeButton, gbc);
		radioPanel.add(initialMergeButton, gbc);
		radioPanel.add(preProdButton, gbc);

		super.add(radioPanel, BorderLayout.NORTH);
		super.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		mergeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mergeButtonActionPerformed();
			}
		});

		initialMergeButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						initialMergeButtonActionPerformed();		
					}
				});

		preProdButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preprodButtonActionPerformed();
			}
		});

	}
	
	private static final long serialVersionUID = 1L;
	private JPanel radioPanel;
	private JLabel lblPanelName;
	private Font myFont = new Font("sansserif",Font.BOLD,12);
	private JRadioButton mergeButton, initialMergeButton, preProdButton;
	private ButtonGroup radioButtonGroup;
	private ClientDetailsBean clientDetailsBean;
	private JButton parentNextButton;
	private GridBagConstraints gbc;
	
}// class closed