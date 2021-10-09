package seedu.teachbook.logic.commands;

import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachbook.testutil.TypicalPersons.getTypicalTeachBook;

import org.junit.jupiter.api.Test;

import seedu.teachbook.model.Model;
import seedu.teachbook.model.ModelManager;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTeachBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTeachBook_success() {
        Model model = new ModelManager(getTypicalTeachBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTeachBook(), new UserPrefs());
        expectedModel.setTeachBook(new TeachBook());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
