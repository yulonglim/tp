package seedu.teachbook.testutil;

import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class TeachBookBuilder {

    private TeachBook teachBook;

    public TeachBookBuilder() {
        teachBook = new TeachBook();
        teachBook.addClass(new Class(new ClassName("A")));
    }

    public TeachBookBuilder(TeachBook teachBook) {
        this.teachBook = teachBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public TeachBookBuilder withStudent(Student student) {
        teachBook.addStudent(GeneralIndex.fromOneBased(1), student);
        return this;
    }

    public TeachBook build() {
        return teachBook;
    }
}
