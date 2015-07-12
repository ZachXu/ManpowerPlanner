package com.peaksmartphone.manpowerplanner.core.gui.dailyschedule;

import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.peaksmartphone.manpowerplanner.core.gui.GUIFactory;
import com.peaksmartphone.manpowerplanner.core.gui.common.AbstractInputPanel;
import com.peaksmartphone.manpowerplanner.core.gui.common.fields.NumberField;
import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

/**
 * <p> Title: {@link DailyScheduleDefManageInputPnl} </p>
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
public class DailyScheduleDefManageInputPnl extends AbstractInputPanel
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private final JLabel mLblDailScheduleName = new JLabel(
      ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_NAME"));
  private final JTextField mTxtDailScheduleName = GUIFactory.createTextfieldStandard(50);
  private final JLabel mLblIsRestDay = new JLabel(
      ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_RESTDAY"));
  private final JCheckBox mChxIsRestDay = GUIFactory.createCheckBox();
  private final JLabel mLblMaxEmployeeAmount = new JLabel(
      ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_MAXEMPLOYEEAMOUNT"));
  private final NumberField mNumMaxEmployeeAmount = GUIFactory.createNumberField(3);
  private final JLabel mLblSortIndex = new JLabel(
      ResourceBundle.getString(DailyScheduleDefManageTableModel.class.getName() + ".CLN_SORTINDEX"));
  private final NumberField mNumSortIndex = GUIFactory.createNumberField(3);
  
  /**
   * 
   */
  public DailyScheduleDefManageInputPnl()
  {
    super();
    init();
  }

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.AbstractInputPanel#init()
   */
  @Override
  protected void init()
  {
    int count = 0;
    add(mLblDailScheduleName, new GridBagConstraints(0, count, 1, 1, 0.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
    add(mTxtDailScheduleName, new GridBagConstraints(1, count, 1, 1, 1.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, GUIFactory.COMPONENTS_INSETS, 0, 0));
    
    count++;
    
    add(mLblIsRestDay, new GridBagConstraints(0, count, 1, 1, 0.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
    add(mChxIsRestDay, new GridBagConstraints(1, count, 1, 1, 1.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, GUIFactory.COMPONENTS_INSETS, 0, 0));
    
    count++;
    
    add(mLblMaxEmployeeAmount, new GridBagConstraints(0, count, 1, 1, 0.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
    add(mNumMaxEmployeeAmount, new GridBagConstraints(1, count, 1, 1, 1.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, GUIFactory.COMPONENTS_INSETS, 0, 0));
    
    count++;
    
    add(mLblSortIndex, new GridBagConstraints(0, count, 1, 1, 0.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
    add(mNumSortIndex, new GridBagConstraints(1, count, 1, 1, 1.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, GUIFactory.COMPONENTS_INSETS, 0, 0));
  }
  
  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.core.gui.common.AbstractInputPanel#enableAll(boolean)
   */
  @Override
  protected void enableAll(boolean pEnabled)
  {
    mTxtDailScheduleName.setEnabled(pEnabled);
    mChxIsRestDay.setEnabled(pEnabled);
    mNumMaxEmployeeAmount.setEnabled(pEnabled);
    mNumSortIndex.setEnabled(pEnabled);
  }

  /**
   * @return the txtDailScheduleName
   */
  public JTextField getTxtDailScheduleName()
  {
    return mTxtDailScheduleName;
  }

  /**
   * @return the chxIsRestDay
   */
  public JCheckBox getChxIsRestDay()
  {
    return mChxIsRestDay;
  }

  /**
   * @return the numMaxEmployeeAmount
   */
  public NumberField getNumMaxEmployeeAmount()
  {
    return mNumMaxEmployeeAmount;
  }

  /**
   * @return the numSortIndex
   */
  public NumberField getNumSortIndex()
  {
    return mNumSortIndex;
  }
}
