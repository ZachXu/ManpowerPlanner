package com.peaksmartphone.manpowerplanner.core.gui.common;

import java.awt.Component;

import javax.swing.JTabbedPane;

/**
 * <p>
 * Title: {@link ClosableTabbedPane}
 * </p>
 * 
 * <b>Description:</b>
 * <p>
 *  TabbedPane with buttons to close the tabs
 * </p>
 * 
 * @author zach.xu1987@gmail.com
 * 
 * <p>
 * Company: PeakSmartPhone
 * </p>
 * 
 */
public class ClosableTabbedPane extends JTabbedPane
{
  private static final long serialVersionUID = 1;
  
  private ClosableTabPaneAction mCloseAddTabAction = null;

  public ClosableTabbedPane()
  {
    super();
    init();
  }

  public ClosableTabbedPane(int pArg0)
  {
    super(pArg0);
    init();
    
  }

  public ClosableTabbedPane(int pArg0, int pArg1)
  {
    super(pArg0, pArg1);
    init();
  }

  private void init()
  {
    setCloseTabAction(new ClosableTabPaneAction()
    {
      public void onCloseTab(ClosableTabbedPane pClosableTabbedPane, int pTabIndex)
      {
        pClosableTabbedPane.removeTabAt(pTabIndex);
      }
    });
  }
  
  /**
   * 
   */
  public void addTab(String pTitle, Component pComponent)
  {
    addTab(pTitle, pComponent, mCloseAddTabAction);
  }
  
  /* (non-Javadoc)
   * @see javax.swing.JTabbedPane#addTab(java.lang.String, java.awt.Component)
   */
  public void addTab(String pTitle, Component pComponent, ClosableTabPaneAction pListener)
  {
    int insertPos = getTabCount();
    super.insertTab(pTitle, null, pComponent, pTitle, insertPos);
    
    this.setTabComponentAt(insertPos, new ButtonTabComponent(this, pListener));
    
    this.setSelectedIndex(insertPos);
  }
  
  /**
   * 
   * @param pTitle
   * @param pComponent
   */
  public void insertTab(String pTitle, Component pComponent, ClosableTabPaneAction pListener, int pInsertPos)
  {
    if (pInsertPos > getTabCount())
    {
      pInsertPos = getTabCount() - 1;
    }
    
    super.insertTab(pTitle, null, pComponent, pTitle, pInsertPos);
    
    this.setTabComponentAt(pInsertPos, new ButtonTabComponent(this, pListener));
  }
  
  /**
   * 
   * @return
   */
  public ClosableTabPaneAction getCloseTabAction()
  {
    return mCloseAddTabAction;
  }

  /**
   * 
   * @param pAction
   */
  public void setCloseTabAction(ClosableTabPaneAction pAction)
  {
    mCloseAddTabAction = pAction;
  }
  
  /**
   * remove all calendar tabs.
   */
  public void clearAllTabs()
  {
    while (getTabCount() > 0)
    {
      removeTabAt(0);
    }
  }
}
