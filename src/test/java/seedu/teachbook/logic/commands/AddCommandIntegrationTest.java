package seedu.teachbook.logic.commands;

import static seedu.teachbook.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.teachbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.teachbook.testutil.TypicalStudents.getTypicalTeachBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.Model;
import seedu.teachbook.model.ModelManager;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.UserPrefs;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
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
        // Build expected student
        // Note that the class of the expected student must be the same as the initialized currently selected class in
        // the expected model ie. it is sufficient for the classes to have the same name.
        Student validStudent = new StudentBuilder().build();

        // Get class of expected student
        Model expectedModel = new ModelManager(model.getTeachBook(), new UserPrefs());
        String className = validStudent.getStudentClass().getClassName().nameOfClass;
        ClassNameDescriptor classNameDescriptor = new ClassNameDescriptor(className);
        GeneralIndex classIndex = expectedModel.getIndexOfClass(classNameDescriptor);

        // Set currently selected class to class of expected student
        expectedModel.updateCurrentlySelectedClass(classIndex);

        // Link the class of the expected student to the currently selected class in the expected model so that
        // addStudent() will add the student to the currently selected class in the expected model instead of
        // the class created during the creation of StudentBuilder
        expectedModel.setClassForStudent(validStudent);

        expectedModel.addStudent(validStudent);

        expectedModel.commitTeachBook();

        CommandResult expectedCommandResult = new CommandResult(String.format(AddCommand.MESSAGE_ADD_STUDENT_SUCCESS,
                validStudent), false, false, true, false);

        assertCommandSuccess(new AddCommand(validStudent), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Student studentInList = model.getTeachBook().getClassList().get(0).getStudentsOfThisClass().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, MESSAGE_DUPLICATE_STUDENT);
    }

}
