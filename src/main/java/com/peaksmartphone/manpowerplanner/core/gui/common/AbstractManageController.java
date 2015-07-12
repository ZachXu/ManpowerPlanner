package com.peaksmartphone.manpowerplanner.core.gui.common;

import java.awt.event.ActionEvent;

import com.peaksmartphone.manpowerplanner.core.gui.common.table.AbstractListTableModel;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;

/**
 * <p> Title: {@link AbstractManageController} </p>
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
public abstract class AbstractManageController<T> implements DisplayController<T>
{
  protected final StandardManageView mView;
  
  protected final AbstractInputPanel mInputPnl;
  
  protected final AbstractListTableModel<T> mTableModel;
  
  protected String mId;
  protected Integer mVersion;
  
  /**
   * @param pInputPnl
   * @param pTableModel
   */
  public AbstractManageController(AbstractInputPanel pInputPnl, AbstractListTableModel<T> pTableModel)
  {
    super();
    
    mInputPnl = pInputPnl;
    mTableModel = pTableModel;
    
    mView = new StandardManageView(this, mTableModel, mInputPnl);
    
    setMaskStatus(MaskStatus.NOT_SELECTED);
  }
  
  /**
   * @return the view
   */
  public StandardManageView getView()
  {
    return mView;
  }

  @Override
  public void onCancel(ActionEvent pEvent)
  {
    tableSelectedIndex(mView.getSelectedTableRow());
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onEdit(java.awt.event.ActionEvent)
   */
  @Override
  public void onEdit(ActionEvent pEvent)
  {
    setMaskStatus(MaskStatus.EDIT);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onCopy(java.awt.event.ActionEvent)
   */
  @Override
  public void onCopy(ActionEvent pEvent)
  {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onExportExcel(java.awt.event.ActionEvent)
   */
  @Override
  public void onExportExcel(ActionEvent pEvent)
  {
    // TODO Auto-generated method stub
    
  }

  /* (non-Javadoc)
   */
  @Override
  public void setMaskStatus(MaskStatus pMaskStatus)
  {
    mView.setMaskStatus(pMaskStatus);
  }
  
}
