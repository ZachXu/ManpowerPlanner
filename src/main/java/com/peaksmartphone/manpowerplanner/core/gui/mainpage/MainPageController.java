package com.peaksmartphone.manpowerplanner.core.gui.mainpage;

import com.peaksmartphone.manpowerplanner.core.MPPManager;

/**
 * <p> Title: {@link MainPageController}</p>
 * 
 * <b>Description:</b> 
 * <p>  </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * 
 */
public class MainPageController
{
  private final MainPageView mView;

  /**
   * 
   */
  public MainPageController()
  {
    super();
    
    mView = new MainPageView(this);
    
    MPPManager.getInstance().setApplicationWindow(mView);
    MPPManager.getInstance().setMainPageController(this);
  }

  /**
   * 
   */
  public void show()
  {
    mView.setVisible(true);
  }
  
  
}
