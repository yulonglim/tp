package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.classobject.ClassNameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindClassCommand extends Command {

    public static final String COMMAND_WORD = "findClass";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final ClassNameContainsKeywordsPredicate predicate;

    public FindClassCommand(ClassNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
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
        model.updateFilteredClassList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredClassList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClassCommand // instanceof handles nulls
                && predicate.equals(((FindClassCommand) other).predicate)); // state check
    }
}
