package com.peaksmartphone.manpowerplanner.core.data;

import java.util.UUID;

/**
 * <p> Title: {@link IdGenerator} </p>
 * 
 * <b>Description:</b> 
 * <p> Id Generator </p>
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
public class IdGenerator
{
  /**
   * 
   * @return weltweit eindeutig ID
   */
  public static String createId() 
  {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
}
