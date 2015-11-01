package com.peaksmartphone.manpowerplanner.core.gui.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.peaksmartphone.manpowerplanner.core.gui.GUIFactory;
import com.peaksmartphone.manpowerplanner.utils.ResourceBundle;

/**
 * <p> Title: {@link MultiSelectionController}</p>
 * 
 * <b>Description:</b> 
 * <p> 
 *   The component consists of two JLists, the left
 *   one for available items and right one for the selected values. The
 *   user can move items between the two lists by clicking the buttons
 *   placed between the lists.
 * </p>
 *
 * @author zach.xu1987@gmail.com
 *
 * $Rev: $:     Revision of last commit<br/>
 * $Author: $:  Author of last commit<br/>
 * $Date: $:    Date of last commit
 * 
 */
public class MultiSelectionController <T extends Object>
{
  private final JPanel mView = new JPanel();
    
  private final JButton mBtnAddSelected = GUIFactory.createIconStandardButton(
      new ImageIcon(MultiSelectionController.class.getResource("/com/peaksmartphone/manpowerplanner/core/gui/images/arrow_right.png")));
  
  private final JButton mBtnAddAll = GUIFactory.createIconStandardButton(
      new ImageIcon(MultiSelectionController.class.getResource("/com/peaksmartphone/manpowerplanner/core/gui/images/arrow_allright.png")));
  
  private final JButton mBtnRemoveSelected = GUIFactory.createIconStandardButton(
      new ImageIcon(MultiSelectionController.class.getResource("/com/peaksmartphone/manpowerplanner/core/gui/images/arrow_left.png")));
  
  private final JButton mBtnRemoveAll = GUIFactory.createIconStandardButton(
      new ImageIcon(MultiSelectionController.class.getResource("/com/peaksmartphone/manpowerplanner/core/gui/images/arrow_allleft.png")));


  private DefaultComboBoxModel<T> mListModelDestination = new DefaultComboBoxModel<T>();
  private DefaultComboBoxModel<T> mListModelSource = new DefaultComboBoxModel<T>();

  private final JList<T> mListSource = new JList<T>();
  private final JList<T> mListDestination = new JList<T>();

  private final List<T> mAvailableItems = new ArrayList<T>();
  
  /**
   * Standard Constructor
   */
  public MultiSelectionController()
  {
    this(Collections.<T>emptyList(), Collections.<T>emptyList());
  }
  
  /**
   * 
   * @param pAvailableItems List with available items
   * @param pSelectedItems List with selected items
   */
  public MultiSelectionController(Collection<T> pAvailableItems, Collection<T> pSelectedItems)
  {
    super();
    
    mListDestination.setModel(mListModelDestination);
    mListSource.setModel(mListModelSource);

    setAvailableItems(pAvailableItems);
    setSelectedItems(pSelectedItems);
    init();
  }
  
  private void init()
  {
    final Dimension btnSize = new Dimension(20, 20);
    mBtnAddSelected.setPreferredSize(btnSize);
    mBtnAddAll.setPreferredSize(btnSize);
    mBtnRemoveSelected.setPreferredSize(btnSize);
    mBtnRemoveAll.setPreferredSize(btnSize);
    
    final Insets btnInsets = new Insets(1, 5, 1, 5);
    
    mView.setLayout(new GridBagLayout());
    
    final JLabel lableSource = new JLabel(
        ResourceBundle.getString(MultiSelectionController.class.getName() + ".LBL_SOURCELIST"));
    lableSource.setHorizontalAlignment(SwingConstants.LEFT);
    mView.add(lableSource,        
        new GridBagConstraints(0, 0, 1, 1, 0.5, 0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    
    final JLabel lableDestination = new JLabel(
        ResourceBundle.getString(MultiSelectionController.class.getName() + ".LBL_DESTINATIONLIST"));
    lableDestination.setHorizontalAlignment(SwingConstants.LEFT);
    mView.add(lableDestination,        
        new GridBagConstraints(2, 0, 1, 1, 0.5, 0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    
    JScrollPane scrListSource = new JScrollPane(mListSource);
    mView.add(scrListSource, new GridBagConstraints(0, 1, 1, 4, 0.5, 1.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    
    JScrollPane scrListDestination = new JScrollPane(mListDestination);
    mView.add(scrListDestination, new GridBagConstraints(2, 1, 1, 4, 0.5, 1.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
        new Insets(0, 0, 0, 0), 0, 0));
    
    mView.add(mBtnAddAll, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
        btnInsets, 0, 0));
    mView.add(mBtnAddSelected, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
        btnInsets, 0, 0));
    mView.add(mBtnRemoveSelected, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
        btnInsets, 0, 0));
    mView.add(mBtnRemoveAll, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
        GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
        btnInsets, 0, 0));
    
    mBtnAddSelected.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        for (T item : mListSource.getSelectedValuesList())
        {
          selectItem((T) item);
        }
      }
    });
    
