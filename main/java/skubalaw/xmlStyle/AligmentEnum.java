package skubalaw.xmlStyle;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by adamczykp on 21.07.2016.
 */
@XmlType(name = "aligmentEnum")
@XmlEnum
public enum AligmentEnum{
    LEFT(0),RIGHT(3),MIDDLE(2);

    AligmentEnum(int aligment) {
        this.aligment = aligment;
    }

    int aligment;

    public int getAligment() {
        return aligment;
    }

    public void setAligment(int aligment) {
        this.aligment = aligment;
    }
};
