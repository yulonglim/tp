package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.classobject.ClassName;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SelectClassCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects the class with "
            + "the specified class name (case-insensitive).\n"
            + "Parameters: CLASSNAME\n"
            + "Example: " + COMMAND_WORD + " Ace";

//    private final ClassNameContainsKeywordsPredicate predicate;
    private final ClassName newSelectedClassName;

//    public SelectClassCommand(ClassNameContainsKeywordsPredicate predicate) {
//        this.predicate = predicate;
//    }

    public SelectClassCommand(ClassName newSelectedClassName) {
        this.newSelectedClassName = newSelectedClassName;
    }

//    @Override
//    public CommandResult execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredClassList(predicate);
//        return new CommandResult(
//                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredClassList().size()));
//    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateCurrentlySelectedClass(newSelectedClassName);
        return new CommandResult("select command executed successfully!",
                false, false, true); // TODO: Change the message text
    }

//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof SelectClassCommand // instanceof handles nulls
//                && predicate.equals(((SelectClassCommand) other).predicate)); // state check
//    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectClassCommand // instanceof handles nulls
                && newSelectedClassName.equals(((SelectClassCommand) other).newSelectedClassName)); // state check
    }
}
