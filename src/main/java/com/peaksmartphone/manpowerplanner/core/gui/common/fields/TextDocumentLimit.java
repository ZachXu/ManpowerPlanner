package com.peaksmartphone.manpowerplanner.core.gui.common.fields;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;


/**
 * 
 * <p> Title: {@link TextDocumentLimit}</p>
 * 
 * <b>Description:</b> 
 * <p> class for limiting length of textfields.</p>
 * 
 *  @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 *
 */
public class TextDocumentLimit extends PlainDocument
{
  private static final long serialVersionUID = 1;
  private int mLimit;

  /**
   * 
   * @param pLimit maximum text length
   */
  public TextDocumentLimit(int pLimit)
  {
    super();
    this.mLimit = pLimit;
  }
  
  /**
   * overwritten to limit the text length.
   * @param pOffset position of the Cursors
   * @param pString actual document value
   * @param pAttr is not processed
   */
  public void insertString(int pOffset, String pString, AttributeSet pAttr)
      throws BadLocationException
  {
    if (pString == null)
    {
      return;
    }

    if ((getLength() + pString.length()) <= mLimit)
    {
      super.insertString(pOffset, pString, pAttr);
    }
  }
}
