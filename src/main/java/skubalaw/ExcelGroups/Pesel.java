package skubalaw.ExcelGroups;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by adamczykp on 25.07.2016.
 */
@Getter
@Setter
public class Pesel {
    @DaneAnnotation(value = DataEnum.PESEL)
   public List<Double> pesel;

}
