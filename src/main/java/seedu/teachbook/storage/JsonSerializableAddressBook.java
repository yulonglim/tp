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
import seedu.teachbook.model.person.Student;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedClass> classes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("classes") List<JsonAdaptedClass> classes) {
        this.persons.addAll(persons);
        this.classes.addAll(classes);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyTeachBook source) {
        persons.addAll(source.getStudentList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        classes.addAll(source.getClassList().stream().map(JsonAdaptedClass::new).collect(Collectors.toList()));
    }

    /**
     * Converts this teachbook book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TeachBook toModelType() throws IllegalValueException {
        TeachBook teachBook = new TeachBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Student student = jsonAdaptedPerson.toModelType();
            if (teachBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            teachBook.addStudent(student);
        }

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
