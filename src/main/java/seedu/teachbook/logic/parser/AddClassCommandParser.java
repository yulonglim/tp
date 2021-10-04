package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.teachbook.logic.commands.AddClassCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddClassCommandParser implements Parser<AddClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClassCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        }

        ClassName name = ParserUtil.parseClassName(argMultimap.getValue(PREFIX_NAME).get());


        Class classObj = new Class(name);

        return new AddClassCommand(classObj);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
