package com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.peaksmartphone.manpowerplanner.core.gui.GUIFactory;
import com.peaksmartphone.manpowerplanner.core.gui.common.rowheadertable.HeaderTableComponent;
import com.peaksmartphone.manpowerplanner.utils.DateUtil;
import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

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
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private final WeeklyScheduleInstController mController;
  
  private final HeaderTableComponent mHeaderTableView;
  
  private final JButton mIconBtnNext = GUIFactory.createIconBtnNext();
  private final JButton mIconBtnPrevious = GUIFactory.createIconBtnPrevious();
  private final JButton mIconBtnExportExcel = GUIFactory.createIconBtnExcel();
  
  private final JLabel mLblDisplayPeriod = new JLabel();
  
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
    
    initTopPanl();
    
    initListener();
    
    mHeaderTableView = new HeaderTableComponent(pWeeklyScheduleInstTableModel, mWeeklyRowHeaderTableModel);
    
    mHeaderTableView.getStandardListTable().setDefaultEditor(Object.class, new WeeklyScheduleInstCellEditor());
    mHeaderTableView.getStandardListTable().setDefaultRenderer(Object.class, new WeeklyScheduleInstCellEditor());
    
    add(mHeaderTableView, BorderLayout.CENTER);
  }

  /**
   * 
   */
  private void initTopPanl()
  {
    JPanel topPnl = new JPanel(new GridBagLayout());
    
    topPnl.add(mIconBtnPrevious, 
        new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, 
            GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
  
    topPnl.add(mIconBtnNext, 
        new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, 
            GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
    
    topPnl.add(mIconBtnExportExcel, 
        new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, 
            GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
  
    mLblDisplayPeriod.setForeground(Color.BLUE);
    topPnl.add(mLblDisplayPeriod, 
        new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0, 
            GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, GUIFactory.COMPONENTS_INSETS, 0, 0));
  
    add(topPnl, BorderLayout.NORTH);
   }
  
  /**
   * 
   */
  private void initListener()
  {
    mIconBtnNext.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.onNext();
      }
    });
    
    mIconBtnPrevious.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.onPrevious();
      }
    });
    
    mIconBtnExportExcel.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.onExportExcel();
      }
    });
  }

  /**
   * @param pWeekDayList
   */
  public void setPeriodDisplay(List<Date> pWeekDayList)
  {
    mLblDisplayPeriod.setText(ResourceBundle.getString(
        WeeklyScheduleInstView.class.getName() + ".LBL_DISPLAYPERIOD", 
        DateUtil.SIMPLEDATEFORMAT.format(pWeekDayList.get(0)),
        DateUtil.SIMPLEDATEFORMAT.format(pWeekDayList.get(6))));
  }
  
  /**
   * 
   * @return JTable
   */
  public JTable getStandardListTable()
  {
    return mHeaderTableView.getStandardListTable();
  }
}
