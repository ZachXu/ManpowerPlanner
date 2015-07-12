package com.peaksmartphone.manpowerplanner.core.services;

import java.util.List;

import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.db.dao.EmployeeDAO;

/**
 * <p> Title: {@link EmployeeService} </p>
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
public class EmployeeService
{
  private final EmployeeDAO mDAO;

  /**
   */
  public EmployeeService()
  {
    mDAO = new EmployeeDAO(MPPManager.getInstance().getSessionManager());
  }
  
  /**
   * @return
   */
  public static EmployeeService getInstance()
  {
    return new EmployeeService();
  }
  
  
  /**
   * 
   * @param pEmployee
   */
  public void deleteData(Employee pEmployee)
  {
    mDAO.deleteData(pEmployee.getId());
  }
  
  /**
   * 
   * @return
   */
  public List<Employee> getAllData()
  {
    return mDAO.getAllData();
  }
  
  /**
   * 
   * @param pId
   * @return
   */
  public Employee getData(String pId)
  {
    return mDAO.getData(pId);
  }
  
  /**
   * 
   * @param pIdList
   * @return
   */
  public List<Employee> getDataByIds(List<String> pIdList)
  {
    return mDAO.getDataByIds(pIdList);
  }
  
  /**
   * 
   * @param pData
   * @return
   */
  public Employee saveData(Employee pData)
  {
    return mDAO.saveData(pData);
  }
  
}
