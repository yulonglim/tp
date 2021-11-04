package seedu.teachbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.teachbook.storage.JsonAdaptedClass.MESSAGE_DUPLICATE_STUDENT;
import static seedu.teachbook.storage.JsonAdaptedClass.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.teachbook.testutil.Assert.assertThrows;
import static seedu.teachbook.testutil.TypicalClasses.A;
import static seedu.teachbook.testutil.TypicalClasses.B;
import static seedu.teachbook.testutil.TypicalClasses.getTypicalClasses;
import static seedu.teachbook.testutil.TypicalStudents.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.model.classobject.ClassName;


public class JsonAdaptedClassTest {
    private static final String INVALID_CLASSNAME = "4@1";

    private static final String VALID_CLASSNAME = getTypicalClasses().get(0).getClassName().toString();
    private static final List<JsonAdaptedStudent> VALID_STUDENTS =
            getTypicalClasses().get(0).getUniqueStudentListOfThisClass().asUnmodifiableObservableList().stream()
            .map(JsonAdaptedStudent::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validClassDetails_returnsClass() throws Exception {
        JsonAdaptedClass classObj = new JsonAdaptedClass(A);
        assertEquals(A, classObj.toModelType());
    }

    @Test
    public void toModelType_nullClassList_returnsClass() throws Exception {
        JsonAdaptedClass classObj = new JsonAdaptedClass(getTypicalClasses().get(1).getClassName().toString(), null);
        assertEquals(getTypicalClasses().get(1), classObj.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClass classObj = new JsonAdaptedClass(INVALID_CLASSNAME, VALID_STUDENTS);
        String expectedMessage = ClassName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, classObj::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClass classObj = new JsonAdaptedClass(null, VALID_STUDENTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, classObj::toModelType);
    }

    @Test
    public void toModelType_invalidStudents_throwsIllegalValueException() {
        List<JsonAdaptedStudent> INVALID_STUDENTS =
                getTypicalClasses().get(0).getUniqueStudentListOfThisClass().asUnmodifiableObservableList().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList());
        INVALID_STUDENTS.add(new JsonAdaptedStudent(BENSON));//duplicate student
        System.out.println(INVALID_STUDENTS);
        JsonAdaptedClass classObj = new JsonAdaptedClass(VALID_CLASSNAME, INVALID_STUDENTS);
        assertThrows(IllegalValueException.class,
                MESSAGE_DUPLICATE_STUDENT,
                classObj::toModelType);
    }

}
