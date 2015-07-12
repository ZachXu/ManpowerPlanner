package com.peaksmartphone.manpowerplanner.core.gui.common;

import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;

/**
 * <p> Title: {@link AbstractInputPanel} </p>
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
public abstract class AbstractInputPanel extends JPanel
{
  
  /**
   * 
   */
  public AbstractInputPanel()
  {
    super(new GridBagLayout());
  }

  /**
   * init panel
   */
  protected abstract void init();

  /**
   * @param pMaskStatus
   */
  public void setMaskStatus(MaskStatus pMaskStatus)
  {
    switch (pMaskStatus)
    {
    case NEW:
    case EDIT:
      enableAll(true);
      break;
    case SELECTED:
    case NOT_SELECTED:
      enableAll(false);
      break;
    default:
      enableAll(false);
      break;
    }
  }
  
  /**
   * 
   */
  protected abstract void enableAll(boolean pEnabled);
}
