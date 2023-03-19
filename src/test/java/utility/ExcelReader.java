package utility;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    private static final String TEST_DATA_WORKBOOK_PATH = "./src/test/resources/testdata/testdataproviders5.xlsx";
    private static Workbook book;
    private static Sheet sheet;

    public static Object[][] getTestData(String sheetname){
        Object[][] data = null;

        try{
            FileInputStream fis = new FileInputStream(TEST_DATA_WORKBOOK_PATH);
            book = WorkbookFactory.create(fis);
            sheet = book.getSheet(sheetname);

            data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    data[i][j] =sheet.getRow(i + 1).getCell(j).toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
