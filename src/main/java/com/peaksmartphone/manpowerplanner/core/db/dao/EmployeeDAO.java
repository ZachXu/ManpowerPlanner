package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.List;

import org.hibernate.Query;

import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;
import com.peaksmartphone.manpowerplanner.utils.SerialClone;

/**
 * <p> Title: {@link EmployeeDAO} </p>
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
public class EmployeeDAO extends AbstractDAO<Employee>
{

  /**
   * @param pSessionManager
   */
  public EmployeeDAO(SessionManager pSessionManager)
  {
    super(pSessionManager);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getData(java.lang.String)
   */
  @Override
  public Employee getData(String pId)
  {
    return (Employee) mSessionManager.get(Employee.class, pId);
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getAllData()
   */
  @Override
  public List<Employee> getAllData()
  {
    String hql = "from " + Employee.class.getName() + " emp ";
    
    Query query = mSessionManager.createQuery(hql);
    
    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#getDataByIds(java.util.List)
   */
  @Override
  public List<Employee> getDataByIds(List<String> pIdList)
  {
    String hql = "from " + Employee.class.getName() + " emp "
        + " where emp.mId in (:mIdList)";
    
    Query query = mSessionManager.createQuery(hql);
    query.setParameterList("mIdList", pIdList);
    
    return query.list();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#saveData()
   */
  @Override
  public Employee saveData(Employee pData)
  {
    Employee retVal = SerialClone.clone(pData);
    
    if (pData.getVersion() == null)
    {
      mSessionManager.save(retVal);
    }
    else
    {
      retVal = (Employee) mSessionManager.merge(retVal);
    }
    
    return retVal;
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#deleteData(java.lang.String)
   */
  @Override
  public void deleteData(String pId)
  {
    mSessionManager.delete(getData(pId));
  }
}
