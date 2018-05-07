import java.io.File;
import java.io.IOException;
import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {

	private File tamplateSheet = null; //new File("src/remanent_start.xls");

	private Workbook w;
	private Cell alkoholName, bootleCount, mlCount;
	

	public ReadExcel(String fileName) {
		tamplateSheet = new File(fileName);
	}

	public void read() throws IOException {

		try {
			w = Workbook.getWorkbook(tamplateSheet);
			Sheet sheet = w.getSheet(0);

			for (int j = 0; j < sheet.getColumns(); j++) {
				for (int i = 0; i < sheet.getRows(); i++) {
					alkoholName = sheet.getCell(j, i);

					if (alkoholName.getType() == CellType.LABEL) {
						System.out.println("I got a label " + alkoholName.getContents());
					}

					if (alkoholName.getType() == CellType.NUMBER) {
						System.out.println("I got a number " + alkoholName.getContents());
					}

				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public void readColumnInRange(int column, int start, int stop) throws IOException {

		try {
			w = Workbook.getWorkbook(tamplateSheet);
			Sheet sheet = w.getSheet(0);
			for (int i = start; i < stop; i++) {
				alkoholName = sheet.getCell(column, i);
				System.out.println(" " + alkoholName.getContents());

			}
		} catch (BiffException e) {
			e.printStackTrace();
		}
	}

	public Object[][] makeListOfCategorizedAlcohol(int start, int stop) throws IOException {
		
		int count = stop-start;
		Object[][] tmp = new Object[count][3];
		try {
			w = Workbook.getWorkbook(tamplateSheet);
			Sheet sheet = w.getSheet(0);			
			
			for (int i = 0; i < count; i++) {
				try {
					alkoholName = sheet.getCell(0, start+i);
					tmp[i][0] = alkoholName.getContents();

					bootleCount = sheet.getCell(3, start+i);
					tmp[i][1] = bootleCount.getContents();
					
					
					mlCount = sheet.getCell(4, start+i);
					tmp[i][2] = mlCount.getContents();
					
					
				} catch (NumberFormatException nfe) {
					
				}
			}

		} catch (BiffException e) {
			e.printStackTrace();
		}
		return tmp;
	}
}