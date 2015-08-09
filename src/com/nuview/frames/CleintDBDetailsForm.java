package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;
import com.nuview.upgrade.action.UpgradeAction;
import com.nuview.upgrade.common.Constancts.ACTION;

public class CleintDBDetailsForm extends JPanel {

	public CleintDBDetailsForm(final ClientDetailsBean clientDetailsBean,
			final JButton parentNextButton, final String formId) {
		super();
		this.clientDetailsBean = clientDetailsBean;
		this.parentNextButton = parentNextButton;
		this.formId = formId;

		initComponents();

	}// constructor closed

	private void initComponents() {

		super.setLayout(new BorderLayout());
		picture = new JLabel("");
		myFont = new Font("sansserif", Font.BOLD, 12);

		// picture.setPreferredSize(new Dimension(126, 33));
		picture.setPreferredSize(new Dimension(28, 26));

		if ("c1".equals(formId)) {
			lblPanelNameString = "Client DB Details";
		} else if ("c2".equals(formId)) {
			lblPanelNameString = "Standard Old Version DB Details";
		} else if ("c3".equals(formId)) {
			lblPanelNameString = "Standard New Version DB Details";
		}

		lblPanelName = new JLabel("<html><h3>" + lblPanelNameString
				+ "</h3></html>");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		lblHostName = new JLabel("Host Name");
		lblSqlInstanceName = new JLabel("SQL Instance Name");
		lblDBName = new JLabel("DB Name");
		lblPort = new JLabel("Port");
		lblUserName = new JLabel("Username");
		lblPassword = new JLabel("Password");

		lblHostName.setFont(myFont);
		lblSqlInstanceName.setFont(myFont);
		lblDBName.setFont(myFont);
		lblPort.setFont(myFont);
		lblUserName.setFont(myFont);
		lblPassword.setFont(myFont);

		txtHostName = new JTextField(12);
		txtSqlInstanceName = new JTextField(12);
		txtDBName = new JTextField(12);
		txtPort = new JTextField(12);
		txtUsername = new JTextField(12);
		txtPassword = new JPasswordField(12);

		txtHostName.setText("localhost");
		txtSqlInstanceName.setText("SQLEXPRESS");
		txtDBName.setText("NuPC0");
		txtPort.setText("1933");
		txtUsername.setText("nvsuperuser1");
		txtPassword.setText("nuview");

		btnTest = new JButton("<html><b>Test</b></html>");
		btnTest.setFont(myFont);

		// Put the Label and combo boxes in a column in a panel.
		clientDBPanel = new JPanel(new GridBagLayout());
		mainPanel = new JPanel(new BorderLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.anchor = GridBagConstraints.WEST;

		clientDBPanel.add(lblHostName, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtHostName, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblSqlInstanceName, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtSqlInstanceName, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblDBName, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtDBName, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblPort, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtPort, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblUserName, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtUsername, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblPassword, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtPassword, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		clientDBPanel.add(btnTest, gbc);

		gbc.gridx++;
		gbc.anchor = GridBagConstraints.WEST;
		clientDBPanel.add(picture, gbc);

		mainPanel.add(lblPanelName, BorderLayout.SOUTH);
		super.add(mainPanel, BorderLayout.NORTH);
		super.add(clientDBPanel, BorderLayout.CENTER);

		txtHostName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				/*if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null, "Invalid Character",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					e.consume();
				}*/

				// Disable Next button when text is changed
				parentNextButton.setEnabled(false);

				picture.setIcon(null);
			}
		});

