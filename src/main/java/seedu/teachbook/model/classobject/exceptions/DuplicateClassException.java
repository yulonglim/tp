package seedu.teachbook.model.classobject.exceptions;

/**
 * Signals that the operation will result in duplicate classobject
 * (Persons are considered duplicates if they have the same
 * Name).
 */
public class DuplicateClassException extends RuntimeException {
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}
