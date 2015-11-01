package com.peaksmartphone.manpowerplanner.core.gui.common.rowheadertable;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;

/**
 * <p> Title: {@link RowHeaderTable} </p>
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
class RowHeaderTable extends JXTable
{

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * @param pTableModel
   */
  public RowHeaderTable(TableModel pTableModel)
  {
    super(pTableModel);
    
    setEnabled(false);
    setBackground((Color) UIManager.get("TableHeader.background"));
  }
  
}
