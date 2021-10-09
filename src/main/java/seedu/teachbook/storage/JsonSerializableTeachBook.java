package seedu.teachbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.teachbook.commons.exceptions.IllegalValueException;
import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.classobject.Class;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableTeachBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "students"
            + " list contains duplicate student(s).";

    private final List<JsonAdaptedClass> classes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students
     * .
     */
    @JsonCreator
    public JsonSerializableTeachBook(@JsonProperty("classes") List<JsonAdaptedClass> classes) {
        this.classes.addAll(classes);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTeachBook(ReadOnlyTeachBook source) {
        classes.addAll(source.getClassList().stream().map(JsonAdaptedClass::new).collect(Collectors.toList()));
    }

    /**
     * Converts this teachbook book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeachBook toModelType() throws IllegalValueException {
        TeachBook teachBook = new TeachBook();

        for (JsonAdaptedClass jsonAdaptedClass : classes) {
            Class classObj = jsonAdaptedClass.toModelType();
            if (teachBook.hasClass(classObj)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            teachBook.addClass(classObj);
        }
        return teachBook;
    }

}
