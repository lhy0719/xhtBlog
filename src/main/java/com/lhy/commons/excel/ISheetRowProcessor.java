package com.lhy.commons.excel;

import java.util.Map;

public interface ISheetRowProcessor {

	/** 处理excel表格的数据行
	 * @param sheetName 当前处理的excel表的表格名,一般对应数据表
	 * @param cellValues 当前处理的行,key为字段名,value为字段值
	 */
	void processRow(String sheetName, Map<String, Object> cellValues);

}
