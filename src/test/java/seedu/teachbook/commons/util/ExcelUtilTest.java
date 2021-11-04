package seedu.teachbook.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.teachbook.logic.commands.PrintCommand.generateColumn;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.teachbook.model.student.Student;
import seedu.teachbook.testutil.TypicalClasses;


public class ExcelUtilTest {
    private static final List<Student> studentList = TypicalClasses.getAClass().getStudentsOfThisClass();


    @Test
    public void isPrinted() {
        List<List<String>> toPrint = new ArrayList<>();
        List<String> studentNames = new ArrayList<>();
        List<String> columnList = new ArrayList<>();

        studentNames.add("Name");
        for (Student s : studentList) {
            studentNames.add(s.getName().fullName);
        }

        toPrint.add(studentNames);
        for (String columnName : columnList) {
            toPrint.add(generateColumn(columnName, studentList));
        }

        String filePath = null;
        try {
            filePath = ExcelUtil.toExcel(toPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String home = System.getProperty("user.home");
        String filePath2 = Paths.get(home, "Downloads").toString();

        assertEquals(filePath, filePath2);
    }
}
