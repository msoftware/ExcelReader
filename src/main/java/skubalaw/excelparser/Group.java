package skubalaw.excelparser;

import lombok.Getter;
import skubalaw.KindOfCell;

import java.util.List;

/**
 * Created by skubalaw on 2016-07-11.
 */
public class Group<T> extends AGroup {

    private List<T> dataList;

    public Group() {

    }

    public List<T> getDataList() {
        return dataList;
    }

    public Group(Integer firstDim, Integer secDim, Integer firstRowNum, Integer lastRowNum, String name, KindOfCell kindOfCell, List<T> dataList, List<Group> groupList) {
        super(firstDim, secDim, firstRowNum,lastRowNum, name, kindOfCell, groupList);

        this.dataList = dataList;
    }



    //  public <T> List<T> getDataList() {
    //   return (List<T>) dataList;
    // }

    @Override
    public String toString() {
        return this.getName();
    }
}

