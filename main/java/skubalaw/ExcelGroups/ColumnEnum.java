package skubalaw.ExcelGroups;

/**
 * Created by adamczykp on 25.07.2016.
 */
public enum ColumnEnum {

    PESEL("dane"), ADDRESS("adres");


    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    ColumnEnum(String adres) {
        this.value=adres;
    }
}
