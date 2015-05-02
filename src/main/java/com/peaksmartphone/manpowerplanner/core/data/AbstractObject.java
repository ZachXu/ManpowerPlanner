package com.peaksmartphone.manpowerplanner.core.data;

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

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mId == null) ? 0 : mId.hashCode());
    return result;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    AbstractObject other = (AbstractObject) obj;
    if (mId == null)
    {
      if (other.mId != null)
        return false;
    }
    else if (!mId.equals(other.mId))
      return false;
    return true;
  }
}
