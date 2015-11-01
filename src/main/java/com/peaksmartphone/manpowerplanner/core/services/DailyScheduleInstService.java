package com.peaksmartphone.manpowerplanner.core.services;

import java.util.Date;
import java.util.List;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.db.dao.DailyScheduleInstDAO;
import com.peaksmartphone.manpowerplanner.utils.DateUtil;

/**
 * <p> Title: {@link DailyScheduleInstService} </p>
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
public class DailyScheduleInstService
{
  private final DailyScheduleInstDAO mDAO;
  
  /**
   * 
   */
  private DailyScheduleInstService()
  {
    mDAO = new DailyScheduleInstDAO(MPPManager.getInstance().getSessionManager());
  }
  
  /**
   * 
   * @return
   */
  public static DailyScheduleInstService getInstance()
  {
    return new DailyScheduleInstService();
  }

  /**
   * 
   * @param pId
   * @return
   */
  public DailyScheduleInst getData(String pId)
  {
    return mDAO.getData(pId);
  }

  /**
   * 
   * @return
   */
  public List<DailyScheduleInst> getAllData()
  {
    return mDAO.getAllData();
  }

  /**
   * 
   * @param pIdList
   * @return
   */
  public List<DailyScheduleInst> getDataByIds(List<String> pIdList)
  {
    return mDAO.getDataByIds(pIdList);
  }

  /**
   * 
   * @param pData
   * @return
   */
  public DailyScheduleInst saveData(DailyScheduleInst pData)
  {
    return mDAO.saveData(pData);
  }
  
  /**
   * 
   * @param pData
   */
  public void deleteData(DailyScheduleInst pData)
  {
    mDAO.deleteData(pData.getId());
  }
  
  /**
   * 
   * @param pBegin
   * @param pEnd
   * @return
   */
  public Table<Date, DailyScheduleDef, DailyScheduleInst> getDailyScheduleInstsInPeriod(Date pBegin, Date pEnd)
  {
    List<DailyScheduleInst> dailyList = mDAO.getDailyScheduleInstInPeriod(pBegin, pEnd);
    
    Table<Date, DailyScheduleDef, DailyScheduleInst> retTable = HashBasedTable.create();
    
    for (DailyScheduleInst dsi : dailyList)
    {
      Date rowkey = DateUtil.getCalendarOnlyWithDate(dsi.getScheduledDate()).getTime();
      
      DailyScheduleDef columnKey = DailyScheduleDefService.getInstance().getData(dsi.getDailyScheduleDefId());
      
      retTable.put(rowkey, columnKey, dsi);
    }
    
    return retTable;
  }
}
