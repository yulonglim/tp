package seedu.teachbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_COLUMN;

import java.util.List;

import seedu.teachbook.commons.util.ExcelUtil;
import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.student.Student;

/**
 * Switches to another class identified using its name.
 */
public class PrintClassCommand extends Command {

    public static final String COMMAND_WORD = "print";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Prints the current selected class "
            + "Parameters: "
            + "[" + PREFIX_COLUMN + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COLUMN + "Grades "
            + PREFIX_COLUMN + "Sign Here";

    public static final String PRINT_SUCCESS =
            "Excel file created!";

    private final List<String> columnList;


    public PrintClassCommand(List<String> columnList) {
        this.columnList = columnList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> toPrint = model.getFilteredStudentList();
        ExcelUtil.toExcel(toPrint, columnList);

        return new CommandResult(PRINT_SUCCESS,
                false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PrintClassCommand);
    }
}
