package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;
import com.peaksmartphone.manpowerplanner.utils.SerialClone;

/**
 * <p> Title: {@link DailyScheduleInstDAO} </p>
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
public class DailyScheduleInstDAO extends AbstractDAO<DailyScheduleInst>
{

  /**
   * @param pSessionManager
   */
  public DailyScheduleInstDAO(SessionManager pSessionManager)
  {
    super(pSessionManager);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getData(java.lang.String)
   */
  @Override
  public DailyScheduleInst getData(String pId)
  {
    return (DailyScheduleInst)mSessionManager.get(DailyScheduleInst.class, pId);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getAllData()
   */
  @Override
  public List<DailyScheduleInst> getAllData()
  {
    String hql = "from " + DailyScheduleInst.class.getName() + " dsi ";

    Query query = mSessionManager.createQuery(hql);

    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getDataByIds(java.util.List)
   */
  @Override
  public List<DailyScheduleInst> getDataByIds(List<String> pIdList)
  {
    String hql = "from " + DailyScheduleInst.class.getName() + " dsi "
        + " where dsi.mId in (:mIdList)";

    Query query = mSessionManager.createQuery(hql);
    query.setParameterList("mIdList", pIdList);

    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#saveData(java.lang.Object)
   */
  @Override
  public DailyScheduleInst saveData(DailyScheduleInst pData)
  {
    DailyScheduleInst retVal = SerialClone.clone(pData);
    
    if (pData.getVersion() == null)
    {
      mSessionManager.save(retVal);
    }
    else
    {
      retVal = (DailyScheduleInst) mSessionManager.merge(retVal);
    }
    
    return retVal;
  }
  
  /**
   * 
   * @param pBegin begin date for the querying
    * @param pEnd end date for the querying
   * @return list of {@link DailyScheduleInst} result
   */
  public List<DailyScheduleInst> getDailyScheduleInstInPeriod(Date pBegin, Date pEnd)
  {
    String hql = "from DailyScheduleInst di "
        + " where di.mScheduledDate >= :begindate "
        + " and di.mScheduledDate <= :enddate";
    
    Query query = mSessionManager.createQuery(hql);
    
    query.setDate("begindate", pBegin);
    query.setDate("enddate", pEnd);
    
    return query.list();
  }

}
