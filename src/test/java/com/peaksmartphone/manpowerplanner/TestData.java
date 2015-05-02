package com.peaksmartphone.manpowerplanner;

import com.peaksmartphone.manpowerplanner.core.db.SessionManager;

/**
 * <p> Title: {@link TestData}</p>
 * 
 * <b>Description:</b> 
 * <p>  </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 * 
 */
public class TestData
{
  public static SessionManager getTestSessionManager()
  {
    return new SessionManager(
        "MPP", "MPP", 
        "jdbc:derby:./singlederby/MPP;create=true", 
        "MPP", "org.apache.derby.jdbc.EmbeddedDriver", "org.hibernate.dialect.DerbyDialect");
  }
}
