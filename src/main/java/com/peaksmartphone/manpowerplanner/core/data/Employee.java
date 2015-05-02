package com.peaksmartphone.manpowerplanner.core.data;

/**
 * <p> Title: {@link Employee}</p>
 * 
 * <b>Description:</b> 
 * <p> Employee Object Data </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * 
 */
public class Employee extends AbstractObject
{
	/**
	 * 
	 */
  private static final long serialVersionUID = 1L;

	private final String mEmployeeName;
	private final int mMaxOccurInWeek;
	
	public final static Employee EMPTY_OBJECT = new Employee(null, null, null, 5);

	/**
   * @param pId
   * @param pVersion
   * @param pEmployeeName
   * @param pMaxOccurInWeek
   */
  private Employee(String pId, Integer pVersion, String pEmployeeName, int pMaxOccurInWeek)
  {
    super(pId, pVersion);
    mEmployeeName = pEmployeeName;
    mMaxOccurInWeek = pMaxOccurInWeek;
  }
  
  /**
   * 
   * @param pEmployeeName
   * @param pMaxOccurInWeek
   * @return
   */
  public static Employee newInstance(String pEmployeeName, int pMaxOccurInWeek)
  {
    return new Employee(IdGenerator.createId(), null, pEmployeeName, pMaxOccurInWeek);
  }
  
  /**
   * 
   * @param pNullAbleValue
   * @return
   */
  public static Employee getEmptyObjectOrValue(Employee pNullAbleValue)
  {
    Employee returnVal = EMPTY_OBJECT;
    
    if (pNullAbleValue != null)
    {
       returnVal = pNullAbleValue;
    }
    
    return returnVal;
  }

  /**
   * @return the employeeName
   */
  public String getEmployeeName()
  {
    return mEmployeeName;
  }

  /**
   * @return the maxOccurInWeek
   */
  public int getMaxOccurInWeek()
  {
    return mMaxOccurInWeek;
  }
}
