package com.peaksmartphone.manpowerplanner.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.peaksmartphone.manpowerplanner.core.data.exception.MPPRuntimeException;

/**
 * <p> Title: {@link MPPProperties}</p>
 * 
 * <b>Description:</b> 
 * <p> Manage all Properties of the application /cfg/prp.properties</p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: Peak Solution GmbH</p>
 *
 * $Rev: 37895 $:     Revision of last commit<br/>
 * $Author: zxu $:  Author of last commit<br/>
 * $Date: 2014-04-11 10:34:20 +0800 (Fr, 11 Apr 2014) $:    Date of last commit
 * 
 */
public class MPPProperties
{
  private static final Logger LOG = LogManager.getLogger(MPPProperties.class);
  private static Properties props = new Properties();
  
  static
  {
    try
    {
      props.load(MPPProperties.class.getResourceAsStream("/cfg/mpp_derby.properties"));
    } 
    catch (IOException ex)
    {
      LOG.error("PRPProperties(): PRPProperties couldn't be loaded!", ex);
      throw new MPPRuntimeException(ex);
    }
  }
  
  
  /**
   * loading properties out of an external file
   * @param pURL link to an external Property File
   */
  public static void loadPropertiesFromFile(String pURL)
  {
    try
    {
      URL url = new URL(pURL);
      props = new Properties();
      props.load(url.openStream());
    } 
    catch (MalformedURLException ex)
    {
      // try load as a file
      File file = new File (pURL);
      try
      {
        props = new Properties();
        props.load(new FileInputStream(file));
      } catch (FileNotFoundException e)
      {
        LOG.error("loadPropertiesFromFile(): Properties cannot loaded", ex);
        throw new MPPRuntimeException(ex);
      } catch (IOException e)
      {
        LOG.error("loadPropertiesFromFile(): Properties cannot loaded!", ex);
        throw new MPPRuntimeException(ex);
      }

    } 
    catch (IOException ex)
    {
      LOG.error("loadPropertiesFromFile(): Properties cannot loaded!", ex);
      throw new MPPRuntimeException(ex);
    }
    
  }

  
  /**
   * 
   * @return ORacle driver class
   */
  public static String getDBDriver()
  {
    return getProperty("DB_Driver");
  }
  
  /**
   * 
   * @return ORacle driver class
   */
  public static String getDBDialect()
  {
    return getProperty("DB_DIALECT");
  }
  
  /**
   * 
   * @return JDBC URL
   */
  public static String getDBURL()
  {
    return getProperty("DB_URL");
  }
  
  /**
   * 
   * @return DB Nutzer
   */
  public static String getDBUser()
  {
    return getProperty("DB_User");
  }
  
  /**
   * 
   * @return DB Nutzer
   */
  public static String getDBSchema()
  {
    return getProperty("DB_Schema");
  }
  
  /**
   * 
   * @return DB Passwort: 
   */
  public static String getDBPassword()
  {
    return getProperty("DB_Password");
  }

  /**
   * @param pKey
   * @return
   */
  public static boolean getBoolean(String pKey)
  {
    return Boolean.valueOf(getProperty(pKey));
  }
  
  /**
   * @param pKey
   * @return
   */
  public static int getInteger(String pKey)
  {
    return Integer.valueOf(getProperty(pKey));
  }
  
  /**
   * @param pKey
   * @return
   */
  public static long getLong(String pKey)
  {
    long retval = 0;
    String value = getProperty(pKey);
    if (value != null)
    {
      retval = Long.valueOf(value);
    }
    
    return retval;
  }
  
  /**
   * @param pKey Key in the property file
   * @return value from the property file
   * @see java.util.Properties#getProperty(java.lang.String)
   */
  public static String getProperty(String pKey)
  {
    return props.getProperty(pKey);
  }
  
  /**
   * @param pKey Key in the property file
   * @param pDefault Default value, if value from property file is null
   * @return value from the property file
   * @see java.util.Properties#getProperty(java.lang.String)
   */
  public static String getProperty(String pKey, String pDefault)
  {
    return props.getProperty(pKey, pDefault);
  }
  
  /**
   * @param pKey key in the property file
   * @param pValue value
   * @see java.util.Properties#getProperty(java.lang.String)
   */
  public static void setProperty(String pKey, String pValue)
  {
    props.setProperty(pKey, pValue);
  }

  /**
   * @param pKey
   * @return startday, 0 if property is null
   */
  public static int getIntegerProperty(String pKey)
  {
    int returnVal = 0;
    String property = getProperty(pKey);
    if (property != null && property.trim().length() != 0)
    {
      returnVal = Integer.valueOf(property);
    }
    return returnVal;
  }
}
