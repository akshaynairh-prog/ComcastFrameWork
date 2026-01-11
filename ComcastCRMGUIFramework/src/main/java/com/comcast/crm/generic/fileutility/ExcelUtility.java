package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
public String getDataFromExcel(String sheetname, int rownum, int cellnum) throws EncryptedDocumentException, IOException {
	FileInputStream fis1=new FileInputStream("./testdata/OrgContactTestscriptdata.xlsx");
//	FileInputStream fis1=new FileInputStream("C:\\Users\\Akshay\\eclipse-workspace\\ComcastCRMGUIFramework\\MockTestData\\Mock Test Data.xlsx");
	Workbook wb=WorkbookFactory.create(fis1);
//	wb.close();
	return(wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue());

}
public int getRowCount(String sheetname) throws EncryptedDocumentException, IOException {
	FileInputStream fis2=new FileInputStream("./testdata/OrgContactTestscriptdata.xlsx");
//FileInputStream fis2=new FileInputStream("C:\\Users\\Akshay\\eclipse-workspace\\ComcastCRMGUIFramework\\MockTestData\\Mock Test Data.xlsx");
	Workbook wb=WorkbookFactory.create(fis2);
	wb.close();
	return(wb.getSheet(sheetname).getLastRowNum());
}
}
