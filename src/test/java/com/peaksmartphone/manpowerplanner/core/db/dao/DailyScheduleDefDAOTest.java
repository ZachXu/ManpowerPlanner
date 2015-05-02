package com.peaksmartphone.manpowerplanner.core.db.dao;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import com.peaksmartphone.manpowerplanner.TestData;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.db.SessionManager;

/**
 * <p> Title: {@link DailyScheduleDefDAOTest} </p>
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
public class DailyScheduleDefDAOTest extends TestCase
{

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.DailyScheduleDefDAO#deleteData(java.lang.String)}.
   */
  public void testDeleteData()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    DailyScheduleDef data1 = DailyScheduleDef.newInstance("zxu", true, 1, 1);
    
    DailyScheduleDefDAO dao = new DailyScheduleDefDAO(manager);
    
    dao.saveData(data1);
    
    assertEquals(data1, dao.getData(data1.getId()));
    
    dao.deleteData(data1.getId());
    
    assertEquals(null, dao.getData(data1.getId()));
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.DailyScheduleDefDAO#getData(java.lang.String)}.
   */
  public void testGetDataString()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    DailyScheduleDef data1 = DailyScheduleDef.newInstance("zxu", true, 1, 1);
    
    DailyScheduleDefDAO dao = new DailyScheduleDefDAO(manager);
    
    dao.saveData(data1);
    
    assertEquals(data1, dao.getData(data1.getId()));
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.DailyScheduleDefDAO#getAllData()}.
   */
  public void testGetAllData()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    DailyScheduleDef data1 = DailyScheduleDef.newInstance("lli", true, 1, 1);
    DailyScheduleDef data2 = DailyScheduleDef.newInstance("zxu", false, 2, 2);
    DailyScheduleDef data3 = DailyScheduleDef.newInstance("zxu", true, 3, 3);
    
    DailyScheduleDefDAO dao = new DailyScheduleDefDAO(manager);
    
    dao.saveData(data1);
    dao.saveData(data2);
    dao.saveData(data3);
    
    assertEquals(3, dao.getAllData().size());
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.DailyScheduleDefDAO#getDataByIds(java.util.List)}.
   */
  public void testGetDataByIdsListOfString()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    DailyScheduleDef data1 = DailyScheduleDef.newInstance("lli", true, 1, 1);
    DailyScheduleDef data2 = DailyScheduleDef.newInstance("zxu", false, 2, 2);
    DailyScheduleDef data3 = DailyScheduleDef.newInstance("zxu", true, 3, 3);
    
    DailyScheduleDefDAO dao = new DailyScheduleDefDAO(manager);
    
    dao.saveData(data1);
    dao.saveData(data2);
    dao.saveData(data3);
    
    List<DailyScheduleDef> dataList = dao.getDataByIds(Arrays.asList(data1.getId(), data2.getId()));
    assertEquals(2, dataList.size());
  }

  /**
   * Test method for {@link com.peaksmartphone.manpowerplanner.core.db.dao.DailyScheduleDefDAO#saveData(com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef)}.
   */
  public void testSaveDataDailyScheduleDef()
  {
    SessionManager manager = TestData.getTestSessionManager();
    
    DailyScheduleDef data = DailyScheduleDef.newInstance("lli", true, 1, 1);
    
    DailyScheduleDefDAO dao = new DailyScheduleDefDAO(manager);
    
    dao.saveData(data);
    
    String id = data.getId();
    
    DailyScheduleDef dataobj = dao.getData(id);
    
    assertEquals(dataobj, data);
  }

}
