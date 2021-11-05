package seedu.teachbook.logic.commands;

import static seedu.teachbook.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachbook.testutil.TypicalStudents.getTypicalTeachBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.teachbook.model.Model;
import seedu.teachbook.model.ModelManager;
import seedu.teachbook.model.UserPrefs;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTeachBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getTeachBook(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_ADD_STUDENT_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student studentInList = model.getTeachBook().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, MESSAGE_DUPLICATE_STUDENT);
    }

}
