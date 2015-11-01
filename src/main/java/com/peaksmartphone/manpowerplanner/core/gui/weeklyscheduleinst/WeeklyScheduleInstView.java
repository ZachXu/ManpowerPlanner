package com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.gui.common.rowheadertable.HeaderTableComponent;
import com.peaksmartphone.manpowerplanner.core.services.DailyScheduleDefService;

/**
 * <p> Title: {@link WeeklyScheduleInstView} </p>
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
public class WeeklyScheduleInstView extends JPanel
{
  private final WeeklyScheduleInstController mController;
  
  private final HeaderTableComponent mHeaderTableView;
  
  /**
   * @param pController
   * @param pDailyScheduleDefCellEditor 
   */
  public WeeklyScheduleInstView(WeeklyScheduleInstController pController, 
      WeeklyScheduleInstTableModel pWeeklyScheduleInstTableModel, 
      WeeklyRowHeaderTableModel mWeeklyRowHeaderTableModel)
  {
    super(new BorderLayout());
    mController = pController;
    mHeaderTableView = new HeaderTableComponent(pWeeklyScheduleInstTableModel, mWeeklyRowHeaderTableModel);
    
    mHeaderTableView.getStandardListTable().setDefaultEditor(Object.class, new WeeklyScheduleInstCellEditor());
    mHeaderTableView.getStandardListTable().setDefaultRenderer(Object.class, new WeeklyScheduleInstCellEditor());
    
    add(mHeaderTableView, BorderLayout.CENTER);
  }
  
}
