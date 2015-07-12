package com.peaksmartphone.manpowerplanner.core.gui.common.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 * <p> Title: {@link AbstractListTableModel} </p>
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
public abstract class AbstractListTableModel<T> extends AbstractTableModel
{
  private static final long serialVersionUID = 1;

  private final String[] mColumnNames;

  private List<T> mDataList = new ArrayList<T>();
  
  /**
   * 
   * @param pDataList List mit Daten
   * @param pColumnNames Liste der SpaltenNamen
   */
  public AbstractListTableModel(List<T> pDataList, String[] pColumnNames)
  {
    mColumnNames = pColumnNames;
    this.mDataList = pDataList;
  }
  
  /**
   * @param pColumn Spalte fuer welche der Name benoetigt wird
   * @return Name der uebergebenen Spalte
   */
  public String getColumnName(int pColumn)
  {
    return mColumnNames[pColumn];
  }
  
  
  /**
   * 
   * @param pDataList Liste von Daten die es darzuestellen gilt.
   */
  public void refresh(List<T> pDataList)
  {
    mDataList = new ArrayList<T>();
    mDataList.addAll(pDataList);
    
    fireTableChanged(new TableModelEvent(this));
  }
  
  /**
   * 
   * @param pRow Tabellenreihe 
   * @return Datenobjekt in der selektierten Reihe der Tabelle
   */
  public T getObjectAt(int pRow)
  {
    T returnVal = null;
    
    if (pRow >= 0)
    {
      returnVal = mDataList.get(pRow);
    }
    
    return returnVal;
  }
  
  /**
   * @return Menge der Spalten
   */
  public int getColumnCount()
  {
    return mColumnNames.length;
  }

  /**
   * @return Anzahl der Zeilen
   */
  public int getRowCount()
  {
    return mDataList.size();
  }
  
  /**
   * setzt das objekt in einer Reihe
   * @param pRow Reihe in der Tabelle
   * @param pData Werthaltendes Objekt dieser Reihe
   */
  public void setDataAt(int pRow, T pData)
  {
    mDataList.set(pRow, pData);
    fireTableDataChanged();
  }
  
  public void removeData(Object pData)
  {
    mDataList.remove(pData);
    fireTableDataChanged();
  }
  
  /**
   * 
   * @param pData neuer Datensatz am Ende der Liste
   */
  public void addData(T pData)
  {
    mDataList.add(pData);
    fireTableDataChanged();
  }
  
  /**
   * If pData with its id is in the table, it will be replaced with the given object
   * @param pData 
   */
  public void replaceOrAddDataObject(T pData)
  {
    int index = getDataList().indexOf(pData);
    
    if (index == -1)
    {
      addData(pData); 
    }
    else
    {
      setDataAt(index, pData);
    }
  }
  
  /**
   * 
   * @return nicht modifizierbare Liste der Daten
   */
  public List<T> getDataList()
  {
    return Collections.unmodifiableList(mDataList);
  }
}
