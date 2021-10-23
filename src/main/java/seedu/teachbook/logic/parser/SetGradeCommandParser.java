package seedu.teachbook.logic.parser;

import seedu.teachbook.logic.commands.SetGradeCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.*;

public class SetGradeCommandParser implements Parser<SetGradeCommand>{
    public SetGradeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GRADE);
        if(!arePrefixesPresent(argMultimap, PREFIX_GRADE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGradeCommand.MESSAGE_USAGE));
        }
        String grades = ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get());
        String[] gradeList = grades.split(">");
        return new SetGradeCommand(gradeList);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
