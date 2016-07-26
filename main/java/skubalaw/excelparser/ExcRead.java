package skubalaw.excelparser;

import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import skubalaw.KindOfCell;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by skubalaw on 2016-07-11.
 */

@Getter
public class ExcRead {

    public Workbook wb;
    private Sheet sheet;
    private int groupRowNumber;
    private List<Group> listOfGroup = new ArrayList<Group>();
    private List<Group> listOfData = new ArrayList<Group>();

    public ExcRead(String str, int groupRowNumber, int sheetNumber) throws IOException {
        try {
            this.wb = WorkbookFactory.create(new File(str));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        this.groupRowNumber = groupRowNumber;
        this.sheet = wb.getSheetAt(sheetNumber);

        loadGroups();
        loadListsOfMembers();
        loadListOfDataColumns();
        listObjOfGroups();

    }

    private void loadListsOfMembers() {

        for (int i = groupRowNumber; i >= 0; i--)
            for (Group g : listOfGroup) {

                if (g.getFirstRowNum() == i && g.getGroupList() != null) {
                    g.getGroupList().add(g);
                }
            }

    }

    private void listObjOfGroups() {
        for (Group g : listOfData) {
            List<Group> temp = new ArrayList<>();
            for (Group s : listOfGroup) {
                if (g.getFirstDim() >= s.getFirstDim() && g.getSecDim() <= s.getSecDim()) {
                    temp.add(s);
                }
            }
            temp.stream().collect(Collectors.toSet());
            g.setGroupList(temp);
        }
    }

    private void loadListOfDataColumns() {
        Row row = sheet.getRow(groupRowNumber-1);
        for (Cell c : row) {
            listOfData.add(new Group(c.getColumnIndex(), c.getColumnIndex(), groupRowNumber,groupRowNumber, c.getStringCellValue(), kindOfCell(c), loadDataToObj(c), new ArrayList<Group>()));
        }
    }

    public Group getGroupByColumn(String colName){
        int i = CellReference.convertColStringToIndex(colName);
        for (Group group : listOfData) {
            if(group.getFirstRowNum() == groupRowNumber && group.getFirstDim() == i){
                return group;
            }
        }
        return null;
    }

    private List<?> loadDataToObj(Cell cell) {


        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return dataList(new Date(), cell);

                } else return dataList(new Double(1), cell);
            case Cell.CELL_TYPE_STRING:

                return dataList(new String(), cell);
            case Cell.CELL_TYPE_BOOLEAN:
                return dataList(new Boolean(null), cell);
            case Cell.CELL_TYPE_FORMULA:
                return dataList(new String(), cell);
            default:
                return dataList(new Object(), cell);
        }
    }

    private <T> List<T> dataList(T t, Cell cell) {
        List<Object> listTemp = new ArrayList<Object>();
        List<T> listOut = new ArrayList<T>();
        List<Cell> tempCellList = new ArrayList<>();


        for (int i = groupRowNumber ; i <= sheet.getLastRowNum(); i++) {
            Row row1 = sheet.getRow(i);

            //  System.out.println(sheet.getPhysicalNumberOfRows());
            if(row1.getCell(cell.getColumnIndex())== null)
                tempCellList.add(null);
            else
                tempCellList.add(row1.getCell(cell.getColumnIndex()));
        }
        //   System.out.println(tempCellList);


        for (Cell cell1 : tempCellList) {

            if(cell1 == null){
                listTemp.add(null);
            }
            else {

                switch (cell1.getCellType()) {
                    case Cell.CELL_TYPE_STRING:

                        listTemp.add(cell1.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell1)) {
                            listTemp.add(cell1.getDateCellValue());
                            break;
                        } else {
                            listTemp.add(cell1.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        listTemp.add(cell1.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        listTemp.add(cell1.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        listTemp.add(null);
                        break;
                    default:
                        break;
                }
            }

        }
        for (Object o : listTemp) {
            listOut.add((T) o);
        }
        return listOut;
    }

    private void loadGroups() {
        List<CellRangeAddress> mergedRegions = sheet.getMergedRegions();
        for (CellRangeAddress x : mergedRegions) {
            listOfGroup.add(
                    new Group(
                            x.getFirstColumn(),
                            x.getLastColumn(),
                            x.getFirstRow(),
                            x.getLastRow(),
                            sheet.getRow(x.getFirstRow()).getCell(x.getFirstColumn()).getStringCellValue(),
                            kindOfCell(sheet.getRow(x.getFirstRow()).getCell(x.getFirstColumn())),
                            null,
                            new ArrayList<Group>()));

        }


        for (int i = 0; i < groupRowNumber-1; i++) {
            Row row = sheet.getRow(i);
            for (Cell c : row) {
                if (!c.getStringCellValue().equals("")) {
                    listOfGroup.add(new Group(
                            c.getColumnIndex(),
                            c.getColumnIndex(),
                            i,
                            i,
                            c.getStringCellValue(),
                            kindOfCell(c),
                            null,
                            new ArrayList<Group>()));
                }
            }
            listOfGroup.stream().collect(Collectors.toSet());
        }




    }

    private KindOfCell kindOfCell(Cell cell) {
        if (cell.getRowIndex() != groupRowNumber) {
            return KindOfCell.GROUP;
        } else return KindOfCell.DATA;
    }

    public List<Group> getAllGroups() {
        return listOfGroup;
    }

    public List<Group> getAllDataColumns() {
        return listOfData;
    }

    public int getSheetRows(){return sheet.getPhysicalNumberOfRows();}
}