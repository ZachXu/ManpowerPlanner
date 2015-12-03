package com.peaksmartphone.manpowerplanner.core.gui.dailyschedule;

import java.awt.event.ActionEvent;
import java.util.List;

import com.google.common.base.Strings;
import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.exception.MPPRuntimeException;
import com.peaksmartphone.manpowerplanner.core.gui.common.AbstractManageController;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;
import com.peaksmartphone.manpowerplanner.core.services.DailyScheduleDefService;
import com.peaksmartphone.manpowerplanner.utils.MPPExceptionHandler;

/**
 * <p> Title: {@link DailyScheduleDefManageController} </p>
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
public class DailyScheduleDefManageController extends AbstractManageController<DailyScheduleDef>
{
  private final DailyScheduleDefService mServiceDailyScheduleDef = DailyScheduleDefService.getInstance();
  
  /**
   * 
   */
  public DailyScheduleDefManageController()
  {
    super(new DailyScheduleDefManageInputPnl(), new DailyScheduleDefManageTableModel());
    
    onRefresh(null);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onSave(java.awt.event.ActionEvent)
   */
  @Override
  public void onSave(ActionEvent pEvent)
  {
    DailyScheduleDef data = readDataObject();
    
    data = mServiceDailyScheduleDef.saveData(data);
     
    mTableModel.replaceOrAddDataObject(data);
    
    MPPManager.getInstance().getSessionManager().commit();
    
    setMaskStatus(mView.checkBetweenSelectedNotSelected());
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onDelete(java.awt.event.ActionEvent)
   */
  @Override
  public void onDelete(ActionEvent pEvent)
  {
    try
    {
      DailyScheduleDef data = readDataObject();
      
      if (!DailyScheduleDef.EMPTY_OBJECT.equals(data) && data != null)
      {
        mServiceDailyScheduleDef.deleteData(data);
        
        MPPManager.getInstance().getSessionManager().commit();
        
        onRefresh(pEvent);
      }
    }
    catch(Exception e)
    {
      MPPExceptionHandler.handleRuntimeException(e);
    }
    
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onNew(java.awt.event.ActionEvent)
   */
  @Override
  public void onNew(ActionEvent pEvent)
  {
    setDataObject(DailyScheduleDef.newInstance(((DailyScheduleDefManageTableModel)getTableModel()).getLastData()));
    setMaskStatus(MaskStatus.NEW);
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
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onRefresh(java.awt.event.ActionEvent)
   */
  @Override
  public void onRefresh(ActionEvent pEvent)
  {
     List<DailyScheduleDef> list = mServiceDailyScheduleDef.getAllData();
     mTableModel.refresh(list);
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
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.DisplayController#tableSelectedIndex(int)
   */
  @Override
  public void tableSelectedIndex(int pSelectedRow)
  {
    if (MaskStatus.SELECTED.equals(mView.checkBetweenSelectedNotSelected()))
    {
      setDataObject(mTableModel.getObjectAt(pSelectedRow));
    }
    else
    {
      setDataObject(DailyScheduleDef.EMPTY_OBJECT);
    }
    
    setMaskStatus(mView.checkBetweenSelectedNotSelected());
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.DisplayController#readDataObject()
   */
  @Override
  public DailyScheduleDef readDataObject()
  {
    DailyScheduleDef retVal = DailyScheduleDef.valueOf(
        mId, 
        mVersion, 
        Strings.emptyToNull(getInputPnl().getTxtDailScheduleName().getText()), 
        getInputPnl().getChxIsRestDay().isSelected(), 
        getInputPnl().getNumMaxEmployeeAmount().getIntegerVal(), 
        getInputPnl().getNumSortIndex().getIntegerVal());
    
    return retVal;
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.DisplayController#setDataObject(java.lang.Object)
   */
  @Override
  public void setDataObject(DailyScheduleDef pSelectedObject)
  {
    mId = pSelectedObject.getId();
    mVersion = pSelectedObject.getVersion();
    
    getInputPnl().getTxtDailScheduleName().setText(pSelectedObject.getName());
    getInputPnl().getChxIsRestDay().setSelected(pSelectedObject.isIsRestDay());
    getInputPnl().getNumMaxEmployeeAmount().setIntegerVal(pSelectedObject.getEmployeeAmount());
    getInputPnl().getNumSortIndex().setIntegerVal(pSelectedObject.getSortIndex());
  }
  
  public DailyScheduleDefManageInputPnl getInputPnl()
  {
    return (DailyScheduleDefManageInputPnl)mInputPnl;
  }

}
