package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachbook.logic.parser.CliSyntax.PREFIX_GRADE;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.teachbook.logic.commands.SetGradeCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.gradeobject.Grade;

public class SetGradeCommandParser implements Parser<SetGradeCommand> {
    public SetGradeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GRADE);
        if (!arePrefixesPresent(argMultimap, PREFIX_GRADE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGradeCommand.MESSAGE_USAGE));
        }
        ArrayList<Grade> gradeList = new ArrayList<>();
        String[] stringGradeList = (argMultimap.getValue(PREFIX_GRADE).get()).split(">");
        for (String grade : stringGradeList) {
            gradeList.add(ParserUtil.parseGrade(grade));
        }
        return new SetGradeCommand(gradeList);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
