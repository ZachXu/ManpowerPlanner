package com.peaksmartphone.manpowerplanner.core.gui.employee;

import java.awt.event.ActionEvent;
import java.util.List;

import com.google.common.base.Strings;
import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.gui.common.AbstractManageController;
import com.peaksmartphone.manpowerplanner.core.gui.common.DisplayController;
import com.peaksmartphone.manpowerplanner.core.gui.common.StandardManageView;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;
import com.peaksmartphone.manpowerplanner.core.services.EmployeeService;

/**
 * <p> Title: {@link EmployeeManageController} </p>
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
public class EmployeeManageController extends AbstractManageController<Employee>
{
  private final EmployeeService mServiceEmployee = EmployeeService.getInstance();

  /**
   * 
   */
  public EmployeeManageController()
  {
    super(new EmployeeManageInputPnl(), new EmployeeManageTableModel());
    
    onRefresh(null);
  }

  /**
   * @return the view
   */
  public StandardManageView getView()
  {
    return mView;
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onSave(java.awt.event.ActionEvent)
   */
  @Override
  public void onSave(ActionEvent pEvent)
  {
    Employee saveData = readDataObject();
    
    saveData = mServiceEmployee.saveData(saveData);
    mTableModel.replaceOrAddDataObject(saveData);
    
    MPPManager.getInstance().getSessionManager().commit();
    
    setMaskStatus(mView.checkBetweenSelectedNotSelected());
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onCancel(java.awt.event.ActionEvent)
   */
  @Override
  public void onCancel(ActionEvent pEvent)
  {
    tableSelectedIndex(mView.getSelectedTableRow());
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onDelete(java.awt.event.ActionEvent)
   */
  @Override
  public void onDelete(ActionEvent pEvent)
  {
    Employee data = readDataObject();
    mServiceEmployee.deleteData(data);
    
    mTableModel.removeData(data);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onNew(java.awt.event.ActionEvent)
   */
  @Override
  public void onNew(ActionEvent pEvent)
  {
    setDataObject(Employee.newInstance());
    setMaskStatus(MaskStatus.NEW);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onRefresh(java.awt.event.ActionEvent)
   */
  @Override
  public void onRefresh(ActionEvent pEvent)
  {
    List<Employee> mEmployeeList = mServiceEmployee.getAllData();
    mTableModel.refresh(mEmployeeList);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener#onExportExcel(java.awt.event.ActionEvent)
   */
  @Override
  public void onExportExcel(ActionEvent pEvent)
  {
    // TODO Auto-generated method stub
    
  }

  /**
   * @param pSelectedRow
   */
  public void tableSelectedIndex(int pSelectedRow)
  {
    if (MaskStatus.SELECTED.equals(mView.checkBetweenSelectedNotSelected()))
    {
      setDataObject(mTableModel.getObjectAt(pSelectedRow));
    }
    else
    {
      setDataObject(Employee.EMPTY_OBJECT);
    }
    
    setMaskStatus(mView.checkBetweenSelectedNotSelected());
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.DisplayController#readObject()
   */
  @Override
  public Employee readDataObject()
  {
    String employeename = Strings.emptyToNull(
        getInputPnl().getTxtEmployeeName().getText());
    
    int maxOccurInWeek = getInputPnl().getNumMaxInOccur().getIntegerVal();
    
    return Employee.valueOf(mId, mVersion, employeename, maxOccurInWeek);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.DisplayController#setObject(java.lang.Object)
   */
  @Override
  public void setDataObject(Employee pSelectedObject)
  {
    mId = pSelectedObject.getId();
    mVersion = pSelectedObject.getVersion();
    
    getInputPnl().getTxtEmployeeName().setText(
        Strings.nullToEmpty(pSelectedObject.getEmployeeName()));
    getInputPnl().getNumMaxInOccur().setIntegerVal(
        pSelectedObject.getMaxOccurInWeek());
  }

  /**
   * 
   * @return
   */
  public EmployeeManageInputPnl getInputPnl()
  {
    return (EmployeeManageInputPnl)mInputPnl;
  }
  
}