		txtUsername.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (Character.isDigit(c)) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null, "Invalid Character",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					e.consume();
				}

				// Disable Next button when text is changed
				parentNextButton.setEnabled(false);

				picture.setIcon(null);
			}
		});

		txtPassword.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				// Disable Next button when text is changed
				parentNextButton.setEnabled(false);

				picture.setIcon(null);
			}
		});

		txtPort.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				// Disable Next button when text is changed
				parentNextButton.setEnabled(false);

				picture.setIcon(null);
			}
		});

		txtDBName.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				// Disable Next button when text is changed
				parentNextButton.setEnabled(false);

				picture.setIcon(null);
			}
		});

		btnTest.addActionListener(new java.awt.event.ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				btnTestActionPerformed();

			}
		});

	}

	/**
	 * Returns an ImageIcon, or null if the path was invalid.
	 */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = CleintDBDetailsForm.class.getResource(path);

		if (imgURL != null) {
			ImageIcon img = new ImageIcon(imgURL);
			return img;
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void resetField() {
		txtUsername.setText("");
		txtPassword.setText("");
	}

	private void btnTestActionPerformed() {
		// Input Validation
		if (txtHostName.getText() == null || txtHostName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter HostName",
					"Missing fields", JOptionPane.DEFAULT_OPTION);
			txtHostName.requestFocus();
			return;
		}

		if (txtDBName.getText() == null || txtDBName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter DBName",
					"Missing fields", JOptionPane.DEFAULT_OPTION);
			txtDBName.requestFocus();
			return;
		}

		if (txtPort.getText() == null || txtPort.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Port", "Missing fields",
					JOptionPane.DEFAULT_OPTION);
			txtPort.requestFocus();
			return;
		}

		if (txtPassword.getText() == null || txtPassword.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Enter Username",
					"Missing fields", JOptionPane.DEFAULT_OPTION);
			txtPassword.requestFocus();
			return;
		}

		if ("c1".equals(formId)) {
			clientDetailsBean.setHostName(txtHostName.getText());
			clientDetailsBean.setSqlInstanceName(txtSqlInstanceName.getText());
			clientDetailsBean.setDbName(txtDBName.getText());
			clientDetailsBean.setPortStr(txtPort.getText());
			clientDetailsBean.setUserName(txtUsername.getText());
			clientDetailsBean.setPassword(txtPassword.getText());
		} else if ("c2".equals(formId)) {
			clientDetailsBean.setOldHostName(txtHostName.getText());
			clientDetailsBean.setOldSqlInstanceName(txtSqlInstanceName
					.getText());
			clientDetailsBean.setOldDBName(txtDBName.getText());
			clientDetailsBean.setOldPortStr(txtPort.getText());
			clientDetailsBean.setOldUserName(txtUsername.getText());
			clientDetailsBean.setOldPassword(txtPassword.getText());
		} else if ("c3".equals(formId)) {
			clientDetailsBean.setNewHostName(txtHostName.getText());
			clientDetailsBean.setNewSqlInstanceName(txtSqlInstanceName
					.getText());
			clientDetailsBean.setNewDBName(txtDBName.getText());
			clientDetailsBean.setNewPortStr(txtPort.getText());
			clientDetailsBean.setNewUserName(txtUsername.getText());
			clientDetailsBean.setNewPassword(txtPassword.getText());
		}

		try {
			testCompleted = UpgradeAction.verifyDatabaseDetails(ACTION.getAction(formId), clientDetailsBean);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(getParent(), e1.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		if (testCompleted) {
			System.out.println("Enable Next button once Test is Success.. "
					+ "Button Name:: " + btnTest.getName());

			if ("c1".equals(formId)) {
				ClientDetailsBean.clientDetailsFlag = true;
			} else if ("c2".equals(formId)) {
				ClientDetailsBean.oldClientDetailsFlag = true;
			} else if ("c3".equals(formId)) {
				ClientDetailsBean.newClientDetailsFlag = true;
			}

			picture.setIcon(createImageIcon("/images/" + "verifiedNew" + ".png"));

			parentNextButton.setEnabled(true);
		} else {
			// display error message
			// JOptionPane.showMessageDialog(getParent(), e1.getMessage(),
			// "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static final long serialVersionUID = 1L;
	private JPanel clientDBPanel, mainPanel;
	private JLabel lblPanelName, lblHostName, lblDBName, lblSqlInstanceName,
			lblPort, lblUserName, lblPassword;
	private JPasswordField txtPassword;
	private JTextField txtHostName, txtSqlInstanceName, txtDBName, txtPort,
			txtUsername;
	private JButton btnTest;
	private JLabel picture;
	boolean testCompleted = false;
	private Font myFont;
	private String lblPanelNameString;
	private ClientDetailsBean clientDetailsBean;
	private JButton parentNextButton;
	private String formId;

}// class closed