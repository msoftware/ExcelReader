package skubalaw.excelparser;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

/**
 * Created by skubalaw on 2016-07-11.
 */
public enum ChangingTypeEnum {

    DATA {

    },
    STRING {
        public <T> List<T> test(List<T> t, Sheet sheet, int columnNumber, int sizeList, int rowNum) {
            Object o = (T) sheet.getRow(rowNum).getCell(columnNumber).getStringCellValue();
            rowNum++;
            t.add((T) o);
            return t;
        }


    },
    NUMERIC {
        @Override
        public <G> List<G> test(List<G> t, Sheet sheet, int columnNumber, int sizeList, int rowNum) {

            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

            System.out.println("weszlo do numeric ");

            Object o = sheet.getRow(sizeList).getCell(columnNumber).getNumericCellValue();
            t.add((G) o);


            return t;
        }
    },
    BOOLEAN {
        @Override
        public <T> List<T> test(List<T> t, Sheet sheet, int columnNumber, int sizeList, int rowNum) {
            Object o = sheet.getRow(sizeList).getCell(columnNumber).getBooleanCellValue();
            t.add((T) o);

            return t;
        }
    },
    ERROR {

    };


    public <T> List<T> test(List<T> t, Sheet sheet, int columnNumber, int sizeList, int rowNum) {
        return t;
    }

}