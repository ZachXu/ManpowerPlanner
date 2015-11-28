package com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.google.common.base.Joiner;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.utils.DateUtil;

/**
 * <p> Title: {@link WeeklyScheduleInstTableModel} </p>
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
public class WeeklyScheduleInstTableModel extends AbstractTableModel
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private Table<Date, DailyScheduleDef, DailyScheduleInst> mDataTable = HashBasedTable.create();
  
  private final List<DailyScheduleDef> mDailyScheduleDefList;
  
  private final Calendar mCalendar = Calendar.getInstance();
  
  /**
   * 
   */
  public WeeklyScheduleInstTableModel(List<DailyScheduleDef> pDailyScheduleDefList)
  {
    mDailyScheduleDefList = pDailyScheduleDefList;
    
    startCalendarOnMontag();
  }
  
  /* (non-Javadoc)
   * @see javax.swing.table.AbstractTableModel#getColumnName(int)
   */
  @Override
  public String getColumnName(int pColumn)
  {
    DailyScheduleDef dailyscheduledef = getColumnDailyScheduleDef(pColumn);
    
    return dailyscheduledef.getName();
  }

  /* (non-Javadoc)
   * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
   */
  @Override
  public Class<?> getColumnClass(int pColumnIndex)
  {
    return super.getColumnClass(pColumnIndex);
  }
  
  /* (non-Javadoc)
   * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
   */
  @Override
  public boolean isCellEditable(int pRowIndex, int pColumnIndex)
  {
    return true;
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getRowCount()
   */
  @Override
  public int getRowCount()
  {
    return WeeklyRowHeaderTableModel.DAYS_IN_WEEK;
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getColumnCount()
   */
  @Override
  public int getColumnCount()
  {
    return mDailyScheduleDefList.size();
  }
  
  /**
   * 
   * @param pStartDate
   * @param pDataTable
   */
  public void refresh(Date pStartDate, Table<Date, DailyScheduleDef, DailyScheduleInst> pDataTable)
  {
    mCalendar.setTime(pStartDate);
    startCalendarOnMontag();
    
    mDataTable = pDataTable;
    fireTableDataChanged();
  }

  /**
   * 
   */
  private void startCalendarOnMontag()
  {
    mCalendar.setMinimalDaysInFirstWeek(WeeklyRowHeaderTableModel.DAYS_IN_WEEK);
    mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
  }
  
  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int pRowIndex, int pColumnIndex)
  {
    Date rowKey = getRowDate(pRowIndex);
    
    DailyScheduleDef columnKey = getColumnDailyScheduleDef(pColumnIndex);
    
    DailyScheduleInst data = mDataTable.get(rowKey, columnKey);
    
    return data;
  }

  /**
   * @param pColumnIndex
   * @return
   */
  protected DailyScheduleDef getColumnDailyScheduleDef(int pColumnIndex)
  {
    DailyScheduleDef columnKey = mDailyScheduleDefList.get(pColumnIndex);
    return columnKey;
  }

  /**
   * @param pRowIndex
   * @return
   */
  public Date getRowDate(int pRowIndex)
  {
    Calendar rowKey = DateUtil.getCalendarOnlyWithDate(mCalendar.getTime());
    
    rowKey.add(Calendar.DAY_OF_WEEK, pRowIndex);
    
    return rowKey.getTime();
  }
  
  /**
   * replace data
   * @param pRowDate
   * @param pColumnDef
   * @param pReplaceDailyScheduleInst
   */
  public void replaceDailyScheduleInst(Date pRowDate, DailyScheduleDef pColumnDef, DailyScheduleInst pReplaceDailyScheduleInst)
  {
    mDataTable.put(pRowDate, pColumnDef, pReplaceDailyScheduleInst);
  }

}
