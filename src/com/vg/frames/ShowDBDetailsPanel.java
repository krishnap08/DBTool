package com.vg.frames;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
	 * <p>ShowPanel observes the data Object Student. <br>
	 * When notified, it updates the Label to present the name.</br></p>
	 */
	public class ShowDBDetailsPanel extends JPanel implements Observer {
		
		 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JLabel oLabel, lblPanelName;
		 private JButton btnExec, parentNextButton;
		 
		public ShowDBDetailsPanel(final JButton parentNextButton,String lblPanelNameString){
			
			setLayout(new BorderLayout());
			
			this.parentNextButton= parentNextButton;
			
			JPanel clientDBPanel = new JPanel();

			oLabel= new JLabel();
			
			btnExec = new JButton("Execute");

           clientDBPanel.add(btnExec);
			
			lblPanelName = new JLabel("<html><h3>"+lblPanelNameString+"</h3></html");
			lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
	        lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
			
          
	       JPanel mainPanel = new JPanel(new BorderLayout());
	        mainPanel.add(lblPanelName, BorderLayout.NORTH);
           this.add(mainPanel, BorderLayout.NORTH);
           
           //this.add(lblPanelName, BorderLayout.NORTH);
           this.add(oLabel,BorderLayout.CENTER);
           
           
           this.add(clientDBPanel, BorderLayout.SOUTH);
           
           
	           
			btnExec.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(ActionEvent e) {
	            	 	            	 
	            	 executeReport();
	            	 
	            	 //Enable Next button once Test is Success
	                    boolean testCompleted = true;
	                    if(testCompleted){
	                    	parentNextButton.setEnabled(true);
	                    }
	             }});
		}
 
		private void executeReport() {
			 int dialogButton = JOptionPane.YES_NO_OPTION;
        	 int dialogResult = JOptionPane.showConfirmDialog(this, "Would You Like to Generate Report?", "Warning",dialogButton);
        		 
             if(dialogResult == JOptionPane.YES_OPTION){ 

            	 String path= getClass().getResource("/batchfiles/test.bat").toString();
	            	System.out.println("path :: "+path);
	            	 String[] command = {"cmd.exe", "/C", "Start", path};
	                 try {
						Process p =  Runtime.getRuntime().exec(command);
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}  
             }
		}
 
		@Override
		public void update(Observable oObservable, Object oObject) {
			// we do not figure who kicked us, we just repaint.
			ClientDetailsBean cleintDBDetailsBean = ((ClientDetailsBean)oObservable); // cast
			
			String text = "<html>" +
					"<h3>HostName :</h3> "+ cleintDBDetailsBean.getHostName()+"<br> " +
					
					"<h3>DB Name  :</h3> " + cleintDBDetailsBean.getDbName()+"" +
					
					//"<h3>Port     :</h3> <h4> "+ cleintDBDetailsBean.getPortStr()+"</h4> <br> " +
					
					//"<h3>Username :</h3> <h4> " +cleintDBDetailsBean.getUserName()+"</h4> <br>" +
					
					//"<h3>Password :</h3> <h4> "+ cleintDBDetailsBean.getPassword()+"</h4> <br> <br>"
					
					 "</html>";
			
			
			this.oLabel.setText(text);
			
			/*System.out.println("#################### Client DB Details #########################");
			System.out.println(cleintDBDetailsBean.toString());
			System.out.println("################################################################");*/
		}
		
	}