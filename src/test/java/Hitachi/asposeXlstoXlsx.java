package Hitachi;

import com.aspose.cells.Workbook;

public class asposeXlstoXlsx {
	public static void main(String[] args) throws Exception {
		Workbook workbook = new Workbook("C:\\Users\\User\\Downloads\\test1\\Test.xls");
		workbook.save("C:\\Users\\User\\Downloads\\test1\\Test.pdf");

	}
}
