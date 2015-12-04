package com.team4.until;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
	

	public static <T> boolean getExcel(List<T> list,List<String> listName,List<String> thead,String date,String name,String title,String fileTitle){
		if(list == null){
			return false;
		}
		//Excel中对应的列数有限制为short
		String value = null;
		//创建webbook
		HSSFWorkbook wb = new HSSFWorkbook();
		//在webbook中添加一个sheet
		HSSFSheet sheet = wb.createSheet(title);
		//创建居中格式
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//创建Excel表第0-2行
		HSSFRow row0 = sheet.createRow(0);
		HSSFRow row1 = sheet.createRow(1);
		HSSFRow row2 = sheet.createRow(2);
		HSSFRow row = null;
		//第0-2行分别为表名、时间、操作员
		HSSFCell cell = row0.createCell((short)0);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		cell = row0.createCell((short)1);
		cell.setCellValue(date);
		cell.setCellStyle(style);
		cell = row1.createCell((short)0);
		cell.setCellValue("员工");
		cell.setCellStyle(style);
		cell = row1.createCell((short)1);
		cell.setCellValue(name);
		cell.setCellStyle(style);
		cell = row2.createCell((short)0);
		cell.setCellValue("操作名称");
		cell.setCellStyle(style);
		cell = row2.createCell((short)1);
		cell.setCellValue(title);
		//第3行为表头
		HSSFRow row3 = sheet.createRow(3);
		cell = row3.createCell((short)0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);
		for(int i = 0;i<thead.size();i++){
			cell = row3.createCell((short)i+1);
			cell.setCellValue(thead.get(i));
			cell.setCellStyle(style);
		}
		//第4行开始为表格数据
		try {
			for(int i = 0;i<list.size();i++){
				row = sheet.createRow(i+4);
				cell = row.createCell((short)0);
				cell.setCellValue(i+1);
				cell.setCellStyle(style);
				for(int j = 0;j<listName.size();j++){
					value = BeanUtils.getProperty(list.get(i), listName.get(j));
					cell = row.createCell((short)j + 1);
					cell.setCellValue(value);
					cell.setCellStyle(style);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		row = sheet.createRow(list.size()+4);
		cell = row.createCell(0);
		cell.setCellValue("总计:");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue(String.valueOf(list.size()) + "条数据");
		cell.setCellStyle(style);
		try {
			FileOutputStream fout = new FileOutputStream("D:/" + fileTitle + ".xls");
			wb.write(fout);
			fout.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
