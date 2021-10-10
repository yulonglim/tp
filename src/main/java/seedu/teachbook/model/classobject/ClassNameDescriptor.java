package seedu.teachbook.model.classobject;

import static java.util.Objects.requireNonNull;
import static seedu.teachbook.commons.util.AppUtil.checkArgument;

/**
 * Represents a Class's name descriptor in the teachbook book.
 */
public class ClassNameDescriptor {

    public final String nameOfClass;

    /**
     * Constructs a {@code ClassNameDescriptor}.
     *
     * @param className A class name.
     */
    public ClassNameDescriptor(String className) {
        requireNonNull(className);
        nameOfClass = className;
    }

    @Override
    public String toString() {
        return nameOfClass;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ClassNameDescriptor) {
            return nameOfClass.equals(((ClassNameDescriptor) other).nameOfClass);
        } else if (other instanceof ClassName) {
            return nameOfClass.equals(((ClassName) other).nameOfClass);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return nameOfClass.hashCode();
    }

}
