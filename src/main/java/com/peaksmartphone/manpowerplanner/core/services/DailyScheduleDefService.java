package com.peaksmartphone.manpowerplanner.core.services;

import java.util.List;

import org.hibernate.Query;

import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.db.dao.DailyScheduleDefDAO;
import com.peaksmartphone.manpowerplanner.utils.SerialClone;

/**
 * <p> Title: {@link DailyScheduleDefService} </p>
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
public class DailyScheduleDefService
{
  private final DailyScheduleDefDAO mDAO;
  
  /**
   * 
   */
  private DailyScheduleDefService()
  {
    mDAO = new DailyScheduleDefDAO(MPPManager.getInstance().getSessionManager());
  }
  
  /**
   * 
   * @return
   */
  public static DailyScheduleDefService getInstance()
  {
    return new DailyScheduleDefService();
  }

  /**
   * 
   * @param pId
   * @return
   */
  public DailyScheduleDef getData(String pId)
  {
    return mDAO.getData(pId);
  }

  /**
   * 
   * @return
   */
  public List<DailyScheduleDef> getAllData()
  {
    return mDAO.getAllData();
  }

  /**
   * 
   * @param pIdList
   * @return
   */
  public List<DailyScheduleDef> getDataByIds(List<String> pIdList)
  {
    return mDAO.getDataByIds(pIdList);
  }

  /**
   * 
   * @param pData
   * @return
   */
  public DailyScheduleDef saveData(DailyScheduleDef pData)
  {
    return mDAO.saveData(pData);
  }
  
  /**
   * 
   * @param pData
   */
  public void deleteData(DailyScheduleDef pData)
  {
    mDAO.deleteData(pData.getId());
  }
}
