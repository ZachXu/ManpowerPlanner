package com.peaksmartphone.manpowerplanner.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p> Title: {@link DateUtil}</p>
 * 
 * <b>Description:</b> 
 * <p> a util class for date calculation </p>
 *
 * @author ghu
 *
 *  <p>Company: Peak Solution GmbH</p>
 *
 * $Rev: $:     Revision of last commit<br/>
 * $Author: $:  Author of last commit<br/>
 * $Date: $:    Date of last commit
 * 
 */
public class DateUtil
{
  public final static long SECOND = 1000;
  public final static long MINUTE = SECOND * 60;
  public final static long HOUR = MINUTE * 60;
  public final static long DAY = HOUR * 24;
  public final static long WEEK = DAY * 7;
  public final static long MONTH = DAY * 30;
  
  public static final SimpleDateFormat SIMPLEDATEFORMAT = new SimpleDateFormat("YYYY.MM.dd");
  
  public static final SimpleDateFormat FILEDATEFORMAT = new SimpleDateFormat("YYYYMMddHHmmss");
  
  /**
   * get a Calendar instance only contains Date
   * @param pDate
   * @return a instance of Calendar
   */
  public static Calendar getCalendarOnlyWithDate(Date pDate)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(pDate);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    
    return calendar;
  }
  
  /**
   * get a Calendar instance without second and millisecond
   * @param pDate
   * @return a instance of Calendar
   */
  public static Calendar getCalendarWithoutSecond(Date pDate)
  {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(pDate);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    
    return calendar;
  }
  
  /**
   * 
   * @param time
   * @return
   */
  public static String calculateTime(long time)
  {
    if (time <= 0)
    {
      return "0";
    }
    
    StringBuilder sb = new StringBuilder();
    
    long days = time / DAY;
    long hours = (time % DAY) / HOUR;
    long minutes = (time % HOUR) / MINUTE;
    long seconds = (time % MINUTE) / SECOND;
    
    if (days > 0)
    {
      sb.append(days).append("d");
    }
    if (hours > 0)
    {
      sb.append(hours).append("h");
    }
    if (minutes > 0)
    {
      sb.append(minutes).append("m");
    }
    if (seconds > 0)
    {
      sb.append(seconds).append("s");
    }
    
    return sb.toString();
  }
  
  /**
   * 
   * @param time
   * @return
   */
  public static String calculateTimeToMinute(long time)
  {
    if (time <= 0)
    {
      return "0m";
    }
    
    StringBuilder sb = new StringBuilder();
    
    long days = time / DAY;
    long hours = (time % DAY) / HOUR;
    long minutes = (time % HOUR) / MINUTE;
    
    if (days > 0)
    {
      sb.append(days).append("d").append(" ");
    }
    if (hours > 0)
    {
      sb.append(hours).append("h").append(" ");
    }

    sb.append(minutes).append("m");
    
    return sb.toString();
  }
  
  public static int getDaysFromMilliseconds(long pMilliSec)
  {
    if (pMilliSec > 0)
    {
      return (int) (pMilliSec / DAY);
    }
    else 
    {
      return 0;
    }
  }
  
  /**
   * @param pValue in the format of '*d*h*m'
   * @return the millisecond value of the date string.
   */
  public static Long transformDateStringIntoMillisecond(String pValue)
  {
    Long retValue = Long.valueOf(0);
    String date = pValue;
    
    if (date != null && date.trim().length() > 0)
    {
      try
      {
        int indexOfD = date.indexOf('d');
        
        if (indexOfD > 0)
        {
          retValue += Long.valueOf(date.substring(0, indexOfD).trim()) * DAY;
        }
        
        date = indexOfD + 1 < date.length() ? date.substring(indexOfD + 1) : "";
        
        int indexOfH = date.indexOf('h');
        
        if (indexOfH > 0)
        {
          retValue += Long.valueOf(date.substring(0, indexOfH).trim()) * HOUR;
        }
        
        date = indexOfH + 1 < date.length() ? date.substring(indexOfH + 1) : "";
        
        int indexOfM = date.indexOf('m');
        
        if (indexOfM > 0)
        {
          retValue += Long.valueOf(date.substring(0, indexOfM).trim()) * MINUTE;
        }
        
      }
      catch (NumberFormatException e)
      {
        retValue = null;
      }
    }
    
    return retValue;
  }
  
  /**
   * 
   * @param pBefore
   * @param pAfter
   * @return validate if pBefore is before or equals pAfter
   */
  public static boolean beforeOrEquals(Date pBefore, Date pAfter)
  {
    Calendar calBefore = getCalendarWithoutSecond(pBefore);
    Calendar calAfter = getCalendarWithoutSecond(pAfter);
    
    return calBefore.equals(calAfter) || calBefore.before(calAfter);
  }
}
