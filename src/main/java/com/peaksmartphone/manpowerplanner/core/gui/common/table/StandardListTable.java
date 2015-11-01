package com.peaksmartphone.manpowerplanner.core.gui.common.table;

import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

/**
 * <p> Title: {@link StandardListTable} </p>
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
public class StandardListTable extends JXTable
{

  /**
   * @param pTableModel
   */
  public StandardListTable(TableModel pTableModel)
  {
    super(pTableModel);
    
    addHighlighter(HighlighterFactory.createAlternateStriping());
  }
  
}
