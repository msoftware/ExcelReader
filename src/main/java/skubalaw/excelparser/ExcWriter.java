package skubalaw.excelparser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import skubalaw.ExcelReflection.ExcelReflection;
import skubalaw.xmlStyle.XmlStyleChooser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by skubalaw on 2016-07-14.
 */
public class ExcWriter<T> extends ExcWriterAbstract {


    private List<Group> listOfDataColumns;
    private List<Group> listOfGroups;
    private String fileName;
    private int rowDataNumber;
    List<String> tmpList = new ArrayList<>();



    public ExcWriter(List<Group> listOfGroups, List<Group> listOfDataColumns, int rowDataNumber, String fileName) throws ParseException, IllegalAccessException {
        this.listOfGroups = listOfGroups;
        this.listOfDataColumns = listOfDataColumns;
        this.fileName = fileName;
        this.rowDataNumber = rowDataNumber;
        if (fileName.endsWith("x")) {
            wb = new XSSFWorkbook();
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.createSheet("Shit");

        writeGroups();
        loadData();


        ExcelReflection reflectionExcel = new ExcelReflection(listOfDataColumns);
    }


    //color choosed group
    public void colorImportatntList(List<String> choosedCells) {
        this.tmpList = choosedCells;
        // System.out.println(choosedCells);
        for (Row r :
                sheet) {
            for (Cell c :
                    r) {
                for (int i = 0; i < choosedCells.size(); i++) {
                    if (c.getCellType() == Cell.CELL_TYPE_STRING && c.getStringCellValue().toString().equals(choosedCells.get(i).toString())) {
                        CellStyle tmpCellStyle = c.getCellStyle();

                        short fillBackgroundColor = tmpCellStyle.getFillBackgroundColor();

                        tmpCellStyle.setFillForegroundColor((short) (fillBackgroundColor - 1));
                        tmpCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

                        Font tmpFont = wb.createFont();
                        tmpFont.setFontHeightInPoints((short) 20);

                        tmpCellStyle.setFont(tmpFont);
                        c.setCellStyle(tmpCellStyle);

                    }
                }

            }

        }

    }


    public void write() {
        if (fileName.endsWith("x")) {

            try {
                FileOutputStream fileOut = new FileOutputStream(fileName);
                wb.write(fileOut);

                fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            try {

                FileOutputStream fileOut = new FileOutputStream(fileName);
                wb.write(fileOut);
                fileOut.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setCellStyle(Cell cell) {

        XmlStyleChooser xmlStyleChooser = new XmlStyleChooser(wb);
        cell.setCellStyle(xmlStyleChooser.readXmlFile());
    }

    public void setCellStyle(Cell cell, int i) {

        XmlStyleChooser xmlStyleChooser = new XmlStyleChooser(wb);
        cell.setCellStyle(xmlStyleChooser.readXmlFile(i));
    }


    private void loadMergedRegions(Group g, short borderTop, short borderBottom, short borderLeft, short borderRight) {


        if (g.getFirstRowNum() < g.getLastRowNum() || g.getFirstDim() < g.getSecDim())
            sheet.addMergedRegion(new CellRangeAddress(g.getFirstRowNum(), g.getLastRowNum(), g.getFirstDim(), g.getSecDim()));
        RegionUtil.setBorderBottom(borderBottom, new CellRangeAddress(g.getFirstRowNum(), g.getLastRowNum(), g.getFirstDim(), g.getSecDim()), sheet, wb);
        RegionUtil.setBorderTop(borderTop, new CellRangeAddress(g.getFirstRowNum(), g.getLastRowNum(), g.getFirstDim(), g.getSecDim()), sheet, wb);
        RegionUtil.setBorderLeft(borderLeft, new CellRangeAddress(g.getFirstRowNum(), g.getLastRowNum(), g.getFirstDim(), g.getSecDim()), sheet, wb);
        RegionUtil.setBorderRight(borderRight, new CellRangeAddress(g.getFirstRowNum(), g.getLastRowNum(), g.getFirstDim(), g.getSecDim()), sheet, wb);


    }

    public void writeGroups() {

        Row row;
        if (listOfGroups.size() != 0) {
            for (int i = 0; i < rowDataNumber; i++) {
                row = sheet.createRow(i);
                int j = i;
                List<Group> tempRowGroup = listOfGroups.stream().filter(p -> p.getFirstRowNum() == j).collect(Collectors.toList());
                for (Group g : tempRowGroup) {

                    Cell cell = row.createCell(g.getFirstDim());
                    cell.setCellValue(g.getName());
                    if (i == rowDataNumber) {
                        setCellStyle(cell, 2);
                    } else {
                        setCellStyle(cell);
                    }
                    loadMergedRegions(g, cell.getCellStyle().getBorderTop(), cell.getCellStyle().getBorderBottom(), cell.getCellStyle().getBorderLeft(), cell.getCellStyle().getBorderRight());
                }
            }
        }
        if (listOfDataColumns.size() != 0) {
            row = sheet.createRow(rowDataNumber - 1);
            for (Group g : listOfDataColumns) {
                Cell cell = row.createCell(g.getFirstDim());
                cell.setCellValue(g.getName());
                setCellStyle(cell, 2);
            }

        }
    }


    public void loadData() throws ParseException {

        Cell cell = null;
        for (int k = rowDataNumber; k < 100;
            //<rowDataNumber + listOfDataColumns.size()+1 ;
             k++) {
            sheet.createRow(k);
        }
        for (int j = 0; j < listOfDataColumns.size(); j++) {
            for (int i = rowDataNumber; i < listOfDataColumns.get(j).getDataList().size() + rowDataNumber; i++) {
                cell = sheet.getRow(i).createCell(j);

                if (listOfDataColumns.get(j).getDataList().get(i - rowDataNumber) != null) {

                    if (listOfDataColumns.get(j).getDataList().get(i - rowDataNumber) instanceof Date) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(listOfDataColumns.get(j).getDataList().get(i - rowDataNumber)));
                    } else if (listOfDataColumns.get(j).getDataList().get(i - rowDataNumber) instanceof Double) {
                        cell.setCellValue((double) listOfDataColumns.get(j).getDataList().get(i - rowDataNumber));
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    } else if (listOfDataColumns.get(j).getDataList().get(i - rowDataNumber) instanceof String) {

                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(listOfDataColumns.get(j).getDataList().get(i - rowDataNumber).toString());
                    } else if (listOfDataColumns.get(j).getDataList().get(i - rowDataNumber) instanceof Boolean) {

                        cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
                        cell.setCellValue((boolean) listOfDataColumns.get(j).getDataList().get(i - rowDataNumber));
                    } else if (listOfDataColumns.get(j).getDataList().get(i - rowDataNumber) instanceof Object) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(listOfDataColumns.get(j).getDataList().get(i - rowDataNumber).toString());
                    }
                } else if (cell == null) {
                    cell = null;
                } else {
                    cell = null;
                }

            }

        }


    }


}
