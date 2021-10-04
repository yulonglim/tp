package seedu.teachbook.testutil;

import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.person.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private TeachBook teachBook;

    public AddressBookBuilder() {
        teachBook = new TeachBook();
    }

    public AddressBookBuilder(TeachBook teachBook) {
        this.teachBook = teachBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Student student) {
        teachBook.addStudent(student);
        return this;
    }

    public TeachBook build() {
        return teachBook;
    }
}
