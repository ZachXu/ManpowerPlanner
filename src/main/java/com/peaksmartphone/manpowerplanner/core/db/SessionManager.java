package com.peaksmartphone.manpowerplanner.core.db;


import java.io.Serializable;
import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.SessionStatistics;

/**
 * <p> Title: SessionManager</p>
 * 
 * <b>Description:</b> 
 * <p> manages the states of the hibernate-connection</p>
 *
 * @author vdi
 *
 *  <p>Company: Peak Solution GmbH</p>
 *
 * $Rev: 38010 $:     Revision of last commit<br/>
 * $Author: zxu $:  Author of last commit<br/>
 * $Date: 2014-04-16 14:27:09 +0800 (Mi, 16 Apr 2014) $:    Date of last commit
 * 
 */
public class SessionManager
{
  public final static ExecutorService EXECUTOR = Executors.newFixedThreadPool(1);
  
  private static class SessionHolder extends ThreadLocal<Session>{
    
    private static SessionFactory mSessionFactory;

    /**
     * @param pSessionFactory
     */
    public SessionHolder(SessionFactory pSessionFactory)
    {
      super();
      mSessionFactory = pSessionFactory;
    }

    /* (non-Javadoc)
     * @see java.lang.ThreadLocal#initialValue()
     */
    @Override
    protected Session initialValue()
    {
      Session session = null;
      
      if (mSessionFactory != null)
      {
        session = mSessionFactory.openSession();
        session.beginTransaction();
      }
      else
      {
        session = super.initialValue();
      }
      return session;
    }

    /**
     * @param pSessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory pSessionFactory)
    {
      mSessionFactory = pSessionFactory;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory()
    {
      return mSessionFactory;
    }
    
    /**
     * close old session and create a new session of current thread
     */
    public void newSession() throws HibernateException
    {
      try
      {
        Session session = get();
        
        if (session != null && session.isOpen())
        {
          session.close();
        }
        
        session = initialValue();
        
        set(session);
      }
      catch(HibernateException e)
      {
        throw e;
      }
    }
  }

  private static final Logger LOG = LogManager.getLogger(SessionManager.class);
  
  private final String mDBUrl;
  private final String mDBUser;
  private final String mDBPassword;
  private final String mDBScheme;
  
  private SessionFactory mSessionFactory;

  private Configuration mConfiguration;

  private static SessionHolder mSessionHolder;

  /**
   * 
   * @param pUser User for DB Connection
   * @param pDBPwd Password for the DB Connection
   * @param pURL URL to the DB
   * @param pScheme Scheme of the DB
   */
  public SessionManager(String pUser, 
      String pDBPwd, 
      String pURL, 
      String pScheme,
      String pDBDriver,
      String pDBDialect)
  {
    mDBUrl = pURL;
    mDBUser = pUser;
    mDBPassword = pDBPwd;
    mDBScheme = pScheme;
   
    LOG.debug("createSession: load die Hibernate Factory");
    mConfiguration = new Configuration().configure(
        "com/peaksmartphone/manpowerplanner/core/db/hibernate/hibernate.cfg.xml")
      .setProperty("hibernate.connection.driver_class", pDBDriver)
      .setProperty("hibernate.dialect", pDBDialect)
      .setProperty("hibernate.connection.password", mDBPassword)
      .setProperty("hibernate.connection.url", mDBUrl)
      .setProperty("hibernate.connection.username", mDBUser)
      .setProperty("hibernate.connection.schema", pScheme)
      .setProperty("hibernate.default_schema", pScheme);
    LOG.debug("createSession: load die Hibernate Factory.");
    
    mConfiguration.buildMappings();
    mSessionFactory = mConfiguration.buildSessionFactory();
    mSessionHolder = new SessionHolder(mSessionFactory);
  }


  /**
   * 
   * @return Session
   */
  public synchronized Session getSession()
  {
    return mSessionHolder.get();
  }

  /**
   * 
   * @return URL of the DB
   */
  public String getDBUrl()
  {
    return mDBUrl;
  }

/**
 * 
 * @return DB-User
 */
  public String getDBUser()
  {
    return mDBUser;
  }

  /**
   * 
   * @return Password for the DB
   */
  public String getDBPassword()
  {
    return mDBPassword;
  }


  /**
   * ending a transaction
   */
  public synchronized void commit()
  {
    Session session = mSessionHolder.get();
    
    try
    {
      session.getTransaction().commit();
      mSessionHolder.newSession();
    } 
    catch (HibernateException e)
    {
      session.getTransaction().rollback();
      LOG.error("Couldn't execute commit!", e);
    }
  }
  
