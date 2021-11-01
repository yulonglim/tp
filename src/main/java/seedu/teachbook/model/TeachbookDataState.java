package seedu.teachbook.model;

import java.util.Objects;

public class TeachbookDataState {
    private final TeachbookDisplayState displayState;
    private final TeachBook teachBook;


    public TeachbookDataState(TeachbookDisplayState displayState, TeachBook teachBook) {
        this.displayState = displayState;
        this.teachBook = teachBook;
    }

    public TeachbookDisplayState getDisplayState() {
        return displayState;
    }

    public TeachBook getTeachBook() {
        return teachBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeachbookDataState that = (TeachbookDataState) o;
        return Objects.equals(displayState, that.displayState) && Objects.equals(teachBook, that.teachBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayState.hashCode(), teachBook.hashCode());
    }
}
