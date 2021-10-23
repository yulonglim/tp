package seedu.teachbook.logic.parser;

import seedu.teachbook.logic.commands.SetGradeCommand;

public class SetGradeCommandParser implements Parser<SetGradeCommand>{
    public SetGradeCommand parse(String args) {
        String grades = args.substring(2);
        String[] gradeList = grades.split(">");
        return new SetGradeCommand(gradeList);
    }
}
