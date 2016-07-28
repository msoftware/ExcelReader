package skubalaw.ExcelReflection;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamczykp on 28.07.2016.
 */
public class FieldReader implements ExcelReflectionInterface, FieldReaderInterface {
    String fileName;

    public FieldReader(String fileName, int amountColumns) {
        this.fileName = fileName;
        List<?> stream = new ArrayList<>();
        int rowCreator = 0;
        Row row = null;
        int i=0;
        int j=0;
        try {
            for (Object object :
                    tmpObjectAddressList) {
                for (Field field :
                        object.getClass().getDeclaredFields()) {
                    row = sheet.createRow(rowCreator);
                    rowCreator++;
                }
                for (Field field :
                        object.getClass().getDeclaredFields()) {

                    List<?> listOfFields = new ArrayList<>();
                    listOfFields = (List<?>) field.get(object);

                       // for (Object objectList :
                        //        listOfFields) {

                    System.out.println(listOfFields);
                            for(int k=0;k<listOfFields.size();k++)
                            {
                             //   System.out.println(listOfFields.size() + "SIZE");
                               // System.out.println(objectList);
                                Cell c = sheet.createRow(i).createCell(k);
                                if(listOfFields.get(k) instanceof String)
                                {
                                    c.setCellValue((String) listOfFields.get(k));

                                }
                                if(listOfFields.get(k) instanceof Double)
                                {
                                    c.setCellValue((Double) listOfFields.get(k));

                                }
                                System.out.println("------");
//                            }


                    }
                    i++;
                    j++;
                }
               // j=0;
               // i=0;
                FileOutputStream fileOut = new FileOutputStream(fileName);
                wb.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }


}

