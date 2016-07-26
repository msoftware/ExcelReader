/*
package skubalaw.annotation;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import skubalaw.excelparser.Group;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by adamczykp on 25.07.2016.
 *//*

@Setter
@Getter
public class Pesel {
    private int pesel;
    List<0> listOfAddress;


    public Pesel(Workbook wb, Sheet sheet, List<Group> listOfDataColumns, int dataRowNumber) {
        listOfAddress = new ArrayList<>();
        //boolean firstEntry=true;
        int rowNumberOfColumn = dataRowNumber - 1;
        for (int i = dataRowNumber; i < listOfDataColumns.get(0).getDataList().size() + dataRowNumber; i++) {
            Adres adres = new Adres();
            for (int j = 0; j < 5; j++) {

                Cell c = sheet.getRow(i).getCell(j);
                if (sheet.getRow(rowNumberOfColumn).getCell(j).getStringCellValue().equals("ulica")) {
                    adres.setSName(c.getStringCellValue());
                } else if (sheet.getRow(rowNumberOfColumn).getCell(j).getStringCellValue().equals("number Domu")) {
                    adres.setHouseNumber((int) c.getNumericCellValue());
                } else if (sheet.getRow(rowNumberOfColumn).getCell(j).getStringCellValue().equals("imie")) {
                    adres.setFirstName(c.getStringCellValue());
                } else if (sheet.getRow(rowNumberOfColumn).getCell(j).getStringCellValue().equals("nazwisko")) {
                    adres.setSName(c.getStringCellValue());
                }


                if (c.getCellType() == Cell.CELL_TYPE_BLANK && sheet.getRow(rowNumberOfColumn).getCell(j).getStringCellValue().equals("pesel")) {
                } else if (c.getCellType() != Cell.CELL_TYPE_BLANK && sheet.getRow(rowNumberOfColumn).getCell(j).getStringCellValue().equals("pesel")) {
                    pesel = (int) c.getNumericCellValue();
                }
            }

            listOfAddress.add(adres);
            // if(firstEntry) {
            //   firstEntry=false;
            //listOfAddress.clear();
            //}
        }


    }

    public Pesel() {

    }
}
*/
