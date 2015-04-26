package com.peaksmartphone.manpowerplanner.data;

/**
 * <p> Title: {@link DailyScheduleDef}</p>
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
public class DailyScheduleDef extends AbstractObject
{
	private final String mName;
	private final boolean mIsRestDay;
	private final int mEmployeeAmount;
	private final int mSortIndex;
	
	/**
	 * 
	 * @param pId
	 * @param pVersion
	 * @param pName
	 * @param pIsRestDay
	 * @param pEmployeeAmount
	 * @param pSortIndex
	 */
	public DailyScheduleDef(String pId, Integer pVersion, String pName,
            boolean pIsRestDay, int pEmployeeAmount, int pSortIndex)
    {
	    super(pId, pVersion);
	    mName = pName;
	    mIsRestDay = pIsRestDay;
	    mEmployeeAmount = pEmployeeAmount;
	    mSortIndex = pSortIndex;
    }

	/**
	 * @return the name
	 */
	public String getName()
	{
		return mName;
	}

	/**
	 * @return the isRestDay
	 */
	public boolean isIsRestDay()
	{
		return mIsRestDay;
	}

	/**
	 * @return the employeeAmount
	 */
	public int getEmployeeAmount()
	{
		return mEmployeeAmount;
	}

	/**
	 * @return the sortIndex
	 */
	public int getSortIndex()
	{
		return mSortIndex;
	}
}
