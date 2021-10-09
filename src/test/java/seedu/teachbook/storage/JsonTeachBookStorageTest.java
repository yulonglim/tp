package seedu.teachbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.teachbook.testutil.Assert.assertThrows;
import static seedu.teachbook.testutil.TypicalPersons.ALICE;
import static seedu.teachbook.testutil.TypicalPersons.HOON;
import static seedu.teachbook.testutil.TypicalPersons.IDA;
import static seedu.teachbook.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.teachbook.commons.exceptions.DataConversionException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.TeachBook;

public class JsonTeachBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyTeachBook> readAddressBook(String filePath) throws Exception {
        return new JsonTeachBookStorage(Paths.get(filePath)).readTeachBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        TeachBook original = getTypicalAddressBook();
        JsonTeachBookStorage jsonTeachBookStorage = new JsonTeachBookStorage(filePath);

        // Save in new file and read back
        jsonTeachBookStorage.saveTeachBook(original, filePath);
        ReadOnlyTeachBook readBack = jsonTeachBookStorage.readTeachBook(filePath).get();
        assertEquals(original, new TeachBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonTeachBookStorage.saveTeachBook(original, filePath);
        readBack = jsonTeachBookStorage.readTeachBook(filePath).get();
        assertEquals(original, new TeachBook(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonTeachBookStorage.saveTeachBook(original); // file path not specified
        readBack = jsonTeachBookStorage.readTeachBook().get(); // file path not specified
        assertEquals(original, new TeachBook(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyTeachBook addressBook, String filePath) {
        try {
            new JsonTeachBookStorage(Paths.get(filePath))
                    .saveTeachBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new TeachBook(), null));
    }
}
