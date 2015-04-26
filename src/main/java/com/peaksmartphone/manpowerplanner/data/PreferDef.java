package com.peaksmartphone.manpowerplanner.data;

import java.util.Date;

/**
 * <p> Title: {@link PreferDef}</p>
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
public class PreferDef extends AbstractObject
{
	private final Employee mEmployee;
	private final DailyScheduleDef mDailyScheduleDef;
	private final Date mPreferDate;
	
	/**
	 * 
	 * @param pId
	 * @param pVersion
	 * @param pEmployee
	 * @param pDailyScheduleDef
	 * @param pPreferDate
	 */
	public PreferDef(String pId, Integer pVersion, Employee pEmployee,
            DailyScheduleDef pDailyScheduleDef, Date pPreferDate)
    {
	    super(pId, pVersion);
	    mEmployee = pEmployee;
	    mDailyScheduleDef = pDailyScheduleDef;
	    mPreferDate = pPreferDate;
    }

	/**
	 * @return the employee
	 */
	public Employee getEmployee()
	{
		return mEmployee;
	}
	
	/**
	 * @return the dailyScheduleDef
	 */
	public DailyScheduleDef getDailyScheduleDef()
	{
		return mDailyScheduleDef;
	}
	
	/**
	 * @return the preferDate
	 */
	public Date getPreferDate()
	{
		return mPreferDate;
	}
	
	
}
