package seedu.address.model.Class;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ClassNameContainsKeywordsPredicate implements Predicate<Class> {
    private final List<String> keywords;

    public ClassNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Class classObj) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(classObj.getClassName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ClassNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
