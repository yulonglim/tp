package seedu.teachbook.logic.parser;

import static seedu.teachbook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.teachbook.logic.commands.SetGradeCommand;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.gradeobject.Grade;

public class SetGradeCommandParser implements Parser<SetGradeCommand> {

    public SetGradeCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetGradeCommand.MESSAGE_USAGE));
        }

        List<Grade> gradeList = ParserUtil.parseGrades(trimmedArgs);;

        return new SetGradeCommand(gradeList);
    }

}
