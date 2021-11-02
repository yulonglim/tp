package seedu.teachbook.model;

import java.util.Objects;
import java.util.function.Predicate;

import seedu.teachbook.commons.core.index.GeneralIndex;
import seedu.teachbook.model.student.Student;

public class TeachbookDisplayState {
    private final GeneralIndex index;
    private final Predicate<Student> predicate;

    public TeachbookDisplayState(GeneralIndex index, Predicate<Student> predicate) {
        this.index = index;
        this.predicate = predicate;
    }

    public Predicate<Student> getPredicate() {
        return predicate;
    }

    public GeneralIndex getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachbookDisplayState that = (TeachbookDisplayState) o;
        return Objects.equals(index, that.index) && Objects.equals(predicate, that.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, predicate);
    }
}
