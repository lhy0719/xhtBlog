package com.lhy.commons.excel;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * @author 张小彬
 * Excel处理工具,每个sheet第一行为表格头,数据处理为String返回
 */
public final class ExcelDataSetUtils {
	private static String getCellValueAsString(Cell cell,String numberPattern,String datePattern){
		int cellType=cell.getCellType();
		String value="";
		switch (cellType){
			case Cell.CELL_TYPE_BLANK:
			case Cell.CELL_TYPE_ERROR:
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value=Boolean.toString(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_NUMERIC:			
				DecimalFormat dm=new DecimalFormat(numberPattern);
				value=DateUtil.isCellDateFormatted(cell)?DateFormatUtils.format(cell.getDateCellValue(), datePattern):dm.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				value=cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				value=cell.getCellFormula();
				break;
		}
		return value;
	}
	private static Object getCellValue(Cell cell){
		int cellType=cell.getCellType();
		Object value=null;
		switch (cellType){
			case Cell.CELL_TYPE_BLANK:
			case Cell.CELL_TYPE_ERROR:
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value=cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:	
				value=DateUtil.isCellDateFormatted(cell)?cell.getDateCellValue():cell.getNumericCellValue();
				break;
			case Cell.CELL_TYPE_STRING:
				value=cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_FORMULA:
				value=cell.getCellFormula();
				break;
		}
		return value;
	}
	private static Map<Integer,String> getFirstRow(Sheet sheet){
		Map<Integer,String> columnNames=new HashMap<Integer,String>();				
		Row firstRow=sheet.getRow(sheet.getFirstRowNum());
		Iterator<Cell> ite=firstRow.cellIterator();
		while(ite.hasNext()){
			Cell cell=ite.next();
			String value=cell.getStringCellValue();
			int columnIndex=cell.getColumnIndex();
			columnNames.put(columnIndex, value);
		}
		return columnNames;
	}
	/** 按照行处理excel数据,通常应用于数据比较多的场景,datePattern和numberPattern都不为空时返回对象类型,否则处理成String;实现自己的ISheetRowProcessor,完成每行的处理逻辑
	 * @param excelFile 要处理的excel文件
	 * @param datePattern 处理日期时的转换格式
	 * @param numberPattern 处理数字时转换格式
	 * @param processor 行回调处理器
	 */
	public static void processExcel(File excelFile,String datePattern,String numberPattern,ISheetRowProcessor processor){
		Boolean objectValue=StringUtils.isBlank(datePattern)||StringUtils.isBlank(numberPattern);
		try {
			Workbook wb=WorkbookFactory.create(excelFile);
			int sheetCount=wb.getNumberOfSheets();
			for (int i=0;i<sheetCount;i++){
				Sheet sheet=wb.getSheetAt(i);
				String tableName=sheet.getSheetName();				
				Map<Integer,String> columnNames=getFirstRow(sheet);				
				for (int num=sheet.getFirstRowNum()+1;num<sheet.getPhysicalNumberOfRows();num++){					
					Row row=sheet.getRow(num);
					Map<String,Object> values=new HashMap<String,Object>();
					for (Integer index:columnNames.keySet()){
						Cell cell=row.getCell(index);
						values.put(columnNames.get(index),objectValue?getCellValue(cell):getCellValueAsString(cell,numberPattern,datePattern));
					}
					processor.processRow(tableName,values);
				}
			}
		} catch (InvalidFormatException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/** 处理excel数据,通常应用于数据比较少的场景,datePattern和numberPattern都不为空时返回对象类型,否则处理成String
	 * @param excelFile 要处理的excel文件
	 * @param datePattern 处理日期时的转换格式
	 * @param numberPattern 处理数字时转换格式
	 * @return Map<String,DataSet> key对应sheetName
	 */
	public static Map<String,DataSet> convertExcel(File excelFile,String numberPattern,String datePattern) {
		Map<String,DataSet> returnMap=new HashMap<String,DataSet>();
		Boolean objectValue=StringUtils.isBlank(datePattern)||StringUtils.isBlank(numberPattern);
		try {
			Workbook wb=WorkbookFactory.create(excelFile);
			int sheetCount=wb.getNumberOfSheets();
			for (int i=0;i<sheetCount;i++){
				Sheet sheet=wb.getSheetAt(i);
				String tableName=sheet.getSheetName();				
				DataSet ds=new ExcelDataSetUtils().new DataSet();
				ds.setTableName(tableName);				
				Map<Integer,String> columnNames=getFirstRow(sheet);	
				ds.setColumnNames(columnNames);
				List<Map<String,Object>> rows=new LinkedList<Map<String,Object>>();
				for (int num=sheet.getFirstRowNum()+1;num<sheet.getPhysicalNumberOfRows();num++){					
					Row row=sheet.getRow(num);
					Map<String,Object> values=new HashMap<String,Object>();
					for (Integer index:columnNames.keySet()){
						Cell cell=row.getCell(index);
						values.put(columnNames.get(index), objectValue?getCellValue(cell):getCellValueAsString(cell,numberPattern,datePattern));
					}
					rows.add(values);
				}
				ds.setRows(rows);
				returnMap.put(tableName, ds);
			}
		} catch (InvalidFormatException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return returnMap;
	}
	public final class DataSet{
		private String tableName;
		private Map<Integer,String> columnNames;
		private List<Map<String,Object>> rows;
		public List<Map<String, Object>> getRows() {
			return rows;
		}

		public void setRows(List<Map<String, Object>> rows) {
			this.rows = rows;
		}

		public Map<Integer,String> getColumnNames() {
			return columnNames;
		}

		public void setColumnNames(Map<Integer,String> columnNames) {
			this.columnNames = columnNames;
		}

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
	}	
}
