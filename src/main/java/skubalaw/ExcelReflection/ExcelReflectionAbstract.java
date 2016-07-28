package skubalaw.ExcelReflection;

import skubalaw.excelparser.Group;

import java.util.List;

/**
 * Created by adamczykp on 27.07.2016.
 */
public abstract class ExcelReflectionAbstract {
    int firstElementOfList;
    int lastElementOfList;


    abstract Object listSwapper(Class newClass, int firstElementOfList, int lastElementOfList);
}
