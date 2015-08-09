package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;
import com.nuview.upgrade.util.ConfigProperty;

public class OpenReportPanel extends JPanel {

	public OpenReportPanel(final JPanel cardsPanel) {

		this.cardsPanel = cardsPanel;
		initComponents();

	}

	private void initComponents() {

		super.setLayout(new BorderLayout());

		clientDBPanel = new JPanel();
		btnInitialUpgrade = new JButton(
				"<html><b>Proceed for Initial Upgrade</b></html>");
		btnInitialUpgrade.setPreferredSize(new Dimension(220, 25));
		btnInitialUpgrade.setFont(new Font("sansserif", Font.BOLD, 12));
		clientDBPanel.add(btnInitialUpgrade);

		mainPanel = new JPanel(new BorderLayout());
		labelPanel = new JPanel(new BorderLayout());

		lblPanelName = new JLabel("<html><b>View Report</b></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(lblPanelName, BorderLayout.NORTH);
		mainPanel.setPreferredSize(new Dimension(200, 100));

		lblShowProgress1 = new JLabel(
				"<html><b>&emsp &emsp Report generation Success &emsp &emsp</b></html");
		lblShowProgress1.setFont(lblShowProgress1.getFont().deriveFont(15.0f));

		// mainPanel.add(lblShowProgress1);

		ConfigProperty config = ConfigProperty.getInstance();
		filePath = config.getClientWorkingDir();

		lblFileLink = new JLabel(
				"<html><b><u><font color='blue'>Open file location</font></u></b></html>");
		lblFileLink.setFont(lblShowProgress1.getFont().deriveFont(15.0f));
		lblFileLink.setToolTipText("<html><b>" + filePath + "</b></html>");
		lblFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		labelPanel.add(lblShowProgress1, BorderLayout.LINE_START);
		labelPanel.add(lblFileLink, BorderLayout.CENTER);
		labelPanel.setPreferredSize(new Dimension(100, 100));

		this.add(mainPanel, BorderLayout.NORTH);
		this.add(labelPanel, BorderLayout.CENTER);
		this.add(clientDBPanel, BorderLayout.SOUTH);

		lblFileLink.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblFileLinkMouseClicked();
			}
		});

		btnInitialUpgrade
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e) {
						btnInitialUpgradeActionPerformed();
					}
				});
	}

	private void btnInitialUpgradeActionPerformed() {

		ClientDetailsBean.initialMergeButtonSelected = true;
		DBUpgradeMain.dbUpgradeMain.initPanels(false);
		System.out.println("OpenreportPanel......");
	}

	private void lblFileLinkMouseClicked() {

		try {
			ConfigProperty config = ConfigProperty.getInstance();
			filePath = config.getClientWorkingDir();
			Desktop.getDesktop().open(new File(filePath));
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(getParent(), e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static final long serialVersionUID = 1L;
	private JLabel lblPanelName, lblFileLink, lblShowProgress1;
	private JButton btnInitialUpgrade;
	private JPanel cardsPanel, clientDBPanel, labelPanel, mainPanel;
	private String filePath;

}