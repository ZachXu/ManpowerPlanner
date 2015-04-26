package com.peaksmartphone.manpowerplanner.data;

import java.io.Serializable;

/**
 * <p> Title: {@link AbstractObject}</p>
 * 
 * <b>Description:</b> 
 * <p> Abstract Object </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * 
 */
public abstract class AbstractObject implements Serializable
{
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
	protected final String mId;
	protected final Integer mVersion;
	
	
	public AbstractObject(String pId, Integer pVersion)
    {
	    super();
	    mId = pId;
	    mVersion = pVersion;
    }

	/**
	 * 
	 * @return
	 */
	public String getId()
	{
		return mId;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getVersion()
	{
		return mVersion;
	}
}
