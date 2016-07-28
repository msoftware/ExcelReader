package skubalaw.ExcelReflection;

import skubalaw.excelparser.ExcWriterAbstract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamczykp on 28.07.2016.
 */
public interface ExcelReflectionInterface {
    List<Object> tmpObjectAddressList = new ArrayList<>();//address list object
    List<Object> tmpObjectPeselList = new ArrayList<>();//pesel list object
    List<List> tmpPeselList = new ArrayList<>();
    List<List> listOfData = new ArrayList<>();
    List<Integer> maxAmountOfGroups = new ArrayList<>();
}
