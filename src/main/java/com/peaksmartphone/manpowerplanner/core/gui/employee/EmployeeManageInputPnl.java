package com.peaksmartphone.manpowerplanner.core.gui.employee;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.peaksmartphone.manpowerplanner.core.gui.GUIFactory;
import com.peaksmartphone.manpowerplanner.core.gui.common.AbstractInputPanel;
import com.peaksmartphone.manpowerplanner.core.gui.common.fields.NumberField;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;
import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

/**
 * <p> Title: {@link EmployeeManageInputPnl} </p>
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
public class EmployeeManageInputPnl extends AbstractInputPanel
{
  private final JLabel mLblEmployeeName = new JLabel(
      ResourceBundle.getString(EmployeeManageTableModel.class.getName() + ".CLN_NAME"));
  private final JTextField mTxtEmployeeName = GUIFactory.createTextfieldStandard(50);
  private final JLabel mLblMaxInOccur = new JLabel(
      ResourceBundle.getString(EmployeeManageTableModel.class.getName() + ".CLN_MAXOCCUR"));
  private final NumberField mNumMaxInOccur = GUIFactory.createNumberField(3);
  
  /**
   * 
   */
  public EmployeeManageInputPnl()
  {
    super();
    init();
  }

  /**
   * 
   */
  protected void init()
  {
    add(mLblEmployeeName, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
    add(mTxtEmployeeName, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, GUIFactory.COMPONENTS_INSETS, 0, 0));
    
    add(mLblMaxInOccur, new GridBagConstraints(0, 1, 1, 1, 0.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.NONE, GUIFactory.COMPONENTS_INSETS, 0, 0));
    add(mNumMaxInOccur, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, 
        GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, GUIFactory.COMPONENTS_INSETS, 0, 0));
  }

  /**
   * @return the txtEmployeeName
   */
  public JTextField getTxtEmployeeName()
  {
    return mTxtEmployeeName;
  }

  /**
   * @return the numMaxInOccur
   */
  public NumberField getNumMaxInOccur()
  {
    return mNumMaxInOccur;
  }

  /**
   * 
   */
  protected void enableAll(boolean pEnabled)
  {
    mTxtEmployeeName.setEnabled(pEnabled);
    mNumMaxInOccur.setEnabled(pEnabled);
  }
  
}
