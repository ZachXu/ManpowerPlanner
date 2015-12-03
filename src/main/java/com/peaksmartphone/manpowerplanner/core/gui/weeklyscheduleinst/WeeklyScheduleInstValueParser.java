package com.peaksmartphone.manpowerplanner.core.gui.weeklyscheduleinst;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;
import com.peaksmartphone.manpowerplanner.core.data.DailyScheduleInst;
import com.peaksmartphone.manpowerplanner.core.data.Employee;
import com.peaksmartphone.manpowerplanner.utils.ValueParser;

/**
 * <p> Title: {@link WeeklyScheduleInstValueParser} </p>
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
public class WeeklyScheduleInstValueParser implements ValueParser<DailyScheduleInst>
{
  private final Joiner mSplitJoiner = Joiner.on(",");

  /* (non-Javadoc)
   * @see com.peaksmartphone.manpowerplanner.utils.ValueParser#parseValue(java.lang.Object)
   */
  @Override
  public String parseValue(DailyScheduleInst pData)
  {
    String value = "";
    
    List<String> strList = new ArrayList<String>();
    
    for (Employee assginedemployee : pData.getAssignedEmployees())
    {
      strList.add(assginedemployee.getEmployeeName());
    }
    value = mSplitJoiner.join(strList);
    return value;
  }
}
