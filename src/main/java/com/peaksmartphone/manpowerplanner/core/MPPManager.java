package com.peaksmartphone.manpowerplanner.core;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.peaksmartphone.manpowerplanner.core.db.SessionManager;
import com.peaksmartphone.manpowerplanner.core.gui.mainpage.MainPageController;
import com.peaksmartphone.manpowerplanner.utils.MPPProperties;

/**
 * <p> Title: {@link MPPManager}</p>
 * 
 * <b>Description:</b> 
 * <p> Controls all important application information. </p>
 *
 * @author zxu
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * 
 */
public class MPPManager
{
  private static final Logger LOG = LogManager.getLogger(MPPManager.class);
  
  private final SessionManager sessionManager;
  private JFrame mApplicationWindow;
  private MainPageController mMainPageController;

  private static MPPManager instance = null;
  
  /**
   * hidden constructor
   */
  private MPPManager()
  {
    this(
        MPPProperties.getDBUser(), 
        MPPProperties.getDBPassword(),
        MPPProperties.getDBURL(),
        MPPProperties.getDBSchema(),
        MPPProperties.getDBDriver(),
        MPPProperties.getDBDialect());
  }
  
  private MPPManager(String pDBUser,
      String pDBPassword,
      String pDBURL,
      String pDBSchema,
      String pDBDriver,
      String pDBDialect)
  {
	  
    sessionManager = new SessionManager(
        pDBUser, 
        pDBPassword,
        pDBURL,
        pDBSchema,
        pDBDriver,
        pDBDialect);
  }
  
  /**
   * initialize the PRP Application with parameters from outside. This enables other
   * applications to use components of the PRP framework.
   * 
   * @param pDBUser connect user
   * @param pDBPassword pwd of the connect user
   * @param pDBURL jdbc address of the database
   * @param pDBSchema db schema where the prp tables are stored
   */
  public static void initMPPManager(final String pDBUser, 
      final String pDBPassword,
      final String pDBURL,
      final String pDBSchema,
      final String pDBDriver,
      final String pDBDialect)
  {
    instance = new MPPManager(
        pDBUser, 
        pDBPassword,
        pDBURL,
        pDBSchema,
        pDBDriver,
        pDBDialect);
  }
  
  /**
   * @return Singleton instance
   */
  public static MPPManager getInstance()
  {
    if (instance == null)
    {
      instance = new MPPManager();
    }
    
    return instance;
  }
  
  /**
   * 
   * @return global instance of the {@link SessionManager}
   */
  public SessionManager getSessionManager()
  {
    return sessionManager;
  }

  /**
   * @return the applicationWindow
   */
  public JFrame getApplicationWindow()
  {
    return mApplicationWindow;
  }

  /**
   * @param pApplicationWindow the applicationWindow to set
   */
  public void setApplicationWindow(JFrame pApplicationWindow)
  {
    mApplicationWindow = pApplicationWindow;
  }

  /**
   * @return the mainPageController
   */
  public MainPageController getMainPageController()
  {
    return mMainPageController;
  }

  /**
   * @param pMainPageController the mainPageController to set
   */
  public void setMainPageController(MainPageController pMainPageController)
  {
    mMainPageController = pMainPageController;
  }
}
