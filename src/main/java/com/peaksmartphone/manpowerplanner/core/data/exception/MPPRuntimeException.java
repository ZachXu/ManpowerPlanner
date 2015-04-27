package com.peaksmartphone.manpowerplanner.core.data.exception;

/**
 * <p> Title: {@link MPPRuntimeException} </p>
 * 
 * <b>Description:</b> 
 * <p> Insert Description here </p>
 *
 * @author zxu
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * $Rev: $:     Revision of last commit<br/>
 * $Author: $:  Author of last commit<br/>
 * $Date: $:    Date of last commit
 * 
 */
public class MPPRuntimeException extends RuntimeException
{

  /**
   * 
   */
  public MPPRuntimeException()
  {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @param pMessage
   * @param pCause
   */
  public MPPRuntimeException(String pMessage, Throwable pCause)
  {
    super(pMessage, pCause);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param pMessage
   */
  public MPPRuntimeException(String pMessage)
  {
    super(pMessage);
    // TODO Auto-generated constructor stub
  }

  /**
   * @param pCause
   */
  public MPPRuntimeException(Throwable pCause)
  {
    super(pCause);
    // TODO Auto-generated constructor stub
  }
  
}