  /**
   * 
   * @param pResource link to the mapping file as resource
   * @param pLoader classloader to be used
   */
  public synchronized void addMappingFile(String pResource, ClassLoader pLoader)
  {
    mConfiguration = mConfiguration.addResource(pResource, pLoader);
    
    mConfiguration.buildMappings();
    
    mSessionFactory = mConfiguration.buildSessionFactory();
    
    mSessionHolder.setSessionFactory(mSessionFactory);
    
    startNewSession();
  }
  
  /**
   * 
   * @param pResources link to the mapping files
   * @param pLoader classloader to be used
   */
  public synchronized void addMappingFiles(String[] pResources, ClassLoader pLoader)
  {
    for(String resource : pResources)
    {
      mConfiguration = mConfiguration.addResource(resource, pLoader);
    }
    
    mConfiguration.buildMappings();
    
    mSessionFactory = mConfiguration.buildSessionFactory();
    
    mSessionHolder.setSessionFactory(mSessionFactory);
    
    startNewSession();
  }
  
  /**
   * 
   */
  public synchronized void startNewSession()
  {
    mSessionHolder.newSession();
  }
  
  
  public synchronized void clear()
  {
    mSessionHolder.get().clear();
  }
  /**
   * cancel a transaction (revert)
   */
  public synchronized void rollback()
  {
    Session session = mSessionHolder.get();
    
    if (session.getTransaction().isActive())
    {
      session.getTransaction().rollback();
    }
    
    mSessionHolder.newSession();
    
  }
  
  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @see org.hibernate.Session#createCriteria(java.lang.Class, java.lang.String)
   */
  public synchronized Criteria createCriteria(Class pArg0, String pArg1)
  {
    return mSessionHolder.get().createCriteria(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @return
   * @see org.hibernate.Session#createCriteria(java.lang.Class)
   */
  public synchronized Criteria createCriteria(Class pArg0)
  {
    return mSessionHolder.get().createCriteria(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @see org.hibernate.Session#createCriteria(java.lang.String, java.lang.String)
   */
  public synchronized Criteria createCriteria(String pArg0, String pArg1)
  {
    return mSessionHolder.get().createCriteria(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @return
   * @see org.hibernate.Session#createCriteria(java.lang.String)
   */
  public synchronized Criteria createCriteria(String pArg0)
  {
    return mSessionHolder.get().createCriteria(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#createFilter(java.lang.Object, java.lang.String)
   */
  public synchronized Query createFilter(Object pArg0, String pArg1)
      throws HibernateException
  {
    return mSessionHolder.get().createFilter(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#createQuery(java.lang.String)
   */
  public synchronized Query createQuery(String pArg0) throws HibernateException
  {
    return mSessionHolder.get().createQuery(pArg0);
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#createSQLQuery(java.lang.String)
   */
  public synchronized SQLQuery createSQLQuery(String pArg0) throws HibernateException
  {
    return mSessionHolder.get().createSQLQuery(pArg0);
  }

  /**
   * @param pArg0
   * @throws HibernateException
   * @see org.hibernate.Session#delete(java.lang.Object)
   */
  public synchronized void delete(Object pArg0) throws HibernateException
  {
    mSessionHolder.get().delete(pArg0);
  }

  /**
   * @param pClass
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#delete(java.lang.String, java.lang.Object)
   */
  public synchronized void delete(String pClass, Object pArg1) throws HibernateException
  {
    mSessionHolder.get().delete(pClass, pArg1);
  }

  /**
   * @param pArg0
   * @see org.hibernate.Session#disableFilter(java.lang.String)
   */
  public synchronized void disableFilter(String pArg0)
  {
    mSessionHolder.get().disableFilter(pArg0);
  }

  /**
   * @param pArg0
   * @return
   * @see org.hibernate.Session#enableFilter(java.lang.String)
   */
  public synchronized Filter enableFilter(String pArg0)
  {
    return mSessionHolder.get().enableFilter(pArg0);
  }

  /**
   * @param pArg0
   * @throws HibernateException
   * @see org.hibernate.Session#evict(java.lang.Object)
   */
  public synchronized void evict(Object pArg0) throws HibernateException
  {
    mSessionHolder.get().evict(pArg0);
  }

  /**
   * @throws HibernateException
   * @see org.hibernate.Session#flush()
   */
  public synchronized void flush() throws HibernateException
  {
    mSessionHolder.get().flush();
  }

  /**
   * @param pArg0
   * @param pArg1
   * @param pArg2
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#get(java.lang.Class, java.io.Serializable, org.hibernate.LockMode)
   */
  public synchronized Object get(Class pArg0, Serializable pArg1, LockMode pArg2)
      throws HibernateException
  {
    return mSessionHolder.get().get(pArg0, pArg1, pArg2);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#get(java.lang.Class, java.io.Serializable)
   */
  public synchronized Object get(Class pArg0, Serializable pArg1) throws HibernateException
  {
    return mSessionHolder.get().get(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @param pArg2
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#get(java.lang.String, java.io.Serializable, org.hibernate.LockMode)
   */
  public synchronized Object get(String pArg0, Serializable pArg1, LockMode pArg2)
      throws HibernateException
  {
    return mSessionHolder.get().get(pArg0, pArg1, pArg2);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#get(java.lang.String, java.io.Serializable)
   */
  public synchronized Object get(String pArg0, Serializable pArg1) throws HibernateException
  {
    return mSessionHolder.get().get(pArg0, pArg1);
  }

  /**
   * @return
   * @see org.hibernate.Session#getCacheMode()
   */
  public CacheMode getCacheMode()
  {
    return mSessionHolder.get().getCacheMode();
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#getCurrentLockMode(java.lang.Object)
   */
  public synchronized LockMode getCurrentLockMode(Object pArg0) throws HibernateException
  {
    return mSessionHolder.get().getCurrentLockMode(pArg0);
  }

  /**
   * @param pArg0
   * @return
   * @see org.hibernate.Session#getEnabledFilter(java.lang.String)
   */
  public synchronized Filter getEnabledFilter(String pArg0)
  {
    return mSessionHolder.get().getEnabledFilter(pArg0);
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#getEntityName(java.lang.Object)
   */
  public synchronized String getEntityName(Object pArg0) throws HibernateException
  {
    return mSessionHolder.get().getEntityName(pArg0);
  }

  /**
   * @return
   * @see org.hibernate.Session#getFlushMode()
   */
  public synchronized FlushMode getFlushMode()
  {
    return mSessionHolder.get().getFlushMode();
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#getIdentifier(java.lang.Object)
   */
  public synchronized Serializable getIdentifier(Object pArg0) throws HibernateException
  {
    return mSessionHolder.get().getIdentifier(pArg0);
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#getNamedQuery(java.lang.String)
   */
  public synchronized Query getNamedQuery(String pArg0) throws HibernateException
  {
    return mSessionHolder.get().getNamedQuery(pArg0);
  }

  /**
   * @return
   * @see org.hibernate.Session#getSessionFactory()
   */
  public synchronized SessionFactory getSessionFactory()
  {
    return mSessionHolder.get().getSessionFactory();
  }

  /**
   * @return
   * @see org.hibernate.Session#getStatistics()
   */
  public synchronized SessionStatistics getStatistics()
  {
    return mSessionHolder.get().getStatistics();
  }

  /**
   * @return
   * @see org.hibernate.Session#isConnected()
   */
  public synchronized boolean isConnected()
  {
    return mSessionHolder.get().isConnected();
  }

  /**
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#isDirty()
   */
  public synchronized boolean isDirty() throws HibernateException
  {
    return mSessionHolder.get().isDirty();
  }

  /**
   * @return
   * @see org.hibernate.Session#isOpen()
   */
  public synchronized boolean isOpen()
  {
    return mSessionHolder.get().isOpen();
  }

  /**
   * @param pArg0
   * @param pArg1
   * @param pArg2
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#load(java.lang.Class, java.io.Serializable, org.hibernate.LockMode)
   */
  public synchronized Object load(Class pArg0, Serializable pArg1, LockMode pArg2)
      throws HibernateException
  {
    return mSessionHolder.get().load(pArg0, pArg1, pArg2);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#load(java.lang.Class, java.io.Serializable)
   */
  public synchronized Object load(Class pArg0, Serializable pArg1) throws HibernateException
  {
    return mSessionHolder.get().load(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#load(java.lang.Object, java.io.Serializable)
   */
  public synchronized void load(Object pArg0, Serializable pArg1) throws HibernateException
  {
    mSessionHolder.get().load(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @param pArg2
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#load(java.lang.String, java.io.Serializable, org.hibernate.LockMode)
   */
  public synchronized Object load(String pArg0, Serializable pArg1, LockMode pArg2)
      throws HibernateException
  {
    return mSessionHolder.get().load(pArg0, pArg1, pArg2);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#load(java.lang.String, java.io.Serializable)
   */
  public synchronized Object load(String pArg0, Serializable pArg1)
      throws HibernateException
  {
    return mSessionHolder.get().load(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#lock(java.lang.Object, org.hibernate.LockMode)
   */
  public synchronized void lock(Object pArg0, LockMode pArg1) throws HibernateException
  {
    mSessionHolder.get().lock(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @param pArg2
   * @throws HibernateException
   * @see org.hibernate.Session#lock(java.lang.String, java.lang.Object, org.hibernate.LockMode)
   */
  public synchronized void lock(String pArg0, Object pArg1, LockMode pArg2)
      throws HibernateException
  {
    mSessionHolder.get().lock(pArg0, pArg1, pArg2);
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#merge(java.lang.Object)
   */
  public synchronized Object merge(Object pArg0) throws HibernateException
  {
    return mSessionHolder.get().merge(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#merge(java.lang.String, java.lang.Object)
   */
  public synchronized Object merge(String pArg0, Object pArg1) throws HibernateException
  {
    return mSessionHolder.get().merge(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @throws HibernateException
   * @see org.hibernate.Session#persist(java.lang.Object)
   */
  public synchronized void persist(Object pArg0) throws HibernateException
  {
    mSessionHolder.get().persist(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#persist(java.lang.String, java.lang.Object)
   */
  public synchronized void persist(String pArg0, Object pArg1) throws HibernateException
  {
    mSessionHolder.get().persist(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @throws HibernateException
   * @see org.hibernate.Session#reconnect(java.sql.Connection)
   */
  public synchronized void reconnect(Connection pArg0) throws HibernateException
  {
    mSessionHolder.get().reconnect(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#refresh(java.lang.Object, org.hibernate.LockMode)
   */
  public synchronized void refresh(Object pArg0, LockMode pArg1) throws HibernateException
  {
    mSessionHolder.get().refresh(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @throws HibernateException
   * @see org.hibernate.Session#refresh(java.lang.Object)
   */
  public synchronized void refresh(Object pArg0) throws HibernateException
  {
    mSessionHolder.get().refresh(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#replicate(java.lang.Object, org.hibernate.ReplicationMode)
   */
  public synchronized void replicate(Object pArg0, ReplicationMode pArg1)
      throws HibernateException
  {
    mSessionHolder.get().replicate(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @param pArg2
   * @throws HibernateException
   * @see org.hibernate.Session#replicate(java.lang.String, java.lang.Object, org.hibernate.ReplicationMode)
   */
  public synchronized void replicate(String pArg0, Object pArg1, ReplicationMode pArg2)
      throws HibernateException
  {
    mSessionHolder.get().replicate(pArg0, pArg1, pArg2);
  }

  /**
   * @param pArg0
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#save(java.lang.Object)
   */
  public synchronized Serializable save(Object pArg0) throws HibernateException
  {
    return mSessionHolder.get().save(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @return
   * @throws HibernateException
   * @see org.hibernate.Session#save(java.lang.String, java.lang.Object)
   */
  public synchronized Serializable save(String pArg0, Object pArg1)
      throws HibernateException
  {
    return mSessionHolder.get().save(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @throws HibernateException
   * @see org.hibernate.Session#saveOrUpdate(java.lang.Object)
   */
  public synchronized void saveOrUpdate(Object pArg0) throws HibernateException
  {
    mSessionHolder.get().saveOrUpdate(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#saveOrUpdate(java.lang.String, java.lang.Object)
   */
  public synchronized void saveOrUpdate(String pArg0, Object pArg1)
      throws HibernateException
  {
    mSessionHolder.get().saveOrUpdate(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @see org.hibernate.Session#setCacheMode(org.hibernate.CacheMode)
   */
  public  void setCacheMode(CacheMode pArg0)
  {
    mSessionHolder.get().setCacheMode(pArg0);
  }

  /**
   * @param pArg0
   * @see org.hibernate.Session#setFlushMode(org.hibernate.FlushMode)
   */
  public void setFlushMode(FlushMode pArg0)
  {
    mSessionHolder.get().setFlushMode(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @see org.hibernate.Session#setReadOnly(java.lang.Object, boolean)
   */
  public void setReadOnly(Object pArg0, boolean pArg1)
  {
    mSessionHolder.get().setReadOnly(pArg0, pArg1);
  }

  /**
   * @param pArg0
   * @throws HibernateException
   * @see org.hibernate.Session#update(java.lang.Object)
   */
  public synchronized void update(Object pArg0) throws HibernateException
  {
    mSessionHolder.get().update(pArg0);
  }

  /**
   * @param pArg0
   * @param pArg1
   * @throws HibernateException
   * @see org.hibernate.Session#update(java.lang.String, java.lang.Object)
   */
  public synchronized void update(String pArg0, Object pArg1) throws HibernateException
  {
    mSessionHolder.get().update(pArg0, pArg1);
  }


  public String getDBScheme()
  {
    return mDBScheme;
  }

}
