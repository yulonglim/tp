package seedu.teachbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.teachbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.commons.util.JsonUtil;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.testutil.TypicalStudents;

public class JsonSerializableTeachBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsTeachBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonTeachBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonTeachBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableTeachBook.class).get();
        TeachBook teachBookFromFile = dataFromFile.toModelType();
        TeachBook typicalPersonsTeachBook = TypicalStudents.getTypicalTeachBook();
        assertEquals(teachBookFromFile, typicalPersonsTeachBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableTeachBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableTeachBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeachBook.MESSAGE_DUPLICATE_CLASS,
                dataFromFile::toModelType);
    }

}
