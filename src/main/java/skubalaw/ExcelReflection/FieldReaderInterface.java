package skubalaw.ExcelReflection;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by adamczykp on 28.07.2016.
 */
public interface FieldReaderInterface {
    Workbook wb=new XSSFWorkbook();
    Sheet sheet=wb.createSheet("test");
}
