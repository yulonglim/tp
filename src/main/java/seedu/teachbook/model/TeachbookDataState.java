package seedu.teachbook.model;

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
}
