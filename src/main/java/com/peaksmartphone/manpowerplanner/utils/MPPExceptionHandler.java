package com.peaksmartphone.manpowerplanner.utils;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.gui.GUIFactory;

/**
 * <p> Title: {@link MPPExceptionHandler} </p>
 * 
 * <b>Description:</b> 
 * <p> Insert Description here </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * $Rev: $:     Revision of last commit<br/>
 * $Author: $:  Author of last commit<br/>
 * $Date: $:    Date of last commit
 * 
 */
public class MPPExceptionHandler extends JDialog
{
  private final JTextPane mTxtPane = new JTextPane();
  private final Exception mException;

  /**
   * 
   * @param pException Exception
   */
  private MPPExceptionHandler(Exception pException) 
  {
    super();
    mException = pException;
    
    init();    
  }
  
  /**
   */
  private void init()
  {
    getContentPane().setLayout(new BorderLayout());
    
    mTxtPane.setBackground(this.getContentPane().getBackground());
    final JScrollPane scrollPane = new JScrollPane(mTxtPane);
    
    getContentPane().add(scrollPane, BorderLayout.CENTER);
    getContentPane().add(initBtnPanel(), BorderLayout.SOUTH);  
    
    String message = "Error occur:\n";
    message += mException.getMessage() + "\n";
    message += getStackTrace(mException);
    mTxtPane.setText(message);
  }
  
  /**
   * 
   */
  private static String getStackTrace(Exception pException)
  {
    final StringBuffer strBuffer = new StringBuffer();
    final StackTraceElement[] elements = pException.getStackTrace();
    
    for (int i = 0; i < elements.length; i++)
    {
      strBuffer.append(pException.getStackTrace()[i].toString() + "\n");
    }
    
    return strBuffer.toString();
  }
  
  
  /**
   * 
   */
  private JPanel initBtnPanel()
  {
    final JPanel btnPanel = new JPanel();
    final JButton button = GUIFactory.createTextButtonClose();
    
    button.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        setVisible(false);  
      }
    });
    
    btnPanel.setLayout(new GridBagLayout());
    
    btnPanel.add(button, 
        new GridBagConstraints(1, 1, 1, 1, 
            0, 0, 
            GridBagConstraints.CENTER, 
            GridBagConstraints.NONE, 
            GUIFactory.COMPONENTS_INSETS, 5, 5));
    
    return btnPanel;    
  }
  
  /**
   * 
   * @param pException anzuzeigende Exception mit Stacktrace
   */
  public static void handleRuntimeException(Exception pException)
  {
    MPPManager.getInstance().getSessionManager().rollback();
    final MPPExceptionHandler errorDialog = new MPPExceptionHandler(pException);
    errorDialog.setTitle(ResourceBundle.getString(MPPExceptionHandler.class.getName() + ".TITLE"));
    errorDialog.setSize(new Dimension(500, 300));
    GUIFactory.centerWindow(errorDialog);
    errorDialog.setModal(true);
    errorDialog.setVisible(true);
  }
}
