import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.FontRecord;
import jxl.read.biff.BiffException;
import jxl.read.biff.Record;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Font;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.WritableFont.FontName;
import jxl.write.biff.RowsExceededException;

public class WriteExcel {

	private Workbook existingWorkbook;
	private WritableWorkbook workbook;
	private WritableSheet excelSheet;
	private WritableCell c;

	private WritableCellFormat cellFormat;
	private static WriteExcel single_instance = null;
	
	public static final String writeFile = "C:\\temp\\remanent_start.xls";
	/*
	 *
	 * 
	 * WritableWorkbook workbookCopy = Workbook.createWorkbook(new
	 * File("output.xls"), existingWorkbook); WritableSheet sheetToEdit =
	 * workbookCopy.getSheet(sheetName);
	 * 
	 */
	private WriteExcel() {

		try {
			
			existingWorkbook = Workbook.getWorkbook(new File(writeFile));
			workbook = Workbook.createWorkbook(new File("output.xls"), existingWorkbook);
			excelSheet = workbook.getSheet("Remanent");
			
			cellFormat = new WritableCellFormat(new WritableFont(WritableFont.createFont("Calibri"),12));
			cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.BLACK);
			cellFormat.setBackground(Colour.YELLOW);
			
			/*
			 * c = excelSheet.getWritableCell(0, 0);
			 * 
			 * times = new WritableCellFormat(); times.setBackground(Colour.AQUA);
			 * 
			 * 
			 * WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
			 * WorkbookSettings wbSettings = new WorkbookSettings();
			 * 
			 * wbSettings.setLocale(new Locale("en", "EN"));
			 * 
			 * times.setWrap(true);
			 */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public void write(int column, int row, Integer data) throws IOException,
	 * WriteException {
	 * 
	 * fillExcel(excelSheet, column, row, data); workbook.write();
	 * 
	 * }
	 */

	public void writeString(int column, int row, String data) throws IOException, WriteException {

		excelSheet.addCell(new Label(column, row, data,cellFormat));

	}

	public void writeInt(int column, int row, int data) throws IOException, WriteException {

		excelSheet.addCell(new Number(column, row, data, cellFormat));

	}

	public void close() {
		try {
			workbook.write();
			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static WriteExcel getInstance() {
		if (single_instance == null)
            single_instance = new WriteExcel();
 
        return single_instance; 	
	}
}