package com.generic.Actions;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.IOException;

//import org.apache.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class Excel {
	
	@SuppressWarnings("resource")
	public String[][] xlReadSheet(String sPath,String sheetName) throws Exception{
		File myXL=new File(sPath);
		FileInputStream myStream=new FileInputStream(myXL);
		XSSFWorkbook myWB = new XSSFWorkbook(myStream);
		XSSFSheet mysheet1=myWB.getSheet(sheetName);
		int xRows0=mysheet1.getLastRowNum()+1;
		int xCols0=mysheet1.getRow(0).getLastCellNum();
		String xData0[][]=new String[xRows0][xCols0];
		for(int i=0;i<xRows0;i++) {
			XSSFRow row0=mysheet1.getRow(i);
			for(short k=0;k<xCols0;k++) {
				XSSFCell cell0=row0.getCell(k);
				if(cell0 == null) {
					cell0=row0.createCell(k);
				}
				String value=celltoString(cell0);
				xData0[i][k]=value;
				
			}
		}
		return xData0;
			
		}
	public static String celltoString(XSSFCell cell) {
		int type=cell.getCellType();
		Object result;
		switch(type) {
		case XSSFCell.CELL_TYPE_NUMERIC://0
		     result=cell.getNumericCellValue();
		     break; 
		case XSSFCell.CELL_TYPE_STRING://1
		     result=cell.getStringCellValue();
		     break;
		case XSSFCell.CELL_TYPE_FORMULA://2
			throw new RuntimeException("We can't evaluate formulas in Java");
		case XSSFCell.CELL_TYPE_BLANK://3
			result="";
			break;
		case XSSFCell.CELL_TYPE_ERROR://5
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We dont support this cell type:"+ type);
		}
		return result.toString();
	}
		
	/*it ll create sheet if not exists
	 * 
	 * @param sPath	
	 * throws Exception
	 */
	public void removeSheet(String sPath,String sheetName) throws Exception {
		try {
		File myXL=new File(sPath);
		System.out.println(myXL.exists());
		if(!myXL.exists()) {
			
			FileOutputStream fos = new FileOutputStream(sPath);
			XSSFWorkbook workbook= new XSSFWorkbook();
			
			XSSFSheet sheet=workbook.createSheet();
			workbook.write(fos);
			fos.flush();
			fos.close();
		}
		FileInputStream myStream;
		XSSFWorkbook myWB=null;
	do {
		try {
			myStream = new FileInputStream(myXL);
			myWB = new XSSFWorkbook(myStream);
		}catch (Exception e) {
			System.out.println("ignore this is in xlRead -->" + e.getMessage());
			Thread.sleep(100);
		}
	}while(myWB==null);
	
	XSSFSheet mySheet1 = myWB.getSheet(sheetName);
	if(mySheet1!=null) {
		myWB.removeSheetAt(myWB.getSheetIndex(sheetName));
		FileOutputStream fos = new FileOutputStream(sPath);
		myWB.write(fos);
		fos.flush();
		fos.close();
	}
		}catch(Exception e) {
		System.out.println( e.getMessage());
	
		
		
	}
	}
	/*it ll create sheet if not exists
	 * 
	 * @param sPath	
	 * throws Exception
	 */
	
	/*Reading particula cell data based on column name and row num
	 * 
	 * @param sPath	
	 * throws Exception
	 */
	public String xlReadCell (String sPath,String sheetName,String columnName,int rowCount) throws Exception{
		boolean columnFound = false;
		String fileName = sPath;
		File file = new File(fileName);
		File sameFileName = new File(fileName);
		String expecteData = "";
		
		do {
			Thread.sleep(3);
			if(!file.renameTo(sameFileName))
				System.out.println("Please colose the " + fileName);
			
			
		}while(!file.renameTo(sameFileName));
		
		try {
			File myXL = new File(sPath);
			FileInputStream myStream;
			XSSFWorkbook myWB=null;
			do {
				try {
					myStream = new FileInputStream(myXL);
					myWB = new XSSFWorkbook(myStream);
				}catch (Exception e) {
					System.out.println("ignore this is in xlRead -->" + e.getMessage());
					Thread.sleep(100);
				}
			}while(myWB==null);			
			
			
			XSSFSheet mySheet1 = myWB.getSheet(sheetName);
			
			int xRows1,xCols1;
			xRows1 = mySheet1.getLastRowNum()+1;
			xCols1 = mySheet1.getRow(0).getLastCellNum();
			
			for(short k=0;k<xCols1;k++) {
				XSSFCell cell1=mySheet1.getRow(0).getCell(k);
				if(cell1 == null)
					cell1 = mySheet1.getRow(0).createCell(k);
				String value1=cell1.toString();
				if(value1.equalsIgnoreCase(columnName)) {
					columnFound=true;
					if(xRows1>=rowCount) {
						XSSFRow row=mySheet1.getRow(rowCount);
						if(row == null)
							row = mySheet1.createRow(rowCount);
						XSSFCell cell=row.getCell(k);	
						
						if(cell == null)
							cell=row.createCell(k);
						
						expecteData=cell.toString();
						
					}else {
						System.out.println("Please checj the row count");
					}
					break;
					
				}
			}
			if(!columnFound)
				System.out.println("Given column not found--"+ columnName);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return expecteData;
	}
		
		

public String[] xlReadColumn (String sPath,String sheetName,String columnName) throws Exception{
	boolean columnFound = false;
	int columnIndex= 0;
	
	String fileName = sPath;
	File file = new File(fileName);
	File sameFileName = new File(fileName);
	
	do {
		Thread.sleep(1);
		if(!file.renameTo(sameFileName))
			System.out.println("Please colose the " + fileName);
		
		
	}while(!file.renameTo(sameFileName));
	
	int xRows1,xCols1;
	String[] xData = {};
	try {
		File myXL = new File(sPath);
		FileInputStream myStream;
		XSSFWorkbook myWB=null;
		do {
			try {
				myStream = new FileInputStream(myXL);
				myWB = new XSSFWorkbook(myStream);
			}catch (Exception e) {
				System.out.println("ignore this is in xlRead -->" + e.getMessage());
				Thread.sleep(100);
			}
		}while(myWB==null);
		
		XSSFSheet mySheet1 = myWB.getSheet(sheetName);
		xRows1 = mySheet1.getLastRowNum()+1;
		xCols1 = mySheet1.getRow(0).getLastCellNum();
		
		for(short k=0;k<xCols1;k++) {
			XSSFCell cell1=mySheet1.getRow(0).getCell(k);
			if(cell1 == null)
				cell1 = mySheet1.getRow(0).createCell(k);
			String value1=cell1.toString();
			if(value1.equalsIgnoreCase(columnName)) {
				xData = new String[xRows1 - 1];
				columnFound =  true;
				columnIndex = k;
				break;
			}
		}
		if(!columnFound)
			System.out.println("Given column not Found --" + columnName);
		if(columnFound)
			for(int i=1;i<xRows1;i++) {
				XSSFRow row1=mySheet1.getRow(i);
				if(row1 == null)
					row1 = mySheet1.createRow(i);
				XSSFCell cell1=row1.getCell(columnIndex);
				if(cell1 == null)
					cell1 = row1.createCell(columnIndex);
				String value = cell1.toString();
				xData[i-1] = value;
			}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	
	return xData;
}

public int xlRead_GetRowCount(String xlPath,String sheetName,String columnName,String expectedData)throws Exception {
	
	int rowNum=0;
	try {
		String[] colData=xlReadColumn(xlPath,sheetName,columnName);
		for(int i=0;i<colData.length;i++) {
			if(colData[i].equalsIgnoreCase(expectedData)) {
				rowNum=i+1;
				break;
			}
		}
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	return rowNum;
}	

public static File file=null;
public void xlWriteData(String xlPath,String sheetName,String columnName,int rowNum,String cellData) throws Exception
{
	int colNum=0;
	boolean columnFound=false;
	String fileName = xlPath;
	
	do {
		if(file!=null) {
			Thread.sleep(200);
			System.out.println("Thread is waiting for file to be null");
		}
	}while(file !=null);
	file = new File(fileName);
	File sameFileName = file;
	do {
		Thread.sleep(10);
		if(!file.renameTo(sameFileName))
			System.out.println("Please colose the " + fileName);
	}while(!file.renameTo(sameFileName));
	
	FileInputStream myStream;
	XSSFWorkbook myWB=null;
	FileOutputStream fOut = null;
	do {
		try {
			myStream = new FileInputStream(file);
			myWB = new XSSFWorkbook(myStream);
			
			fOut= new FileOutputStream(file);
		}catch (Exception e) {
			System.out.println("ignore this is in xlRead -->" + e.getMessage());
			Thread.sleep(100);
		}
	}while(myWB==null);
	
	XSSFSheet oSheet=myWB.getSheet(sheetName);
	
	int xCols1 = oSheet.getRow(0).getLastCellNum();
	
	for(short k=0;k < xCols1; k++) {
		XSSFCell cell1=oSheet.getRow(0).getCell(k);
		if(cell1 == null)
			cell1 = oSheet.getRow(0).createCell(k);
		String value1=cell1.toString();
		if(value1.equalsIgnoreCase(columnName)) {
			columnFound=true;
			colNum=k;
			break;
		}
	
}
	if(!(rowNum == 0))
		if(columnFound) {
			XSSFRow row =oSheet.getRow(rowNum);
			if(row == null)
				row =oSheet.createRow(rowNum);
			XSSFCell cell=row.getCell(colNum);
			if(cell == null)
				cell =row.createCell(colNum);
			cell.setCellType(XSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(cellData);			
			
			
			
		}else {
			System.out.println("Given column not found--"+ columnName);
		}
	else
	{
		System.out.println("Hedders cont cheange");
	}
	try {
		myWB.write(fOut);
		fOut.flush();
		fOut.close();
	}catch(Exception e) {
		System.out.println(e.getMessage());
	}
	//methodToWrite(myWB,xlpath);
	file = null;

}
}







	                 
	 


