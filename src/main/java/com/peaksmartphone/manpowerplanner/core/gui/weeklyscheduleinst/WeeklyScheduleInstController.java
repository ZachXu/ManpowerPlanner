package com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst;

import java.util.Date;
import java.util.List;

import com.google.common.collect.Table;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.services.DailyScheduleDefService;
import com.peaksmartphone.manpowerplanner.core.services.DailyScheduleInstService;
import com.peaksmartphone.manpowerplanner.utils.DateUtil;

/**
 * <p> Title: {@link WeeklyScheduleInstController} </p>
 * 
 * <b>Description:</b> 
 * <p> controller for Weeklyscheduleinst </p>
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
public class WeeklyScheduleInstController
{
  private final WeeklyScheduleInstTableModel mWeeklyScheduleInstTM;
  private final WeeklyRowHeaderTableModel mWeeklyRowHeaderTM;
  
  private final DailyScheduleDefService mServiceDailyScheduleDef = DailyScheduleDefService.getInstance();
  private final DailyScheduleInstService mServiceDailyScheduleInst = DailyScheduleInstService.getInstance();
  
  private final WeeklyScheduleInstView mView;
  
  private Table<Date, DailyScheduleDef, DailyScheduleInst> mDataTable;
  
  /**
   * 
   */
  public WeeklyScheduleInstController()
  {
    super();
    
    mWeeklyRowHeaderTM = new WeeklyRowHeaderTableModel();
    
    List<DailyScheduleDef> allDailyScheduleDefs = mServiceDailyScheduleDef.getAllData();
    
    mWeeklyScheduleInstTM = new WeeklyScheduleInstTableModel(allDailyScheduleDefs);
    
    mView = new WeeklyScheduleInstView(this, mWeeklyScheduleInstTM, mWeeklyRowHeaderTM);

    loadData();
  }

  /**
   * 
   */
  protected void loadData()
  {
    List<Date> weekdatelist = mWeeklyRowHeaderTM.getWeekDayList();
    
    if (weekdatelist.size() == 7)
    {
      Date startdate = weekdatelist.get(0);
      Date enddate = weekdatelist.get(weekdatelist.size() - 1);
      
      mDataTable = mServiceDailyScheduleInst.getDailyScheduleInstsInPeriod(startdate, enddate);
      
      mWeeklyScheduleInstTM.refresh(startdate, mDataTable);
      
      mView.setPeriodDisplay(weekdatelist);
    }
  }

  /**
   * @return the view
   */
  public WeeklyScheduleInstView getView()
  {
    return mView;
  }

  /**
   * 
   */
  public void onNext()
  {
    Date currentDate = mWeeklyRowHeaderTM.getStartWeekDate();
    
    currentDate = new Date(currentDate.getTime() + DateUtil.WEEK);
    
    mWeeklyRowHeaderTM.refresh(currentDate);
    
    loadData();
  }

  /**
   * 
   */
  public void onPrevious()
  {
    Date currentDate = mWeeklyRowHeaderTM.getStartWeekDate();
    
    currentDate = new Date(currentDate.getTime() - DateUtil.WEEK);
    
    mWeeklyRowHeaderTM.refresh(currentDate);
    
    loadData();
  }

  
}
