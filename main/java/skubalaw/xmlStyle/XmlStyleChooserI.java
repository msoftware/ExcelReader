package skubalaw.xmlStyle;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import javax.xml.bind.JAXBContext;

/**
 * Created by adamczykp on 22.07.2016.
 */
public interface XmlStyleChooserI {
    public CellStyle getCellStyle(Style style);
    Style getStyle(int whichStyle);
}
