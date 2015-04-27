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
    
	private final DailyScheduleDef mDailyScheduleDef;
	private final Date mScheduledDate;
	private final Set<Employee> mAssignedEmployee;
	
	/**
	 * 
	 * @param pId
	 * @param pVersion
	 * @param pDailyScheduleDef
	 * @param pScheduledDate
	 * @param pAssignedEmployee
	 */
	public DailyScheduleInst(String pId, Integer pVersion,
            DailyScheduleDef pDailyScheduleDef, Date pScheduledDate,
            Set<Employee> pAssignedEmployee)
    {
	    super(pId, pVersion);
	    mDailyScheduleDef = pDailyScheduleDef;
	    mScheduledDate = pScheduledDate;
	    
	    if (pAssignedEmployee == null)
	    {
	    	mAssignedEmployee = new HashSet<Employee>();
	    }
	    else
	    {
	    	mAssignedEmployee = pAssignedEmployee;
	    }
    }

	/**
	 * @return the dailyScheduleDef
	 */
	public DailyScheduleDef getDailyScheduleDef()
	{
		return mDailyScheduleDef;
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
	public Set<Employee> getAssignedEmployee()
	{
		return mAssignedEmployee;
	}
}
