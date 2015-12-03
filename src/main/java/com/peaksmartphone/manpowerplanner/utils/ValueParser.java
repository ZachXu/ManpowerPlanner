package com.peaksmartphone.manpowerplanner.utils;

/**
 * <p> Title: {@link ValueParser} </p>
 * 
 * <b>Description:</b> 
 * <p> Insert Description here </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * $Rev: $:     Revision of last commit<br/>
 * $Author: $:  Author of last commit<br/>
 * $Date: $:    Date of last commit
 * 
 */
public interface ValueParser<T>
{
  /**
   * parse data to String value
   * @param pData
   * @return
   */
  public String parseValue(T pData);
}
