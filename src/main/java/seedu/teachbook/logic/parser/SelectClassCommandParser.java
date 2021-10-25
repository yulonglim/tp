package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.teachbook.logic.commands.SelectClassCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.classobject.ClassNameDescriptor;


/**
 * Parses input arguments and creates a new SelectClassCommand object
 */
public class SelectClassCommandParser implements Parser<SelectClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectClassCommand
     * and returns a SelectClassCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectClassCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectClassCommand.MESSAGE_USAGE));
        }

        ClassNameDescriptor className = ParserUtil.parseClassNameForLocatingClass(trimmedArgs);
        return new SelectClassCommand(className); // TODO: better write how to parse "select" command
    }

}
