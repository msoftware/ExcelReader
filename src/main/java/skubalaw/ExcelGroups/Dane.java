package skubalaw.ExcelGroups;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by adamczykp on 25.07.2016.
 */
@Setter
@Getter
public class Dane {
    @ColumnAnnotation(value=ColumnEnum.PESEL)
   public List<Pesel> dane;
    @ColumnAnnotation(value=ColumnEnum.ADDRESS)
    public List<Address> adres;

}
