package skubalaw.cellObjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import skubalaw.excelparser.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skubalaw on 2016-07-12.
 */


@AllArgsConstructor
@NoArgsConstructor
public class Builder {

    List<String> groupNames = new ArrayList<>();

    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }

    public List<String> getGroupNames() {

        return groupNames;
    }


    public Builder getGroup(List<String> s) {

        groupNames.addAll(s);
        return this;
    }

    public<T> T getData(List<Group> dataColumn, int rowNum) {
        Object o = new Object();
        for (Group s : dataColumn) {
            //
            List<String> temp = new ArrayList<>();
            for (Group ss : s.getGroupList()) {
                temp.add(ss.getName());
            }
            temp.add(s.getName());
            if (groupNames.containsAll(temp)) {

                //    System.out.println(s.getDataList());
                o = s.getDataList().get(rowNum);
            }
        }
        if(o != null) System.out.println(o.getClass());
        return (T) o;
    }
}
