package skubalaw.xmlStyle;

import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import skubalaw.KindOfCell;

import javax.xml.bind.annotation.*;

/**
 * Created by adamczykp on 20.07.2016.
 */
@XmlRootElement(name = "test")
public class Style {

    @XmlElement
    public short getFontSize() {
        return fontSize;
    }

    public void setFontSize(short fontSize) {
        this.fontSize = fontSize;
    }

    @XmlElement
    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    @XmlElement
    public short getBorder() {
        return border;
    }

    public void setBorder(short border) {
        this.border = border;
    }

    @XmlElement
    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    short fontSize;
    String fontStyle;
    short border;
    String bgColor;
    boolean underLine;
    AligmentEnum aligment;


    @XmlElement
    public KindOfCell getCell() {
        return cell;
    }

    public void setCell(KindOfCell cell) {
        this.cell = cell;
    }

    KindOfCell cell;

    @XmlElement
    public AligmentEnum getAligment() {
        return aligment;
    }

    public void setAligment(AligmentEnum aligment) {
        this.aligment = aligment;
    }

    @XmlElement
    public String getFontName() {
        return fontName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    String fontName;

    @XmlElement
    public boolean isUnderLine() {
        return underLine;
    }

    public void setUnderLine(boolean underLine) {
        this.underLine = underLine;
    }

    @XmlElement
    public boolean isItalic() {
        return italic;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    boolean italic;

    @XmlElement
    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    boolean bold;


    @XmlAttribute
    public String getNameOfStyle() {
        return nameOfStyle;
    }

    public void setNameOfStyle(String nameOfStyle) {
        this.nameOfStyle = nameOfStyle;
    }

    String nameOfStyle;
}
