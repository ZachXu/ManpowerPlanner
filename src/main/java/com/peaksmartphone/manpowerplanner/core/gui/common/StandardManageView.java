package com.peaksmartphone.manpowerplanner.core.gui.common;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;

import com.peaksmartphone.manpowerplanner.core.gui.common.table.StandardListTable;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbar;

/**
 * <p> Title: {@link StandardManageView} </p>
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
public class StandardManageView extends JPanel
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private final DisplayController mController;
  
  private final StandardEditToolbar mEditToolbar;
  private final AbstractInputPanel mInputPnl;
  private final StandardListTable mTable;

  /**
   * @param pController
   */
  public StandardManageView(DisplayController pController, 
      TableModel pTableModel, AbstractInputPanel pInputPnl)
  {
    super();
    mController = pController;
    
    setLayout(new BorderLayout());

    mEditToolbar = new StandardEditToolbar(pController);
    mTable = new StandardListTable(pTableModel);
    mInputPnl = pInputPnl;
    
    add(mEditToolbar, BorderLayout.NORTH);
    add(new JScrollPane(mTable), BorderLayout.CENTER);
    add(mInputPnl, BorderLayout.SOUTH);
    
    initListener();
  }

  /**
   * 
   */
  private void initListener()
  {
    mTable.getSelectionModel().addListSelectionListener(new ListSelectionListener()
    {
      
      @Override
      public void valueChanged(ListSelectionEvent pE)
      {
        mController.tableSelectedIndex(mTable.getSelectedRow());
      }
    });
  }

  /**
   * @return the employeInputPnl
   */
  public JPanel getInputPnl()
  {
    return mInputPnl;
  }

  /**
   * @param pMaskStatus
   */
  public void setMaskStatus(MaskStatus pMaskStatus)
  {
    mEditToolbar.setMaskStatus(pMaskStatus);
    mInputPnl.setMaskStatus(pMaskStatus);
  }

  /**
   * @return the table
   */
  public JXTable getTable()
  {
    return mTable;
  }
  
  /**
   * 
   * @return
   */
  public MaskStatus checkBetweenSelectedNotSelected()
  {
    MaskStatus returnVal = MaskStatus.NOT_SELECTED;
    
    if (getSelectedTableRow() > -1)
    {
      returnVal = MaskStatus.SELECTED;
    }
    
    return returnVal;
  }

  /**
   * @return
   */
  public int getSelectedTableRow()
  {
    return mTable.getSelectedRow();
  }
  
}
