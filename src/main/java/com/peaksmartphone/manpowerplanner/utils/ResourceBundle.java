package com.peaksmartphone.manpowerplanner.utils;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * <p> Title: ResourceBundle</p>
 * 
 * <b>Description:</b> 
 * <p> Insert Description here </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 */
public final class ResourceBundle
{
  private static java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com.peaksmartphone.manpowerplanner.resources");
  
  
  /** 
   * Klasse nicht instantiierbar!
   */
  private ResourceBundle()
  {    
    
  }
  
  public static void setLocale(Locale pLocale)
  {
    bundle = java.util.ResourceBundle.getBundle("com.peaksmartphone.manpowerplanner.resources", pLocale);
  }

  /**
   * 
   * @param pKey key aus dem File resource.properties
   * @return Eingetragener Wert in den resourcen
   */
  public static String getString(String pKey, Object...pArgs)
  {
    return MessageFormat.format(bundle.getString(pKey), pArgs);
  }

}
