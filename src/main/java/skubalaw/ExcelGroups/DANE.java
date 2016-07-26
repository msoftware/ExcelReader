package skubalaw.ExcelGroups;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by adamczykp on 25.07.2016.
 */
@Setter
@Getter
public class DANE {
    @ColumnAnnotation(value=ColumnEnum.PESEL)
   public List<PESEL> dane;
    @ColumnAnnotation(value=ColumnEnum.ADDRESS)
    public List<ADDRESS> adres;

}
