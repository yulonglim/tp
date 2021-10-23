package seedu.teachbook.logic.commands;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.gradeObject.Grade;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_GRADE;

import java.util.ArrayList;

public class SetGradeCommand extends Command{

    public final ArrayList<Grade> grades;
    public static final String COMMAND_WORD = "setGrade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a grading system "
            + "Parameters: "
            + PREFIX_GRADE + "Grade1>Grade2>Grade3... "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GRADE + "A>B>C>D>E";

    public static final String MESSAGE_SUCCESS = "Grading system set: %1$s";
    public static final String MESSAGE_GRADE_EXISTS = "Grade has already been set. Delete grades before setting again";

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public SetGradeCommand(ArrayList<Grade> grades) {
        requireNonNull(grades);
        this.grades = grades;
    }

    public String gradeToString() {
        StringBuilder result = new StringBuilder();
        for(Grade grade : grades) {
            result.append(grade.toString()).append(" ");
        }
        return result.toString();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if(model.getGradeList().size() != 0) {
            throw new CommandException(MESSAGE_GRADE_EXISTS);
        }
        model.setGradeList(grades);
        return new CommandResult(String.format(MESSAGE_SUCCESS, gradeToString()));
    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof seedu.teachbook.logic.commands.SetGradeCommand // instanceof handles nulls
//                && Arrays.equals(grades, ((SetGradeCommand) other).grades));
//    }
}
