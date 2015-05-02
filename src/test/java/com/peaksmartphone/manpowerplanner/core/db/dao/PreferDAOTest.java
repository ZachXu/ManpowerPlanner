package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.peaksmartphone.manpowerplanner.TestData;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.data.PreferDef;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;

/**
 * <p> Title: {@link PreferDAOTest} </p>
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
public class PreferDAOTest extends TestCase
{

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.PreferDefDAO#getAllData()}.
   */
  public void testGetAllData()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    EmployeeDAO empDAO = new EmployeeDAO(manager);
    
    Employee emp1 = Employee.newInstance("zxu", 5);
    Employee emp2 = Employee.newInstance("老党", 5);
    empDAO.saveData(emp1);
    empDAO.saveData(emp2);
    
    DailyScheduleDefDAO dsdDAO = new DailyScheduleDefDAO(manager);
    
    DailyScheduleDef dsd1 = DailyScheduleDef.newInstance("dsdtest", true, 0, 1);
    DailyScheduleDef dsd2 = DailyScheduleDef.newInstance("yeban", true, 0, 1);
    
    dsdDAO.saveData(dsd1);
    dsdDAO.saveData(dsd2);
    
    PreferDef pfd = PreferDef.newInstance(
        empDAO.getData(emp1.getId()), 
        dsdDAO.getData(dsd1.getId()), 
        new Date());
    PreferDef pfd2 = PreferDef.newInstance(
        empDAO.getData(emp2.getId()), 
        dsdDAO.getData(dsd2.getId()), 
        new Date());
    
    PreferDefDAO dao = new PreferDefDAO(manager);
    
    dao.saveData(pfd);
    dao.saveData(pfd2);
    
    List<PreferDef> pfdlist = dao.getAllData();
    
    assertEquals(2, pfdlist.size());
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.PreferDefDAO#getDataByIds(java.util.List)}.
   */
  public void testGetDataByIdsListOfString()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    EmployeeDAO empDAO = new EmployeeDAO(manager);
    
    Employee emp1 = Employee.newInstance("zxu", 5);
    Employee emp2 = Employee.newInstance("老党", 5);
    empDAO.saveData(emp1);
    empDAO.saveData(emp2);
    
    DailyScheduleDefDAO dsdDAO = new DailyScheduleDefDAO(manager);
    
    DailyScheduleDef dsd1 = DailyScheduleDef.newInstance("dsdtest", true, 0, 1);
    DailyScheduleDef dsd2 = DailyScheduleDef.newInstance("yeban", true, 0, 1);
    
    dsdDAO.saveData(dsd1);
    dsdDAO.saveData(dsd2);
    
    PreferDef pfd = PreferDef.newInstance(
        empDAO.getData(emp1.getId()), 
        dsdDAO.getData(dsd1.getId()), 
        new Date());
    PreferDef pfd2 = PreferDef.newInstance(
        empDAO.getData(emp2.getId()), 
        dsdDAO.getData(dsd2.getId()), 
        new Date());
    
    PreferDefDAO dao = new PreferDefDAO(manager);
    
    dao.saveData(pfd);
    dao.saveData(pfd2);
    
    List<PreferDef> pfdlist = dao.getDataByIds(Arrays.asList(pfd.getId(), pfd2.getId()));
    
    assertEquals(2, pfdlist.size());
    
    manager.rollback();
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.PreferDefDAO#saveData(com.peaksmartphone.manpowerplanner.core.data.PreferDef)}.
   */
  public void testSaveDataPreferDef()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    Employee emp = Employee.newInstance("zxu", 5);
    DailyScheduleDef dsd = DailyScheduleDef.newInstance("dsdtest", true, 0, 1);
    
    PreferDef pfd = PreferDef.newInstance(emp, dsd, new Date());
    
    PreferDefDAO dao = new PreferDefDAO(manager);
    
    dao.saveData(pfd);
    
    assertEquals(pfd, dao.getData(pfd.getId()));
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#deleteData(java.lang.String)}.
   */
  public void testDeleteData()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    Employee emp = Employee.newInstance("zxu", 5);
    DailyScheduleDef dsd = DailyScheduleDef.newInstance("dsdtest", true, 0, 1);
    
    PreferDef pfd = PreferDef.newInstance(emp, dsd, new Date());
    
    PreferDefDAO dao = new PreferDefDAO(manager);
    
    dao.saveData(pfd);
    
    assertEquals(pfd, dao.getData(pfd.getId()));
    
    dao.deleteData(pfd.getId());
    
    assertEquals(null, dao.getData(pfd.getId()));
  }

}
