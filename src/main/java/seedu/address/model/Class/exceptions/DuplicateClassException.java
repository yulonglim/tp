package seedu.address.model.Class.exceptions;

/**
 * Signals that the operation will result in duplicate Class (Persons are considered duplicates if they have the same
 * Name).
 */
public class DuplicateClassException extends RuntimeException {
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}
