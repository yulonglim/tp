package seedu.teachbook.logic.commands;

import seedu.teachbook.logic.commands.exceptions.CommandException;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.logic.parser.CliSyntax.*;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_TAG;

public class EditClassCommand extends Command {
    public static final String COMMAND_WORD = "editClass";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits class name of currently selected class. "
            + "Parameters: "
            + PREFIX_NAME + "CLASSNAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "ClassA ";

    public static final String MESSAGE_SUCCESS = "Class name edited: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "Class already exists in teachbook!";
    public static final String EMPTY_CLASSNAME = "Class name cannot be an empty string";

    public String newClassName;

    public EditClassCommand(String newClassName) {
        this.newClassName = newClassName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Class newClass = new Class(new ClassName(newClassName));
        if (model.hasClass(newClass)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }
        model.setClassname(newClass);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newClassName));
    }
}
