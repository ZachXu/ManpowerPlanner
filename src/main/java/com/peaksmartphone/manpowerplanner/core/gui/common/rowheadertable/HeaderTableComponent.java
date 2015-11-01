package com.peaksmartphone.manpowerplanner.core.gui.common.rowheadertable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.peaksmartphone.manpowerplanner.core.gui.common.table.StandardListTable;

/**
 * <p> Title: {@link HeaderTableComponent} </p>
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
public class HeaderTableComponent extends JPanel
{
  private static class RowHeaderCellRender extends JLabel implements TableCellRenderer
  {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
     */
    /**
     * 
     */
    public RowHeaderCellRender()
    {
      super();
      setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable pTable, Object pValue, boolean pIsSelected,
        boolean pHasFocus, int pRow, int pColumn)
    {
      setForeground(pTable.getForeground());
      setBackground(pTable.getBackground());
      
      setToolTipText(String.valueOf(pValue));
      
      String value = String.format("<html>%s</html>", (pValue == null) ? "" : pValue.toString());
      
      setText(value);
      
      setVerticalAlignment(SwingConstants.CENTER);
      
      return this;
    }
    
  }
  
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private final StandardListTable mTable;
  private final RowHeaderTable mRowHeaderTable;
  private final JScrollPane mScrollPane;
  
  /**
   * 
   */
  public HeaderTableComponent(TableModel pTableModel, TableModel pRowHeaderTableModel)
  {
    super(new BorderLayout());
    
    mTable = new StandardListTable(pTableModel);
    
    mRowHeaderTable = new RowHeaderTable(pRowHeaderTableModel);
    
    mRowHeaderTable.setDefaultRenderer(Object.class, new RowHeaderCellRender());
    
    mScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    mScrollPane.setColumnHeaderView(mTable.getTableHeader());
    mScrollPane.setViewportView(mTable);
    
    mScrollPane.setRowHeaderView(mRowHeaderTable);
    
    addComponentListener(new ComponentAdapter()
    {

      /* (non-Javadoc)
       * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.ComponentEvent)
       */
      @Override
      public void componentResized(ComponentEvent pE)
      {
        updateRowHeight(pE.getComponent().getHeight());
      }
    });
    
    add(mScrollPane, BorderLayout.CENTER);
  }

  /**
   * @param pHeight
   */
  protected void updateRowHeight(int pHeight)
  {
    int row = mTable.getRowCount();
    
    int rowheight = (int)((pHeight - mTable.getTableHeader().getHeight()) / row);
    
    if (rowheight > 0)
    {
      mTable.setRowHeight(rowheight);
      mRowHeaderTable.setRowHeight(rowheight);
    }
  }
  
  /**
   * @return the table
   */
  public StandardListTable getStandardListTable()
  {
    return mTable;
  }

  /**
   * 
   * @param args test
   */
  public static void main(String[] args)
  {
    TableModel tm = new AbstractTableModel()
    {
      private String[] columnNames = new String[]{
        "D", "A", "N"
      };
      
      /* (non-Javadoc)
       * @see javax.swing.table.AbstractTableModel#getColumnName(int)
       */
      @Override
      public String getColumnName(int pColumn)
      {
        return columnNames[pColumn];
      }
      
      @Override
      public int getColumnCount()
      {
        return columnNames.length;
      }

      @Override
      public Object getValueAt(int pRowIndex, int pColumnIndex)
      {
        return new Random().nextInt(2);
      }
      
      @Override
      public int getRowCount()
      {
        return 5;
      }
    };
    
    TableModel rowTM = new AbstractTableModel()
    {
      @Override
      public int getColumnCount()
      {
        return 1;
      }

      @Override
      public Object getValueAt(int pRowIndex, int pColumnIndex)
      {
        return pRowIndex;
      }
      
      @Override
      public int getRowCount()
      {
        return 5;
      }
    };
    
    
    final HeaderTableComponent tablecomponent = new HeaderTableComponent
        (tm, rowTM);
    
    final JFrame f = new JFrame("有行头的表格2");
    f.setContentPane(tablecomponent);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    f.pack();
    SwingUtilities.invokeLater(new Runnable()
    {

      @Override
      public void run() {
        f.setVisible(true);
      }
      
    });
  }
  
}
