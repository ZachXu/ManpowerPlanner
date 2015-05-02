package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.peaksmartphone.manpowerplanner.TestData;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;

/**
 * <p> Title: {@link EmployeeDAOTest} </p>
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
public class EmployeeDAOTest extends TestCase
{

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.EmployeeDAO#deleteData(java.lang.String)}.
   */
  public void testDeleteData()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    Employee data1 = Employee.newInstance("zxu", 5);
    
    EmployeeDAO dao = new EmployeeDAO(manager);
    
    dao.saveData(data1);
    
    assertEquals(data1, dao.getData(data1.getId()));
    
    dao.deleteData(data1.getId());
    
    assertEquals(null, dao.getData(data1.getId()));
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.EmployeeDAO#getData(java.lang.String)}.
   */
  public void testGetDataString()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    Employee data1 = Employee.newInstance("zxu", 5);
    
    EmployeeDAO dao = new EmployeeDAO(manager);
    
    dao.saveData(data1);
    
    assertEquals(data1, dao.getData(data1.getId()));
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.EmployeeDAO#getAllData()}.
   */
  public void testGetAllData()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    Employee data1 = Employee.newInstance("zxu", 5);
    Employee data2 = Employee.newInstance("lli", 5);
    Employee data3 = Employee.newInstance("jku", 5);
    
    EmployeeDAO dao = new EmployeeDAO(manager);
    
    dao.saveData(data1);
    dao.saveData(data2);
    dao.saveData(data3);
    
    assertEquals(3, dao.getAllData().size());
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.EmployeeDAO#getDataByIds(java.util.List)}.
   */
  public void testGetDataByIdsListOfString()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    Employee data1 = Employee.newInstance("zxu", 5);
    Employee data2 = Employee.newInstance("lli", 5);
    Employee data3 = Employee.newInstance("jku", 5);
    
    EmployeeDAO dao = new EmployeeDAO(manager);
    
    dao.saveData(data1);
    dao.saveData(data2);
    dao.saveData(data3);
    
    List<Employee> empList = dao.getDataByIds(Arrays.asList(data1.getId(), data2.getId()));
    assertEquals(2, empList.size());
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.EmployeeDAO#saveData(com.peaksmartphone.manpowerplanner.core.data.Employee)}.
   */
  public void testSaveDataEmployee()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    Employee saveData = Employee.newInstance("zxu", 5);
    
    EmployeeDAO dao = new EmployeeDAO(manager);
    
    dao.saveData(saveData);
    
    String id = saveData.getId();
    
    Employee dataobj = dao.getData(id);
    
    assertEquals(dataobj, saveData);
  }

}
