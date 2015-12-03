package com.peaksmartphone.manpowerplanner;

import java.awt.SystemColor;

import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.gui.mainpage.MainPageController;
import com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst.WeeklyScheduleInstValueParser;
import com.peaksmartphone.manpowerplanner.utils.ExcelExportUtil;
import com.peaksmartphone.manpowerplanner.utils.MPPProperties;

/**
 * 
 * <p> Title: {@link StartManpowerPlanner}</p>
 * 
 * <b>Description:</b> 
 * <p> Insert Description here </p>
 *
 * @author zach.xu1987@gmail.com
 *
 *  <p>Company: PeakSmartPhone</p>
 *
 *
 */
public class StartManpowerPlanner {

  private static final Logger LOG = LogManager.getLogger(StartManpowerPlanner.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	  MPPProperties.loadPropertiesFromFile(args[0]);
	  start();
	}

  /**
   * 
   */
  private static void start()
  {

    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

      ColorUIResource c = new ColorUIResource(UIManager.getColor("Panel.background"));

      UIManager.put("TaskPane.titleForeground", new ColorUIResource(SystemColor.menuText));
      UIManager.put("TaskPane.titleOver", new ColorUIResource(SystemColor.menuText));
      UIManager.put("TaskPane.titleBackgroundGradientStart", c.darker());
      UIManager.put("TaskPane.titleBackgroundGradientEnd", c.darker().darker());
      UIManager.put("TaskPane.background", c.brighter());
      UIManager.put("TaskPane.borderColor", c.darker());
    } 
    catch (UnsupportedLookAndFeelException pEx) 
    {
      LOG.warn("Error setting Look & Feel:", pEx);
    }
    catch (ClassNotFoundException pEx) 
    {
      LOG.warn("Error setting Look & Feel:", pEx);
    }
    catch (InstantiationException pEx) 
    {
      LOG.warn("Error setting Look & Feel:", pEx);
    }
    catch (IllegalAccessException pEx) 
    {
      LOG.warn("Error setting Look & Feel:", pEx);
    }
    
    ToolTipManager.sharedInstance().setInitialDelay(500);
    ToolTipManager.sharedInstance().setReshowDelay(500);
    ToolTipManager.sharedInstance().setDismissDelay(30000);
    
    ExcelExportUtil.registerValueParser(DailyScheduleInst.class, new WeeklyScheduleInstValueParser());
    

    final MainPageController mpController = new MainPageController();
    
    mpController.show();      
  }

}
