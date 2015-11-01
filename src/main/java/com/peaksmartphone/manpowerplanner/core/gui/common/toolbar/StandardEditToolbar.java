package com.peaksmartphone.manpowerplanner.core.gui.common.toolbar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.peaksmartphone.manpowerplanner.core.gui.GUIFactory;

/**
 * <p> Title: {@link StandardEditToolbar}</p>
 * 
 * <b>Description:</b> 
 * <p> Standard Toolbar for editing data</p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * 
 */
public class StandardEditToolbar extends JPanel
{
  private static final long serialVersionUID = 1;
  protected final StandardEditToolbarActionListener mController;
  private final JButton mIconBtnSave =  GUIFactory.createIconBtnSave();
  private final JButton mIconBtnCancel = GUIFactory.createIconButtonCancel();
  private final JButton mIconBtnNew = GUIFactory.createIconBtnAdd();
  private final JButton mIconBtnDelete = GUIFactory.createIconBtnDelete();
  private final JButton mIconBtnEdit = GUIFactory.createIconBtnEdit();
  private final JButton mIconBtnRefresh = GUIFactory.createIconBtnRefresh();
  private final JButton mIconBtnExport = GUIFactory.createIconBtnExcel();
  
  /**
   * 
   * @param pController class, reacting on the actions in the toolbar.
   */
  public StandardEditToolbar(StandardEditToolbarActionListener pController)
  {
    mController = pController;
    init();
  }
  
  /**
   */
  private void init()
  {
    final FlowLayout flowLayout = new FlowLayout();
    this.setLayout(flowLayout);
    flowLayout.setAlignment(FlowLayout.LEFT);
    
    mIconBtnSave.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        mController.onSave(pE);        
      }
    });
    add(mIconBtnSave);
    
    mIconBtnCancel.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        mController.onCancel(pE);        
      }
    });
    add(mIconBtnCancel);
    addSeparator();
    
    mIconBtnNew.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        mController.onNew(pE);        
      }
    });
    add(mIconBtnNew);
    
    mIconBtnDelete.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        mController.onDelete(pE);        
      }
    });
    add(mIconBtnDelete);
    
    mIconBtnEdit.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        mController.onEdit(pE);        
      }
    });
    add(mIconBtnEdit);
    
    mIconBtnRefresh.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        mController.onRefresh(pE);        
      }
    });
    add(mIconBtnRefresh);
    addSeparator();
    
    mIconBtnExport.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        mController.onExportExcel(pE);
      }
    });
    

    add(mIconBtnExport);
    
  }
  
  /**
   * adds a separation space between the last added and next added component
   */
  public void addSeparator()
  {
    add(Box.createHorizontalStrut(10));
  }
    
  /**
   * Aktiviert die Buttons abhaengig vom Maskenstatus
   * @param pMaskenStatus Bearbeitungsstatus der Hauptmaske, von welcher die Toolbar ein Teil ist.
   * @param pFilterStatus Angabe, nach welchen Kriterien gefiltert wird.
   */
  public void setMaskStatus(MaskStatus pMaskenStatus)
  {
    if (MaskStatus.NEW.equals(pMaskenStatus) || MaskStatus.EDIT.equals(pMaskenStatus))
    {
      mIconBtnSave.setEnabled(true);
      mIconBtnCancel.setEnabled(true);
      mIconBtnNew.setEnabled(false);
      mIconBtnDelete.setEnabled(false);
      mIconBtnEdit.setEnabled(false);
      mIconBtnRefresh.setEnabled(false);
      mIconBtnExport.setEnabled(false);
      
    }
    else if (MaskStatus.SELECTED.equals(pMaskenStatus))
    {
      mIconBtnSave.setEnabled(false);
      mIconBtnCancel.setEnabled(false);
      mIconBtnNew.setEnabled(true);
      mIconBtnDelete.setEnabled(true);
      mIconBtnEdit.setEnabled(true);
      mIconBtnRefresh.setEnabled(true);
      mIconBtnExport.setEnabled(true);
      
    }
    else if (MaskStatus.NOT_SELECTED.equals(pMaskenStatus))
    {
      mIconBtnSave.setEnabled(false);
      mIconBtnCancel.setEnabled(false);
      mIconBtnNew.setEnabled(true);
      mIconBtnDelete.setEnabled(false);
      mIconBtnEdit.setEnabled(false);
      mIconBtnRefresh.setEnabled(true);
      mIconBtnExport.setEnabled(true);
    }
  }
    
}
