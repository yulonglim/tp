package seedu.teachbook.logic.parser;

import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.logic.commands.AddCommand;
import seedu.teachbook.logic.commands.EditCommand;
import seedu.teachbook.logic.commands.GradeCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.*;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_GRADE;

public class GradeCommandParser implements Parser<GradeCommand>{

    @Override
    public GradeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GRADE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_GRADE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        }
        return new GradeCommand(index, ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

