package com.peaksmartphone.manpowerplanner.core.gui.common.fields;

import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.peaksmartphone.manpowerplanner.core.data.exception.MPPRuntimeException;

/**
 * <p> Title: {@link NumberField}</p>
 * 
 * <b>Description:</b> 
 * <p> Field for input and display of numeric values. Wrong user input is automatically corrected.
 *  
 *  </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * $Rev: 42076 $:     Revision of last commit<br/>
 * $Author: zxu $:  Author of last commit<br/>
 * $Date: 2014-11-20 18:04:11 +0800 (Do, 20 Nov 2014) $:    Date of last commit
 * 
 */
public class NumberField extends JTextField
{
  private static final long serialVersionUID = 1;
  
  private int mMaxLength;
  
  private boolean mPrefSizeSet = false;
  private boolean mMinSizeSet = false;
  
  /**
   * 
   * @param pMaxLenght max. length of the filed (unscaled Value + pScale + 1 space for seperator)
   * @param pScale decimal places
   */
  public NumberField(int pMaxLenght)
  {
    mMaxLength = pMaxLenght;
    
    this.setDocument(new TextDocumentLimit(pMaxLenght));
    
    this.addFocusListener(new FocusAdapter()
    {
       /* (non-Javadoc)
       * @see java.awt.event.FocusAdapter#focusLost(java.awt.event.FocusEvent)
       */
      public void focusLost(FocusEvent pE)
      {
        validateValue();
      }
    });
    
    this.addKeyListener(new KeyAdapter()
    {
      
      @Override
      public void keyPressed(KeyEvent pE)
      {
        if (pE.getKeyCode() == KeyEvent.VK_ENTER)
        {
          validateValue();
        }
      }
    });
    
    setColumns(pMaxLenght);
    
  }
  
  /* (non-Javadoc)
   * @see javax.swing.JTextField#getPreferredSize()
   */
  @Override
  public Dimension getPreferredSize()
  {   
    if (mPrefSizeSet)
    {
      return super.getPreferredSize();
    }
    else
    {
      return calcDimension();
    }
  }
  
  
  /*
   * 
   */
  private Dimension calcDimension()
  {
    return new Dimension(
        mMaxLength * getColumnWidth() + getInsets().left + getInsets().right, 
        20);
  }
  
  /* (non-Javadoc)
   * @see javax.swing.JComponent#getMinimumSize()
   */
  @Override
  public Dimension getMinimumSize()
  {
    if (mMinSizeSet)
    {
      return super.getMinimumSize();
    }
    else
    {
      return calcDimension();
    }
  }
  
  /* (non-Javadoc)
   * @see javax.swing.JComponent#setPreferredSize(java.awt.Dimension)
   */
  @Override
  public void setPreferredSize(Dimension pPreferredSize)
  {
    mPrefSizeSet = true;
    super.setPreferredSize(pPreferredSize);
  }
  
  /* (non-Javadoc)
   * @see javax.swing.JComponent#setMinimumSize(java.awt.Dimension)
   */
  @Override
  public void setMinimumSize(Dimension pMinimumSize)
  {
    mMinSizeSet = true;
    super.setMinimumSize(pMinimumSize);
  }
  
  /**
   * 
   * @return value of the field as Long. null, if field is empty.
   */
  public Long getLongVal()
  {
    return Long.parseLong(getText());
  }

  /**
   * 
   * @return value of the field as Integer. null, if field is empty.
   */
  public Integer getIntegerVal()
  {
    Integer retVal = null;
    
    try
    {
      retVal = Integer.parseInt(getText()); 
    }
    catch(NumberFormatException pE)
    {
      retVal = 0;
    }
    
    return retVal;
  }
  
  /**
   * validates the field input. If not valid, the value is set back to empty space
   */
  private void validateValue()
  {
    try
    {
        Long.parseLong(getText());
    }
    catch (NumberFormatException ex)
    {
      setText("");
    }
    
  }
  
  /**
   * 
   * @param pValue sets the value as text
   */
  public void setLongVal(Long pValue)
  {
    String txtVal = "";
    
    if (pValue != null)
    {
      txtVal = String.valueOf(pValue);
    }
    
    setText(txtVal);
  }
  
  /**
   * 
   * @param pValue sets the value as text
   */
  public void setIntegerVal(Integer pValue)
  {
    String txtVal = "";
    
    if (pValue != null)
    {
      txtVal = String.valueOf(pValue);
    }
    
    setText(txtVal);
  }

}
