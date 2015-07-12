package com.peaksmartphone.manpowerplanner.core.gui.common;

import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.StandardEditToolbarActionListener;

/**
 * <p> Title: {@link DisplayController} </p>
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
public interface DisplayController<T> extends StandardEditToolbarActionListener
{
  /**
   * 
   * @param pSelectedRow
   */
  public void tableSelectedIndex(int pSelectedRow);
  
  /**
   * 
   * @return T
   */
  public T readDataObject();
  
  /**
   * 
   * @param pSelectedObject
   */
  public void setDataObject(T pSelectedObject);
  
  /**
   * status of Mask
   * @param pMaskStatus
   */
  public void setMaskStatus(MaskStatus pMaskStatus);
}
