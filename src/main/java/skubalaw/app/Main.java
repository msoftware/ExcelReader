/**
 * Created by skubalaw on 2016-07-11.
 */
package skubalaw.app;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import skubalaw.KindOfCell;
import skubalaw.cellObjects.Builder;
import skubalaw.excelparser.*;
import skubalaw.xmlStyle.Style;
import skubalaw.xmlStyle.XmlStyleChooser;
import skubalaw.xmlStyle.XmlStyleChooserI;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.WatchEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Main {

    public static void main(String[] args) throws ParseException {
        try {

            int rowDataColNum = 3;

            //przechowywany obiekt
            ExcRead exc = new ExcRead("adres1.xlsx", rowDataColNum, 0);

            //dostep do danego wiersza
            Wiersze.Wiersz1.MAIN2.getUpg3().naglowek10();//wpis do listy//importatnt!
            Wiersze.Wiersz1.MAIN2.getUpg3().naglowek11();
            Wiersze.Wiersz1.none.getUpg2().dowGr3().naglowek5();

            Object s = new Builder().getGroup(Wiersze.list).getData(exc.getAllDataColumns(), 0);

             ExcWriter excWriter = new ExcWriter(exc.getAllGroups(), exc.getAllDataColumns(), rowDataColNum, "testowe1.xlsx");
            excWriter.choosedList(Wiersze.list);
            excWriter.write();



        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
