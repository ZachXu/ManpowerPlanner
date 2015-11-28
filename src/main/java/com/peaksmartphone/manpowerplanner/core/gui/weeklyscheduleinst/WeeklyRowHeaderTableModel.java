package com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

import com.peaksmartphone.manpowerplanner.utils.DateUtil;

/**
 * <p> Title: {@link WeeklyRowHeaderTableModel} </p>
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
public class WeeklyRowHeaderTableModel extends AbstractTableModel
{
  private static class WeekDayGenerator{
    
    private final Calendar mCalendar = DateUtil.getCalendarOnlyWithDate(new Date());
    
    /**
     * 
     */
    private WeekDayGenerator()
    {
      super();
      mCalendar.setMinimalDaysInFirstWeek(DAYS_IN_WEEK);
      mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    }
    
    /**
     * 
     * @return
     */
    public static WeekDayGenerator newInstance()
    {
      return new WeekDayGenerator();
    }
    
    /**
     * 
     * @param pStartDay
     */
    public void refresh(Date pStartDay)
    {
      mCalendar.setTime(pStartDay);
      mCalendar.setMinimalDaysInFirstWeek(DAYS_IN_WEEK);
      mCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    }

    /**
     * 
     * @return 
     */
    public List<Date> generateWeekDayList()
    {
      final List<Date> weekdayList = new ArrayList<Date>();
      
      for (int i = 0; i < DAYS_IN_WEEK; i++)
      {
        if (i > 0)
        {
          mCalendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        
        Calendar valuecalendar = DateUtil.getCalendarOnlyWithDate(mCalendar.getTime());
        weekdayList.add(valuecalendar.getTime());
      }
      
      return weekdayList;
    }
  }
  
  
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static final int DAYS_IN_WEEK = 7;
  
  private final WeekDayGenerator mWeekdaygenerator = WeekDayGenerator.newInstance();
  
  private List<Date> mWeekDayList = Collections.<Date>emptyList();
  
  private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("EEEE YYYY-MM-dd");
  
  /**
   * 
   */
  public WeeklyRowHeaderTableModel()
  {
    super();
    refresh(new Date());
  }

  /**
   * refresh start day of the week
   * @param pStartDay
   */
  public void refresh(Date pStartDay)
  {
    mWeekdaygenerator.refresh(pStartDay);
    
    mWeekDayList = mWeekdaygenerator.generateWeekDayList();
    fireTableDataChanged();
  }
  

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getRowCount()
   */
  @Override
  public int getRowCount()
  {
    return DAYS_IN_WEEK;
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getColumnCount()
   */
  @Override
  public int getColumnCount()
  {
    return 1;
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int pRowIndex, int pColumnIndex)
  {
    Object retVal = null;
    
    if (!mWeekDayList.isEmpty())
    {
      retVal = DATEFORMAT.format(mWeekDayList.get(pRowIndex));
    }
    
   
    return retVal;
  }

  /**
   * @return the weekDayList
   */
  public List<Date> getWeekDayList()
  {
    return mWeekDayList;
  }
  
  /**
   * 
   * @return
   */
  public Date getStartWeekDate()
  {
    Date retVal = new Date();
    
    Iterator<Date> itDate = mWeekDayList.iterator();
    
    if (itDate.hasNext())
    {
      retVal = itDate.next();
    }
    
    return retVal;
  }

}
