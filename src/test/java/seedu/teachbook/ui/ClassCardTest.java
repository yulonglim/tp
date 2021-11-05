package seedu.teachbook.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.teachbook.testutil.Assert.assertThrows;

import java.net.URL;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.fxml.FXML;
import seedu.teachbook.MainApp;

public class ClassCardTest {
    private static final String MISSING_FILE_PATH = "UiPartTest/missingFile.fxml";
    private static final String INVALID_FILE_PATH = "UiPartTest/invalidFile.fxml";
    private static final String VALID_FILE_PATH = "UiPartTest/validFile.fxml";
    private static final String VALID_FILE_WITH_FX_ROOT_PATH = "UiPartTest/validFileWithFxRoot.fxml";
    private static final TestFxmlObject VALID_FILE_ROOT = new TestFxmlObject("Hello World!");

    @TempDir
    public Path testFolder;

    @Test
    public void constructor_nullFileUrl_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassCardTest.TestClassCardPart<Object>((URL) null));
        assertThrows(NullPointerException.class, () -> new ClassCardTest.TestClassCardPart<Object>((URL) null,
                new Object()));
    }

    @Test
    public void constructor_missingFileUrl_throwsAssertionError() throws Exception {
        URL missingFileUrl = new URL(testFolder.toUri().toURL(), MISSING_FILE_PATH);
        assertThrows(AssertionError.class, () -> new ClassCardTest.TestClassCardPart<Object>(missingFileUrl));
        assertThrows(AssertionError.class, () -> new ClassCardTest.TestClassCardPart<Object>(missingFileUrl,
                new Object()));
    }

    @Test
    public void constructor_invalidFileUrl_throwsAssertionError() {
        URL invalidFileUrl = getTestFileUrl(INVALID_FILE_PATH);
        assertThrows(AssertionError.class, () -> new ClassCardTest.TestClassCardPart<Object>(invalidFileUrl));
        assertThrows(AssertionError.class, () -> new ClassCardTest.TestClassCardPart<Object>(invalidFileUrl,
                new Object()));
    }

    @Test
    public void constructor_validFileUrl_loadsFile() {
        URL validFileUrl = getTestFileUrl(VALID_FILE_PATH);
        assertEquals(VALID_FILE_ROOT, new ClassCardTest.TestClassCardPart<TestFxmlObject>(validFileUrl).getRoot());
    }

    @Test
    public void constructor_validFileWithFxRootUrl_loadsFile() {
        URL validFileUrl = getTestFileUrl(VALID_FILE_WITH_FX_ROOT_PATH);
        TestFxmlObject root = new TestFxmlObject();
        assertEquals(VALID_FILE_ROOT, new ClassCardTest.TestClassCardPart<TestFxmlObject>(validFileUrl, root).getRoot());
    }

    @Test
    public void constructor_nullFileName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassCardTest.TestClassCardPart<Object>((String) null));
        assertThrows(NullPointerException.class, () -> new ClassCardTest.TestClassCardPart<Object>((String) null,
                new Object()));
    }

    @Test
    public void constructor_missingFileName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassCardTest.TestClassCardPart<Object>(MISSING_FILE_PATH));
        assertThrows(NullPointerException.class, () -> new ClassCardTest.TestClassCardPart<Object>(MISSING_FILE_PATH,
                new Object()));
    }

    @Test
    public void constructor_invalidFileName_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new ClassCardTest.TestClassCardPart<Object>(INVALID_FILE_PATH));
        assertThrows(AssertionError.class, () -> new ClassCardTest.TestClassCardPart<Object>(INVALID_FILE_PATH,
                new Object()));
    }

    private URL getTestFileUrl(String testFilePath) {
        String testFilePathInView = "/view/" + testFilePath;
        URL testFileUrl = MainApp.class.getResource(testFilePathInView);
        assertNotNull(testFileUrl, testFilePathInView + " does not exist.");
        return testFileUrl;
    }

    private static class TestClassCardPart<Region> extends UiPart<Region> {

        @FXML
        private TestFxmlObject validFileRoot; // Check that @FXML annotations work

        TestClassCardPart(URL fxmlFileUrl, Region root) {
            super(fxmlFileUrl, root);
        }

        TestClassCardPart(String fxmlFileName, Region root) {
            super(fxmlFileName, root);
        }

        TestClassCardPart(URL fxmlFileUrl) {
            super(fxmlFileUrl);
            assertEquals(VALID_FILE_ROOT, validFileRoot);
        }

        TestClassCardPart(String fxmlFileName) {
            super(fxmlFileName);
            assertEquals(VALID_FILE_ROOT, validFileRoot);
        }

    }

}

