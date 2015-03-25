package com.nuview.frames;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.nuview.model.ClientDetailsBean;
import com.nuview.model.ObjectsToMergeBean;
import com.nuview.upgrade.util.ConfigPropertyUtil;

public class ObjectsToMergeForm extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lblPanelName, lblForms, lblTables, lblAgents, lblWorkflows, lblScripts, lblFormControls, lblProperties, lblMenus, lblTableFields;
	private JTextField txtForms, txtTables, txtAgents, txtWorkflows, txtScripts, txtFormControls, txtProperties, txtMenus, txtTableFields;
	
	private JButton btnDownload;
	boolean testCompleted = false;
	private ConfigPropertyUtil propUtil = new ConfigPropertyUtil();

	public ObjectsToMergeForm(final FileListPanel fileListPanel, final ObjectsToMergeBean objectsToMergeBean, final JPanel cards,
			final JButton parentNextButton, String lblPanelNameString) {

		setLayout(new BorderLayout());

		btnDownload = new JButton("<html><b>Process</b></html>");

		btnDownload.setPreferredSize(new Dimension(220, 25));
		//btnDownload.setLocation(getWidth()/2-85, getHeight()/2-25);
		
		lblPanelName = new JLabel("<html><h3>" + lblPanelNameString
				+ "</h3></html");
		lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
		lblPanelName.setHorizontalAlignment(SwingConstants.CENTER);

		lblForms = new JLabel("FORMS:");
		lblTables = new JLabel("TABLES:");
		lblAgents = new JLabel("AGENTS:");
		lblWorkflows = new JLabel("WORK FLOWS:");
		lblScripts = new JLabel("SCRIPTS:");
		lblFormControls = new JLabel("FORM CONTROLS:");
		lblProperties = new JLabel("PROPERTIES:");
		lblMenus = new JLabel("MENUS:");
		lblTableFields = new JLabel("TABLE FIELDS:");
		
		
		txtForms = new JTextField(12);
		txtForms.setText("ALL");
		txtTables = new JTextField(12);
		txtTables.setText("ALL");
		txtAgents = new JTextField(12);
		txtAgents.setText("ALL");
		txtWorkflows = new JTextField(12);
		txtWorkflows.setText("ALL");
		txtScripts = new JTextField(12);
		txtScripts.setText("ALL");
		txtFormControls = new JTextField(12);
		txtFormControls.setText("ALL");	
		txtProperties = new JTextField(12);
		txtProperties.setText("ALL");	
		txtMenus = new JTextField(12);
		txtMenus.setText("ALL");	
		txtTableFields = new JTextField(12);
		txtTableFields.setText("ALL");	
		
		// Put the Label and combo boxes in a column in a panel.
		JPanel clientDBPanel = new JPanel(new GridBagLayout());

		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2, 2, 2, 2);
		gbc.anchor = GridBagConstraints.WEST;

		clientDBPanel.add(lblForms, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtForms, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblTables, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtTables, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblAgents, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtAgents, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblWorkflows, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtWorkflows, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblScripts, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtScripts, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblFormControls, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtFormControls, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblProperties, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtProperties, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblMenus, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtMenus, gbc);

		gbc.gridx = 0;
		gbc.gridy++;
		clientDBPanel.add(lblTableFields, gbc);
		gbc.gridx++;
		clientDBPanel.add(txtTableFields, gbc);

		/*gbc.gridx = 0;
		gbc.gridx++;
		gbc.gridy++;
		gbc.anchor = GridBagConstraints.CENTER;
		clientDBPanel.add(btnDownload, gbc);
*/
		buttonPanel.add(btnDownload);
		mainPanel.add(lblPanelName, BorderLayout.SOUTH);
		
		this.add(mainPanel, BorderLayout.NORTH);
		this.add(clientDBPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		txtForms.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE)
						|| (c == KeyEvent.VK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					JOptionPane.showMessageDialog(null, "Invalid Character",
							"ERROR", JOptionPane.ERROR_MESSAGE);
					e.consume();
				}

				// Disable Next button when text is changed
				parentNextButton.setEnabled(false);
			}
		});

		txtTables.addKeyListener(new KeyAdapter() {
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

			}
		});

		txtAgents.addKeyListener(new KeyAdapter() {
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

			}
		});

		txtWorkflows.addKeyListener(new KeyAdapter() {
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

			}
		});

		txtScripts.addKeyListener(new KeyAdapter() {
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

			}
		});
		
		txtFormControls.addKeyListener(new KeyAdapter() {
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

			}
		});
		
		txtProperties.addKeyListener(new KeyAdapter() {
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

					}
				});
		
		
		txtMenus.addKeyListener(new KeyAdapter() {
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

							}
						});
		
		txtTableFields.addKeyListener(new KeyAdapter() {
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

									}
								});
				
		btnDownload.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Input Validation

				if (txtForms.getText() == null
						|| txtForms.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Forms",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtForms.requestFocus();
					return;
				}

				if (txtTables.getText() == null
						|| txtTables.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Tables",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtTables.requestFocus();
					return;
				}

				if (txtAgents.getText() == null
						|| txtAgents.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Agents",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtAgents.requestFocus();
					return;
				}

				if (txtWorkflows.getText() == null
						|| txtWorkflows.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter WorkFlows",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtWorkflows.requestFocus();
					return;
				}
				
				if (txtScripts.getText() == null
						|| txtScripts.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Scripts",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtScripts.requestFocus();
					return;
				}
				
				if (txtFormControls.getText() == null
						|| txtFormControls.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Form Controls",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtFormControls.requestFocus();
					return;
				}
				
				if (txtProperties.getText() == null
						|| txtProperties.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Properties",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtProperties.requestFocus();
					return;
				}
				
				if (txtMenus.getText() == null
						|| txtMenus.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Menus",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtMenus.requestFocus();
					return;
				}
				
				if (txtTableFields.getText() == null
						|| txtTableFields.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Table fields",
							"Missing fields", JOptionPane.DEFAULT_OPTION);
					txtTableFields.requestFocus();
					return;
				}
				
				//set inputs to bean
				objectsToMergeBean.setForms(txtForms.getText());
				objectsToMergeBean.setTables(txtTables.getText());
				objectsToMergeBean.setAgents(txtAgents.getText());
				objectsToMergeBean.setWorkflows(txtWorkflows.getText());
				objectsToMergeBean.setScripts(txtScripts.getText());
				objectsToMergeBean.setFormControls(txtFormControls.getText());
				objectsToMergeBean.setProperties(txtProperties.getText());
				objectsToMergeBean.setMenus(txtMenus.getText());
				objectsToMergeBean.setTableFields(txtTableFields.getText());

				// Enable Next button once Test is Success
				testCompleted = true;
				if (testCompleted) {
					System.out.println("Download button clicked...");
					
					System.out.println(
							  "#################### Objects to Merge #########################");
							  System.out.println(objectsToMergeBean.toString());
							  System.out.println
							  ("################################################################");
							  
				  ClientDetailsBean.downLoadSelectedFlag = true;
				 // FileListPanel fileListPanel = new FileListPanel();
				  fileListPanel.getFileList();
				  /*fileListPanel.putClientProperty("PANEL_PROPERTY", "NINTH_PANNEL");
				  cards.add(fileListPanel, "NINTH_PANNEL");
				 
				  CardLayout cl = (CardLayout) cards.getLayout();
				  cl.show(fileListPanel.getParent(), "NINTH_PANNEL");
				 */
				  CardLayout cl = (CardLayout) cards.getLayout();
				  cl.next(cards);
				  
								
				}

				/*
				 * if (true) {
				 * 
				 * JOptionPane.showMessageDialog(null, "Task completed...",
				 * "SUCCESS", JOptionPane.DEFAULT_OPTION); // resetField(); }
				 */

			}
		});

	}// constructor closed


	/*@Override
	public void update(Observable oObservable, Object oObject) {
		
		ObjectsToMergeBean objectsToMergeBean = ((ObjectsToMergeBean) oObservable); // cast	
		
		System.out.println("Update method called.... in Obects to merge form");
		propUtil.setProperty("Forms",objectsToMergeBean.getForms());
		propUtil.setProperty("Tables",objectsToMergeBean.getTables());
		propUtil.setProperty("Agents",objectsToMergeBean.getAgents());
		propUtil.setProperty("WorkFlows",objectsToMergeBean.getWorkflows());
		propUtil.setProperty("Scripts",objectsToMergeBean.getScripts());
		propUtil.setProperty("FormControls",objectsToMergeBean.getFormControls());
		propUtil.setProperty("Properties",objectsToMergeBean.getProperties());
		propUtil.setProperty("Menus",objectsToMergeBean.getMenus());
		propUtil.setProperty("TableFields",objectsToMergeBean.getTableFields());
		
	}
*/

}// class closed