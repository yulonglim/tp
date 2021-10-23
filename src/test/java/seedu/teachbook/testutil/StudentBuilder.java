package seedu.teachbook.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.gradeObject.Grade;
import seedu.teachbook.model.student.Address;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.tag.Tag;
import seedu.teachbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_CLASS = "A";
    public static final String DEFAULT_GRADE = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Class myClass;
    private Set<Tag> tags;
    private Grade grade;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        myClass = new Class(new ClassName(DEFAULT_CLASS));
        tags = new HashSet<>();
        grade = new Grade(DEFAULT_GRADE);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code personToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        myClass = studentToCopy.getStudentClass();
        tags = new HashSet<>(studentToCopy.getTags());
        grade = studentToCopy.getGrade();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public StudentBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Sets the {@code Class} of the {@code Student} that we are building.
     */
    public StudentBuilder withClass(String className) {
        this.myClass = new Class(new ClassName(className));
        return this;
    }

    public Student build() {
        return new Student(name, phone, email, address, tags, grade);
    }

}
