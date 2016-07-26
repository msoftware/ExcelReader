package skubalaw.cellObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Adam on 10.07.2016.
 */
@AllArgsConstructor
public class MergedRegionParam {
    Integer x;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {

        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    Integer y;
}
