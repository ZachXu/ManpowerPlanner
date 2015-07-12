package com.peaksmartphone.manpowerplanner.core.gui.dailyschedule;

import java.util.Collections;

import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.gui.common.table.AbstractListTableModel;
import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

/**
 * <p> Title: {@link DailyScheduleDefManageTableModel} </p>
 * 
 * <b>Description:</b> 
 * <p> Table model for DailyScheduleDef management </p>
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
public class DailyScheduleDefManageTableModel extends AbstractListTableModel<DailyScheduleDef>
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final int CLN_NAME = 0;
  private static final int CLN_RESTDAY = 1;
  private static final int CLN_MAXEMPLOYEEAMOUNT = 2;
  private static final int CLN_SORTINDEX = 3;
  
  private static final String[] COLUMNNAMES = new String[]
  { ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_NAME"),
    ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_RESTDAY"),
    ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_MAXEMPLOYEEAMOUNT"),
    ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_SORTINDEX")
    };

  /**
   * @param pDataList
   * @param pColumnNames
   */
  public DailyScheduleDefManageTableModel()
  {
    super(Collections.<DailyScheduleDef>emptyList(), COLUMNNAMES);
  }
  
  /* (non-Javadoc)
   * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
   */
  @Override
  public Class<?> getColumnClass(int pColumnIndex)
  {
    if (pColumnIndex == CLN_RESTDAY)
    {
      return Boolean.class;
    }
    
    return super.getColumnClass(pColumnIndex);
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int pRowIndex, int pColumnIndex)
  {
    DailyScheduleDef data = getObjectAt(pRowIndex);
    
    Object retVal = null;
    
    switch (pColumnIndex)
    {
    case CLN_NAME:
      retVal = data.getName();
      break;
    case CLN_RESTDAY:
      retVal = data.isIsRestDay();
      break;
    case CLN_MAXEMPLOYEEAMOUNT:
      retVal = data.getEmployeeAmount();
      break;
    case CLN_SORTINDEX:
      retVal = data.getSortIndex();
      break;
    default:
      break;
    }
    
    return retVal;
  }

}
