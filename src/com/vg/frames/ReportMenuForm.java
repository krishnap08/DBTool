package com.vg.frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
    public class ReportMenuForm extends JPanel{

        private static final long serialVersionUID = 1L;
        
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        private JLabel lblPanelName;
        static String generateReportString = "Merge Analysis";
        static String mergeString = "Initial upgrade";
        static String initialMergeString = "Pre-Production Upgrade";
        static String preProdString = "Production Upgrade";

        public ReportMenuForm(final ClientDetailsBean clientDetailsBean, String lblPanelNameString) {

        	super(new BorderLayout());

        	String text = "<html> <body><h3> " +lblPanelNameString+ "</h3> <br><br><br> </body></html>";
        	lblPanelName = new JLabel(text);
            //lblPanelName.setFont(new Font());
            lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
            lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
        	
            //Create the radio buttons.
            JRadioButton genReportButton = new JRadioButton(generateReportString);
            genReportButton.setActionCommand(generateReportString);
            genReportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            //genReportButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            //genReportButton.setSelected(true);

            JRadioButton mergeButton = new JRadioButton(mergeString);
            mergeButton.setActionCommand(mergeString);
            //mergeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            //mergeButton.setAlignmentY(Component.CENTER_ALIGNMENT);

            JRadioButton initialMergeButton = new JRadioButton(initialMergeString);
          //  initialMergeButtonButton.setMnemonic(KeyEvent.VK_D);
            initialMergeButton.setActionCommand(initialMergeString);
            //initialMergeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            //initialMergeButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            
            //JRadioButton preProdButton = new JRadioButton(preProdString);
           // preProdButton.setActionCommand(preProdString);
            //preProdButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            //preProdButton.setAlignmentY(Component.CENTER_ALIGNMENT);


            //Group the radio buttons.
            ButtonGroup group = new ButtonGroup();
            group.add(genReportButton);
            group.add(mergeButton);
            group.add(initialMergeButton);
            //group.add(preProdButton);

            //Register a listener for the radio buttons.
            /*
            radioButtonMenuPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            gbc.anchor = GridBagConstraints.WEST;

            ButtonGroup group = new ButtonGroup();
            for (String item : answerItems) {
                JRadioButton radioButton = new JRadioButton(item);
                group.add(radioButton);
                radioButtonMenuPanel.add(radioButton, gbc);
            }
            */
            
           // JPanel labelPanel = new JPanel(new BorderLayout());
           // labelPanel.add(lblPanelName, BorderLayout.NORTH);
            
            
            //Put the radio buttons in a column in a panel.
            JPanel radioPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            gbc.anchor = GridBagConstraints.WEST;

            radioPanel.add(lblPanelName);
            
            radioPanel.add(genReportButton,gbc);
            radioPanel.add(mergeButton,gbc);
            radioPanel.add(initialMergeButton,gbc);
            //radioPanel.add(preProdButton,gbc);

            
           
            //add(radioPanel, BorderLayout.LINE_START);
            add(radioPanel, BorderLayout.NORTH);
            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            
            
            genReportButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	clientDetailsBean.setGenerateReportFlag(true);
                	clientDetailsBean.setMergeFlag(false);
                	clientDetailsBean.setInitialMergeFlag(false);
                	clientDetailsBean.setPreProdFlag(false);
                }});
            
            mergeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	clientDetailsBean.setGenerateReportFlag(false);
                	clientDetailsBean.setMergeFlag(true);
                	clientDetailsBean.setInitialMergeFlag(false);
                	clientDetailsBean.setPreProdFlag(false);
                }});
            
            initialMergeButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	clientDetailsBean.setGenerateReportFlag(false);
                	clientDetailsBean.setMergeFlag(false);
                	clientDetailsBean.setInitialMergeFlag(true);
                	clientDetailsBean.setPreProdFlag(false);
                }});
            
            
            /*preProdButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                	clientDetailsBean.setGenerateReportFlag(false);
                	clientDetailsBean.setMergeFlag(false);
                	clientDetailsBean.setInitialMergeFlag(false);
                	clientDetailsBean.setPreProdFlag(true);
                }});*/
            
            
        }// constructor closed

    }// class closed