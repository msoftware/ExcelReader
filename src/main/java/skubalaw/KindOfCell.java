package skubalaw;

/**
 * Created by skubalaw on 2016-07-11.
 */
public enum KindOfCell {

    GROUP(1), DATA(2), REQUIRE_DATAP(3);


    int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    KindOfCell(int i) {
        this.i=i;
    }
}
