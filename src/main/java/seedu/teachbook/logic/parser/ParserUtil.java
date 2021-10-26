package seedu.teachbook.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.model.gradeobject.GradingSystem.NOT_GRADED;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.teachbook.commons.core.index.Index;
import seedu.teachbook.commons.core.index.IndexComparator;
import seedu.teachbook.commons.util.StringUtil;
import seedu.teachbook.logic.parser.exceptions.ParseException;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.classobject.ClassNameDescriptor;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.student.Address;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    public static List<Index> parseIndices(String oneBasedIndicesSpaceSeparated) throws ParseException {
        String trimmedIndices = oneBasedIndicesSpaceSeparated.trim();
        String[] indexArray = trimmedIndices.split("\\s+");

        // remove duplicate indices
        Set<String> indexSet = new HashSet<>(Arrays.asList(indexArray));
        String[] rawIndices = indexSet.toArray(new String[0]);

        List<Index> oneBasedIndices = new ArrayList<>();
        for (String rawIndex : rawIndices) {
            oneBasedIndices.add(parseIndex(rawIndex));
        }

        // sort indices
        oneBasedIndices.sort(new IndexComparator());

        return oneBasedIndices;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    public static ClassName parseClassName(String className) throws ParseException {
        requireNonNull(className);
        String trimmedClassName = className.trim();
        if (!ClassName.isValidClassName(trimmedClassName)) {
            throw new ParseException(ClassName.MESSAGE_CONSTRAINTS);
        }
        return new ClassName(trimmedClassName);
    }

    public static ClassNameDescriptor parseClassNameForLocatingClass(String className) {
        requireNonNull(className);
        String trimmedClassName = className.trim();
        return new ClassNameDescriptor(trimmedClassName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String teachbook} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code teachbook} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim();
        if (!Grade.isValidGrade(trimmedGrade)) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return new Grade(trimmedGrade);
    }

    public static List<Grade> parseGrades(String grades) throws ParseException {
        requireNonNull(grades);
        if (grades.endsWith(">")) { // prohibit grades ending with '>'
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        final List<Grade> gradeList = new ArrayList<>();
        String[] stringGradeList = grades.split(">");
        for (String grade : stringGradeList) {
            gradeList.add(parseGrade(grade));
        }
        Set<Grade> gradeSet = new HashSet<>(gradeList);
        if (gradeSet.size() < gradeList.size()) { // gradeList contains repeated grades
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        if (gradeList.stream().anyMatch(grade -> grade.equals(NOT_GRADED))) { // gradeList contains ""
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return gradeList;
    }

}
