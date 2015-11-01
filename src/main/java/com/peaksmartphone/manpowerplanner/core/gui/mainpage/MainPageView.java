package com.peaksmartphone.manpowerplanner.core.gui.mainpage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.peaksmartphone.manpowerplanner.core.gui.common.ClosableTabbedPane;
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
  
  private final JMenuBar mMainMenubar = new JMenuBar();
  
  private final JMenu mMenuWorkCalendar = new JMenu();
  private final JMenuItem mMenuItemWorkCalendar = new JMenuItem();
  
  private final JMenu mMenuAdmin = new JMenu();
  private final JMenuItem mMenuItemEmpManage = new JMenuItem();
  private final JMenuItem mMenuItemDSDManage = new JMenuItem();
  private final JMenuItem mMenuItem_EmployeePreference = new JMenuItem();
  
  private final JMenu mMenu_Language = new JMenu();
   
  private final JMenuItem mMenuitem_lang_CHINESE = new JMenuItem();
  private final JMenuItem mMenuitem_lang_ENGLISH = new JMenuItem();
  private final JMenuItem mMenuitem_lang_DEFAULT = new JMenuItem();
  
  private final ClosableTabbedPane mTabbedPane = new ClosableTabbedPane();
  
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
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    setLayout(new BorderLayout());
    
    initMenu();
    
    add(mTabbedPane, BorderLayout.CENTER);
  }
  
  /**
   * 
   */
  private void initMenu()
  {
    mMenuItemWorkCalendar.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.openWorkCalendar();
      }
    });
    
    mMenuWorkCalendar.add(mMenuItemWorkCalendar);
    
    mMainMenubar.add(mMenuWorkCalendar);
    
    
    mMenuItemEmpManage.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.openEmployeeManagement();
      }
    });
    
    mMenuAdmin.add(mMenuItemEmpManage);
    
    
    mMenuItemDSDManage.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.openDailyScheduleDefManagement();
      }
    });
    
    mMenuAdmin.add(mMenuItemDSDManage);
    
    mMenuAdmin.addSeparator();
    
    mMenuItem_EmployeePreference.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.openEmployeePreferenceManage();
      }
    });
    
    mMenuAdmin.add(mMenuItem_EmployeePreference);
    
    mMenuAdmin.addSeparator();

    mMenuitem_lang_CHINESE.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.openLanguageChoice(Locale.CHINESE);
      }
    });
    
    mMenu_Language.add(mMenuitem_lang_CHINESE);
    
    mMenuitem_lang_ENGLISH.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.openLanguageChoice(Locale.ENGLISH);
      }
    });
    
    mMenu_Language.add(mMenuitem_lang_ENGLISH);

    mMenuitem_lang_DEFAULT.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.openLanguageChoice(Locale.ROOT);
      }
    });
    
    mMenu_Language.add(mMenuitem_lang_DEFAULT);
    
    mMenuAdmin.add(mMenu_Language);
    
    mMainMenubar.add(mMenuAdmin);
    
    add(mMainMenubar, BorderLayout.NORTH);
    
    updateMenuText();
  }

  /**
   * 
   */
  public void updateMenuText()
  {
    mMenuWorkCalendar.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENU_WORKCALENDAR"));
    mMenuItemWorkCalendar.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENUITEM_WORKCALENDAR"));
    
    mMenuAdmin.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENU_ADMINISTRATOR"));
    mMenuItemEmpManage.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENUITEM_EMPLOYMANAGE"));
    mMenuItemDSDManage.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENUITEM_DSDMANAGE"));
    mMenuItem_EmployeePreference.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENUITEM_EMPLOYPREF"));
    
    mMenu_Language.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENU_LANGUAGE"));
    mMenuitem_lang_CHINESE.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENUITEM_LANG_CHINESE"));
    mMenuitem_lang_ENGLISH.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENUITEM_LANG_ENGLISH"));
    mMenuitem_lang_DEFAULT.setText(ResourceBundle.getString(MainPageView.class.getName() + ".MENUITEM_LANG_DEFAULT"));
  }

  /**
   * @return the tabbedPane
   */
  public ClosableTabbedPane getTabbedPane()
  {
    return mTabbedPane;
  }
  
  
}
