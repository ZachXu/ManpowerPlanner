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
public class DailyScheduleDef extends AbstractObject implements Comparable<DailyScheduleDef>
{
	/**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private static final int DEFAULT_MAXEMPLOYEEAMOUNT = 3;
  
  private final String mName;
	private final boolean mIsRestDay;
	private final Integer mMaxEmployeeAmount;
	private final Integer mSortIndex;
	
	public static DailyScheduleDef EMPTY_OBJECT = new DailyScheduleDef();
	
	public DailyScheduleDef()
	{
	  this(null, null, null, false, DEFAULT_MAXEMPLOYEEAMOUNT, Integer.MAX_VALUE);
	}
	
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
	 * @return
	 */
	public static DailyScheduleDef newInstance(DailyScheduleDef pLastDailyScheduleDef)
  {
	  int sortIndex = 0;
	  
	  if (pLastDailyScheduleDef != null && !DailyScheduleDef.EMPTY_OBJECT.equals(pLastDailyScheduleDef))
	  {
	    sortIndex = pLastDailyScheduleDef.getSortIndex() + 10;
	  }
	  
    return new DailyScheduleDef(IdGenerator.createId(), null, null, false, 
        DEFAULT_MAXEMPLOYEEAMOUNT, sortIndex);
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
	public Integer getEmployeeAmount()
	{
		return mMaxEmployeeAmount;
	}

	/**
	 * @return the sortIndex
	 */
	public Integer getSortIndex()
	{
		return mSortIndex;
	}
	
	/**
	 * 
	 * @param pId
	 * @param pVersion
	 * @param pName
	 * @param pIsRestDay
	 * @param pMaxEmployeeAmount
	 * @param pSortIndex
	 * @return
	 */
  public static DailyScheduleDef valueOf(String pId, Integer pVersion, String pName, boolean pIsRestDay,
      int pMaxEmployeeAmount, int pSortIndex)
  {
    return new DailyScheduleDef(pId, pVersion, pName, pIsRestDay, pMaxEmployeeAmount, pSortIndex);
  }

  /* (non-Javadoc)
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(DailyScheduleDef pO)
  {
    if (pO == null)
    {
      return -1;
    }
    
    return getSortIndex().compareTo(pO.getSortIndex());
  }
}
