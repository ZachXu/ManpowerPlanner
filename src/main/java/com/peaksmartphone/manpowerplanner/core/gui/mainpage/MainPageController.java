package com.peaksmartphone.manpowerplanner.core.gui.mainpage;

import java.util.Locale;

import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.gui.dailyschedule.DailyScheduleDefManageController;
import com.peaksmartphone.manpowerplanner.core.gui.employee.EmployeeManageController;
import com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst.WeeklyScheduleInstController;
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
    final WeeklyScheduleInstController weeklyController = new WeeklyScheduleInstController();
    mView.getTabbedPane().addTab(
        ResourceBundle.getString(MainPageController.class.getName() + ".TAB_WORKCALENDAR"), 
        weeklyController.getView());
  }

  /**
   * 
   */
  public void openEmployeeManagement()
  {
    final EmployeeManageController empController = new EmployeeManageController();
    mView.getTabbedPane().addTab(
        ResourceBundle.getString(MainPageController.class.getName() + ".TAB_EMPLOYEE"), 
        empController.getView());
  }

  /**
   * 
   */
  public void openDailyScheduleDefManagement()
  {
    final DailyScheduleDefManageController controller = new DailyScheduleDefManageController();
    mView.getTabbedPane().addTab(
        ResourceBundle.getString(MainPageController.class.getName() + ".TAB_DAILYDEF"), 
        controller.getView());
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
