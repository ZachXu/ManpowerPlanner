package com.peaksmartphone.manpowerplanner.core.data;

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
	/**
   * 
   */
  private static final long serialVersionUID = 1L;
  private final String mName;
	private final boolean mIsRestDay;
	private final int mMaxEmployeeAmount;
	private final int mSortIndex;
	
	/**
	 * 
	 * @param pId
	 * @param pVersion
	 * @param pName
	 * @param pIsRestDay
	 * @param pMaxEmployeeAmount
	 * @param pSortIndex
	 */
	public DailyScheduleDef(String pId, Integer pVersion, String pName,
            boolean pIsRestDay, int pMaxEmployeeAmount, int pSortIndex)
  {
    super(pId, pVersion);
    mName = pName;
    mIsRestDay = pIsRestDay;
    mMaxEmployeeAmount = pMaxEmployeeAmount;
    mSortIndex = pSortIndex;
  }
	
	/**
	 * 
	 * @param pName
	 * @param pIsRestDay
	 * @param pMaxEmployeeAmount
	 * @param pSortIndex
	 * @return
	 */
	public static DailyScheduleDef newInstance(String pName,
      boolean pIsRestDay, int pMaxEmployeeAmount, int pSortIndex)
  {
	  return new DailyScheduleDef(IdGenerator.createId(), null, 
	      pName, pIsRestDay, pMaxEmployeeAmount, pSortIndex);
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
		return mMaxEmployeeAmount;
	}

	/**
	 * @return the sortIndex
	 */
	public int getSortIndex()
	{
		return mSortIndex;
	}
}
