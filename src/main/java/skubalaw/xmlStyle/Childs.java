package skubalaw.xmlStyle;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by adamczykp on 21.07.2016.
 */
@XmlRootElement
public class Childs {
    Style style;
    Style style2;
    Style style3;

    @XmlElement(name="styl2")
    public Style getStyle2() {
        return style2;
    }

    public void setStyle2(Style style2) {
        this.style2 = style2;
    }
    @XmlElement(name="styl3")
    public Style getStyle3() {
        return style3;
    }

    public void setStyle3(Style style3) {
        this.style3 = style3;
    }

    @XmlElement(name="styl1")
    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
