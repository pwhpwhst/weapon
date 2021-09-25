package com.pwhTest.hadoopTest2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HadoopTest{
	public static void main(String args[]) throws InterruptedException, IOException{
		
//		String csvFile="export.csv";
		String csvFile=args[0];
		FileInputStream fis = null;
		String fileContent="";
		  try {
			   File file = new File(csvFile);
			   fis = new FileInputStream(file);
			   
	            InputStreamReader reader = new InputStreamReader(fis,"UTF-8");
	            BufferedReader br = new BufferedReader(reader);
	            String line;
	            while ((line = br.readLine()) != null) {
	                fileContent+=line+"\n";
	            }
	            br.close();
	            reader.close();
		  }catch (Exception e1) {
			  e1.printStackTrace();
		  }
		  
		  String[] arr=fileContent.split("\n");
		
		 List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		for(int i1=1;i1<arr.length;i1++) {
			Map<String,String> map=new HashMap<String,String>();
			map.put("en", arr[i1].split(",")[0]);
			map.put("ch", arr[i1].split(",")[1]);
			list.add(map);
		}
		List<Map<String,String>> randomLlist=new ArrayList<Map<String,String>>();
		randomLlist.addAll(list);
		Collections.shuffle(randomLlist);
		
	       Workbook wb = new XSSFWorkbook();
	       Sheet sheet = wb.createSheet("原");
	       Row row = sheet.createRow(0);
	       Cell enCell = row.createCell(0);
	       Cell chCell = row.createCell(1);
	       enCell.setCellValue("英");
	       chCell.setCellValue("中");
	       for(int i1=0;i1<list.size();i1++){
	    	   row = sheet.createRow(i1+1);
		        enCell = row.createCell(0);
		        chCell = row.createCell(1);
		       enCell.setCellValue(list.get(i1).get("en"));
		       chCell.setCellValue(list.get(i1).get("ch"));
	       }
	       
	       sheet = wb.createSheet("乱");
	        row = sheet.createRow(0);
	        enCell = row.createCell(0);
	        chCell = row.createCell(1);
	       enCell.setCellValue("英");
	       chCell.setCellValue("中");
	       for(int i1=0;i1<randomLlist.size();i1++){
	    	   row = sheet.createRow(i1+1);
		        enCell = row.createCell(0);
		        chCell = row.createCell(1);
		       enCell.setCellValue(randomLlist.get(i1).get("en"));
		       chCell.setCellValue(randomLlist.get(i1).get("ch"));
	       }
	       
	       
	       sheet = wb.createSheet("英");
	        row = sheet.createRow(0);
	        enCell = row.createCell(0);
	       enCell.setCellValue("英");
	       for(int i1=0;i1<randomLlist.size();i1++){
	    	   row = sheet.createRow(i1+1);
		        enCell = row.createCell(0);
		       enCell.setCellValue(randomLlist.get(i1).get("en"));
	       }
	       
	       sheet = wb.createSheet("中");
	        row = sheet.createRow(0);
	        chCell = row.createCell(0);
	        chCell.setCellValue("中");
	       for(int i1=0;i1<randomLlist.size();i1++){
	    	   row = sheet.createRow(i1+1);
		        enCell = row.createCell(0);
		       enCell.setCellValue(randomLlist.get(i1).get("ch"));
	       }
	       

	       FileOutputStream fos = new FileOutputStream("../shanbayExt.xlsx");
	       wb.write(fos);
	       fos.close();
	       System.out.println("创建完成");

	}
}