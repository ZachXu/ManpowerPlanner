package com.peaksmartphone.manpowerplanner.core.gui;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.peaksmartphone.manpowerplanner.core.gui.common.StandardManageView;
import com.peaksmartphone.manpowerplanner.core.gui.common.fields.NumberField;
import com.peaksmartphone.manpowerplanner.core.gui.common.fields.TextDocumentLimit;
import com.peaksmartphone.manpowerplanner.core.gui.common.toolbar.MaskStatus;

/**
 * <p> Title: {@link GUIFactory} </p>
 * 
 * <b>Description:</b> 
 * <p> General Factory for common methods </p>
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
public class GUIFactory
{
  public static final Dimension STANDARDSIZE_ICONBUTTON = new Dimension(35, 35);
  
  public static final Dimension SMALLSIZE_ICONBUTTON = new Dimension(20, 20);
  
  public static final Insets COMPONENTS_INSETS = new Insets(5, 5, 5, 5);
  
  /**
   * 
   * @return save
   */
  public static JButton createIconBtnSave()
  {
    return createIconButton(
        new ImageIcon(
            GUIFactory.class.getResource(
                "/com/peaksmartphone/manpowerplanner/core/gui/images/Button_Save.png")));
  }
  
  /**
   * 
   * @return cancel
   */
  public static JButton createIconBtnCancel()
  {
    return createIconButton(
        new ImageIcon(
            GUIFactory.class.getResource(
                "/com/peaksmartphone/manpowerplanner/core/gui/images/Button_Cancel.png")));
  }
  
  /**
   * 
   * @return edit
   */
  public static JButton createIconBtnAdd()
  {
    return createIconButton(
        new ImageIcon(
            GUIFactory.class.getResource(
                "/com/peaksmartphone/manpowerplanner/core/gui/images/Button_Add.png")));
  }
  
  /**
   * 
   * @return edit
   */
  public static JButton createIconBtnEdit()
  {
    return createIconButton(
        new ImageIcon(
            GUIFactory.class.getResource(
                "/com/peaksmartphone/manpowerplanner/core/gui/images/Button_Edit.png")));
  }
  
  /**
   * 
   * @return delete button
   */
  public static JButton createIconBtnDelete()
  {
    return createIconButton(
        new ImageIcon(
            GUIFactory.class.getResource(
                "/com/peaksmartphone/manpowerplanner/core/gui/images/Button_Delete.png")));
  }
  
  /**
   * 
   * @return refresh button
   */
  public static JButton createIconBtnRefresh()
  {
    return createIconButton(
        new ImageIcon(
            GUIFactory.class.getResource(
                "/com/peaksmartphone/manpowerplanner/core/gui/images/Button_Refresh.png")));
  }
  
  /**
   * 
   * @return refresh button
   */
  public static JButton createIconBtnExcel()
  {
    return createIconButton(
        new ImageIcon(
            GUIFactory.class.getResource(
                "/com/peaksmartphone/manpowerplanner/core/gui/images/Button_Excel.png")));
  }
  
  public static JButton createIconButton(ImageIcon pImageIcon)
  {
    return createIconButton(pImageIcon, true);
  }
  
  /**
   * 
   * @param pIsStandardSize if it's true then set the button with standard size otherwise set the button with the small size
   * @return Button with icon and tooltip
   */
  public static JButton createIconButton(ImageIcon pImageIcon, boolean pIsStandardSize)
  {
    final JButton returnVal = new JButton(pImageIcon);
    
    if (pIsStandardSize)
    {
      returnVal.setPreferredSize(STANDARDSIZE_ICONBUTTON);
    }
    else 
    {
      returnVal.setPreferredSize(SMALLSIZE_ICONBUTTON);
    }

    return returnVal;
  }
  
  /**
   * 
   * @param pMaxTextLength
   * @param pMandatory
   * @return
   */
  public static JTextField createTextfieldStandard(int pMaxTextLength)
  {
    final JTextField returnVal = new JTextField();
    returnVal.setDocument(new TextDocumentLimit(pMaxTextLength));

    return returnVal;
  }
  
  /**
   * 
   * @param pMaxTextLength
   * @param pMandatory
   * @return
   */
  public static NumberField createNumberField(int pMaxTextLength)
  {
    final NumberField returnVal = new NumberField(pMaxTextLength);
    return returnVal;
  }

  /**
   * @return
   */
  public static JCheckBox createCheckBox()
  {
    return new JCheckBox();
  }
}
