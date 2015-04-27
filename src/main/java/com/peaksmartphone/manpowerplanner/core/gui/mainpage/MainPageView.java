package com.peaksmartphone.manpowerplanner.core.gui.mainpage;

import javax.swing.JFrame;

import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

/**
 * <p> Title: {@link MainPageView} </p>
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
public class MainPageView extends JFrame
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private final MainPageController mController;

  /**
   * @param pController
   */
  public MainPageView(MainPageController pController)
  {
    super();
    mController = pController;
    init();
  }
  
  private void init()
  {
    setTitle(ResourceBundle.getString(MainPageView.class.getName() + ".TITLE_MANPOWERPLANER"));
    
    setExtendedState(JFrame.MAXIMIZED_BOTH);
  }
  
}
