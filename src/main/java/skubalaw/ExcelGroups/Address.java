package skubalaw.ExcelGroups;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by adamczykp on 25.07.2016.
 */
@Setter
@Getter
public class Address {

    @DaneAnnotation(value = DataEnum.ULICA)
   public List<String> ulica;
    @DaneAnnotation(value = DataEnum.NUMERDOMU)
    public List<Integer> numerDomu;
    @DaneAnnotation(value = DataEnum.IMIE)
    public List<String> imie;
    @DaneAnnotation(value = DataEnum.NAZWISKO)
    public List<String> nazwisko;
   // @DaneAnnotation(value = DataEnum.JAKIES)
   // public List<String> jakies;

}
