package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.List;

import com.peaksmartphone.manpowerplanner.core.db.SessionManager;

/**
 * <p> Title: {@link AbstractDAO} </p>
 * 
 * <b>Description:</b> 
 * <p> Abstract DAO class </p>
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
public abstract class AbstractDAO<T>
{
  protected final SessionManager mSessionManager;

  /**
   * @param pSessionManager
   */
  public AbstractDAO(SessionManager pSessionManager)
  {
    super();
    mSessionManager = pSessionManager;
  }
  
 /**
  * 
  * @return Data Object with pId
  */
 public abstract T getData(String pId);
 
 /**
  * 
  * @return All Data Object in Database
  */
 public abstract List<T> getAllData();
 
 /**
  * 
  * @param pIdList list of Ids
  * @return all Data for the Ids
  */
 public abstract List<T> getDataByIds(List<String> pIdList);
  
 /**
  * 
  * @return saved Data
  */
 public abstract T saveData(T pData);
 
 /**
  * 
  * @param pId delete data
  */
 public void deleteData(String pId)
 {
   mSessionManager.delete(getData(pId));
 }
  
}
