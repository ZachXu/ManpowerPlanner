package com.peaksmartphone.manpowerplanner.core.gui.mainpage;

import java.util.Locale;

import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

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

  /**
   * 
   */
  public void openWorkCalendar()
  {
    // TODO Auto-generated method stub
    
  }

  /**
   * 
   */
  public void openEmployeeManagement()
  {
    // TODO Auto-generated method stub
    
  }

  /**
   * 
   */
  public void openDailyScheduleDefManagement()
  {
    // TODO Auto-generated method stub
    
  }

  /**
   * 
   */
  public void openEmployeePreferenceManage()
  {
    // TODO Auto-generated method stub
    
  }

  /**
   * @param pLocale
   */
  public void openLanguageChoice(Locale pLocale)
  {
    ResourceBundle.setLocale(pLocale);
    
    mView.updateMenuText();
  }
  
  
}
