package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_COLUMN;

import java.util.ArrayList;
import java.util.List;

import seedu.teachbook.commons.util.ExcelUtil;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.tag.Tag;

/**
 * Switches to another class identified using its name.
 */
public class PrintCommand extends Command {

    public static final String COMMAND_WORD = "print";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Prints the current selected class "
            + "Parameters: "
            + "[" + PREFIX_COLUMN + "COL]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COLUMN + "Grades "
            + PREFIX_COLUMN + "Sign Here"
            + PREFIX_COLUMN + "Phone";

    public static final String PRINT_SUCCESS =
            "Excel file created!";

    private final List<String> columnList;


    public PrintCommand(List<String> columnList) {
        this.columnList = columnList;
    }

    private static List<String> generateColumn(String columnName, List<Student> studentList) {
        List<String> result = new ArrayList<>();
        result.add(columnName);

        switch (columnName.toLowerCase()) {
        case "class":
            for (Student student : studentList) {
                result.add(student.getStudentClass().getClassName().nameOfClass);
            }
            break;

        case "phone":
            for (Student student : studentList) {
                result.add(student.getPhone().value);
            }
            break;

        case "email":
            for (Student student : studentList) {
                result.add(student.getEmail().value);
            }
            break;

        case "tags":
            for (Student student : studentList) {
                StringBuilder remarks = new StringBuilder();
                for (Tag t : student.getTags()) {
                    remarks.append(t.tagName).append("; ");
                }
                result.add(remarks.toString());
            }
            break;

        case "grade":
            for (Student student : studentList) {
                result.add(student.getGrade().value);
            }
            break;

        case "remark":
            for (Student student : studentList) {
                result.add(student.getRemark().value);
            }
            break;

        case "attendance":
            for (Student student : studentList) {
                result.add(student.getAttendance().isPresent ? "Present" : "Absent");
            }
            break;

        default:
            for (int i = 0; i < studentList.size(); i++) {
                result.add("");
            }
            break;
        }

        return result;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> studentList = model.getFilteredStudentList();
        List<List<String>> toPrint = new ArrayList<>();
        List<String> studentNames = new ArrayList<>();
        studentNames.add("Name");
        for (Student s : studentList) {
            studentNames.add(s.getName().fullName);
        }

        toPrint.add(studentNames);
        for (String columnName : columnList) {
            toPrint.add(generateColumn(columnName, studentList));
        }

        ExcelUtil.toExcel(toPrint);

        return new CommandResult(PRINT_SUCCESS,
                false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrintCommand // instanceof handles nulls
                && columnList.equals(((PrintCommand) other).columnList));
    }

}
