package seedu.teachbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.teachbook.commons.core.LogsCenter;
import seedu.teachbook.commons.exceptions.DataConversionException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.ReadOnlyUserPrefs;
import seedu.teachbook.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getTeachBookFilePath() {
        return addressBookStorage.getTeachBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTeachBook> readTeachBook() throws DataConversionException, IOException {
        return readTeachBook(addressBookStorage.getTeachBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTeachBook> readTeachBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readTeachBook(filePath);
    }

    @Override
    public void saveTeachBook(ReadOnlyTeachBook addressBook) throws IOException {
        saveTeachBook(addressBook, addressBookStorage.getTeachBookFilePath());
    }

    @Override
    public void saveTeachBook(ReadOnlyTeachBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveTeachBook(addressBook, filePath);
    }

}
