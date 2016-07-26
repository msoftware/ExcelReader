package skubalaw.excelparser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import skubalaw.KindOfCell;

import java.util.List;

/**
 * Created by skubalaw on 2016-07-11.
 */

//@AllArgsConstructor

public abstract class AGroup {


    public AGroup(Integer firstDim,Integer secDim,Integer firstRowNum, Integer lastRowNum, String name, KindOfCell kindOfCell, List<Group> groupList) {
        this.firstDim=firstDim;
        this.secDim=secDim;
        this.firstRowNum=firstRowNum;
        this.lastRowNum=lastRowNum;
        this.name=name;
        this.kindOfCell=kindOfCell;
        this.groupList=groupList;
    }

    AGroup(){

    }
    private Integer firstDim;

    public Integer getFirstDim() {
        return firstDim;
    }

    public void setFirstDim(Integer firstDim) {
        this.firstDim = firstDim;
    }

    public Integer getSecDim() {
        return secDim;
    }

    public void setSecDim(Integer secDim) {
        this.secDim = secDim;
    }

    public Integer getFirstRowNum() {
        return firstRowNum;
    }

    public void setFirstRowNum(Integer firstRowNum) {
        this.firstRowNum = firstRowNum;
    }

    public Integer getLastRowNum() {
        return lastRowNum;
    }

    public void setLastRowNum(Integer lastRowNum) {
        this.lastRowNum = lastRowNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KindOfCell getKindOfCell() {
        return kindOfCell;
    }

    public void setKindOfCell(KindOfCell kindOfCell) {
        this.kindOfCell = kindOfCell;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    private Integer secDim;
    private Integer firstRowNum;
    private Integer lastRowNum;

    private String name;
    private KindOfCell kindOfCell;


    private List<Group> groupList;


}
