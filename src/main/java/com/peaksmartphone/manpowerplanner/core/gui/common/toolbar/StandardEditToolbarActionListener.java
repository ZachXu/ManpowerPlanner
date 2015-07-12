package com.peaksmartphone.manpowerplanner.core.gui.common.toolbar;

import java.awt.event.ActionEvent;

/**
 * <p> Title: {@link StandardEditToolbarActionListener}</p>
 * 
 * <b>Description:</b> 
 * <p> Interface for using action in {@link StandardEditToolbar}. Has to be implemented by Controllers using
 *  {@link StandardEditToolbar}.
 *  </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 * 
 */
public interface StandardEditToolbarActionListener
{
  /**
   * 
   * @param pEvent Event trigger save action
   */
  void onSave(ActionEvent pEvent);
  
  /**
   * 
   * @param pEvent cancel action
   */
  void onCancel(ActionEvent pEvent);
  
  /**
   * 
   * @param pEvent start editing data
   */
  void onEdit(ActionEvent pEvent);
  
  /**
   * 
   * @param pEvent trigger delete
   */
  void onDelete(ActionEvent pEvent);

  
  /**
   * 
   * @param pEvent start creation of data
   */
  void onNew(ActionEvent pEvent);
  
  /**
   * 
   * @param pEvent start creation of data
   */
  void onCopy(ActionEvent pEvent);
  
  /**
   * 
   * @param pEvent refresh list
   */
  void onRefresh(ActionEvent pEvent);
  
  /**
   * 
   * @param pEvent start export a excel file of jtable
   */
  void onExportExcel(ActionEvent pEvent);

}
