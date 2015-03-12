package com.vg.frames;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ScrollListPanel extends JPanel {

  JScrollPane scrollpane, scrollpane2, scrollpane3;
  private JLabel lblPanelName;
  public ScrollListPanel(String lblPanelNameString) {

	setLayout(new BorderLayout());
	  
    String categories[] = { "DBdetails.txt", "testreport.txt", "TestReport.xlsx"};
    JList list = new JList(categories);
    list.setVisibleRowCount(2);
    
    
    scrollpane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   // scrollpane.setPreferredSize(new Dimension(10,25));
    scrollpane.setViewportView(list);
  /*  scrollpane2 = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollpane2.setPreferredSize(new Dimension(10,25));
    
    scrollpane3 = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollpane3.setPreferredSize(new Dimension(10,25));
    */
    
    JPanel mainPanel = new JPanel(new BorderLayout());
    
    lblPanelName = new JLabel(lblPanelNameString);
    //lblPanelName.setFont(new Font());
    lblPanelName.setFont(lblPanelName.getFont().deriveFont(16.0f));
    lblPanelName.setHorizontalAlignment( SwingConstants.CENTER );
    mainPanel.add(lblPanelName,BorderLayout.NORTH);
    
    JPanel subPanel = new JPanel(new BorderLayout());
   
    
    mainPanel.add(new JLabel("Folder One Contents"));
    subPanel.add(scrollpane);
    
   /* subPanel.add(new JLabel("Folder Two Contents"));
    subPanel.add(scrollpane2);
    
    subPanel.add(new JLabel("Folder Three Contents"));
    subPanel.add(scrollpane3); */
    
    JLabel lblFileLink = new JLabel("<html></h3><b>Open Test Report<b></h3></html>");

	 // To indicate the the link is clickable
	 lblFileLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 
	 this.add(mainPanel,BorderLayout.NORTH);
	 this.add(subPanel, BorderLayout.CENTER);
     this.add(lblFileLink, BorderLayout.SOUTH);
	 
	 lblFileLink.addMouseListener(new MouseAdapter() {
	         @Override
   public void mouseClicked(MouseEvent e) {
      	try {
			Desktop.getDesktop().open(new File(getClass().getResource("/batchfiles/TestReport.xlsx").toURI()));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
        catch (IOException e1) {

           e1.printStackTrace();
       }
   }
});
    
  }
  
  /*JLabel label;

  public ScrollListPanel() {
    super(true);
    label = new JLabel("Folder List");
    setLayout(new BorderLayout());

    JScrollBar hbar = new JScrollBar(JScrollBar.HORIZONTAL);
    JScrollBar vbar = new JScrollBar(JScrollBar.VERTICAL);
    
    hbar.setMaximum(100);
    hbar.setMinimum(1);
    
    
    vbar.setMaximum(100);
    vbar.setMinimum(1);
    
    //hbar.setUnitIncrement(2);
    //hbar.setBlockIncrement(1);

   // hbar.addAdjustmentListener(new MyAdjustmentListener());
    //vbar.addAdjustmentListener(new MyAdjustmentListener());

    add(label, BorderLayout.NORTH);
    add(hbar, BorderLayout.SOUTH);
    add(vbar, BorderLayout.EAST);
    
    
  }

*/ 
}