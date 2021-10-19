package seedu.teachbook.commons.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import seedu.teachbook.model.student.Student;

public class ExcelUtil {

    private static Font setHeaderFont(Font headerFont) {
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.BLACK.index);
        return headerFont;
    }

    public static void toExcel(List<Student> studentList, List<String> columns) {
        try {
            Workbook workbook = new HSSFWorkbook();
            Sheet sh = workbook.createSheet("Student List");
            String[] columnHeadings = new String[columns.size() + 1];
            columnHeadings[0] = "Name";
            for (int i = 0; i < columns.size(); i++) {
                columnHeadings[i + 1] = columns.get(i);
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
            int rownum = 1;
            for (Student student : studentList) {
                Row row = sh.createRow(rownum++);
                row.createCell(0).setCellValue(student.getName().fullName);
            }

            for (int i = 0; i < columnHeadings.length; i++) {
                sh.autoSizeColumn(i);
            }

            String home = System.getProperty("user.home");
            FileOutputStream fileOut = new FileOutputStream(home + "/Downloads/" + sh.getSheetName() + ".xls");
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
