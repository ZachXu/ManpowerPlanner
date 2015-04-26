package com.peaksmartphone.manpowerplanner.data;

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

	private final String mEmplyeeName;
	
	public final static Employee EMPTY_OBJECT = new Employee(null, null, null);

	/**
	 * 
	 * @param pId
	 * @param pVersion
	 * @param pEmplyeeName
	 */
	private Employee(String pId, Integer pVersion, String pEmplyeeName)
    {
	    super(pId, pVersion);
	    mEmplyeeName = pEmplyeeName;
    }

	public String getEmplyeeName()
	{
		return mEmplyeeName;
	}
	
	
}
