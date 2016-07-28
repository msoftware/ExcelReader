package skubalaw.ExcelReflection;

import skubalaw.ExcelGroups.*;
import skubalaw.excelparser.Group;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by adamczykp on 27.07.2016.
 */
public class ExcelReflection<T> extends ExcelReflectionAbstract implements ExcelReflectionInterface {

    List<Group> listOfDataColumns;
    private int amountOfPeselGroups;
    List<List> listOfAddress;
    List<T> listOfPesel;
    Pesel pesel;

    public int getAmountColumns() {
        return amountColumns;
    }

    int amountColumns=0;
    private boolean settingValues = true;
    int elementOfList=0;



    public ExcelReflection(List<Group> listOfDataColumns) {
        this.listOfDataColumns = listOfDataColumns;
        firstElementOfList++;

        for (int i = 0; i < listOfDataColumns.get(0).getDataList().size(); i++) {
            settingValues=true;
            nullCounter(firstElementOfList);
            listSwapper(Address.class, firstElementOfList, lastElementOfList);
            listSwapper(Pesel.class, firstElementOfList, lastElementOfList);
            settingValues=false;
        }
        createListsForEachObject();

        for(int i =0; i<amountOfPeselGroups;i++) {
            tmpObjectAddressList.add(listSwapper(Address.class, firstElementOfList, lastElementOfList));
        }
        elementOfList=0;
        for(int i =0; i<amountOfPeselGroups;i++) {
            tmpObjectPeselList.add(listSwapper(Pesel.class, firstElementOfList, lastElementOfList));
        }
        System.out.println(listSwapper(Dane.class, firstElementOfList, lastElementOfList).getClass().getSimpleName());
        FieldReader fieldReader= new FieldReader("testowe2.xlsx",getAmountColumns());


    }

    //zwraca liczbe elementow ile trzeba przypisac do peselu
    public int nullCounter(int firstElement) {
        pesel = new Pesel();
        pesel.setPesel(listOfDataColumns.get(0).getDataList());
        List<Double> tmpIntList = new ArrayList<>();

        for (int i = 0; i < pesel.getPesel().size(); i++) {
            if (pesel.getPesel().get(i) != null) {
                tmpIntList.add(pesel.getPesel().get(i));
            }
        }
        amountOfPeselGroups = tmpIntList.size();

        boolean wasThere = false;
        List<Double> lsitaTmp = new ArrayList<>();
        for (int i = firstElement; i < pesel.getPesel().size(); i++) {
            if (!wasThere) {
                lsitaTmp.add(pesel.getPesel().get(i));
                wasThere = true;
                continue;
            }
            if (pesel.getPesel().get(i) == null) {
                lsitaTmp.add(pesel.getPesel().get(i));
            }
            if (pesel.getPesel().get(i) != null) {
                break;
            }
        }
        maxAmountOfGroups.add(lsitaTmp.size());
        firstElementOfList = lastElementOfList;
        lastElementOfList = lsitaTmp.size() + firstElement;
        return lsitaTmp.size();
    }

    //LIST FOR PESEL, address
    private void createListsForEachObject() {
        listOfPesel = (List<T>) pesel.getPesel();
        listOfAddress = listOfData;


        for (int j = 0; j < listOfPesel.size(); j++) {
            List<T> tmpPesel = new ArrayList<>();
            boolean first = true;
            for (int i = 0; i < listOfPesel.size(); i++) {
                if (first) {
                    tmpPesel.add(listOfPesel.get(i));
                    first = false;
                    continue;
                }
                if (listOfPesel.get(i) == null) {
                    tmpPesel.add(null);
                } else {
                    listOfPesel = listOfPesel.subList(i, listOfPesel.size());
                    tmpPeselList.add(tmpPesel);
                    break;
                }
            }
        }
        tmpPeselList.add(listOfPesel);
        for (int i = 0; i < listOfAddress.size(); i++) {
            for (int j = 0; j < tmpPeselList.size(); j++) {
                if (listOfAddress.get(i).equals(tmpPeselList.get(j))) {
                    listOfAddress.remove(i);
                }
            }
        }
        listOfPesel = (List<T>) pesel.getPesel();
    }

    //DaneAnnotation-work.
    public Object listSwapper(Class newClass, int firstElementOfList, int nextElementInList) {
        int tmpAmountColumns = 0;
        Object classObject = null;
        try {
            classObject = newClass.newInstance();
            Field[] fields = classObject.getClass().getDeclaredFields();
            for (Field field : fields) {
                tmpAmountColumns++;
                for (Annotation annotation : field.getAnnotations())
                    if (annotation.annotationType().getSimpleName().equals("ColumnAnnotation")) {
                        ColumnAnnotation columnAnnotation = (ColumnAnnotation) annotation;
                        //if()
                      //field.set(classObject,tmpObjectPeselList);
                        switch(columnAnnotation.value().getValue()){
                            case "pesel":
                                field.set(classObject,tmpObjectPeselList);
                                break;
                            case "address":
                                field.set(classObject,tmpObjectAddressList);
                                break;
                        }

                    } else if (annotation.annotationType().getSimpleName().equals("DaneAnnotation")) {
                        DaneAnnotation data = (DaneAnnotation) annotation;
                        if(settingValues) {
                            if (!listOfDataColumns.get(data.value().getNumRow()).getDataList().subList(firstElementOfList, nextElementInList).isEmpty()) {
                                listOfData.add(listOfDataColumns.get(data.value().getNumRow()).getDataList().subList(firstElementOfList, nextElementInList));
                                field.set(classObject, listOfData);
                            break;
                            }
                        }
                        if(!settingValues) {
                            switch (classObject.getClass().getSimpleName()) {
                                case "Address":
                                  //  System.out.println(field.getName() + "  Field name   "  + listOfAddress.get(elementOfList));
                                    field.set(classObject,listOfAddress.get(elementOfList));
                                    elementOfList++;
                                    break;
                                case "Pesel":
                                  //  System.out.println(field.getName() + "  Field name   "  + tmpPeselList.get(elementOfList));
                                    field.set(classObject,tmpPeselList.get(elementOfList));
                                    elementOfList++;
                                    break;

                            }
                        }

                    }


            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // System.out.println("-----------");
        if (amountColumns == 0) {
            amountColumns = tmpAmountColumns;
            // System.out.println(amountColumns + "amount of columns without pesel");
        }
        //System.out.println("dodawanie do classObject list of data");
        // System.out.println(listOfData);
        return classObject;
    }

}
