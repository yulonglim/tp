package seedu.teachbook.testutil;

import seedu.teachbook.model.TeachBook;
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
    }

    public TeachBookBuilder(TeachBook teachBook) {
        this.teachBook = teachBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public TeachBookBuilder withPerson(Student student) {
        teachBook.addStudent(student);
        return this;
    }

    public TeachBook build() {
        return teachBook;
    }
}
