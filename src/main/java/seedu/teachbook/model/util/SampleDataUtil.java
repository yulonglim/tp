package seedu.teachbook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.teachbook.model.ReadOnlyTeachBook;
import seedu.teachbook.model.TeachBook;
import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.student.Address;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Class[] getSampleClasses() {
        return new Class[] {
            new Class(new ClassName("Ace")),
            new Class(new ClassName("Cuddly Bears")),
            new Class(new ClassName("Talent"))
        };
    }

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("classMonitor")),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet()),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet()),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("viceMonitor")),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet()),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet())
        };
    }

    public static ReadOnlyTeachBook getSampleTeachBook() {
        TeachBook sampleTeachBook = new TeachBook();

        Class[] sampleClasses = getSampleClasses();
        for (Class sampleClass: sampleClasses) {
            sampleTeachBook.addClass(sampleClass);
        }

        Student[] sampleStudents = getSampleStudents();
        for (int i = 0; i < sampleStudents.length; i++) {
            Student sampleStudent = sampleStudents[i];
            Class classOfSampleStudent = sampleClasses[i % sampleClasses.length];
            sampleStudent.setStudentClass(classOfSampleStudent);
            classOfSampleStudent.addStudent(sampleStudent);
        }

        return sampleTeachBook;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