    mBtnAddAll.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        setSelectedItems(mAvailableItems);
      }
    });
    
    mBtnRemoveSelected.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        for (T item : mListDestination.getSelectedValuesList())
        {
          deselectItem((T)item);
        }  
      }
    });
    
    mBtnRemoveAll.addActionListener(new ActionListener()
    {
      /* (non-Javadoc)
       * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
       */
      public void actionPerformed(ActionEvent pE)
      {
        setSelectedItems(Collections.<T>emptyList());
      }
    });
  }

  /**
   * @return the view
   */
  public JPanel getView()
  {
    return mView;
  }

  /**
   * 
   * @param pSourceList 
   */
  public void setAvailableItems(Collection<T> pAvailableItems)
  {
    mAvailableItems.clear();
    mAvailableItems.addAll(pAvailableItems);

    mListModelSource.removeAllElements();
    mListModelDestination.removeAllElements();
    
    for (T item : mAvailableItems)
    {
      mListModelSource.addElement(item);
    }
  }
  
  /**
   * @return all available items
   */
  public Collection<T> getAvailableItems()
  {
    return Collections.unmodifiableCollection(mAvailableItems);
  }
  
  /**
   * @param pDestinationList 
   */
  public void setSelectedItems(Collection<T> pDestinationList)
  {
    for (T item : mAvailableItems)
    {
      if (pDestinationList.contains(item))
      {
        selectItem(item);
      }
      else
      {
        deselectItem(item);
      }
    }
  }
  
  /**
   * 
   * @param pSelectedItems 
   */
  public void setSelectedItems(List<T> pSelectedItems, boolean pAddMissing)
  {
    final List<T> toAdd = new ArrayList<T>(mAvailableItems);
    
    if(!toAdd.containsAll(pSelectedItems))
    {
      if (toAdd.addAll(pSelectedItems))
      {
        setAvailableItems(toAdd);
      }
    }
      
    setSelectedItems(pSelectedItems);
  }
  
  /**
   * 
   * @return all selected items
   */
  public List<T> getSelectedItems()
  { 
    final List<T> list = new ArrayList<T>();
    
    for (int i = 0; i < mListModelDestination.getSize(); i++)
    {
      list.add((T) mListModelDestination.getElementAt(i));
    }
    
    return list;
  }

  /**
   * @param pItem item to select
   */
  public void selectItem(T pItem)
  {
    if (mListModelSource.getIndexOf(pItem) > -1)
    {
      mListModelSource.removeElement(pItem);
      addElementInOrder(mListModelDestination, pItem);
    }
  }
  
  /**
   * @param pItem item to deselect
   */
  public void deselectItem(T pItem)
  {
    if (mListModelDestination.getIndexOf(pItem) > -1)
    {
      mListModelDestination.removeElement(pItem);
      addElementInOrder(mListModelSource, pItem);
    }
  }
  
  /**
   * @author Zach.Xu
   * @param pDefaultComboBoxModel
   * @param pItem
   */
  private void addElementInOrder(DefaultComboBoxModel<T> pDefaultComboBoxModel, T pItem)
  {
    int insertpos = -1;
    for(int i = 0;i<pDefaultComboBoxModel.getSize();i++)
    {
      if(mAvailableItems.indexOf(pDefaultComboBoxModel.getElementAt(i))>mAvailableItems.indexOf(pItem))
      {
        insertpos=i;break;
      }
    }
    if(insertpos!=-1)
    {
      pDefaultComboBoxModel.insertElementAt(pItem, insertpos);
    }
    else
    {
      pDefaultComboBoxModel.addElement(pItem);
    }
  }
  
  /**
   * @param pEnabled sets the GUI enabled/disabled
   */  
  public void setEnabled(boolean pEnabled)
  {
    mView.setEnabled(pEnabled);
    mListDestination.setEnabled(pEnabled);
    mListSource.setEnabled(pEnabled);
    mBtnAddAll.setEnabled(pEnabled);
    mBtnAddSelected.setEnabled(pEnabled);
    mBtnRemoveAll.setEnabled(pEnabled);
    mBtnRemoveSelected.setEnabled(pEnabled);
  }
  
  /**
   * @param pListCellRenderer renderer used to display the items in the two lists
   */
  public void setRenderer(ListCellRenderer<T> pListCellRenderer)
  {
    mListSource.setCellRenderer(pListCellRenderer);
    mListDestination.setCellRenderer(pListCellRenderer);
  }
  
  /**
   * Wraps the method of the two JList
   * @param pHeight
   */
  public void setFixedCellHeight(int pHeight)
  {
    mListSource.setFixedCellHeight(pHeight);
    mListDestination.setFixedCellHeight(pHeight);
  }

  /**
   * Wraps the method of the two JList
   * @param pWidth
   */
  public void setFixedCellWidth(int pWidth)
  {
    mListSource.setFixedCellWidth(pWidth);
    mListDestination.setFixedCellWidth(pWidth);   
  }
  
  /**
   * Wraps the method of the two JList
   * @param pVisibleRowCount
   */
  public void setVisibleRowCount(int pVisibleRowCount)
  {
    mListSource.setVisibleRowCount(pVisibleRowCount);
    mListDestination.setVisibleRowCount(pVisibleRowCount);     
  }
  
  /**
   * 
   * @return {@link JList} of source for calculating
   */
  public JList<T> getSourceList()
  {
    return mListSource;
  }
  
  
  public static <T> List<T> openMultiSelectionDialog(JFrame pOwner, Point pPoint, 
      List<T> pAvaiableList,
      List<T> pSelectedList)
  {
    final List<T> selectedList = new ArrayList<T>();
    
    final MultiSelectionController<T> multiSelController = 
        new MultiSelectionController<T>(pAvaiableList, pSelectedList);
    
    final JDialog dialog = new JDialog(pOwner);
    dialog.setModal(true);
    dialog.setLocation(pPoint);
    
    JPanel contentPanel = new JPanel(new BorderLayout());
    
    JButton btnOk = GUIFactory.createIconBtnOk();
    
    JButton btnCancel = GUIFactory.createIconBtnCancel();
    
    JPanel btnPanel = new JPanel(new FlowLayout());
    btnPanel.add(btnOk);
    btnPanel.add(btnCancel);
    
    contentPanel.add(btnPanel, BorderLayout.SOUTH);
    contentPanel.add(multiSelController.getView(), BorderLayout.CENTER);
    
    btnOk.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        selectedList.addAll(multiSelController.getSelectedItems());
        dialog.setVisible(false);
      }
    });
    
    btnCancel.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        dialog.setVisible(false);
      }
    });
    
    dialog.setContentPane(contentPanel);
    dialog.pack();
    dialog.setVisible(true);
    
    return selectedList;

  }
  
  public static void main(String[] args)
  {
    final Joiner join = Joiner.on(",");
    
    final List<String> l = Arrays.asList("eins", "zwei", "drei", "vier", "fuenf", "sechs", "sieben");
    
    final JFrame frame = new JFrame();
    
    JPanel contentPanel = new JPanel(new BorderLayout());
    
    final JButton btn = new JButton("Start");
    
    contentPanel.add(btn, BorderLayout.SOUTH);
    
    frame.setContentPane(contentPanel);
    
    btn.addActionListener(new ActionListener()
    {
      
      @Override
      public void actionPerformed(ActionEvent pE)
      {
        List<String> selectList = MultiSelectionController.openMultiSelectionDialog(frame, 
            btn.getLocation(), l, Arrays.asList("zwei"));
        
        System.out.println(join.join(selectList));
      }
    });
    
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    
  }
}
