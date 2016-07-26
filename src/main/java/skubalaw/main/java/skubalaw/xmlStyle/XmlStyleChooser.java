package skubalaw.xmlStyle;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import skubalaw.KindOfCell;
import skubalaw.excelparser.ExcRead;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.awt.Color;


/**
 * Created by adamczykp on 20.07.2016.
 */
@Setter
@Getter
public class XmlStyleChooser implements XmlStyleChooserI {
    private CellStyle fontStyle;
    private short fontSize;//done
    private String font;
    private short border;//done
    private String bgColor;//done
    private Workbook wb;//done
    private JAXBContext context;//done
    private String file;
    private Childs foo;

    public XmlStyleChooser(Workbook wb) {
        this.wb = wb;


      /*  //new
        try {
            this.context = JAXBContext.newInstance(Childs.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Style styl = new Style();
        styl.setBgColor("");
        styl.setBorder(XSSFCellStyle.BORDER_DOTTED);
        styl.setFontSize((short) 12);
        styl.setBold(false);
        styl.setItalic(true);
        styl.setCell(KindOfCell.GROUP);

        //middle-2 left-0, right-3,
        Style styl2=new Style();
        styl2.setBgColor("BLUE");
        styl2.setBorder(XSSFCellStyle.BORDER_DOTTED);
        styl2.setFontSize((short) 8);
        styl2.setBold(false);
        styl2.setItalic(false);
        styl2.setCell(KindOfCell.DATA);


        Style styl3=new Style();
        styl3.setBgColor("RED");;
        styl3.setBorder(XSSFCellStyle.BORDER_DOTTED);
        styl3.setFontSize((short) 15);
        styl3.setBold(true);
        styl3.setItalic(false);
        styl2.setCell(KindOfCell.DATA);


        Childs childs = new Childs();
        childs.setStyle(styl);
        childs.setStyle2(styl2);
        childs.setStyle3(styl3);

        //pobranie obiektu i zapis do pliku text.xml
        JAXBElement<Childs> jaxbElement = new JAXBElement(new QName(Childs.class.getSimpleName()), Childs.class, childs);
        StringWriter writer = new StringWriter();
        try {
            JAXBContext context = JAXBContext.newInstance(Childs.class);

            context.createMarshaller().marshal(jaxbElement, writer);
            Marshaller marshaller = this.context.createMarshaller();
            marshaller.marshal(childs, new File("Style1.xml"));
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/


        //  readXmlFile();
    }

    //changing style (xml)

    public Style getStyle(int whichStyle) {
        Style s;
        switch (whichStyle) {
            case 1:
                s = foo.getStyle();
                break;
            case 2:
                s = foo.getStyle3();
                break;
            case 3:
                s = foo.getStyle3();
                break;
            default:
                s = foo.getStyle();
                break;
        }
        return s;
    }

    //selecting file
    public CellStyle readXmlFile() {
        try {
            this.context = JAXBContext.newInstance(Childs.class);
            file = "Style1.xml";
            Source source = new StreamSource(file);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<Childs> root = unmarshaller.unmarshal(source, Childs.class);


            foo = root.getValue();

            return getCellStyle(getStyle(1));


        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }


    }


    public CellStyle readXmlFile(int i) {
        try {
            this.context = JAXBContext.newInstance(Childs.class);
            file = "Style1.xml";
            Source source = new StreamSource(file);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement<Childs> root = unmarshaller.unmarshal(source, Childs.class);


            foo = root.getValue();

            return getCellStyle(getStyle(2));


        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }


    }


    public CellStyle getCellStyle(Style style) {
        String color = style.getBgColor();

//CellStyle
        fontStyle = wb.createCellStyle();
        //color
        try {
            if (color == "" || color == null) {

            } else {
                int[] intARGB = new int[4];

                intARGB[0] = Integer.valueOf(color.substring(1, 3), 16); // alpha
                intARGB[1] = Integer.valueOf(color.substring(3, 5), 16); // red
                intARGB[2] = Integer.valueOf(color.substring(5, 7), 16); // green
                intARGB[3] = Integer.valueOf(color.substring(7), 16); // blue

                HSSFWorkbook hwb = new HSSFWorkbook();
                HSSFPalette palette = hwb.getCustomPalette();
                HSSFColor myColor = palette.findSimilarColor(intARGB[1], intARGB[2], intARGB[3]);

                short palIndex = myColor.getIndex();

                fontStyle.setFillForegroundColor(palIndex);
                fontStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

            }
        } catch (Exception e) {

        }

        //border
        fontStyle.setBorderBottom(style.getBorder());
        fontStyle.setBorderTop(style.getBorder());
        fontStyle.setBorderLeft(style.getBorder());
        fontStyle.setBorderRight(style.getBorder());

        //fontSize
        Font txtFont = wb.createFont();
        short fontSize = style.getFontSize();
        if (fontSize > 0) {
            txtFont.setFontHeightInPoints(fontSize);
        } else {
            txtFont.setFontHeightInPoints((short) 12);
        }

        //bold
        if (style.isBold()) {
            txtFont.setBold(true);
        }

        //italic
        if (style.isItalic()) {
            txtFont.setItalic(true);
        }

        //underline
        if (style.isUnderLine()) {
            txtFont.setUnderline(Font.U_SINGLE);
        }

        //font name
        if (style.getFontName() != "" || style.getFontName() != null) {
            txtFont.setFontName(style.getFontName());
        }

        //aligment
        if (style.getAligment() != null) {
            fontStyle.setAlignment((short) style.getAligment().getAligment());
        }


        fontStyle.setFont(txtFont);
        return fontStyle;

    }

}
