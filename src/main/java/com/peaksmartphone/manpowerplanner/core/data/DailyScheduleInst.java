package com.peaksmartphone.manpowerplanner.core.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p> Title: {@link DailyScheduleInst}</p>
 * 
 * <b>Description:</b> 
 * <p>  </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * 
 */
public class DailyScheduleInst extends AbstractObject
{
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;
    
	private final String mDailyScheduleDefId;
	private final Date mScheduledDate;
	private final Set<Employee> mAssignedEmployees;
	
  /**
   * @param pId
   * @param pVersion
   * @param pDailyScheduleDefId
   * @param pScheduledDate
   * @param pAssignedEmployees
   */
  public DailyScheduleInst(String pId, Integer pVersion, String pDailyScheduleDefId, Date pScheduledDate,
      Set<Employee> pAssignedEmployees)
  {
    super(pId, pVersion);
    mDailyScheduleDefId = pDailyScheduleDefId;
    mScheduledDate = pScheduledDate;
    
    if (pAssignedEmployees == null)
    {
      mAssignedEmployees = new HashSet<Employee>();
    }
    else
    {
      mAssignedEmployees = pAssignedEmployees;
    }
  }
  
  /**
   * 
   * @param pDailyScheduleDefId
   * @param pScheduledDate
   * @param pAssignedEmployee
   * @return
   */
  public static DailyScheduleInst newInstance(String pDailyScheduleDefId, Date pScheduledDate,
      Set<Employee> pAssignedEmployee)
  {
    return new DailyScheduleInst(IdGenerator.createId(), null, pDailyScheduleDefId, pScheduledDate, pAssignedEmployee);
  }

  /**
   * @return the dailyScheduleDefId
   */
  public String getDailyScheduleDefId()
  {
    return mDailyScheduleDefId;
  }

  /**
   * @return the scheduledDate
   */
  public Date getScheduledDate()
  {
    return mScheduledDate;
  }

  /**
   * @return the assignedEmployee
   */
  public Set<Employee> getAssignedEmployees()
  {
    return mAssignedEmployees;
  }
	
}
