package com.peaksmartphone.manpowerplanner.core.gui.employee;

import java.util.Collections;

import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.gui.common.table.AbstractListTableModel;
import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

/**
 * <p> Title: {@link EmployeeManageTableModel} </p>
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
public class EmployeeManageTableModel extends AbstractListTableModel<Employee>
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private static final int CLN_NAME = 0;
  private static final int CLN_MAXOCCUR = 1;
  
  
  /**
   * 
   */
  public static final String[] COLUMN_NAMES = new String[]{
    ResourceBundle.getString(EmployeeManageTableModel.class.getName() + ".CLN_NAME"),
    ResourceBundle.getString(EmployeeManageTableModel.class.getName() + ".CLN_MAXOCCUR"),
  };

  /**
   * @param pDataList
   * @param pColumnNames
   */
  public EmployeeManageTableModel()
  {
    super(Collections.<Employee>emptyList(), COLUMN_NAMES);
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int pRowIndex, int pColumnIndex)
  {
    Object retVal = null;
    
    Employee data = getObjectAt(pRowIndex);
    
    switch (pColumnIndex)
    {
    case CLN_NAME:
      retVal = data.getEmployeeName();
      break;
    case CLN_MAXOCCUR:
      retVal = data.getMaxOccurInWeek();
      break;
    default:
      break;
    }
    
    return retVal;
  }

}
