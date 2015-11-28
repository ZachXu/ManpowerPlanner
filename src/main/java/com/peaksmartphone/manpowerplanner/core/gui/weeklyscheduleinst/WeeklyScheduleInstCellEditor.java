package com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.peaksmartphone.manpowerplanner.core.MPPManager;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleDef;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.core.gui.GUIFactory;
import com.peaksmartphone.manpowerplanner.core.gui.common.MultiSelectionController;
import com.peaksmartphone.manpowerplanner.core.services.DailyScheduleInstService;
import com.peaksmartphone.manpowerplanner.core.services.EmployeeService;
import com.peaksmartphone.manpowerplanner.utils.MPPExceptionHandler;

/**
 * <p> Title: {@link WeeklyScheduleInstCellEditor} </p>
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
public class WeeklyScheduleInstCellEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer
{
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  private final Joiner mSplitJoiner = Joiner.on(",");
  
  private DailyScheduleInstService mServiceDailyScheduleInst = DailyScheduleInstService.getInstance();
   
  /**
   * @param pCellDailySchedueInst
   */
  public WeeklyScheduleInstCellEditor()
  {
    super();
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
   */
  @Override
  public Component getTableCellRendererComponent(JTable pTable, Object pValue, boolean pIsSelected, boolean pHasFocus,
      int pRow, int pColumn)
  {
    return generateEditCellRenderEditor(pTable, pRow, pColumn);
  }

  /* (non-Javadoc)
   * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
   */
  @Override
  public Component getTableCellEditorComponent(JTable pTable, Object pValue, boolean pIsSelected, int pRow, int pColumn)
  {
    return generateEditCellRenderEditor(pTable, pRow, pColumn);
  }

  /**
   * @param pValue
   * @return
   */
  protected Component generateEditCellRenderEditor(final JTable pTable, final int pRow, final int pColumn)
  {
    final List<Employee> selectedList = new ArrayList<Employee>();
    
    final WeeklyScheduleInstTableModel weeklyScheduleInstTableModel = (WeeklyScheduleInstTableModel)pTable.getModel();
    
    final Object tblvalue = weeklyScheduleInstTableModel.getValueAt(pRow, pColumn);

    final JPanel panel = new JPanel(new BorderLayout());
    final JLabel label = new JLabel();
    
    if (tblvalue instanceof DailyScheduleInst)
    {
      final DailyScheduleInst data = (DailyScheduleInst)tblvalue;
      
      if (data != null)
      {
        initialValueData(selectedList, label, data);
      }
    }
    final JButton btnEdit = GUIFactory.createIconCellEditBtn();
    
    final JFrame owner = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, pTable);
    
    btnEdit.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        Date rowDate = weeklyScheduleInstTableModel.getRowDate(pRow);
        
        DailyScheduleDef columnDef = weeklyScheduleInstTableModel.getColumnDailyScheduleDef(pColumn);
        
        DailyScheduleInst dailyscheduleinst = null;
        
        Object objValue = weeklyScheduleInstTableModel.getValueAt(pRow, pColumn);
        
        if (objValue != null)
        {
          dailyscheduleinst = (DailyScheduleInst)objValue;
        }  
        
        List<Employee> avaiableEmployeeList = EmployeeService.getInstance().getAvaiableEmployeeList(rowDate, columnDef.getId());
        
        List<Employee> rsList = MultiSelectionController.openMultiSelectionDialog(owner, 
            btnEdit.getLocation(), 
            avaiableEmployeeList, 
            selectedList);
        
        if (rsList != null && !rsList.isEmpty())
        {
          try
          {
            if (dailyscheduleinst != null && !DailyScheduleInst.EMPTY_OBJECT.equals(dailyscheduleinst))
            {
              dailyscheduleinst = dailyscheduleinst.setAssignedEmployees(new HashSet<Employee>(rsList));
            }
            else
            {
              dailyscheduleinst = DailyScheduleInst.newInstance(columnDef.getId(), 
                  rowDate, new HashSet<Employee>(rsList));
            }
            
            mServiceDailyScheduleInst.saveData(dailyscheduleinst);
            
            MPPManager.getInstance().getSessionManager().commit();
            
            weeklyScheduleInstTableModel.replaceDailyScheduleInst(rowDate, columnDef, dailyscheduleinst);
            
            weeklyScheduleInstTableModel.fireTableRowsUpdated(pRow, pRow);
            
            selectedList.clear();
            initialValueData(selectedList, label, dailyscheduleinst);
            
          }
          catch(Exception e)
          {
            MPPExceptionHandler.handleRuntimeException(e);
          }
        }
      }
    });
    
    panel.add(label, BorderLayout.CENTER);
    panel.add(btnEdit, BorderLayout.EAST);
    
    return panel;
  }

  /**
   * @param pSelectedList
   * @param pLblTblCell
   * @param pDaiyScheduleInst
   */
  protected void initialValueData(final List<Employee> pSelectedList, final JLabel pLblTblCell,
      final DailyScheduleInst pDaiyScheduleInst)
  {
    String value = "";
    
    List<String> strList = new ArrayList<String>();
    
    for (Employee assginedemployee : pDaiyScheduleInst.getAssignedEmployees())
    {
      strList.add(assginedemployee.getEmployeeName());
    }
    
    pSelectedList.addAll(pDaiyScheduleInst.getAssignedEmployees());
    
    value = mSplitJoiner.join(strList);
    pLblTblCell.setText(value);
    pLblTblCell.setToolTipText(value);
  }

  /* (non-Javadoc)
   * @see javax.swing.CellEditor#getCellEditorValue()
   */
  @Override
  public Object getCellEditorValue()
  {
    return null;
  }

}
