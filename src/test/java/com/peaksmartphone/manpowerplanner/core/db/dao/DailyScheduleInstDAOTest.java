package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.peaksmartphone.manpowerplanner.TestData;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;

import junit.framework.TestCase;

/**
 * <p> Title: {@link DailyScheduleInstDAOTest} </p>
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
public class DailyScheduleInstDAOTest extends TestCase
{
  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.AbstractDAO#deleteData(java.lang.String)}.
   */
  public void testDeleteData()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    EmployeeDAO empDAO = new EmployeeDAO(manager);
    
    Employee emp1 = Employee.newInstance("zxu", 5);
    Employee emp2 = Employee.newInstance("老党", 5);
    empDAO.saveData(emp1);
    empDAO.saveData(emp2);
    
    DailyScheduleDefDAO dsdDAO = new DailyScheduleDefDAO(manager);
    
    DailyScheduleDef dsd = DailyScheduleDef.newInstance("dsdtest", true, 0, 1);
    
    dsdDAO.saveData(dsd);
    
    Set<Employee> empSet = new HashSet<Employee>();
    empSet.add(empDAO.getData(emp1.getId()));
    empSet.add(empDAO.getData(emp2.getId()));
    
    DailyScheduleInst dsi = DailyScheduleInst.newInstance(
        dsd.getId(), new Date(), empSet);
    
    DailyScheduleInstDAO dao = new DailyScheduleInstDAO(manager);
    dao.saveData(dsi);
    
    assertEquals(dsi, dao.getData(dsi.getId()));
    
    dao.deleteData(dsi.getId());
    
    assertEquals(null, dao.getData(dsi.getId()));
  }

}
