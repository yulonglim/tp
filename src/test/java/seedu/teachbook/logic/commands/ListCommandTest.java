package seedu.teachbook.logic.commands;

import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachbook.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.teachbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.teachbook.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.teachbook.model.Model;
import seedu.teachbook.model.ModelManager;
import seedu.teachbook.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getTeachBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
