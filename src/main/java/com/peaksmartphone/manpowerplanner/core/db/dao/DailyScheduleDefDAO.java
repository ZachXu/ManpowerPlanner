package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.List;

import org.hibernate.Query;

import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;
import com.peaksmartphone.manpowerplanner.utils.SerialClone;

/**
 * <p> Title: {@link DailyScheduleDefDAO} </p>
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
public class DailyScheduleDefDAO extends AbstractDAO<DailyScheduleDef>
{

  /**
   * @param pSessionManager
   */
  public DailyScheduleDefDAO(SessionManager pSessionManager)
  {
    super(pSessionManager);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getData(java.lang.String)
   */
  @Override
  public DailyScheduleDef getData(String pId)
  {
    return (DailyScheduleDef)mSessionManager.get(DailyScheduleDef.class, pId);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getAllData()
   */
  @Override
  public List<DailyScheduleDef> getAllData()
  {
    String hql = "FROM " + DailyScheduleDef.class.getName() + " dsd ORDER BY dsd.mSortIndex ASC";
    
    Query query = mSessionManager.createQuery(hql);
    
    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getDataByIds(java.util.List)
   */
  @Override
  public List<DailyScheduleDef> getDataByIds(List<String> pIdList)
  {
    String hql = "from " + DailyScheduleDef.class.getName() + " mDSD "
                + " where mDSD.mId in (:mIdList)";
    
    Query query = mSessionManager.createQuery(hql);
    query.setParameterList("mIdList", pIdList);
    
    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#saveData(java.lang.Object)
   */
  @Override
  public DailyScheduleDef saveData(DailyScheduleDef pData)
  {
    DailyScheduleDef retVal = SerialClone.clone(pData);
    
    if (pData.getVersion() == null)
    {
      mSessionManager.save(retVal);
    }
    else
    {
      retVal = (DailyScheduleDef) mSessionManager.merge(retVal);
    }
    
    return retVal;
  }

}
