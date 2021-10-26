package seedu.teachbook.commons.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {

    private static Font setHeaderFont(Font headerFont) {
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.index);
        return headerFont;
    }

    public static void toExcel(List<List<String>> listOfColumns) {
        try {
            Workbook workbook = new HSSFWorkbook();
            Sheet sh = workbook.createSheet("Student List");
            String[] columnHeadings = new String[listOfColumns.size() + 1];

            for (int i = 0; i < listOfColumns.size(); i++) {
//                columnHeadings[i] = StringUtil.toCamelCase(listOfColumns.get(i).get(0));
                columnHeadings[i] = listOfColumns.get(i).get(0);
            }

            Font headerFont = setHeaderFont(workbook.createFont());
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);

            Row headerRow = sh.createRow(0);
            for (int i = 0; i < columnHeadings.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnHeadings[i]);
                cell.setCellStyle(headerStyle);
            }

            for (int i = 1; i < listOfColumns.get(0).size(); i++) {
                Row row = sh.createRow(i);
                for (int j = 0; j < listOfColumns.size(); j++) {
                    row.createCell(j).setCellValue(listOfColumns.get(j).get(i));
                }
            }

            for (int i = 0; i < columnHeadings.length; i++) {
                sh.autoSizeColumn(i);
            }

            String home = System.getProperty("user.home");
            FileOutputStream fileOut = new FileOutputStream(home + "/Downloads/" + sh.getSheetName() + ".xls");
            // TODO: catch exception when file is open
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isOpen() {
        try {
            String home = System.getProperty("user.home");
            FileOutputStream fileOut = new FileOutputStream(home + "/Downloads/Student List.xls");
            fileOut.close();
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}
