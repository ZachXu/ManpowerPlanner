package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.List;

import org.hibernate.Query;

import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.PreferDef;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;
import com.peaksmartphone.manpowerplanner.utils.SerialClone;

/**
 * <p> Title: {@link PreferDefDAO} </p>
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
public class PreferDefDAO extends AbstractDAO<PreferDef>
{

  /**
   * @param pSessionManager
   */
  public PreferDefDAO(SessionManager pSessionManager)
  {
    super(pSessionManager);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getData(java.lang.String)
   */
  @Override
  public PreferDef getData(String pId)
  {
    return (PreferDef)mSessionManager.get(PreferDef.class, pId);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getAllData()
   */
  @Override
  public List<PreferDef> getAllData()
  {
    String hql = "from " + PreferDef.class.getName();
    
    Query query = mSessionManager.createQuery(hql);
    
    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getDataByIds(java.util.List)
   */
  @Override
  public List<PreferDef> getDataByIds(List<String> pIdList)
  {
    String hql = "from " + PreferDef.class.getName() + " pfd "
        + " where pfd.mId in (:mIdList)";

    Query query = mSessionManager.createQuery(hql);
    query.setParameterList("mIdList", pIdList);

    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#saveData(java.lang.Object)
   */
  @Override
  public PreferDef saveData(PreferDef pData)
  {
    PreferDef retVal = SerialClone.clone(pData);
    
    if (pData.getVersion() == null)
    {
      mSessionManager.save(retVal);
    }
    else
    {
      retVal = (PreferDef) mSessionManager.merge(retVal);
    }
    
    return retVal;
  }
  
}