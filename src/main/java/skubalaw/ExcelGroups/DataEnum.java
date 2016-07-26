package skubalaw.ExcelGroups;

/**
 * Created by adamczykp on 25.07.2016.
 */
public enum DataEnum {
    PESEL("pesel",0),ULICA("ulica",1), NUMERDOMU("numerDomu",2), IMIE("imie",3), NAZWISKO("nazwisko",4);


    String value;

    public int getNumRow() {
        return numRow;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }

    int numRow;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    DataEnum(String nazwisko, int numRow) {
        this.numRow=numRow;
        this.value=nazwisko;
    }
}
