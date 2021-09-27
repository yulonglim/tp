package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Class.Class;

/**
 * Adds a person to the address book.
 */
public class AddClassCommand extends Command {

    public static final String COMMAND_WORD = "addClass";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a class to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Class A ";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This class already exists in the address book";

    private final Class toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddClassCommand(Class classObj) {
        requireNonNull(classObj);
        toAdd = classObj;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClass(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addClass(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddClassCommand // instanceof handles nulls
                && toAdd.equals(((AddClassCommand) other).toAdd));
    }
}
