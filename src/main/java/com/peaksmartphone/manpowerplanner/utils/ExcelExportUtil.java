package com.peaksmartphone.manpowerplanner.utils;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 
 * <p> Title: {@link ExcelExportUtil} </p>
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
public class ExcelExportUtil
{ 
  private static final Logger LOG = LogManager.getLogger(ExcelExportUtil.class);
  
  private static Map<Class, ValueParser> registerMap = new ConcurrentHashMap<Class, ValueParser>();
  
  /**
   *
   */
  enum Styles
  {
    Standard, Header,
  }

  /**
   * Singleton
   */
  private ExcelExportUtil()
  {
  }

  /**
   * 
   * @return Singleton Instance
   */
  public static ExcelExportUtil getInstance()
  {
    return new ExcelExportUtil();
  }

  /**
   * @param pWorkBook
   * @return
   */
  private Map<Styles, CellStyle> createCellStyles(Workbook pWorkBook)
  {
    Map<Styles, CellStyle> styles = new HashMap<Styles, CellStyle>();

    CellStyle style = pWorkBook.createCellStyle();
    style.setBorderBottom(CellStyle.BORDER_THIN);
    style.setBorderLeft(CellStyle.BORDER_THIN);
    style.setBorderRight(CellStyle.BORDER_THIN);
    style.setBorderTop(CellStyle.BORDER_THIN);
    styles.put(Styles.Standard, style);

    Font headerFont = pWorkBook.createFont();
    headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

    CellStyle headerStyle = pWorkBook.createCellStyle();
    headerStyle.cloneStyleFrom(styles.get(Styles.Standard));
    headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    headerStyle.setFont(headerFont);
    styles.put(Styles.Header, headerStyle);

    return styles;
  }
  
  public void exportTable(TableModel pRowTableModel, JTable pContentTable)
  {
    exportTable(pRowTableModel, pContentTable, "");
  }

  
  public void exportTable(TableModel pRowTableModel, JTable pContentTable, String pFileName)
  {
    boolean issuccess = true;
    
    if("".equals(pFileName) || pFileName == null)
    {
      pFileName = String.format("%1$s%2$s.xls", "exportData", DateUtil.FILEDATEFORMAT.format(new Date()));
    }
    String tempPath = System.getProperty("java.io.tmpdir");
    final File xlsFile = new File(tempPath + pFileName);
    
    try
    {
      exportTable(pRowTableModel, pContentTable, xlsFile);
    } catch (IOException e)
    {
      issuccess = false;
      LOG.error(e.getMessage(), e);
    }

    
    if(issuccess)
    {
      //JOptionPane.showMessageDialog(null, ResourceBundle.getString(ExcelExportUtil.class.getName()+".EXPORT_SUCCESS_MESSAGE"));
      try
      {
        new ProcessBuilder("rundll32", "url.dll,FileProtocolHandler", xlsFile.getAbsolutePath()).start();
      } catch (IOException e)
      {
        MPPExceptionHandler.handleRuntimeException(e);
      }
    }
  }

 
  public void exportTable(TableModel pRowTableModel, JTable pContentTable, File pFile) throws IOException
  {
    TableModel contentTableModel = pContentTable.getModel();
    
    final Workbook wb = new HSSFWorkbook();

    final Sheet sheet = wb.createSheet("Schedule 1");

    final Map<Styles, CellStyle> styles = createCellStyles(wb);

    Row row;
    Cell cell;

    // Schreibe Header
    row = sheet.createRow((short) 0);
    
    cell = row.createCell(0);
    cell.setCellStyle(styles.get(Styles.Header));
    cell.setCellValue("");
    
    for (int colNr = 0; colNr < contentTableModel.getColumnCount(); colNr++)
    {
      cell = row.createCell(colNr + 1);
      cell.setCellStyle(styles.get(Styles.Header));
      cell.setCellValue(contentTableModel.getColumnName(colNr));
    }

    // Schreibe Daten
    for (int rowNr = 0; rowNr < contentTableModel.getRowCount(); rowNr++)
    {
      row = sheet.createRow((short) rowNr + 1);
      
      cell = row.createCell(0);
      cell.setCellStyle(styles.get(Styles.Header));
      cell.setCellValue(String.valueOf(pRowTableModel.getValueAt(rowNr, 0)));
      

      for (int colNr = 0; colNr < contentTableModel.getColumnCount(); colNr++)
      {
        Object value = contentTableModel.getValueAt(rowNr, colNr);

        cell = row.createCell(colNr + 1);
        cell.setCellStyle(styles.get(Styles.Standard));
        cell.setCellValue(parseData(value));
      }
    }

    // Spaltenbreite automatisch anpassen
    for (int colNr = 0; colNr < contentTableModel.getColumnCount(); colNr++)
    {
      sheet.autoSizeColumn(colNr);
    }

    // Write the output to a file
    FileOutputStream fileOut = new FileOutputStream(pFile);
    wb.write(fileOut);
    fileOut.close();
  }
  
  
  /**
   * @param pValue
   * @return
   */
  private String parseData(Object pValue)
  {
    String retVal = "";
    
    if (pValue instanceof String)
    {
      retVal = pValue != null ? (String) pValue : "";
    }
    else
    {
      if (pValue != null)
      {
        ValueParser valueParser = registerMap.get(pValue.getClass());
        
        if (valueParser != null)
        {
          retVal = valueParser.parseValue(pValue);
        }
        else
        {
          retVal = String.valueOf(pValue);
        }
      }
    }
    
    return retVal;
  }

  /**
   * 
   * @param pKey
   * @param pParser
   */
  public static void registerValueParser(Class pKey, ValueParser pParser)
  {
    registerMap.put(pKey, pParser);
  }
}
