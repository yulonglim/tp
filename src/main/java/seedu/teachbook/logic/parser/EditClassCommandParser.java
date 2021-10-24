package seedu.teachbook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.teachbook.logic.commands.EditClassCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

public class EditClassCommandParser implements Parser<EditClassCommand> {
    @Override
    public EditClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClassCommand.MESSAGE_USAGE));
        }
        String newClassName = argMultimap.getValue(PREFIX_NAME).get();
        if (newClassName.equals("")) {
            throw new ParseException(EditClassCommand.EMPTY_CLASSNAME);
        }
        return new EditClassCommand(newClassName);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
