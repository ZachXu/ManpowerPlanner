package com.peaksmartphone.manpowerplanner.core.gui.common;


/**
 * <p> Title: {@link ClosableTabPaneAction}</p>
 * 
 * <b>Description:</b> 
 * <p> Listener to react on Actions like:
 * <ol>
 * <li>close a certain tab</li>
 * <li>add a new tab<li>
 * </ol>
 * 
 * </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 */
public interface ClosableTabPaneAction
{
  /**
   * 
   * @param closableTabbedPane reference of the TabbedPane
   * @param tabIndex index of the tab to be closed
   */
  void onCloseTab(ClosableTabbedPane closableTabbedPane, int tabIndex);
}
